package com.nagarro.failurebackend.controller;

/**
 *  @author rishabhsinghla
 *  Failure Controller for getting fail transactions
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.failurebackend.entity.Transaction;
import com.nagarro.failurebackend.repo.TransactionRepository;

@RestController
@RequestMapping("/failure")
public class FailureController {

	@Autowired
	private TransactionRepository transactionRepository;

	@GetMapping("/{accountNumber}")
	public ResponseEntity<List<Transaction>> getFailureTransactions(@PathVariable String accountNumber) {
		List<Transaction> transactions = transactionRepository.findByAccountNumberAndStatus(accountNumber, "fail");
		return ResponseEntity.ok(transactions);
	}
}
