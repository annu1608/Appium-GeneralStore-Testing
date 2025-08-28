package Store;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestUtils.AndroidBaseTest;
import annu.PageObjects.android.CartPage;
import annu.PageObjects.android.ProductCatalogue;

public class ItemSearchByName_Validation extends AndroidBaseTest{
	
	
	@Test(dataProvider = "getData", groups= {"Regression"})
	public void ItemSearchByNameTest(HashMap<String,String> input) throws InterruptedException {
		
		 String expectedProduct = "Jordan 6 Rings";
		
	        formPage.setNameField(input.get("name"));
	        formPage.setGender(input.get("gender"));
	        formPage.setCountry(input.get("country"));
	        ProductCatalogue productCatalogue = formPage.submitFoam();
	        productCatalogue.desireProductName(expectedProduct);
	        productCatalogue.addProductToCart(expectedProduct);
	        CartPage cartPage = productCatalogue.goToCartPage();
	        cartPage.pageToLoad();
		String actualProduct = cartPage.cartItemName();
	    
        Assert.assertEquals(actualProduct, expectedProduct);
			
		}
	        
	        
	}


