package com.niit.blogbook.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.blogbook.dao.ForumCommentDAO;
import com.niit.blogbook.model.ForumComment;

public class ForumCommentTestCase {
	static ForumCommentDAO forumCommentDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		forumCommentDAO = (ForumCommentDAO) context.getBean("forumCommentDAO");
	}

	@Ignore
	@Test
	public void addForumCommentTest() {
		ForumComment forumComment = new ForumComment();
		forumComment.setCommentDate(new java.util.Date());
		forumComment.setCommentMessage("This is the worst war between 2 neighbouring countries.");
		forumComment.setForumId(34);
		forumComment.setUsername("sss");
		assertTrue("Problem adding forum comment", forumCommentDAO.addForumComment(forumComment));
	}

	@Ignore
	@Test
	public void deleteForumCommentTest() {
		ForumComment forumComment = forumCommentDAO.getForumComment(9);
		assertTrue("Problem deleting forum comment", forumCommentDAO.deleteForumComment(forumComment));
	}

	@Ignore
	@Test
	public void editForumCommentTest() {
		ForumComment forumComment = forumCommentDAO.getForumComment(10);
		forumComment.setCommentMessage("Edited comment");
		assertTrue("Problem editing forum comment", forumCommentDAO.editForumComment(forumComment));
	}

	@Ignore
	@Test
	public void listForumCommentsTest() {
		List<ForumComment> forumCommentList = forumCommentDAO.getForumCommentList(000);
		for (ForumComment fc : forumCommentList) {
			System.out.print("\n" + fc.getCommentId() + "\t");
			System.out.println(fc.getCommentMessage());
		}
	}

	@Ignore
	@Test
	public void getForumCommentTest() {
		ForumComment forumComment = forumCommentDAO.getForumComment(10);
		if (forumComment == null)
			System.out.println("Forum Comment not found.");
		else {
			System.out.print("Forum comment id: " + forumComment.getCommentId());
			System.out.println("by " + forumComment.getUsername());
		}
	}
}
