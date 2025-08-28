package Store;

import java.time.Duration;
import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import TestUtils.AndroidBaseTest;
import annu.PageObjects.android.CartPage;
import annu.PageObjects.android.ProductCatalogue;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class HybridContextSwitch_Test extends AndroidBaseTest{
	
	@Test(dataProvider = "getData", groups= {"Integration"})
	public void HybridContextSwitchTest(HashMap<String,String> input) throws InterruptedException {
		
		    formPage.setNameField(input.get("name"));
	        formPage.setGender(input.get("gender"));
	        formPage.setCountry(input.get("country"));

	        ProductCatalogue productCatalogue = formPage.submitFoam();
	        productCatalogue.addItemToCartByIndex(0);
	        productCatalogue.addItemToCartByIndex(0);

	        CartPage cartPage = productCatalogue.goToCartPage();
	        cartPage.pageToLoad();

	        cartPage.acceptTermConditon();
	        cartPage.submitOrder();
	        
	// Hybrid --> Google Page--->
	//NATIVE_APP
	//WEBVIEW_com.androidsample.generalstore

	Thread.sleep(6000);  // temporary, can be replaced with better wait

     // Check available contexts
        Set<String> contexts = driver.getContextHandles();
          for (String contextName : contexts) {
              System.out.println("Available context: " + contextName); }
                                           

    // Switch to WEBVIEW only if present
        if (contexts.contains("WEBVIEW_com.androidsample.generalstore")) 
             {
             driver.context("WEBVIEW_com.androidsample.generalstore");

     		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")) );  // Wait until Google search box is visible
     
          searchBox.sendKeys("EPFO"); // Perform search
          searchBox.sendKeys(Keys.ENTER);
  
           Thread.sleep(3000);

    
           driver.pressKey(new KeyEvent(AndroidKey.BACK));// Back to native context
           driver.context("NATIVE_APP");
                  } 
                   else {
                          System.out.println("WEBVIEW context not available yet. Cannot switch.");   
                          }
                       
	
	
	}}
