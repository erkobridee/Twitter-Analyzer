package com.erkobridee.twitter.analyzer.dashboard.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.erkobridee.twitter.analyzer.core.dao.BaseDAO;
import com.erkobridee.twitter.analyzer.dashboard.dao.IQueryCountDAO;
import com.erkobridee.twitter.analyzer.dashboard.vo.QueryCountVO;

@Scope("prototype")
@Repository
public class QueryCountDAO extends BaseDAO implements IQueryCountDAO {

	//--------------------------------------------------------------------------
	
	protected static Logger logger = Logger.getLogger("dao");
	
	//--------------------------------------------------------------------------
	
	@Override
	public List<QueryCountVO> getQueryCount() {
		List<QueryCountVO> queryCountList = null; 
		
		String sql = "select vs.query as label, count(0) as value from view_search as vs group by vs.query";
		
		RowMapper<QueryCountVO> mapper = new RowMapper<QueryCountVO>() {  
	        public QueryCountVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	QueryCountVO vo = new QueryCountVO();
	            
	        	vo.setName(rs.getString("label"));
	        	vo.setCount(rs.getLong("value"));
	        	
	        	return vo;
	        }
	    };
		
	    try {	    
	    	queryCountList = jdbcTemplate.query(sql, mapper);
	    } catch(Exception e) {
	    	logger.error(e);
	    }
		
		return queryCountList;
	}

}
