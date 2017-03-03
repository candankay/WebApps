package odev.model;


public class CustomerClient {

	private int id;
	private String created_at;
	private String deleted_at;
	private String updated_at;
	private String token;
	private int expiryMonth;
	private int expiryYear;
	private int startMonth;
	private int startYear;
	private int issueNumber;
	private String birthday;
	private String gender;
	private String number;
	private CustomerShipping billPerson;
	private CustomerBilling shipPerson;
	private String email;
	
	
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getDeleted_at() {
		return deleted_at;
	}
	public void setDeleted_at(String deleted_at) {
		this.deleted_at = deleted_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public int getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}
	public int getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public int getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(int issueNumber) {
		this.issueNumber = issueNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public CustomerShipping getBillPerson() {
		return billPerson;
	}
	public void setBillPerson(CustomerShipping billPerson) {
		this.billPerson = billPerson;
	}
	public CustomerBilling getShipPerson() {
		return shipPerson;
	}
	public void setShipPerson(CustomerBilling shipPerson) {
		this.shipPerson = shipPerson;
	}
}