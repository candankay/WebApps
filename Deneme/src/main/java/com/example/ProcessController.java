package com.example;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.util.ProcessUtils;
import com.example.ws.ConnectClearSettle;

@RequestMapping("/process")
@Controller
public class ProcessController {
	
	public ConnectClearSettle cls;
	
	@RequestMapping("login")
	String login(@RequestParam String username,@RequestParam String password,Model model) {
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
	    		if(cvp != null && cvp.get("token") != null)
	    			model.addAttribute("token", cvp.get("token").toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
          return "process/login";
    }

}
