package com.erkobridee.twitter.analyzer.coletor.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import com.erkobridee.twitter.analyzer.coletor.vo.SearchVO;
import com.erkobridee.twitter.analyzer.core.service.ServiceFactory;

public class TaskManager {

	protected static Logger logger = Logger.getLogger("task");
	
	//--------------------------------------------------------------------------
	
	@Autowired
	private ServiceFactory serviceFactory;
	
	private TaskExecutor taskExecutor;
	
	//--------------------------------------------------------------------------
	
	public void setTaskExecutor(TaskExecutor taskExecutor) { 
		this.taskExecutor = taskExecutor;
	}
	
	//--------------------------------------------------------------------------
	
	public TaskManager() {}
	
	//--------------------------------------------------------------------------
	
	public void run() {
		logger.info("Configuring searchs...");
		
		List<SearchVO> searchs = serviceFactory.dbService().findAllSearch();
		
		for(SearchVO search : searchs) {
			this.startSearch(search);
		}
		
	}
	
	//--------------------------------------------------------------------------
	
	private void startSearch(SearchVO search) {
		TaskWork taskWork = new TaskWork(
			search, 
			serviceFactory.dbService(), 
			serviceFactory.twitterService(),
			serviceFactory.tweetsIndexedByQueryService()
		);
		
		logger.info("Start search: " + search.getQuery());
		
		taskExecutor.execute(taskWork);		
	}
	
	
}
