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
	
	@FindBy(id = "select2-CustomerId-container")
	public WebElement Customer;
	
	@FindBy(id = "select2-ProductId-container")
	public WebElement Product;
	
	@FindBy(id = "select2-UOMId-container")
	public WebElement Uom;
	
	@FindBy(id = "ItemQty")
	public WebElement Qty;
	
	@FindBy(id = "btn_add")
	public WebElement Add;
	
	@FindBy(xpath = "//button[@id='Create' and @value='Save & Close']")
	public WebElement SaveandClose;
	
	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm dropdown-toggle']")
	public WebElement DropDown;
	
	@FindBy(id = "Save3")
	public WebElement Save;
	
	@FindBy(id = "popup_ok")
	public WebElement PopupOk;
	
	
	
	public DeliveryOrder(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}

}
