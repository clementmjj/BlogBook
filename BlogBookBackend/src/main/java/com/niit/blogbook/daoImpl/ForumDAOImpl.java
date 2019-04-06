package com.niit.blogbook.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.blogbook.dao.ForumDAO;
import com.niit.blogbook.model.Blog;
import com.niit.blogbook.model.Forum;
import com.niit.blogbook.model.Friend;

@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateForumDetails(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Forum getForum(int forumId) {

		Session session = sessionFactory.openSession();
		Forum forum = session.get(Forum.class, forumId);
		session.close();
		return forum;
	}

	@Override
	public List<Forum> getForumList() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Forum");
		List<Forum> forumList = query.list();
		return forumList;
	}

	@Override
	public boolean approveForum(Forum forum) {
		forum.setStatus("A");
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean rejectForum(Forum forum) {
		forum.setStatus("R");
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Forum> getLimitedForumList(String username, int startRowNum, int endRowNum) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Friend where (username = '" + username + "' or friendUsername='"
				+ username + "') and status = 'A'");
		List<Friend> friendList = query.list();
		String usernameList = "''";
		if (friendList.size() > 0) {
			for (Friend friend : friendList) {
				usernameList += "'" + (friend.getFriendUsername().equals(username) ? friend.getUsername()
						: friend.getFriendUsername()) + "',";
			}
			usernameList = usernameList.substring(2, usernameList.length() - 1);
		}
		query = session
				.createSQLQuery("select * from ( select a.*, ROWNUM rnum from ( select * from forum where (username IN ("
						+ usernameList + ") AND status = 'A') order by createddate DESC) a where ROWNUM <= " + endRowNum
						+ ") where rnum >= " + startRowNum)
				.addEntity(Forum.class);
		List<Forum> forumList = query.list();
		session.close();
		return forumList;
	}

}
