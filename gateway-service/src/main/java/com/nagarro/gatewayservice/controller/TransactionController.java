package com.nagarro.gatewayservice.controller;

/**
 *  @author rishabhsinghla
 *  Controller for sending transactions to client according to status
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.gatewayservice.entity.Transaction;
import com.nagarro.gatewayservice.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/{accountNumber}")
	public ResponseEntity<List<Transaction>> getTransactions(@PathVariable(name = "accountNumber") String accountNumber,
			@RequestParam(name = "status") String status) {
		List<Transaction> transactions = transactionService.getTransactions(accountNumber, status);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}
}