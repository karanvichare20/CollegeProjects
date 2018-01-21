package fleetmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Admin {

	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	
	public void adminMenu() {
		
		try {
			System.out.println("\nWelcome Admin!");
	        System.out.print("\nFor vehicle maintenance press 'V'\nFor employee maintenance press 'E'\nFor trip management press 'T'\nFor overview report press 'O'\nTo logout press 'L'");
	        System.out.print("\n-->Enter selection: ");
	        String select = reader.readLine();
	        while(true) {
	        	if(select.equalsIgnoreCase("V")) {
	        		MaintainVehicle mv = new MaintainVehicle();
	        		mv.maintainVehicle();
	        		break;
	        	}else if(select.equalsIgnoreCase("E")) {
	        		MaintainEmployee me = new MaintainEmployee();
	        		me.maintainEmployee();
	        		break;
	        	}else if(select.equalsIgnoreCase("T")) {
	        		AdminTripManagement atm = new AdminTripManagement();
	        		atm.tripManagement();
	        		break;
	        	}else if(select.equalsIgnoreCase("O")) {
	        		AdminOverview ao = new AdminOverview();
	        		ao.adminOverview();
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
	
	
	
	
/*	public void goBackToMainMenu() {
		
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
	}*/
}
