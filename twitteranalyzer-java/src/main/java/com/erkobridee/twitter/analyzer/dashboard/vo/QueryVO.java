package com.erkobridee.twitter.analyzer.dashboard.vo;

import java.io.Serializable;

public class QueryVO implements Serializable {

	//--------------------------------------------------------------------------
	
	private static final long serialVersionUID = -4829959786716193130L;
	
	//--------------------------------------------------------------------------
	
	private int id;
	private String name;	
	private int streamSize = 50;
	
	//--------------------------------------------------------------------------
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStreamSize() {
		return streamSize;
	}
	public void setStreamSize(int streamSize) {
		this.streamSize = streamSize;
	}
	
	//--------------------------------------------------------------------------
	
}
