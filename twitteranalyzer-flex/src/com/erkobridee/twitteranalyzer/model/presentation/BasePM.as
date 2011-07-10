package com.erkobridee.twitteranalyzer.model.presentation
{
	import flash.events.IEventDispatcher;

	[Bindable]
	public class BasePM
	{
		
		[Dispatcher]
		public var dispatcher:IEventDispatcher;
		
		public var currentState:String = "";
		
		public function BasePM() {}
		
	}
}