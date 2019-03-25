package com.niit.blogbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.niit.blogbook.dao.NotificationDAO;
import com.niit.blogbook.model.Notification;

@RestController
public class NotificationController {
	@Autowired
	NotificationDAO notificationDAO;
	
	@GetMapping(value = "/getNotifications/{username}")
	public String listNotifications(@PathVariable("username") String username) {
		List<Notification> notificationList=notificationDAO.getNotificationList(username);
		if (notificationList!=null) {
			{
				Gson gson = new Gson();
				return gson.toJson(notificationList);
			}
		} else {
			return "Error getting notification list";
		}
	}
}
