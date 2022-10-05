package com.example.mockwallet.infra.db.tables;

import com.example.mockwallet.infra.db.StringIdTableBase;
import org.jooq.Field;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class WalletTable extends StringIdTableBase {

  public static final WalletTable WALLET_TABLE = new WalletTable();
  public final Field<Double> BALANCE = makeField("BALANCE", Double.class);
  public final Field<Timestamp> CREATED_AT = makeField("CREATED_AT", Timestamp.class);

  @Override
  public String getTableName() {
    return "WALLET";
  }
}
