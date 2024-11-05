package com.swaglabs.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {
private XSSFSheet sheet;
	public  ReadData(String filename)
	{
		String filepath=".\\resources\\testdata\\"+filename+".xlsx";
		try {
			FileInputStream instream = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(instream);
			 sheet=workbook.getSheet("Sheet1");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String getData(int rowNum, int cellNum)
	{
		return sheet.getRow(rowNum).getCell(cellNum).getStringCellValue();
	}
	
	public List<String> getData(int rowNum)
	{
		List <String> rowValues = new ArrayList<String>();
		int cellCount=sheet.getRow(rowNum).getLastCellNum();
		for (int i=0;i<cellCount;i++) {
		String data=sheet.getRow(rowNum).getCell(i).getStringCellValue();
		rowValues.add(data);
		}
		return rowValues;
	}
	
	public String[][] getData() {
		int rowCount=sheet.getLastRowNum();
		int cellCount=sheet.getRow(0).getLastCellNum();
		
		String resData[][]=new String[rowCount][cellCount];
		int k=0,l;
		for (int i = 1;i<=rowCount;i++)
		{
			l=0;
			for (int j=0;j<cellCount;j++)
			{
				String data=sheet.getRow(i).getCell(j).getStringCellValue();
				resData[k][l]=data;
				l++;
			}
			k++;
		}
		return resData;
	}
	public static void main(String[] args) {
		ReadData read = new ReadData("loginpage");
		String value[][]=read.getData();
		
		for (int i=0;i<value.length; i++) {
			for (int j=0;j<value[i].length; j++) {
				System.out.println(value[i][j]+" ");
			}
			System.out.println();
			}
	}
}
/*
Workbook
Sheet
rows
cell
data
*/