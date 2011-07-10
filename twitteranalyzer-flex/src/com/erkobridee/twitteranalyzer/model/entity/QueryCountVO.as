package com.erkobridee.twitteranalyzer.model.entity
{
	[Bindable]
	[RemoteClass(alias="com.erkobridee.twitter.analyzer.dashboard.vo.QueryCountVO")]
	public class QueryCountVO
	{
		
		public var name:String;
		public var count:Number;
		
	}
}