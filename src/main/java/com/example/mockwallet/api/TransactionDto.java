package com.example.mockwallet.api;

import com.example.mockwallet.core.domain.Transaction;

import java.io.Serializable;
import java.time.LocalDateTime;


/*
Would probably use jackson serializing instead normally
 */
class TransactionDto implements Serializable {
  private double amount;
  private boolean received;
  private boolean sent;
  private String otherAccountId;
  private LocalDateTime timeOfTransaction;

  public TransactionDto(double amount, Transaction.TransactionType transactionType, String otherAccountId, LocalDateTime timeOfTransaction) {
    this.amount = amount;
    if (transactionType == Transaction.TransactionType.SENT) {
      sent = true;
      received = false;
    } else {
      sent = false;
      received = true;
    }
    this.otherAccountId = otherAccountId;
    this.timeOfTransaction = timeOfTransaction;
  }

  public double getAmount() {
    return amount;
  }

  public boolean isReceived() {
    return received;
  }

  public boolean isSent() {
    return sent;
  }

  public String getOtherAccountId() {
    return otherAccountId;
  }

  public LocalDateTime getTimeOfTransaction() {
    return timeOfTransaction;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public void setReceived(boolean received) {
    this.received = received;
  }

  public void setSent(boolean sent) {
    this.sent = sent;
  }

  public void setOtherAccountId(String otherAccountId) {
    this.otherAccountId = otherAccountId;
  }

  public void setTimeOfTransaction(LocalDateTime timeOfTransaction) {
    this.timeOfTransaction = timeOfTransaction;
  }
}
