package com.niit.blogbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.niit.blogbook.dao.BlogDAO;
import com.niit.blogbook.dao.BlogLikeDislikeDAO;
import com.niit.blogbook.model.Blog;
import com.niit.blogbook.model.BlogDislike;
import com.niit.blogbook.model.BlogLike;

@RestController
public class BlogController {
	@Autowired
	BlogDAO blogDAO;
	@Autowired
	BlogLikeDislikeDAO blogLikeDislikeDAO;

	@PostMapping(value = "/addBlog")
	public String addBlog(@RequestBody Blog blog) {
		// the following line is just for testing. delete it after completing frontend
		blog.setCreatedDate(new java.util.Date());

		if (blogDAO.addBlog(blog)) {
			Gson gson = new Gson();
			return gson.toJson(blog);
		} else {
			return "Error adding blog";
		}
	}

	@GetMapping(value = "/deleteBlog/{blogId}")
	public String deleteBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.deleteBlog(blog)) {
			{
				Gson gson = new Gson();
				return gson.toJson(blog);
			}
		} else {
			return "Error deleting blog";
		}
	}

	@PostMapping(value = "/updateBlog")
	public String updateBlog(@RequestBody Blog blog) {
		blog.setCreatedDate(blogDAO.getBlog(blog.getBlogId()).getCreatedDate());
		if (blogDAO.updateBlog(blog)) {
			Gson gson = new Gson();
			return gson.toJson(blog);
		} else {
			return "Error updating blog";
		}
	}

	@GetMapping(value = "/approveBlog/{blogId}")
	public String approveBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.approveBlog(blog)) {
			{
				Gson gson = new Gson();
				return gson.toJson(blog);
			}
		} else {
			return "Error approving blog";
		}
	}

	@GetMapping(value = "/rejectBlog/{blogId}")
	public String rejectBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.rejectBlog(blog)) {
			{
				Gson gson = new Gson();
				return gson.toJson(blog);
			}
		} else {
			return "Error rejecting blog";
		}
	}

	@GetMapping(value = "/getBlogList")
	public ResponseEntity<List<Blog>> getBlogList() {
		List<Blog> blogList = blogDAO.getBlogList();
		if (blogList != null) {
			return new ResponseEntity<List<Blog>>(blogList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Blog>>(blogList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getBlog/{blogId}")
	public String getBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blog != null) {
			Gson gson = new Gson();
			return gson.toJson(blog);
		} else
			return "Error retrieving blog";
	}

	@GetMapping(value = "/incrementLike/{blogId}")
	public String incrementLike(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.incrementLike(blog)) {
			Gson gson = new Gson();
			return gson.toJson(blog);
		} else {
			return "Error incrementing like";
		}
	}

	@GetMapping(value = "/incrementDislike/{blogId}")
	public String incrementDislike(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.incrementDislike(blog)) {
			Gson gson = new Gson();
			return gson.toJson(blog);
		} else {
			return "Error incrementing dislike";
		}
	}

	@GetMapping(value = "/decrementLike/{blogId}")
	public String decrementLike(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.decrementLike(blog)) {
			Gson gson = new Gson();
			return gson.toJson(blog);
		} else {
			return "Error decrement like";
		}
	}

	@GetMapping(value = "/decrementDislike/{blogId}")
	public String decrementDislike(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.decrementDislike(blog)) {
			Gson gson = new Gson();
			return gson.toJson(blog);
		} else {
			return "Error decrement dislike";
		}
	}

}
