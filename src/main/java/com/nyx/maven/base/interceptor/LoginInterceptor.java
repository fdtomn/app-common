package com.nyx.maven.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nyx.maven.base.controller.LoginController;
import com.nyx.maven.base.domain.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	static Logger logger = Logger.getLogger(LoginInterceptor.class);

	/**
	 * 忽略的url 不需要拦截
	 */
	// private static final String[] IGNORE_URI = { "/account/login" };

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("after..........");
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("post................");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String uri = request.getRequestURI();
		logger.info(uri);
		User user = LoginController.getUser(request);
		// 用户没有登录
		if (user == null) {
			//记录用户的请求 登录后跳转到对应的页面
			request.getSession().setAttribute("uri", uri);
			response.sendRedirect(request.getContextPath() + "/account/login");
		}
		return true;
	}

}
