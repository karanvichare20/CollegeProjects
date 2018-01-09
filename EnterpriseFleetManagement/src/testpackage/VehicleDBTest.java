package testpackage;

import java.util.ArrayList;

import dbhelper.VehicleDB;

public class VehicleDBTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		VehicleDB vehDb = new VehicleDB();
		//vehDb.addFromFile();
		
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		list = VehicleDB.getAvailableVehicles();
//		System.out.println(list);
		
		String phrase = "'truck','bus','car','walk','";
		System.out.println(phrase);
		
		phrase = phrase.substring(0, phrase.length()-2);
		System.out.println(phrase);
		
	}

}
