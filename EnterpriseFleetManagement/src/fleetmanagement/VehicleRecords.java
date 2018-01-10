package fleetmanagement;

import java.util.HashMap;

import dbhelper.VehicleRecordsDB;

public class VehicleRecords {

	private int vehId;
	private double miles;
	private double maintCost;
	private double fuelCost;
	private String status;
	private String currPin;
	
	
	public VehicleRecords() {
		
	}
	
	public VehicleRecords(int id) {
		
		HashMap<String, String> result = VehicleRecordsDB.queryDB(id);
		
		if(!result.isEmpty()){
			vehId = Integer.parseInt(result.get("veh_id"));
			miles = Double.parseDouble(result.get("miles"));
			maintCost = Double.parseDouble(result.get("maintenance_cost"));
			fuelCost = Double.parseDouble(result.get("fuel_cost"));
			status = result.get("status");
			currPin = result.get("curr_pin");
		}
		
	}

	public int getVehId() {
		return vehId;
	}

	public String getCurrPin() {
		return currPin;
	}

	public double getMiles() {
		return miles;
	}

	public double getMaintCost() {
		return maintCost;
	}

	public double getFuelCost() {
		return fuelCost;
	}

	public String getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return "Distance travelled till date in KMs: "+miles+
				"\nMaintenance cost till date: "+maintCost+
				"\nFuel cost till date: "+fuelCost+
				"\nBookin status: "+status+
				"\nCurrent location pin: "+currPin;
	}
	
}
