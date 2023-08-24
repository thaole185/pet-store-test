package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter; 
	public static ExtentReports extent; 
	public static ExtentTest test; 
	public String testName;
	
	public void onStart(ITestContext context) {
	
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+File.separator+"reports"+File.separator+"myReport");
		
		sparkReporter.config().setDocumentTitle("Automation Test"); 
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter); 
		
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Tester name", "THao");
		extent.setSystemInfo("os","apple");
		extent.setSystemInfo("Browser name","Chrome,Firefox,Edge");
		
		test = extent.createTest(context.getName());
	}
	
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Testcase passed is:"+result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test case that fail is:"+result.getName());
		test.log(Status.FAIL, "Result of failure is:"+result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
}


