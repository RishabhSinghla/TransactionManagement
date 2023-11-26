package com.nagarro.gatewayservice.entity;

/**
 *  @author rishabhsinghla
 *  Entity class for transaction
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	private Long id;
	private String accountNumber;
	private String transactionId;
	private String status;
	private String amount;
	private String date;

}
