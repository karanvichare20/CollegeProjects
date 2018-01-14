package fleetmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaintainVehicle {
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 

	public void maintainVehicle() {

		try {
	        
	        System.out.print("To add a new vehicle press A\nTo retrieve details of a vehicle press R\nTo update a vehicle record press U\nTo decommission a vehicle press D\nTo go back to main menu press M");
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
	        	}else if (select.equalsIgnoreCase("M")) {
	        		Admin admin = new Admin();
	        		admin.adminMenu();
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
		Vehicle veh = new Vehicle();
		veh.addVehicle();

		goBackToPreviousMenu();
	}
	
	public void retrieveVehicle() {
		
		System.out.println("Enter vehicle id: ");
		try {
			String select = reader.readLine();
	        while(true) {
	        	if(!select.matches("-?\\d+")) {
	        		System.out.print("Invalid entry. Please enter correct 4 digit Vehicle ID:");
	        		select = reader.readLine();
	        	}else {
	        		Vehicle veh = new Vehicle(Integer.parseInt(select));
	        		VehicleRecords vehRec = new VehicleRecords(Integer.parseInt(select));
	        		if(veh.getVehId() == 0){
	        			System.out.println("There is no record of the vehicle in the database.");
	        			System.out.println("Enter vehicle id again: ");
	        			select = reader.readLine();
	        			continue;
	        		}else{
	        			System.out.println(veh);
	        			System.out.println(vehRec);
	        		}
	        		break;
	        	}
	        }
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
		
		goBackToPreviousMenu();
	}
	
	public void updateVehicle() {
		
		System.out.println("UPDATED A VEHICLE");
		goBackToPreviousMenu();
	}
	
	public void decommissionVehicle() {
		
		System.out.println("Enter vehicle id: ");
		try {
			String select = reader.readLine();
	        while(true) {
	        	if(!select.matches("-?\\d+")) {
	        		System.out.print("Invalid entry. Please enter correct 4 digit Vehicle ID:");
	        		select = reader.readLine();
	        	}else {
	        		Vehicle veh = new Vehicle(Integer.parseInt(select));
	        		if(veh.getVehId() == 0){
	        			System.out.println("There is no record of the vehicle in the database.");
	        			System.out.println("Enter vehicle id again: ");
	        			select = reader.readLine();
	        			continue;
	        		}else{
	        			veh.decommissionVehicle();
	        		}
	        		break;
	        	}
	        }
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
		
		goBackToPreviousMenu();
	}
	
	public void goBackToPreviousMenu() {
		
		System.out.println("To go back to previous menu press 'B'\nTo logout press 'L'");
		
		try {
			
			System.out.print("Enter selection:");
			String select = reader.readLine();
			
			while(true) {
				
				if(select.equalsIgnoreCase("B")) {
					maintainVehicle();
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
