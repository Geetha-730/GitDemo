package com.swaglabs.testscripts;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.swaglabs.pages.Loginpage;
import com.swaglabs.pages.Productspage;
import com.swaglabs.utils.Browser;
import com.swaglabs.utils.DataConfigConstants;
import com.swaglabs.utils.ReadData;
import com.swaglabs.utils.Screenshots;

public class EndToEndScenario extends Browser {

	@Parameters("browsername")
	@BeforeClass(alwaysRun=true)
	public void setup(String browsername)
	{
		launchBrowser(browsername);
		openURL();
	}
	
	@AfterClass(alwaysRun=true)
	public void teardown()
	{
		
		closeBrowser();
	}
	
	@Test(enabled=true,priority=1,groups="smoke")
	public void verifyLoginBtnFunctionalityWithValidUser()
	{
		ReadData read = new ReadData("loginpage");
		List<String> data=read.getData(1);
		String uname=data.get(0);
		String pass=data.get(1);
		Loginpage loginpage = new Loginpage(driver, "loginpage");
		loginpage.setUsername(uname);
		loginpage.setPassword(pass);
		loginpage.clickLoginBtn();
		Productspage productspage = new Productspage(driver, "productspage");
		Assert.assertTrue(productspage.isProductLabelVisible(), "Product label is not visible");
		Screenshots.takePicture(driver);
		
	}
	@Test(priority=2,dependsOnMethods="verifyLoginBtnFunctionalityWithValidUser",groups="smoke") 
	public void verifyE2EOrderItemFlow()
	{
		Productspage productspage = new Productspage(driver, "productspage");
		boolean status=productspage.orderItem();
		Assert.assertTrue(status, "Thank you msg not displayed");

	}
	}








