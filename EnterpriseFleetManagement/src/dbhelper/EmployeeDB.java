package dbhelper;

import java.sql.*;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeDB {

	private String inputFile = "../EnterpriseFleetManagement/employee.txt";
	
	private static Connection conn = null;
	private static Statement stmt = null;
	
	public static void main(String[] args) {
		EmployeeDB pe = new EmployeeDB();
		pe.addFromFile();
	}
	
	public void addFromFile() {
		
		BufferedReader br = null;
		String[] aList = new String[9];
		
		try {
			
			String currentLine;
			br = new BufferedReader(new FileReader(inputFile));
			
			while((currentLine = br.readLine()) != null) {
				
				aList = currentLine.split(",");
				
				String sql = "INSERT INTO employee (fname,lname,email,pass,phone,street,city,pincode,role) VALUES('"
						+aList[0]+"','"+aList[1]+"','"+aList[2]+"','"+aList[3]+"','"+aList[4]+"','"+aList[5]+"','"+aList[6]+"','"+aList[7]+"','"+aList[8]+"');";
				
				System.out.println(currentLine);
				
				int empId = addEmployeeToDB(sql);
				System.out.println("employee id is: "+empId);
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	public static int addEmployeeToDB(String sql) {
		
//		Connection conn = null;
//		Statement stmt = null;
		int empId = 0;
		
		try {
			
/*			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();	*/
			
			getSQLConnection();
			
			stmt.executeUpdate(sql);
			
			String sql2 = "SELECT MAX(emp_id) FROM employee;";
			ResultSet rs = stmt.executeQuery(sql2);
			
			while (rs.next()) {
				empId = rs.getInt("max(emp_id)");
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
		
		return empId;
	}
	
	public static boolean deactivateEmployeeInDB(int empId) {
		
		boolean success = false;
		String sql = "UPDATE employee SET status = 'inactive' WHERE emp_id = " + empId + ";";
		
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
	
	public static boolean reactivateEmployeeInDB(int empId) {
		
		boolean success = false;
		String sql = "UPDATE employee SET status = 'active' WHERE emp_id = " + empId + ";";
		
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
	
	public static String loginQueryDB(String columnName, int pKey) {
		
		String value = null;
		
		try {
			
			getSQLConnection();
			
			String sql = "SELECT "+columnName+" FROM employee WHERE emp_id = "+pKey+";";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				value = rs.getString(columnName);
			}
			
			rs.close();
			closeSQLConnection();
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		
		return value;
	}
	
	public static HashMap<String, String> queryDB(int pKey) {
		
		HashMap<String, String> result = new HashMap<String, String>();
		
		try {
			
			getSQLConnection();
			
			String sql = "SELECT * FROM employee WHERE emp_id = "+pKey+";";
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
