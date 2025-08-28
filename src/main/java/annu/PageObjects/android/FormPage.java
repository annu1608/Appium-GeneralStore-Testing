package annu.PageObjects.android;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import annu.utils.AndroidAction;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidAction{
	
    AndroidDriver driver;

    public FormPage(AndroidDriver driver) {
        super(driver);   // parent class constructor call
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id= "com.androidsample.generalstore:id/nameField")
	public WebElement nameField;

	@AndroidFindBy(xpath= "//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;
	
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement selectCountry;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private  WebElement shopButn;
	
	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private  WebElement toastText;
	
	
	public void setActivity() {
		
		 ((JavascriptExecutor) driver).executeScript("mobile: startActivity", 
				 ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity" ));
	}
	
	public void setNameField(String name) {
	
		 try {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		        WebElement field = wait.until(ExpectedConditions
		                .visibilityOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/nameField")));
		        field.clear();
		        if (name != null && !name.isEmpty()) {
		            field.sendKeys(name);
		            driver.hideKeyboard();
		        }
		    } catch (StaleElementReferenceException e) {
		        // re-locate once more if stale
		        WebElement field = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"));
		        field.clear();
		        if (name != null && !name.isEmpty()) {
		            field.sendKeys(name);
		            driver.hideKeyboard();
		        }
		    }
	        
		    }
		

	
	public void setGender(String gender) {
	    if (gender.equalsIgnoreCase("Female")) {
	    	femaleOption.click();
	    } else {
	    	maleOption.click();
	    }
	}
	
	public void setCountry(String countryName) {
		selectCountry.click();
		scrollToText(countryName);
	 driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\""+countryName+"\")")).click();
		
	}
	
	public  String getToastMsg() {
		
		 return toastText.getAttribute("name");  
		
		
	}
	
	public ProductCatalogue submitFoam() {
		
           shopButn.click();
           return  new ProductCatalogue(driver);
	            }
	
	
}


