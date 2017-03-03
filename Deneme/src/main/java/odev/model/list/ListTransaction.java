package odev.model.list;

public class ListTransaction {
	private ListTransactionMerchant merchant;

	public ListTransaction(ListTransactionMerchant merchant){
		this.merchant = merchant;
	}
	
	public ListTransactionMerchant getMerchant() {
		return merchant;
	}

	public void setMerchant(ListTransactionMerchant merchant) {
		this.merchant = merchant;
	}
}
