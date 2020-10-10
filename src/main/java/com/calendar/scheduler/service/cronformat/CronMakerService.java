package com.calendar.scheduler.service.cronformat;

import java.util.Map;

import org.quartz.SchedulerException;

public interface CronMakerService {
	
	public String createCronExpression(Map<String, String> parameters) throws SchedulerException;

}
