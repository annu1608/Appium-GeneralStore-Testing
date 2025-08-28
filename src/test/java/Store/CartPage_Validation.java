package Store;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestUtils.AndroidBaseTest;
import annu.PageObjects.android.CartPage;
import annu.PageObjects.android.ProductCatalogue;

public class CartPage_Validation extends AndroidBaseTest{
	
	@Test(dataProvider = "getData", groups = {"Functional"})
	public void CartPAgeTest(HashMap<String,String> input) throws InterruptedException {
		
		
		formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        ProductCatalogue productCatalogue = formPage.submitFoam();
        productCatalogue.addItemToCartByIndex(0);
        productCatalogue.addItemToCartByIndex(0);

        CartPage cartPage = productCatalogue.goToCartPage();
        cartPage.pageToLoad();
        
        
        int cartCount = cartPage.noOfProudct();
        int TotalItemCount= cartPage.getCartProducts().size();
        Assert.assertEquals(cartCount,TotalItemCount );
	}

}
