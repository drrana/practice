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
	
}
