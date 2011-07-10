package com.erkobridee.twitter.analyzer.dashboard.quartz.work.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.messaging.MessageTemplate;

import com.erkobridee.twitter.analyzer.dashboard.dao.IQueryCountDAO;
import com.erkobridee.twitter.analyzer.dashboard.quartz.work.IWork;
import com.erkobridee.twitter.analyzer.dashboard.vo.QueryCountVO;

import flex.messaging.messages.AsyncMessage;
import flex.messaging.util.UUIDUtils;

public class TotalTweetsByQueryWork implements IWork {

	//--------------------------------------------------------------------------
	
	protected static Logger logger = Logger.getLogger("quartz");
	
	//--------------------------------------------------------------------------

	@Autowired
	private MessageTemplate template;
	
	@Autowired
	private IQueryCountDAO dao;
	
	//--------------------------------------------------------------------------
	
	public void doWork() {		
		List<QueryCountVO> queryCountList = dao.getQueryCount();
		
		// cria uma nova mensagem...
		//AsyncMessage message = new AsyncMessage();
		AsyncMessage message = template.createMessage();
		
		// cria um UUID para identificar unicamente esse cliente.
		message.setClientId(UUIDUtils.createUUID());
		
		// define qual o serviço de destino dessa mensagem
		// configurada no messaging-config.xml
		message.setDestination("totalTweetsByQuery");
		
		// define unicamente essa mensagem
		message.setMessageId(UUIDUtils.createUUID(true));
		message.setTimestamp(System.currentTimeMillis());
		
		// define o corpo dessa mensagem, pode ser qualquer objeto.
		message.setBody(queryCountList); 
		
		// envia a mensagem para os clientes inscritos no mesmo
		// destino.		
		template.getMessageBroker().routeMessageToService(message, null);
		
		
	}
	
	//--------------------------------------------------------------------------
	
}
