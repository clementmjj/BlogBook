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
import com.niit.blogbook.dao.BlogCommentDAO;
import com.niit.blogbook.model.Blog;
import com.niit.blogbook.model.BlogComment;

@RestController
public class BlogCommentController {

	@Autowired
	BlogCommentDAO blogCommentDAO;

	@PostMapping(value = "/addBlogComment")
	public String addBlogComment(@RequestBody BlogComment blogComment) {
		// the following line is just for testing. delete it after completing frontend
		blogComment.setCommentDate(new java.util.Date());
		if (blogCommentDAO.addComment(blogComment)) {
			Gson gson = new Gson();
			return gson.toJson(blogComment);
		} else {
			return "Error adding blog comment";
		}
	}

	@GetMapping(value = "/deleteBlogComment/{blogCommentId}")
	public ResponseEntity<String> deleteBlogComment(@PathVariable("blogCommentId") int blogCommentId) {
		BlogComment blogComment = blogCommentDAO.getBlogComment(blogCommentId);
		if (blogCommentDAO.deleteComment(blogComment)) {
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/updateBlogComment")
	public ResponseEntity<String> updateBlogComment(@RequestBody BlogComment blogComment) {
		blogComment.setCommentDate(blogCommentDAO.getBlogComment(blogComment.getCommentId()).getCommentDate());
		if (blogCommentDAO.editComment(blogComment)) {
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getBlogComment/{blogCommentId}")
	public ResponseEntity<BlogComment> getBlogComment(@PathVariable("blogCommentId") int blogCommentId) {
		BlogComment blogComment = blogCommentDAO.getBlogComment(blogCommentId);
		if (blogComment != null)
			return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
		else
			return new ResponseEntity<BlogComment>(blogComment, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/getBlogCommentList/{blogId}")
	public String getBlogCommentList(@PathVariable("blogId") int blogId) {
		List<BlogComment> blogCommentList = blogCommentDAO.getBlogCommentList(blogId);
		if (blogCommentList != null) {
			{
				Gson gson = new Gson();
				return gson.toJson(blogCommentList);
			}
		} else {
			return "Blog comment list is empty or error retrieving the list";
		}
	}
}
