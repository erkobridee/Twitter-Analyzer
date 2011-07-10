package com.erkobridee.twitteranalyzer.controller
{
	import com.erkobridee.twitteranalyzer.event.AllSearchsEvent;
	import com.erkobridee.twitteranalyzer.model.entity.TweetsIndexedByQueryVO;
	
	import mx.collections.ArrayCollection;
	import mx.messaging.Consumer;
	import mx.messaging.events.MessageEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.mxml.RemoteObject;

	public class AllSearchsController extends ControllerBase
	{
	
		//----------------------------------------------------------------------
		
		[Inject(source="queryService")]
		public var queryService:RemoteObject;
		
		[Inject(source="totalTweetsByQueryConsumer")]
		public var totalTweetsByQueryConsumer:Consumer;
		
		[Inject(source="tweetsIndexedByQueryConsumer")]
		public var tweetsIndexedByQueryConsumer:Consumer;
		
		//----------------------------------------------------------------------
		
		public function AllSearchsController() {}
		
		//----------------------------------------------------------------------
		
		[PostConstruct]
		public function postConstruct():void {
			totalTweetsByQueryConsumer.addEventListener(MessageEvent.MESSAGE, totalTweetsByQueryConsumerHandler);
			totalTweetsByQueryConsumer.subscribe();
			
			tweetsIndexedByQueryConsumer.addEventListener(MessageEvent.MESSAGE, tweetsIndexedByQueryConsumerHandler);
			tweetsIndexedByQueryConsumer.subscribe();
			
			this.getQueryList();
		}
		
		[EventHandler(event="AllSearchsEvent.GET_QUERY_LIST")]
		public function getQueryList(event:AllSearchsEvent=null):void {
			serviceHelper.executeServiceCall(
				queryService.getQueryList(),
				getQueryListResult,
				onError
			);
		}
		
		//----------------------------------------------------------------------
		
		private function totalTweetsByQueryConsumerHandler(e:MessageEvent):void 
		{
			var send:AllSearchsEvent = new AllSearchsEvent(AllSearchsEvent.RECEIVED_TOTAL_TWEETS_BY_QUERY);
			send.result = ArrayCollection(e.message.body);
			dispatcher.dispatchEvent(send);			
		}
		
		private function tweetsIndexedByQueryConsumerHandler(e:MessageEvent):void
		{
			var send:AllSearchsEvent = new AllSearchsEvent(AllSearchsEvent.RECEIVED_TWEETS_INDEXED_BY_QUERY);
			send.result = TweetsIndexedByQueryVO(e.message.body);
			dispatcher.dispatchEvent(send);	
		}
		
		private function getQueryListResult(event:ResultEvent):void 
		{
			var send:AllSearchsEvent = new AllSearchsEvent(AllSearchsEvent.RETURN_GET_QUERY_LIST);
			send.result = ArrayCollection(event.result);
			dispatcher.dispatchEvent(send);
		}
		
	}
}