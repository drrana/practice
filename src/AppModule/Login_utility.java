package AppModule;

import MicroMethods.Login_Action;
import dataTable.Log;
import dataTable.ObjectRepository;
import dataTable.Xls_Reader;

public class Login_utility {

	public static int TestCaseRow = 2;
	public static Xls_Reader currentTestSuiteXLS = new Xls_Reader("src/config/GmailLogin.xlsx");

	public static void Gmail_Login(String MethodName) throws Exception
	{
		
		String sUserName = currentTestSuiteXLS.getCellData(MethodName, ObjectRepository.Login_Excel_Email, TestCaseRow);
		Login_Action.txtbx_UserName().sendKeys(sUserName);
		Log.info("'" + sUserName + "' is entered in UserName text box.");
		Login_Action.Next_Button().click();
		Log.info("Clicked on 1st NExt Button");
		String sPassword = currentTestSuiteXLS.getCellData(MethodName, ObjectRepository.Login_Excel_Password,TestCaseRow);
		Thread.sleep(1000);
		Login_Action.txtbx_Password().sendKeys(sPassword);
		Log.info("'" + sPassword + "' is entered in Password text box.");
		Login_Action.Next_Button().click();
		Log.info("Clicked on 2nd NExt Button");
	
	}

	public static void verify_Text() throws Exception {
		Login_Action.Textverification();
		Log.info("Text Verified");
	}
	
	public static void verify_Invalid_login() throws Exception {
		Login_Action.Textverification_invalid();
		Log.info("Invalid Login Verified- User is not able to login with invalid username");
	}
	
}
