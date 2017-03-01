package com.example.model.list;

public class ListMerchant {
	private int id;
	private boolean allowPartialRefund;
	private String name;
	private boolean allowPartialCapture;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isAllowPartialRefund() {
		return allowPartialRefund;
	}
	public void setAllowPartialRefund(boolean allowPartialRefund) {
		this.allowPartialRefund = allowPartialRefund;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAllowPartialCapture() {
		return allowPartialCapture;
	}
	public void setAllowPartialCapture(boolean allowPartialCapture) {
		this.allowPartialCapture = allowPartialCapture;
	}
}
