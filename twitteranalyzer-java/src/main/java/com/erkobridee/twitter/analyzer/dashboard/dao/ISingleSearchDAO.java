package com.erkobridee.twitter.analyzer.dashboard.dao;

import java.util.List;

import com.erkobridee.twitter.analyzer.dashboard.vo.NameValueVO;
import com.erkobridee.twitter.analyzer.dashboard.vo.QueryVO;
import com.erkobridee.twitter.analyzer.dashboard.vo.StreamVO;

public interface ISingleSearchDAO {

	List<StreamVO> getLastTweets(QueryVO query);
	
	List<NameValueVO> getTopUsersTweetsCount(QueryVO query);
	
	List<NameValueVO> getTweetsPerDay(QueryVO query);
	
	
}
