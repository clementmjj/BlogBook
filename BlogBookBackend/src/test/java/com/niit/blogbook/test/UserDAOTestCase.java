package com.niit.blogbook.test;

import static org.junit.Assert.assertTrue;

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
		user.setFirstName("Clement");
		user.setLastName("S");
		user.setEmail("clement@gmail.com");
		user.setRole("user");
		user.setIsOnline("True");
		user.setStatus("Active");
		assertTrue("Problem adding user", userDAO.addUser(user));
	}

	@Ignore
	@Test
	public void deleteUserTest() {
		UserDetail user = userDAO.getUser(7);
		assertTrue("Problem deleting user", userDAO.deleteUser(user));
	}

	@Ignore
	@Test
	public void updateUserTest() {
		UserDetail user = userDAO.getUser(6);
		user.setRole("Admin");
		assertTrue("Problem updating user", userDAO.updateUser(user));
	}
}
