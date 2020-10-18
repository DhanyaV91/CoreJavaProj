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
import java.time.temporal.ChronoUnit;

public class ToyRentalServiceImpl  implements ToyRentalService{
	
	public int bookToy(int toyid, int custid, int days) throws SQLException, ClassNotFoundException {
		Connection conn = DAO.getConnection();
		ToyService t = new ToyServiceImpl();
		List<Toy> ToyList= t.search(toyid);
		if(!ToyList.isEmpty()) {
			Statement stmt = conn.createStatement();
			String search = "select * from toy_rental where toy_id="+toyid+" and customer_id="+custid;
			ResultSet rs = stmt.executeQuery(search);
			if(rs.next() == false) {
				LocalDate rent_start_date = LocalDate.now();
				LocalDate rent_end_date = LocalDate.now().plusDays(days);
				int rent_amt_per_day = 300;
				int total_amt =  getTotalRentAmount(days, rent_amt_per_day);
				int fine = 0;
				String status = "rented";
				String insertQuery = "insert into toy_rental(customer_id, toy_id, rental_start_date, rental_end_date, rental_amt_per_day, total_amt, fine, rent_status) values(?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement stmt1 = conn.prepareStatement(insertQuery);
				stmt1.setInt(1, custid);
				stmt1.setInt(2, toyid);
				stmt1.setString(3, rent_start_date.toString());
				stmt1.setString(4, rent_end_date.toString());			
				stmt1.setInt(5, rent_amt_per_day);
				stmt1.setInt(6, total_amt);
				stmt1.setInt(7, fine);
				stmt1.setString(8, status);	
				int b = stmt1.executeUpdate();
				return b;
			}else {
				return 0;
			}
		}else {
			return 0;
		}
	}

	public String returnToy(int tid, int cid) throws ClassNotFoundException, SQLException {
		Connection conn = DAO.getConnection();
		Statement stmt = conn.createStatement();
		String search = "select * from toy_rental where toy_id="+tid+" and customer_id="+cid;
		ResultSet rs = stmt.executeQuery(search);
		if(rs.next()) {
			LocalDate todaysDate = LocalDate.now();
			LocalDate last_date_of_rent = LocalDate.parse(rs.getString(5));
			long diff = ChronoUnit.DAYS.between(last_date_of_rent, todaysDate);
			long rentAmtPerDay = rs.getInt(6);
			if(diff > 0) {
				System.out.println("You have exceeded the rent period by "+diff+" days!Hence you need to pay "+(diff * rentAmtPerDay)+" now!");
			}
			String deleteQuery = "delete from toy_rental where toy_id="+tid;
			PreparedStatement stmt1 = conn.prepareStatement(deleteQuery);
			int b = stmt1.executeUpdate();
			if(b>0) {
				return "Thank you for returning the Toy! Hope you have enjoyed playing with the toy!"; 
			}else {
				return "It seems like there was some problem!";
			}
		}else {
			return "No rental records present!";
		}
	}
	
	public ArrayList getRentalDetails(int custid) throws SQLException, ClassNotFoundException{
		Connection conn = DAO.getConnection();
		Statement stmt = conn.createStatement();
		String query = "select c.customer_name, c.city, c.state, c.zip, c.country, tr.rental_start_date, tr.rental_end_date, tr.total_amt, tr.fine, tr.rent_status from customer as c, toy_rental as tr where c.customer_id=tr.customer_id and tr.customer_id="+custid;
		ResultSet rs = stmt.executeQuery(query);
		ArrayList custToyRentalDetails = new ArrayList();
		while(rs.next()) {
			custToyRentalDetails.add(rs.getString(1));
			custToyRentalDetails.add(rs.getString(2));
			custToyRentalDetails.add(rs.getString(3));
			custToyRentalDetails.add(rs.getInt(4)); 
			custToyRentalDetails.add(rs.getString(5));
			custToyRentalDetails.add(rs.getString(6));
			custToyRentalDetails.add(rs.getString(7));
			custToyRentalDetails.add(rs.getInt(8));
			custToyRentalDetails.add(rs.getInt(9));
			custToyRentalDetails.add(rs.getString(10));
		}
		return custToyRentalDetails;
	}
	
	public int getTotalRentAmount(int rent_days, int rent_amt_per_day) {
		int totalAmtForMonth = rent_days * rent_amt_per_day;
		return totalAmtForMonth;
	}
	
	public ArrayList toyRentDetails(int toyid) throws ClassNotFoundException, SQLException {
		Connection conn = DAO.getConnection();
		Statement stmt = conn.createStatement();
		String query = "select t.toy_name, t.toy_type, c.customer_name, c.city, t.quantity, tr.rental_amt_per_day, tr.rental_start_date, tr.rental_end_date, tr.total_amt, tr.rent_status from toy as t, toy_rental as tr, customer as c where t.toy_id=tr.toy_id and c.customer_id=tr.customer_id and tr.toy_id="+toyid;
		ResultSet rs = stmt.executeQuery(query);
		ArrayList toyRentalArray = new ArrayList();
		
		while(rs.next()) {
			toyRentalArray.add(rs.getString(1));
			toyRentalArray.add(rs.getString(2));
			toyRentalArray.add(rs.getString(3));
			toyRentalArray.add(rs.getString(4));
			toyRentalArray.add(rs.getInt(5));
			toyRentalArray.add(rs.getInt(6));
			toyRentalArray.add(rs.getString(7));
			toyRentalArray.add(rs.getString(8));
			toyRentalArray.add(rs.getInt(9));
			toyRentalArray.add(rs.getString(10));
		}
	return toyRentalArray;
	}
}
