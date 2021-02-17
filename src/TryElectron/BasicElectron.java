package TryElectron;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import com.excel.utility.util;

public class BasicElectron extends util {
	
	static WebDriver driver;
	util utl = new util();
	
	@SuppressWarnings("deprecation")
	@BeforeTest
	public static void main () throws IOException {
		killProcess();
		System.setProperty("webdriver.chrome.driver", "C:\\Eclipse_Oxy\\Drivers\\chromedriver_win32.chromedriver");
		ChromeOptions co = new ChromeOptions();
		
		co.addArguments("--start-fullscreen");
		co.addArguments("disable-infobars");
		
		DesiredCapabilities desCap = new DesiredCapabilities();
		String remoteDebuggingAddress = "localhost:9222";
		co.setExperimentalOption("debuggerAddress", remoteDebuggingAddress);
		desCap.setCapability(ChromeOptions.CAPABILITY, co);
		
		driver = new ChromeDriver(desCap);
		
		driver.get("https://www.google.com");
	}

}
