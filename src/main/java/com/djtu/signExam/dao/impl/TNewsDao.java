package com.djtu.signExam.dao.impl;

import com.djtu.signExam.model.TNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 14-7-16.
 */
@Repository("tNewsDao")
public class TNewsDao extends BaseDaoImpl<TNews>{
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
