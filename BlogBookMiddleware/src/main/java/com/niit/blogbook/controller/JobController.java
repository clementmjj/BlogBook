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

	@GetMapping(value = "/getJob/{jobId}")
	public ResponseEntity<Job> getJob(@PathVariable("jobId") int jobId) {
		Job job = jobDAO.getJob(jobId);
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	@GetMapping(value = "/getJobList")
	public ResponseEntity<List<Job>> getJobList() {
		List<Job> jobList = jobDAO.getJobList();
		return new ResponseEntity<List<Job>>(jobList, HttpStatus.OK);
	}
}
