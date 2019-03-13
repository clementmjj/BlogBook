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

import com.niit.blogbook.dao.JobDAO;
import com.niit.blogbook.model.Job;

@RestController
public class JobController {
	@Autowired
	JobDAO jobDAO;

	@PostMapping(value = "/addJob")
	public ResponseEntity<String> addJob(@RequestBody Job job) {
		//the following line is just for testing. delete after frontend is completed
		job.setPostedDate(new java.util.Date());
		if (jobDAO.addJob(job))
			return new ResponseEntity<String>("Successfull", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Unsuccessfull", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/updateJob")
	public ResponseEntity<String> updateJob(@RequestBody Job job) {
		if (jobDAO.updateJobDetails(job))
			return new ResponseEntity<String>("Successfull", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Unsuccessfull", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/deleteJob/{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable("jobId") int jobId) {
		Job job = jobDAO.getJob(jobId);
		if (jobDAO.deleteJob(job))
			return new ResponseEntity<String>("Successfull", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Unsuccessfull", HttpStatus.INTERNAL_SERVER_ERROR);
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
