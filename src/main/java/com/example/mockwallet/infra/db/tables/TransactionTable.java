package com.example.mockwallet.infra.db.tables;

import com.example.mockwallet.infra.db.StringIdTableBase;
import org.jooq.Field;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TransactionTable extends StringIdTableBase {

  public static final TransactionTable TRANSACTION_TABLE = new TransactionTable();
  public final Field<String> SENDER = makeField("SENDER", String.class);
  public final Field<String> RECEIVER = makeField("RECEIVER", String.class);
  public final Field<Double> AMOUNT = makeField("AMOUNT", Double.class);
  public final Field<Timestamp> CREATED_AT = makeField("CREATED_AT", Timestamp.class);

  @Override
  public String getTableName() {
    return "TRANSACTION";
  }
}