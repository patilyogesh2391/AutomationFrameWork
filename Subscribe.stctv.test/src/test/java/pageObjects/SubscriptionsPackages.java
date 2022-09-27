package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubscriptionsPackages {

	static WebDriver driver;
	static WebDriverWait wait;

	public SubscriptionsPackages(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	static String xpathForCountry = "//li[span[contains(text(),'{name}')]]/a";
	static String xpathForPriceBasedOnType = "//h1[contains(text(),'{typeName}')]/..//span[@class='amount']";
	static String xpathForCurrencyBasedOnType = "//h1[contains(text(),'{typeName}')]/..//span[@class='currency']";

	@FindBy(xpath = "//a[@class='header-btns-country hide-mobile']")
	static WebElement countryLink;

	@FindBy(xpath = "//li[@id='bh']/span[contains(text(),'Bahrain')]")
	static WebElement selectCountryBahrain;

	@FindBy(xpath = "//div/h1[contains(text(),'Lite')]")
	WebElement liteTypePackage;

	@FindBy(xpath = "//div/h1[contains(text(),'Lite')]/..//div/span[contains(@class,'amount')]")
	WebElement litePackagePrice;

	@FindBy(xpath = "//div/h1[contains(text(),'Lite')]/..//div/span[contains(@class,'currency')]")
	WebElement litePackageCurrency;

	@FindBy(xpath = "//h1")
	static List<WebElement> types;

	@FindBy(xpath = "//a[@id='changeLanguageButton']/span")
	static WebElement linkEnglishButton;

	public static void clickOnCountryLink() {
		countryLink.click();
	}

	public static void selectCountry(String countryName) throws InterruptedException {
		WebElement countryNameElement = driver
				.findElement(By.xpath(xpathForCountry.replaceFirst(Pattern.quote("{name}"), countryName)));
		countryNameElement.click();
	}

	public String getPackage() {
		return liteTypePackage.getText();
	}

	public String getPrice() {
		return litePackagePrice.getText();
	}

	public String getCurrency() {
		return litePackageCurrency.getText();
	}

	public static List<String> getTypes() {

		List<String> values = new ArrayList<String>();

		for (WebElement element : types) {

			values.add(element.getText());
		}
		return values;
	}

	public static String getPriceBasedOnType(String typeName) {
		WebElement priceBasedOnType = driver
				.findElement(By.xpath(xpathForPriceBasedOnType.replaceFirst(Pattern.quote("{typeName}"), typeName)));
		priceBasedOnType.getText();
		return priceBasedOnType.getText();

	}

	public static String getCurrencyBasedOnType(String typeName) {
		return driver
				.findElement(By.xpath(xpathForCurrencyBasedOnType.replaceFirst(Pattern.quote("{typeName}"), typeName)))
				.getText();

	}

	public static WebElement getButtonEnglish() {
		return linkEnglishButton;
	}

	public static void selectLanguage(String language) {
		if (language.equalsIgnoreCase("English")) {
			wait.until(ExpectedConditions.elementToBeClickable(SubscriptionsPackages.getButtonEnglish()));
			SubscriptionsPackages.getButtonEnglish().click();
		} else {
			// for other language
		}

	}

}
