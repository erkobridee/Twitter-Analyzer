package com.erkobridee.twitter.analyzer.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.erkobridee.twitter.analyzer.dashboard.dao.ISingleSearchDAO;
import com.erkobridee.twitter.analyzer.dashboard.vo.NameValueVO;
import com.erkobridee.twitter.analyzer.dashboard.vo.QueryVO;
import com.erkobridee.twitter.analyzer.dashboard.vo.StreamVO;

@Scope("prototype")
@Service("singleSearchService")
@RemotingDestination(channels={"my-amf"})
public class SingleSearchService {

	@Autowired
	private ISingleSearchDAO singleSearchDAO;
	
	public List<StreamVO> getLastTweets(QueryVO query) {
		return singleSearchDAO.getLastTweets(query);
	}
	
	public List<NameValueVO> getTopUsersTweetsCount(QueryVO query) {
		return singleSearchDAO.getTopUsersTweetsCount(query);
	}
	
	public List<NameValueVO> getTweetsPerDay(QueryVO query) {
		return singleSearchDAO.getTweetsPerDay(query);
	}
	
}
