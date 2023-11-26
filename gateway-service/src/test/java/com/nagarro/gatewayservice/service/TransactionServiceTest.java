package com.nagarro.gatewayservice.service;

/**
 *  @author rishabhsinghla
 *  Test class for Transaction Service
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import com.nagarro.gatewayservice.entity.Transaction;

class TransactionServiceTest {

	@Test
	void testGetTransactionsForAllStatus() {
		WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
		TransactionService transactionService = new TransactionService(webClient);

		List<Transaction> result = transactionService.getTransactions("123456", "all");

		assertEquals(0, result.size());
	}

	@Test
	void testGetTransactionsForSingleStatus() {
		WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
		TransactionService transactionService = new TransactionService(webClient);

		List<Transaction> result = transactionService.getTransactions("123456", "success");

		assertEquals(0, result.size());
	}
}
