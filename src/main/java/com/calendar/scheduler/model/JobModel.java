package com.calendar.scheduler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class JobModel {
	
	private String jobId;
	private String subject;
	private String location;
	private String body;
	private String from;
	private String to;
	
	private CronModel cronModel;
	
	
	public JobModel(String jobId, String subject, String location, String body, String from, String to,
			String seconds, String minutes, String hours, String dayOfMonth, String month, String dayOfWeek,
			String year, String recurrence, String count, String endDate) {
		super();
		this.jobId = jobId;
		this.subject = subject;
		this.location = location;
		this.body = body;
		this.from = from;
		this.to = to;
		
		this.cronModel = new CronModel(seconds, minutes, hours, dayOfMonth, month, dayOfWeek, year, recurrence, count, endDate);
		
	}
      
}
