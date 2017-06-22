package MicroMethods;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;

import sun.util.logging.resources.logging;

public class Baseclass {
	public static WebDriver driver;
	public static boolean bResult;

	public Baseclass(WebDriver driver) {
		Baseclass.driver = driver;
		Baseclass.bResult = true;
		DOMConfigurator.configure("log4j.xml");
		 
	}
}