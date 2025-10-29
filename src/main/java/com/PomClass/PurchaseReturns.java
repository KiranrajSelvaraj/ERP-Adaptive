package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseReturns {
	
	public static WebDriver driver;
	
	//Index:-
	@FindBy(xpath = "//input[@id='Create']")
	public WebElement AddPurchaseReturn;
	
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
	public WebElement ReferenceNo;
	
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
	
	@FindBy(id = "select2-ProductId-container")
	public WebElement ChooseProduct;
	
	@FindBy(id = "select2-UOMId-container")
	public WebElement Uom;
	
	@FindBy(xpath = "//input[@id='Qty']")
	public WebElement Qty;
	
	@FindBy(id = "divIsLooseFOC")
	public WebElement Foc;
	
	@FindBy(xpath = "//input[@id='Price']")
	public WebElement Price;
	
	@FindBy(id = "DetailDiscountPercentage")
	public WebElement DiscountPercentage;
	
	@FindBy(id = "DiscountAmt")
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
	
	@FindBy(xpath = "//input[@value='Delete']")
	public WebElement Delete;
	
	@FindBy(id = "popup_ok")
	public WebElement PopupOk;
	
	@FindBy(id = "popup_cancel")
	public WebElement PopupCancel;
	
	@FindBy(id = "btnhold")
	public WebElement Hold;
	
	@FindBy(xpath = "//button[@id='btnPrint']")
	public WebElement Print;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
	
	
	public PurchaseReturns(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
