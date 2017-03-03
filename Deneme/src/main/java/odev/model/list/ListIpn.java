package odev.model.list;

public class ListIpn {
	private ListIpnMerchant merchant;
	private boolean sent;
	public ListIpnMerchant getMerchant() {
		return merchant;
	}
	public void setMerchant(ListIpnMerchant merchant) {
		this.merchant = merchant;
	}
	public boolean isSent() {
		return sent;
	}
	public void setSent(boolean sent) {
		this.sent = sent;
	}
}
