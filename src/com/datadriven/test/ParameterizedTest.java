package com.datadriven.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.excel.utility.Xls_Reader;
import com.excel.utility.util;

public class ParameterizedTest extends util {

	static WebDriver driver = null;
	static Xls_Reader reader = new Xls_Reader(
			"C:\\Eclipse_Oxy\\myWorkspace\\DataDrivenFramework\\src\\com\\testdata\\DataSheet.xlsx");

	@BeforeTest
	public void beforeTest() {

		try {
			killProcess();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.setProperty("webdriver.gecko.driver", "C:\\Eclipse_Oxy\\Drivers\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		driver = new FirefoxDriver();

		int a = windCount(driver);

		if (a != 0) {
			driver.manage().window().maximize();
			driver.get("https:\\www.facebook.com");
		} else {
			System.out.println("There's other instance open for the browser. Kindly close and try again.");
		}

	}

	@Test(priority = 1)
	public static void test() throws ParseException {

		int rowCount = reader.getRowCount("RegTestData");
		System.out.println("Rowcount is: " + rowCount);

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
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

			driver.findElement(By.name("firstname")).clear();
			driver.findElement(By.name("firstname")).sendKeys(firstName);
			driver.findElement(By.name("lastname")).clear();
			driver.findElement(By.name("lastname")).sendKeys(lastName);
			driver.findElement(By.name("reg_email__")).clear();
			driver.findElement(By.name("reg_email__")).sendKeys(contNum);
			Select selectDate = new Select(driver.findElement(By.id("day")));
			selectDate.selectByVisibleText(dd);
			Select selectMonth = new Select(driver.findElement(By.id("month")));
			selectMonth.selectByVisibleText(mm);
			Select selectYear = new Select(driver.findElement(By.id("year")));
			selectYear.selectByVisibleText(yyyy);
			WebElement genRadBtn = driver.findElement(By.cssSelector("input[value='" + Gender + "']"));
			genRadBtn.click();

			// reader.setCellData("RegTestData", "FName", rowNum, data);
			reader.addColumn("RegTestData", "TempColumnIWillDelete");
		}
	}
	
	@AfterTest
	public static void afterTest() {
		reader.removeColumn("RegTestData", 6);
	}

}
