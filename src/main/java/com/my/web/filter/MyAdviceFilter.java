package com.my.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAdviceFilter extends AdviceFilter {
	
	Logger log = LoggerFactory.getLogger(MyAdviceFilter.class);
	
    @Override  
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {  
    	log.info("====预处理/前置处理");  
        return true;//返回false将中断后续拦截器链的执行  
    }  
    @Override  
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {  
    	log.info("====后处理/后置返回处理");  
    }  
    @Override  
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {  
    	log.info("====完成处理/后置最终处理");  
    }  
}
