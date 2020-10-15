package com.manipal.corejavaproj.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.manipal.corejavaproj.service.DAO;

public class Admin {
	public Admin() {}

	public int authorise(String admin_name, String admin_pass) throws ClassNotFoundException, SQLException {
		Connection conn = DAO.getConnection();
		Statement stmt = conn.createStatement();
		String query = "select * from admin where admin_name='"+admin_name+"' and password='"+admin_pass+"'";
		ResultSet rs = stmt.executeQuery(query);
		if(rs.next()) {
			return 1;
		}else {
			return 0;
		}
	}
	
	
}
