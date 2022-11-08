package Listener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
public class ListnerTestng implements ITestListener {
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("******TEST CASE EXCUTED SUCCESFULLY*******");
	}
	@Override
	public void onStart(ITestContext context) {
		System.out.println("******TEST CASE EXCUTION START SUCCESFULLY*******");
}
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("******TEST CASE FAIL*******");
		TakesScreenshot ts = (TakesScreenshot) Leetcode.LogIn.driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date=new Date();
		
		String actualDate=format.format(date);
		
		
		File des = new File("E:\\eclipse-workspace4\\ImplimentTestNg\\ScreenShot\\"+actualDate+".png");
		try {
			FileHandler.copy(src, des);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("******TEST CASE SKIPPED*******");

	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("******TEST CASE STARTING EXCUTION SUCCESFULLY*******");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("******TEST CASE PASS*******");
	}

}
