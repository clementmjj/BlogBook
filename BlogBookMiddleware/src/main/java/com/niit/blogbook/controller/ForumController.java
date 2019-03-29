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
import com.niit.blogbook.dao.FriendDAO;
import com.niit.blogbook.dao.NotificationDAO;
import com.niit.blogbook.model.Forum;
import com.niit.blogbook.model.Notification;
import com.niit.blogbook.model.UserDetail;

@RestController
public class ForumController {

	@Autowired
	ForumDAO forumDAO;
	@Autowired
	FriendDAO friendDAO;
	@Autowired
	NotificationDAO notificationDAO;

	@PostMapping(value = "/addForum")
	public String addForum(@RequestBody Forum forum) {
		forum.setCreatedDate(new java.util.Date());
		if (forumDAO.addForum(forum)) {
			List<UserDetail> friendList = friendDAO.getFriendList(forum.getUsername());
			for (UserDetail user : friendList) {
				Notification notification = new Notification();
				notification.setNotificationDate(new java.util.Date());
				notification.setStatus("UR");
				notification.setUsername(user.getUsername());
				notification.setType("Forum Added");
				notification.setMessage(forum.getUsername() + " has added a new forum.");
				notificationDAO.addNotification(notification);
			}
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
		forum.setCreatedDate(forumDAO.getForum(forum.getForumId()).getCreatedDate());

		if (forumDAO.updateForumDetails(forum)) {
			Gson gson = new Gson();
			return gson.toJson(forum);
		} else
			return "Error updating forum";
	}

	@GetMapping(value = "/approveForum/{forumId}")
	public String approveForum(@PathVariable("forumId") int forumId) {
		Forum forum = forumDAO.getForum(forumId);
		if (forumDAO.approveForum(forum)) {
			{
				Notification notification = new Notification();
				notification.setNotificationDate(new java.util.Date());
				notification.setStatus("UR");
				notification.setUsername(forum.getUsername());
				notification.setType("Forum Approved");
				notification.setMessage("Your forum " + forum.getForumTitle() + " has been approved.");
				notificationDAO.addNotification(notification);
				Gson gson = new Gson();
				return gson.toJson(forum);
			}
		} else {
			return "Error approving forum";
		}
	}

	@GetMapping(value = "/rejectForum/{forumId}")
	public String rejectForum(@PathVariable("forumId") int forumId) {
		Forum forum = forumDAO.getForum(forumId);
		if (forumDAO.rejectForum(forum)) {
			{
				Notification notification = new Notification();
				notification.setNotificationDate(new java.util.Date());
				notification.setStatus("UR");
				notification.setUsername(forum.getUsername());
				notification.setType("Forum Rejected");
				notification.setMessage("Your forum " + forum.getForumTitle() + " has been rejected.");
				notificationDAO.addNotification(notification);
				Gson gson = new Gson();
				return gson.toJson(forum);
			}
		} else {
			return "Error rejecting forum";
		}
	}

	@GetMapping(value = "/getForumList")
	public ResponseEntity<List<Forum>> getForumList() {
		List<Forum> forumList = forumDAO.getForumList();
		if (forumList != null)
			return new ResponseEntity<List<Forum>>(forumList, HttpStatus.OK);
		else
			return new ResponseEntity<List<Forum>>(forumList, HttpStatus.INTERNAL_SERVER_ERROR);
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
}
