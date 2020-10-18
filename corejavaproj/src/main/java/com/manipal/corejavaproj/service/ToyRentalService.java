package com.manipal.corejavaproj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.manipal.corejavaproj.model.Customer;
import com.manipal.corejavaproj.model.Toy;
import com.manipal.corejavaproj.model.ToyRental;

public interface ToyRentalService {

	int bookToy(int toyid, int custid, int days) throws SQLException, ClassNotFoundException;

	String returnToy(int tid, int cuid) throws ClassNotFoundException, SQLException;

	ArrayList getRentalDetails(int custid) throws SQLException, ClassNotFoundException;

	ArrayList toyRentDetails(int toyid) throws ClassNotFoundException, SQLException;

}
