package Store;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import TestUtils.AndroidBaseTest;
import io.appium.java_client.AppiumBy;

public class BoilerCode extends AndroidBaseTest {
	


	@Test
	public void eCommerceTest() {
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
	//enter name	
	driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Annu kumari");
	driver.hideKeyboard();
	
	//select gender
	driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
	
	//select country
	driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
	
	//scrolling to the country by text
	driver.findElement(AppiumBy.androidUIAutomator(
		    "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Bhutan\"))"
		));
	
	//click to country name by text
	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Bhutan\")")).click();
		

	//click to lets shop
	driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	
	//search product by name
	 driver.findElement(AppiumBy.androidUIAutomator(
			    "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Jordan Lift Off\"))"
			));
	 
	 //Iterate product out of 2 	  
	 int productCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
	 
	 for(int i=0; i<productCount; i++)
	 {
		 String ProductName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
		 
		 if (ProductName.equalsIgnoreCase("Jordan 6 Rings")) {
			 //add to cart
			 driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
		 }
		
	 }
	 
	 //click go to cart
	  driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	  
	 //wait cart page to load
	  wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title"))," text", "Cart"));
	  
	 // cart product name
	   driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText();
	  
	  
	  //tick checkbox
	   driver.findElement(AppiumBy.xpath("//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]")).click();
	
	 
	 //long press to know term and condition
	 WebElement TermConditions= driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
	 
	 ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
			 ImmutableMap.of("elementId",((RemoteWebElement)TermConditions).getId(),"duration",2000));
	 
	 //terms and condition full text
      driver.findElement(AppiumBy.id("android:id/message")).getText();
	
	//close terms and condition
	  driver.findElement(AppiumBy.id("android:id/button1")).click();
	
	//click to purchase
	  driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
	
	}
	

}
