package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;

import com.swaglabs.utils.SmartFunctions;

public class Loginpage extends SmartFunctions {

	public Loginpage(WebDriver driver, String pagename) {
		super(driver, pagename);						
		}
	public void setUsername(String uname)
	{
		enterText("username",uname);
	}
	public void setPassword(String  pass)
	{
		enterText("password",pass);
	}
	public void clickLoginBtn()
	{
		click("loginbtn");
	}
	public String getActualErrorMsg()
	{
		return getText("errorMsg");
	}

}
