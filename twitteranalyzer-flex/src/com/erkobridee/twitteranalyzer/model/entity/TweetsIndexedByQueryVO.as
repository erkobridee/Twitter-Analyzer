package com.erkobridee.twitteranalyzer.model.entity
{
	
	[Bindable]
	[RemoteClass(alias="com.erkobridee.twitter.analyzer.dashboard.vo.TweetsIndexedByQueryVO")]
	public class TweetsIndexedByQueryVO
	{
		
		public var label:String;
		public var value:Number;
		
	}
}