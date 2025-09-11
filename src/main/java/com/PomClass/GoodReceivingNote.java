package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoodReceivingNote {
	
	public static WebDriver driver;
	
	@FindBy(id = "MultipleToSingleInvoice")
	public WebElement MultipleConvert;
	
	@FindBy(id = "CreateLP")
	public WebElement CreateLP;
	
	@FindBy(id = "GRNNo")
	public WebElement FindGRNNo;
	
	@FindBy(id = "SearchString")
	public WebElement FindVendor;
	
	@FindBy(id = "searchstring")
	public WebElement Fetch;
	
	@FindBy(id = "clear")
	public WebElement Clear;
	
	@FindBy(id = "Complete")
	public WebElement Complete;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
	
	@FindBy(id = "popup_ok")
	public WebElement AlertOK;
	
	@FindBy(id = "ConvertInvoice")
	public WebElement ConvertInvoice;
	
	
	public GoodReceivingNote(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
