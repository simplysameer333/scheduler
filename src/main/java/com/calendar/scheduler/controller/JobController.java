package com.calendar.scheduler.controller;

import java.text.ParseException;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.scheduler.model.JobModel;
import com.calendar.scheduler.service.JobService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class JobController {
	
	@Autowired
	@Qualifier("QuartzJobService")
	private JobService jobService;
	
	@GetMapping(value = "/addTask" )
	public Boolean createJob(JobModel job) {
		log.info("Create JobModel " + job.toString());
		try {
			return jobService.addTask(job);
		} catch (SchedulerException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
