package ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;

public class connect extends HttpServlet {
	
	public Connection getConnection(){
		Connection conn = null;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "trung98N@");
			return conn;
		} catch (SQLException e){
			return null;
		}
	}
}
