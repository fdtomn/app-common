package com.nyx.maven.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nyx.maven.base.domain.User;

@Controller
@RequestMapping("/account")
public class LoginController {

	Logger logger = Logger.getLogger(LoginController.class);
	
	/**
	 * 跳转到登陆界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String goLogin(HttpServletRequest request){
		
		if(getUser(request) == null){
			//用户未登录过跳转到登陆页面
			return "login/login";
		}else{
			//用户已经登录
			return "index";
		}
		
	}
	
	/**
	 * 执行真正的登录方法
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(User user, HttpServletRequest request,ModelMap modelMap){
		user.setId(System.currentTimeMillis()+"");
		if(getUser(request) == null){
			request.getSession().setAttribute("user", user);
		}
		//user = userServiceImpl.getUser(user);
		
		modelMap.put("user", user);
		//重定向到首页面
		return "redirect:/index";
	}

	/**
	 * 注销用户
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		if(session != null){
			session.removeAttribute("user");
		}
		
		logger.info("注销......");
		//重定向到登陆界面
		return "redirect:/account/login";
	}
	
	/**
	 * 从session中获取用户
	 * @param request
	 * @return
	 */
	public static User getUser(HttpServletRequest request) {
		return (User)request.getSession().getAttribute("user");
	}
}
