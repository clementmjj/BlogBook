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

import com.niit.blogbook.dao.BlogDAO;
import com.niit.blogbook.model.Blog;

@RestController
public class BlogController {
	@Autowired
	BlogDAO blogDAO;

	@PostMapping(value = "/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog) {
		// the following line is just for testing. delete it after completing frontend
		blog.setCreatedDate(new java.util.Date());

		if (blogDAO.addBlog(blog)) {
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.deleteBlog(blog)) {
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/updateBlog")
	public ResponseEntity<String> updateBlog(@RequestBody Blog blog) {
		blog.setCreatedDate(blogDAO.getBlog(blog.getBlogId()).getCreatedDate());
		if (blogDAO.updateBlog(blog)) {
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/approveBlog/{blogId}")
	public ResponseEntity<String> approveBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.approveBlog(blog)) {
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/rejectBlog/{blogId}")
	public ResponseEntity<String> rejectBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.rejectBlog(blog)) {
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
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
	public ResponseEntity<Blog> getBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blog != null)
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		else
			return new ResponseEntity<Blog>(blog, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/incrementLike/{blogId}")
	public ResponseEntity<String> incrementLike(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.incrementLike(blog)) {
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/incrementDislike/{blogId}")
	public ResponseEntity<String> incrementDislike(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.incrementDislike(blog)) {
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/decrementLike/{blogId}")
	public ResponseEntity<String> decrementLike(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.decrementLike(blog)) {
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/decrementDislike/{blogId}")
	public ResponseEntity<String> decrementDislike(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.decrementDislike(blog)) {
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
