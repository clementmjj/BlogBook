package com.niit.blogbook.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.blogbook.dao.NotificationDAO;
import com.niit.blogbook.model.Notification;

public class NotificationDAOTestCase {
	@Autowired
	static NotificationDAO notificationDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		notificationDAO = (NotificationDAO) context.getBean("notificationDAO");
	}

	@Ignore
	@Test
	public void addNotificationTest() {
		Notification notification = new Notification();
		notification.setUsername("f1");
		notification.setType("FriendReq");
		notification.setMessage("New friend req");
		assertTrue("Problem adding notification", notificationDAO.addNotification(notification));
	}

	@Ignore
	@Test
	public void deleteNotificationTest() {
		Notification notification = notificationDAO.getNotification(118);
		assertTrue("Problem deleting notification", notificationDAO.deleteNotification(notification));
	}

	@Ignore
	@Test
	public void markNotificationAsReadTest() {
		assertTrue("Problem marking notification as read", notificationDAO.markNotificationAsRead(119));
	}

	@Ignore
	@Test
	public void markNotificationAsUnreadTest() {
		assertTrue("Problem marking notification as unread", notificationDAO.markNotificationAsUnread(119));
	}

	@Ignore
	@Test
	public void getNotificationTest() {
		Notification notification = notificationDAO.getNotification(119);
		assertTrue("Problem retrieving notification", notification != null);
		System.out.println("id: " + notification.getNotificationId());
		System.out.println("type: " + notification.getType());
	}

	@Ignore
	@Test
	public void getNotificationListTest() {
		List<Notification> notificationList = notificationDAO.getNotificationList("f1");
		assertTrue("Problem listing notifications", notificationList != null);
		for (Notification notification : notificationList) {
			System.out.println("id: " + notification.getNotificationId());
			System.out.println("type: " + notification.getType());
			System.out.println("message: " + notification.getMessage());
		}
	}
}
