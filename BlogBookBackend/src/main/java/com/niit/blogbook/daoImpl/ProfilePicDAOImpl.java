package com.niit.blogbook.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.blogbook.dao.ProfilePicDAO;
import com.niit.blogbook.model.ProfilePic;

@Repository("profilePicDAO")
@Transactional
public class ProfilePicDAOImpl implements ProfilePicDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addProfilePic(ProfilePic profilePic) {
		try {
			sessionFactory.getCurrentSession().save(profilePic);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ProfilePic getProfilePic(String username) {
		Session session = sessionFactory.openSession();
		ProfilePic profilePic = session.get(ProfilePic.class, username);
		session.close();
		return profilePic;
	}

	@Override
	public boolean deleteProfilePic(String username) {
		ProfilePic profilePic = getProfilePic(username);
		try {
			sessionFactory.getCurrentSession().delete(profilePic);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
