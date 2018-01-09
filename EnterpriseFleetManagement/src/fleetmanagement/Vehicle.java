package fleetmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import dbhelper.VehicleDB;


public class Vehicle {
	
	private int vehId;
	private String model;
	private String brand;
	private String regNo;
	private int year;
	private int pax;
	private String category;
	private double mileage;
	private String service;

	public Vehicle() {
		
	}
	
	public Vehicle(int id) {
		
		HashMap<String, String> result = VehicleDB.queryDB(id);
		
		vehId = Integer.parseInt(result.get("veh_id"));
		model = result.get("model");
		brand = result.get("brand");
		regNo = result.get("reg_no");
		year = Integer.parseInt(result.get("year"));
		pax = Integer.parseInt(result.get("pax"));
		category = result.get("category");
		mileage = Double.parseDouble(result.get("mileage"));
		service = result.get("service");
		
	}
	
	public String getModel() {
		return model;
	}

	public String getBrand() {
		return brand;
	}

	public String getRegNo() {
		return regNo;
	}

	public int getPax() {
		return pax;
	}

	public String getCategory() {
		return category;
	}

	public double getMileage() {
		return mileage;
	}

	public int getVehId() {
		return vehId;
	}

	public int getYear() {
		return year;
	}

	public String getService() {
		return service;
	}

	public void addVehicle() {
		
		try {
			
			System.out.println("Add a new vehicle");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter vehicle model: ");
	        model = reader.readLine();
	        
	        System.out.print("Enter vehicle brand: ");
	        brand = reader.readLine();
	        
	        System.out.print("Enter vehicle registration number: ");
	        regNo = reader.readLine();
	        
	        System.out.print("Enter vehicle year of manufacture: ");
	        year = Integer.parseInt(reader.readLine());
	        
	        System.out.print("Enter vehicle number of passenger capacity: ");
	        pax = Integer.parseInt(reader.readLine());
	        
	        System.out.print("Enter vehicle category: ");
	        category = reader.readLine();
	        
	        System.out.print("Enter vehicle mileage: ");
	        mileage = Double.parseDouble(reader.readLine());
	        
	        String sql = "INSERT INTO vehicle (model, brand, reg_no, year, pax, category, mileage) VALUES('"
					+model+"','"+brand+"','"+regNo+"',"+year+","+pax+",'"+category+"',"+mileage+");";
	        
	        vehId = VehicleDB.addVehicleToDB(sql);
	        System.out.println("Vehicle ID is: "+vehId);
	        
		} catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
	
	public void decommissionVehicle(int vehId) {
		
		boolean success = VehicleDB.decommVehicleInDB(vehId);
		System.out.println("Vehicle has been de-commissioned successfully :"+success);
		
	}

}
