package com.manipal.corejavaproj.model;

public class ToyRental {
	
	private int rental_id;
	private int customer_id;
	private int toy_id;
	private int rental_amount_per_day;
	private int total_amount;
	private int fine;
	private String rental_status;
	private String rental_start_date;
	private String rental_end_date;
	
	public ToyRental() {}
	
	public ToyRental(int rental_id, int customer_id, int toy_id, int rental_amount_per_day, int total_amount, int fine,
			String rental_status, String rental_start_date, String rental_end_date) {
		super();
		this.rental_id = rental_id;
		this.customer_id = customer_id;
		this.toy_id = toy_id;
		this.rental_amount_per_day = rental_amount_per_day;
		this.total_amount = total_amount;
		this.fine = fine;
		this.rental_status = rental_status;
		this.rental_start_date = rental_start_date;
		this.rental_end_date = rental_end_date;
	}	
	
	public int getRental_id() {
		return rental_id;
	}
	public void setRental_id(int rental_id) {
		this.rental_id = rental_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getToy_id() {
		return toy_id;
	}
	public void setToy_id(int toy_id) {
		this.toy_id = toy_id;
	}
	public int getRental_amount_per_day() {
		return rental_amount_per_day;
	}
	public void setRental_amount_per_day(int rental_amount_per_day) {
		this.rental_amount_per_day = rental_amount_per_day;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
	public String getRental_status() {
		return rental_status;
	}
	public void setRental_status(String rental_status) {
		this.rental_status = rental_status;
	}
	public String getRental_start_date() {
		return rental_start_date;
	}
	public void setRental_start_date(String rental_start_date) {
		this.rental_start_date = rental_start_date;
	}
	public String getRental_end_date() {
		return rental_end_date;
	}
	public void setRental_end_date(String rental_end_date) {
		this.rental_end_date = rental_end_date;
	}	
	public String toString() {
		return "Rental Id: "+ this.rental_id + "\nCustomer Id: "+ this.customer_id + "\nToy Id: " + this.toy_id + "\nRental Amount Per Day: " + this.rental_amount_per_day + "\nTotal Amount: " + this.total_amount + "\nRental Status: " + this.rental_status + "\nRent Starts From: " + this.rental_start_date + "\nRent Ends On: " + this.rental_end_date; 
	}
}
