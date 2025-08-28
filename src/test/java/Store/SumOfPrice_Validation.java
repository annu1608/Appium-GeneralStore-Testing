package Store;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestUtils.AndroidBaseTest;
import annu.PageObjects.android.CartPage;
import annu.PageObjects.android.ProductCatalogue;

public class SumOfPrice_Validation extends AndroidBaseTest {
	
	@Test(dataProvider = "getData" , groups={"Regression"})
	public void SumOfPriceTest(HashMap<String,String> input) throws InterruptedException {
		
		formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setCountry(input.get("country"));
        ProductCatalogue productCatalogue = formPage.submitFoam();
        
        productCatalogue.addItemToCartByIndex(0);
        productCatalogue.addItemToCartByIndex(0);
        CartPage cartPage = productCatalogue.goToCartPage();
        cartPage.pageToLoad();

        Double totalSum = cartPage.getProductSum();
        Double TotalpriceDisplay = cartPage.getAmontDisplay();
        
        Assert.assertEquals(totalSum, TotalpriceDisplay);


                }

		
		
	}

