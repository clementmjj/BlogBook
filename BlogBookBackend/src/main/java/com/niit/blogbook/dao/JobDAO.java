package com.niit.blogbook.dao;

import java.util.List;

import com.niit.blogbook.model.Job;

public interface JobDAO {
	public boolean addJob(Job job);

	public boolean deleteJob(Job job);

	public boolean updateJobDetails(Job job);
	
	public Job getJob(int jobId);
	
	public List<Job> getJobList();
}
