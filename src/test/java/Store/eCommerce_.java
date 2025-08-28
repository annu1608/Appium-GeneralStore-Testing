package Store;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import TestUtils.AndroidBaseTest;
import io.appium.java_client.AppiumBy;

public class eCommerce_ extends AndroidBaseTest{
	
	@Test
	public void PricesSumTest() throws InterruptedException {
		
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Annu kumari");
		driver.hideKeyboard();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
	    driver.findElement(AppiumBy.androidUIAutomator(
			    "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Bhutan\"))"
			));
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Bhutan\")")).click();
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),"text", "Cart"));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title"),"Cart"));
		
		List<WebElement> productPrices= driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
		int count= productPrices.size();
		double ActualSum =0;
		for(int i =0; i<count; i++)
		{
			String Amount = productPrices.get(i).getText();
			Double price = Double.parseDouble(Amount.substring(1));
			ActualSum =  ActualSum + price;// 160.97
			System.out.println("ActualSum: "+ ActualSum );
	        }
		
	String displayprice = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();		
	Double TotalpriceDisplay = Double.parseDouble(displayprice.substring(1));
	System.out.println("TotalpriceDisplay: "+ TotalpriceDisplay );
	
	Assert.assertEquals(ActualSum, TotalpriceDisplay);
	
	
	 WebElement TermConditions= driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
	 
	 ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
			 ImmutableMap.of("elementId",((RemoteWebElement)TermConditions).getId(),"duration",2000));
   driver.findElement(AppiumBy.id("android:id/button1")).click();

	driver.findElement(AppiumBy.xpath("//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]")).click();
	driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
	
	
	

	
	
	
	}}
