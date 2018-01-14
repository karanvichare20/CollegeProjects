package fleetmanagement;

import java.util.HashMap;

import dbhelper.MaintenanceTrackerDB;

public class MaintenanceTracker {
	
	private int maintId;
	private int vehId;
	private String maintDate;
	private String dueDate;
	private double maintCost;
	private String description;
	private String maintType;
	
	
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
	
	
}
