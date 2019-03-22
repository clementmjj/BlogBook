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
import com.niit.blogbook.dao.ForumDAO;
import com.niit.blogbook.model.Forum;

@RestController
public class ForumController {

	@Autowired
	ForumDAO forumDAO;

	@PostMapping(value = "/addForum")
	public String addForum(@RequestBody Forum forum) {
		// the following line is just for testing. delete it after completing frontend
		forum.setCreatedDate(new java.util.Date());
		if (forumDAO.addForum(forum)) {
			Gson gson = new Gson();
			return gson.toJson(forum);
		} else
			return "Error adding forum";
	}

	@GetMapping(value = "/deleteForum/{forumId}")
	public String deleteForum(@PathVariable("forumId") int forumId) {
		Forum forum = forumDAO.getForum(forumId);
		if (forumDAO.deleteForum(forum)) {
			Gson gson = new Gson();
			return gson.toJson(forum);
		} else
			return "Error deleting forum";
	}

	@PostMapping(value = "/updateForum")
	public String updateForum(@RequestBody Forum forum) {
		// the following line is just for testing. delete it after completing frontend
		forum.setCreatedDate(forumDAO.getForum(forum.getForumId()).getCreatedDate());

		if (forumDAO.updateForumDetails(forum)) {
			Gson gson = new Gson();
			return gson.toJson(forum);
		} else
			return "Error updating forum";
	}

	@GetMapping(value = "/getForum/{forumId}")
	public String getForum(@PathVariable("forumId") int forumId) {
		Forum forum = forumDAO.getForum(forumId);
		if (forum != null) {
			Gson gson = new Gson();
			return gson.toJson(forum);
		} else
			return "Error retrieving forum";
	}

	@GetMapping(value = "/getForumList")
	public ResponseEntity<List<Forum>> getForumList() {
		List<Forum> forumList = forumDAO.getForumList();
		if (forumList != null)
			return new ResponseEntity<List<Forum>>(forumList, HttpStatus.OK);
		else
			return new ResponseEntity<List<Forum>>(forumList, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
