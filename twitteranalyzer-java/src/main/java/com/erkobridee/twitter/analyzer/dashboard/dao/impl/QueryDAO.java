package com.erkobridee.twitter.analyzer.dashboard.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.erkobridee.twitter.analyzer.core.dao.BaseDAO;
import com.erkobridee.twitter.analyzer.dashboard.dao.IQueryDAO;
import com.erkobridee.twitter.analyzer.dashboard.vo.QueryVO;

@Scope("prototype")
@Repository
public class QueryDAO extends BaseDAO implements IQueryDAO {

	//--------------------------------------------------------------------------
	
	protected static Logger logger = Logger.getLogger("dao");
	
	//--------------------------------------------------------------------------
	
	@Override
	public List<QueryVO> getQueryList() {
		List<QueryVO> queryList = null;
		
		String sql = "select id, query from search order by query";
		
		RowMapper<QueryVO> mapper = new RowMapper<QueryVO>() {  
	        public QueryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	QueryVO vo = new QueryVO();
	            
	        	vo.setId(rs.getInt("id"));
	        	vo.setName(rs.getString("query"));
	        	
	        	return vo;
	        }
	    };
		
	    try {	    
	    	queryList = jdbcTemplate.query(sql, mapper);
	    } catch(Exception e) {
	    	logger.error(e);
	    }
		
		return queryList;
	}

}
