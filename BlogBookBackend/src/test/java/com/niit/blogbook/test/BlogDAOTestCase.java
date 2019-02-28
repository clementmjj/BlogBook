package com.niit.blogbook.test;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.blogbook.dao.BlogDAO;
import com.niit.blogbook.model.Blog;

import junit.framework.TestCase;

public class BlogDAOTestCase {

	static BlogDAO blogDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		blogDAO = (BlogDAO) context.getBean("blogDAO");
	}

	@Ignore
	@Test
	public void addBlogTest() {
		Blog blog = new Blog();
		blog.setBlogTitle("Blog 1");
		blog.setBlogContent("This is the content of blog 1");
		blog.setCreatedDate(new Date());
		blog.setStatus("NA");
		blog.setUserId(8347);
		assertTrue("Problem adding blog", blogDAO.addBlog(blog));
	}

	@Ignore
	@Test
	public void deleteBlogTest() {
		Blog blog = blogDAO.getBlog(3);
		assertTrue("Problem deleting blog", blogDAO.deleteBlog(blog));
	}

	@Ignore
	@Test
	public void updateBlogTest() {
		Blog blog = blogDAO.getBlog(000);
		blog.setBlogContent("Content of blog1 updated");
		assertTrue("Problem updating blog", blogDAO.updateBlog(blog));
	}

	@Ignore
	@Test
	public void approveBlogTest() {
		Blog blog = blogDAO.getBlog(3);
		assertTrue("Problem approving blog", blogDAO.approveBlog(blog));
	}

	@Ignore
	@Test
	public void rejectBlogTest() {
		Blog blog = blogDAO.getBlog(3);
		assertTrue("Problem rejecting blog", blogDAO.rejectBlog(blog));
	}

	@Ignore
	@Test
	public void listBlogsTest() {
		List<Blog> blogList = blogDAO.getBlogList();
		for (Blog blog : blogList) {
			System.out.print(blog.getBlogId() + "\t");
			System.out.print(blog.getBlogTitle() + "\t");
			System.out.println(blog.getBlogContent() + "\n");
		}
	}
}
