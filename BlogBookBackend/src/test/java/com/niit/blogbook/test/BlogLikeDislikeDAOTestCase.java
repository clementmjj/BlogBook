package com.niit.blogbook.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.blogbook.dao.BlogLikeDislikeDAO;
import com.niit.blogbook.model.BlogDislike;
import com.niit.blogbook.model.BlogLike;

public class BlogLikeDislikeDAOTestCase {

	static BlogLikeDislikeDAO blogLikeDislikeDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		blogLikeDislikeDAO = (BlogLikeDislikeDAO) context.getBean("blogLikeDislikeDAO");
	}

	@Ignore
	@Test
	public void addBlogLikeTest() {
		BlogLike blogLike = new BlogLike();
		blogLike.setBlogId(20);
		blogLike.setUsername("aaa");
		assertTrue("Problem adding blog like", blogLikeDislikeDAO.addBlogLike(blogLike));
	}

	@Ignore
	@Test
	public void removeBlogLikeTest() {
		BlogLike blogLike = blogLikeDislikeDAO.getBlogLikeById(10);
		assertTrue("Problem deleting blog like", blogLikeDislikeDAO.removeBlogLike(blogLike));
	}

	@Ignore
	@Test
	public void addBlogDislikeTest() {
		BlogDislike blogDislike = new BlogDislike();
		blogDislike.setBlogId(20);
		blogDislike.setUsername("aaa");
		assertTrue("Problem adding blog dislike", blogLikeDislikeDAO.addBlogDislike(blogDislike));
	}

	@Ignore
	@Test
	public void removeBlogDislikeTest() {
		BlogDislike blogDislike = blogLikeDislikeDAO.getBlogDislikeById(10);
		assertTrue("Problem deleting blog dislike", blogLikeDislikeDAO.removeBlogDislike(blogDislike));
	}

	@Ignore
	@Test
	public void getBlogLikeByIdTest() {
		BlogLike blogLike = blogLikeDislikeDAO.getBlogLikeById(10);
		if (blogLike == null)
			System.out.println("Blog like not found");
		else {
			System.out.println("Like id " + blogLike.getBlogLikeId() + " by " + blogLike.getUsername() + " on blog "
					+ blogLike.getBlogId());
		}
	}

	@Ignore
	@Test
	public void getBlogLikeByUserTest() {
		BlogLike blogLike = blogLikeDislikeDAO.getBlogLikeByUser("aaa", 10);
		if (blogLike == null)
			System.out.println("Blog like not found");
		else {
			System.out.println("Like id " + blogLike.getBlogLikeId());
		}
	}

	@Ignore
	@Test
	public void getBlogLikesListTest() {
		List<BlogLike> blogLikesList = blogLikeDislikeDAO.getBlogLikesList("aaa");
		if (blogLikesList.size() > 0) {
			for (BlogLike bl : blogLikesList) {
				System.out.print("Like id: " + bl.getBlogLikeId() + "\t");
				System.out.print("by " + bl.getUsername() + "\t");
				System.out.println("on blog " + bl.getBlogId() + "\t");
			}
		} else
			System.out.println("No likes found");
	}

	@Ignore
	@Test
	public void getBlogDislikeByIdTest() {
		BlogDislike blogDislike = blogLikeDislikeDAO.getBlogDislikeById(10);
		if (blogDislike == null)
			System.out.println("Blog dislike not found");
		else {
			System.out.println("Dislike id " + blogDislike.getBlogDislikeId() + " by " + blogDislike.getUsername()
					+ " on blog " + blogDislike.getBlogId());
		}
	}

	@Ignore
	@Test
	public void getBlogDislikeByUserTest() {
		BlogDislike blogDislike = blogLikeDislikeDAO.getBlogDislikeByUser("aaa", 10);
		if (blogDislike == null)
			System.out.println("Blog dislike not found");
		else {
			System.out.println("Dislike id " + blogDislike.getBlogDislikeId());
		}
	}

	@Ignore
	@Test
	public void getBlogDislikesListTest() {
		List<BlogDislike> blogDislikesList = blogLikeDislikeDAO.getBlogDislikesList("aaa");
		if (blogDislikesList.size() > 0) {
			for (BlogDislike bl : blogDislikesList) {
				System.out.print("Dislike id: " + bl.getBlogDislikeId() + "\t");
				System.out.print("by " + bl.getUsername() + "\t");
				System.out.println("on blog " + bl.getBlogId() + "\t");
			}
		} else
			System.out.println("No dislikes found");
	}
}
