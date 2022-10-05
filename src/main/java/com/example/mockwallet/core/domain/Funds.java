package com.example.mockwallet.core.domain;

import com.example.mockwallet.core.exception.WalletException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Funds implements Comparable<Funds>{
  private final BigDecimal value;

  //mostly doing the null check because domain objects should have immutable null fields when possible
  public Funds(Number value) {
    if (value == null) {
      throw WalletException.internalError();
    }
    this.value = BigDecimal.valueOf(value.doubleValue()).setScale(2, RoundingMode.FLOOR);
  }

  public double getDoubleValue() {
    return value.doubleValue();
  }

  public Funds subtract(Funds amount) {
    var newValue = value.subtract(amount.value);
    return new Funds(newValue);
  }


  @Override
  public int compareTo(Funds o) {
    return value.compareTo(o.value);
  }

  public Funds add(Funds amount) {
    return new Funds(value.add(amount.value));
  }
}
