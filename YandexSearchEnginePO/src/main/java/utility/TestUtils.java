package utility;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import base.TestBase;

public class TestUtils extends TestBase{
	
	public static long load_time_out=10;
	public static long implicit_wait=10;
	
	/* takeScreenShot Method - will take screenshot , copy it to screenshot folder in project
	 *  using reporter log i am adding screenshot hyperlink to testNG report - index.html , 
	 *  on clicking Image it will navigate to screenshot*/
	
	public static void takeScreenShot() 
	{
		TakesScreenshot tc = (TakesScreenshot)d;
		File srcFile=tc.getScreenshotAs(OutputType.FILE);
		File destFile=new File(System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png");
		try {
			FileUtils.copyFile(srcFile,destFile);
			Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
	           
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	} 

	//method take screenshot , copy file to screenshot folder and return file path
	public static String getScreenShotPath() 
	{
		TakesScreenshot tc = (TakesScreenshot)d;
		File srcFile=tc.getScreenshotAs(OutputType.FILE);
		File destFile=new File(System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png");
		try {
			FileUtils.copyFile(srcFile,destFile);
				           
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return destFile.getAbsolutePath();
	}
}
