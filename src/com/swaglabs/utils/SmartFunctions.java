package com.swaglabs.utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SmartFunctions {
	private WebDriver driver;
	private ReadLocator read;
	public SmartFunctions(WebDriver driver, String pagename)
	{
		this.driver=driver;
		 read = new ReadLocator(pagename);
	}
	
	protected WebElement getElement(String elementname)
	{
		String locator=read.getLocator(elementname);
		if(locator.charAt(0)=='/'||locator.charAt(0)=='(') {
			return driver.findElement(By.xpath(locator));	
		}
		else
		{
			try {
				return driver.findElement(By.id(locator));
			}
			catch(NoSuchElementException e)
			{
				return driver.findElement(By.name(locator));
			}
		}
	}
	protected List<WebElement> getElements(String elementname)
	{
		String locator=read.getLocator(elementname);
		if(locator.charAt(0)=='/') {
		return driver.findElements(By.xpath(locator));
	}
		else
		{
			try {
				return driver.findElements(By.id(locator));
				}
			catch(NoSuchElementException e)
			{
				return driver.findElements(By.name(locator));
			} 
		}
		}
	
	protected void enterText(String elementname,String data)
	{
		getElement(elementname).sendKeys(data);
		Screenshots.takePicture(driver);
	}
	
	protected void click(String elementname)
	{
		getElement(elementname).click();
		Screenshots.takePicture(driver);
	}
	
	protected  String getText(String elementname)
	{
		String data=  getElement(elementname).getText();
		Screenshots.takePicture(driver);
		return data;
	}
	protected boolean isElementVisible(String elementname)
	{
		try {
			return getElement(elementname).isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
