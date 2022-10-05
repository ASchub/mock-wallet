package com.example.mockwallet.api;

import com.example.mockwallet.core.domain.CreateAccountRequest;
import com.example.mockwallet.core.domain.Funds;

import java.util.UUID;

class CreateAccountRequestMapper {
  /*
  not using actual dto classes so this layering is a bit overkill
   */

  public static CreateAccountRequest fromDto(Number funds) {
    var accountId = UUID.randomUUID().toString();
    Number actualFunds = funds != null ? funds : 0;
    return new CreateAccountRequest(accountId, new Funds(actualFunds));
  }
}
