package com.erkobridee.twitter.analyzer.coletor.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.erkobridee.twitter.analyzer.coletor.dao.ITweetDAO;
import com.erkobridee.twitter.analyzer.coletor.vo.SearchVO;
import com.erkobridee.twitter.analyzer.coletor.vo.TweetVO;
import com.erkobridee.twitter.analyzer.coletor.vo.TwitterUserVO;
import com.erkobridee.twitter.analyzer.core.dao.BaseDAO;

@Scope("prototype")
@Repository
public class TweetDAO extends BaseDAO implements ITweetDAO {

	//--------------------------------------------------------------------------
	
	protected static Logger logger = Logger.getLogger("dao_coletor");
	
	//--------------------------------------------------------------------------
	
	public void save(SearchVO search) {
		logger.debug("save");
		
		for(TweetVO tweet : search.getTweets()) {
			this.processTweet(search.getId(), tweet);
		}
	}
	
	private void processTweet(int searchId, TweetVO tweet) {		
		this.saveTwitterUser(tweet.getFromUser());

		this.saveTweet(tweet);		
		this.saveTweetSearch(searchId, tweet.getId());
	}
	
	private void saveTwitterUser(TwitterUserVO twitterUser) {
		String sql = "";
		Object[] params = null;
		
		boolean flag = super.have("SELECT count(0) FROM twitter_user WHERE id = ?", twitterUser.getId());
		
		if( !flag ) {
			sql = "INSERT INTO twitter_user (id, name, profile_image_url, dt_update) VALUES (?, ?, ?, ?)";
			params = new Object[] {
				twitterUser.getId(),
				twitterUser.getName(),
				twitterUser.getProfileImageUrl(),
				twitterUser.getDtUpdate()
			};
			
			logger.debug("execute insert");
			
		} else {
			Date dtUpdate = null;
			try {
				sql = "SELECT dt_update FROM twitter_user WHERE id = ?";
				dtUpdate = (Date) jdbcTemplate.queryForObject(sql, Date.class, twitterUser.getId());
			} catch(Exception e) {
				logger.error(e);
			}
			
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(dtUpdate);			
			int lastUpdateDayYear = calendar.get(Calendar.DAY_OF_YEAR);
			
			calendar.setTime(twitterUser.getDtUpdate());
			int actualDayYear = calendar.get(Calendar.DAY_OF_YEAR);
			
			if(lastUpdateDayYear < actualDayYear) {
				sql = "UPDATE twitter_user SET name = ?, profile_image_url = ?, dt_update = ? WHERE id = ?";
				params = new Object[] {
					twitterUser.getName(),
					twitterUser.getProfileImageUrl(),
					twitterUser.getDtUpdate(),
					twitterUser.getId()
				};				
			} else {
				sql = null;
			}
		}
		
		if( sql != null ) {
			try {
				jdbcTemplate.update(sql, params);
			} catch(Exception e) {
				logger.error(e);
			}
		}
	}
	
	private void saveTweet(TweetVO tweet) {
		boolean flag = super.have("SELECT count(0) FROM tweet WHERE id = ?", tweet.getId());
		
		if( !flag ) {		
			String sql = "INSERT INTO tweet (id, from_user_id, text, dt_created) VALUES (?, ?, ?, ?)";
			Object[] params = new Object[] {
				tweet.getId(),
				tweet.getFromUser().getId(),
				tweet.getText(),
				tweet.getDtCreated()
			};
			
			try {
				jdbcTemplate.update(sql, params);
			} catch(Exception e) {
				logger.error(e);
			}			
		}
	}

	private void saveTweetSearch(int searchId, Long tweetId) {
		String sql = "INSERT INTO tweet_search (search_id, tweet_id) VALUES (?, ?)";
		Object[] params = new Object[] {searchId, tweetId};
		
		try {
			jdbcTemplate.update(sql, params);
		} catch(Exception e) {
			logger.error(e);
		}
	}
	
}
