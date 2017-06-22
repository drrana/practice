package dataTable;

import java.io.IOException;
import org.apache.log4j.Logger;


/**
 * @Description : In this class, we initialize log4j and the invoke a method from that class to get the instance of log4j in each of 
 * 				  the other classes. It is a java based utility that has got all the generic methods already implemented so that we are enabled to use log4j.
 * 				  Log levels are popularly known as printing methods. These are used for printing the log messages.
 * @author Rushi.Dixit
 */

import dataTable.*;
public class Log {
	// Initialize Log4j logs
	private static Logger Log = Logger.getLogger(Log.class.getName());

	// This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
	public static void startTestCase() {
		Log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Log.info("XXXXXXXXXXXXXXXXXXXXX     Execution of StraighterLine Test cases     XXXXXXXXXXXXXXXXXXX");
		Log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	public static void Environmentdetails(String Environment) throws IOException {
		Log.info("========================================================================================");
		Log.info("XXXXXXXXXXXXXXXXXX " + Environment + " Site is used for performing testing" + "  XXXXXXXXXXXXXXXXXX");
		Log.info("XXXXXXXXXXXXXXX Execution started at:-" + Genericmethod.GetTimeStampValue() + " XXXXXXXXXXXXXXXXXXX");
		Log.info("========================================================================================");
	}

	public static void EndTestScript() {
		Log.info("<======================================================================================>");
		Log.info("<================================ End of test script ==================================>");
		Log.info("<======================================================================================>");
	}

	// This is to print log for the ending of the test case
	public static void endTestCase() {
		Log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		Log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXX             E---N---D           XXXXXXXXXXXXXXXXXXXXXXXXXXX");
		Log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}

	public static void info(String message) {
		Log.info(message);
	}

	public static void warn(String message) {
		Log.warn(message);
	}

	public static void error(String message) {
		Log.error(message);
	}

	public static void fatal(String message) {
		Log.fatal(message);
	}

	public static void debug(String message) {
		Log.debug(message);
	}
}