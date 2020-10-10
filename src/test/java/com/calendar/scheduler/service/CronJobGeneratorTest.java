package com.calendar.scheduler.service;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.calendar.scheduler.model.JobModel;

@SpringBootTest
class CronJobGeneratorTest {
	
	private JobModel job = null;
	
	@Autowired
	@Qualifier("QuartzJobService")
	private JobService jobService;
	
	@Test
	void addJobTest() throws SchedulerException, ParseException {
		job = new JobModel("jobId", "subject", "location", "Every 5 mins", 
				"from", "to", "0", "0", "12",  null, null,  null, null,  null, null, "08/10/2020");
		System.out.println(jobService.addTask(job));
	}
}
