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
	        		MaintainVehicle mv = new MaintainVehicle();
	        		mv.maintainVehicle();
	        		break;
	        	}else if(select.equalsIgnoreCase("E")) {
	        		MaintainEmployee me = new MaintainEmployee();
	        		me.maintainEmployee();
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
	
}
