package practise.AppiumFramework;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseClass {
	  public static AppiumDriverLocalService service;
	  public static AndroidDriver<AndroidElement>  driver;
	  static String path=System.getProperty("user.dir");
	
	public AppiumDriverLocalService startServer()
	{
		//
	boolean flag=	checkIfServerIsRunnning(4723);
	if(!flag)
	{
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		service= AppiumDriverLocalService.buildService(builder);
		
//		service=AppiumDriverLocalService.buildDefaultService();
		service.start();
	}
		return service;
		
	}
	
public static boolean checkIfServerIsRunnning(int port) {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
public static void startEmulator() throws IOException, InterruptedException
{

	Runtime.getRuntime().exec(path +"\\src\\main\\java\\resources\\startEmulator.bat");
	Thread.sleep(6000);
}

	public static  AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException
	{
		
FileInputStream fis=new FileInputStream(path +"\\src\\main\\java\\practise\\AppiumFramework\\global.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
	

		// TODO Auto-generated method stub
	 File appDir = new File("src");
     File app = new File(appDir, (String) prop.get(appName));
     DesiredCapabilities capabilities = new DesiredCapabilities();
     String device=(String) prop.get("device");
//    String device= System.getProperty("deviceName");
  if(device.contains("emulator"))
  {
  startEmulator();
  }
     capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
    
     capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
     capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,14);
     
     capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
    driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    return driver;
	}
	
	public static void getScreenshot(String s) throws IOException
	{
	File scrfile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrfile,new File(path +"\\evidences\\"+s+".png"));
	
	}
	
}



//package practise.AppiumFramework;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.URL;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.AndroidElement;
//import io.appium.java_client.remote.MobileCapabilityType;
//import io.appium.java_client.service.local.AppiumDriverLocalService;
//import io.appium.java_client.service.local.AppiumServiceBuilder;
//
//
//public class BaseClass 
//	{
//	
//	/*
//	 * Below Maven Dependencies must be added in pom.xml for supporting startServer method.
//	 * slf4j-simple
//	 * slf4j-api
//	 * commons-io
//	 * commons-validator
//	 * commons-lan3
//	 */
//	public static AppiumDriverLocalService service;
//	static String path=System.getProperty("user.dir");
//	public static AndroidDriver<AndroidElement> driver;
//	
//	public AppiumDriverLocalService startAppiumServer(int port)
//	{
//		
//		boolean flag= checkIfAppiumServerRunning(port);
//		if (flag)
//		{
//			System.out.println("Appium server is running on port="+port);
//		}
//		else
//		{	
//		System.out.println("Starting Appium Server");
//		AppiumServiceBuilder builder = new AppiumServiceBuilder();
//		builder.withIPAddress("127.0.0.1");
//		builder.usingPort(port);
//		service= AppiumDriverLocalService.buildService(builder);
//		service.start();
//		}
//		return service;
//	}
//	
//	public static boolean checkIfAppiumServerRunning(int port)
//	{
//		boolean isServerRunning=false;
//		ServerSocket serverSocket;
//		try
//		{
//			serverSocket =new ServerSocket(port);
//			serverSocket.close();
//		
//		} catch (Exception e)
//		{
//			isServerRunning=true;
//		} finally
//		{
//			serverSocket=null;
//		}
//		return isServerRunning;
//	}
//	
//	public static void startEmulator() throws IOException, InterruptedException
//	{
//		Runtime.getRuntime().exec(path+"\\src\\main\\java\\resources\\startEmulator.bat");
//		Thread.sleep(6000);
//	}
//	//TODO's this code needs to be made more generic as close is not working
////	public static void closeEmulator(String device) throws IOException
////	{
////		if (device.contains("emulator"))
////		{
////			System.out.println("Closing Emulaor");
////			Runtime.getRuntime().exec("adb -s emulator-5554 emu kill");
////		}
////	}
//	public static void getScreenshot(AndroidDriver<AndroidElement> webDriver) throws IOException
//	{	
//		System.out.println(path);
//		System.out.println("i am from getScreenhotin");
////		System.out.println(screenshotName);
//		File srcfle=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		System.out.println("abc34");
//		FileUtils.copyFile(srcfle, new File(path +"\\evidences\\screenshotName1.png"));
//		System.out.println("i am from getScreenhotout");
//	}
//	public static void getScreenshot(String s) throws IOException
//	{
//	File scrfile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//	FileUtils.copyFile(scrfile,new File(System.getProperty("user.dir")+"\\"+s+".png"));
//	
//	}
//	
//	public static AndroidDriver<AndroidElement> capabilities(String apkFile, String deviceName)	throws IOException, InterruptedException
////	public static void main(String[] args) throws MalformedURLException
//	{
//// Below piece of code it to get rid of hardcoded file path and it will always return the current working directory.
//				
////		Below piece of code is to remove hardcoded value of APK file.
//		FileInputStream fis=new FileInputStream(path +"\\src\\main\\java\\practise\\AppiumFramework\\global.properties");
//		Properties prop=new Properties();
//		prop.load(fis);
//
//	 AndroidDriver<AndroidElement>  driver;
//	 File appDir = new File("src");
//     File app = new File(appDir, (String) prop.get(apkFile));
//     String device=(String) prop.get(deviceName);
//     
//     if (device.contains("emulator"))
//     {
//    	 System.out.println("Launching Emulator= "+device);
//    	 startEmulator(); 
//     }
// 	     
//     DesiredCapabilities capabilities = new DesiredCapabilities();
//     
//     capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,device);
//     capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
//     capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
//     capabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
//     driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//     return driver;
//      	}
//
//}

