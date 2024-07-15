package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
	public Connection getConnection(){
		Connection con=null;
		//System.out.println("Connection called");
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PBL","root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
