package fleetmanagement;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import dbhelper.TripManagementDB;
import dbhelper.VehicleDB;
import dbhelper.VehicleRecordsDB;

public class TripManagement {

	private int tripId;
	private int vehId;
	private int empId;
	private String pickPin;
	private String dropPin;
	private double distance;
	private double fuelUsed;
	private double tripCost;
	private String bookTime;
	private String tripStart;
	private String tripEnd;
	private String status;
	
	private Calendar calendar = Calendar.getInstance();
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
	private DecimalFormat decForm = new DecimalFormat(".##");
	
	private HashMap<String, String> locList;
	
	private String locFile = "../EnterpriseFleetManagement/locations.txt";
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public TripManagement() {
		setLocList();
	}
	
	public TripManagement(int id) {
		this();
		
		HashMap<String, String> result = TripManagementDB.queryDB(id);
		
		if(!result.isEmpty()){
			tripId = Integer.parseInt(result.get("trip_id"));
			vehId = Integer.parseInt(result.get("veh_id"));
			empId = Integer.parseInt(result.get("emp_id"));
			pickPin = result.get("pick_pin");
			dropPin = result.get("drop_pin");
			distance = Double.parseDouble(result.get("distance"));
			fuelUsed = Double.parseDouble(result.get("fuel_used"));
			tripCost = Double.parseDouble(result.get("trip_cost"));
			bookTime = result.get("book_time");
			tripStart = result.get("trip_start");
			tripEnd = result.get("trip_end");
			status = result.get("status");
		}
		
	}
	
	public int getTripId() {
		return tripId;
	}
	
	public int getVehId() {
		return vehId;
	}

	public int getEmpId() {
		return empId;
	}

	public String getPickPin() {
		return pickPin;
	}

	public String getDropPin() {
		return dropPin;
	}
	
	public String getTripStart() {
		return tripStart;
	}

	public String getTripEnd() {
		return tripEnd;
	}

	public String getStatus() {
		return status;
	}
	
	public double getDistance() {
		return distance;
	}

	public double getFuelUsed() {
		return fuelUsed;
	}

	public double getTripCost() {
		return tripCost;
	}

	public String getBookTime() {
		return bookTime;
	}

	public void setLocList() {
		
		BufferedReader br = null;
		locList = new HashMap<String, String>();
		String[] temp = new String[2];
		
		try {
			
			String currentLine;
			br = new BufferedReader(new FileReader(locFile));
			
			while((currentLine = br.readLine()) != null) {
				temp = currentLine.split(";");
				locList.put(temp[1].trim(), temp[0]);
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	public HashMap<String, String> getLocList() {
		
		return locList;
		
	}
	
	@Override
	public String toString() {
		return "Trip ID: "+tripId+
				"\nVehicle ID: "+vehId+
				"\nEmployee ID: "+empId+
				"\nPickup location pincode: "+pickPin+
				"\nDrop location pincode: "+dropPin+
				"\nTrip distance in KMs: "+distance+
				"\nFuel used in ltr: "+fuelUsed+
				"\nTrip cost: "+tripCost+
				"\nBooking time: "+bookTime+
				"\nTrip start time: "+tripStart+
				"\nTrip end time: "+tripEnd+
				"\nTrip current status: "+status;
	}
	
	public void displayLoc() {
		for(Map.Entry<String, String> entry : locList.entrySet()) {
			String key = entry.getKey();
		    String value = entry.getValue();
		    System.out.println(value+"-"+key);
		}
	}
	
	public void bookRide(Employee emp) {
		
		empId = emp.getEmpId();
		if(TripManagementDB.checkActiveBookingInDB(empId)) {
			System.out.println("You already have an existing active booking or an ongoing trip. Cancel or Complete it to book a new trip.");
			return;
		}
		
		System.out.println("\nChoose the pincode of PICK-UP location from below list: ");
		displayLoc();
		pickPin = inputLocation();
		System.out.println("Your selected pick-up location is: "+locList.get(pickPin));
		
		System.out.println("\nChoose the pincode of DROP location from below list: ");
		displayLoc();
		dropPin = inputLocation();
		while(dropPin.equals(pickPin)) {
			System.out.println("\nYour drop location is same as the pick up location, choose another drop location.");
			dropPin = inputLocation();
		}
		System.out.println("Your selected drop location is: "+locList.get(dropPin));
		
		vehId = searchVehicle(emp);
		if(vehId == 0) {
			return;
		}
		
		Vehicle assignedVehicle = new Vehicle(vehId);
		
		try {
			System.out.println("\nAvailable vehicle is: "+assignedVehicle.getBrand()+" "+assignedVehicle.getModel());
			//System.out.print(assignedVehicle.getBrand()+" "+assignedVehicle.getModel());
			
			System.out.println("\nTo confirm press 'Y'\nTo go back press 'B'");
			System.out.print("Enter selection: ");
			String confirm = reader.readLine();
			
			while(true) {
				if(confirm.equalsIgnoreCase("Y")) {
					
					//Create entry in Trip Management table;
					bookTime = dateFormat.format(new Date(calendar.getTimeInMillis()));
					tripId = TripManagementDB.createTripEntry(this);
					
					//Update Vehicle records table.
//					VehicleRecordsDB.updateStatusInDB(vehId, "booked");
					VehicleRecordsDB.updateColumnValueInDB(vehId, "status", "unavailable");
					
					//Show vehicle details to Employee along with the booking ID.
//					Vehicle assignedVehicle = new Vehicle(vehId);
					System.out.println("\nYour trip has been booked successfully at: "+bookTime+"\tYour booking ID is: "+tripId);
					printAssignedVehicleDetails(assignedVehicle);
					
					break;
					
				}else if (confirm.equalsIgnoreCase("B")) {
					return;
				}else {
					System.out.println("Invalid input. Try again.");
					System.out.print("Enter your selection: ");
					confirm = reader.readLine();
				}
			}	
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	
	}

	public String inputLocation() {

		String pincode=null;
		try {
			
			System.out.print("-->Enter selection:");
			pincode = reader.readLine();
			while(!pincode.matches("-?\\d+")||!locList.containsKey(pincode)) {
				System.out.println("Invalid entry, enter again:");
				pincode = reader.readLine();
			}
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return pincode;
	}
	
	public int searchVehicle(Employee emp) {
		
		int selectedVehId = 0;
		String role = emp.getRole();
		
		ArrayList<VehicleRecords> vehRecList = new ArrayList<VehicleRecords>();
		
		ArrayList<String> category = getCategory(role);
		ArrayList<Integer> availableVehId = VehicleDB.getAvailableVehicles(category);
		
		if(!availableVehId.isEmpty()) {
			for(int id : availableVehId) {
				VehicleRecords vr = new VehicleRecords(id);
				if(vr.getCurrPin().equals(pickPin)) {
					vehRecList.add(vr);
				}
			}	
			
			if(!vehRecList.isEmpty()) {
				selectedVehId = vehRecList.get(0).getVehId();
			}else {
				System.out.println("\nCurrently no vehicles are available nearby your pick-up location."
						+ " We are assigning you a vehicle from a different location. This vehicle may take some time to reach you.");
				selectedVehId = availableVehId.get(0);
			}
		
		}else {
			System.out.println("Currently no vehicles are available. Please try again later.");
		}
		
		return selectedVehId;
		
	}
	
	public ArrayList<String> getCategory(String role) {
		
		ArrayList<String> category = new ArrayList<String>();
		
		if(role.equals("executive")) {
			category.add("luxury");
		}else if(role.equals("manager")) {
			category.add("luxury");
			category.add("comfort");
		}else if(role.equals("lead")) {
			category.add("comfort");
		}else if(role.equals("senior")) {
			category.add("comfort");
			category.add("shared");
		}else if(role.equals("junior")) {
			category.add("shared");
			category.add("bus");
		}else if(role.equals("external")) {
			category.add("bus");
		}else if(role.equals("staff")) {
			String input = getStaffInput();
			if(input.equalsIgnoreCase("B")) {
				category.add("bus");
			}else if(input.equalsIgnoreCase("T")){
				category.add("truck");
			}	
		}
		
		return category;
	}
	
	public String getStaffInput() {
		
		String entry = "T";
		
		System.out.println("For individual travel press 'B'\nFor goods transport press 'T'");
		try {
			System.out.print("Enter selection:");
			entry = reader.readLine();
			while(!entry.matches("B|T|b|t")) {
				System.out.println("Invalid entry, enter again:");
				entry = reader.readLine();
			}
			
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return entry;
		
	}
	
	public void printAssignedVehicleDetails(Vehicle veh) {
		
		System.out.print("\n-->Details of the vehicle assigned to you: ");
		System.out.println("Brand: "+veh.getBrand());
		System.out.println("Model: "+veh.getModel());
		System.out.println("Registration Number: "+veh.getRegNo());
		System.out.println("Category: "+veh.getCategory());
		if(veh.getCategory().equals("truck")) {
			System.out.println("Capacity in tonnes: "+veh.getPax());
		}else {
			System.out.println("Capacity in persons: "+veh.getPax());
		}

	}
	
	public void startRide(Employee emp) {
		
		HashMap<String, String> record = TripManagementDB.getTripRecordFromDB(emp.getEmpId(), "booked");
		
		if(record.isEmpty()) {
			System.out.println("\nThere are no vehicles booked in your name or your last trip hasn't ended yet. Please end your ongoing trip and/or book a new trip.");
			return;
		}
		tripId = Integer.parseInt(record.get("trip_id"));
		
/*		tripId = TripManagementDB.getTripIdFromDB(emp.getEmpId(), "booked");
		
		if(tripId == 0) {
			System.out.println("There are no vehicles booked in your name.");
			return;
		}*/
		
		vehId = Integer.parseInt(record.get("veh_id"));
		pickPin = record.get("pick_pin");
		
		System.out.println("\nYou have an active booking with booking ID: "+tripId+".\nTo start this trip press 'S' \nTo go back to previous menu press 'B'");
		
		try {
			
			System.out.print("Enter selection:");
			String select = reader.readLine();
			
			while(true) {
				
				if(select.equalsIgnoreCase("S")) {
					
					tripStart = dateFormat.format(new Date(calendar.getTimeInMillis()));
					
					boolean success = TripManagementDB.updateTripStatusInDB(this, "enroute");
					
					if(success) {
						VehicleRecordsDB.updateColumnValueInDB(vehId, "curr_pin", pickPin);
						System.out.println("\nyour trip has been started.");
					}else {
						System.out.println("\nyour trip could not be started due to technical error. Try again later.");
					}
					
					break;
					
				}else if (select.equalsIgnoreCase("B")) {
					return;
				}else {
					System.out.println("\nInvalid input. Try again.");
					System.out.print("Enter your selection: ");
					select = reader.readLine();
				}
			}
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
		
	}
	
	public void endRide(Employee emp) {
		
		HashMap<String, String> record = TripManagementDB.getTripRecordFromDB(emp.getEmpId(), "enroute");
		
		if(record.isEmpty()) {
			System.out.println("You have no ongoing rides in your name.");
			return;
		}
		tripId = Integer.parseInt(record.get("trip_id"));
		
		
/*		tripId = TripManagementDB.getTripIdFromDB(emp.getEmpId(), "enroute");
		
		if(tripId == 0) {
			System.out.println("You have no ongoing rides in your name.");
			return;
		}*/
		
		vehId = Integer.parseInt(record.get("veh_id"));
		dropPin = record.get("drop_pin");
				
		System.out.println("Your trip with booking ID: "+tripId+" is ongoing.\nTo end this trip press'E' \nTo go back to previous menu press 'B'");
		
		try {
			
			System.out.print("Enter selection:");
			String select = reader.readLine();
			
			while(true) {
				
				if(select.equalsIgnoreCase("E")) {
					
					//update trip_management table -> trip_start, status(ended) using tripId.
					//update vehicle_records -> status, curr_pin.
					
					tripEnd = dateFormat.format(new Date(calendar.getTimeInMillis()));
					calcTripParameters();
					
					boolean tSuccess = TripManagementDB.updateTripStatusInDB(this, "ended");
					if(tSuccess) {
						
						boolean vSuccess = VehicleRecordsDB.updateTripEndDetailsInDB(vehId, "available", dropPin, distance, tripCost);
						if(vSuccess) {
							System.out.println("your trip has been ended.");
							System.out.println("your trip cost is: EUR "+tripCost);
						}else {
							System.out.println("your trip could not be ended due to technical error with vehicle data. Try again later.");
						}
						
/*						VehicleRecordsDB.updateColumnValueInDB(vehId, "status", "available");
						VehicleRecordsDB.updateColumnValueInDB(vehId, "curr_pin", dropPin);
						VehicleRecordsDB.updateColumnValueInDB(vehId, "miles", Double.toString(distance));
						VehicleRecordsDB.updateColumnValueInDB(vehId, "fuel_cost", Double.toString(tripCost));
						*/

					}else {
						System.out.println("your trip could not be ended due to technical error with trip data. Try again later.");
					}

					break;
					
				}else if (select.equalsIgnoreCase("B")) {
					return;
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
	
	public void calcTripParameters() {
		
		try {
			
			System.out.print("\nEnter distance travelled in kilometers xxx.xx format: ");
			distance = Double.parseDouble(reader.readLine());
			
			System.out.print("\nEnter fuel price for the day in xx.xx price/ltr format: ");
			double fuelPrice = Double.parseDouble(reader.readLine());
			
			Vehicle veh = new Vehicle(vehId);
			double mileage = veh.getMileage();
			
			fuelUsed = Double.parseDouble(decForm.format(distance/mileage));
			
			tripCost = Double.parseDouble(decForm.format(fuelPrice*fuelUsed));
			
/*			fuelUsed = distance/mileage;
			
			tripCost = fuelPrice*fuelUsed;*/
			
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
	
	public void cancelRide(Employee emp) {
		
		HashMap<String, String> record = TripManagementDB.getTripRecordFromDB(emp.getEmpId(), "booked");
		
		if(record.isEmpty()) {
			System.out.println("There are no vehicles booked in your name or you have an ongoing trip.\nP.N: Ongoing trip cannot be cancelled. Please end the trip");
			return;
		}
		tripId = Integer.parseInt(record.get("trip_id"));
		
/*		tripId = TripManagementDB.getTripIdFromDB(emp.getEmpId(), "booked");
		
		if(tripId == 0) {
			System.out.println("There are no vehicles booked in your name.");
			return;
		}*/
		
		vehId = Integer.parseInt(record.get("veh_id"));
		
		System.out.println("You have an active booking with booking ID: "+tripId+".\nTo cancel this trip press 'C' \tTo go back to previous menu press 'B'");
		
		try {
			
			System.out.print("Enter selection:");
			String select = reader.readLine();
			
			while(true) {
				
				if(select.equalsIgnoreCase("C")) {
					
//					tripStart = dateFormat.format(new Date(calendar.getTimeInMillis()));
					
					boolean success = TripManagementDB.updateTripStatusInDB(this, "cancelled");
					if(success) {
						VehicleRecordsDB.updateColumnValueInDB(vehId, "status", "available");
						System.out.println("your trip has been cancelled.");
					}else {
						System.out.println("your trip could not be cancelled due to technical error. Try again later.");
					}
					
					break;
					
				}else if (select.equalsIgnoreCase("B")) {
					return;
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
