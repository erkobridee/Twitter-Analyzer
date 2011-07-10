package com.erkobridee.twitter.analyzer.coletor.vo;

import java.io.Serializable;
import java.util.Date;

public class TweetVO implements Serializable {

	private static final long serialVersionUID = 122381401777443005L;

	private Long id;
	private String text;
	private TwitterUserVO fromUser;
	private Date dtCreated;
	
	public TweetVO() {
		this.id = null;
		this.text = null;
		this.fromUser = null;
		this.dtCreated = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public TwitterUserVO getFromUser() {
		return fromUser;
	}

	public void setFromUser(TwitterUserVO fromUser) {
		this.fromUser = fromUser;
	}

	public Date getDtCreated() {
		return dtCreated;
	}

	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}

}
