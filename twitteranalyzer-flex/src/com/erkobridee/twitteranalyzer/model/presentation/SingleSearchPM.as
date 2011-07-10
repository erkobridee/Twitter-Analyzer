package com.erkobridee.twitteranalyzer.model.presentation
{
	import com.erkobridee.twitteranalyzer.event.MainLayoutEvent;
	import com.erkobridee.twitteranalyzer.event.SingleSearchEvent;
	import com.erkobridee.twitteranalyzer.model.entity.QueryVO;
	
	import mx.collections.ArrayCollection;

	[Bindable]
	public class SingleSearchPM extends BasePM
	{
		//----------------------------------------------------------------------
		
		public var query:QueryVO;
		
		public var streamDP:ArrayCollection;
		
		public var topUsersDP:ArrayCollection;
		
		public var dailyTweetsDP:ArrayCollection;
		
		//----------------------------------------------------------------------
		
		[EventHandler(event="SingleSearchEvent.GET_QUERY_INFO")]
		public function getQueryInfo(event:SingleSearchEvent):void 
		{
			query = event.query;
			
			this.updateData();			
		}
		
		[EventHandler(event="SingleSearchEvent.RETURN_GET_LAST_TWEETS")]
		public function returnedLastTweets(event:SingleSearchEvent):void 
		{
			streamDP = ArrayCollection(event.result);
		}
		
		[EventHandler(event="SingleSearchEvent.RETURN_GET_TOP_USERS_TWEETS_COUNT")]
		public function returnedTopUsersTweetsCount(event:SingleSearchEvent):void 
		{
			topUsersDP = ArrayCollection(event.result);
		}
		
		[EventHandler(event="SingleSearchEvent.RETURN_GET_TWEETS_PER_DAY")]
		public function returnedDailyTweets(event:SingleSearchEvent):void 
		{
			dailyTweetsDP = ArrayCollection(event.result);
		}
		
		//----------------------------------------------------------------------
		
		public function updateData():void 
		{
			var send:SingleSearchEvent = null;
			
			send = new SingleSearchEvent(SingleSearchEvent.GET_LAST_TWEETS);
			send.query = query;
			dispatcher.dispatchEvent(send);
			
			send = new SingleSearchEvent(SingleSearchEvent.GET_TOP_USERS_TWEETS_COUNT);
			send.query = query;
			dispatcher.dispatchEvent(send);
			
			send = new SingleSearchEvent(SingleSearchEvent.GET_TWEETS_PER_DAY);
			send.query = query;
			dispatcher.dispatchEvent(send);
		}
		
		public function returnToAllSearchs():void 
		{
			dispatcher.dispatchEvent(new MainLayoutEvent(MainLayoutEvent.SEE_ALL_SEARCHS));
		}
		
		
		//----------------------------------------------------------------------
		
		
	}
}