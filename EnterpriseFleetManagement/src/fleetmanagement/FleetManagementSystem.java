package fleetmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FleetManagementSystem {

	public static void main(String[] args) {
		
		//Start of application- Main Menu
		ApplicationLogin login = new ApplicationLogin();
		System.out.print("\n");
		System.out.println("<----------------------------------------------------------------------------------ENTERPRISE FLEET MANAGEMENT SYSTEM---------------------------------------------------------------------------------->\n");
		System.out.println("For Admin login press 'A' \nFor Employee login press 'E'");	
		
		try {
			
			String choice = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
			
			while(true) {
				
				System.out.print("\nPlease enter your choice: ");
				choice = reader.readLine();
				
				if(choice.equalsIgnoreCase("A")) {
					login.adminLogin();
					break;
				}else if(choice.equalsIgnoreCase("E")) {
					login.employeeLogin();
					break;
				}else {
					System.out.println("Invalid entry. Try again");
				}
			}
			
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }

	}

}
