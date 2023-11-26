package com.nagarro.gatewayservice.service;

/**
 *  @author rishabhsinghla
 *  Service class for getting transaction according to status
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nagarro.gatewayservice.entity.Transaction;

@Service
public class TransactionService {

	private final WebClient webClient;

	@Autowired
	public TransactionService(WebClient webClient) {
		this.webClient = webClient;
	}

	public List<Transaction> getTransactions(String accountNumber, String status) {
		List<Transaction> all = new ArrayList<>();

		if (status.equalsIgnoreCase("all")) {
			List<CompletableFuture<List<Transaction>>> futures = new ArrayList<>();

			futures.add(fetchTransactionsAsync("http://localhost:8084/success/" + accountNumber));
			futures.add(fetchTransactionsAsync("http://localhost:8082/failure/" + accountNumber));
			futures.add(fetchTransactionsAsync("http://localhost:8083/pending/" + accountNumber));

			CompletableFuture<Void> allOf = CompletableFuture
					.allOf(futures.toArray(new CompletableFuture[futures.size()]));

			List<Transaction> allTransactions = allOf.thenApply(v -> futures.stream().map(CompletableFuture::join)
					.flatMap(List::stream).collect(Collectors.toList())).join();

			all.addAll(allTransactions);
		} else {
			List<Transaction> transactions = fetchTransactionsAsync(getServiceUrl(status, accountNumber)).join();
			all.addAll(transactions);
		}

		return all;
	}

	private CompletableFuture<List<Transaction>> fetchTransactionsAsync(String uri) {
		ParameterizedTypeReference<List<Transaction>> responseType = new ParameterizedTypeReference<List<Transaction>>() {
		};

		return CompletableFuture
				.supplyAsync(() -> webClient.get().uri(uri).retrieve().bodyToMono(responseType).block());
	}

	private String getServiceUrl(String status, String accountNumber) {
		if (status.equalsIgnoreCase("success")) {
			return "http://localhost:8084/" + status + "/" + accountNumber;
		} else if (status.equalsIgnoreCase("failure")) {
			return "http://localhost:8082/" + status + "/" + accountNumber;
		} else if (status.equalsIgnoreCase("pending")) {
			return "http://localhost:8083/" + status + "/" + accountNumber;
		}
		return accountNumber;
	}
}