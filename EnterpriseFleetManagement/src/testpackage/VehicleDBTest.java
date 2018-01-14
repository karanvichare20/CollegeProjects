package testpackage;

import java.util.ArrayList;

import dbhelper.VehicleDB;
import dbhelper.VehicleRecordsDB;

public class VehicleDBTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		VehicleDB vehDb = new VehicleDB();
		//vehDb.addFromFile();
		
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		list = VehicleDB.getAvailableVehicles();
//		System.out.println(list);
		
/*		String phrase = "'truck','bus','car','walk','";
		System.out.println(phrase);
		
		phrase = phrase.substring(0, phrase.length()-2);
		System.out.println(phrase);*/
		
//		vehDb.queryDB(1004);
		
		VehicleRecordsDB vehRecDb = new VehicleRecordsDB();
/*		for(int i=0; i<vehRecDb.listAllAvailableVehiclesDB().size(); i++){
			System.out.println(vehRecDb.listAllAvailableVehiclesDB().get(i));
		}*/
		
		for(int i=0; i<vehRecDb.listStatusOfAllVehiclesDB().size(); i++){
			System.out.println(vehRecDb.listStatusOfAllVehiclesDB().get(i));
		}
		
		
	}

}
