package com.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.CustomerClient;
import com.example.model.Merchant;
import com.example.model.list.ListData;
import com.example.util.ProcessUtils;
import com.example.ws.ConnectClearSettle;

@RequestMapping("/process")
@Controller
public class ProcessController {
	
	public ConnectClearSettle cls;
	
	@RequestMapping("login")
	String login(@RequestParam String username,@RequestParam String password,Model model,HttpServletRequest request) {
    		System.out.println("candan");
    		System.out.println("kayabaşı");
    		JSONObject params = new JSONObject();
    		try {
				params.put("email", username);
	    		params.put("password", password);
	    		cls = new ConnectClearSettle();
	    		JSONObject cvp = cls.getDataFromURL(ProcessUtils.URL_LOGIN, params, null);
	    		model.addAttribute("username", username);
	    		model.addAttribute("password", password);
	    		if(cvp != null && cvp.get("token") != null){
	    			model.addAttribute("token", cvp.get("token").toString());
	    			request.getSession().setAttribute("token", cvp.get("token").toString());
	    		}
			} catch (JSONException e) {
				e.printStackTrace();
			}
          return "process/login";
    }
	
	@RequestMapping("client")
	String client(@RequestParam String transactionId,Model model ,HttpServletRequest request) {

		try {
			cls = new ConnectClearSettle();
			JSONObject js = new JSONObject();
			js.put("transactionId",transactionId);
			JSONObject cvp = cls.getDataFromURL(ProcessUtils.URL_CLIENT, js, request.getSession().getAttribute("token").toString());
    		if(cvp != null){
    			List<CustomerClient> mr = new ArrayList<CustomerClient>();
    			mr.add(ProcessUtils.createClientDataFromJSON(cvp));
    			model.addAttribute("customer",mr);
    		}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          return "process/client";
    }
	
	@RequestMapping("merchant")
	String merchant(@RequestParam String transactionId,Model model ,HttpServletRequest request) {

		try {
			cls = new ConnectClearSettle();
			JSONObject js = new JSONObject();
			js.put("transactionId",transactionId);
			JSONObject cvp = cls.getDataFromURL(ProcessUtils.URL_MERCHANT, js, request.getSession().getAttribute("token").toString());
    		if(cvp != null){
    			List<Merchant> mr = new ArrayList<Merchant>();
    			mr.add(ProcessUtils.createMerchantDataFromJSON(cvp));
    			model.addAttribute("merchant",mr);
    		}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          return "process/merchant";
    }
	
	@GetMapping("list")
	String list(Model model ,HttpServletRequest request) {
          return "process/list";
    }
	
	@PostMapping("list")
	String list(@RequestParam String fromDate,@RequestParam String toDate,Model model ,HttpServletRequest request) {
    		System.out.println("canda1n");
    		System.out.println("kayabaşı1");
    		try {
	    		cls = new ConnectClearSettle();
	    		JSONObject js = new JSONObject();
	    		js.put("fromDate",fromDate);
	    		js.put("toDate",toDate);
	    		JSONObject cvp = cls.getDataFromURL(ProcessUtils.URL_LIST, js, request.getSession().getAttribute("token").toString());
	    		if(cvp != null){
	    			model.addAttribute("per_page",cvp.get("per_page").toString());
	    			model.addAttribute("next_page_url",cvp.get("next_page_url").toString());
	    			model.addAttribute("from",cvp.get("from").toString());
	    			model.addAttribute("to",cvp.get("to").toString());
	    			model.addAttribute("current_page",cvp.get("current_page").toString());
	    			model.addAttribute("prev_page_url",cvp.get("prev_page_url").toString());
	    			
	    			JSONArray ja_data = cvp.getJSONArray("data");
	    			int size = (int)cvp.get("to");
	    			List<ListData> list = new ArrayList<ListData>();
	    			for(int i=1; i<size; i++) 
	    			{
	    			    JSONObject jObj = ja_data.getJSONObject(i);
	    			    list.add( ProcessUtils.createListDataFromJSON(jObj));
	    			} 
	    			model.addAttribute("data",list);
	    		}
	    			
			} catch (JSONException e) {
				e.printStackTrace();
			}
          return "process/list";
    }

}
