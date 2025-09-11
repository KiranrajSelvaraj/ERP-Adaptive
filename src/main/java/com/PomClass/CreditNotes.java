package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreditNotes {
	
	public static WebDriver driver;
	
	@FindBy(id = "Create")
	public WebElement AddCreditNote;
	
	@FindBy(id = "FindCreditNoteNo")
	public WebElement FindCreditNoteNo;
	
	@FindBy(id = "searchstring")
	public WebElement Fetch;
	
	@FindBy(id = "clear")
	public WebElement Clear;
	
	@FindBy(xpath = "//span[@id='select2-CustomerId-container']")
	public WebElement Customer;
	
	@FindBy(id = "select2-SalesInvoiceReferenceNo-container")
	public WebElement InvoiceNo;
	
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
	
	@FindBy(xpath = "//a[text()='Copy Credit Note']")
	public WebElement CopyCreditNote;
	
	@FindBy(id = "Create")
	public WebElement Save;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
	
	
	
	public CreditNotes (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
