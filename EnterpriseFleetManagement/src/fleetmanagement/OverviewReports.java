package fleetmanagement;

import java.util.ArrayList;
import java.util.HashMap;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

public class OverviewReports {
	
	public static void displayAllAvailableVehicles(ArrayList<HashMap<String, String>> list) {

		JasperReportBuilder report = DynamicReports.report();//a new report
		report
		.columns(
				Columns.column("Vehicle ID", "veh_id", DataTypes.stringType()),
				Columns.column("Model", "model", DataTypes.stringType()),
				Columns.column("Brand", "brand", DataTypes.stringType()),
				Columns.column("Reg No.", "reg_no", DataTypes.stringType()),
				Columns.column("Capacity", "pax", DataTypes.stringType()),
				Columns.column("Category", "category", DataTypes.stringType()),
				Columns.column("Current Location Pin", "curr_pin", DataTypes.stringType())
				)
		.title(//title of the report
				Components.text("List of available vehicles.")
				.setHorizontalAlignment(HorizontalAlignment.CENTER))
		.pageFooter(Components.pageXofY())//show page number on the page footer
		.setDataSource(list);

		try {
			//show the report
			report.show(false);

			//export the report to a pdf file
			//report.toPdf(new FileOutputStream("c:/report.pdf"));
		} catch (DRException dre) {
			dre.printStackTrace();
		} /*catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	
	public static void displayStatusOfAllVehicles(ArrayList<HashMap<String, String>> list) {

		JasperReportBuilder report = DynamicReports.report();//a new report
		report
		.columns(
				Columns.column("Vehicle ID", "veh_id", DataTypes.stringType()),
				Columns.column("Model", "model", DataTypes.stringType()),
				Columns.column("Brand", "brand", DataTypes.stringType()),
				Columns.column("Reg No.", "reg_no", DataTypes.stringType()),
				Columns.column("Capacity", "pax", DataTypes.stringType()),
				Columns.column("Category", "category", DataTypes.stringType()),
				Columns.column("Service status", "service", DataTypes.stringType()),
				Columns.column("Booking status", "status", DataTypes.stringType()),
				Columns.column("Current Location Pin", "curr_pin", DataTypes.stringType())
				)
		.title(//title of the report
				Components.text("Current status of all vehicles.")
				.setHorizontalAlignment(HorizontalAlignment.CENTER))
		.pageFooter(Components.pageXofY())//show page number on the page footer
		.setDataSource(list);

		try {
			//show the report
			report.show(false);

			//export the report to a pdf file
			//report.toPdf(new FileOutputStream("c:/report.pdf"));
		} catch (DRException dre) {
			dre.printStackTrace();
		} /*catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	
	public static void displayVehicleRunningStats(ArrayList<HashMap<String, String>> list, int id) {

		JasperReportBuilder report = DynamicReports.report();//a new report
		report
		.columns(
				Columns.column("Total distance run (in KMs)", "miles", DataTypes.stringType()),
				Columns.column("Total fuel cost (in EUR)", "fuelcost", DataTypes.stringType()),
				Columns.column("Total maintenance cost (in EUR)", "maintcost", DataTypes.stringType())
				)
		.title(//title of the report
				Components.text("Running stats of vehicle with ID: "+id)
				.setHorizontalAlignment(HorizontalAlignment.CENTER))
		.pageFooter(Components.pageXofY())//show page number on the page footer
		.setDataSource(list);

		try {
			//show the report
			report.show(false);

			//export the report to a pdf file
			//report.toPdf(new FileOutputStream("c:/report.pdf"));
		} catch (DRException de) {
			de.printStackTrace();
		} /*catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	
	public static void displayTripHistoryForVehicle(ArrayList<HashMap<String, String>> list, int id) {

		JasperReportBuilder report = DynamicReports.report();//a new report
		report
		.columns(
				Columns.column("Booking ID", "trip_id", DataTypes.stringType()),
//				Columns.column("Vehicle ID", "veh_id", DataTypes.stringType()),
				Columns.column("Employee ID", "emp_id", DataTypes.stringType()),
				Columns.column("pick-up location pin", "pick_pin", DataTypes.stringType()),
				Columns.column("Drop-ff location pin", "drop_pin", DataTypes.stringType()),
				Columns.column("Distance (in KMs)", "distance", DataTypes.stringType()),
				Columns.column("Fuel used (in Ltr)", "fuel_used", DataTypes.stringType()),
				Columns.column("Trip cost (in EUR)", "trip_cost", DataTypes.stringType()),
				Columns.column("Trip booking time", "book_time", DataTypes.stringType()),
				Columns.column("Trip start time", "trip_start", DataTypes.stringType()),
				Columns.column("Trip end time", "trip_end", DataTypes.stringType()),
				Columns.column("Trip current status", "status", DataTypes.stringType())
				)
		.title(//title of the report
				Components.text("Trip history for vehicle with ID: "+id)
				.setHorizontalAlignment(HorizontalAlignment.CENTER))
		.pageFooter(Components.pageXofY())//show page number on the page footer
		.setDataSource(list);

		try {
			//show the report
			report.show(false);

			//export the report to a pdf file
			//report.toPdf(new FileOutputStream("c:/report.pdf"));
		} catch (DRException dre) {
			dre.printStackTrace();
		} /*catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	
	public static void displayTripRecordsOfEmployee(ArrayList<HashMap<String, String>> list, int id) {

		JasperReportBuilder report = DynamicReports.report();//a new report
		report
		.columns(
				Columns.column("Booking ID", "trip_id", DataTypes.stringType()),
				Columns.column("Vehicle ID", "veh_id", DataTypes.stringType()),
//				Columns.column("Employee ID", "emp_id", DataTypes.stringType()),
				Columns.column("pick-up location pin", "pick_pin", DataTypes.stringType()),
				Columns.column("Drop-ff location pin", "drop_pin", DataTypes.stringType()),
				Columns.column("Distance (in KMs)", "distance", DataTypes.stringType()),
				Columns.column("Fuel used (in Ltr)", "fuel_used", DataTypes.stringType()),
				Columns.column("Trip cost (in EUR)", "trip_cost", DataTypes.stringType()),
				Columns.column("Trip booking time", "book_time", DataTypes.stringType()),
				Columns.column("Trip start time", "trip_start", DataTypes.stringType()),
				Columns.column("Trip end time", "trip_end", DataTypes.stringType()),
				Columns.column("Trip current status", "status", DataTypes.stringType())
				)
		.title(//title of the report
				Components.text("Trip records for employee with ID: "+id)
				.setHorizontalAlignment(HorizontalAlignment.CENTER))
		.pageFooter(Components.pageXofY())//show page number on the page footer
		.setDataSource(list);

		try {
			//show the report
			report.show(false);

			//export the report to a pdf file
			//report.toPdf(new FileOutputStream("c:/report.pdf"));
		} catch (DRException dre) {
			dre.printStackTrace();
		} /*catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	
	public static void displayMaintHistoryForVehicle(ArrayList<HashMap<String, String>> list, int id) {

		JasperReportBuilder report = DynamicReports.report();//a new report
		report
		.columns(
				Columns.column("Maintennce ID", "maint_id", DataTypes.stringType()),
//				Columns.column("Vehicle ID", "veh_id", DataTypes.stringType()),
				Columns.column("Current maintenace date", "maint_date", DataTypes.stringType()),
				Columns.column("Due date for next maintenance", "due_date", DataTypes.stringType()),
				Columns.column("Maintenance cost (in EUR)", "maint_cost", DataTypes.stringType()),
				Columns.column("Description", "description", DataTypes.stringType()),
				Columns.column("Maintenance type", "maint_type", DataTypes.stringType())
				)
		.title(//title of the report
				Components.text("Maintenance history for vehicle with ID: "+id)
				.setHorizontalAlignment(HorizontalAlignment.CENTER))
		.pageFooter(Components.pageXofY())//show page number on the page footer
		.setDataSource(list);

		try {
			//show the report
			report.show(false);

			//export the report to a pdf file
			//report.toPdf(new FileOutputStream("c:/report.pdf"));
		} catch (DRException dre) {
			dre.printStackTrace();
		} /*catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	
}