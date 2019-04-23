package com.niit.blogbook.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.blogbook.dao.JobDAO;
import com.niit.blogbook.model.Job;

public class JobDAOTestCase {
	static JobDAO jobDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		jobDAO = (JobDAO) context.getBean("jobDAO");
	}

	// @Ignore
	@Test
	public void addJobTest() {
		for (int i = 0; i < 50; i++) {
			Job job = new Job();
			job.setJobDesignation("Job title " + (i + 1));
			job.setJobDescription("Job description " + (i + 1));
			job.setJobProfile("Job Profile " + (i + 1));
			job.setJobStatus("open");
			job.setPostedDate(new java.util.Date());
			job.setQualificationRequired("none");
			job.setUsername("michellec");
			assertTrue("Problem adding job", jobDAO.addJob(job));
		}
	}

	@Ignore
	@Test
	public void deleteJobTest() {
		Job job = jobDAO.getJob(4);
		assertTrue("Problem deleting job", jobDAO.deleteJob(job));
	}

	@Ignore
	@Test
	public void updateJobTest() {
		Job job = jobDAO.getJob(5);
		job.setQualificationRequired("B.Tech");
		assertTrue("Problem updating job details", jobDAO.updateJobDetails(job));
	}

	@Ignore
	@Test
	public void openJobTest() {
		Job job = jobDAO.getJob(30);
		assertTrue("Problem opening job", jobDAO.openJob(job));
	}

	@Ignore
	@Test
	public void closeJobTest() {
		Job job = jobDAO.getJob(30);
		assertTrue("Problem closing job", jobDAO.closeJob(job));
	}

	@Ignore
	@Test
	public void getJobTest() {
		Job job = jobDAO.getJob(30);
		if (job == null)
			System.out.println("Job not found");
		else
			System.out.println("Job found; created by " + job.getUsername());
	}

	@Ignore
	@Test
	public void getJobListTest() {
		List<Job> jobList = jobDAO.getJobList();
		for (Job job : jobList) {
			System.out.print("\n" + job.getJobId() + "\t");
			System.out.print(job.getJobDesignation() + "\t");
			System.out.print(job.getJobDescription() + "\t");
			System.out.println(job.getJobStatus());
		}
	}

	@Ignore
	@Test
	public void getUserJobListTest() {
		List<Job> jobList = jobDAO.getUserJobList("aaa");
		for (Job job : jobList) {
			System.out.print("\n" + job.getJobId() + "\t");
			System.out.print(job.getJobDesignation() + "\t");
			System.out.print(job.getJobDescription() + "\t");
			System.out.println(job.getJobStatus());
		}
	}

	@Ignore
	@Test
	public void getLimitedJobListTest() {
		List<Job> jobList = jobDAO.getLimitedJobList("aaa", 0, 4);
		for (Job job : jobList) {
			System.out.print("\n" + job.getJobId() + "\t");
			System.out.print(job.getJobDesignation() + "\t");
			System.out.print(job.getJobDescription() + "\t");
			System.out.println(job.getJobStatus());
		}
	}

	@Ignore
	@Test
	public void jobSearchTest() {
		List<Job> jobList = jobDAO.jobSearch("job");
		for (Job job : jobList) {
			System.out.print("\n" + job.getJobId() + "\t");
			System.out.print(job.getJobDesignation() + "\t");
			System.out.print(job.getJobDescription() + "\t");
			System.out.println(job.getJobStatus());
		}
	}
}
