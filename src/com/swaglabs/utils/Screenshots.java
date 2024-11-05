package com.swaglabs.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {
	
	private static File source;
	private static  String getCurrentDateTime(String pattern)
	{
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
		return localDateTime.format(format);
	}
	public static void takePicture(WebDriver driver) 
	{
		try {
		String filepath=".//targets//screenshots//"+getCurrentDateTime("yyyy_MM_dd_HH")+"//"+getCurrentDateTime("ddMMyyyyHHmmss")+".png";
		File target=new File(filepath);
		
		//Screenshot 
		TakesScreenshot ts= (TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(source, target);
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
