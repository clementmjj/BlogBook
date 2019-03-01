package com.niit.blogbook.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.blogbook.dao.BlogCommentDAO;
import com.niit.blogbook.model.BlogComment;

public class BlogCommentDAOTestCase {
	static BlogCommentDAO blogCommentDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		blogCommentDAO = (BlogCommentDAO) context.getBean("blogCommentDAO");
	}
	
	@Ignore
	@Test
	public void addBlogCommentTest() {
		BlogComment blogComment = new BlogComment();
		blogComment.setBlogId(1);
		blogComment.setCommentDate(new java.util.Date());
		blogComment.setCommentMessage("My first comment");
		blogComment.setUserId(345);
		assertTrue("Problem adding blog comment", blogCommentDAO.addComment(blogComment));
	}
	
	@Ignore
	@Test
	public void deleteBlogCommentTest() {
		BlogComment blogComment = blogCommentDAO.getBlogComment(2);
		assertTrue("Problem deleting blog comment", blogCommentDAO.deleteComment(blogComment));
	}
	
	@Ignore
	@Test
	public void editBlogCommentTest() {
		BlogComment blogComment = blogCommentDAO.getBlogComment(3);
		blogComment.setCommentMessage("Edited comment");
		assertTrue("Problem editing blog comment", blogCommentDAO.editComment(blogComment));
	}
	
	@Ignore
	@Test
	public void listBlogCommentsTest() {
		List<BlogComment> blogCommentList = blogCommentDAO.getCommentList();
		for(BlogComment bc : blogCommentList)
		{
			System.out.print("\n"+bc.getCommentId()+"\t");
			System.out.println(bc.getCommentMessage());
		}
	}
}