package com.example.mockwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockWalletApplication.class, args);
	}

}

/*
  Implement a basic bookkeeping service that handles monetary transactions and keeps track of account
  balances. This is similar to a normal bank account.
  The service should have at least the following API methods:
  1. get balance - return the balance for an account
  2. transfer - transfer funds to or from an account
  3. list transactions - list transaction entries for an account
  4. create account (optional) - create an account, this can be done implicitly when creating
  transactions or explicitly
  The implementation should be a HTTP server exposing the API using REST. No UI is required.
  We will be using curl or POSTman to test the API.

 */