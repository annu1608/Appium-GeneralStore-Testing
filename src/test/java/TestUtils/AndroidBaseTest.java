package TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import annu.PageObjects.android.FormPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTest {
	

	public 	AndroidDriver driver;
	public AppiumDriverLocalService service ;
	public FormPage formPage;
	
	@BeforeClass(alwaysRun=true)
	public void configureAppium() throws URISyntaxException, IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\annu\\resources\\data.properties");
	 //if ipAdress is not equal to null then execute first statement if equal to null execute second statement 
		String ipAddress=System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress"):prop.getProperty("ipAddress");
		prop.load(fis);
		  // String ipAddress = prop.getProperty("ipAddress");
		    int port = Integer.parseInt(prop.getProperty("port"));
		    String androidDevice = prop.getProperty("AndroidDevice");
	
		//appium server location
		 service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\ANNU\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAddress)
				.usingPort(port)
				.build();        
		    service.start();
		 
		 
		UiAutomator2Options options= new UiAutomator2Options();//start emulator
		options.setDeviceName(androidDevice);
		options.setApp("C:\\Users\\ANNU\\eclipse-fresh-workspace\\GernalStore\\src\\test\\java\\Apk_app\\General-Store.apk"); // apk file location
		options.setNoReset(false); 
        options.setChromedriverExecutable("C:\\Users\\ANNU\\Downloads\\driver\\chromedriver.exe");
		
		
		 driver = new AndroidDriver(new URI(("http://" + ipAddress + ":" + port)).toURL(),options);
		 if (driver == null) {
		        throw new RuntimeException("❌ Driver failed to initialize. Check Appium logs.");
		    }
		
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 driver.rotate(new DeviceRotation(0, 0, 0));
		 formPage=  new FormPage(driver);

		
	}
	
	
    @BeforeMethod(alwaysRun = true)
	public void preSetUp() {
    	// adb devices
    	//adb shell dumpsys window | findstr "mCurrentFocus"
    	//mCurrentFocus=Window{cd43808 u0 com.androidsample.generalstore/com.androidsample.generalstore.MainActivity}
    	// 1st way → Activity object
    	// Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
    	// driver.startActivity(activity);
    	
              formPage = new FormPage(driver);  //every fresh bindings
		      formPage.setActivity(); 
		            
		       }

			 

 	public static  List<HashMap<String, String>> getJsonToMap(String JsonFilePath) throws IOException
 	{
 		//parse json file - json string (commons- io)
 		//json string - HashMap(jackson)
 		//System.getProperty("user.dir")+"\\src\\test\\java\\TestData\\eCommerce.json"
 		
 	    String jsonContent= FileUtils.readFileToString(new File(JsonFilePath),
 				StandardCharsets.UTF_8);
 	    
 		ObjectMapper mapper = new ObjectMapper();
 		List<HashMap<String,String>> data= mapper.readValue(jsonContent, 
 				new TypeReference<List<HashMap<String,String>>>(){
 		});
 		return data;
 	}
 	
   @DataProvider 
   public Object[][] getData() throws IOException {
		 
		 
		 List <HashMap<String, String>> data= getJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\TestData\\eCommerce.json");
		 return new Object[][] {
		 {data.get(0)}};
		
	 } 
   
 	
  
	public String getScreenShotPath(String testCaseName , AndroidDriver driver) throws IOException {
		File source =driver.getScreenshotAs(OutputType.FILE);
	  
	    String destinationPath = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	    
	   
	    File destinationFile = new File(destinationPath);
	    FileUtils.copyFile(source, destinationFile);
	    
	    return destinationPath;
		
		
	}
	
	
	@AfterClass(alwaysRun=true)
	public  void tearDown() {
		
		   try {
		        // Force portrait before quitting
		        if (driver != null) {
		            driver.rotate(new DeviceRotation(0, 0, 0));  
		        }
		    } catch (Exception e) {
		        System.out.println("Could not reset orientation: " + e.getMessage());
		    }
		 
		  if (driver != null) {
		        driver.quit();
		    }
		    if (service != null) {
		        service.stop();
		    }
		    
		   
			  }
		
		
	

}
