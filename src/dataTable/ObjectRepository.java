package dataTable;

public class ObjectRepository {
	public static final String Login_link="//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']";
	public static final String emailBox="//input[@aria-label='Email or phone']";
	public static final String ClickOnNextbtn="//span[contains(.,'Next')]";
	public static final String PasswordBox="//input[@name='password']";
	public static String Path_ScreenShot="";
	
	
	public static final String Login_Excel_Email ="Username";
	public static final String Login_Excel_Password ="Password";
	public static final String scenario1 = "sheet1";
	public static final String Login_Page_Email_Address =  "//input[@id='identifierId']"; //"//input[@aria-label='Email or phone']";
	public static final String Login_Page_Password_Address = "//input[@name='password']";
	public static final String Next_Button = "//content[@class='CwaK9']";
	//public static final String URL = "https://mail.google.com/mail/u/0/#inbox";
	
	
	//for Invalid date 
	public static final String scenario2 = "Sheet2_invalid_username";
	public static final String wrong_passwordtext=".//*[@id='password']/div[2]/div[2]";
	
	
	//For Email Utlity 
	public static final String Send_email_from ="From";
	public static final String Send_email_To ="To";
	public static final String EmailUsername ="Username";
	public static final String Email_Password ="Password";
	public static final String Send_email_CC ="CC";
	
	//for Controller.xlsx
	public static final String Controller_sheet_email_details="email_details";
	
}