package com.erkobridee.twitter.analyzer.coletor.dao;

import java.util.List;

import com.erkobridee.twitter.analyzer.coletor.vo.SearchVO;

public interface ISearchDAO {

	SearchVO findById(int id);
	
	void save(SearchVO search);
	
	List<SearchVO> findAll();
	
}
