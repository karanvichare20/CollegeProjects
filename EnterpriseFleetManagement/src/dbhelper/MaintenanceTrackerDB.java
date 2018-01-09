package dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MaintenanceTrackerDB {

	private static Connection conn = null;
	private static Statement stmt = null;
	
	

	
	
	
	
	public static void getSQLConnection() throws SQLException, ClassNotFoundException {
		
		Class.forName(DatabaseContants.JDBC_DRIVER);
		conn = DriverManager.getConnection(DatabaseContants.DB_URL,DatabaseContants.USER,DatabaseContants.PASS);
		stmt = conn.createStatement();
	}
	
	public static void closeSQLConnection() throws SQLException {
		
		stmt.close();
		conn.close();
	}
}
