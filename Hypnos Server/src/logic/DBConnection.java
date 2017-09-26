package logic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private DBConnection() {
		
	}
	
	public static Connection getConnection() throws IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IOException(e);
		}
		
		String dbUser = "aly16";
		String dbPass = "monkeybutt";
		String host = "csc-db0.csc.calpoly.edu";
		String dbName = "aly16";	

		String dbUrl = String.format("jdbc:mysql://%s/%s?autoReconnect=true", host, dbName);

		try {
			return DriverManager.getConnection(dbUrl, dbUser, dbPass);
		} catch (SQLException e) {
			throw new IOException(e);
		}

	}

	public static void close(Connection conn) throws SQLException{
		if (conn != null) {
			conn.close();
		}
	}
}
