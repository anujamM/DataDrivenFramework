package com.datadriven.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.excel.utility.Xls_Reader;
import com.excel.utility.util;

public class WebTableHandle extends util {

	static WebDriver driver = null;

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
			driver.get("https://www.w3schools.com/html/html_tables.asp");
		} else {
			System.out.println("There's other instance open for the browser. Kindly close and try again.");
		}

	}

	@Test
	public static void main() {

		List<WebElement> rows = driver.findElements(By.xpath("//table[@id=\"customers\"]/tbody/tr"));
		System.out.println("Total number of rows: " + (rows.size() - 1));
		int rowCount = rows.size();
		System.out.println("RowCount: " + rowCount);

		Xls_Reader reader = new Xls_Reader(
				"C:\\Eclipse_Oxy\\myWorkspace\\DataDrivenFramework\\src\\com\\testdata\\DataSheet.xlsx");

		if (!reader.isSheetExist("SeleniumTable")) {
			reader.addSheet("SeleniumTable");

			reader.addColumn("SeleniumTable", "CompanyName");
			reader.addColumn("SeleniumTable", "ContactName");
			reader.addColumn("SeleniumTable", "CountryName");

			String prevXPath_Company = "//*[@id=\"customers\"]/tbody/tr[";
			String postXPath_Company = "]/td[1]";

			System.out.println("Name of companies:::::::::::");

			for (int i = 2; i <= rowCount; i++) {
				String actualXpath_Company = prevXPath_Company + i + postXPath_Company;
				String compnayName = driver.findElement(By.xpath(actualXpath_Company)).getText();
				System.out.println(compnayName);
				reader.setCellData("SeleniumTable", "CompanyName", i, compnayName);

			}

			String prevXpath_Contact = "//table[@id=\"customers\"]/tbody/tr[";
			String postXPath_Contact = "]/td[2]";

			System.out.println("Name of contacts:::::::::::");

			for (int j = 2; j <= rowCount; j++) {
				String actualXpath_Contact = prevXpath_Contact + j + postXPath_Contact;
				String contactName = driver.findElement(By.xpath(actualXpath_Contact)).getText();
				System.out.println(contactName);
				reader.setCellData("SeleniumTable", "ContactName", j, contactName);
			}

			String prevXPath_Country = "//*[@id=\"customers\"]/tbody/tr[";
			String postXPath_Country = "]/td[3]";

			System.out.println("Name of the countries::::::::::::::::");

			for (int k = 2; k <= rowCount; k++) {
				String actualXpath_Country = prevXPath_Country + k + postXPath_Country;
				String countryName = driver.findElement(By.xpath(actualXpath_Country)).getText();
				System.out.println(countryName);
				reader.setCellData("SeleniumTable", "CountryName", k, countryName);
			}

		}
		else {
			System.out.println("Datasheet with this name already exists. Try a new name.");
			reader.removeSheet("SeleniumTable");
			main();
			
		}

	}

}
