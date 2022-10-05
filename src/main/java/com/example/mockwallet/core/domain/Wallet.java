package com.example.mockwallet.core.domain;

import com.example.mockwallet.core.exception.WalletException;

import java.time.LocalDateTime;

public class Wallet {
  private final String id;
  private final Funds balance;
  private final LocalDateTime createdAt;

  public Wallet(String id, Funds balance, LocalDateTime createdAt) {
    if (id == null || balance == null || createdAt == null) {
      throw WalletException.internalError();
    }
    this.id = id;
    this.balance = balance;
    this.createdAt = createdAt;
  }

  public String getId() {
    return id;
  }

  public Funds getBalance() {
    return balance;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}
