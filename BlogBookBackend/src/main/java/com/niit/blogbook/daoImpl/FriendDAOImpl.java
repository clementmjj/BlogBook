package com.niit.blogbook.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.blogbook.dao.FriendDAO;
import com.niit.blogbook.model.Friend;
import com.niit.blogbook.model.UserDetail;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean sendFriendReq(Friend friend) {
		try {
			friend.setStatus("P");
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean acceptFriendReq(int friendId) {
		try {
			Session session = sessionFactory.openSession();
			Friend friend = session.get(Friend.class, friendId);
			session.close();
			friend.setStatus("A");
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteFriendReq(int friendId) {
		try {
			sessionFactory.getCurrentSession().delete(friendId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Friend getFriendDetail(int friendId) {
		try {
			Session session = sessionFactory.openSession();
			Friend friend = session.get(Friend.class, friendId);
			session.close();
			return friend;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Friend> getFriendList(String username) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Friend where username = '" + username + "' and status = 'A'");
		List<Friend> friendList = query.list();
		session.close();
		return friendList;
	}

	@Override
	public List<Friend> getPendingFriends(String username) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Friend where username = '" + username + "' and status = 'P'");
		List<Friend> friendList = query.list();
		session.close();
		return friendList;
	}

	@Override
	public List<UserDetail> getSuggestedFriends(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}