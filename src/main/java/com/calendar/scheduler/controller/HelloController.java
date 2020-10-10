package com.calendar.scheduler.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
/**
 * https://stackoverflow.com/questions/5973797/is-there-any-java-code-for-creating-cron-expression/37403475
 * http://www.cronmaker.com/?1
 * https://examples.javacodegeeks.com/enterprise-java/quartz/quartz-scheduler-cron-expression-example/
 * @author User
 *
 */
public class HelloController {

	@GetMapping(value = "/")
	public String checkApplication() {
		log.info("Test Controller is called");
		return "Test Controller called " + LocalDateTime.now();
	}

}
