package com.prashant.finance_management.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.prashant.finance_management.dto.TransactionRequestDTO;
import com.prashant.finance_management.dto.TransactionResponseDTO;
import com.prashant.finance_management.entity.Transaction;
import com.prashant.finance_management.enums.TransactionType;
import com.prashant.finance_management.exception.ResourceNotFoundException;
import com.prashant.finance_management.mapper.TransactionMapper;
import com.prashant.finance_management.repository.TransactionRepository;
import com.prashant.finance_management.service.TransactionService;

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
    public List<TransactionResponseDTO> getAllTransactions() {

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

    @Override
    public List<TransactionResponseDTO> getTransactionByCategory(String category){

        return transactionRepository.findByCategory(category)
                .stream()
                .map(TransactionMapper:: toResponseDTO)
                .toList();
    }

    @Override
    public List<TransactionResponseDTO> getTransactionByType(TransactionType type){

        return transactionRepository.findByType(type)
                .stream()
                .map(TransactionMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<TransactionResponseDTO> getTransactionsByAmountGreaterThan(Double amount){

        return transactionRepository.findByAmountGreaterThan(amount)
                .stream()
                .map(TransactionMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<TransactionResponseDTO> getTransactionsByAmountLessThan(Double amount){

        return transactionRepository.findByAmountLessThan(amount)
                .stream()
                .map(TransactionMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<TransactionResponseDTO> getTransactionsByAmountBetween(Double min, Double max) {
        return transactionRepository.findByAmountBetween(min, max)
                .stream()
                .map(TransactionMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<TransactionResponseDTO> searchTransactions(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getAllTransactions();
        }

        List<Transaction> matchedTransactions = new ArrayList<>();
        matchedTransactions.addAll(transactionRepository.findByTitleContaining(keyword));
        matchedTransactions.addAll(transactionRepository.findByTitleStartsWith(keyword));
        matchedTransactions.addAll(transactionRepository.findByTitleEndsWith(keyword));

        List<Transaction> uniqueTransactions = new ArrayList<>();
        for (Transaction transaction : matchedTransactions) {
            boolean alreadyAdded = uniqueTransactions.stream()
                    .anyMatch(existing -> existing.getId() != null && existing.getId().equals(transaction.getId()));
            if (!alreadyAdded) {
                uniqueTransactions.add(transaction);
            }
        }

        return uniqueTransactions.stream()
                .map(TransactionMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<TransactionResponseDTO> getTransactionByCategoryAndType(String category, TransactionType type) {
        return transactionRepository.findByCategoryAndType(category, type)
                .stream()
                .map(TransactionMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<TransactionResponseDTO> getTransactionByTypeSortedDesc(TransactionType type) {
        return transactionRepository.findByTypeOrderByAmountDesc(type)
                .stream()
                .map(TransactionMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<TransactionResponseDTO> getTransactionByTypeSortedAsc(TransactionType type) {
        return transactionRepository.findByTypeOrderByAmountAsc(type)
                .stream()
                .map(TransactionMapper::toResponseDTO)
                .toList();
    }

    //--------------------------------------------------------------
    @Override
    public List<TransactionResponseDTO> getTransactionByCategoryJPQL(String category) {
        return transactionRepository.findTransactionByCategoryJPQL(category)
                .stream()
                .map(TransactionMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<TransactionResponseDTO> getTransactionByAmountGreaterThanJPQL(Double amount) {
        return transactionRepository.findTransactionByAmountGreaterThanJPQL(amount)
                .stream()
                .map(TransactionMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<TransactionResponseDTO> searchTransactionsJPQL(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getAllTransactions();
        }

        List<Transaction> matchedTransactions = transactionRepository.searchTransactionsJPQL(keyword);

        return matchedTransactions.stream()
                .map(TransactionMapper::toResponseDTO)
                .toList();
    }
}
