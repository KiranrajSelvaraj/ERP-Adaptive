package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesInvoice {
	
	public static WebDriver driver;
	
	@FindBy(id = "Create")
	public WebElement AddInvoice;
	
	@FindBy(id = "FindCustomer")
	public WebElement FindCustomer;
	
	@FindBy(id = "FindInvoiceNumber")
	public WebElement FindInvoiceNumber;
	
	@FindBy(id = "searchstring")
	public WebElement Fetch;
	
	@FindBy(id = "clear")
	public WebElement clear;
	
	@FindBy(id = "select2-CustomerId-container")
	public WebElement Customer;
	
	@FindBy(id = "select2-GSTTypeId-container")
	public WebElement GstType;
	
	@FindBy(id = "select2-CurrencyId-container")
	public WebElement CurrencyCode;
	
	@FindBy(id = "Date")
	public WebElement Date;
	
	@FindBy(id = "select2-PaymentTermsId-container")
	public WebElement Terms;
	
	@FindBy(id = "select2-ProductId-container")
	public WebElement ChooseProduct;
	
	@FindBy(id = "select2-UOMId-container")
	public WebElement ChooseUom;
	
	@FindBy(id = "ItemQty")
	public WebElement Qty;
	
	@FindBy(id = "ItemPrice")
	public WebElement Price;
	
	@FindBy(id = "btn_add")
	public WebElement Add;
	
	@FindBy(id = "Create")
	public WebElement Save;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
	
	@FindBy(id = "btnDotMatrix")
	public WebElement DotMatrix;
	
	@FindBy(id = "IssueCreditNote")
	public WebElement IssueCreditNote;
	
	@FindBy(id = "popup_ok")
	public WebElement PopupOk;
	
	@FindBy(id = "popup_no")
	public WebElement PopupNo;
	
	@FindBy(id = "popup_cancel")
	public WebElement PopupCancel;
	
	
	
	
	
	public SalesInvoice (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
