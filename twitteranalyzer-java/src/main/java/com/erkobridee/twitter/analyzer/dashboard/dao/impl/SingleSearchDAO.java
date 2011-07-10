package com.erkobridee.twitter.analyzer.dashboard.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.erkobridee.twitter.analyzer.core.dao.BaseDAO;
import com.erkobridee.twitter.analyzer.dashboard.dao.ISingleSearchDAO;
import com.erkobridee.twitter.analyzer.dashboard.vo.NameValueVO;
import com.erkobridee.twitter.analyzer.dashboard.vo.QueryVO;
import com.erkobridee.twitter.analyzer.dashboard.vo.StreamVO;

@Scope("prototype")
@Repository
public class SingleSearchDAO extends BaseDAO implements ISingleSearchDAO {

	@Override
	public List<StreamVO> getLastTweets(QueryVO query) {
		List<StreamVO> streamList = null;

		String sql = "select vs.user, vs.user_image, vs.text, vs.dt_created from view_search as vs where vs.query = ? order by vs.dt_created desc limit 0, ?";
		
		RowMapper<StreamVO> mapper = new RowMapper<StreamVO>() {  
	        public StreamVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	StreamVO vo = new StreamVO();
	            
	        	vo.setText(rs.getString("text"));
	        	vo.setUserName(rs.getString("user"));
	        	vo.setUserImageUrl(rs.getString("user_image"));
	        	vo.setTime( 
	        		rs.getString("dt_created") != null ?
	        		new Date(rs.getTimestamp("dt_created").getTime()) : null
	        	);
	        	
	        	return vo;
	        }
	    };
		
	    Object[] params = new Object[] {
	    	query.getName(),
	    	query.getStreamSize()
	    };
	    
	    try {	    
	    	streamList = jdbcTemplate.query(sql, params, mapper); 
	    } catch(Exception e) {
	    	logger.error(e);
	    }
		
		return streamList;
	}
	
	public List<NameValueVO> getTopUsersTweetsCount(QueryVO query) {
		List<NameValueVO> valueList = null;
		
		String sql = "select vs.user as name, count(0) as value from view_search as vs where vs.query = ? group by vs.user order by value desc limit 0,10";
		
		RowMapper<NameValueVO> mapper = new RowMapper<NameValueVO>() {  
	        public NameValueVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	NameValueVO vo = new NameValueVO();
	            
	        	vo.setName(rs.getString("name"));
	        	vo.setValue(rs.getInt("value"));
	        	
	        	return vo;
	        }
	    };
		
	    Object[] params = new Object[] {
	    	query.getName()
	    };
	    
	    try {	    
	    	valueList = jdbcTemplate.query(sql, params, mapper); 
	    } catch(Exception e) {
	    	logger.error(e);
	    }
		
		return valueList;
	}
	
	public List<NameValueVO> getTweetsPerDay(QueryVO query) {
		List<NameValueVO> valueList = null;
		
		String sql = "select vs.dt_created_day as name, count(0) as value from view_search as vs where vs.query = ? and vs.dt_created_year_month = date_format(now(), '%Y-%m') group by vs.dt_created_day order by name";
		
		RowMapper<NameValueVO> mapper = new RowMapper<NameValueVO>() {  
	        public NameValueVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	NameValueVO vo = new NameValueVO();
	            
	        	vo.setName(rs.getString("name"));
	        	vo.setValue(rs.getInt("value"));
	        	
	        	return vo;
	        }
	    };
		
	    Object[] params = new Object[] {
	    	query.getName()
	    };
	    
	    try {	    
	    	valueList = jdbcTemplate.query(sql, params, mapper); 
	    } catch(Exception e) {
	    	logger.error(e);
	    }
		
		return valueList;
	}

}
