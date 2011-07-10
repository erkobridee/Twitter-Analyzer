package com.erkobridee.twitter.analyzer.coletor.task;

import org.apache.log4j.Logger;

import com.erkobridee.twitter.analyzer.coletor.service.IDBService;
import com.erkobridee.twitter.analyzer.coletor.service.ITwitterService;
import com.erkobridee.twitter.analyzer.coletor.vo.SearchVO;
import com.erkobridee.twitter.analyzer.core.service.ITweetsIndexedByQueryService;

public class TaskWork implements Runnable {

	//--------------------------------------------------------------------------
	
	protected static Logger logger = Logger.getLogger("task");
	
	//--------------------------------------------------------------------------
	
	private SearchVO search;

	private IDBService dbService;
	
	private ITwitterService twitterService;
	
	private ITweetsIndexedByQueryService tweetsIndexedByQueryService;
	
	private boolean runningFlag = true;
	
	private int delay = 1000;
	
	//--------------------------------------------------------------------------
	
	public TaskWork(
		SearchVO search, 
		IDBService dbService, 
		ITwitterService twitterService,
		ITweetsIndexedByQueryService tweetsIndexedByQueryService
	) {
		this.search = search;
		this.dbService = dbService;
		this.twitterService = twitterService;
		this.tweetsIndexedByQueryService = tweetsIndexedByQueryService;
	}
	
	//--------------------------------------------------------------------------
	
	@Override
	public void run() {
		while(this.runningFlag) {
			
			this.execute();
			
			try { 
				Thread.sleep(this.delay); 
			} catch(InterruptedException e) {
				logger.error(e);
			}
		}
	}
	
	//--------------------------------------------------------------------------

	private void execute() {
		logger.debug("execute search: " + search.getQuery());
		
		search = dbService.findById(search.getId());
		search = twitterService.doSearch(search);
		dbService.saveSearch(search);
		
		tweetsIndexedByQueryService.sendValue(
			search.getQuery(), 
			search.getTweets().size()
		);
	}
	
	//--------------------------------------------------------------------------
}
