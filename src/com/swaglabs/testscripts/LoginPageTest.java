package com.swaglabs.testscripts;

import java.util.List; 

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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

public class LoginPageTest extends Browser {

	@Parameters("browsername")
	@BeforeMethod(alwaysRun=true)
	public void setup(String browsername)
	{
		launchBrowser(browsername);
		openURL();
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		
		closeBrowser();
	}
	
	@Test(enabled=true,groups="smoke")
	public void verifyLoginBtnFunctionalityWithBlankUsernameAndBlankPassword()
	{
		Loginpage loginpage = new Loginpage(driver, "loginpage");
		loginpage.clickLoginBtn();
		String actualErrorMsg=loginpage.getActualErrorMsg();
		Assert.assertEquals(actualErrorMsg, DataConfigConstants.ERRORMSG_USERNAMEBLANK);
		Screenshots.takePicture(driver);
		}
	
	@Test(enabled=true,groups="sanity")
	public void verifyLoginBtnFunctionalityWithBlankPassword()
	{
		ReadData read =  new ReadData("loginpage");
		String uname=read.getData(1,0);
		Loginpage loginpage = new Loginpage(driver, "loginpage");
		loginpage.setUsername(uname);
		loginpage.clickLoginBtn();
		String actualErrorMsg=loginpage.getActualErrorMsg();
		Assert.assertEquals(actualErrorMsg, DataConfigConstants.ERRORMSG_PASSWORDBLANK);
		Screenshots.takePicture(driver);
		
	}
	
	@Test(enabled=true,groups="sanity")
	public void verifyLoginBtnFunctionalityInvalidUsernameAndInvalidPassword()
	{
		ReadData read =  new ReadData("loginpage");
		List<String> data=read.getData(10);
		String uname=data.get(0);
		String pass=data.get(1);
		Loginpage loginpage = new Loginpage(driver, "loginpage");
		loginpage.setUsername(uname);
		loginpage.setPassword(pass);
		loginpage.clickLoginBtn();
		String actualErrorMsg=loginpage.getActualErrorMsg();
		Assert.assertEquals(actualErrorMsg, DataConfigConstants.ERRORMSG_INVALIDUSERNAME_PASSWORD);
		Screenshots.takePicture(driver);
		
	}
	
	@Test(enabled=true,groups="smoke")
	public void verifyLoginBtnFunctionalityWithLockedUser()
	{
		ReadData read =  new ReadData("loginpage");
		List<String> data=read.getData(2);
		String uname=data.get(0);
		String pass=data.get(1);
		Loginpage loginpage = new Loginpage(driver, "loginpage");
		loginpage.setUsername(uname);
		loginpage.setPassword(pass);
		loginpage.clickLoginBtn();
		String actualErrorMsg=loginpage.getActualErrorMsg();
		Assert.assertEquals(actualErrorMsg, DataConfigConstants.ERRORMSG_LOCKED_USER);
		Screenshots.takePicture(driver);
		
	}
	
	@Test(enabled=true,groups="sanity")
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
	
	@Test(enabled=true,dataProvider="logininfo",groups="smoke")
	public void verifyLoginBtnFunctionalityWithValidUser(String uname,String pass)
	{
		
		Loginpage loginpage = new Loginpage(driver, "loginpage");
		loginpage.setUsername(uname);
		loginpage.setPassword(pass);
		loginpage.clickLoginBtn();
		Productspage productspage = new Productspage(driver, "productspage");
		Assert.assertTrue(productspage.isProductLabelVisible(), "Product label is not visible");
		Screenshots.takePicture(driver);
		
	}
	
	@DataProvider(name="logininfo")
	public String[][] logindata()
	{
		ReadData read = new ReadData("loginpage");
		return read.getData();
		
	}
		
	}
