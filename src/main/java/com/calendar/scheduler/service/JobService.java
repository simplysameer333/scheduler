package com.calendar.scheduler.service;

import java.text.ParseException;

import org.quartz.SchedulerException;

import com.calendar.scheduler.model.JobModel;


public interface JobService {
	
	public boolean cancelTask(String jobId);
	
	public boolean addTask(JobModel job) throws SchedulerException, ParseException;

}
