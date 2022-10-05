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

  public void writeAccount(String accountId, Funds initialBalance) {
    if (accountId == null || initialBalance == null) {
      throw WalletException.internalError();
    }
    jooq
      .insertInto(WALLET_TABLE.TABLE)
      .columns(
        WALLET_TABLE.ID,
        WALLET_TABLE.BALANCE
      )
      .values(
        accountId,
        initialBalance.getDoubleValue()
      ).execute();
  }

  public Wallet fetchAccount(String accountId) {
    if (accountId == null) {
      throw WalletException.internalError();
    }
    var account = jooq.select()
      .from(WALLET_TABLE.TABLE)
      .where(WALLET_TABLE.ID.eq(accountId))
      .fetchOne();
    if (account == null) {
      throw WalletException.notFound();
    }
    return account.map(this::createAccount);
  }

  private Wallet createAccount(Record accountRecord) {
    return new Wallet(
      accountRecord.get(WALLET_TABLE.ID),
      new Funds(accountRecord.get(WALLET_TABLE.BALANCE)),
      LocalDateTime.ofInstant(accountRecord.get(WALLET_TABLE.CREATED_AT).toInstant(), ZoneId.systemDefault()));
  }

  public void updateFundsForAccount(Wallet wallet, Funds newBalance) {
    if (wallet == null || newBalance == null) {
      throw WalletException.internalError();
    }
    jooq.update(WALLET_TABLE.TABLE)
      .set(WALLET_TABLE.BALANCE, newBalance.getDoubleValue())
      .where(WALLET_TABLE.ID.eq(wallet.getId()))
      .execute();
  }
}
