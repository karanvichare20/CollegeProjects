package fleetmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
	
	public void displayAllAvailableVehicles(){
		
		ArrayList<HashMap<String, String>> vehList = VehicleRecordsDB.listAllAvailableVehiclesDB();
		Iterator<HashMap<String, String>> iterator = vehList.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
	public void displayStatusOfAllVehicles(){
		
		ArrayList<HashMap<String, String>> vehList = VehicleRecordsDB.listStatusOfAllVehiclesDB();
		Iterator<HashMap<String, String>> iterator = vehList.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}