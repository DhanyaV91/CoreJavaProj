package com.manipal.corejavaproj.service;

import java.sql.SQLException;
import java.util.List;

import com.manipal.corejavaproj.model.Customer;

public interface CustomerService {
	public List<Customer> getCustomers() throws ClassNotFoundException, SQLException;
	public int createCustomer(Customer customer) throws ClassNotFoundException, SQLException;
	public int deleteCustomer(int custid) throws ClassNotFoundException, SQLException;
	public int updateCustomer(int custid, Customer customer) throws ClassNotFoundException, SQLException;
	public List<Customer> search(String name) throws ClassNotFoundException, SQLException;
}
