package ch.creditsuisse.poland.wroclaw.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author sai
 * 
 *         This is a singleton class to create DB Connection
 */
public class DBConnection {

	private static Connection conn;

	// Private Constructor can only be accessed by the singleton class
	// using static factory method
	private DBConnection() {
		try {
			//Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Class.forName("org.hsqldb.jdbcDriver");
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "SA","");
			//conn = DriverManager.getConnection("jdbc:hsqldb:file:data/mydb", "SA","");
			//conn = DriverManager.getConnection("jdbc:hsqldb:mem:database", "SA","");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Static Factory Method
	@SuppressWarnings("static-access")
	public static Connection getConnection() {
		if (conn != null) {
			return conn;
		} else {
			return new DBConnection().conn;
		}
	}
}
