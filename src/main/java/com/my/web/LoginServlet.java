package com.my.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String error = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		token.setRememberMe(true);
		try {
			if(subject.isAuthenticated()){
				System.out.println("已经登录过了");
			} else {
				subject.login(token);
			}
			/*Session session = subject.getSession();
			Collection<Object> keys = session.getAttributeKeys();
			for(Object key : keys){
				System.out.println(key + ": " + session.getAttribute(key));
			}*/
		} catch (UnknownAccountException e) {
			error = "用户名/密码错误";
		} catch (IncorrectCredentialsException e) {
			error = "用户名/密码错误";
		} catch (AuthenticationException e) {
			// 其他错误，比如锁定，如果想单独处理请单独 catch处理
			error = "其他错误：" + e.getMessage();
		}
/*		Enumeration<String> enums =request.getHeaderNames();
		while(enums.hasMoreElements()){
			String head = enums.nextElement();
			System.out.print(head + ": ");
			System.out.print(request.getHeader(head) + "\n");
		}*/
		if (error != null) {// 出错了，返回登录页面
			request.setAttribute("error", error);
			//response.setStatus(303);
			response.sendRedirect("login.jsp");
/*
			request.getRequestDispatcher("/login.jsp").forward(
					request, response);*/
		} else {// 登录成功
			WebUtils.redirectToSavedRequest(request, response, "/success.jsp");
/*			response.sendRedirect("success.jsp");
			request.getRequestDispatcher("/success.jsp")
					.forward(request, response);*/
		}
	}

}
