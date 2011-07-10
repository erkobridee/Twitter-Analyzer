package com.erkobridee.twitteranalyzer.event
{
	import com.erkobridee.twitteranalyzer.model.entity.QueryVO;
	
	import flash.events.Event;
	
	public class SingleSearchEvent extends Event
	{
		//----------------------------------------------------------------------
		
		public static const GET_QUERY_INFO:String = 'getQueryInfo';
		
		public static const GET_LAST_TWEETS:String = 'getLastTweets';
		
		public static const RETURN_GET_LAST_TWEETS:String = 'returnGetLastTweets';
		
		public static const GET_TOP_USERS_TWEETS_COUNT:String = 'getTopUsersTweetsCount';
		
		public static const RETURN_GET_TOP_USERS_TWEETS_COUNT:String = 'returnGetTopUsersTweetsCount';
		
		public static const GET_TWEETS_PER_DAY:String = 'getTweetsPerDay';
		
		public static const RETURN_GET_TWEETS_PER_DAY:String = 'returnGetTweetsPerDay';
		
		//----------------------------------------------------------------------
		
		public var query:QueryVO;
		
		public var result:*;
		
		//----------------------------------------------------------------------
		
		
		public function SingleSearchEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
	}
}