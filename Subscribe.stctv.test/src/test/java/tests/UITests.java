package tests;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.Types;
import pageObjects.SubscriptionsPackages;
import utility.BaseClass;
import static utility.BrowserFactory.*;

public class UITests extends BaseClass {

	WebDriver driver;
	SubscriptionsPackages subscriptionsPackages;
	Properties property;

	@BeforeClass
	public void beforeTest() throws IOException {

		driver = getDriver();
		subscriptionsPackages = PageFactory.initElements(driver, SubscriptionsPackages.class);
		property = readDataFromPropertyFile(
				"G:\\AutomationFrameWork\\Subscribe.stctv.test\\src\\test\\resources\\testData.properties");
		nagivateToApplication(property.getProperty("baseURL"));
		Reporter.log("Navigate to the application url");
		maximizeWindow();
		initializeImplicitWait();
	}

	@AfterClass
	public void AfterTest() throws InterruptedException {
		closeAllWindow();
	}

	@AfterMethod
	public void AfterMethod() throws InterruptedException {
		nagivateToApplication(property.getProperty("baseURL"));
	}

	@Test(dataProvider = "getData")
	public void testBahrain(String countryName, String language) throws InterruptedException, IOException {
		SubscriptionsPackages.selectLanguage(language);
		Reporter.log("Selected " + language + " language");
		SubscriptionsPackages.clickOnCountryLink();
		Reporter.log("Clicked on country link");
		SubscriptionsPackages.selectCountry(countryName);
		Reporter.log("Selected the " + countryName + " Country");
		for (Types c : Types.values()) {

			Assert.assertTrue(SubscriptionsPackages.getTypes().contains(c.getValue()));
		}
		Reporter.log("Packages for country " + countryName + " are verified");
		validatePriceForPackeage(countryName, property.getProperty("LitePackageName"));
		validatePriceForPackeage(countryName, property.getProperty("ClassicPackageName"));
		validatePriceForPackeage(countryName, property.getProperty("PremiumPackageName"));
		Reporter.log("Price of packages for country " + countryName + " are verified");
		validateCurrencyForPackeage(countryName, property.getProperty("LitePackageName"));
		validateCurrencyForPackeage(countryName, property.getProperty("ClassicPackageName"));
		validateCurrencyForPackeage(countryName, property.getProperty("PremiumPackageName"));
		Reporter.log("Currency of packages for country " + countryName + " are verified");
	}
}
