package com.hpe.calEStore.model;

import org.hibernate.validator.constraints.NotEmpty;

public class UserAddress {
	
	@NotEmpty(message="Address Line1 should not be empty.")
	private String addressLine1;
	
	@NotEmpty(message="Address Line 2 should not be empty.")
	private String addressLine2;
	
	@NotEmpty(message="City should not be empty.")
	private String city;
	
	@NotEmpty(message="State should not be empty.")
	private String state;
	
	@NotEmpty(message="Zip code should not be empty.")
	private String zipCode;
	
	
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	
	
}
