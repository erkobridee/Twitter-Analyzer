package com.erkobridee.twitter.analyzer.dashboard.dao;

import java.util.List;

import com.erkobridee.twitter.analyzer.dashboard.vo.QueryCountVO;

public interface IQueryCountDAO {

	List<QueryCountVO> getQueryCount();
	
}
