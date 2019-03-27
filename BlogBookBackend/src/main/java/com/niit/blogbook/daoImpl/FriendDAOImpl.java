package com.niit.blogbook.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.blogbook.dao.FriendDAO;
import com.niit.blogbook.dao.UserDAO;
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
			sessionFactory.getCurrentSession().delete(getFriendDetail(friendId));
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
		Query query = session.createQuery("from Friend where (username = '" + username + "' or friendUsername='"
				+ username + "') and status = 'A'");
		List<Friend> friendList = query.list();
		session.close();
		return friendList;
	}

	@Override
	public List<Friend> getPendingFriends(String username) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Friend where (username = '" + username + "' or friendUsername='"
				+ username + "') and status = 'P'");
		List<Friend> friendList = query.list();
		session.close();
		return friendList;
	}

	@Override
	public List<UserDetail> getSuggestedFriends(String username) {
		UserDAO userDAO = new UserDAOImpl();
		List<UserDetail> suggestedFriendList = new ArrayList<UserDetail>();

		List<Friend> friendList = getFriendList(username);

		for (Friend friend : friendList) {

			String user = "";
			if (friend.getUsername().equals(username))
				user = friend.getFriendUsername();
			else
				user = friend.getUsername();

			System.out.println(username + " " + user);
			List<Friend> friendsFriendList = getFriendList(user);

			for (Friend f : friendsFriendList) {
				if (f.getUsername().equals(username) || f.getFriendUsername().equals(username))
					continue;
				System.out.println("\t" + f.getUsername() + " " + f.getFriendUsername());
				if (f.getUsername().equals(user)) {
					if (!checkIfFriends(username, f.getFriendUsername())) {
						
						suggestedFriendList.add(userDAO.getUser(f.getFriendUsername()));
						System.out.println("added " + f.getFriendUsername());
					}
				} else if (!checkIfFriends(username, f.getUsername())) {
					System.out.println("adding " + f.getUsername());
					suggestedFriendList.add(userDAO.getUser(f.getUsername()));
				}

			}
		}
		return suggestedFriendList;
	}

	@Override
	public boolean checkIfFriends(String username1, String username2) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Friend where ((username = '" + username1 + "' and friendUsername = '"
				+ username2 + "') or (friendUsername = '" + username1 + "' and username = '" + username2
				+ "')) and status = 'A'");
		try {
			query.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}