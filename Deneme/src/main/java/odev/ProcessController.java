package odev;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import odev.model.CustomerClient;
import odev.model.Merchant;
import odev.model.list.ListData;
import odev.util.ProcessUtils;
import odev.ws.ConnectClearSettle;

@RequestMapping("/process")
@Controller
public class ProcessController {
	
	public ConnectClearSettle cls;

	@RequestMapping("welcome")
	String welcome(Model model,HttpServletRequest request) {
		Optional<Object> token = Optional.ofNullable(request.getSession().getAttribute("token"));
		if(token.isPresent()){
			return "process/welcome";
		}
		model.addAttribute("loginError","Kullanıcı Girişi Zaman Aşımına Uğradı");
		return "index";
	}
	
	@RequestMapping("client")
	String client(@RequestParam String transactionId,Model model ,HttpServletRequest request) {
		Optional<Object> token = Optional.ofNullable(request.getSession().getAttribute("token"));
		if(!token.isPresent()){
			model.addAttribute("loginError","Kullanıcı Girişi Zaman Aşımına Uğradı");
			return "index";
		}
		try {
			cls = new ConnectClearSettle();
			JSONObject js = new JSONObject();
			js.put("transactionId",transactionId);
			JSONObject cvp = cls.getDataFromURL(ProcessUtils.URL_CLIENT, js, request.getSession().getAttribute("token").toString(),RequestMethod.GET.toString());
    		if(cvp != null){
    			List<CustomerClient> mr = new ArrayList<CustomerClient>();
    			mr.add(ProcessUtils.createClientDataFromJSON(cvp));
    			model.addAttribute("customer",mr);
    		}
    		else{
    			model.addAttribute("customerError", "Customer Bilgisi Sunucudan Alınamadı");
    		}
		} catch (JSONException e) {
			e.printStackTrace();
			model.addAttribute("customerError", "Customer Bilgisi Sunucudan Alınamadı");
		}
          return "process/client";
    }
	
	@RequestMapping("merchant")
	String merchant(@RequestParam String transactionId,Model model ,HttpServletRequest request) {
		Optional<Object> token = Optional.ofNullable(request.getSession().getAttribute("token"));
		if(!token.isPresent()){
			model.addAttribute("loginError","Kullanıcı Girişi Zaman Aşımına Uğradı");
			return "index";
		}
		try {
			cls = new ConnectClearSettle();
			JSONObject js = new JSONObject();
			js.put("transactionId",transactionId);
			JSONObject cvp = cls.getDataFromURL(ProcessUtils.URL_MERCHANT, js, request.getSession().getAttribute("token").toString(),RequestMethod.GET.toString());
    		if(cvp != null){
    			List<Merchant> mr = new ArrayList<Merchant>();
    			mr.add(ProcessUtils.createMerchantDataFromJSON(cvp));
    			model.addAttribute("merchant",mr);
    		}
    		else{
    			model.addAttribute("merchantError","Merchant Bilgisi Sunucudan Alınamadı");
    		}
		} catch (JSONException e) {
			e.printStackTrace();
			model.addAttribute("merchantError","Merchant Bilgisi Sunucudan Alınamadı");
		}
          return "process/merchant";
    }
	
	@GetMapping("list")
	String list(@RequestParam(required=false) String fromDate,@RequestParam(required=false) String toDate,
			@RequestParam(required=false) String page,Model model ,HttpServletRequest request) {
		Optional<Object> token = Optional.ofNullable(request.getSession().getAttribute("token"));
		if(token.isPresent()){
			cls = new ConnectClearSettle();
			if(page != null && !page.equals("") && 
					fromDate != null && !fromDate.equals("") &&
					toDate != null && !toDate.equals("")){
				JSONObject js = new JSONObject();
	    		try {
					js.put("fromDate",fromDate);
		    		js.put("toDate",toDate);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				model = getListModel(fromDate,toDate,model,
						cls.getDataFromURL(page, js, request.getSession().getAttribute("token").toString(),RequestMethod.GET.toString()));
			}
			return "process/list";
		}
		else {
			model.addAttribute("loginError","Kullanıcı Girişi Zaman Aşımına Uğradı");
			return "index";
		}
    }
	
	private Model getListModel(String fromDate,String toDate,Model model,JSONObject cvp){
		try{
			model.addAttribute("per_page",cvp.get("per_page").toString());
			model.addAttribute("next_page_url",cvp.get("next_page_url").toString());
			model.addAttribute("from",cvp.get("from").toString());
			model.addAttribute("to",cvp.get("to").toString());
			model.addAttribute("current_page",cvp.get("current_page").toString());
			model.addAttribute("prev_page_url",cvp.get("prev_page_url").toString());
			
			JSONArray ja_data = cvp.getJSONArray("data");
			int size = (int)ja_data.length();
			List<ListData> list = new ArrayList<ListData>();
			for(int i=1; i<size; i++) 
			{
			    JSONObject jObj = ja_data.getJSONObject(i);
			    list.add( ProcessUtils.createListDataFromJSON(jObj));
			}
			if(!list.isEmpty())
				model.addAttribute("data",list);
			if(fromDate!= null && !fromDate.equals(""))
				model.addAttribute("fromDate",fromDate);
			if(toDate!= null && !toDate.equals(""))
	    		model.addAttribute("toDate",toDate);
			return model;
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@PostMapping("list")
	String list(@RequestParam(required=false) String fromDate,@RequestParam(required=false) String toDate,
			Model model ,HttpServletRequest request) {
		Optional<Object> token = Optional.ofNullable(request.getSession().getAttribute("token"));
		if(!token.isPresent()){
			model.addAttribute("loginError","Kullanıcı Girişi Zaman Aşımına Uğradı");
			return "index";
		}
		else {
			if(fromDate.equals("") || toDate.equals("")){
				model.addAttribute("tarihlerBos","Başlangıç veya Bitiş Tarihi Girilmedi");
				return "process/list";
			}
			else if(!fromDate.equals("") || !toDate.equals("")){
				cls = new ConnectClearSettle();
				JSONObject js = new JSONObject();
	    		try {
					js.put("fromDate",fromDate);
		    		js.put("toDate",toDate);
				} catch (JSONException e) {
					e.printStackTrace();
				}
	    		model = getListModel(fromDate,toDate,model,
	    				cls.getDataFromURL(ProcessUtils.URL_LIST, js, request.getSession().getAttribute("token").toString(), RequestMethod.POST.toString()));
			}
		}
          return "process/list";
    }
	
	@GetMapping(value="report")
	String report(Model model ,HttpServletRequest request){
		Optional<Object> token = Optional.ofNullable(request.getSession().getAttribute("token"));
		if(!token.isPresent()){
			model.addAttribute("loginError","Kullanıcı Girişi Zaman Aşımına Uğradı");
			return "index";
		}
		return "process/report";
	}
	
	@GetMapping(value="reportData")
	@ResponseBody
	String report(@RequestParam String data,Model model ,HttpServletRequest request) {
		Optional<Object> token = Optional.ofNullable(request.getSession().getAttribute("token"));
		if(!token.isPresent()){
			model.addAttribute("loginError","Kullanıcı Girişi Zaman Aşımına Uğradı");
			return "index";
		}
		else {
			if(data== null || data.equals(""))
				return "process/report";
			JSONObject json=null,cvp=null;
			try {
				cls = new ConnectClearSettle();
				json = new JSONObject(data);
				cvp = cls.getDataFromURL(ProcessUtils.URL_REPORT, json, request.getSession().getAttribute("token").toString(),RequestMethod.GET.toString());
			} catch (JSONException e) {
				e.printStackTrace();
				return "error";
			}
			model.addAttribute("data",cvp);
			return cvp.toString();
		}
    }

}
