package com.erkobridee.twitteranalyzer.component.event
{
	import com.erkobridee.twitteranalyzer.model.entity.QueryVO;
	
	import flash.events.Event;
	
	public class SeeSearchDetailsItemRendererEvent extends Event
	{
		//----------------------------------------------------------------------
	
		public static const SEE_DETAIL:String = "seeDetailEvent";
		
		//----------------------------------------------------------------------
		
		public var query:QueryVO;
		
		//----------------------------------------------------------------------
		
		public function SeeSearchDetailsItemRendererEvent(type:String, bubbles:Boolean=true, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
	}
}