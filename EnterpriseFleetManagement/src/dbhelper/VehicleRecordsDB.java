package dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class VehicleRecordsDB {

	private static Connection conn = null;
	private static Statement stmt = null;
	
	public static void addVehicleRecordToDB(int vehId) {
		
		try {
			
			getSQLConnection();
			
			//Updates the corresponding record in the vehicle_records table in the database.
			String sql = "INSERT INTO vehicle_records (veh_id) VALUES("+vehId+");";
			stmt.executeUpdate(sql);
			
			closeSQLConnection();
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public static HashMap<String, String> queryDB(int pKey) {
		
		HashMap<String, String> result = new HashMap<String, String>();
		
		try {
			
			getSQLConnection();
			
			String sql = "SELECT * FROM vehicle_records WHERE veh_id = "+pKey+";";
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
	
/*	public static void updateStatusInDB(int vehId, String status) {
		
		try {
			
			getSQLConnection();
			
			//Updates the corresponding record in the vehicle_records table in the database.
			String sql = "UPDATE vehicle_records SET status = '"+status+"' WHERE veh_id = "+vehId+";";
			stmt.executeUpdate(sql);
			
			closeSQLConnection();
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}*/
	
	public static void updateColumnValueInDB(int vehId, String columnName , String value) {
		
		String sql = null;
		if(columnName.matches("status|curr_pin")) {
			sql = "UPDATE vehicle_records SET "+columnName+" = '"+value+"' WHERE veh_id = "+vehId+";";
		}else if(columnName.matches("miles|maintenance_cost|fuel_cost")) {
			sql = "UPDATE vehicle_records SET "+columnName+" = "+columnName+" + "+Double.parseDouble(value)+" WHERE veh_id = "+vehId+";";
		}
		
		try {
			
			getSQLConnection();
			
			//Updates the corresponding record in the vehicle_records table in the database.
			stmt.executeUpdate(sql);
			
			closeSQLConnection();
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	public static boolean updateTripEndDetailsInDB(int vehId, String status , String currPin, double dist, double fcost) {
		
		boolean success = false;
		String sql = "UPDATE vehicle_records "
				+ "SET status = '"+status+"', curr_pin = '"+currPin+"', miles = miles + "+dist+", fuel_cost = fuel_cost + "+fcost+
				" WHERE veh_id = "+vehId+";";
		
		try {
			
			getSQLConnection();
			
			//Updates the corresponding record in the vehicle_records table in the database.
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
