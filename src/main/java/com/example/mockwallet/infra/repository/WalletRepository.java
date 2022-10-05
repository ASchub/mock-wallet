package com.example.mockwallet.infra.repository;

import com.example.mockwallet.core.domain.Wallet;
import com.example.mockwallet.core.domain.Funds;
import com.example.mockwallet.core.exception.WalletException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.example.mockwallet.infra.db.tables.WalletTable.WALLET_TABLE;

@Repository
@Transactional
public class WalletRepository {

  private final DSLContext jooq;

  @Autowired
  public WalletRepository(DSLContext jooq) {
    this.jooq = jooq;
  }

  public void writeWallet(String walletId, Funds initialBalance) {
    if (walletId == null || initialBalance == null) {
      throw WalletException.internalError();
    }
    jooq
      .insertInto(WALLET_TABLE.TABLE)
      .columns(
        WALLET_TABLE.ID,
        WALLET_TABLE.BALANCE
      )
      .values(
        walletId,
        initialBalance.getDoubleValue()
      ).execute();
  }

  public Wallet fetchWallet(String accountId) {
    if (accountId == null) {
      throw WalletException.internalError();
    }
    var account = jooq.select()
      .from(WALLET_TABLE.TABLE)
      .where(WALLET_TABLE.ID.eq(accountId))
      .fetchOne();
    if (account == null) {
      throw WalletException.notFound("Wallet not found");
    }
    return account.map(this::createWallet);
  }

  private Wallet createWallet(Record walletRecord) {
    return new Wallet(
      walletRecord.get(WALLET_TABLE.ID),
      new Funds(walletRecord.get(WALLET_TABLE.BALANCE)),
      LocalDateTime.ofInstant(walletRecord.get(WALLET_TABLE.CREATED_AT).toInstant(), ZoneId.systemDefault()));
  }

  public void updateFundsForWallet(Wallet wallet, Funds newBalance) {
    if (wallet == null || newBalance == null) {
      throw WalletException.internalError();
    }
    jooq.update(WALLET_TABLE.TABLE)
      .set(WALLET_TABLE.BALANCE, newBalance.getDoubleValue())
      .where(WALLET_TABLE.ID.eq(wallet.getId()))
      .execute();
  }
}
