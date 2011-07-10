package com.erkobridee.twitteranalyzer.model.entity
{
	[Bindable]
	[RemoteClass(alias="com.erkobridee.twitter.analyzer.dashboard.vo.StreamVO")]
	public class StreamVO
	{
		
		public var time:Date;
		public var text:String;
		public var userName:String;
		public var userImageUrl:String;
		
		public function StreamVO() {}
	}
}