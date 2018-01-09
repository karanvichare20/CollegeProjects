package fleetmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import dbhelper.EmployeeDB;

public class Employee {
	
	private int empId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private String street;
	private String city;
	private String pincode;
	private String role;
	private String status;
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	
	public Employee() {
		
	}
	
	public Employee(int id) {
		
		HashMap<String, String> result = EmployeeDB.queryDB(id);
		
		empId = Integer.parseInt(result.get("emp_id"));
		firstName = result.get("fname");
		lastName = result.get("lname");
		email = result.get("email");
		password = result.get("pass");
		phone = result.get("phone");
		street = result.get("street");
		city = result.get("city");
		pincode = result.get("pincode");
		role = result.get("role");
		status = result.get("status");
		
//		System.out.println(empId+"\t"+firstName+"\t"+lastName+"\t"+email+"\t"+password+"\t"
//							+phone+"\t"+street+"\t"+city+"\t"+pincode+"\t"+role+"\t"+status);
		
	}
	
	public int getEmpId() {
		return empId;
	}

	public String getRole() {
		return role;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getPincode() {
		return pincode;
	}

	public String getStatus() {
		return status;
	}

	public void addEmployee() {
		
		try {
			
			System.out.println("Add a new employee");
			
//			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter firstName: ");
			firstName = reader.readLine();
	        
	        System.out.print("Enter lastName: ");
	        lastName = reader.readLine();
	        
	        System.out.print("Enter email: ");
	        email = reader.readLine();
	        
	        System.out.print("Enter password: ");
	        password = reader.readLine();
	        
	        System.out.print("Enter phone number: ");
	        phone = reader.readLine();
	        
	        System.out.print("Enter street address: ");
	        street = reader.readLine();
	        
	        System.out.print("Enter city: ");
	        city = reader.readLine();
	        
	        System.out.print("Enter pincode: ");
	        pincode = reader.readLine();
	        
	        System.out.print("Enter employee designation: ");
	        role = reader.readLine();
	        
	        String sql = "INSERT INTO employee (fname,lname,email,pass,phone,street,city,pincode,role) VALUES('"
					+firstName+"','"+lastName+"','"+email+"','"+password+"','"+phone+"','"+street+"','"+city+"','"+pincode+"','"+role+"');";
	        
	        empId = EmployeeDB.addEmployeeToDB(sql);
	        System.out.println("Employee ID is: "+empId);
	        
		} catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
	
	public void deactivateEmployee(int empId) {
		
		boolean success = EmployeeDB.deactivateEmployeeInDB(empId);
		System.out.println("Employee with ID "+empId+" de-activated successfully: "+success);
		
	}
	
	public void employeeMenu() {
		
		try {
			
			System.out.println("\nWelcome "+firstName+" "+lastName+"!\n");
			System.out.println("To book a ride press 'B'\nTo start a booked trip press 'S'\nTo end your ongoing trip press 'E'\n"
					+ "To cancel an existing booking press 'C'\nTo logout press 'L'");
			System.out.print("Enter your selection: ");
			String select = reader.readLine();
			
			while(true) {
				
				if(select.equalsIgnoreCase("B")) {
					bookRide();
					break;
				}else if (select.equalsIgnoreCase("S")) {
					startRide();
					break;
				}else if (select.equalsIgnoreCase("E")) {
					endRide();
					break;
				}else if (select.equalsIgnoreCase("C")) {
					cancelRide();
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
	
	public void bookRide() {
		
		TripManagement trip = new TripManagement();
		trip.bookRide(this);
		
		goBackToMainMenu();
	}
	
	public void startRide() {
		
		TripManagement trip = new TripManagement();
		trip.startRide(this);
		
		goBackToMainMenu();
	}
	
	public void endRide() {
		
		TripManagement trip = new TripManagement();
		trip.endRide(this);
		
		goBackToMainMenu();
	}
	
	public void cancelRide() {
		
		TripManagement trip = new TripManagement();
		trip.cancelRide(this);
		
		goBackToMainMenu();
	}
	
	public void goBackToMainMenu() {
		
		System.out.println("To go back to main menu press 'M'\nTo logout press 'L'");
		
		try {
			
			System.out.print("Enter selection:");
			String select = reader.readLine();
			
			while(true) {
				
				if(select.equalsIgnoreCase("M")) {
					employeeMenu();
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
