package com.calendar.scheduler.model;

import java.util.ArrayList;

import com.calendar.scheduler.service.JobThread;


public class Jobs {

    private ArrayList<JobThread> jobThreads;

    public Jobs(ArrayList<JobThread> jobThreads) {
        this.jobThreads = jobThreads;
    }

    public Jobs(){}

    public ArrayList<JobThread> getJobs() {
        return jobThreads;
    }

    public void setJobs(ArrayList<JobThread> jobThreads) {
        this.jobThreads = jobThreads;
    }



    @Override
    public String toString() {
        String result = "jobThreads=\n";
        for (JobThread jobThread : jobThreads) {
            result += jobThread.toString() + "\n";
        }
        return result;
    }
}
