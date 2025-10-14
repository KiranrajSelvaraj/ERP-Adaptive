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
	
	@FindBy(id = "select2-CustomerId-container")
	public WebElement Customer;
	
	@FindBy(id = "select2-GSTTypeId-container")
	public WebElement GstType;
	
	@FindBy(id = "ProductCheck")
	public WebElement ProductCheck;
	
	@FindBy(id = "select2-ProductId-container")
	public WebElement ChooseProduct;
	
	@FindBy(id = "ServiceCheck")
	public WebElement ServiceCheck;
	
	@FindBy(id = "select2-ServiceId-container")
	public WebElement ChooseService;
	
	@FindBy(id = "OpenCheck")
	public WebElement OpenCheck;
	
	@FindBy(id = "IsZeroRatedGST")
	public WebElement IsZeroRatedGSTCheckbox;
	
	@FindBy(id = "OpenItem")
	public WebElement ChooseOpen;
	
	@FindBy(id = "select2-UOMId-container")
	public WebElement Uom;
	
	@FindBy(id = "ItemQty")
	public WebElement Qty;
	
	@FindBy(id = "IsLooseFOC")
	public WebElement FocCheckbox;
	
	@FindBy(id = "FOC")
	public WebElement Foc;
	
	@FindBy(id = "ItemPrice")
	public WebElement Price;
	
	@FindBy(xpath = "//button[@id='btn_add']")
	public WebElement Add;
	
	@FindBy(id = "DiscountType")
	public WebElement OverAllDiscountType;
	
	@FindBy(id = "convertcreditnote")
	public WebElement convertcreditnote;
	
	@FindBy(xpath = "//a[text()='Copy Return']")
	public WebElement CopyReturn;
	
	@FindBy(id = "IssueCreditNote")
	public WebElement IssueCreditNote;
	
	@FindBy(id = "popup_ok")
	public WebElement PopupOk;
	
	@FindBy(id = "Create")
	public WebElement Save;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
	
	
	public SalesReturn (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
