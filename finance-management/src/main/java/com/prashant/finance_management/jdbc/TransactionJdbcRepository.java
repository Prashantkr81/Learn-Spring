package com.prashant.finance_management.jdbc;

import com.prashant.finance_management.entity.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionJdbcRepository{

    private final JdbcTemplate jdbcTemplate;

    public TransactionJdbcRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate= jdbcTemplate;
    }

    public List<Transaction> findAllTransactions(){

        String sql= """
                SELECT *
                FROM transaction
                """;
                return jdbcTemplate.query(sql,
                        new TransactionRowMapper());
    }

}