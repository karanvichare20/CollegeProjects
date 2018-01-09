package dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import fleetmanagement.TripManagement;

public class TripManagementDB {

	private static Connection conn = null;
	private static Statement stmt = null;
	
	
	public static boolean checkActiveBookingInDB(int empId) {
		
		boolean result = false;
		
		try {
			
			getSQLConnection();
			
			String sql = "SELECT status FROM trip_management WHERE emp_id = "+empId+" AND status IN ('booked','enroute');";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				result = true;
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

	public static int createTripEntry(TripManagement trip) {
		
		int tripId = 0;
		
		try {
			
			getSQLConnection();
			
			String sql = "INSERT INTO trip_management (veh_id, emp_id, pick_pin, drop_pin, book_time) VALUES("
						+trip.getVehId()+","+trip.getEmpId()+",'"+trip.getPickPin()+"','"+trip.getDropPin()+"','"+trip.getBookTime()+"');";
			stmt.executeUpdate(sql);
			
			String sql2 = "SELECT MAX(trip_id) FROM trip_management;";
			ResultSet rs = stmt.executeQuery(sql2);
			
			while (rs.next()) {
				tripId = rs.getInt("max(trip_id)");
			}
			
			closeSQLConnection();
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		
		return tripId;
	}
	
/*	public static int getTripIdFromDB(int empId, String status) {
		
		int tripId = 0;
		
		try {
			
			getSQLConnection();
			
			String sql = "SELECT trip_id FROM trip_management WHERE emp_id = "+empId+" AND status = '"+status+"';";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				tripId = rs.getInt("trip_id");
			}
			
			rs.close();
			closeSQLConnection();
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		
		return tripId;
	}*/
	
	public static HashMap<String, String> getTripRecordFromDB(int empId, String status) {
		
		HashMap<String, String> result = new HashMap<String, String>();
		
		try {
			
			getSQLConnection();
			
			String sql = "SELECT * FROM trip_management WHERE emp_id = "+empId+" AND status = '"+status+"';";
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
	
	public static boolean updateTripStatusInDB(TripManagement trip, String status) {
		
		boolean success = false;
		
		String sql = null;
		
		if(status.equals("enroute")) {
			sql = "UPDATE trip_management "
				+ "SET trip_start = '"+trip.getTripStart()+"', status = 'enroute' "
				+ "WHERE trip_id = "+trip.getTripId()+";";	
			
		}else if(status.equals("ended")) {
			sql = "UPDATE trip_management "
					+ "SET trip_end = '"+trip.getTripEnd()+"', status = 'ended', distance = "+trip.getDistance()+", fuel_used = "+trip.getFuelUsed()+", trip_cost = "+trip.getTripCost()+" WHERE trip_id = "+trip.getTripId()+";";
			
		}else if(status.equals("cancelled")) {
			sql = "UPDATE trip_management "
					+ "SET status = 'cancelled' "
					+ "WHERE trip_id = "+trip.getTripId()+";";
			
		}
		
		try {
			
			getSQLConnection();
			
			int result = stmt.executeUpdate(sql);
			if(result > 0) {
				success = true;
			}
			
			closeSQLConnection();
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		
		return success;
	}
	
/*	public static int getVehIdFromDB(int tripId) {
		
		int vehId = 0;
		
		try {
			
			getSQLConnection();
			
			String sql = "SELECT veh_id FROM trip_management WHERE trip_id = "+tripId+";";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				vehId = rs.getInt("veh_id");
			}
			
			rs.close();
			closeSQLConnection();
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		
		return vehId;
	}*/
	
	public static HashMap<String, String> queryDB(int pKey) {
		
		HashMap<String, String> result = new HashMap<String, String>();
		
		try {
			
			getSQLConnection();
			
			String sql = "SELECT * FROM trip_management WHERE trip_id = "+pKey+";";
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
