package com.example.model.list;

public class ListFx {
	private ListFxMerchant merchant;

	public ListFx(ListFxMerchant merchant){
		this.merchant = merchant;
	}
	
	public ListFxMerchant getMerchant() {
		return merchant;
	}

	public void setMerchant(ListFxMerchant merchant) {
		this.merchant = merchant;
	}
}
