package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesReturn {
	
	public static WebDriver driver;
	
	@FindBy(id = "Create")
	public WebElement AddSalesReturn;
	
	@FindBy(id = "FindSalesReturnNo")
	public WebElement FindSalesReturnNo;
	
	@FindBy(id = "searchstring")
	public WebElement Fetch;
	
	@FindBy(id = "clear")
	public WebElement Clear;
	
	@FindBy(xpath = "//select[@name='salesreturntable_length']//following-sibling::option[4]")
	public WebElement ShowTableLength;
	
	@FindBy(id = "convertcreditnote")
	public WebElement convertcreditnote;
	
	@FindBy(xpath = "//a[text()='Copy Return']")
	public WebElement CopyReturn;
	
	@FindBy(id = "IssueCreditNote")
	public WebElement IssueCreditNote;
	
	@FindBy(id = "popup_ok")
	public WebElement AlertOK;
	
	@FindBy(id = "Create")
	public WebElement Save;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
	
	@FindBy(xpath = "(//button[text()='Add'])[2]")
	public WebElement Add;
	
	
	public SalesReturn (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
