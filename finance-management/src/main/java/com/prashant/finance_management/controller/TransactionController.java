package com.prashant.finance_management.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prashant.finance_management.dto.TransactionRequestDTO;
import com.prashant.finance_management.dto.TransactionResponseDTO;
import com.prashant.finance_management.enums.TransactionType;
import com.prashant.finance_management.service.TransactionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService= transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(
            @Valid @RequestBody TransactionRequestDTO requestDTO
    ){
        TransactionResponseDTO response = transactionService.createTransaction(requestDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Long id){

        TransactionResponseDTO response= transactionService.getTransactionById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(
            @PathVariable Long id,
            @Valid @RequestBody TransactionRequestDTO requestDTO) {

        return ResponseEntity.ok(
                transactionService.updateTransaction(id, requestDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(
            @PathVariable Long id) {

        transactionService.deleteTransaction(id);

        return ResponseEntity.ok("Transaction deleted successfully.");
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        List<TransactionResponseDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TransactionResponseDTO>> searchTransactions(
            @RequestParam(required = false) String keyword) {

        List<TransactionResponseDTO> transactions = transactionService.searchTransactions(keyword);
        return ResponseEntity.ok(transactions);
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionByCategory(
            @PathVariable String category) {

        List<TransactionResponseDTO> transactions = transactionService.getTransactionByCategory(category);
        return ResponseEntity.ok(transactions);

    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionByType(
            @PathVariable TransactionType type) {

        List<TransactionResponseDTO> transactions = transactionService.getTransactionByType(type);
        return ResponseEntity.ok(transactions);

    }

    @GetMapping("/type/{type}/sorted-desc")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionByTypeSortedDesc(
            @PathVariable TransactionType type) {

        List<TransactionResponseDTO> transactions = transactionService.getTransactionByTypeSortedDesc(type);
        return ResponseEntity.ok(transactions);

    }
    
    @GetMapping("/type/{type}/sorted-asc")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionByTypeSortedAsc(
            @PathVariable TransactionType type) {

        List<TransactionResponseDTO> transactions = transactionService.getTransactionByTypeSortedAsc(type);
        return ResponseEntity.ok(transactions);

    }

    @GetMapping("/amount/greater-than/{amount}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByAmountGreaterThan(
            @PathVariable Double amount) {

        List<TransactionResponseDTO> transactions = transactionService.getTransactionsByAmountGreaterThan(amount);
        return ResponseEntity.ok(transactions);

    }

    @GetMapping("/amount/less-than/{amount}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByAmountLessThan(
            @PathVariable Double amount) {

        List<TransactionResponseDTO> transactions = transactionService.getTransactionsByAmountLessThan(amount);
        return ResponseEntity.ok(transactions);

    }

    @GetMapping("/amount/between")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByAmountBetween(
            @RequestParam Double min,
            @RequestParam Double max) {

        List<TransactionResponseDTO> transactions = transactionService.getTransactionsByAmountBetween(min, max);
        return ResponseEntity.ok(transactions);

    }

    @GetMapping("/category/{category}/type/{type}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionByCategoryAndType(
            @PathVariable String category,
            @PathVariable TransactionType type) {

        List<TransactionResponseDTO> transactions = transactionService.getTransactionByCategoryAndType(category, type);
        return ResponseEntity.ok(transactions);

    }

    @GetMapping("/jpql/category/{category}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionByCategoryJPQL(
            @PathVariable String category) {

        List<TransactionResponseDTO> transactions = transactionService.getTransactionByCategoryJPQL(category);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/jpql/amount/greater-than/{amount}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionByAmountGreaterThanJPQL(  
            @PathVariable Double amount) {

        List<TransactionResponseDTO> transactions = transactionService.getTransactionByAmountGreaterThanJPQL(amount);
        return ResponseEntity.ok(transactions);

    }

    @GetMapping("/jpql/search")
    public ResponseEntity<List<TransactionResponseDTO>> searchTransactionsJPQL(
            @RequestParam(required = false) String keyword) {

        List<TransactionResponseDTO> transactions = transactionService.searchTransactionsJPQL(keyword);
        return ResponseEntity.ok(transactions);
    }

    //Native Queries
    @GetMapping("/native/category/{category}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionByCategoryNative(
            @PathVariable String category) {

        List<TransactionResponseDTO> transactions = transactionService.getTransactionByCategoryNative(category);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/native/amount/greater-than/{amount}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionByAmountGreaterThanNative(
            @PathVariable Double amount) {

        List<TransactionResponseDTO> transactions = transactionService.getTransactionByAmountGreaterThanNative(amount);
        return ResponseEntity.ok(transactions);

    }


    //JDBC
    @GetMapping("/jdbc/all")
    public ResponseEntity<List<TransactionResponseDTO>>  findAllTransactions() {
        List<TransactionResponseDTO> transactions = transactionService.findAllTransactions();
        return ResponseEntity.ok(transactions);
    }

}
