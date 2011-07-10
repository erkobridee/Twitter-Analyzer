package com.erkobridee.twitteranalyzer.controller
{
	import flash.events.IEventDispatcher;
	
	import mx.rpc.events.FaultEvent;
	
	import org.swizframework.utils.services.ServiceHelper;

	public class ControllerBase
	{
		
		[Dispatcher]
		public var dispatcher:IEventDispatcher;
		
		[Inject(source="serviceHelper")]
		public var serviceHelper:ServiceHelper;
		
		public function ControllerBase() {}
		
		
		public function onError(event:FaultEvent):void {
			// TODO: definir mensagem a ser disparada
			
			trace(event.fault.message);
		}
		
	}
}