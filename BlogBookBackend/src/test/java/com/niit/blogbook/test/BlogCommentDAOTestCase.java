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
		blogComment.setUsername("sss");
		assertTrue("Problem adding blog comment", blogCommentDAO.addBlogComment(blogComment));
	}

	@Ignore
	@Test
	public void deleteBlogCommentTest() {
		BlogComment blogComment = blogCommentDAO.getBlogComment(2);
		assertTrue("Problem deleting blog comment", blogCommentDAO.deleteBlogComment(blogComment));
	}

	@Ignore
	@Test
	public void editBlogCommentTest() {
		BlogComment blogComment = blogCommentDAO.getBlogComment(80);
		blogComment.setCommentMessage("Edited comment");
		assertTrue("Problem editing blog comment", blogCommentDAO.editBlogComment(blogComment));
	}

	@Ignore
	@Test
	public void getBlogCommentListTest() {
		List<BlogComment> blogCommentList = blogCommentDAO.getBlogCommentList(95);
		for (BlogComment bc : blogCommentList) {
			System.out.print("\n" + bc.getCommentId() + "\t");
			System.out.println(bc.getCommentMessage());
		}
	}

	@Ignore
	@Test
	public void getBlogComment() {
		BlogComment blogComment = blogCommentDAO.getBlogComment(0);
		if (blogComment == null)
			System.out.print("Blog comment not found.");
		else {
			System.out.print(blogComment.getUsername() + "\t");
			System.out.println(blogComment.getCommentMessage());
		}
	}
}
