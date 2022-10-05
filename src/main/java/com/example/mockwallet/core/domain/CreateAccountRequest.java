package com.example.mockwallet.core.domain;

import com.example.mockwallet.core.exception.WalletException;

public final class CreateAccountRequest {
  private final String accountId;
  private final Funds initialBalance;

  public CreateAccountRequest(String accountId, Funds initialBalance) {
    if (accountId == null || initialBalance == null) {
      throw WalletException.internalError("create account request can't contain null values");
    }
    this.accountId = accountId;
    this.initialBalance = initialBalance;
  }

  public String getAccountId() {
    return accountId;
  }

  public Funds getInitialFunds() {
    return initialBalance;
  }
}
