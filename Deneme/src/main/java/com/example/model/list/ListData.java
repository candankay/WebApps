package com.example.model.list;

import com.example.model.Acquirer;
import com.example.model.CustomerPerson;

public class ListData {
	private ListFx fx;
	private String updated_at;
	private ListIpn ipn;
	private String created_at;
	private ListMerchant merchant;
	private ListTransaction transaction;
	private Acquirer acquirer;
	private CustomerPerson customerInfo;
	private boolean refundable;
	
	public ListData(){
		
	}
	
	public ListData(ListFx fx, ListIpn ipn, ListMerchant merchant, ListTransaction transaction, Acquirer acquirer,
			CustomerPerson customerInfo) {
		super();
		this.fx = fx;
		this.ipn = ipn;
		this.merchant = merchant;
		this.transaction = transaction;
		this.acquirer = acquirer;
		this.customerInfo = customerInfo;
	}

	public boolean isRefundable() {
		return refundable;
	}
	public void setRefundable(boolean refundable) {
		this.refundable = refundable;
	}
	public ListFx getFx() {
		return fx;
	}
	public void setFx(ListFx fx) {
		this.fx = fx;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public ListIpn getIpn() {
		return ipn;
	}
	public void setIpn(ListIpn ipn) {
		this.ipn = ipn;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public ListMerchant getMerchant() {
		return merchant;
	}
	public void setMerchant(ListMerchant merchant) {
		this.merchant = merchant;
	}
	public ListTransaction getTransaction() {
		return transaction;
	}
	public void setTransaction(ListTransaction transaction) {
		this.transaction = transaction;
	}
	public Acquirer getAcquirer() {
		return acquirer;
	}
	public void setAcquirer(Acquirer acquirer) {
		this.acquirer = acquirer;
	}
	public CustomerPerson getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(CustomerPerson customerInfo) {
		this.customerInfo = customerInfo;
	}
}
