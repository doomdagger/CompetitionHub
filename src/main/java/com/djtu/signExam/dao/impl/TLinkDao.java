package com.djtu.signExam.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.djtu.signExam.model.TLink;

@Repository("tLinkDao")
public class TLinkDao extends BaseDaoImpl<TLink>{
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	    @Override
	    public JdbcTemplate getJdbcTemplate() {
	        return jdbcTemplate;
	    }
	    protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }

}
