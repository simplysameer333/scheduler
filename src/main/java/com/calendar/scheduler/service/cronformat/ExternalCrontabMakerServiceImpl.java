package com.calendar.scheduler.service.cronformat;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/* http://www.cronmaker.com/help/rest-api-help.html
 * http://www.cronmaker.com/ 
 */
@Service
@Slf4j
public class ExternalCrontabMakerServiceImpl implements CronMakerService {
	
	private final String EXTERNAL_URL = "http://www.cronmaker.com/rest/";
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String createCronExpression(Map<String, String> parameters) {
		log.info("incoming information " + parameters);
		
		StringBuilder sb = new StringBuilder(EXTERNAL_URL);
		String recurrenceType = parameters.get("recurrence");
		
		if (recurrenceType != null) {
			switch (recurrenceType.toUpperCase()) {
			case "EVERYDAY":
				sb.append("daily/everyDay?");
				sb.append("hour=").append(parameters.get("hours")).append("&minute=").append(parameters.get("minutes"));
				break;
			case "WEEKDAYS":
				sb.append("daily/weekdays?");
				break;
			case "WEEKLY":
				sb.append("weekly?days=");
				break;
			case "MONTHLY":
				sb.append("monthly/day/21/ofEvery/3?");
				break;
			case "YEARLY":
				sb.append("yearly/atDay/18/ofEvery/2?");
				break;
			default:
				break;
		}
		}
		
		System.out.println(sb.toString());
	    String result = restTemplate.getForObject(sb.toString(), String.class);
	  //  result = result.substring(0, result.lastIndexOf('?')+1);
	    log.info("Result Cron " + result);
	    return result;
	}

}
