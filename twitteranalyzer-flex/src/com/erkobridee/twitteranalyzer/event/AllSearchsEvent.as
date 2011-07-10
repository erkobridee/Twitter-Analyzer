package com.erkobridee.twitteranalyzer.event
{
	import flash.events.Event;
	
	public class AllSearchsEvent extends Event
	{
		//----------------------------------------------------------------------
		
		public static const GET_QUERY_LIST:String = "getQueryList";
		
		public static const RETURN_GET_QUERY_LIST:String = "returnGetQueryList";
		
		public static const RECEIVED_TOTAL_TWEETS_BY_QUERY:String = "receivedTotalTweetsByQuery";
		
		public static const RECEIVED_TWEETS_INDEXED_BY_QUERY:String = "receivedTweetsIndexedByQuery";
		
		//----------------------------------------------------------------------
		
		public var result:*;
		
		//----------------------------------------------------------------------
		
		public function AllSearchsEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
	}
}