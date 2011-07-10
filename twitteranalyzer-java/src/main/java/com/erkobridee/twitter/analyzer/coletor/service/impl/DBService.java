package com.erkobridee.twitter.analyzer.coletor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.erkobridee.twitter.analyzer.coletor.dao.ISearchDAO;
import com.erkobridee.twitter.analyzer.coletor.dao.ITweetDAO;
import com.erkobridee.twitter.analyzer.coletor.service.IDBService;
import com.erkobridee.twitter.analyzer.coletor.vo.SearchVO;

@Service
@Scope("prototype")
public class DBService implements IDBService {

	@Autowired
	private ISearchDAO searchDAO;
	
	@Autowired
	private ITweetDAO tweetDAO;
	
	public SearchVO findById(int id) {
		return searchDAO.findById(id);
	}
	
	public List<SearchVO> findAllSearch() {
		return searchDAO.findAll();
	}
	
	public void saveSearch(SearchVO search) {
		searchDAO.save(search);
		tweetDAO.save(search);		
	}
	
}
