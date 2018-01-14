package fleetmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import dbhelper.MaintenanceTrackerDB;

public class MaintenanceTracker {
	
	private int maintId;
	private int vehId;
	private String maintDate;
	private String dueDate;
	private double maintCost;
	private String description;
	private String maintType;
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public MaintenanceTracker() {
		
	}

	public MaintenanceTracker(int id) {
		
		HashMap<String, String> result = MaintenanceTrackerDB.queryDB(id);
		
		if(!result.isEmpty()) {
			maintId = Integer.parseInt(result.get("maint_id"));
			vehId = Integer.parseInt(result.get("veh_id"));
			maintDate = result.get("maint_date");
			dueDate = result.get("due_date");
			maintCost = Double.parseDouble(result.get("maint_cost"));
			description = result.get("description");
			maintType = result.get("maint_type");
			
		}
	}

	public int getMaintId() {
		return maintId;
	}

	public int getVehId() {
		return vehId;
	}

	public String getMaintDate() {
		return maintDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public double getMaintCost() {
		return maintCost;
	}

	public String getDescription() {
		return description;
	}

	public String getMaintType() {
		return maintType;
	}
	
	public void displayMaintHistoryForVehicle(){
		
		System.out.println("Enter vehicle id: ");
		try {
			String select = reader.readLine();
	        while(true) {
	        	if(!select.matches("-?\\d+")) {
	        		System.out.print("Invalid entry. Please enter correct 4 digit Vehicle ID:");
	        		select = reader.readLine();
	        	}else {
	        		Vehicle veh = new Vehicle(Integer.parseInt(select));
//	        		VehicleRecords vehRec = new VehicleRecords(Integer.parseInt(select));
	        		if(veh.getVehId() == 0){
	        			System.out.println("There is no record of the vehicle in the database.");
	        			System.out.println("Enter vehicle id again: ");
	        			select = reader.readLine();
	        			continue;
	        		}else{
	        			ArrayList<HashMap<String, String>> maintList = MaintenanceTrackerDB.listMaintHistoryForVehicle(veh.getVehId());
	        			Iterator<HashMap<String, String>> iterator = maintList.iterator();
	        			
	        			while(iterator.hasNext()) {
	        				System.out.println(iterator.next());
	        			}
	        		}
	        		break;
	        	}
	        }
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
}
