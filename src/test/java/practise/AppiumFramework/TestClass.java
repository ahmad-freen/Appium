package practise.AppiumFramework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClass {
	@Test
	public void firstMethod()
	{
		System.out.println("i am running from test class 1");
	}
	@Test
	public void secondMethod()
	{
		String abc= "Hello";
		System.out.println("i am running from test class 2");
		Assert.assertEquals(abc, "Helloo");
	}
}
