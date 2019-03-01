package com.niit.blogbook.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.blogbook.model.ForumComment;

@Repository("forumCommentDAO")
@Transactional
public class ForumCommentDAOImpl implements ForumCommentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addComment(ForumComment comment) {
		try {
			sessionFactory.getCurrentSession().save(comment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteComment(ForumComment comment) {
		try {
			sessionFactory.getCurrentSession().delete(comment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean editComment(ForumComment comment) {
		try {
			sessionFactory.getCurrentSession().update(comment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ForumComment> getForumCommentList() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from ForumComment");
		List<ForumComment> forumCommentList = query.list();
		session.close();
		return forumCommentList;
	}

	@Override
	public ForumComment getComment(int commentId) {
		Session session = sessionFactory.openSession();
		ForumComment forumComment = session.get(ForumComment.class, commentId);
		session.close();
		return forumComment;
	}

}
