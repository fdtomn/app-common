package com.nyx.maven.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nyx.maven.base.domain.User;

@Controller
@RequestMapping("/account")
public class LoginController {

	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String goLogin(){
		return "login/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(User user){
		
		//user = userServiceImpl.getUser(user);
		System.out.println("id: "+user.getId());
		return "index";
	}

	public static User getUser(HttpServletRequest request) {
		return (User)request.getSession().getAttribute("user");
	}
}
