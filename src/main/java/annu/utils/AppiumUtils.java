package annu.utils;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

import io.appium.java_client.android.AndroidDriver;

public class AppiumUtils{


     public Double getFormattedAmont(String amount) {
       
         Double price = Double.parseDouble(amount.substring(1));
            return price;
            }

	 
     public void waitForElement(WebElement ele ,AndroidDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains((ele),"text", "Cart"));
         }


}
