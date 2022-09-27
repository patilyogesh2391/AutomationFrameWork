package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

import pageObjects.SubscriptionsPackages;

public class BaseClass {

	static File file;
	static FileInputStream fileInputStream;
	static Properties property;

	public static Properties readDataFromPropertyFile(String filePath) throws IOException {
		file = new File(filePath);
		fileInputStream = new FileInputStream(file);
		property = new Properties();
		property.load(fileInputStream);
		return property;
	}

	@DataProvider(name = "getData")
	public Object[][] getData() {
		String lang = property.getProperty("Language");
		return new Object[][] { { property.getProperty("Bahraincountry"), lang },
				{ property.getProperty("KSAcountry"), lang }, { property.getProperty("Kuwaitcountry"), lang } };
	}

	public static void validatePriceForPackeage(String countryName, String packageType) {
		Assert.assertEquals(SubscriptionsPackages.getPriceBasedOnType(packageType),
				property.getProperty(countryName + "." + packageType));
	}

	public static void validateCurrencyForPackeage(String countryName, String packageType) {

		Assert.assertEquals(SubscriptionsPackages.getCurrencyBasedOnType(packageType),
				property.getProperty(countryName + ".Currency"));
	}
}
