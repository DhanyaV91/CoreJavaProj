package com.manipal.corejavaproj.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.manipal.corejavaproj.model.Customer;
import com.manipal.corejavaproj.model.Toy;
import com.manipal.corejavaproj.model.ToyRental;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ToyRentalServiceImpl  implements ToyRentalService{
	List<Customer> toyRentalArray;
	
	public int bookToy(int toyid, int custid, int days) throws SQLException, ClassNotFoundException {
		Connection conn = DAO.getConnection();
		ToyService t = new ToyServiceImpl();
		List<Toy> ToyList= t.search(toyid);
		if(!ToyList.isEmpty()) {
			LocalDate rent_start_date = LocalDate.now();
			LocalDate rent_end_date = LocalDate.now().plusDays(days);
			int rent_amt_per_day = 300;
			int total_amt =  getTotalRentAmount(days, rent_amt_per_day);
			int fine = 0;
			String status = "rented";
			String insertQuery = "insert into toy_rental(customer_id, toy_id, rental_start_date, rental_end_date, rental_amt_per_day, total_amt, fine, rent_status) values(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(insertQuery);
			stmt.setInt(1, custid);
			stmt.setInt(2, toyid);
			stmt.setString(3, rent_start_date.toString());
			stmt.setString(4, rent_end_date.toString());			
			stmt.setInt(5, rent_amt_per_day);
			stmt.setInt(6, total_amt);
			stmt.setInt(7, fine);
			stmt.setString(8, status);	
			int b = stmt.executeUpdate();
			return b;
		}else {
			return 0;
		}
	}

	public int returnToy(int tid) throws ClassNotFoundException, SQLException {
		Connection conn = DAO.getConnection();
		String deleteQuery = "delete from toy_rental where toy_id="+tid;
		PreparedStatement stmt = conn.prepareStatement(deleteQuery);
		int b = stmt.executeUpdate();

		return b;
	}
	
	public List<Customer> getRentalDetails(int custid) throws SQLException, ClassNotFoundException{
		Connection conn = DAO.getConnection();
		Statement stmt = conn.createStatement();
		String query = "select c.customer_id, c.customer_name, c.city, c.state, c.zip, c.country from customer as c, toy_rental as tr where c.customer_id=tr.customer_id and c.customer_id="+custid;
		ResultSet rs = stmt.executeQuery(query);
		toyRentalArray = new ArrayList<Customer>();
		while(rs.next()) {
			Customer toyrent = new Customer();
			  toyrent.setCustId(rs.getInt(1)); 
			  toyrent.setCustName(rs.getString(2));
			  toyrent.setCity(rs.getString(3));
			  toyrent.setState(rs.getString(4));
			  toyrent.setZip(rs.getInt(5)); 
			  toyrent.setCountry(rs.getString(6));			  
			toyRentalArray.add(toyrent);
		}
		return toyRentalArray;
	}
	
	public int getTotalRentAmount(int rent_days, int rent_amt_per_day) {
		int totalAmtForMonth = rent_days * rent_amt_per_day;
		return totalAmtForMonth;
	}
	
	public List<ToyRental> toyRentDetails(int toyid) throws ClassNotFoundException, SQLException {
		Connection conn = DAO.getConnection();
		Statement stmt = conn.createStatement();
		String query = "select toy_id, customer_id, rental_start_date, rental_end_date, rent_status from toy_rental where toy_id="+toyid;
		ResultSet rs = stmt.executeQuery(query);
		List<ToyRental> toyArray = new ArrayList<ToyRental>();
		
		while(rs.next()) {
			ToyRental toy_rent = new ToyRental(); 
			toy_rent.setToy_id(rs.getInt(1));
			toy_rent.setCustomer_id(rs.getInt(2));
			toy_rent.setRental_start_date(rs.getString(3));
			toy_rent.setRental_end_date(rs.getString(4));
			toy_rent.setRental_status(rs.getString(5));
			toyArray.add(toy_rent);
		}
	return toyArray;
	}
}
