package com.nagarro.pendingbackend.controller;

/**
 *  @author rishabhsinghla
 *  Controller class for sending pending transactions
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pendingbackend.entity.Transaction;
import com.nagarro.pendingbackend.repo.TransactionRepository;

@RestController
@RequestMapping("/pending")
public class PendingController {

	@Autowired
	private TransactionRepository transactionRepository;

	@GetMapping("/{accountNumber}")
	public ResponseEntity<List<Transaction>> getPendingTransactions(@PathVariable String accountNumber) {
		List<Transaction> transactions = transactionRepository.findByAccountNumberAndStatus(accountNumber, "pending");
		return ResponseEntity.ok(transactions);
	}
}
