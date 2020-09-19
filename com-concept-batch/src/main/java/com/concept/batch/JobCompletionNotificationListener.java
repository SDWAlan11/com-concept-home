package com.concept.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.err.println("It's over" + jobExecution.getJobId());
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.err.println("It's beginning" + jobExecution.getJobId());
	}

}
