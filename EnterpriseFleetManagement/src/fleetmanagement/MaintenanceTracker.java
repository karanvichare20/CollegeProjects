package fleetmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import dbhelper.MaintenanceTrackerDB;
import dbhelper.VehicleDB;
import dbhelper.VehicleRecordsDB;

public class MaintenanceTracker {
	
	private int maintId;
	private int vehId;
	private String maintDate;
	private String dueDate;
	private double maintCost;
	private String description;
	private String maintType;
	
	private Calendar calendar = Calendar.getInstance();
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
	
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
	
	public void sendForRepairMaint(Vehicle veh) {
		
		vehId = veh.getVehId();
		maintDate = dateFormat.format(new Date(calendar.getTimeInMillis()));
		
		calcCost();
		writeDescription();
		
		maintType = "repair";
		
		maintId = MaintenanceTrackerDB.createMaintenanceEntry(this);
		if(maintId > 0) {
			boolean success = VehicleDB.repairVehicleInDB(vehId);
			if(success){
				VehicleRecordsDB.updateColumnValueInDB(vehId, "maintenance_cost", Double.toString(maintCost));
				System.out.println("Vehicle with vehicle ID: "+vehId+" has been sent for repair.");
			}else{
				System.out.println("Unable to update vehicle repair/inspection status due to technical issues. Try again later.");
			}
		}else{
			System.out.println("Unable to start repair due to technical issues. Try again later.");
		}
		
	}

	public void sendForInspectionMaint(Vehicle veh) {
		
		vehId = veh.getVehId();
		
		Date date = new Date(calendar.getTimeInMillis());
		maintDate = dateFormat.format(date);
		
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, +6);    
		date = calendar.getTime();
		dueDate = dateFormat.format(date);
		
		calcCost();
		writeDescription();
		
		maintType = "inspection";
		
		maintId = MaintenanceTrackerDB.createMaintenanceEntry(this);
		if(maintId > 0) {
			boolean success = VehicleDB.repairVehicleInDB(vehId);
			if(success){
				VehicleRecordsDB.updateColumnValueInDB(vehId, "maintenance_cost", Double.toString(maintCost));
				System.out.println("Vehicle with vehicle ID: "+vehId+" has been sent for inspection.");
			}else{
				System.out.println("Unable to update vehicle repair/inspection status due to technical issues. Try again later.");
			}
		}else{
			System.out.println("Unable to start inspection due to technical issues. Try again later.");
		}
		
//		System.out.println(maintDate);
//		System.out.println(dueDate);
	}
	
	public void calcCost() {

		try {
			
			System.out.print("Enter total maintenance/repair cost in xx.xx format: ");
			maintCost = Double.parseDouble(reader.readLine());
							
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
	
	public void writeDescription() {

		try {
			
			System.out.print("Enter the detailed description of the maintenance/repair: ");
			description = reader.readLine();
							
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
}
