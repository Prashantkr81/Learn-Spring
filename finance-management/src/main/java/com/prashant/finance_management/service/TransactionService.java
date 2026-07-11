package com.prashant.finance_management.service;

import com.prashant.finance_management.dto.TransactionRequestDTO;
import com.prashant.finance_management.dto.TransactionResponseDTO;

import java.util.List;

public interface TransactionService {

    TransactionResponseDTO createTransaction (TransactionRequestDTO requestDTO);

    List<TransactionResponseDTO> gtAllTransactions();

    TransactionResponseDTO getTransactionById(Long id);

    TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO requestDTO);

    void deleteTransaction(Long id);
}
