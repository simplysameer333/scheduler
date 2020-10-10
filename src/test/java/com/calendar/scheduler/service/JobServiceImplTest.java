package com.calendar.scheduler.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.calendar.scheduler.model.JobModel;
import com.calendar.scheduler.service.cronformat.CronMakerService;

@SpringBootTest
class JobServiceImplTest {
	
	private JobModel job = null;
	
	@Autowired
	private CronMakerService cronMapper;
	
	@Autowired
	@Qualifier("QuartzJobService")
	private JobService jobService;
	
	@Test	
	void specificTimeTest() throws SchedulerException {
		Map<String, String> parameters = new HashMap<String, String>();		
		parameters.put("seconds", "0");
	    parameters.put("minutes", "21");
	    parameters.put("hours", "13");
	    parameters.put("dayOfMonth", "08");
	    parameters.put("month", "10");
	    parameters.put("dayOfWeek", "*");
	    parameters.put("year", "2020");
	    parameters.put("recurrence", "EVERYDAY");
	    parameters.put("count", "0");
	    
	    
		String cronExp = cronMapper.createCronExpression(parameters);
		System.out.println(cronExp);
	}
	
	
	
}
