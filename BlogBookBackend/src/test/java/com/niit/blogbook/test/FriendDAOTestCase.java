package com.niit.blogbook.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.blogbook.dao.FriendDAO;
import com.niit.blogbook.model.Friend;
import com.niit.blogbook.model.UserDetail;

public class FriendDAOTestCase {

	static FriendDAO friendDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		friendDAO = (FriendDAO) context.getBean("friendDAO");
	}

	@Ignore
	@Test
	public void sendFriendRequestTest() {
		Friend friend = new Friend();
		friend.setUsername("f3");
		friend.setFriendUsername("f5");
		assertTrue("Problem adding friend req", friendDAO.sendFriendReq(friend));
	}

	@Ignore
	@Test
	public void acceptFriendRequestTest() {
		assertTrue("Problem accepting friend req", friendDAO.acceptFriendReq(114));
	}

	@Ignore
	@Test
	public void deleteFriendRequestTest() {
		assertTrue("Problem deleting friend req", friendDAO.deleteFriendReq(116));
	}

	@Ignore
	@Test
	public void getFriendListTest() {
		String username = "f1";
		List<Friend> friendList = friendDAO.getFriendList(username);
		assertTrue("Problem getting friend list", friendList != null);
		for (Friend friend : friendList) {
			if (friend.getUsername().equals(username))
				System.out.println(friend.getFriendUsername());
			else
				System.out.println(friend.getUsername());
		}
	}

	@Ignore
	@Test
	public void getPendingFriendListTest() {
		List<Friend> friendList = friendDAO.getPendingFriends("f1");
		assertTrue("Problem getting pending friend list", friendList != null);
		for (Friend friend : friendList) {
			System.out.println(friend.getUsername() + " and " + friend.getFriendUsername() + " pending");
		}
	}

	//@Ignore
	@Test
	public void getSuggestedFriendListTest() {
		String username = "f1";
		List<UserDetail> suggestedFriendList = friendDAO.getSuggestedFriends(username);
		System.out.println("Suggested friends: ");
		for(UserDetail user:suggestedFriendList)
			System.out.println(user.getUsername());
		assertTrue("Problem getting suggested friend list", suggestedFriendList != null);
		
	}

	@Ignore
	@Test
	public void getFriendStatusTest() {
		if (friendDAO.checkIfFriends("f2", "f1"))
			System.out.println("They are friends");
		else
			System.out.println("They are not friends");
	}
}
