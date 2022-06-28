package com.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoblaunchController {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	
	@GetMapping("/launchJob")
	public String launchJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters params = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
		
		jobLauncher.run(job, params);
		
		return "JOB LAUNCHED !!!";
		
	}
}
