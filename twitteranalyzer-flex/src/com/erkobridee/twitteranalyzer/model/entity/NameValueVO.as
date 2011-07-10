package com.erkobridee.twitteranalyzer.model.entity
{
	[Bindable]
	[RemoteClass(alias="com.erkobridee.twitter.analyzer.dashboard.vo.NameValueVO")]
	public class NameValueVO
	{
		
		public var name:String;
		public var value:Number;
		
		public function NameValueVO() {}
	}
}