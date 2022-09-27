package utility;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	static ChromeOptions chromeOptions;
	static WebDriver driver;
	File file;
	FileInputStream fileInputStream;
	Properties property;

	public static WebDriver getDriver() {
		chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
		return driver;
	}

	public static void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public static void initializeImplicitWait() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	public static void closeAllWindow() {
		driver.quit();
	}

	public static void nagivateToApplication(String url) {
		driver.get(url);
	}
}
