package com.example.mockwallet.api;

import com.example.mockwallet.core.domain.Funds;

import java.math.BigDecimal;

class TransferRequestMapper {
  public static TransferRequest fromDto(String fromAccountId, String receivingAccountId, Number amount) {
    return new TransferRequest(fromAccountId, receivingAccountId, new Funds(BigDecimal.valueOf(amount.doubleValue())));
  }
}
