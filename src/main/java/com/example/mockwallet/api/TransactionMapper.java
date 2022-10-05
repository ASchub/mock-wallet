package com.example.mockwallet.api;

import com.example.mockwallet.core.domain.Transaction;

import java.util.List;

public class TransactionMapper {

  public static List<TransactionDto> toDto(List<Transaction> transactions) {
    return transactions.stream().map(TransactionMapper::toDto).toList();
  }

  public static TransactionDto toDto(Transaction transaction) {
    return new TransactionDto(
      transaction.getAmount().getDoubleValue(),
      transaction.getTransactionType(),
      transaction.getOtherAccountId(),
      transaction.getCreatedAt());
  }
}
