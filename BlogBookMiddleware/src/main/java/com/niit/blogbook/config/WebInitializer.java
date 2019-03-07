package com.niit.blogbook.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("---RootConfigClasses---");
		return new Class[] { WebResolver.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("---getServletConfigClasses---");
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("---getServletMappings---");
		return new String[] { "/" };
	}

}
