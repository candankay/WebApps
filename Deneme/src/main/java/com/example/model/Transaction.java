package com.example.model;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class Transaction {

	private String id;
	private String transactionId;
	private String referenceNo;
	private String status;
	private String channel;
	private String chainId;
	private String operation;
	private int fxTransactionId;
	private DateTimeAtCompleted created;
	private DateTimeAtCompleted updated;
	private int acquirerTransactionId; 
	private String code;
	private String message;
	
	private TransactionFx fx;
	private Merchant merchant;
	private Customer customer;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getChainId() {
		return chainId;
	}
	public void setChainId(String chainId) {
		this.chainId = chainId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getFxTransactionId() {
		return fxTransactionId;
	}
	public void setFxTransactionId(int fxTransactionId) {
		this.fxTransactionId = fxTransactionId;
	}
	public DateTimeAtCompleted getCreated() {
		return created;
	}
	public void setCreated(DateTimeAtCompleted created) {
		this.created = created;
	}
	public DateTimeAtCompleted getUpdated() {
		return updated;
	}
	public void setUpdated(DateTimeAtCompleted updated) {
		this.updated = updated;
	}
	public int getAcquirerTransactionId() {
		return acquirerTransactionId;
	}
	public void setAcquirerTransactionId(int acquirerTransactionId) {
		this.acquirerTransactionId = acquirerTransactionId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TransactionFx getFx() {
		return fx;
	}
	public void setFx(TransactionFx fx) {
		this.fx = fx;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
