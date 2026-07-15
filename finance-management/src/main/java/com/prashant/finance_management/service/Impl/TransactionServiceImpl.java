package com.prashant.finance_management.service.Impl;

import com.prashant.finance_management.dto.TransactionRequestDTO;
import com.prashant.finance_management.dto.TransactionResponseDTO;
import com.prashant.finance_management.entity.Transaction;
import com.prashant.finance_management.exception.ResourceNotFoundException;
import com.prashant.finance_management.mapper.TransactionMapper;
import com.prashant.finance_management.repository.TransactionRepository;
import com.prashant.finance_management.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO request) {

        // Convert RequestDTO to Entity
        Transaction transaction = TransactionMapper.toEntity(request);

        // Save Entity to Database
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Convert Entity to ResponseDTO
        return TransactionMapper.toResponseDTO(savedTransaction);
    }

    @Override
    public List<TransactionResponseDTO> gtAllTransactions() {

        List<Transaction> transactions= transactionRepository.findAll();

        return transactions.stream()
                .map(TransactionMapper::toResponseDTO)
                .toList();

    }

    @Override
    public TransactionResponseDTO getTransactionById(Long id) {


        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Transaction not Found with id: "+ id));

        return TransactionMapper.toResponseDTO(transaction);
    }

    @Override
    public TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO requestDTO) {

        Transaction transaction = transactionRepository.findById(id)
                        .orElseThrow(()->  new ResourceNotFoundException("Transaction not Found with id: "+ id));

        transaction.setTitle(requestDTO.getTitle());
        transaction.setType(requestDTO.getType());
        transaction.setDescription((requestDTO.getDescription()));
        transaction.setCategory(requestDTO.getCategory());
        transaction.setDate(requestDTO.getDate());
        transaction.setAmount(requestDTO.getAmount());

        Transaction updatedTransaction = transactionRepository.save(transaction);

        return TransactionMapper.toResponseDTO(updatedTransaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)){
            throw new ResourceNotFoundException("Transaction not Found with id: "+ id);
        }

        transactionRepository.deleteById(id);
    }


}
