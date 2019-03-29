package com.niit.blogbook.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.blogbook.dao.UserDAO;
import com.niit.blogbook.model.UserDetail;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addUser(UserDetail user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteUser(UserDetail user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateUser(UserDetail user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public UserDetail getUser(String username) {
		Session session = sessionFactory.openSession();
		UserDetail user = session.get(UserDetail.class, username);
		session.close();
		return user;
	}

	@Override
	public boolean approveUser(UserDetail user) {
		user.setStatus("A");
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean rejectUser(UserDetail user) {
		user.setStatus("R");
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<UserDetail> getUserList() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from UserDetail");
		List<UserDetail> userList = query.list();
		session.close();
		return userList;
	}

}
