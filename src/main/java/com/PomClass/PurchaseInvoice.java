package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class PurchaseInvoice {
	
	public static WebDriver driver;
	
	//Index:-
	@FindBy(xpath = "//input[@id='Create']")
	public WebElement AddPurchaseInvoice;
	
	@FindBy(xpath = "//input[@id='Post']")
	public WebElement Post;
	
	@FindBy(id = "select2-CurrencyCode-container")
	public WebElement AllCurrency;
	
	@FindBy(id = "FindVendor")
	public WebElement FindVendor;
	
	@FindBy(id = "FindTransactionNo")
	public WebElement FindTransactionNo;
	
	@FindBy(id = "from")
	public WebElement FromDate;
	
	@FindBy(id = "to")
	public WebElement ToDate;
	
	@FindBy(id = "Status")
	public WebElement AllStatus;
	
	@FindBy(id = "searchstring")
	public WebElement Fetch;
	
	@FindBy(xpath = "//button[@id='clear']")
	public WebElement Clear;
	
	//Edit:-
	@FindBy(id = "select2-VendorId-container")
	public WebElement Vendor;
	
	@FindBy(xpath = "//input[@id='ReferenceNo']")
	public WebElement InvoiceNo;
	
	@FindBy(id = "select2-CurrencyId-container")
	public WebElement CurrencyCode;
	
	@FindBy(id = "ProductCheck")
	public WebElement ProductCheckBox;
	
	@FindBy(id = "ServiceCheck")
	public WebElement ServiceCheckBox;
	
	@FindBy(id = "OpenCheck")
	public WebElement OpenCheckBox;
	
	@FindBy(id = "OpenItem")
	public WebElement OpenProduct;
	
	@FindBy(id="select2-ProductId-container")
	public WebElement Chooseproduct;
	
	@FindBy(id = "select2-UOMId-container")
	public WebElement Uom;
	
	@FindBy(id = "//input[@id='Qty']")
	public WebElement Qty;
	
	@FindBy(id = "FOCQty")
	public WebElement Foc;
	
	@FindBy(xpath = "//input[@id='Price']")
	public WebElement Price;
	
	@FindBy(id = "DetailDiscountPercentage")
	public WebElement DiscountPercentage;
	
	@FindBy(id = "DiscAmount")
	public WebElement DiscountAmount;
	
	@FindBy(id = "IsUnitDisc")
	public WebElement IsUnitDiscCheckbox;
	
	@FindBy(id = "btn_add")
	public WebElement Add;
	
	@FindBy(id = "DiscountType")
	public WebElement OverAllDiscountType;
	
	@FindBy(id = "Discount")
	public WebElement OverAllDiscount;
	
	@FindBy(xpath = "//button[@id='Create']")
	public WebElement Save;
	
	@FindBy(id = "btnhold")
	public WebElement Hold;
	
	@FindBy(xpath = "//button[@id='btnPrint']")
	public WebElement Print;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
	
	//Details:-
	@FindBy(id = "EditDetails")
	public WebElement InfoEdit;
	
	@FindBy(xpath = "//a[text()='Payment']")
	public WebElement Payment;
	
	@FindBy(xpath = "//a[text()='Copy Invoice']")
	public WebElement CopyInvoice;
	
	@FindBy(xpath = "//input[@id='purchasereturn']")
	public WebElement PurchaseReturn;
	
	@FindBy(id = "popup_ok")
	public WebElement PopupOk;
	
	@FindBy(id = "popup_cancel")
	public WebElement PopupCancel;
	
	
	
	
	public PurchaseInvoice(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

}
