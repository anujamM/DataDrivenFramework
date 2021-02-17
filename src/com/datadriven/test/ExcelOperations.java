package com.datadriven.test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.excel.utility.Xls_Reader;
import com.excel.utility.util;

public class ExcelOperations extends util {

	static WebDriver driver = null;
	static Xls_Reader reader = new Xls_Reader(
			"C:\\Eclipse_Oxy\\myWorkspace\\DataDrivenFramework\\src\\com\\testdata\\DataSheet.xlsx");

	@BeforeTest
	public void beforeTest() {

		try {
			killProcess();
		} catch (IOException e) {
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

	@DataProvider
	public static Iterator<Object[]> getTestData() throws ParseException {
		ArrayList<Object[]> testData = util.getDatafromExcel();
		return testData.iterator();
	}

	@Test(dataProvider = "getTestData")
	public static void passVal(String firstName, String lastName, String contNum, String dd, String mm, String yyyy, int Gender) throws ParseException {

		// reader.addSheet("RegTestData");
		// int colNum = reader.getColumnCount("RegTestData");
		// System.out.println(colNum);
		// int rowNum = reader.getCellRowNum("RegTestData", "FName", "Anujam");
		// System.out.println(rowNum);

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
	}

	@AfterTest
	public void taerDown() {
		driver.close();
	}

}
