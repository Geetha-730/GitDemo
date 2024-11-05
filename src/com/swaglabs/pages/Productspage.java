package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;

import com.swaglabs.utils.SmartFunctions;

public class Productspage extends SmartFunctions {

	public Productspage(WebDriver driver, String pagename) {
		super(driver, pagename);
		}
	public boolean isProductLabelVisible()
	{
		return isElementVisible("productsLabel");
	}
	
	public boolean orderItem()
	{
		click("addtocartBtn");
		click("cartIcon");
		click("checkoutBtn");
		enterText("firstName","Geetha");
		enterText("lastName","B");
		enterText("postalcode","560061");
		click("continueBtn");
		click("finishBtn");
		return isElementVisible("thankyouheader");
	}
	

}
