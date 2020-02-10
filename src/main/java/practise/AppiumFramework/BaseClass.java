package practise.AppiumFramework;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;


public class BaseClass {
	public static AndroidDriver<AndroidElement> capabilities(String apkFile, String deviceName)	throws IOException
//	public static void main(String[] args) throws MalformedURLException
{
// Below piece of code it to get rid of hardcoded file path and it will always return the current working directory.
		String path=System.getProperty("user.dir");
		
//		Below piece of code is to remove hardcoded value of APK file.
FileInputStream fis=new FileInputStream(path +"\\src\\main\\java\\practise\\AppiumFramework\\global.properties");
Properties prop=new Properties();
prop.load(fis);

	 AndroidDriver<AndroidElement>  driver;
	 File appDir = new File("src");
     File app = new File(appDir, (String) prop.get(apkFile));

     DesiredCapabilities capabilities = new DesiredCapabilities();
     
     capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, prop.get(deviceName));
     capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
     capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
     capabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
     driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     return driver;
      	}

}