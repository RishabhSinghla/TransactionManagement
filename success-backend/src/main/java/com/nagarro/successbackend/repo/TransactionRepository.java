package com.nagarro.successbackend.repo;

/**
 *  @author rishabhsinghla
 *  Repository interface for getting data from H2 database
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.successbackend.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByAccountNumberAndStatus(String accountNumber, String status);
}
