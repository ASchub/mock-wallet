package com.example.mockwallet.infra.db;

import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;


public abstract class TableBase {

  public final Table<Record> TABLE = makeTable();

  public abstract Schema getSchema();

  public abstract String getTableName();

  public <T> Field<T> makeField(String columnName, Class<T> type) {
    return DSL.field(makeFieldName(columnName), type);
  }

  public Table<Record> makeTable() {
    return DSL.table(makeTableName());
  }

  private Name makeTableName() {
    return DSL.name(getSchema().getName(), getTableName());
  }

  private Name makeFieldName(String columnName) {
    return DSL.name(getSchema().getName(), getTableName(), columnName);
  }
}

