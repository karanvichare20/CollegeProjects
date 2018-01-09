package dbhelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VehicleDB {
	
	private String inputFile = "../EnterpriseFleetManagement/vehicle.txt";
	
	private static Connection conn = null;
	private static Statement stmt = null;
	
	public static void main(String[] args) {
		VehicleDB pv = new VehicleDB();
		pv.addFromFile();
	}
	
	public void addFromFile() {
		
		BufferedReader br = null;
		String[] aList = new String[7];
		
		try {
			
			String currentLine;
			br = new BufferedReader(new FileReader(inputFile));
			
			while((currentLine = br.readLine()) != null) {
				
				aList = currentLine.split(",");
						
				String sql = "INSERT INTO vehicle (model, brand, reg_no, year, pax, category, mileage) VALUES('"
						+aList[0]+"','"+aList[1]+"','"+aList[2]+"',"+Integer.parseInt(aList[3])+","+Integer.parseInt(aList[4])+",'"+aList[5]+"',"+Double.parseDouble(aList[6])+");";
				
				System.out.println(currentLine);

				int vehId = addVehicleToDB(sql);
				System.out.println("vehicle id is: "+vehId);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int addVehicleToDB(String sql) {
		
//		Connection conn = null;
//		Statement stmt = null;
		int vehId = 0;
		
		try {
			
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			stmt = conn.createStatement();
			
			getSQLConnection();
			
			stmt.executeUpdate(sql);
			
			String sql2 = "SELECT MAX(veh_id) FROM vehicle;";
			ResultSet rs = stmt.executeQuery(sql2);
			
			while (rs.next()) {
				vehId = rs.getInt("max(veh_id)");
				VehicleRecordsDB.addVehicleRecordToDB(vehId);
			}
			
			rs.close();
			
			closeSQLConnection();
//			stmt.close();
//			conn.close();
			
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		
		return vehId;
	}
	
	public static boolean decommVehicleInDB(int vehId) {
		
		boolean success = false;
		String sql = "UPDATE vehicle SET service = 'decommissioned' WHERE veh_id = " + vehId + ";";
//		Connection conn = null;
//		Statement stmt = null;
		
		try {
			
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			stmt = conn.createStatement();
			
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
	
	public static ArrayList<Integer> getAvailableVehicles(ArrayList<String> category) {
		
		ArrayList<Integer> vehIdList = new ArrayList<Integer>();
		
		String phrase = "'";
		for(String cat : category) {
			phrase = phrase + cat + "','";
		}
		phrase = phrase.substring(0, phrase.length()-2);
		
		String sql = "SELECT v.veh_id AS veh_id "
					+ "FROM vehicle AS v NATURAL JOIN vehicle_records AS vr "
					+ "WHERE v.service = 'active' AND vr.status = 'available'"
					+ "AND v.category IN (" + phrase + ");";
		try {
			
			getSQLConnection();
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				vehIdList.add(rs.getInt("veh_id"));
			}
			
			rs.close();
			closeSQLConnection();
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		
		return vehIdList;
	}
	
	public static HashMap<String, String> queryDB(int pKey) {
		
		HashMap<String, String> result = new HashMap<String, String>();
		
		try {
			
			getSQLConnection();
			
			String sql = "SELECT * FROM vehicle WHERE veh_id = "+pKey+";";
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
