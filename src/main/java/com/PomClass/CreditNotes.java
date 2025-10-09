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
	
	@FindBy(id = "select2-GSTTypeId-container")
	public WebElement GstType;
	
	@FindBy(id = "select2-CurrencyId-container")
	public WebElement CurrencyCode;
	
	@FindBy(id = "select2-SalesInvoiceReferenceNo-container")
	public WebElement InvoiceNo;
	
	@FindBy(id = "ProductCheck")
	public WebElement ProductCheckbox;
	
	@FindBy(id = "select2-ProductId-container")
	public WebElement ChooseProduct;
	
	@FindBy(id = "ServiceCheck")
	public WebElement ServiceCheckbox;
	
	@FindBy(id = "select2-ServiceId-container")
	public WebElement ServiceProduct;
	
	@FindBy(id = "OpenCheck")
	public WebElement OpenCheckbox;
	
	@FindBy(id = "OpenItem")
	public WebElement OpenProduct;
	
	@FindBy(id = "IsZeroRatedGST")
	public WebElement IsZeroRatedGSTCheckbox;
	
	@FindBy(id = "select2-UOMId-container")
	public WebElement ChooseUom;
	
	@FindBy(id = "ItemQty")
	public WebElement Qty;
	
	@FindBy(id = "IsLooseFOC")
	public WebElement IsLooseFocCheckbox;
	
	@FindBy(id = "FOC")
	public WebElement Foc;
	
	@FindBy(id = "ItemPrice")
	public WebElement Price;
	
	@FindBy(id = "DiscountPercentage")
	public WebElement DiscountPercentage;
	
	@FindBy(id = "DiscountAmt")
	public WebElement DiscountAmount;
	
	@FindBy(id = "IsUnits")
	public WebElement ItemLevelDiscCheckbox;
		
	@FindBy(id = "btn_add")
	public WebElement Add;
	
	@FindBy(id = "DiscountType")
	public WebElement OverAllDiscountType;
	
	@FindBy(id = "Discount")
	public WebElement OverAllDiscount;
	
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
