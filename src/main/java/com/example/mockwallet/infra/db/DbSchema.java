package com.example.mockwallet.infra.db;

import org.jooq.Name;
import org.jooq.impl.DSL;

public class DbSchema {

  public static final DbSchema DB_SCHEMA = new DbSchema();

  public final org.jooq.Schema SCHEMA = makeSchema();

  public String getSchemaName() {
    return "MOCKDB";
  }

  public org.jooq.Schema makeSchema() {
    return DSL.schema(makeSchemaName());
  }

  private Name makeSchemaName() {
    return DSL.name(getSchemaName());
  }

}
