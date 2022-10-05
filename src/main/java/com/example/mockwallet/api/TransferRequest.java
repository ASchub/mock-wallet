package com.example.mockwallet.api;

import com.example.mockwallet.core.domain.Funds;
import com.example.mockwallet.core.exception.WalletException;

public class TransferRequest {
  private final String senderWalletId;
  private final String receivingWalletId;
  private final Funds amount;

  public TransferRequest(String senderWalletId, String receivingWalletId, Funds amount) {
    if (senderWalletId == null || receivingWalletId == null || amount == null) {
      throw WalletException.badRequest();
    }
    this.senderWalletId = senderWalletId;
    this.receivingWalletId = receivingWalletId;
    this.amount = amount;
  }

  public String getSenderWalletId() {
    return senderWalletId;
  }

  public String getReceivingWalletId() {
    return receivingWalletId;
  }

  public Funds getAmount() {
    return amount;
  }
}
