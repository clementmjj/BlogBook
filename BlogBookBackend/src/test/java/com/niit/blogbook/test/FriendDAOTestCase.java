package com.niit.blogbook.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.blogbook.dao.FriendDAO;
import com.niit.blogbook.model.Friend;

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
		friend.setUsername("f2");
		friend.setFriendUsername("f4");
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
		assertTrue("Problem deleting friend req", friendDAO.deleteFriendReq(113));
	}

	@Ignore
	@Test
	public void getFriendListTest() {
		List<Friend> friendList=friendDAO.getFriendList("f1");
		assertTrue("Problem getting friend list", friendList !=null);
		for(Friend friend: friendList)
		{
			System.out.println(friend.getUsername()+" friend of "+friend.getFriendUsername());
		}
	}

	@Ignore
	@Test
	public void getPendingFriendListTest() {
		List<Friend> friendList=friendDAO.getPendingFriends("f1");
		assertTrue("Problem getting friend list", friendList !=null);
		for(Friend friend: friendList)
		{
			System.out.println(friend.getUsername()+" and "+friend.getFriendUsername()+" pending");
		}
	}

	@Ignore
	@Test
	public void getSuggestedFriendListTest() {

	}

}
