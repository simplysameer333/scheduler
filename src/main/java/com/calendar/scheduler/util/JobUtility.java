package com.calendar.scheduler.util;

import java.util.HashMap;
import java.util.Map;

import com.calendar.scheduler.model.CronModel;

public class JobUtility {

	public static Map<String, String> createMapFromJobModel(CronModel job) {
		 Map<String, String> parameters = new HashMap<String, String>();        
		 parameters.put("seconds",job.getSeconds());
	     parameters.put("minutes",job.getMinutes());
	     parameters.put("hours",job.getHours());
	     parameters.put("dayOfMonth",job.getDayOfMonth());
	     parameters.put("month",job.getMonth());
	     parameters.put("dayOfWeek",job.getDayOfWeek());
	     parameters.put("year",job.getYear());
	     parameters.put("recurrence",job.getRecurrenceType());
	     parameters.put("count",job.getCount());
	     parameters.put("endDate",job.getEndDate());
	     
	     return parameters;
	}
	
	public static CronModel createJobModelFromMap( Map<String, String> parameters) {
		CronModel cronModel = new CronModel(parameters.get("seconds"),
				parameters.get("minutes"),
				parameters.get("hours"),
				parameters.get("dayOfMonth"),
				parameters.get("month"),
				parameters.get("dayOfWeek"),
				parameters.get("year"),
				parameters.get("recurrence"),
				parameters.get("count"),
				parameters.get("endDate")				
		);
		
		return cronModel;
	}

}
