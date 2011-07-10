package com.erkobridee.twitter.analyzer.dashboard.vo;

import java.io.Serializable;

public class QueryCountVO implements Serializable {

	//--------------------------------------------------------------------------

	private static final long serialVersionUID = -8753863548276291373L;
	
	//--------------------------------------------------------------------------

	private String name;
	private Long count;
	
	//--------------------------------------------------------------------------
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	//--------------------------------------------------------------------------
		
}
