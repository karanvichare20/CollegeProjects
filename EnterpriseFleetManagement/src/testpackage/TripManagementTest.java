package testpackage;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import dbhelper.TripManagementDB;
import fleetmanagement.Employee;
import fleetmanagement.TripManagement;

public class TripManagementTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
//		TripManagement trip = new TripManagement(11000030);
		TripManagement trip = new TripManagement();
/*		HashMap<String, String> locList = trip.getLocList();
		for(Map.Entry<String, String> entry : locList.entrySet()) {
			String key = entry.getKey();
		    String value = entry.getValue();
		    System.out.println(key+"-"+value);
		}*/
		
		trip.displayLoc();
		
//		trip.bookRide();
//		trip.prntTM();
		
		
/*		Calendar calendar = Calendar.getInstance();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		String tripStart = dateFormat.format(new Date(calendar.getTimeInMillis()));
		
		System.out.println(tripStart);*/
		
//		HashMap<String, String> result = new HashMap<String, String>();
	//	System.out.println(result.get("trip_id"));
//		if(result.isEmpty()) {
//			System.out.println("is empty");
//		}
		
//		HashMap<String, String> result;
		
/*		result = TripManagementDB.getTripRecordFromDB(11009, "booked");
		System.out.println(result);
		
		result = TripManagementDB.getTripRecordFromDB(11007, "enroute");
		System.out.println(result);
		
		
		result = TripManagementDB.getTripRecordFromDB(11009, "ended");
		System.out.println(result);
		
		result = TripManagementDB.getTripRecordFromDB(11009, "cancelled");
		System.out.println(result);
		*/
		
     }
	
}

