package com.calendar.scheduler.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.scheduler.model.JobModel;
import com.calendar.scheduler.service.cronformat.CronMakerService;
import com.calendar.scheduler.util.JobUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("QuartzJobService")
public class QuartzJobServiceImpl implements JobService {

	@Autowired
	private CronMakerService cronMapper;
	
	@Override
	public boolean cancelTask(String jobId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addTask(JobModel job) throws SchedulerException, ParseException {
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedFact.getScheduler();
        scheduler.start();
         
        // define the job and tie it to our HelloJob class
        JobBuilder jobBuilder = JobBuilder.newJob(JobThread.class);
         
        JobDetail jobDetail = jobBuilder.usingJobData("joIdb", job.getJobId()).build();

        Map<String, String> parameters = JobUtility.createMapFromJobModel(job.getCronModel());
        
        log.info("Current time: " + new Date()); 
        
        scheduler.scheduleJob(jobDetail, getCronTrigger(parameters));
		return true;
	}
	
	private  Trigger getCronTrigger(Map<String, String> parameters) throws SchedulerException, ParseException {   
		Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(parameters.get("endDate"));  
		
		CronTrigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(cronMapper.createCronExpression(parameters))) 
                .endAt(endDate)
                .build();
        return trigger;
	}
}
