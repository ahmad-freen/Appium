package practise.AppiumFramework;

import java.util.Set;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilities
{
	AndroidDriver<AndroidElement> driver;

	public Utilities(AndroidDriver<AndroidElement> driver)
	{
		this.driver=driver;
	}

	
//	 This code is to get and switch context of  HybridApplication.
	public void getContextA() throws InterruptedException
	{	
		Thread.sleep(7000);
		Set<String> contextNames=driver.getContextHandles();
		System.out.println(contextNames);
		for (String contextName : contextNames) 
			{
            	            	
            	if (!contextName.equals("NATIVE_APP") ) 
            		{	            		
            			System.out.println(contextName+"A");
            			driver.context(contextName); //set context to WEBVIEW_
            			break;
            		}
			}
       System.out.println("execute context switch successfully!!!  "
               + contextNames.toArray()[1]);
		
	}
	// below function is to scroll upto the element to view
	/*	text= provide text value to scroll up-to the text to appear in the screen.
	 * 
	 */
	public void scrollToView(String text)
	{
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
//		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\"))."
//				+ "scrollIntoView(new UiSelector().textMatches(\"LeBron Soldier 12\").instance(0))");
	}
}
