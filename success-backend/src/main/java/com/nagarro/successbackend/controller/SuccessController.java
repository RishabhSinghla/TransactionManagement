package com.nagarro.successbackend.controller;

/**
 *  @author rishabhsinghla
 *  Controller for getting success transactions
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.successbackend.entity.Transaction;
import com.nagarro.successbackend.repo.TransactionRepository;

@RestController
@RequestMapping("/success")
public class SuccessController {

	@Autowired
	private TransactionRepository transactionRepository;

	@GetMapping("/{accountNumber}")
	public ResponseEntity<List<Transaction>> getSuccessTransactions(@PathVariable String accountNumber) {
		List<Transaction> transactions = transactionRepository.findByAccountNumberAndStatus(accountNumber, "success");
		return ResponseEntity.ok(transactions);
	}
}
