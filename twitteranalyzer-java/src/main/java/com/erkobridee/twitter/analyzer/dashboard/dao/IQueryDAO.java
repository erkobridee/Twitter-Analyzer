package com.erkobridee.twitter.analyzer.dashboard.dao;

import java.util.List;

import com.erkobridee.twitter.analyzer.dashboard.vo.QueryVO;

public interface IQueryDAO {

	List<QueryVO> getQueryList();
	
}
