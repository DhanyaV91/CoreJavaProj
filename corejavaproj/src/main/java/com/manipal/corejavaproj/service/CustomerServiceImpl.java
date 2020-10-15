package com.manipal.corejavaproj.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.manipal.corejavaproj.model.Customer;

public class CustomerServiceImpl implements CustomerService{
	
	List<Customer> customerArray;

	public List<Customer> getCustomers() throws ClassNotFoundException, SQLException{
	
			Connection conn = DAO.getConnection();
			Statement stmt = conn.createStatement();
			String query = "select customer_id, customer_name, city, state, zip, country from customer";
			ResultSet rs = stmt.executeQuery(query);
			customerArray = new ArrayList<Customer>();
			
			while(rs.next()) {
				Customer cust = new Customer();
				cust.setCustId(rs.getInt(1));
				cust.setCustName(rs.getString(2));
				cust.setCity(rs.getString(3));
				cust.setState(rs.getString(4));
				cust.setZip(rs.getInt(5));
				cust.setCountry(rs.getString(6));
				customerArray.add(cust);
			}
		return customerArray;
	}
	
	public int createCustomer(Customer customer) throws ClassNotFoundException, SQLException {
					
			Connection conn = DAO.getConnection();
			String insertQuery = "insert into customer(customer_name, password, city, state, zip, country) values(?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(insertQuery);
			stmt.setString(1, customer.getCustName());
			stmt.setString(2, customer.getPassword());
			stmt.setString(3, customer.getCity());
			stmt.setString(4, customer.getState());
			stmt.setInt(5, customer.getZip());
			stmt.setString(6, customer.getCountry());
			int b = stmt.executeUpdate();

		return b;
	}
	
	public int deleteCustomer(int custid) throws ClassNotFoundException, SQLException {
		
		Connection conn = DAO.getConnection();
		String deleteQuery = "delete from customer where customer_id="+custid;
		PreparedStatement stmt = conn.prepareStatement(deleteQuery);
		int b = stmt.executeUpdate();

		return b;
	}
	
	public int updateCustomer(int custid, Customer customer) throws ClassNotFoundException, SQLException {
		
		Connection conn = DAO.getConnection();
		String insertQuery = "update customer set ";
		if(!customer.getCustName().isEmpty() && customer.getCustName() != null) {
			insertQuery += "customer_name='"+customer.getCustName()+"', ";
		}
		if(!customer.getPassword().isEmpty() && customer.getPassword() != null) {
			insertQuery += "password='"+customer.getCustName()+"', ";
		}
		if(!customer.getCity().isEmpty() && customer.getCity() != null) {
			insertQuery += "city='"+customer.getCity()+"', ";
		}
		if(!customer.getState().isEmpty() && customer.getState() != null) {
			insertQuery += "state='"+customer.getState()+"', ";
		}
		if(customer.getZip() != 0) {
			insertQuery += "zip="+customer.getZip()+", ";
		}
		if(!customer.getCountry().isEmpty() && customer.getCountry() != null){
			insertQuery += "country='"+customer.getCountry()+"', ";
		}
		String updateQuery = insertQuery.substring(0, insertQuery.length() - 2);
		updateQuery += " where customer_id="+custid;
		PreparedStatement stmt = conn.prepareStatement(updateQuery);
		int c = stmt.executeUpdate();

		return c;
	}

	public List<Customer> search(String cname) throws ClassNotFoundException, SQLException {
		Connection conn = DAO.getConnection();
		Statement stmt = conn.createStatement();
		String query = "select customer_id, customer_name, city, state, zip, country from customer where customer_name='"+cname+"'";
		ResultSet rs = stmt.executeQuery(query);
		customerArray = new ArrayList<Customer>();
		
		try {
			while(rs.next()) {
				Customer cust = new Customer();
				cust.setCustId(rs.getInt(1));
				cust.setCustName(rs.getString(2));
				cust.setCity(rs.getString(3));
				cust.setState(rs.getString(4));
				cust.setZip(rs.getInt(5));
				cust.setCountry(rs.getString(6));
				customerArray.add(cust);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return customerArray;
	}
}
