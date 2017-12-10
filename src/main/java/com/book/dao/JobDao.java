package com.book.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JobDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final String MATCH_ADMIN_SQL="SELECT COUNT(*) FROM job where job_id = ? and password = ? ";
    private static final String RE_PASSWORD_SQL="UPDATE job set password = ? where job_id = ? ";
    private static final String GET_PASSWD_SQL="SELECT password from job where job_id = ?";

    public int getMatchCount(int jobId,String password){
        return jdbcTemplate.queryForObject(MATCH_ADMIN_SQL,new Object[]{jobId,password},Integer.class);
    }

    public int rePassword(int jobId,String newPasswd){
        return jdbcTemplate.update(RE_PASSWORD_SQL,new Object[]{newPasswd,jobId});
    }
    public String getPasswd(int id){
        return jdbcTemplate.queryForObject(GET_PASSWD_SQL,new Object[]{id},String.class);
    }

}
