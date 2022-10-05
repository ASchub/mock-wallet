package com.example.mockwallet.core.service;

import com.example.mockwallet.api.TransferRequest;
import com.example.mockwallet.core.domain.Funds;
import com.example.mockwallet.core.domain.Transaction;
import com.example.mockwallet.core.exception.WalletException;
import com.example.mockwallet.infra.repository.TransactionRepository;
import com.example.mockwallet.infra.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionService {

  private final WalletRepository walletRepository;
  private final TransactionRepository transactionRepository;

  @Autowired
  public TransactionService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
    this.walletRepository = walletRepository;
    this.transactionRepository = transactionRepository;
  }

  public void transfer(TransferRequest transferRequest) {
    var sendingWallet = walletRepository.fetchAccount(transferRequest.getSenderWalletId());
    var receivingWallet = walletRepository.fetchAccount(transferRequest.getReceivingWalletId());
    var transferAmount = transferRequest.getAmount();
    var newSendingWalletTempBalance = sendingWallet.getBalance().subtract(transferAmount);
    var newReceivingWalletTempBalance = receivingWallet.getBalance().add(transferAmount);

    if (newSendingWalletTempBalance.compareTo(new Funds(0)) < 0) {
      throw WalletException.badRequest("Insufficient funds");
    }
    walletRepository.updateFundsForAccount(sendingWallet, newSendingWalletTempBalance);
    walletRepository.updateFundsForAccount(receivingWallet, newReceivingWalletTempBalance);
    transactionRepository.writeTransaction(sendingWallet.getId(), receivingWallet.getId(), transferAmount);
  }

  public List<Transaction> getTransactions(String account) {
    return transactionRepository.fetchTransactions(account);
  }
}
