package com.prashant.finance_management.jdbc;

import com.prashant.finance_management.entity.Transaction;
import com.prashant.finance_management.enums.TransactionType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRowMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet rs,
                              int rowNum)
        throws SQLException{
        Transaction transaction= new Transaction();

        transaction.setId(rs.getLong("id"));
        transaction.setTitle(rs.getString("title"));
        transaction.setAmount(rs.getDouble("amount"));
        transaction.setCategory(rs.getString("category"));

        transaction.setType(
                TransactionType.valueOf(
                        rs.getString("type")));

        transaction.setDate(
                rs.getDate("date").toLocalDate());

        transaction.setDescription(
                rs.getString("description"));

        return transaction;
    }
}
