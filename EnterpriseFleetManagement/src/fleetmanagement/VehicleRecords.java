package fleetmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
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
		
		if(!vehList.isEmpty()) {
			OverviewReports.displayAllAvailableVehicles(vehList);
		}else{
			System.out.println("No records");
		}
		
	}
	
	public void displayStatusOfAllVehicles(){
		
		ArrayList<HashMap<String, String>> vehList = VehicleRecordsDB.listStatusOfAllVehiclesDB();
		Iterator<HashMap<String, String>> iterator = vehList.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		if(!vehList.isEmpty()) {
			OverviewReports.displayStatusOfAllVehicles(vehList);
		}else{
			System.out.println("No records");
		}
		
	}
	
	public void displayVehicleRunningStats(){
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> statList = new HashMap<String, String>();
		
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
	        			statList.put("miles", ""+vehRec.getMiles());
	        			System.out.println("Total kilometers run: "+vehRec.getMiles());
	        			
	        			statList.put("fuelcost", ""+vehRec.getFuelCost());
	        			System.out.println("Total fuel cost till date: "+vehRec.getFuelCost());
	        			
	        			statList.put("maintcost", ""+vehRec.getMaintCost());
	        			System.out.println("Total maintenance cost till date: "+vehRec.getMaintCost());
	        			
	        			list.add(statList);
	        			OverviewReports.displayVehicleRunningStats(list, vehRec.getVehId());
	        		}
	        		break;
	        	}
	        }
		}catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
}
