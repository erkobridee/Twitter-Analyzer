package com.erkobridee.twitter.analyzer.dashboard.vo;

import java.io.Serializable;

public class TweetsIndexedByQueryVO implements Serializable {

	private static final long serialVersionUID = 3237936925562282852L;

	//--------------------------------------------------------------------------
	
	private String label;
	private int value;
	
	//--------------------------------------------------------------------------
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	//--------------------------------------------------------------------------	
}
