package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SmmPI {
	public static WebDriver driver;

	@FindBy(xpath = "/html/body/div[1]/div[2]/section[2]/div/div/div/div/a/div[1]")
	public WebElement SMM;

	@FindBy(xpath = "//span[@id=\"select2-VendorId-container\"]")
	public WebElement Vendor;

	@FindBy(xpath = "//*[@id=\"select2-VendorId-container\"]//following::input[@type='search']")
	public WebElement VendorSearchbox;

	@FindBy(id = "ReferenceNo")
	public WebElement InvoiceNo;
	
	@FindBy(id = "ReferenceNo")
	public WebElement PurchaseSReturnNo;

	@FindBy(xpath = "//*[@id=\"select2-GSTTypeId-container\"]")
	public WebElement Gsttype;

	@FindBy(xpath = "//*[@id=\"select2-GSTTypeId-container\"]//following::input[@type='search']")
	public WebElement GsttypeSearchbox;

	@FindBy(xpath = "//*[@id=\"select2-CurrencyId-container\"]")
	public WebElement Currencycode;

	@FindBy(xpath = "//*[@id=\"select2-CurrencyId-container\"]//following::input[@type='search']")
	public WebElement CurrencycodeSearchbox;

	@FindBy(id = "TransactionDate")
	public WebElement TransactionDate;

	@FindBy(id = "ReferenceDate")
	public WebElement InvoiceDate;

	@FindBy(id = "PaymentTermsId")
	public WebElement Terms;

	@FindBy(id = "PaymentDueDate")
	public WebElement DueDate;

	@FindBy(id = "ExRate")
	public WebElement CurrencyRate;

	@FindBy(id = "ReferenceNo1")
	public WebElement ReferenceNo;

	@FindBy(xpath = "//span[@id=\"select2-WarehouseId-container\"]")
	public WebElement WareHouse;

	@FindBy(xpath = "//span[@id=\"select2-AccountDivisionId-container\"]")
	public WebElement AccountDivision;

	@FindBy(xpath = "//span[@id=\"select2-AccountDivisionId-container\"]//following::input[@type='search']")
	public WebElement AccountDivisionSearchbox;

	@FindBy(id = "ProductCheck")
	public WebElement Productcheckbox;

	@FindBy(xpath = "//span[@id=\"select2-ProductId-container\"]")
	public WebElement Product;

	@FindBy(xpath = "//span[@id=\"select2-ProductId-container\"]//following::input[@type='search']")
	public WebElement ProductSearchbox;

	@FindBy(id = "ServiceCheck")
	public WebElement ServiceCheckbox;

	@FindBy(xpath = "//span[@id=\"select2-ServiceId-container\"]")
	public WebElement Service;

	@FindBy(xpath = "//span[@id=\"select2-ServiceId-container\"]//following::input[@type='search']")
	public WebElement Servicesearchbox;

	@FindBy(id = "OpenCheck")
	public WebElement Open;

	@FindBy(id = "OpenItem")
	public WebElement Opensearchbox;

	@FindBy(id = "HeaderCheck")
	public WebElement Header;

	@FindBy(xpath = "//span[@id=\"select2-UOMId-container\"]")
	public WebElement UOM;

	@FindBy(xpath = "//span[@id=\"select2-UOMId-container\"]//following::input[@type='search']")
	public WebElement UOMsearchbox;

	@FindBy(xpath = "/html/body/span/span/span[1]/input")
	public WebElement UOMSearch;

	@FindBy(id = "Qty")
	public WebElement Qty;

	@FindBy(id = "FOC")
	public WebElement FOC;

	@FindBy(id = "Price")
	public WebElement Price;

	@FindBy(id = "IsUnitDisc")
	public WebElement UnitDiscCheckbox;

	@FindBy(id = "DetailDiscountPercentage")
	public WebElement DiscountPer;

	@FindBy(id = "DiscAmount")
	public WebElement DiscountAmt;
	
	@FindBy(id = "DiscountAmt")
	public WebElement PRDiscountAmt;

	@FindBy(id = "PurchaseDetailTotal")
	public WebElement Total;

	@FindBy(id = "btn_add")
	public WebElement Add;

	@FindBy(id = "BatchNumber")
	public WebElement batchtable;

	@FindBy(xpath = "//button[text()='Save & Close']")
	public WebElement SaveandClose;

	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;

	// Index page

	@FindBy(xpath = "//table[@id='purchasetable']")
	public WebElement Indexpurchasetable;

	@FindBy(xpath = "//table[@id='purchasetable']//tr//td[2]")
	public WebElement IndexTransactionDateallcolumns;

	@FindBy(xpath = "//table[@id='purchasetable']//tr//td[2]//following-sibling::td[4]")
	public WebElement IndexInvoicenoallcolumns;

	@FindBy(xpath = "//span[@id=\"select2-CurrencyCode-container\"]")
	public WebElement IndexCurrency;

	@FindBy(xpath = "(//span[@id=\"select2-CurrencyCode-container\"]//following::input[@type='search'])[2]")
	public WebElement IndexCurrencySearchbox;

	@FindBy(id = "clear")
	public WebElement Clear;

	@FindBy(id = "searchstring")
	public WebElement Fetch;

	@FindBy(id = "popup_ok")
	public WebElement Ok;
	
	@FindBy(id="FindOrderNumber")
	public WebElement FindOrderNo;

	public SmmPI(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

}
