package fleetmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaintainEmployee {
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public void maintainEmployee() {
		
		try {
	        
	        System.out.print("To add a new employee press A\nTo retrieve details of an employee press R\nTo update an employee record press U\nTo de-activate an employee press D\nTo go back to main menu press M");
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
	
	public void addNewEmployee() {
		Employee emp = new Employee();
		emp.addEmployee();
		
		goBackToPreviousMenu();
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
		
		goBackToPreviousMenu();
	}
	
	public void updateEmployee() {
		
		System.out.println("UPDATED AN EMPLOYEE");
		goBackToPreviousMenu();
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
		
		goBackToPreviousMenu();
	}
	
	public void goBackToPreviousMenu() {
		
		System.out.println("To go back to previous menu press 'B'\nTo logout press 'L'");
		
		try {
			
			System.out.print("Enter selection:");
			String select = reader.readLine();
			
			while(true) {
				
				if(select.equalsIgnoreCase("B")) {
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
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}

}
