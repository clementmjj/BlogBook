package com.niit.blogbook.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.blogbook.dao.ForumDAO;
import com.niit.blogbook.model.Forum;

public class ForumDAOTestCase {

	static ForumDAO forumDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		forumDAO = (ForumDAO) context.getBean("forumDAO");
	}
	
	@Ignore
	@Test
	public void addForumTest()
	{
		Forum forum=new Forum();
		forum.setCreatedDate(new java.util.Date());
		forum.setForumTitle("Forum 3");
		forum.setForumContent("Content of forum 3");
		forum.setForumStatus("NA");
		forum.setUserId(735);
		assertTrue("Problem adding forum", forumDAO.addForum(forum));
	}
	
	@Ignore
	@Test
	public void deleteForumTest()
	{
		Forum forum=forumDAO.getForum(7);
		assertTrue("Problem deleting forum", forumDAO.deleteForum(forum));
	}
	
	@Ignore
	@Test
	public void updateForumTest()
	{
		Forum forum=forumDAO.getForum(8);
		forum.setForumContent("This is the changed forum content");
		assertTrue("Problem updating forum", forumDAO.updateForumDetails(forum));
	}
	
	@Ignore
	@Test
	public void listForumsTest()
	{
		List<Forum> forumList=forumDAO.getForumList();
		for(Forum forum : forumList)
		{
			System.out.print("\n"+forum.getForumId()+"\t");
			System.out.print(forum.getForumTitle()+"\t");
			System.out.println(forum.getForumStatus());
		}
	}
}
