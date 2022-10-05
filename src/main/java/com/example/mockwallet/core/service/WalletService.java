package com.example.mockwallet.core.service;

import com.example.mockwallet.api.TransferRequest;
import com.example.mockwallet.core.domain.CreateAccountRequest;
import com.example.mockwallet.core.domain.Funds;
import com.example.mockwallet.core.exception.WalletException;
import com.example.mockwallet.infra.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WalletService {
  /*
  TODO
  handle race conditions by locking resources (during transfer atleast)
  if account was in jwt, could easily make request scoped bean to hold it instead of input of all methods
   */
  private final WalletRepository walletRepository;

  @Autowired
  public WalletService(WalletRepository walletRepository) {
    this.walletRepository = walletRepository;
  }

  public String createAccount(CreateAccountRequest createAccountRequest) {
    if (createAccountRequest == null) {
      throw WalletException.internalError();
    }
    walletRepository.writeAccount(createAccountRequest.getAccountId(), createAccountRequest.getInitialFunds());
    return createAccountRequest.getAccountId();
  }

  public Funds getBalance(String accountId) {
    return walletRepository.fetchAccount(accountId).getBalance();
  }
}
