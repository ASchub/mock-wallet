package com.example.mockwallet.infra.repository;

import com.example.mockwallet.core.domain.Funds;
import com.example.mockwallet.core.domain.Transaction;
import com.example.mockwallet.core.domain.Wallet;
import com.example.mockwallet.core.exception.WalletException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

import static com.example.mockwallet.infra.db.tables.TransactionTable.TRANSACTION_TABLE;
import static com.example.mockwallet.infra.db.tables.WalletTable.WALLET_TABLE;

@Repository
@Transactional
public class TransactionRepository {

  private final DSLContext jooq;

  @Autowired
  public TransactionRepository(DSLContext jooq) {
    this.jooq = jooq;
  }

  public void writeTransaction(String sendingWalletId, String receivingWalletId, Funds transferAmount) {
    if (sendingWalletId == null || receivingWalletId == null || transferAmount == null) {
      throw WalletException.internalError();
    }
    jooq
      .insertInto(TRANSACTION_TABLE.TABLE)
      .columns(
        TRANSACTION_TABLE.SENDER,
        TRANSACTION_TABLE.RECEIVER,
        TRANSACTION_TABLE.AMOUNT
      )
      .values(
        sendingWalletId,
        receivingWalletId,
        transferAmount.getDoubleValue()
      ).execute();
  }

  public List<Transaction> fetchTransactions(String accountId) {
    return jooq
      .select()
      .from(TRANSACTION_TABLE.TABLE)
      .where(TRANSACTION_TABLE.RECEIVER.eq(accountId)).or(TRANSACTION_TABLE.SENDER.eq(accountId))
      .orderBy(TRANSACTION_TABLE.CREATED_AT.desc())
      .fetch()
      .map(record -> createTransaction(record, accountId));
  }

  private Transaction createTransaction(Record transactionRecord, String accountId) {
    var receiver = transactionRecord.get(TRANSACTION_TABLE.RECEIVER);
    Transaction.TransactionType type;
    String otherAccountId;
    if (receiver.equals(accountId)) {
      type = Transaction.TransactionType.RECEIVED;
      otherAccountId = transactionRecord.get(TRANSACTION_TABLE.SENDER);
    } else {
      type = Transaction.TransactionType.SENT;
      otherAccountId = receiver;
    }
    return new Transaction(new Funds(transactionRecord.get(TRANSACTION_TABLE.AMOUNT)), type, otherAccountId, LocalDateTime.ofInstant(transactionRecord.get(TRANSACTION_TABLE.CREATED_AT).toInstant(), ZoneId.systemDefault()));
  }
}
