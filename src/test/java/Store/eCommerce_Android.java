package Store;

import java.util.HashMap;
import org.testng.annotations.Test;

import TestUtils.AndroidBaseTest;
import annu.PageObjects.android.CartPage;
import annu.PageObjects.android.ProductCatalogue;

public class eCommerce_Android extends AndroidBaseTest{
	
	 @Test(dataProvider="getData", groups ={"Smoke"})
	    public void eCommerce_AndroidTest(HashMap<String,String>input) throws InterruptedException {
	        
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
	    }
	
	 
	


}