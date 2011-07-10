package com.erkobridee.twitter.analyzer.coletor.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import com.erkobridee.twitter.analyzer.coletor.service.ITwitterService;
import com.erkobridee.twitter.analyzer.coletor.vo.SearchVO;
import com.erkobridee.twitter.analyzer.coletor.vo.TweetVO;
import com.erkobridee.twitter.analyzer.coletor.vo.TwitterUserVO;

@Service
@Scope("prototype")
public class TwitterService implements ITwitterService {

	protected static Logger logger = Logger.getLogger("service");
	
	public SearchVO doSearch(SearchVO search) {
		Query query = new Query(search.getQuery());
		
		if(
			search.getSinceId() != null && 
			search.getSinceId() != 0
		) {
			query.setSinceId(search.getSinceId());
		}
		
		List<Tweet> tweets = doSearchOnTwitter(query); 
		
		if(tweets != null && tweets.size() > 0) {
			this.processTweets(search, tweets);
			
			boolean executeFlag = true;
			
			while( executeFlag ) {		
			
				query.setMaxId(
					tweets.get(tweets.size()-1).getId()
				);
				tweets = doSearchOnTwitter(query);
			
			
				if( 
					tweets != null && 
					tweets.size() > 0 &&
					query.getMaxId() != tweets.get(tweets.size()-1).getId() 
				) {
					this.processTweets(search, tweets);
					
					executeFlag = true;
				} else {
					executeFlag = false;
				}
			
			}
		}
		
		return search;
		
	}
	
	private List<Tweet> doSearchOnTwitter(Query query) {
		List<Tweet> tweets = null;
		
		try {
			Twitter twitter = new TwitterFactory().getInstance();
			query.setRpp(100);		
			QueryResult result = twitter.search(query);		
			tweets = result.getTweets();
		} catch(TwitterException te) {			
			// log disparado quando n‹o Ž possivel se conectar no twitter para realizar a consulta
			//logger.error(te);
		}
		
		return tweets;
	}
	
	private void processTweets(SearchVO search, List<Tweet> tweets) {
		
		for( Tweet t : tweets ) {
			
			TweetVO tweetVO = new TweetVO();
			tweetVO.setId(t.getId());
			tweetVO.setText(t.getText());
			tweetVO.setDtCreated(t.getCreatedAt());
			
			TwitterUserVO fromUser = new TwitterUserVO();
			fromUser.setDtUpdate(new Date());
			fromUser.setId(t.getFromUserId());
			fromUser.setName(t.getFromUser());
			fromUser.setProfileImageUrl(t.getProfileImageUrl());
			tweetVO.setFromUser(fromUser);
			
			search.getTweets().add(tweetVO);
			
		}
		
	}
		
}
