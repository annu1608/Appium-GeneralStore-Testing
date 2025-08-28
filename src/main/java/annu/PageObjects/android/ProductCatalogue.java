package annu.PageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import annu.utils.AndroidAction;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogue extends AndroidAction{
	

    AndroidDriver driver;

    public ProductCatalogue(AndroidDriver driver) {
        super(driver);   // parent class constructor call
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

	
	
    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	private List<WebElement> productName;
    
	@AndroidFindBy(xpath= "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCartBtn;
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement CartBtn;
	
	
	public void desireProductName(String desireProduct) {
	       scrollToText(desireProduct);
	 	}
	
	
	public void addProductToCart(String itemName) {
		   for(int i=0; i<productName.size(); i++)
		        {
		      	 String ProductName = productName.get(i).getText();
			        if (ProductName.equalsIgnoreCase(itemName)) 
				     addToCartBtn.get(i).click();
			 }
		 }
			 
		
	
    public void addItemToCartByIndex(int index) {
    	addToCartBtn.get(index).click();
    }	
	public CartPage goToCartPage() throws InterruptedException {
		CartBtn.click();
		Thread.sleep(3000);
		
		return new CartPage(driver);
	}
}
