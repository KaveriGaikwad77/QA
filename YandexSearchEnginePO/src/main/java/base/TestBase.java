package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import utility.TestUtils;

public class TestBase {
	
	public static WebDriver d;
	public static Properties pop;
	FileInputStream fis;
	String browserChoice;
	String applicationURL;
	
	//In constructor loading property file
	public TestBase()
	{
		try {
			//System.out.println(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
			 fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
			}
		catch (FileNotFoundException e) {
					e.printStackTrace();
			}
		
		pop = new Properties();
		try {
				pop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	//BrowserSetUP - Reading Browser choice and WebApplication URL from property file to launch browser accordingly.
	public void initialize()
	{
		//Reading Choice from Property File
		browserChoice = pop.getProperty("browser");
		applicationURL=pop.getProperty("url");
		System.out.println(browserChoice);
		
		if(browserChoice.equalsIgnoreCase("ie"))			
		{
			//BrowserDriver path from BrowserSetup Folder inside project
			String path= System.getProperty("user.dir")+"/BrowserSetup/IEDriverServer.exe";
			System.out.println("path = "+path);
			
			//Lunching IE Browser
			System.setProperty("webdriver.ie.driver", path);
			d = new InternetExplorerDriver();
			d.get(applicationURL);
			
		}
		else if(browserChoice.equalsIgnoreCase("chrome"))
		{
			//BrowserDriver path from BrowserSetup Folder inside project
			String path= System.getProperty("user.dir")+"/BrowserSetup/chromedriver.exe";
			System.out.println("path = "+path);
			
			//Lunching Chrome Browser and setuping desired capabilities
			System.setProperty("webdriver.chrome.driver", path);
			
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
			d = new ChromeDriver(options);
			d.get(applicationURL);
		}
		else
		{
			System.out.println("Invalid Browser Choice");
		}
		
		//maximizing browser and defining waits  - pageLoadTimeout , implicitlyWait
		
		d.manage().window().maximize();
		//d.manage().deleteAllCookies();
		//d.manage().timeouts().pageLoadTimeout(TestUtils.load_time_out, TimeUnit.SECONDS);
		d.manage().timeouts().implicitlyWait(TestUtils.implicit_wait, TimeUnit.SECONDS);
	}

	//TearDown - Closing Browser Instance
	public void tearDown(){
		d.close();
		d.quit();
	}
}
