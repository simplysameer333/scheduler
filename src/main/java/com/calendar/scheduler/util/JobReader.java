package com.calendar.scheduler.util;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.calendar.scheduler.model.JobModel;

public class JobReader {

    public static List<JobModel> read() throws IOException {
    	String path = new ClassPathResource("sample.txt").getFile().getPath();
        List<String> lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset() );
        List<JobModel> jobList =  new ArrayList<JobModel>();
        for (String line : lines) {
        	String[] values = line.split(",");
        	
        	JobModel job = new JobModel(values[0], values[1], values[2], values[3], values[4], 
        			values[5], values[7], values[8], values[9], values[10], values[11], values[12], values[13], values[14], values[15], values[16]);
        	jobList.add(job);
		}
        return jobList;
    }

}
