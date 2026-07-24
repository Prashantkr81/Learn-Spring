package com.prashant.finance_management.service;

import com.prashant.finance_management.dto.TransactionRequestDTO;
import com.prashant.finance_management.dto.TransactionResponseDTO;
import com.prashant.finance_management.enums.TransactionType;

import java.util.List;

public interface TransactionService {

    TransactionResponseDTO createTransaction (TransactionRequestDTO requestDTO);

    List<TransactionResponseDTO> getAllTransactions();

    TransactionResponseDTO getTransactionById(Long id);

    TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO requestDTO);

    void deleteTransaction(Long id);

    List<TransactionResponseDTO> getTransactionByCategory(String category);

    List<TransactionResponseDTO> getTransactionByType(TransactionType type);

    List<TransactionResponseDTO> getTransactionsByAmountGreaterThan(Double amount);

    List<TransactionResponseDTO> getTransactionsByAmountLessThan(Double amount);

    List<TransactionResponseDTO> getTransactionsByAmountBetween(Double min, Double max);

    List<TransactionResponseDTO> searchTransactions(String keyword);

    List<TransactionResponseDTO> getTransactionByCategoryAndType(String category, TransactionType type);

    List<TransactionResponseDTO> getTransactionByTypeSortedDesc(TransactionType type);

    List<TransactionResponseDTO> getTransactionByTypeSortedAsc(TransactionType type);
}