package com.calendar.scheduler.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.calendar.scheduler.model.JobModel;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString
@Slf4j
public class JobThread implements Runnable, Job {
	private JobModel model;
    public JobThread(JobModel model) {
        this.model = model;
    }

    @Override
    public void run() {        
    	log.info("starting " + model);
    }

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("starting " + model);
		
	}
}
