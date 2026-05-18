package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerPaymentAnalysis {
	
	public static WebDriver driver;
	
	//index
	@FindBy(id = "fromDate")
	public WebElement FromDate;
	
	@FindBy(id = "toDate")
	public WebElement ToDate;
	
	@FindBy(id = "select2-CustomerId-container")
	public WebElement Customer;
	
	@FindBy(id = "searchstring")
	public WebElement Fetch;
	
	@FindBy(id = "clear")
	public WebElement clear;
	
	@FindBy(id = "print")
	public WebElement print;
	
	@FindBy(id = "btnExport")
	public WebElement Excel;
	
	public CustomerPaymentAnalysis (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

}
