package com.erkobridee.twitter.analyzer.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.flex.messaging.MessageTemplate;
import org.springframework.stereotype.Service;

import com.erkobridee.twitter.analyzer.core.service.ITweetsIndexedByQueryService;
import com.erkobridee.twitter.analyzer.dashboard.vo.TweetsIndexedByQueryVO;

import flex.messaging.messages.AsyncMessage;
import flex.messaging.util.UUIDUtils;

@Scope("prototype")
@Service("tweetsIndexedByQueryService")
public class TweetsIndexedByQueryService implements ITweetsIndexedByQueryService {

	//--------------------------------------------------------------------------
	
	@Autowired
	private MessageTemplate template;
	
	//--------------------------------------------------------------------------
	
	public void sendValue(String label, int value) {
		TweetsIndexedByQueryVO vo = new TweetsIndexedByQueryVO();
		vo.setLabel(label);
		vo.setValue(value);
		
		//---
		
		// cria uma nova mensagem...
		//AsyncMessage message = new AsyncMessage();
		AsyncMessage message = template.createMessage();
		
		// cria um UUID para identificar unicamente esse cliente.
		message.setClientId(UUIDUtils.createUUID());
		
		// define qual o serviço de destino dessa mensagem
		// configurada no messaging-config.xml
		message.setDestination("tweetsIndexedByQuery");
		
		// define unicamente essa mensagem
		message.setMessageId(UUIDUtils.createUUID(true));
		message.setTimestamp(System.currentTimeMillis());
		
		// define o corpo dessa mensagem, pode ser qualquer objeto.
		message.setBody(vo); 
		
		// envia a mensagem para os clientes inscritos no mesmo
		// destino.		
		template.getMessageBroker().routeMessageToService(message, null);
	}
	
}
