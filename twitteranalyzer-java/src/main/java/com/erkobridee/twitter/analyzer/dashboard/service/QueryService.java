package com.erkobridee.twitter.analyzer.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.erkobridee.twitter.analyzer.dashboard.dao.IQueryDAO;
import com.erkobridee.twitter.analyzer.dashboard.vo.QueryVO;

@Scope("prototype")
@Service("queryService")
@RemotingDestination(channels={"my-amf"})
public class QueryService {

	@Autowired
	private IQueryDAO queryDAO;
	
	public List<QueryVO> getQueryList() {
		return queryDAO.getQueryList();
	}
	
}
