package com.erkobridee.twitteranalyzer.model.entity
{
	
	[Bindable]
	[RemoteClass(alias="com.erkobridee.twitter.analyzer.dashboard.vo.QueryVO")]
	public class QueryVO
	{
		
		public var id:Number;
		public var name:String;
		public var streamSize:Number;
		
	}
}