package com.my.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyOnecePerFilter extends OncePerRequestFilter  {
	
	Logger log = LoggerFactory.getLogger(MyOnecePerFilter.class);
	
	public MyOnecePerFilter() {
	}

	@Override
	protected void doFilterInternal(ServletRequest request,
			ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
        log.info("=========once per request filter");  
        chain.doFilter(request, response);  
	}

}
