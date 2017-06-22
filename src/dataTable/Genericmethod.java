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

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import dataTable.Log;


public class Genericmethod {
	public static WebDriver driver = null;
	public WebDriverWait wait;
	static boolean acceptNextAlert = true;
	public static Xls_Reader currentXls_file = new Xls_Reader("src/config/Controller.xlsx");
	public static int TestCaseRow = 2;
	
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
	 * 
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
	
	public static void SendmailWithAttachment (final String MethodName){  
		 //public void main(String [] args){  
		String To = currentXls_file.getCellData(MethodName, ObjectRepository.Send_email_To,TestCaseRow);
		  //String to="rdixit@delaplex.in";//change accordingly  
		 
		  //1) get the session object     
		  //Get the session object  
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.socketFactory.port", "465");  
		  props.put("mail.smtp.socketFactory.class",  
		            "javax.net.ssl.SSLSocketFactory");  
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "465"); 
		  
		  Session session = Session.getDefaultInstance(props,  
				   new javax.mail.Authenticator() 
				  {  
			  
			  		protected PasswordAuthentication getPasswordAuthentication() 
				   	{  
			  			String Email_username = currentXls_file.getCellData(MethodName, ObjectRepository.EmailUsername,TestCaseRow);
			  			String Email_Password = currentXls_file.getCellData(MethodName, ObjectRepository.Email_Password,TestCaseRow);
					   //return new PasswordAuthentication("intelatext.ghn1@gmail.com","intelaText@123");//Enter email id and password 
			  			return new PasswordAuthentication(ObjectRepository.EmailUsername,ObjectRepository.Email_Password);
				    }  
				  });  
		     
		  //2) compose message     
		  try{  
			  MimeMessage message = new MimeMessage(session);  
			  String Email_From= currentXls_file.getCellData(MethodName, ObjectRepository.Email_Password,TestCaseRow); 
			  message.setFrom(new InternetAddress(ObjectRepository.Send_email_from));//Enter Email which is used for From:  
			   message.addRecipient(Message.RecipientType.TO,new InternetAddress(To)); 
			   //message.addRecipients(Message.RecipientType.CC,"ppatle@delaplex.in,akurani@delaplex.in" );
			   message.setSubject("Test Execution Report");    
			   MimeBodyPart messageBodyPart1=new MimeBodyPart();
			   messageBodyPart1.setText("Hello," +"\n \n"+  "Test Execution for Test Cases is Completed." +"\n \n"+ "Please find the attached Execution Rpeort herein." +"\n \n"+ "Thanks");
		      
		    //4) create new MimeBodyPart object and set DataHandler object to this object      
		    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
		  
		    //File file1 = new File("file:///D:/Rishi/LearnAutomation/myprojects/application/testng-xslt/index.html");
		    
		    //String filename="D:/Rishi/LearnAutomation/myprojects/application/testng-xslt/index.html";
		    //String filename = "../application/testng-xslt/index.html";
		    String filename = "D://Rishi//myprojects//GmailPom//test-output//emailable-report.html";//change accordingly  
		    DataSource source = new FileDataSource(filename);  
		    messageBodyPart2.setDataHandler(new DataHandler(source));  
		    
		    messageBodyPart2.setFileName(filename);  
		        
		     
		    //5) create Multipart object and add MimeBodyPart objects to this object      
		    Multipart multipart = new MimeMultipart();  
		    multipart.addBodyPart(messageBodyPart1);  
		    multipart.addBodyPart(messageBodyPart2);  
		  
		    //6) set the multiplart object to the message object  
		    message.setContent(multipart);  
		     
		    //7) send message  
		    Transport.send(message);  
		   
		   System.out.println("message sent....");  
		   }catch (MessagingException ex) {ex.printStackTrace();}  
		 }  
		 
}