package com.example.mockwallet.api;

import com.example.mockwallet.core.service.TransactionService;
import com.example.mockwallet.core.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/wallets")
public class WalletController {
  /*
  TODO
    Auth (jwt)
    better requirements for account creation (validation mostly)
    account id should be in jwt
   */

  private final WalletService walletService;
  private final TransactionService transactionService;

  @Autowired
  public WalletController(WalletService accountRepository, TransactionService transactionService) {
    this.walletService = accountRepository;
    this.transactionService = transactionService;
  }

  //intial funds is just for this mocks testing purpose, transfer validates origin accounts availability.
  @PostMapping("/create")
  public String createWallet(
    @RequestParam(value = "initialBalance", required = false) Number balance
  ) {
    return walletService.createAccount(CreateAccountRequestMapper.fromDto(balance));
  }

  @GetMapping("/{account}/balance")
  public double getBalance(@PathVariable("account") String account) {
    return walletService.getBalance(account).getDoubleValue();
  }

  @PutMapping("/{account}/transfer/{receiver}/{amount}")
  public void transferFunds(
    @PathVariable("account") String account,
    @PathVariable("receiver") String receivingAccount,
    @PathVariable("amount") Number amount) {

    transactionService.transfer(TransferRequestMapper.fromDto(account, receivingAccount, amount));
  }

  /*
  probably want pagination
   */
  @GetMapping("/{account}/transactions")
  public List<TransactionDto> getTransactions(@PathVariable("account") String account) {
    return TransactionMapper.toDto(transactionService.getTransactions(account));
  }
}
