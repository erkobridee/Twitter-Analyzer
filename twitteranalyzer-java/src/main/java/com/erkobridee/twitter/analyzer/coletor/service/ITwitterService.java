package com.erkobridee.twitter.analyzer.coletor.service;

import com.erkobridee.twitter.analyzer.coletor.vo.SearchVO;

public interface ITwitterService {

	SearchVO doSearch(SearchVO search);
	
}
