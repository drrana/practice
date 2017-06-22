package TestScript;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.util.Properties;

import MicroMethods.*;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import dataTable.Genericmethod;
import dataTable.Log;
import dataTable.ObjectRepository;

public class TcLogin {

	public WebDriver driver;
	public StringBuffer verificationErrors = new StringBuffer();
	public static Properties CONFIG;
	
	@BeforeSuite
	/**
	* @Created By : Rushi.Dixt
	* @Description : This method will be execute before executing the TCScript class 
	* @Return : Test case ID for execution
	* @throws Exception
	**/
	public void InitializeSuite() throws Exception {
		/** This method will read the config.properties file and load all the values over the object fs.*/
    	FileInputStream fs = new FileInputStream("src/config/config.properties");
    	CONFIG= new Properties();
    	CONFIG.load(fs);
    	String Environment = CONFIG.getProperty("Environment");
    	Log.Environmentdetails(Environment);
    	Log.startTestCase();
    	Log.info("Retriving the values from config.properties file." );
    	/*SetupEnvironment.SetdataSheet();*/
    }
	
	
	@BeforeMethod
	public void setUp() throws Exception {
		/** This step will invoke the driver whose value has been passed as browser name in config.properties file*/
		driver = Genericmethod.OpenBrowser(CONFIG);
        String sBrowserName = CONFIG.getProperty("BrowserType");
        Log.info(sBrowserName+" Browser has been initiated for testing.");
		new Baseclass(driver);
	}
	
	@Test (priority = 0)
	public void Verify_Login_process() throws Exception
  	{
  		Log.info("--------- Execution of Test Case ID - C_TS59 - Scenario_CartAction_59 - started -----------");
  		try
  		{
  			BusinessUnit.Perform_Gmail_login(ObjectRepository.scenario1);
  			//SetupEnvironment.createXLSReport(ObjectRepository.KEYWORD_PASS, ObjectRepository.cart_Test_case_59, "Cart");
  			
  		}
  		catch(Exception e)
  		{
  			//Genericmethod.takeScreenshot(driver);
  			Log.error(e.getMessage());
  			//SetupEnvironment.createXLSReport(ObjectRepository.KEYWORD_FAIL, ObjectRepository.cart_Test_case_59, "Cart");
  			throw (e);
  		}
  		
  		
  	}
	
	@Test (priority = 1)
	public void Verify_LoggedIn_User() throws Exception
  	{
  		Log.info("--------- Execution of Test Case ID - C_TS59 - Scenario_CartAction_59 - started -----------");
  		try
  		{
  			BusinessUnit.verify_loggedin_User(ObjectRepository.scenario1);
  			//SetupEnvironment.createXLSReport(ObjectRepository.KEYWORD_PASS, ObjectRepository.cart_Test_case_59, "Cart");
  			
  		}
  		catch(Exception e)
  		{
  			//Genericmethod.takeScreenshot(driver);
  			Log.error(e.getMessage());
  			//SetupEnvironment.createXLSReport(ObjectRepository.KEYWORD_FAIL, ObjectRepository.cart_Test_case_59, "Cart");
  			throw (e);
  		}
  	}
	
	
	 @AfterMethod
	    /**
	   	 * @Created By : Vaibhav.Shrivastava
	   	 * @Description : This method will be executed after complete execution of each Test method
	   	 * @Return : Closed browser
	   	 * @throws Exception
	   	 */
	    public void tearDown() throws Exception {
		 
		 Thread.sleep(5000);
			driver.quit();
			String sBrowserName = CONFIG.getProperty("BrowserType");
	        Log.info(sBrowserName+" Browser have been Closed.");
	        Log.EndTestScript();
		    String verificationErrorString = verificationErrors.toString();
		    if (!"".equals(verificationErrorString)) 
		    {
		    	fail(verificationErrorString);
		    }
		}
	
	

}
