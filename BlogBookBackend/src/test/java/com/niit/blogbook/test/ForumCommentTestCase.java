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
		forumComment.setUserId(244);
		assertTrue("Problem adding forum comment", forumCommentDAO.addComment(forumComment));
	}

	@Ignore
	@Test
	public void deleteForumCommentTest() {
		ForumComment forumComment = forumCommentDAO.getComment(9);
		assertTrue("Problem deleting forum comment", forumCommentDAO.deleteComment(forumComment));
	}

	@Ignore
	@Test
	public void editForumCommentTest() {
		ForumComment forumComment = forumCommentDAO.getComment(10);
		forumComment.setCommentMessage("Edited comment");
		assertTrue("Problem editing forum comment", forumCommentDAO.editComment(forumComment));
	}

	//@Ignore
	@Test
	public void listForumCommentsTest() {
		List<ForumComment> forumCommentList = forumCommentDAO.getForumCommentList();
		for (ForumComment fc : forumCommentList) {
			System.out.print("\n" + fc.getCommentId() + "\t");
			System.out.println(fc.getCommentMessage());
		}
	}
}