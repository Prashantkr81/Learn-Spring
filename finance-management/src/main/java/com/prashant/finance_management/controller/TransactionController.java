package com.prashant.finance_management.controller;


import com.prashant.finance_management.dto.TransactionRequestDTO;
import com.prashant.finance_management.dto.TransactionResponseDTO;
import com.prashant.finance_management.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService= transactionService;
    }

    @PutMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(
            @Valid @RequestBody TransactionRequestDTO requestDTO
    ){
        TransactionResponseDTO response = transactionService.createTransaction(requestDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/id")
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
}
