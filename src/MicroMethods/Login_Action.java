package MicroMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import dataTable.Genericmethod;
import dataTable.ObjectRepository;

public class Login_Action extends Baseclass {
	
	private static WebElement element = null;
	private static String st = null;
	

	public Login_Action(WebDriver driver) {
		super(driver);
		
	}

	public static WebElement txtbx_UserName() throws Exception {
		try {
			Thread.sleep(2000);
			element = driver.findElement(By.xpath(ObjectRepository.Login_Page_Email_Address));
			Genericmethod.highlightElement_btn(element);
			dataTable.Log.info("'Username' text box is found on the Login page.");
		} catch (Exception e) {
			dataTable.Log.info("'Username' text box is not found on the Login page.");
			throw (e);
		}
		return element;
	}
	
	public static WebElement txtbx_Password() throws Exception {
		try {
			Thread.sleep(2000);
			element = driver.findElement(By.xpath(ObjectRepository.Login_Page_Password_Address));
			Genericmethod.highlightElement_btn(element);
			dataTable.Log.info("'Password' text box is found on the Login page.");
		} catch (Exception e) {
			dataTable.Log.info("'Password' text box is not found on the Login page.");
			throw (e);
		}
		return element;
	}
	
	public static WebElement Next_Button() throws Exception {
		try {
			Thread.sleep(2000);
			element = driver.findElement(By.xpath(ObjectRepository.Next_Button));
			Genericmethod.highlightElement_link(element);
			dataTable.Log.info("'Next Button' is found on the Login page.");
		} catch (Exception e) {
			dataTable.Log.info("'Next Button' is not found on the Login page.");
			throw (e);
		}
		return element;
	}

	public static void Textverification() throws Exception 
	{
		try {
			Thread.sleep(5000);
			st = driver.getCurrentUrl();
			System.out.println("Current URL of the Opened Window is " +st);
			
			String expected = "https://mail.google.com/mail/u/0/";
			System.out.println("Expected URL is " +expected);
			Assert.assertEquals(expected, st);
			dataTable.Log.info("'URL is matched.");
		} catch (Exception e) {
			dataTable.Log.info("'URL is not matched.");
			throw (e);
		}
		return;
		
	}
	
	public static void Textverification_invalid() throws Exception 
	{
		try {
			Thread.sleep(5000);
			st = driver.findElement(By.xpath(ObjectRepository.wrong_passwordtext)).getText();
			System.out.println("Wrong Password validation on Screen is " +st);
			String expected = "Wrong password. Try again.";
			System.out.println("Expected validation is" +expected);
			Assert.assertEquals(expected, st,"Invalid user credentials detected as expected. ");
			
			dataTable.Log.info("'Wrong Password text is detected.");
		} catch (Exception e) {
			dataTable.Log.info("'Wrong Password text not detected");
			throw (e);
		}
		return;
		
	}
	
	
	
}

