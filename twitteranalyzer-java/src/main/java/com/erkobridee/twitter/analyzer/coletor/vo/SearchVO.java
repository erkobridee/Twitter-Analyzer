package com.erkobridee.twitter.analyzer.coletor.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchVO implements Serializable {

	private static final long serialVersionUID = 6876150544413452625L;

	private int id;
	private String query;
	private Long sinceId;
	private Date dtExecuted;
	
	private List<TweetVO> tweets;
	
	public SearchVO() {
		this.id = 0;
		this.query = null;
		this.sinceId = null;
		this.dtExecuted = null;
		this.tweets = new ArrayList<TweetVO>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Long getSinceId() {
		return sinceId;
	}

	public void setSinceId(Long sinceId) {
		this.sinceId = sinceId;
	}

	public Date getDtExecuted() {
		return dtExecuted;
	}

	public void setDtExecuted(Date dtExecuted) {
		this.dtExecuted = dtExecuted;
	}

	public List<TweetVO> getTweets() {
		return tweets;
	}

	public void setTweets(List<TweetVO> tweets) {
		this.tweets = tweets;
	}
	
}
