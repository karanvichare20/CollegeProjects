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
		Vehicle veh = new Vehicle();
		veh.addVehicle();

		goBackToMainMenu();
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
		
		goBackToMainMenu();
	}
	
	public void updateVehicle() {
		
		System.out.println("UPDATED A VEHICLE");
		goBackToMainMenu();
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
		Employee emp = new Employee();
		emp.addEmployee();
		
		goBackToMainMenu();
	}
	
	public void retrieveEmployee() {
		
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
	        			System.out.println(emp);
	        		}
	        		break;
	        	}
	        }
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
		
		goBackToMainMenu();
	}
	
	public void updateEmployee() {
		
		System.out.println("UPDATED AN EMPLOYEE");
		goBackToMainMenu();
	}
	
	public void deactivateEmployee() {
		
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
	        			emp.deactivateEmployee();
	        		}
	        		break;
	        	}
	        }
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
		
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
