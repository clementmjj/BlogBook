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

	@Ignore
	@Test
	public void addJobTest() {
		Job job = new Job();
		job.setJobDesignation("IT Professional");
		job.setJobDescription("Professional IT Engineer with minimum 2 years of experience.");
		job.setJobProfile("IT");
		job.setJobStatus("Open");
		job.setPostedDate(new java.util.Date());
		job.setQualificationRequired("MCA");
		assertTrue("Problem adding job", jobDAO.addJob(job));
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
	public void listJobsTest() {
		List<Job> jobList = jobDAO.getJobList();
		for (Job job : jobList) {
			System.out.print("\n" + job.getJobId() + "\t");
			System.out.print(job.getJobDesignation() + "\t");
			System.out.print(job.getJobDescription() + "\t");
			System.out.println(job.getJobStatus());
		}
	}
}