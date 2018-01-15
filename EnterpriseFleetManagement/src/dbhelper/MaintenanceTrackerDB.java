package dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import fleetmanagement.MaintenanceTracker;

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

	public static ArrayList<HashMap<String, String>> listMaintHistoryForVehicle(int vehId) {
		
		ArrayList<HashMap<String, String>> maintList = new ArrayList<HashMap<String, String>>();
		
		String sql = "SELECT * FROM maintenance_tracker WHERE veh_id = "+vehId+";";
		try {
			
			getSQLConnection();
			
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			while(rs.next()) {
				HashMap<String, String> result = new HashMap<String, String>();
				String name;
				// The column count starts from 1
				for (int i = 1; i <= columnCount; i++ ) {
					name = rsmd.getColumnName(i);
					result.put(name, rs.getString(name));
				}
				maintList.add(result);
			}
			
			rs.close();
			closeSQLConnection();
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		
		return maintList;
		
	}
	
	public static int createMaintenanceEntry(MaintenanceTracker maint) {
		
		int maintId = 0;
		
		try {
			
			getSQLConnection();
			
			String sql = "INSERT INTO maintenance_tracker (veh_id, maint_date, due_date, maint_cost, description, maint_type) "
					+ "VALUES ("+maint.getVehId()+", '"+maint.getMaintDate()+"', '"+maint.getDueDate()+"', "+maint.getMaintCost()+", '"+maint.getDescription()+"', '"+maint.getMaintType()+"');";
			
			stmt.executeUpdate(sql);
			
			String sql2 = "SELECT MAX(maint_id) FROM maintenance_tracker;";
			ResultSet rs = stmt.executeQuery(sql2);
			
			while (rs.next()) {
				maintId = rs.getInt("max(maint_id)");
			}
			
			closeSQLConnection();
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		
		return maintId;
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
