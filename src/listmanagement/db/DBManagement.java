package listmanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManagement {
	private static Connection conn = null;

	public static Connection getConn() {
		if (conn == null) {
			try {
				String dbURL = "jdbc:mysql://localhost/ListManagement";
				String dbID = "root";
				String dbPassword = "1234";
				Class.forName("com.mysql.jdbc.Driver");
				setConn(DriverManager.getConnection(dbURL, dbID, dbPassword));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static void setConn(Connection conn) {
		DBManagement.conn = conn;
	}

}
