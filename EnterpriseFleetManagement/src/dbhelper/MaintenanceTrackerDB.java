package dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class MaintenanceTrackerDB {

	private static Connection conn = null;
	private static Statement stmt = null;
	

	public static HashMap<String, String> queryDB(int pKey) {
		
		HashMap<String, String> result = new HashMap<String, String>();
		
		try {
			
			getSQLConnection();
			
			String sql = "SELECT * FROM maintenance_tracker WHERE maint_id = "+pKey+";";
			ResultSet rs = stmt.executeQuery(sql);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();

			while (rs.next()) {
				
				String name;
				// The column count starts from 1
				for (int i = 1; i <= columnCount; i++ ) {
					name = rsmd.getColumnName(i);
					result.put(name, rs.getString(name));
				}
			}
			
			rs.close();
			closeSQLConnection();
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		
		return result;
	}

	
	
	
	
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
