package fleetmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminTripManagement {
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 

	public void tripManagementMenu() {

		try {
	        
	        System.out.print("To assign a vehicle to employee press A\nTo revoke a vehicle press R\nTo check current location(status) of vehicle press S\nTo get all vehicle overview press O\nTo go back to main menu press M");
	        System.out.print("\nEnter selection: ");
	        String select = reader.readLine();
	        while(true) {
	        	if(select.equalsIgnoreCase("A")) {
	        		assignVehicleToEmployee();
	        		break;
	        	}else if(select.equalsIgnoreCase("R")) {
	        		revokeVehicleFromEmployee();
	        		break;
	        	}else if(select.equalsIgnoreCase("S")) {
	        		checkVehicleStatus();
	        		break;
	        	}else if(select.equalsIgnoreCase("O")) {
	        		getVehicleOverview();
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
	
	public void assignVehicleToEmployee(){
		
		System.out.println("Enter employee id: ");
		try {
			String select = reader.readLine();
	        while(true) {
	        	if(!select.matches("-?\\d+")) {
	        		System.out.print("Invalid entry. Please enter correct 5 digit Employee ID:");
	        		select = reader.readLine();
	        	}else {
	        		Employee emp = new Employee(Integer.parseInt(select));
	        		if(emp.getEmpId() == 0){
	        			System.out.println("There is no record of the employee in the database.");
	        			System.out.println("Enter employee id again: ");
	        			select = reader.readLine();
	        			continue;
	        		}else{
	        			TripManagement trip = new TripManagement();
	        			trip.bookRide(emp);
	        		}
	        		break;
	        	}
	        }
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }

		goBackToPreviousMenu();
	}
	
	public void revokeVehicleFromEmployee() {
		
		System.out.println("Enter employee id: ");
		try {
			String select = reader.readLine();
	        while(true) {
	        	if(!select.matches("-?\\d+")) {
	        		System.out.print("Invalid entry. Please enter correct 5 digit Employee ID:");
	        		select = reader.readLine();
	        	}else {
	        		Employee emp = new Employee(Integer.parseInt(select));
	        		if(emp.getEmpId() == 0){
	        			System.out.println("There is no record of the employee in the database.");
	        			System.out.println("Enter employee id again: ");
	        			select = reader.readLine();
	        			continue;
	        		}else{
	        			TripManagement trip = new TripManagement();
	        			trip.cancelRide(emp);
	        		}
	        		break;
	        	}
	        }
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
		
		goBackToPreviousMenu();
	}
	
	public void checkVehicleStatus() {
		
		System.out.println("Enter vehicle id: ");
		try {
			String select = reader.readLine();
	        while(true) {
	        	if(!select.matches("-?\\d+")) {
	        		System.out.print("Invalid entry. Please enter correct 4 digit Vehicle ID:");
	        		select = reader.readLine();
	        	}else {
	        		VehicleRecords vehRec = new VehicleRecords(Integer.parseInt(select));
	        		if(vehRec.getVehId() == 0){
	        			System.out.println("There is no record of the vehicle in the database.");
	        			System.out.println("Enter vehicle id again: ");
	        			select = reader.readLine();
	        			continue;
	        		}else{
	        			System.out.println("The status of vehicle with ID "+vehRec.getVehId()+" is: "+vehRec.getStatus());
	        			System.out.println("The current location of vehicle with ID "+vehRec.getVehId()+" is: "+(new TripManagement()).getLocation(vehRec.getCurrPin()));
	        		}
	        		break;
	        	}
	        }
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
		
		goBackToPreviousMenu();
	}
	
	public void getVehicleOverview() {
		
		VehicleRecords vehRec = new VehicleRecords();
		
		try {
	        
	        System.out.print("To see all available vehicles press A\nTo see status of all vehicles press S");
	        System.out.print("\nEnter selection: ");
	        String select = reader.readLine();
	        while(true) {
	        	if(select.equalsIgnoreCase("A")) {
	        		vehRec.displayAllAvailableVehicles();
	        		break;
	        	}else if(select.equalsIgnoreCase("S")) {
	        		vehRec.displayStatusOfAllVehicles();
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
		
		goBackToPreviousMenu();
	}
	
	public void goBackToPreviousMenu() {
		
		System.out.println("To go back to previous menu press 'B'\nTo logout press 'L'");
		
		try {
			
			System.out.print("Enter selection:");
			String select = reader.readLine();
			
			while(true) {
				
				if(select.equalsIgnoreCase("B")) {
					tripManagementMenu();
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
