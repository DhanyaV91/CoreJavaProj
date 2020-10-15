package com.manipal.corejavaproj.model;

class InvalidNameException extends Exception{
	public InvalidNameException(String string) {
		super(string);
	}
}

public class Customer {

	private int custId;
	private String custName;
	private String password;
	private String city;
	private String state;
	private int zip;
	private String country;
	
	public Customer() {}
	
	public Customer(String custName, String password, String city, String state, int zip, String country) throws InvalidNameException {
		super();
		String str = "!@#$%&*()'+,-./:;<=>?[]^_`{|}0123456789";
		if(custName.length() > 6) {
			for(int i=0; i<custName.length(); i++) {
				if(str.contains(Character.toString(custName.charAt(i)))) {
					throw new InvalidNameException("Customer name is invalid!");
				}else {
					this.custName = custName;
				}
			}
		}else {
			throw new InvalidNameException("Customer name is invalid!");
		}
		this.password = password;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}


	public int getCustId() {
		return custId;
	}


	public void setCustId(int custId) {
		this.custId = custId;
	}


	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		String str = "!@#$%&*()'+,-./:;<=>?[]^_`{|}0123456789";
		for(int i=0; i<custName.length();i++) {
			if(str.contains(Character.toString(custName.charAt(i)))) {
				System.out.println("Customer name is invalid!");
				System.exit(0);
			}else {
				this.custName = custName;
			}
			
		}
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	public int getZip() {
		return zip;
	}


	public void setZip(int zip) {
		this.zip = zip;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}
	
	public String toString() {
		return "Customer ID: "+ this.custId + "\nCustomer Name: "+ this.custName + "\nAddress: " + this.city + ", " + this.state + " - " + this.zip + ", " + this.country; 
	}
}
