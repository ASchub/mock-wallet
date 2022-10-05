package com.example.mockwallet.core.domain;

import com.example.mockwallet.core.exception.WalletException;

import java.time.LocalDateTime;

public class Transaction {

  private final Funds amount;
  private final TransactionType transactionType;
  private final String otherAccountId;
  private final LocalDateTime createdAt;

  public Transaction(Funds amount, TransactionType transactionType, String otherAccountId, LocalDateTime createdAt) {
    if (amount == null || transactionType == null || otherAccountId == null || createdAt == null) {
      throw WalletException.internalError();
    }
    this.amount = amount;
    this.transactionType = transactionType;
    this.otherAccountId = otherAccountId;
    this.createdAt = createdAt;
  }

  public Funds getAmount() {
    return amount;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public String getOtherAccountId() {
    return otherAccountId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public enum TransactionType {
    RECEIVED,
    SENT
  }
}
