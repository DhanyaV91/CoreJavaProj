package com.manipal.corejavaproj.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.manipal.corejavaproj.model.Toy;

public class ToyServiceImpl implements ToyService{
	
	List<Toy> toyArray;

	public List<Toy> getToyDetails() throws SQLException, ClassNotFoundException {
		Connection conn = DAO.getConnection();
		Statement stmt = conn.createStatement();
		String query = "select * from toy";
		ResultSet rs = stmt.executeQuery(query);
		toyArray = new ArrayList<Toy>();
		
		while(rs.next()) {
			Toy toy = new Toy();
			toy.setToyId(rs.getInt(1));
			toy.setToyName(rs.getString(2));
			toy.setToyType(rs.getString(3));
			toy.setMinAge(rs.getInt(4));
			toy.setMaxAge(rs.getInt(5));
			toy.setPrice(rs.getInt(6));
			toy.setQuantity(rs.getInt(7));
			toy.setRentalAmount(rs.getInt(8));
			toyArray.add(toy);
		}
	return toyArray;
	}

	public int insertToyDetails(Toy toy) throws ClassNotFoundException, SQLException {
		Connection conn = DAO.getConnection();
		String insertQuery = "insert into toy(toy_name, toy_type, minage, maxage, price, quantity, rental_amount) values(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(insertQuery);
		stmt.setString(1, toy.getToyName());
		stmt.setString(2, toy.getToyType());
		stmt.setInt(3, toy.getMinAge());
		stmt.setInt(4, toy.getMaxAge());
		stmt.setInt(5, toy.getPrice());
		stmt.setInt(6, toy.getQuantity());
		stmt.setInt(7, toy.getRentalAmount());
		int b = stmt.executeUpdate();

		return b;
	}

	public int deleteToyDetails(int toyid) throws ClassNotFoundException, SQLException {
		
		Connection conn = DAO.getConnection();
		String deleteQuery = "delete from toy where toy_id="+toyid;
		PreparedStatement stmt = conn.prepareStatement(deleteQuery);
		int b = stmt.executeUpdate();

		return b;
	}

	public int updateToyDetails(int toyid, Toy toy) throws ClassNotFoundException, SQLException {
		Connection conn = DAO.getConnection();
		String insertQuery = "update toy set ";
		if(!toy.getToyName().isEmpty() && toy.getToyName() != null) {
			insertQuery += "toy_name='"+toy.getToyName()+"', ";
		}
		if(!toy.getToyType().isEmpty() && toy.getToyType() != null) {
			insertQuery += "toy_type='"+toy.getToyType()+"', ";
		}
		if(toy.getMinAge() != 0) {
			insertQuery += "minage='"+toy.getMinAge()+"', ";
		}
		if(toy.getMaxAge() != 0) {
			insertQuery += "maxage='"+toy.getMaxAge()+"', ";
		}
		if(toy.getPrice() != 0) {
			insertQuery += "price="+toy.getPrice()+", ";
		}
		if(toy.getQuantity() != 0) {
			insertQuery += "quantity="+toy.getQuantity()+", ";
		}		
		if(toy.getRentalAmount() != 0){
			insertQuery += "rental_amount='"+toy.getRentalAmount()+"', ";
		}
		String updateQuery = insertQuery.substring(0, insertQuery.length() - 2);
		updateQuery += " where toy_id="+toyid;
		PreparedStatement stmt = conn.prepareStatement(updateQuery);
		int c = stmt.executeUpdate();

		return c;
	}
	
	public List<Toy> search(int toyid) throws SQLException, ClassNotFoundException{
		Connection conn = DAO.getConnection();
		Statement stmt = conn.createStatement();
		String query = "select * from toy where toy_id="+toyid;
		ResultSet rs = stmt.executeQuery(query);
		toyArray = new ArrayList<Toy>();
		while(rs.next()) {
			Toy toy = new Toy();
			  toy.setToyId(rs.getInt(1)); toy.setToyName(rs.getString(2));
			  toy.setToyType(rs.getString(3)); toy.setMinAge(rs.getInt(4));
			  toy.setMaxAge(rs.getInt(5)); toy.setPrice(rs.getInt(6));
			  toy.setQuantity(rs.getInt(7)); toy.setRentalAmount(rs.getInt(8));
			  toyArray.add(toy);
		}
	return toyArray;		
	}

}

class ElectronicToy extends Toy{
	
	private int numberofbatteries;
	private String operatingmode; 
}

class MuscialToy extends Toy {
	private int noOfSpeakers;
}