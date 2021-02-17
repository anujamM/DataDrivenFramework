package com.datadriven.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.excel.utility.util;

public class ParameterizedTestV2 extends util {

	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() throws IOException {
		try {
			killProcess();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.setProperty("webdriver.gecko.driver", "C:\\Eclipse_Oxy\\Drivers\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		
		driver = new FirefoxDriver();
		int a = windCount(driver);
		
		if(a != 0) {
			driver.manage().window().maximize();
			
		}
		else {
			System.out.println("There is a problem is the beforeClass method. Please look into it.");
		}
	}
	
	@Test
	@Parameters({"url", "emailID"})
	public void yahooLoginTest(String url, String emailID) {
		driver.get(url);
		driver.findElement(By.xpath("//*[@id=\"login-username\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"login-username\"]")).sendKeys(emailID);
		System.out.println("Email Id entered.");
		driver.findElement(By.xpath("//*[@id=\"login-signin\"]"));
		driver.navigate().to("https://login.yahoo.com/account/challenge/phone-obfuscation?authMechanism=primary&display=login&done=https%3A%2F%2Fwww.yahoo.com%2F&sessionIndex=QQ--&eid=4200&acrumb=UjhH7cuK");
	}
	
//	@AfterTest
//	public void tearDown() {
//		driver.close();
//	}

}
