package com.excel.utility;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class util {
	
	static Xls_Reader reader;
	
	public static void killProcess() throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("taskkill /im firefox.exe /f /t");
//		proc.destroy();
	}

	public static int windCount(WebDriver driver) {
		Set<String> windHandles = driver.getWindowHandles();
		int numberofWinds = windHandles.size();
		return numberofWinds;
	}

	public static String dateFormat(String x) {
		String date = null;
		if(x.indexOf("0") == 0) {
			date = x.substring(1, 2);			
		}
		else {
			date = x;
		}
		return date;
	}

	public static String monthIssue(String y) {
		String z = null;
		String temp = "Sep";
		if(y.equalsIgnoreCase(temp)) {
			z = y + "t";
			return z;
		}
		else {
			return y;
		}
	}

	public static int genderVal(String a) {
		if(a.equalsIgnoreCase("Female")) {
			return 1;
		}
		else
			return 2;
	}
	
	public static ArrayList<Object[]> getDatafromExcel() throws ParseException{
		

		
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		try {
			reader = new Xls_Reader("C:\\Eclipse_Oxy\\myWorkspace\\DataDrivenFramework\\src\\com\\testdata\\DataSheet.xlsx");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int rowNum = 2; rowNum <= reader.getRowCount("RegTestData"); rowNum++) {
			System.out.println("======**======");

			String firstName = reader.getCellData("RegTestData", "FName", rowNum);
			System.out.println("First Name is: " + firstName);

			String lastName = reader.getCellData("RegTestData", "LName", rowNum);
			System.out.println("Last Name is: " + lastName);

			String contNum = reader.getCellData("RegTestData", "ContactNo", rowNum);
			System.out.println("Contact Number is: " + contNum);

			String dateOfBirth = reader.getCellData("RegTestData", "DOB", rowNum);
			System.out.println("DOB is: " + dateOfBirth);
			Date x = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth);
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			String date = sdf.format(x);
			System.out.println("Final: " + date);

			String y = date.substring(0, 2);
			String dd = dateFormat(y);
			System.out.println("The date is: " + dd);
			String z = date.substring(3, 6);
			String mm = monthIssue(z);
			System.out.println("The month is: " + mm);
			String yyyy = date.substring(7, 11);
			System.out.println("The year is: " + yyyy);

			String g = reader.getCellData("RegTestData", "Gender", rowNum);
			System.out.println(g);
			int Gender = genderVal(g);
			System.out.println("Gender val is: " + Gender);
			
			Object obj[] = {firstName, lastName, contNum, dd, mm, yyyy, Gender};
			myData.add(obj);
			
		}
		return myData;
		
	}
}
