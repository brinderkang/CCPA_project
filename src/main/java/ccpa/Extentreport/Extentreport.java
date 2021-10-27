//package ccpa.Extentreport;
//
//import java.io.File;
//import java.lang.reflect.Method;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.testng.ITestResult;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Test;
//
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
//
////import com.aventstack.extentreports.ExtentReports;
//
//import ccpa.base.Baseclass;
//
//public class Extentreport extends Baseclass {
//	public static ExtentReports report;
//	public static ExtentTest logger;
//	
//	@BeforeSuite
//	 public void startExtentReport()
//	 {
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Date date = new Date();		
//		String TempFileName=dateFormat.format(date);
//		String NewFileName1=TempFileName.replace("/","_");
//		String NewFileName2=NewFileName1.replace(" ","_");
//		String NewFileName=NewFileName2.replace(":","_");
//		System.out.println(NewFileName);
//		System.out.println(System.getProperty("user.dir"));
//		//ExtentReports(String filePath,Boolean replaceExisting) 
//		 //filepath - path of the file, in .htm or .html format - path where your report needs to generate. 
//		 //replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
//		 //True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
//		 //False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
////		report = new ExtentReports ("D:\\Eclipse\\workspace\\Job_management\\src\\Logs\\extentreport.html", false);
//		report = new ExtentReports (System.getProperty("user.dir")+"\\src\\Logs\\extentreport-"+NewFileName+".html", true);
////		report = new ExtentReports ("D:\\Brinder\\OBJ\\Gmail\\src\\Logs\\extentreport-"+NewFileName+".html", true);
//		
//		 //extent.addSystemInfo("Environment","Environment Name")
//		 report
//		 .addSystemInfo("Host Name", "Automation Testing")
//		 .addSystemInfo("Environment", "ANET")
//		 .addSystemInfo("User Name", "Brinder Kang");
////		loading the external xml file (i.e., extent-config.xml) which was placed at below path     
////		            
////		 report.loadConfig(new File("D:\\Brinder\\OBJ\\Gmail\\src\\Logs\\extent-config.xml"));
////		 report.loadConfig(new File(System.getProperty("user.dir")+"\\src\\Logs\\extent-config.xml"));
////		 report.loadConfig(new File(System.getProperty("user.dir")+"\\src\\Logs\\extent-config.xml"));  
//	 }
//	
//	@BeforeMethod
//		public void beforeMethod(Method method)
//			{
//				logger = report.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName());
//				
//				
//			} 
//	
//	@AfterMethod
//	 public void getResult(ITestResult result){	
//	 if(result.getStatus() == ITestResult.FAILURE){
//	 logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
//	 logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
//	 }
//	 else if(result.getStatus() == ITestResult.SKIP){
//	 logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
//	 }
//	 else if(result.getStatus() == ITestResult.SUCCESS){
//		 logger.log(LogStatus.PASS, "Test Case Passed is "+result.getName());
//		 }
//	 // ending test
//	 //endTest(logger) : It ends the current test and prepares to create HTML report
//	 report.endTest(logger);
//	 }
//
//	
//	
//	@AfterSuite
//	public void closebrowser()
//	{
////		extn.close_report();
////		objseldriver.close_browser();
//		report.flush();
////        Call close() at the very end of your session to clear all resources. 
////        If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
////        You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
////        Once this method is called, calling any Extent method will throw an error.
////        close() - To close all the operation
//		report.close();
//		
//	}
//
//
//}
