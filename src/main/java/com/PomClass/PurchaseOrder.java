package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseOrder {

	
	public static WebDriver driver;
	
	@FindBy(id="Create")
	public WebElement AddPurchaseOrder;
	
	@FindBy(id = "clear")
	public WebElement Clear;
	
	@FindBy(id="select2-VendorId-container")
	public WebElement Vendor;
	
	@FindBy(xpath="(//span[@id='select2-VendorId-container']//following::input[@type='search'])[1]")
	public WebElement VendorSearch;
	
	@FindBy(id="select2-GSTTypeId-container")
	public WebElement GSTType;
	
	@FindBy(xpath="//span[@id='select2-GSTTypeId-container']//following::input[@type='search']")
	public WebElement GSTTypeSearch;
	
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
	public WebElement ChooseproductName;
	
	@FindBy(xpath="(//span[@id='select2-ProductId-container']//following::input[@type='search'])[1]")
	public WebElement ProductSearch;
	
	@FindBy(id="select2-UOMId-container")
	public WebElement UOM;
	
	@FindBy(xpath="(//span[@id='select2-UOMId-container']//following::input[@type='search'])[1]")
	public WebElement UOMSearch;
	
	@FindBy(id ="Qty")
	public WebElement Quantity;
	
	@FindBy(id = "IsLooseFOC")
	public WebElement IsFoc;
	
	@FindBy(id = "FOCQty")
	public WebElement Foc;
	
	@FindBy(id="Price")
	public WebElement SGD;
	
	@FindBy(id="DetailDiscountPercentage")
	public WebElement DiscountPercentage;
	
	@FindBy(id="DiscountAmt")
	public WebElement DiscountAmount;
	
	@FindBy(id = "IsUnitDisc")
	public WebElement IsUnitDisc;
	
	@FindBy(id="PurchaseOrderDetailTotal")
	public WebElement TotalPrice;
	
	@FindBy(id = "DiscountType")
	public WebElement OverAllDiscountType;
	
	@FindBy(id = "Discount")
	public WebElement OverAllDiscount;
	
	@FindBy(id="btn_add")
	public WebElement AddButton;
	
	@FindBy(id="Create")
	public WebElement SaveButton;
	
	@FindBy(id="ConvertInvoice")
	public WebElement ConvertInvoice;
	
	@FindBy(id="ReferenceNo")
	public WebElement InvoiceNo;
	
	@FindBy(id="popup_ok")
	public WebElement PopupAlertOk;
	
	@FindBy(xpath = "//a[text()='Copy Order']")
	public WebElement CopyOrder;
	
	@FindBy(xpath = "//select[@name='POtable_length']//following-sibling::option[4]")
	public WebElement ShowTableLength;
	
	@FindBy(id = "FindOrderNo")
	public WebElement FindOrderNo;
	
	@FindBy(id = "BatchNumber")
	public WebElement BatchNo;
	
	@FindBy(xpath = "//input[@class='ManufactureDate datepick form-control hasDatepicker']")
	public WebElement MfgDate;
	
	@FindBy(id = "ValidPeriod")
	public WebElement ValidPeriodInMonth;
	
	@FindBy(xpath = "//input[@class='ExpiryDate datepick form-control hasDatepicker']")
	public WebElement ExpDate;
	
	@FindBy(id = "BQty")
	public WebElement BQty;
	
	@FindBy(id = "LQty")
	public WebElement LQty;
	
	@FindBy(xpath = "(//button[text()='Add'])[2]")
	public WebElement Add;
	
	@FindBy(id = "ConvertGRN")
	public WebElement ConvertGRN;
	
	@FindBy(id = "Create")
	public WebElement Save;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
		
	
	public PurchaseOrder(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
}
