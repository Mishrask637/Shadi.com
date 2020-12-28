package com.testclasses;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class TestScenario {
	 
	static boolean isavailable = false;

	public void startServer() {
		CommandLine cmd = new CommandLine("C:\\Program Files\\nodejs\\node.exe");
		cmd.addArgument("C:\\Users\\LENOVO\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"); // C:\\Program
		cmd.addArgument("--address");
		cmd.addArgument("0.0.0.0");
		cmd.addArgument("--port");
		cmd.addArgument("4723");
		
		DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {
			//Thread.sleep(5000);
			executor.execute(cmd, handler);
			Thread.sleep(5000);
			System.out.println("Appium server started successfully");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	
	public void stopServer() { Runtime runtime =
			 Runtime.getRuntime(); try {
				  runtime.exec("taskkill /F /IM node.js");
				  Thread.sleep(3000);
				  runtime.exec("taskkill /F /IM node.exe");
				  Thread.sleep(5000);
				  System.out.println("Server Stopped successfully"); } catch (IOException e) {
			  e.printStackTrace(); } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} }
	

	public static void main(String[] args) throws InterruptedException {

		AppiumDriver<MobileElement> driver = null;

		TestScenario appiumServer = new TestScenario();
		appiumServer.startServer();
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "RZ8N92QX07Z"); // Give Device ID of your mobile phone 48ce4ed7cf40
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "10");
		caps.setCapability("appPackage", "com.shaadi.android");
		caps.setCapability("appActivity", "com.shaadi.android.ui.splash.SplashScreenActivity");
		caps.setCapability("noReset", "true");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20000");
		caps.setCapability("automationName", "UiAutomator2");
	
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			Thread.sleep(5000);
			System.out.println("Done");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error");
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		try
		{
			new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/btn_morph_login"))).isEnabled();
			isavailable = true;
		}
		catch (Exception e) {
			System.out.println("Element Login Is Not Available i.e. App is already logged in");
		}
		
		if(isavailable)
		{
		new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/btn_morph_login")));
		driver.findElement(MobileBy.id("com.shaadi.android:id/btn_morph_login")).click();
	
		new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/edt_username")));
		driver.findElement(MobileBy.id("com.shaadi.android:id/edt_username")).clear();
		
		driver.findElement(MobileBy.id("com.shaadi.android:id/edt_username")).sendKeys("suraj.mishra94@yahoo.in");;
	
		new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/edt_password")));
		driver.findElement(MobileBy.id("com.shaadi.android:id/edt_password")).sendKeys("637Mishra");
	
		new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/btn_login")));
		driver.findElement(MobileBy.id("com.shaadi.android:id/btn_login")).click();
		
		isavailable=false;
		
		}
		
		try {
			new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/menu_skip"))).isEnabled();
			isavailable = true;
		}
		catch (Exception e) {
			System.out.println("Element Skip Is Not Available");
		}
		
		if(isavailable)
		{
			driver.findElement(MobileBy.id("com.shaadi.android:id/menu_skip")).click();
			isavailable=false;
		}
		
		
		
		
		new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/tvMyShaadi")));
		driver.findElement(MobileBy.id("com.shaadi.android:id/tvMyShaadi")).click();
		
		assertEquals(true, new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/tv_title"))).isDisplayed());
		
		new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/imageView12")));
		driver.findElement(MobileBy.id("com.shaadi.android:id/imageView12")).click();
		
		new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/btnConnect")));
		driver.findElement(MobileBy.id("com.shaadi.android:id/btnConnect")).click();
		
		driver.navigate().back();
		
		Thread.sleep(2000);
		
		driver.findElement(MobileBy.AndroidUIAutomator(
		        "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
		         ".scrollIntoView(new UiSelector().textContains(\"New Matches\"))"));
		
		assertEquals(true, new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.shaadi.android:id/recyclerView\"]/android.widget.FrameLayout[2]//android.widget.TextView[@resource-id=\"com.shaadi.android:id/tv_title\"]"))).isDisplayed());

		new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/imageView12")));
		
		Thread.sleep(2000);
		
		driver.findElements(MobileBy.id("com.shaadi.android:id/imageView12")).get(4).click();
		
		new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/btnConnect")));

		Thread.sleep(3000);
		
		driver.findElement(MobileBy.id("com.shaadi.android:id/btnConnect")).click();
		
		driver.navigate().back();
		
		driver.findElement(MobileBy.AndroidUIAutomator(
		        "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
		         ".scrollIntoView(new UiSelector().text(\"Account Settings\"))"));
		
		new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/txt_title")));
		driver.findElements(MobileBy.id("com.shaadi.android:id/txt_title")).get(2).click();
		
		
		scrollByID(driver, "com.shaadi.android:id/btn_logoute", 0);
		
		new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.shaadi.android:id/btn_logout")));
		driver.findElement(MobileBy.id("com.shaadi.android:id/btn_logout")).click();
		
		
		assertEquals(true, driver.findElement(MobileBy.id("com.shaadi.android:id/btn_login")).isEnabled());

		driver.quit();
		
		appiumServer.stopServer();

		
	}
		public static void scrollByID(AppiumDriver<MobileElement>driver ,String Id, int index) {

	        try {

	             driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceId(\""+Id+"\").instance("+index+"));")); 

	        } catch (Exception e) {
	           e.printStackTrace();
	        }
	    }
		
		
	}
