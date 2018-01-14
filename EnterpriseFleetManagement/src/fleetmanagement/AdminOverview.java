package fleetmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminOverview {

	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	
	public void adminOverview() {
		
		VehicleRecords vehRec = new VehicleRecords();
		TripManagement trip = new TripManagement();
		MaintenanceTracker maint = new MaintenanceTracker();
		
		try {
			System.out.println("-----Admin Overview Menu-----\n");
	        System.out.print("To see all available vehicles press A\nTo see status of all vehicles press S\nTo see vehicle's running stats press V"
	        		+ "\nTo see a vehicle's trip history press T\nTo see a vehicle's repair and maintenance history press R"
	        		+ "\nTo see an employee's trip records press E\nTo go main menu press M");
	        System.out.print("\nEnter selection: ");
	        String select = reader.readLine();
	        while(true) {
	        	if(select.equalsIgnoreCase("A")) {
	        		vehRec.displayAllAvailableVehicles();
	        		break;
	        	}else if(select.equalsIgnoreCase("S")) {
	        		vehRec.displayStatusOfAllVehicles();
	        		break;
	        	}else if(select.equalsIgnoreCase("V")) {
	        		vehRec.displayVehicleRunningStats();
	        		break;
	        	}else if(select.equalsIgnoreCase("T")) {
	        		trip.displayTripHistoryForVehicle();
	        		break;
	        	}else if(select.equalsIgnoreCase("R")) {
	        		maint.displayMaintHistoryForVehicle();
	        		break;
	        	}else if(select.equalsIgnoreCase("E")) {
	        		trip.displayTripRecordsOfEmployee();
	        		break;
	        	}else if (select.equalsIgnoreCase("M")) {
	        		Admin admin = new Admin();
	        		admin.adminMenu();
	        		return;
	        	}else {
	        		System.out.println("Invalid input. Try again.");
					System.out.print("Enter your selection: ");
					select = reader.readLine();
	        	}
	        }
	     
	    } catch (IOException ioe) {
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
					adminOverview();
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
