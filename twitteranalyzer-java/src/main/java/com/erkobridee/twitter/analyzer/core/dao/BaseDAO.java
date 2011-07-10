package com.erkobridee.twitter.analyzer.core.dao;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDAO {

	//--------------------------------------------------------------------------
	
	protected static Logger logger = Logger.getLogger("dao");
	
	//--------------------------------------------------------------------------
	
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//--------------------------------------------------------------------------
	
	protected boolean have(String sql, Object...args ) {		
		return ( this.count(sql, args) > 0 );
	}
	
	protected long count(String sql, Object...args ) {
		long value = 0;
		try {
			value = jdbcTemplate.queryForLong(sql, args);
		} catch(Exception e) {
			logger.error(e);
		}
		return value;
	}
	
	//--------------------------------------------------------------------------
	
}
