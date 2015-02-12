package com.nyx.maven.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
class HomeController {

	@RequestMapping({"","/index"})
	public String goHome(){
		
		System.out.println("-------------");
		return "index";
	}
}
