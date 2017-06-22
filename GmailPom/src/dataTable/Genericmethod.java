package dataTable;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import dataTable.Log;

/**
 * @Description : This class contains method which handle exception found during runtime. It also contains method for 
 * 				  taking screenshot, explicit wait and invoking different browsers.
 * @author Rushi.dixit
 *
 */
public class Genericmethod {
	public static WebDriver driver = null;
	public WebDriverWait wait;
	static boolean acceptNextAlert = true;

	/**
	 * Description : This method handle element present during execution of test script
	 * @param by
	 * @return true / false
	 */
	public static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Description : This method handles the alert
	 * @return true / false
	 */
	public static boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	/**
	 * Description: This method handles alert and return the value
	 * @return Alert accept / Alert dismiss
	 */
	public static String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
        
    /**
     * Description : This method take the screen shot of the system where ever it fail during execution process
     * @param driver
     * @param ss == Test Case ID
     * @throws Exception
     */
	public static void takeScreenshot(WebDriver driver, String ss) throws Exception {
		try {
			File scrFile = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String systime = GetTimeStampValue().replace(":", "-");
			FileUtils.copyFile(scrFile, new File(ObjectRepository.Path_ScreenShot + ss + " " + systime + ".jpg"));

		} catch (Exception e) {
			Log.error("Class Genericmethod | Method takeScreenshot | Exception occured while capturing ScreenShot : " + e.getMessage());
			throw new Exception();
		}
	}
    
	public static String GetTimeStampValue() throws IOException {
		Calendar cal = Calendar.getInstance();
		Date time = cal.getTime();
		String timestamp = time.toString();
		return timestamp;
	}

    /**
     * Description : This method invoke the driver based on the value passed through config.properties file 
     * @param CONFIG : Fetch the value for the browser to be invoke 
     * @return Driver
     * @throws Exception
     */
    public static WebDriver OpenBrowser(Properties CONFIG) throws Exception {
		String sBrowserName;
		String url;
		try {
			sBrowserName = CONFIG.getProperty("BrowserType");
			url = CONFIG.getProperty("TestURL");
			if(sBrowserName.equals("Mozilla")) {
				driver = new FirefoxDriver();
				Log.info("<======================================================================================>");
				Log.info("<============================= Execution of New Test Script ===========================>");
				Log.info("<======================================================================================>");
				Log.info("New driver instantiated.");
				Log.info(url+"==> is used for performing testing." );
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			    driver.get(CONFIG.getProperty("TestURL"));
			    Log.info("Web application launched successfully.");
			}
			else if(sBrowserName.equals("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
				driver = new ChromeDriver();
				Log.info("<======================================================================================>");
				Log.info("<============================= Execution of New Test Script ===========================>");
				Log.info("<======================================================================================>");
				Log.info("New driver instantiated.");
				Log.info(url+"==> is used for performing testing." );
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.get(CONFIG.getProperty("TestURL"));
				Log.info("Web application launched successfully.");
			}
			else if(sBrowserName.equals("IE")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				System.setProperty("webdriver.ie.driver","Driver\\IEDriverServer.exe");				
				driver = new InternetExplorerDriver(capabilities);
				Log.info("<======================================================================================>");
				Log.info("<============================= Execution of New Test Script ===========================>");
				Log.info("<======================================================================================>");
				Log.info("New driver instantiated.");
				Log.info(url+"==> is used for performing testing." );
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.get(CONFIG.getProperty("TestURL"));
				Log.info("Web application launched successfully.");
			}
		} catch (Exception e) {
			Log.error("Class Genericmethod | Method OpenBrowser | Exception desc : "+e.getMessage());
		}
		return driver;
	}
    
    /**
     * Description :This Method wait for the element to be visible on page for specified time
     * @param element
     * @return element
     */
	public static WebElement waitForElement(WebElement element) {
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		 return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
     * Description :This Method highlights the button element to be visible on page for specified time
     * @param element
     * @return element
     */
	public static void highlightElement_btn(WebElement element)throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);", element);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "color: black; border: 3px solid yellow");
		Thread.sleep(2000);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "");
	}

	/**
     * Description :This Method highlights the link element to be visible on page for specified time
     * @param element
     * @return element
     */
	public static void highlightElement_link(WebElement element)throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);", element);
		js.executeScript("arguments[0].setAttribute('style', arguments[1], arguments[2]);",element,"color: black; border: 3px solid yellow; background-color: yellow");
		Thread.sleep(2000);
		js.executeScript("arguments[0].setAttribute('style', arguments[1], arguments[2]);",element, "");
	}
}