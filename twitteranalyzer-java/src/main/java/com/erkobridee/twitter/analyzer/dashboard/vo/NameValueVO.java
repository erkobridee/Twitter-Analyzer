package com.erkobridee.twitter.analyzer.dashboard.vo;

import java.io.Serializable;

public class NameValueVO implements Serializable {

	//--------------------------------------------------------------------------
	
	private static final long serialVersionUID = -1031152730415030079L;

	//--------------------------------------------------------------------------
	
	private String name;
	private int value;
	
	//--------------------------------------------------------------------------
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	//--------------------------------------------------------------------------
		
}
