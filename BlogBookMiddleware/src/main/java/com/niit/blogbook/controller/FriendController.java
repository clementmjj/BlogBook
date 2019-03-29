package com.niit.blogbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.niit.blogbook.dao.FriendDAO;
import com.niit.blogbook.dao.NotificationDAO;
import com.niit.blogbook.model.Friend;
import com.niit.blogbook.model.Notification;
import com.niit.blogbook.model.UserDetail;

@RestController
public class FriendController {
	@Autowired
	FriendDAO friendDAO;
	@Autowired
	NotificationDAO notificationDAO;

	@PostMapping(value = "/sendFriendReq")
	public String sendFriendReq(@RequestBody Friend friend) {
		// the following line is just for testing. delete it after completing frontend
		if (friendDAO.sendFriendReq(friend)) {
			Notification notification = new Notification();
			notification.setNotificationDate(new java.util.Date());
			notification.setStatus("UR");
			notification.setUsername(friend.getFriendUsername());
			notification.setType("New Friend Request");
			notification.setMessage(friend.getUsername() + " has sent you a friend request.");
			notificationDAO.addNotification(notification);
			Gson gson = new Gson();
			return gson.toJson(friend);
		} else {
			return "Error sending friend req";
		}
	}

	@GetMapping(value = "/acceptFriendReq/{friendReqId}")
	public String acceptFriendReq(@PathVariable("friendReqId") int friendId) {
		if (friendDAO.acceptFriendReq(friendId)) {
			Notification notification = new Notification();
			notification.setNotificationDate(new java.util.Date());
			notification.setStatus("UR");
			notification.setUsername(friendDAO.getFriendDetail(friendId).getUsername());
			notification.setType("Friend Request Accepted");
			notification.setMessage(
					friendDAO.getFriendDetail(friendId).getFriendUsername() + " has accepted your friend request.");
			notificationDAO.addNotification(notification);
			Gson gson = new Gson();
			return gson.toJson(friendDAO.getFriendDetail(friendId));
		} else {
			return "Error accepting friend req";
		}
	}

	@GetMapping(value = "/rejectFriendReq/{friendReqId}")
	public String deleteFriendReq(@PathVariable("friendReqId") int friendId) {
		if (friendDAO.deleteFriendReq(friendId)) {
			Gson gson = new Gson();
			return gson.toJson(friendDAO.deleteFriendReq(friendId));
		} else {
			return "Error deleting friend req";
		}
	}

	@GetMapping(value = "/unfriend/{username1}/{username2}")
	public String unfriend(@PathVariable("username1") String username1, @PathVariable("username2") String username2) {
		if (friendDAO.unfriend(username1, username2)) {
			Gson gson = new Gson();
			return gson.toJson(true);
		} else {
			return "Error unfriending " + username1 + " and " + username2;
		}
	}

	@GetMapping(value = "/listFriends/{username}")
	public String getFriendList(@PathVariable("username") String username) {
		List<UserDetail> friendList = friendDAO.getFriendList(username);
		if (friendList != null) {
			Gson gson = new Gson();
			return gson.toJson(friendList);
		} else {
			return "Error getting friend list";
		}
	}

	@GetMapping(value = "/listPendingFriends/{username}")
	public String getPendingFriendList(@PathVariable("username") String username) {
		List<Friend> pendingFriendList = friendDAO.getPendingFriends(username);
		if (pendingFriendList != null) {
			Gson gson = new Gson();
			return gson.toJson(pendingFriendList);
		} else {
			return "Error getting pending friend list";
		}
	}

	@GetMapping(value = "/listSuggestedFriends/{username}")
	public String getSuggestedFriendList(@PathVariable("username") String username) {
		List<UserDetail> suggestedFriendList = friendDAO.getSuggestedFriends(username);
		if (suggestedFriendList != null) {
			Gson gson = new Gson();
			return gson.toJson(suggestedFriendList);
		} else
			return "Error getting suggested friends";
	}
}
