package fleetmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Admin {

	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	
	public void adminMenu() {
		
		try {
	        
	        System.out.print("For vehicle maintenance press V\nFor employee maintenance press E\nTo logout press L");
	        System.out.print("\nEnter selection: ");
	        String select = reader.readLine();
	        while(true) {
	        	if(select.equalsIgnoreCase("V")) {
	        		maintainVehicle();
	        		break;
	        	}else if(select.equalsIgnoreCase("E")) {
	        		maintainEmployee();
	        		break;
	        	}else if (select.equalsIgnoreCase("L")) {
	        		System.out.println("You have been successfully logged out");
	        		break;
	        	}else {
	        		System.out.println("Invalid input. Try again.");
					System.out.print("Enter your selection: ");
					select = reader.readLine();
	        	}
	        }
	     
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
	
	public void maintainVehicle() {

		try {
	        
	        System.out.print("To add a new vehicle press A\nTo retrieve details of a vehicle press R\nTo update a vehicle record press U\nTo decommission a vehicle press D\nTo go back to previous menu press B");
	        System.out.print("\nEnter selection: ");
	        String select = reader.readLine();
	        while(true) {
	        	if(select.equalsIgnoreCase("A")) {
	        		addNewVehicle();
	        		break;
	        	}else if(select.equalsIgnoreCase("R")) {
	        		retrieveVehicle();
	        		break;
	        	}else if (select.equalsIgnoreCase("U")) {
	        		updateVehicle();
	        		break;
	        	}else if (select.equalsIgnoreCase("D")) {
	        		decommissionVehicle();
	        		break;
	        	}else if (select.equalsIgnoreCase("B")) {
	        		adminMenu();
	        		break;
	        	}else {
	        		System.out.println("Invalid input. Try again.");
					System.out.print("Enter your selection: ");
					select = reader.readLine();
	        	}
	        }
	     
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
	
	public void addNewVehicle() {
//		Vehicle veh = new Vehicle();
//		veh.addVehicle();
		System.out.println("ADDED A VEHICLE");
		goBackToMainMenu();
	}
	
	public void retrieveVehicle() {
		
		System.out.println("RETRIEVED A VEHICLE");
		goBackToMainMenu();
	}
	
	public void updateVehicle() {
		
		System.out.println("UPDATED A VEHICLE");
		goBackToMainMenu();
	}
	
	public void decommissionVehicle() {
		
		System.out.println("DECOMMISSIONED A VEHICLE");
		goBackToMainMenu();
	}
	
	public void maintainEmployee() {
		
		try {
	        
	        System.out.print("To add a new employee press A\nTo retrieve details of an employee press R\nTo update an employee record press U\nTo de-activate an employee press D\nTo go back to previous menu press B");
	        System.out.print("\nEnter selection: ");
	        String select = reader.readLine();
	        while(true) {
	        	if(select.equalsIgnoreCase("A")) {
	        		addNewEmployee();
	        		break;
	        	}else if(select.equalsIgnoreCase("R")) {
	        		retrieveEmployee();
	        		break;
	        	}else if (select.equalsIgnoreCase("U")) {
	        		updateEmployee();
	        		break;
	        	}else if (select.equalsIgnoreCase("D")) {
	        		deactivateEmployee();
	        		break;
	        	}else if (select.equalsIgnoreCase("B")) {
	        		adminMenu();
	        		break;
	        	}else {
	        		System.out.println("Invalid input. Try again.");
					System.out.print("Enter your selection: ");
					select = reader.readLine();
	        	}
	        }
	     
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
	
	public void addNewEmployee() {
//		Vehicle veh = new Vehicle();
//		veh.addVehicle();
		System.out.println("ADDED AN EMPLOYEE");
		goBackToMainMenu();
	}
	
	public void retrieveEmployee() {
		
		System.out.println("RETRIEVED AN EMPLOYEE");
		goBackToMainMenu();
	}
	
	public void updateEmployee() {
		
		System.out.println("UPDATED AN EMPLOYEE");
		goBackToMainMenu();
	}
	
	public void deactivateEmployee() {
		
		System.out.println("DEACTIVATED AN EMPLOYEE");
		goBackToMainMenu();
	}
	
	public void goBackToMainMenu() {
		
		System.out.println("To go back to main menu press 'M'\nTo logout press 'L'");
		
		try {
			
			System.out.print("Enter selection:");
			String select = reader.readLine();
			
			while(true) {
				
				if(select.equalsIgnoreCase("M")) {
					adminMenu();
					break;
				}else if (select.equalsIgnoreCase("L")) {
					System.out.println("You have been successfully logged out");
					break;
				}else {
					System.out.println("Invalid input. Try again.");
					System.out.print("Enter your selection: ");
					select = reader.readLine();
				}
			}
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
}
