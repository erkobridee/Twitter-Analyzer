package com.erkobridee.twitter.analyzer.dashboard.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.erkobridee.twitter.analyzer.dashboard.quartz.work.IWork;

public class WorkJob extends QuartzJobBean 
{
	private IWork work;
	
	public void setWork(IWork work) {
		this.work = work;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		work.doWork();
	}

}
