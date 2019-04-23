package com.niit.blogbook.test;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.blogbook.dao.ProfilePicDAO;
import com.niit.blogbook.model.ProfilePic;

public class ProfilePicTestCase {

	static ProfilePicDAO profilePicDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		profilePicDAO = (ProfilePicDAO) context.getBean("profilePicDAO");
	}

	@Ignore
	@Test
	public void getProfilePicTest() {
		ProfilePic profilePic = profilePicDAO.getProfilePic("aaa");
		if (profilePic == null)
			System.out.println("Profile pic not found.");
		else
			System.out.println("Size: " + profilePic.getImage().length + " bytes");
	}

	@Ignore
	@Test
	public void deleteProfilePicTest() {
		ProfilePic profilePic = profilePicDAO.getProfilePic("aaa");
		assertTrue("Problem deleting profile picture.", profilePicDAO.deleteProfilePic("aaa"));
	}
}
