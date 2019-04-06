package com.niit.blogbook.dao;

import java.util.List;

import com.niit.blogbook.model.Job;

public interface JobDAO {
	public boolean addJob(Job job);

	public boolean deleteJob(Job job);

	public boolean updateJobDetails(Job job);

	public boolean openJob(Job job);

	public boolean closeJob(Job job);

	public boolean incrementApplications(Job job);

	public boolean decrementApplications(Job job);

	public Job getJob(int jobId);

	public List<Job> getJobList();

	public List<Job> getLimitedJobList(String username, int startRowNum, int endRowNum);
}
