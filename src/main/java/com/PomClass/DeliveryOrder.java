package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeliveryOrder {
	
	public static WebDriver driver;
	
	@FindBy(id = "Create")
	public WebElement AddOrder;
	
	@FindBy(id = "MultipleToSingleInvoice")
	public WebElement ConvertSalesInvoice;
	
	@FindBy(id = "FindCustomer")
	public WebElement FindCustomerDONo;
	
	@FindBy(id = "searchstring")
	public WebElement Fetch;
	
	@FindBy(id = "clear")
	public WebElement Clear;
	
	@FindBy(xpath = "//select[@name='DeliveryOrderTable_length']//following-sibling::option[4]")
	public WebElement ShowTableLength;
	
	@FindBy(xpath = "//a[text()='Copy Order']")
	public WebElement CopyOrder;
	
	@FindBy(id = "Create")
	public WebElement Save;
	
	
	
	public DeliveryOrder(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}

}
