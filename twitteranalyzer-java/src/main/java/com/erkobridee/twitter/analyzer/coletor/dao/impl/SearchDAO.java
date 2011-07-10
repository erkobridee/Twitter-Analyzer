package com.erkobridee.twitter.analyzer.coletor.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.erkobridee.twitter.analyzer.coletor.dao.ISearchDAO;
import com.erkobridee.twitter.analyzer.coletor.vo.SearchVO;
import com.erkobridee.twitter.analyzer.core.dao.BaseDAO;

/*
 * TODO: ver documentação
 * 
 * http://static.springsource.org/spring/docs/3.0.x/reference/jdbc.html
 */

@Scope("prototype")
@Repository
public class SearchDAO extends BaseDAO implements ISearchDAO {

	//--------------------------------------------------------------------------
	
	protected static Logger logger = Logger.getLogger("dao_coletor");
	
	//--------------------------------------------------------------------------
	
	public SearchVO findById(int id) {
		logger.debug("findById: " + id);
		
		SearchVO search = null;
		
		boolean flag = super.have("select count(0) from search where id = ?", id);
		
		if(flag) {
		
			String sql = "select id, query, since_id from search where id = ?";
			
		    RowMapper<SearchVO> mapper = new RowMapper<SearchVO>() {  
		        public SearchVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	SearchVO vo = new SearchVO();
		            
		        	vo.setId(rs.getInt("id"));
		        	vo.setQuery(rs.getString("query"));
		        	vo.setSinceId( 
		        		rs.getString("since_id") != null ? 
		        		rs.getLong("since_id") : null
		        	);
		        	
		        	return vo;
		        }
		    };
		
		    try {
			    search = this.jdbcTemplate.queryForObject(sql, mapper, id);			
			} catch(Exception e) {
				if(!(e instanceof EmptyResultDataAccessException))
					logger.error(e);
			}
		    
		}
		
		return search;
	}

	public void save(SearchVO search) {
		String sql = "";
		Object[] params = null;
		
		search.setDtExecuted(new Date());
		
		if(search.getId() == 0) {
			logger.debug("save : insert");
			
			search.setId(search.getQuery().hashCode());
			sql = "INSERT INTO search (id, query, since_id, dt_executed) VALUES (?, ?, ?, ?)";			
			params = new Object[] { search.getId(), search.getQuery(), search.getSinceId(), search.getDtExecuted() };
		} else {
			logger.debug("save : update");
			
			search.setSinceId(
				(search.getTweets() != null && search.getTweets().size() > 0) ?
				search.getTweets().get(0).getId() : search.getSinceId()
			);
			
			sql = "UPDATE search SET since_id = ?, dt_executed = ? WHERE id = ?";
			params = new Object[] { search.getSinceId(), search.getDtExecuted(), search.getId() };
		}
		
		try {
			jdbcTemplate.update(
				sql, params
			);
		} catch(Exception e) {
			logger.error(e);
		}

	}

	public List<SearchVO> findAll() {
		logger.debug("findAll");
		
		List<SearchVO> searchList = null;
		String sql = "select id, query, since_id, dt_executed from search order by query";
		
		RowMapper<SearchVO> mapper = new RowMapper<SearchVO>() {  
	        public SearchVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	SearchVO vo = new SearchVO();
	            
	        	vo.setId(rs.getInt("id"));
	        	vo.setQuery(rs.getString("query"));
	        	vo.setSinceId(rs.getLong("since_id"));
	        	
	        	return vo;
	        }
	    };
		
	    try {	    
	    	searchList = jdbcTemplate.query(sql, mapper);
	    } catch(Exception e) {
	    	logger.error(e);
	    }
	    
	    return searchList;
	}

}
