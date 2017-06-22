package TestScript;

import AppModule.Login_utility;

public class BusinessUnit {

	
	public static void Perform_Gmail_login(String MethodName) throws Exception
	{
		Login_utility.Gmail_Login(MethodName);
				
	}
	
	public static void verify_loggedin_User(String MethodName) throws Exception
	{
		Login_utility.Gmail_Login(MethodName);
		Login_utility.verify_Text();
		
	}
	
	public static void Verify_LoggedIn_User_with_Invalid_Username(String MethodName) throws Exception
	{
		Login_utility.Gmail_Login(MethodName);
		Login_utility.verify_Invalid_login();
		
	}
	
	
}
