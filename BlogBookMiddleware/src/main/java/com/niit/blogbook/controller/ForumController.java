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

import com.niit.blogbook.dao.ForumDAO;
import com.niit.blogbook.model.Forum;

@RestController
public class ForumController {
	
	@Autowired
	ForumDAO forumDAO;
	
	@PostMapping(value="/addForum")
	public ResponseEntity<String> addForum(@RequestBody Forum forum)
	{
		// the following line is just for testing. delete it after completing frontend
		forum.setCreatedDate(new java.util.Date());
		
		if(forumDAO.addForum(forum))
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/deleteForum/{forumId}")
	public ResponseEntity<String> deleteForum(@PathVariable("forumId") int forumId)
	{
		Forum forum=forumDAO.getForum(forumId);
		if(forumDAO.deleteForum(forum))
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value="/updateForum")
	public ResponseEntity<String> updateForum(@RequestBody Forum forum)
	{
		// the following line is just for testing. delete it after completing frontend
		forum.setCreatedDate(forumDAO.getForum(forum.getForumId()).getCreatedDate());
		
		if(forumDAO.updateForumDetails(forum))
			return new ResponseEntity<String>("Successful", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/getForum/{forumId}")
	public ResponseEntity<Forum> getForum(@PathVariable("forumId") int forumId)
	{
		Forum forum=forumDAO.getForum(forumId);
		if(forum!=null)
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		else
			return new ResponseEntity<Forum>(forum, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/getForumList")
	public ResponseEntity<List<Forum>> getForumList()
	{
		List<Forum> forumList=forumDAO.getForumList();
		if(forumList!=null)
			return new ResponseEntity<List<Forum>>(forumList, HttpStatus.OK);
		else
			return new ResponseEntity<List<Forum>>(forumList, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
