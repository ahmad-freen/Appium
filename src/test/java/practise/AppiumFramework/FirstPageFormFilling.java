package practise.AppiumFramework;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static java.time.Duration.ofSeconds;

import java.io.File;
import java.io.IOException;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class FirstPageFormFilling extends BaseClass {
	
	@Test
	public void formFilling() throws IOException, InterruptedException
	{
	service= startServer();	
	
	AndroidDriver<AndroidElement> driver=capabilities("appName");
	File srcfle=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

	driver.findElementByXPath("//android.widget.TextView[@resource-id='android:id/text1']").click();
	
	driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bahamas\"));").click();
	
	driver.findElementByAndroidUIAutomator("text(\"Let's  Shop\")").click();
	
	//To Verify Toast Message
	String sbc= driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
	BaseClass.getScreenshot("toastmsg");
	System.out.println(sbc);
	
	driver.findElement(By.className("android.widget.EditText")).sendKeys("Rizwan");
	driver.hideKeyboard();
	WebElement str= driver.findElementByAndroidUIAutomator("text(\"Male\")");
	boolean bol= str.isSelected();
	System.out.println(bol);
	if (bol==true)
	{
		System.out.println("Male is already Selected");
		driver.findElementByAndroidUIAutomator("text(\"Femaile\")").click();
	}
	driver.findElementByAndroidUIAutomator("text(\"Let's  Shop\")").click();
	WebDriverWait wait =new WebDriverWait(driver,10);
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Products']")));
	
	//Product Page
	
	driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"PG 3\"));");
//	driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\"))."
//			+ "scrollIntoView(new UiSelector().textMatches(\"LeBron Soldier 12\").instance(0))");
	int count= driver.findElementsById("com.androidsample.generalstore:id/productName").size();
	System.out.println(count);
	TouchAction ta=new TouchAction(driver);
	for(int i=0;i<count;i++)
	{
		String text= driver.findElementsById("com.androidsample.generalstore:id/productName").get(i).getText();
		if (text.equalsIgnoreCase("PG 3"))
		{
			System.out.println(text);
			WebElement web=driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(i);
			web.click();
//			ta.tap(tapOptions().withElement(element(web))).perform();
		}
	}
	
	String abd=driver.findElementByXPath("//android.widget.TextView[contains(@resource-id,'counterText')]").getText();
	System.out.println(abd);
	driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Cart']")));
	
	// Cart Page
	
	String productCat= driver.findElementById("com.androidsample.generalstore:id/productName").getText();
//	Assert.assertEquals("PG 3", productCat);
	
	WebElement terms=driver.findElementByXPath("//android.widget.TextView[@text='Please read our terms of conditions']");
	ta.longPress(longPressOptions().withElement(element(terms)).withDuration(ofSeconds(2))).release().perform();
	String msg=driver.findElementById("android:id/message").getText();
	System.out.println(msg);
	driver.findElement(By.xpath("//android.widget.Button[@text='CLOSE']")).click();
//	service.stop();
	//TODO's thie code needs to be made more generic as emulaor value is hard coded here
	//	closeEmulator("emulator");
	}
	
}

