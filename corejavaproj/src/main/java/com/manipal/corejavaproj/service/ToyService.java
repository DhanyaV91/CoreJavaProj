package com.manipal.corejavaproj.service;

import java.sql.SQLException;
import java.util.List;
import com.manipal.corejavaproj.model.Toy;

public interface ToyService {
	public List<Toy> getToyDetails() throws SQLException, ClassNotFoundException;
	public int insertToyDetails(Toy toy) throws ClassNotFoundException, SQLException;
	public int deleteToyDetails(int toyid) throws ClassNotFoundException, SQLException;
	public int updateToyDetails(int toyid, Toy toy) throws ClassNotFoundException, SQLException;
	List<Toy> search(int toyid) throws SQLException, ClassNotFoundException;
}
