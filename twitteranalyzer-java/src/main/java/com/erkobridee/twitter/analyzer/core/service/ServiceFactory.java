package com.erkobridee.twitter.analyzer.core.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.erkobridee.twitter.analyzer.coletor.service.IDBService;
import com.erkobridee.twitter.analyzer.coletor.service.ITwitterService;
import com.erkobridee.twitter.analyzer.coletor.service.impl.DBService;
import com.erkobridee.twitter.analyzer.coletor.service.impl.TwitterService;
import com.erkobridee.twitter.analyzer.core.service.impl.TweetsIndexedByQueryService;

// http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/beans.html#beans-classpath-scanning

@Configuration
public class ServiceFactory {

	@Bean
	@Scope("prototype")
	public IDBService dbService() {
		return new DBService();
	}
	
	@Bean
	@Scope("prototype")
	public ITwitterService twitterService() {
		return new TwitterService();
	}
	

	@Bean
	@Scope("prototype")
	public ITweetsIndexedByQueryService tweetsIndexedByQueryService() {
		return new TweetsIndexedByQueryService();
	}
	
}
