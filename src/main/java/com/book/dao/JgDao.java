package com.book.dao;

import com.book.domain.Jg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class JgDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public ArrayList<Jg> getAllJgs() {
        final ArrayList<Jg> jgs = new ArrayList<>();
        String sql = "SELECT * FROM jg";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    Jg jg = new Jg();
                    jg.setJgId(resultSet.getInt("jg_id"));
                    jg.setJgName(resultSet.getString("jg_name"));
                    jg.setJgReason(resultSet.getString("jg_reason"));
                    jg.setJgState(resultSet.getInt("jg_state"));
                    jgs.add(jg);
                }
            }
        });
        return jgs;
    }

    public void jgState(int jgState,int jgId) {

        String sql = "UPDATE jg set jg_state = ? where jg_id = ? ";
        jdbcTemplate.update(sql, new Object[]{jgState,jgId});

    }
    public void jgAdd(Jg jg) {
        ArrayList<Jg> list=getAllJgs();
        int id = list.get(list.size()-1).getJgId()+1;
        String sql = "INSERT INTO jg VALUES(?,?,?,?) ";
        jdbcTemplate.update(sql, new Object[]{id,jg.getJgName(),jg.getJgReason(),0});

    }

}
