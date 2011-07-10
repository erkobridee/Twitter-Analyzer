package com.erkobridee.twitteranalyzer.model.presentation
{
	import com.erkobridee.twitteranalyzer.event.AllSearchsEvent;
	import com.erkobridee.twitteranalyzer.event.MainLayoutEvent;
	import com.erkobridee.twitteranalyzer.event.SingleSearchEvent;
	import com.erkobridee.twitteranalyzer.model.entity.QueryVO;
	import com.erkobridee.twitteranalyzer.model.entity.TweetsIndexedByQueryVO;
	import com.erkobridee.twitteranalyzer.view.AllSearchs;
	
	import mx.charts.renderers.CircleItemRenderer;
	import mx.charts.series.LineSeries;
	import mx.collections.ArrayCollection;
	import mx.core.ClassFactory;
	import mx.utils.ObjectUtil;

	[Bindable]
	public class AllSearchsPM extends BasePM
	{
		
		//----------------------------------------------------------------------
		
		private var view:AllSearchs;
		
		[ViewAdded]
		public function allSearchsAdded(view:AllSearchs):void
		{
			this.view = view;
			
			if(lineSeries && lineSeries.length > 0) {
				view.tweetQueryChart.series = lineSeries;
			}
		}
		
		[ViewRemoved]
		public function allSearchsRemoved(view:AllSearchs):void
		{
			this.view = null;
		}
		
		//----------------------------------------------------------------------
		
		public var queriesList:ArrayCollection;
		
		public var totalTweetsByQueryList:ArrayCollection;
		
		public var lineChartDP:ArrayCollection = new ArrayCollection();
		
		public var lineChartDPMaxItems:Number = 100;
		
		private var lastPieChartObject:Object = {};
		
		private var lineSeries:Array = [];
		
		//----------------------------------------------------------------------
		
		[EventHandler(event="AllSearchsEvent.RETURN_GET_QUERY_LIST")]
		public function returnGetQueryList(event:AllSearchsEvent):void 
		{
			queriesList = ArrayCollection(event.result);
			
			this.defineLineSeries();
		}
		
		[EventHandler(event="AllSearchsEvent.RECEIVED_TOTAL_TWEETS_BY_QUERY")]
		public function receivedTotalTweetsByQuery(event:AllSearchsEvent):void
		{
			totalTweetsByQueryList = ArrayCollection(event.result);
		}
		
		[EventHandler(event="AllSearchsEvent.RECEIVED_TWEETS_INDEXED_BY_QUERY")]
		public function receivedTweetsIndexedByQuery(event:AllSearchsEvent):void
		{
			var vo:TweetsIndexedByQueryVO = TweetsIndexedByQueryVO(event.result); 
			lastPieChartObject = ObjectUtil.clone(lastPieChartObject);
			lastPieChartObject['time'] = new Date();
			lastPieChartObject[vo.label] = vo.value;
			
			this.addObjectToLineChartDP(lastPieChartObject);
		}
		
		//----------------------------------------------------------------------
		
		public function seeDetail(query:QueryVO):void
		{
			var send:SingleSearchEvent = new SingleSearchEvent(SingleSearchEvent.GET_QUERY_INFO);
			send.query = query;
			dispatcher.dispatchEvent(send);
			
			dispatcher.dispatchEvent(new MainLayoutEvent(MainLayoutEvent.SEE_DETAIL));
		}
		
		//----------------------------------------------------------------------
		
		private function defineLineSeries():void 
		{
			lineSeries = [];
			
			for each( var vo:QueryVO in queriesList ) {
				lineSeries.push(this.createLineSerie(vo.name));	
				
				lastPieChartObject[vo.name] = 0;
			}
			
			lastPieChartObject['time'] = new Date();			
			lineChartDP.addItem(lastPieChartObject);
			
			lastPieChartObject = ObjectUtil.clone(lastPieChartObject);
			lastPieChartObject['time'] = new Date();
			lineChartDP.addItem(lastPieChartObject);
			
			if( view ) {
				view.tweetQueryChart.series = lineSeries;	
			}
		}
		
		private function createLineSerie(name:String):LineSeries 
		{
			var ls:LineSeries = new LineSeries();
			ls.xField = 'time';
			ls.yField = name
			ls.displayName = name;	
			//ls.setStyle("itemRenderer", new ClassFactory(mx.charts.renderers.CircleItemRenderer)); 
			ls.setStyle("form", "curve");
			
			return ls;
		}
		
		private function addObjectToLineChartDP(o:Object):void {
			if( lineChartDP.length == lineChartDPMaxItems ) {
				lineChartDP.removeItemAt(0);
			}
			lineChartDP.addItem(o);
		}
		
	}
}