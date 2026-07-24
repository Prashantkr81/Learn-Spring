package com.prashant.finance_management.repository;

import com.prashant.finance_management.entity.Transaction;
import com.prashant.finance_management.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository <Transaction, Long> {

    //Equality
    List<Transaction> findByCategory(String category);

    List<Transaction> findByType(TransactionType type);

    //comparison
    List<Transaction> findByAmountLessThan(Double amount);

    List<Transaction> findByAmountGreaterThan(Double amount);

    List<Transaction> findByAmountBetween(Double min, Double max);

    //String Searching
    List<Transaction> findByTitleContaining(String keyword);

    List<Transaction> findByTitleStartsWith(String keyword);

    List<Transaction> findByTitleEndsWith(String keyword);

    //Multiple
    List<Transaction> findByCategoryAndType(String category, TransactionType type);

    //sorting
    List<Transaction> findByTypeOrderByAmountDesc(TransactionType type);

    List<Transaction> findByTypeOrderByAmountAsc(TransactionType type);

}
