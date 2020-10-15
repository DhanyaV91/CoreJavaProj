package com.manipal.corejavaproj.service;

import java.sql.SQLException;
import java.util.List;

import com.manipal.corejavaproj.model.Customer;
import com.manipal.corejavaproj.model.Toy;
import com.manipal.corejavaproj.model.ToyRental;

public interface ToyRentalService {

	int bookToy(int toyid, int custid, int days) throws SQLException, ClassNotFoundException;

	int returnToy(int tid) throws ClassNotFoundException, SQLException;

	List<Customer> getRentalDetails(int custid) throws SQLException, ClassNotFoundException;

	List<ToyRental> toyRentDetails(int toyid) throws ClassNotFoundException, SQLException;

}
