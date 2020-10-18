package com.manipal.corejavaproj.model;

class InvalidAgeException extends Exception{
	public InvalidAgeException(String string) {
		super(string);
	}
}

public class Toy {
	
	private int toyId;
	private String toyName;
	private String toyType;
	private int minAge;
	private int maxAge;
	private int price;
	private int quantity;
	private int rentalAmount;
	
	public Toy() {}
	
	public Toy(String toyName, String toyType, int minAge, int maxAge, int price, int quantity, int rentalAmount) throws InvalidAgeException, InvalidNameException {
		super();
		String str = "!@#$%&*()'+,-./:;<=>?[]^_`{|}0123456789";
		for(int i=0; i<toyName.length(); i++) {
			if(str.contains(Character.toString(toyName.charAt(i)))) {
				throw new InvalidNameException("Toy name is invalid!");
			}else {
				this.toyName = toyName;
			}
		}
		this.toyType = toyType;
		if(minAge < 0 && maxAge > 12) {
			throw new InvalidAgeException("Toy age is invalid!");
		}else {
			this.minAge = minAge;
			this.maxAge = maxAge;
		}		
		this.price = price;
		this.quantity = quantity;
		this.rentalAmount = rentalAmount;
	}	
	
	public int getToyId() {
		return toyId;
	}

	public void setToyId(int toyId) {
		this.toyId = toyId;
	}

	public String getToyName() {
		return toyName;
	}

	public void setToyName(String toyName) throws InvalidNameException {
		String str = "!@#$%&*()'+,-./:;<=>?[]^_`{|}0123456789";
		for(int i=0; i<toyName.length(); i++) {
			if(str.contains(Character.toString(toyName.charAt(i)))) {
				throw new InvalidNameException("Toy name is invalid!");
			}else {
				this.toyName = toyName;
			}
		}
	}

	public String getToyType() {
		return toyType;
	}

	public void setToyType(String toyType) {
		this.toyType = toyType;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRentalAmount() {
		return rentalAmount;
	}

	public void setRentalAmount(int rentalAmount) {
		this.rentalAmount = rentalAmount;
	}
	
	public String toString() {
		return "Toy ID: "+ this.toyId + "\nToy Name: "+ this.toyName + "\nToy Type: " + this.toyType + "\nMin Age: " + this.minAge + "\nMax Age: " + this.maxAge + "\nPrice: " + this.price + "\nQuantity: " + this.quantity + "\nRental Amount: " + this.rentalAmount+"\n\n"; 
	}
	
}
