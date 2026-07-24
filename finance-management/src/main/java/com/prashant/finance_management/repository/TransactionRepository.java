package com.prashant.finance_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prashant.finance_management.entity.Transaction;
import com.prashant.finance_management.enums.TransactionType;

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


    //-----------------------------------------------------------------
    // JPQL Queries
    @Query("""
        SELECT t FROM 
        Transaction t
        WHERE t.category = :category
            """)
    List<Transaction> findTransactionByCategoryJPQL(@Param("category") String category);

    @Query("""
        SELECT t FROM 
        Transaction t
        WHERE t.amount > :amount
            """)
    List<Transaction> findTransactionByAmountGreaterThanJPQL(@Param("amount") Double amount);

    //JPQL me SELECT t likhna optional hota hai.
    @Query("""
        FROM Transaction t
        WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    List<Transaction> searchTransactionsJPQL(@Param("keyword") String keyword);
}
