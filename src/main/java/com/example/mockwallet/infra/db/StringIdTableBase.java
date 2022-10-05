package com.example.mockwallet.infra.db;

import org.jooq.Field;
import org.jooq.Schema;

import static com.example.mockwallet.infra.db.DbSchema.DB_SCHEMA;

public abstract class StringIdTableBase extends TableBase {

  public final Field<String> ID = makeField("ID", String.class);

  @Override
  public Schema getSchema() {
    return DB_SCHEMA.SCHEMA;
  }

}


