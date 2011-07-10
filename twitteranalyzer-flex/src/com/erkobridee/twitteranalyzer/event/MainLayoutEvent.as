package com.erkobridee.twitteranalyzer.event
{
	import flash.events.Event;
	
	public class MainLayoutEvent extends Event
	{
		//----------------------------------------------------------------------
		
		public static const SEE_DETAIL:String = 'seeDetail';
		
		public static const SEE_ALL_SEARCHS:String = 'seeAllSearchs';
		
		//----------------------------------------------------------------------
		
		// TODO: definir atributos
		
		//----------------------------------------------------------------------
		
		public function MainLayoutEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
	}
}