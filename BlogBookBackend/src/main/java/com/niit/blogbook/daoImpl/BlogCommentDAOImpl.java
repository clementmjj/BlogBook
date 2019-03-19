package com.niit.blogbook.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.blogbook.dao.BlogCommentDAO;
import com.niit.blogbook.model.Blog;
import com.niit.blogbook.model.BlogComment;

@Repository("blogCommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addComment(BlogComment blogComment) {
		try {
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean editComment(BlogComment blogComment) {
		try {
			sessionFactory.getCurrentSession().update(blogComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteComment(BlogComment blogComment) {
		try {
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<BlogComment> getBlogCommentList(int blogId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from BlogComment WHERE blogId = '" + blogId + "'");
		List<BlogComment> blogCommentList = query.list();
		session.close();
		return blogCommentList;
	}

	@Override
	public BlogComment getBlogComment(int commentId) {
		Session session = sessionFactory.openSession();
		BlogComment blogComment = session.get(BlogComment.class, commentId);
		session.close();
		return blogComment;
	}

}
