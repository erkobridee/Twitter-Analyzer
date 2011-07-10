package com.erkobridee.twitter.analyzer.dashboard.vo;

import java.io.Serializable;
import java.util.Date;

public class StreamVO implements Serializable {

	//--------------------------------------------------------------------------
	
	private static final long serialVersionUID = -659351172712034451L;

	//--------------------------------------------------------------------------
	
	private Date time;
	private String text;
	private String userName;
	private String userImageUrl;
	
	//--------------------------------------------------------------------------
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImageUrl() {
		return userImageUrl;
	}
	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
	
	//--------------------------------------------------------------------------
	
}
