package com.example.model;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class Customer {

	private int id;
	private DateTimeAtCompleted created;
	private DateTimeAtCompleted deleted;
	private DateTimeAtCompleted updated;
	private String number;
	private int expiryMonth;
	private int expiryYear;
	private int startMonth;
	private int startYear;
	private int issueNumber;
	private String email;
	private DateTimeAtCompleted birthday;
	private String gender;
	private Person billPerson;
	private Person shipPerson;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DateTimeAtCompleted getCreated() {
		return created;
	}
	public void setCreated(DateTimeAtCompleted created) {
		this.created = created;
	}
	public DateTimeAtCompleted getDeleted() {
		return deleted;
	}
	public void setDeleted(DateTimeAtCompleted deleted) {
		this.deleted = deleted;
	}
	public DateTimeAtCompleted getUpdated() {
		return updated;
	}
	public void setUpdated(DateTimeAtCompleted updated) {
		this.updated = updated;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public DateTimeAtCompleted getBirthday() {
		return birthday;
	}
	public void setBirthday(DateTimeAtCompleted birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Person getBillPerson() {
		return billPerson;
	}
	public void setBillPerson(Person billPerson) {
		this.billPerson = billPerson;
	}
	public Person getShipPerson() {
		return shipPerson;
	}
	public void setShipPerson(Person shipPerson) {
		this.shipPerson = shipPerson;
	}
}