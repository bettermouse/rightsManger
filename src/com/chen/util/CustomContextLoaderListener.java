package com.chen.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

public class CustomContextLoaderListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		WebApplicationContext webApplicationContext =super.getCurrentWebApplicationContext();
		//ConstantMap.webApplicationContext=webApplicationContext;
		ServletContext servletContext =webApplicationContext.getServletContext();
		//servletContext.setAttribute("constantMap", new ConstantMap());
		servletContext.setAttribute("applicationContextPath", servletContext.getContextPath());
		servletContext.setAttribute("myBaseInfo", BaseInfoUtil.getInstacne().read());
	}

}
