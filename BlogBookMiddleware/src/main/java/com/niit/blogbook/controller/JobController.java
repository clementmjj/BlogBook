package com.niit.blogbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.niit.blogbook.dao.JobDAO;
import com.niit.blogbook.dao.UserDAO;
import com.niit.blogbook.model.Job;
import com.niit.blogbook.model.UserDetail;

@RestController
public class JobController {
	@Autowired
	JobDAO jobDAO;
	@Autowired
	UserDAO userDAO;

	@PostMapping(value = "/addJob")
	public String addJob(@RequestBody Job job) {
		job.setPostedDate(new java.util.Date());
		if (jobDAO.addJob(job)) {
			for (UserDetail user : userDAO.getUserList()) {
				if (user.getUsername().equals(job.getUsername()))
					continue;
			}
			Gson gson = new Gson();
			return gson.toJson(job);
		} else
			return "Error adding job";
	}

	@PostMapping(value = "/updateJob")
	public String updateJob(@RequestBody Job job) {
		job.setPostedDate(job.getPostedDate());
		if (jobDAO.updateJobDetails(job)) {
			Gson gson = new Gson();
			return gson.toJson(job);
		} else
			return "Error updating job";
	}

	@GetMapping(value = "/openJob/{jobId}")
	public String openJob(@PathVariable("jobId") int jobId) {
		Job job = jobDAO.getJob(jobId);
		if (jobDAO.openJob(job)) {
			Gson gson = new Gson();
			return gson.toJson(job);
		} else
			return "Error opening job";
	}

	@GetMapping(value = "/closeJob/{jobId}")
	public String closeJob(@PathVariable("jobId") int jobId) {
		Job job = jobDAO.getJob(jobId);
		if (jobDAO.closeJob(job)) {
			Gson gson = new Gson();
			return gson.toJson(job);
		} else
			return "Error closing job";
	}

	@GetMapping(value = "/deleteJob/{jobId}")
	public String deleteJob(@PathVariable("jobId") int jobId) {
		Job job = jobDAO.getJob(jobId);
		if (jobDAO.deleteJob(job)) {
			Gson gson = new Gson();
			return gson.toJson(jobId);
		} else
			return "Error deleting job";
	}

	@GetMapping(value = "/incrementApplications/{jobId}")
	public String incrementApplications(@PathVariable("jobId") int jobId) {
		Job job = jobDAO.getJob(jobId);
		if (jobDAO.incrementApplications(job)) {
			Gson gson = new Gson();
			return gson.toJson(jobId);
		} else
			return "Error incrementing job apllications";
	}

	@GetMapping(value = "/decrementApplications/{jobId}")
	public String decrementApplications(@PathVariable("jobId") int jobId) {
		Job job = jobDAO.getJob(jobId);
		if (jobDAO.decrementApplications(job)) {
			Gson gson = new Gson();
			return gson.toJson(jobId);
		} else
			return "Error decrementing job apllications";
	}

	@GetMapping(value = "/getJob/{jobId}")
	public String getJob(@PathVariable("jobId") int jobId) {
		Job job = jobDAO.getJob(jobId);
		if (job != null) {
			Gson gson = new Gson();
			return gson.toJson(job);
		} else
			return "Error getting job or it does not exist";
	}

	@GetMapping(value = "/getJobList")
	public String getJobList() {
		List<Job> jobList = jobDAO.getJobList();
		if (jobList != null) {
			Gson gson = new Gson();
			return gson.toJson(jobList);
		} else
			return "Error getting job list.";
	}

	@GetMapping(value = "/getLimitedJobList/{username}/{startRowNum}/{endRowNum}")
	public String getLimitedJobList(@PathVariable("username") String username,
			@PathVariable("startRowNum") int startRowNum, @PathVariable("endRowNum") int endRowNum) {
		List<Job> jobList = jobDAO.getLimitedJobList(username, startRowNum, endRowNum);
		if (jobList != null) {
			Gson gson = new Gson();
			return gson.toJson(jobList);
		} else {
			return "Error getting limited job list.";
		}
	}
}
