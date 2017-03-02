package com.example;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.util.ProcessUtils;
import com.example.ws.ConnectClearSettle;
 
@Controller
public class HomeController {
	
	private ConnectClearSettle cls;
	
	@GetMapping("")
	public String index(Model model) {

		return "index";
	}
	
	@PostMapping("")
	public String index(@RequestParam String username,@RequestParam String password,Model model,HttpServletRequest request) {
		Optional<Object> token = Optional.ofNullable(request.getAttribute("token"));
		if(!token.isPresent()){
    		System.out.println("candan");
    		System.out.println("kayabaşı");
    		JSONObject params = new JSONObject();
    		try {
				params.put("email", username);
	    		params.put("password", password);
	    		cls = new ConnectClearSettle();
	    		Optional<JSONObject> response = Optional.ofNullable(cls.getDataFromURL(ProcessUtils.URL_LOGIN, params, null,RequestMethod.POST.toString()));
	    		if(response.isPresent()){
	    			JSONObject cvp = response.get();
	    			model.addAttribute("token", cvp.get("token").toString());
	    			request.getSession().setAttribute("token", cvp.get("token").toString());
	    		}
	    		else{
	    			model.addAttribute("loginError","Kullanıcı Adı veya Parola Yanlış");
	    			return "index";
	    		}
			} catch (JSONException e) {
				e.printStackTrace();
				model.addAttribute("loginError","Sistem Girişinde Hata Alındı");
    			return "index";
			}
          return "process/welcome";
		}
		model.addAttribute("token", token.toString());
		request.getSession().setAttribute("token", token.toString());
		return "index";
	}
}
/*
 * Email: ​demo@bumin.com.tr  Password: ​cjaiU8CV 
 * */
 