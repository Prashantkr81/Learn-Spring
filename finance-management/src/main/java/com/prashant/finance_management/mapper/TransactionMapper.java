package com.prashant.finance_management.mapper;

import com.prashant.finance_management.dto.TransactionRequestDTO;
import com.prashant.finance_management.dto.TransactionResponseDTO;
import com.prashant.finance_management.entity.Transaction;

public class TransactionMapper {

    // RequestDTO -> Entity
    public static Transaction toEntity(TransactionRequestDTO request){

        Transaction transaction= new Transaction();

        transaction.setTitle(request.getTitle());
        transaction.setAmount(request.getAmount());
        transaction.setDate(request.getDate());
        transaction.setCategory(request.getCategory());
        transaction.setType(request.getType());
        transaction.setDescription(request.getDescription());

        return transaction;
    }

    // Entity -> ResponseDTO
    public static TransactionResponseDTO toResponseDTO(Transaction transaction) {

        TransactionResponseDTO dto = new TransactionResponseDTO();

        dto.setId(transaction.getId());
        dto.setTitle(transaction.getTitle());
        dto.setAmount(transaction.getAmount());
        dto.setCategory(transaction.getCategory());
        dto.setType(transaction.getType());
        dto.setDate(transaction.getDate());
        dto.setDescription(transaction.getDescription());

        return dto;
    }
}