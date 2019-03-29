package com.niit.blogbook.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.blogbook.dao.UserDAO;
import com.niit.blogbook.model.UserDetail;

public class UserDAOTestCase {
	static UserDAO userDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
	}

	@Ignore
	@Test
	public void addUserTest() {
		UserDetail user = new UserDetail();
		user.setUsername("f6");
		user.setPassword("pass123");
		user.setFirstName("first name");
		user.setLastName("last name");
		user.setEmail("email@gmail.com");
		user.setRole("student");
		user.setIsOnline("Off");
		user.setStatus("NA");
		assertTrue("Problem adding user", userDAO.addUser(user));
	}

	@Ignore
	@Test
	public void deleteUserTest() {
		UserDetail user = userDAO.getUser("sss");
		assertTrue("Problem deleting user", userDAO.deleteUser(user));
	}

	@Ignore
	@Test
	public void updateUserTest() {
		UserDetail user = userDAO.getUser("sss");
		user.setRole("Admin");
		assertTrue("Problem updating user", userDAO.updateUser(user));
	}

	@Ignore
	@Test
	public void approveUserTest() {
		UserDetail user = userDAO.getUser("clements");
		assertTrue("Problem approving user", userDAO.approveUser(user));
	}

	@Ignore
	@Test
	public void rejectUserTest() {
		UserDetail user = userDAO.getUser("clements12");
		assertTrue("Problem rejecting user", userDAO.rejectUser(user));
	}

	@Ignore
	@Test
	public void getUserTest() {
		UserDetail user = userDAO.getUser("f4");
		assertTrue("Problem retrieving user", userDAO.rejectUser(user));
		System.out.println(user.getFirstName() + "," + user.getEmail());
	}

	@Ignore
	@Test
	public void getUserListTest() {
		List<UserDetail> userList = userDAO.getUserList();
		for (UserDetail user : userList) {
			System.out.println(user.getUsername());
		}
	}
}
