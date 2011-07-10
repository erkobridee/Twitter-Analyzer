package com.erkobridee.twitter.analyzer.coletor.vo;

import java.io.Serializable;
import java.util.Date;

public class TwitterUserVO implements Serializable {

	private static final long serialVersionUID = 4700061356580213560L;

	private Long id;
	private String name;
	private String profileImageUrl;
	private Date dtUpdate;
	
	public TwitterUserVO() {
		this.id = null;
		this.name = null;
		this.profileImageUrl = null;
		this.dtUpdate = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public Date getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(Date dtUpdate) {
		this.dtUpdate = dtUpdate;
	}
	
}
