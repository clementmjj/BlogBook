package com.niit.blogbook.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.blogbook.dao.JobDAO;
import com.niit.blogbook.model.Job;
import com.niit.blogbook.model.UserDetail;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteJob(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateJobDetails(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Job getJob(int jobId) {
		Session session = sessionFactory.openSession();
		Job job = session.get(Job.class, jobId);
		session.close();
		return job;
	}

	@Override
	public List<Job> getJobList() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Job");
		List<Job> jobList = query.list();
		session.close();
		return jobList;
	}

}
