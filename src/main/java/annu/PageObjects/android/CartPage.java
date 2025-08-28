package annu.PageObjects.android;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import annu.utils.AndroidAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage  extends AndroidAction{
	
	AndroidDriver driver;
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;	
		  PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}

	
		@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
		private List<WebElement> productList;
		
		@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
		private WebElement displayPrice;
		
		@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
		private WebElement termConditon;
		
		@AndroidFindBy(id = "android:id/button1")
		private WebElement closeBtn;
		
		@AndroidFindBy(xpath ="//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]")
		private WebElement checkBox;
	
		@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
		private WebElement proceed;	
	
		@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
		private WebElement pageTitleText;	
		
		@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
		private List<WebElement> productName;
		
		@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
		private WebElement  cartItemName;
		
		
		
		
     public void pageToLoad() {
			waitForElement( pageTitleText, driver);
		          }

     public String cartItemName() {
	       return cartItemName.getText();
	              }
     
     public List<WebElement> getproductList() {
			return  productList;
		          }
	    
	public double getProductSum() {
		
	        int count= productList.size();
		    double totalSum =0;
	     	for(int i =0; i<count; i++)
	     	{
		     	String Amount = productList.get(i).getText();
		     	Double price =  getFormattedAmont(Amount);
		         totalSum =  totalSum + price;}// 160.97
	            	return totalSum;
			
		    }
		
   public Double getAmontDisplay() {
	             String ActualAmountDisplay = displayPrice.getText();
	           return   getFormattedAmont(ActualAmountDisplay);
	         
		        }
   

   public int noOfProudct() {
	   
	  return productName.size();
                 }
   
   
   
   
   public List<String> getCartProducts() {
	    List<String> products = new ArrayList<>();
	    for (WebElement e : productName) {
	        products.add(e.getText());
	    }
	    return products;
	}
	   
  
	public void acceptTermConditon() {
			
		longPressAction(termConditon);
		closeBtn.click();
		}
	
	
	public void submitOrder() {
		
        checkBox.click();
		proceed.click();
	}


	
}
