package com.erkobridee.twitteranalyzer.controller
{
	import com.erkobridee.twitteranalyzer.event.SingleSearchEvent;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.mxml.RemoteObject;

	public class SingleSearchController extends ControllerBase
	{
		
		//----------------------------------------------------------------------
		
		[Inject(source="singleSearchService")]
		public var singleSearchService:RemoteObject;
		
		//----------------------------------------------------------------------
		
		public function SingleSearchController() {}
		
		//----------------------------------------------------------------------
		
		[EventHandler(event="SingleSearchEvent.GET_LAST_TWEETS")]
		public function getLastTweets(event:SingleSearchEvent):void 
		{
			serviceHelper.executeServiceCall(
				singleSearchService.getLastTweets(event.query),
				getLastTweetsHandler,
				onError
			);
		}
		
		[EventHandler(event="SingleSearchEvent.GET_TOP_USERS_TWEETS_COUNT")]
		public function getTopUsersTweetsCount(event:SingleSearchEvent):void
		{
			serviceHelper.executeServiceCall(
				singleSearchService.getTopUsersTweetsCount(event.query),
				getTopUsersTweetsCountHandler,
				onError
			);
		}
		
		[EventHandler(event="SingleSearchEvent.GET_TWEETS_PER_DAY")]
		public function getTweetsPerDay(event:SingleSearchEvent):void
		{
			serviceHelper.executeServiceCall(
				singleSearchService.getTweetsPerDay(event.query),
				getTweetsPerDayHandler,
				onError
			);
		}
		
		//----------------------------------------------------------------------
		
		private function getLastTweetsHandler(event:ResultEvent):void
		{
			var send:SingleSearchEvent = new SingleSearchEvent(SingleSearchEvent.RETURN_GET_LAST_TWEETS);
			send.result = ArrayCollection(event.result);
			dispatcher.dispatchEvent(send);
		}
		
		private function getTopUsersTweetsCountHandler(event:ResultEvent):void
		{
			var send:SingleSearchEvent = new SingleSearchEvent(SingleSearchEvent.RETURN_GET_TOP_USERS_TWEETS_COUNT);
			send.result = ArrayCollection(event.result);
			dispatcher.dispatchEvent(send);
		}
		
		private function getTweetsPerDayHandler(event:ResultEvent):void
		{
			var send:SingleSearchEvent = new SingleSearchEvent(SingleSearchEvent.RETURN_GET_TWEETS_PER_DAY);
			send.result = ArrayCollection(event.result);
			dispatcher.dispatchEvent(send);
		}
		
	}
}