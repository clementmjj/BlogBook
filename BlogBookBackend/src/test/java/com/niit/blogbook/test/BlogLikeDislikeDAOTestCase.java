package com.niit.blogbook.test;

import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.blogbook.dao.BlogLikeDislikeDAO;

public class BlogLikeDislikeDAOTestCase {

	static BlogLikeDislikeDAO blogLikeDislikeDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		blogLikeDislikeDAO = (BlogLikeDislikeDAO) context.getBean("blogLikeDislikeDAO");
	}
}
