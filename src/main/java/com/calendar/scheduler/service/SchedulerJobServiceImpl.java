package com.calendar.scheduler.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.PostConstruct;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.calendar.scheduler.model.JobModel;
import com.calendar.scheduler.service.cronformat.CronMakerService;
import com.calendar.scheduler.util.JobReader;
import com.calendar.scheduler.util.JobUtility;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service("SchedulerJobService")
@Slf4j
public class SchedulerJobServiceImpl implements JobService {
	
    private ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    private Map<String, ScheduledFuture<?>> ScheduledFutureMap = new HashMap<String, ScheduledFuture<?>>();
    
    @Autowired
	private CronMakerService maker;

    @Getter
    private List<JobModel> jobList;

    @PostConstruct
    public void startup() throws SchedulerException{
    	 taskScheduler.initialize();
        try {
        	jobList = JobReader.read();
        	if(!jobList.isEmpty())
        		scheduleJobs();
        } catch (IOException e) {
            log.warn("Can't read job file: " + e);
        }
    }

    private void scheduleJobs() throws SchedulerException{
        log.info("Scheduling " + jobList.size() + " jobs...");

        for (JobModel job: jobList) {
        	addTask(job);
        }
    }
    
    @Override
    public boolean cancelTask(String jobId) {
    	ScheduledFuture<?> future = ScheduledFutureMap.get(jobId);
    	if(!future.isCancelled() && !future.isDone()) {
    		future.cancel(true);
    	}
    		
        log.info("Job " + jobId + " is cancelled");
        return true;
    }

    @Override
    public boolean addTask(JobModel job) throws SchedulerException {
    	JobThread j = new JobThread(job);
    	
    	Map<String, String> parameters = JobUtility.createMapFromJobModel(job.getCronModel());
        
    	String cronTabExpression = maker.createCronExpression(parameters);
    	ScheduledFuture<?> future = taskScheduler.schedule(j, new CronTrigger(cronTabExpression));
    	ScheduledFutureMap.put(j.getModel().getJobId(), future);
    		
        log.info("Job is created " + job.toString()); 
        return true;
    }
    
    public void stop() {
        taskScheduler.shutdown();
        taskScheduler.initialize();
        log.info("Jobs stopped");
    }
}
