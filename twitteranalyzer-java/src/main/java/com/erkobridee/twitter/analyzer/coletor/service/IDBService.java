package com.erkobridee.twitter.analyzer.coletor.service;

import java.util.List;

import com.erkobridee.twitter.analyzer.coletor.vo.SearchVO;

public interface IDBService {

	SearchVO findById(int id);
	
	List<SearchVO> findAllSearch();
	
	void saveSearch(SearchVO search);
	
}
