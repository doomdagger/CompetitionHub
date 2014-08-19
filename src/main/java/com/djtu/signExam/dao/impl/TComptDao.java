package com.djtu.signExam.dao.impl;

import com.djtu.signExam.model.TCompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 14-7-16.
 */
@Repository("tComptDao")
public class TComptDao extends BaseDaoImpl<TCompt>{
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
