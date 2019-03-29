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
import com.niit.blogbook.dao.ForumCommentDAO;
import com.niit.blogbook.dao.ForumDAO;
import com.niit.blogbook.dao.NotificationDAO;
import com.niit.blogbook.model.ForumComment;
import com.niit.blogbook.model.Notification;

@RestController
public class ForumCommentController {

	@Autowired
	ForumCommentDAO forumCommentDAO;
	@Autowired
	ForumDAO forumDAO;
	@Autowired
	NotificationDAO notificationDAO;

	@PostMapping(value = "/addForumComment")
	public String addForumComment(@RequestBody ForumComment forumComment) {
		// the following line is just for testing. delete it after completing frontend
		forumComment.setCommentDate(new java.util.Date());
		if (forumCommentDAO.addForumComment(forumComment)) {
			Notification notification = new Notification();
			notification.setNotificationDate(new java.util.Date());
			notification.setStatus("UR");
			notification.setUsername(forumDAO.getForum(forumComment.getForumId()).getUsername());
			notification.setType("New Forum Comment");
			notification.setMessage("Your forum " + forumDAO.getForum(forumComment.getForumId()).getForumTitle()
					+ " has a new comment.");
			notificationDAO.addNotification(notification);
			Gson gson = new Gson();
			return gson.toJson(forumComment);
		} else {
			return "Error adding forum comment";
		}
	}

	@GetMapping(value = "/deleteForumComment/{forumCommentId}")
	public String deleteForumComment(@PathVariable("forumCommentId") int forumCommentId) {
		ForumComment forumComment = forumCommentDAO.getForumComment(forumCommentId);
		if (forumCommentDAO.deleteForumComment(forumComment)) {
			Gson gson = new Gson();
			return gson.toJson(forumComment);
		} else {
			return "Error deleting forum comment";
		}
	}

	@PostMapping(value = "/editForumComment")
	public String editForumComment(@RequestBody ForumComment forumComment) {
		forumComment.setCommentDate(forumCommentDAO.getForumComment(forumComment.getCommentId()).getCommentDate());
		if (forumCommentDAO.editForumComment(forumComment)) {
			{
				Gson gson = new Gson();
				return gson.toJson(forumComment);
			}
		} else {
			return "Error in editing forum comment";
		}
	}

	@GetMapping(value = "/getForumComment/{forumCommentId}")
	public ResponseEntity<ForumComment> getForumComment(@PathVariable("forumCommentId") int forumCommentId) {
		ForumComment forumComment = forumCommentDAO.getForumComment(forumCommentId);
		if (forumComment != null)
			return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
		else
			return new ResponseEntity<ForumComment>(forumComment, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/getForumCommentList/{forumId}")
	public String getForumCommentList(@PathVariable("forumId") int forumId) {
		List<ForumComment> forumCommentList = forumCommentDAO.getForumCommentList(forumId);
		if (forumCommentList != null) {
			{
				Gson gson = new Gson();
				return gson.toJson(forumCommentList);
			}
		} else {
			return "Forum comment list is empty or error retrieving the list";
		}
	}
}
