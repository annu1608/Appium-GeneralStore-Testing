package Store;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestUtils.AndroidBaseTest;
import io.appium.java_client.AppiumBy;

public class FormPage_Validation extends AndroidBaseTest{
	
@Test(dataProvider = "getData", groups= {"Functional"})
	public void FillForm_ErrorValidation(HashMap<String,String> input) throws InterruptedException {
		
		formPage.setNameField(""); 
        formPage.setGender(input.get("gender"));
        formPage.setCountry(input.get("country"));
        formPage.submitFoam();
        String toastMessage = formPage.getToastMsg();
	    
	    Assert.assertEquals(toastMessage, "Please enter your name");
	}
	

	@Test(dataProvider = "getData")
	public void FillForm_PositiveFlow(HashMap<String,String> input) throws InterruptedException {
		
	 
		formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setCountry(input.get("country"));
        formPage.submitFoam();	
	    Assert.assertTrue(driver.findElements(AppiumBy.xpath("(//android.widget.Toast)[1]")).size()<1);
	    
	}
	














}
