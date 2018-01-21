package fleetmanagement;

import java.io.*;
import dbhelper.EmployeeDB;

public class ApplicationLogin {
	
	private String userId; 		//Variable to store the user ID of the user.
	private String password; 	//Variable to store the password of the user.
	
	/*The method to be called for validating a user login.*/
	
	public void employeeLogin() {
		
		int count = 0;
		
		try {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	        System.out.print("\n");
	        System.out.print("-----------------------------------------EMPLOYEE LOGIN---------------------------------------------->");
	        System.out.print("\nEnter Employee ID: ");
	        userId = reader.readLine();
	        while(true) {
	        	if(!userId.matches("-?\\d+")) {
	        		System.out.print("Invalid entry. Please enter correct 5 digit Employee ID:");
	        		userId = reader.readLine();
	        	}else if(!userId.equals(EmployeeDB.loginQueryDB("emp_id",Integer.parseInt(userId)))) {
	        		System.out.print("Employee ID doesn't exists. Please enter a valid Employee ID:");
	        		userId = reader.readLine();
	        	}else if ((EmployeeDB.loginQueryDB("status",Integer.parseInt(userId))).equals("inactive")) {
	        		System.out.print("Employee no more active. Please enter a valid Employee ID:");
	        		userId = reader.readLine();
	        	}else {
	        		break;
	        	}
	        }
	        
	        System.out.print("Enter Password: ");
	        password = reader.readLine();
	        while(!password.equals(EmployeeDB.loginQueryDB("pass",Integer.parseInt(userId)))) {
	        	count++;
	        	if(count==3) {
	        		System.out.println("Too many invalid password attempts.");
	        		return;
	        	}
	        	System.out.print("Please enter the correct Password:");
	        	password = reader.readLine();
	        }
	        
	        Employee emp = new Employee(Integer.parseInt(userId));
	        emp.employeeMenu();
	        
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
	
	public void adminLogin() {
		
		int count = 0;
		
		try {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	        
	        System.out.print("-----------------------------------------ADMIN LOGIN---------------------------------------------->");
	        System.out.print("\nEnter Admin password: ");
	        password = reader.readLine();
	        while(!password.equals("root")) {
	        	count++;
	        	if(count==3) {
	        		System.out.println("Too many invalid attempts.");
	        		return;
	        	}
	        	System.out.print("Please enter a valid Admin password:");
	        	password = reader.readLine();
	        }
	        
	        Admin admin = new Admin();
	        admin.adminMenu();
	        
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}

}
