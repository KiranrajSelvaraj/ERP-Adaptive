package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Vendors {
	
	public static WebDriver driver;
	
	@FindBy(id="Create")
	public WebElement AddVendor;
	
	@FindBy(id="import")
	public WebElement ImportExcel;
	
	@FindBy(id="SearchString")
	public WebElement FindVendorCode;
	
	@FindBy(id="searchstring")
	public WebElement Fetch;
	
	@FindBy(id="clear")
	public WebElement Clear;
	
		
	@FindBy(id="Create")
	public WebElement Create;
	
	@FindBy(id="btnCreate")
	public WebElement Save;
	
	@FindBy(xpath="//input[@value='Back']")
	public WebElement Back;
	
	@FindBy(id = "popup_ok")
	public WebElement AlertOk;
	
	@FindBy(id="popup_cancel")
	public WebElement AlertCancel;
	
	// Vendor Create	
	@FindBy(id="Code")
	public WebElement Code;
	
	@FindBy(id="VendorName")
	public WebElement VendorName;
	
	@FindBy(id="RegNo")
	public WebElement RegistrationNo;
	
	@FindBy(id="select2-CurrencyId-container")
	public WebElement Currency;
	
	@FindBy(id="IsGST")
	public WebElement GSTRegistered;
	
	@FindBy(id="GSTRegNo")
	public WebElement GSTRegNo;
	
	@FindBy(id="select2-GSTTypeId-container")
	public WebElement GSTType;
	
	@FindBy(id="ContactPerson")
	public WebElement ContactPerson;
	
	@FindBy(id="MobileNo")
	public WebElement MobileNo;
	
	@FindBy(id="select2-VendorTypeId-container")
	public WebElement VendorType;
	
	@FindBy(id="IsNonTradeCreditors")
	public WebElement NonTradeCreditors;
	
	@FindBy(id="select2-ChartOfAccountsId-container")
	public WebElement APAccount;
	
	@FindBy(id="Remarks")
	public WebElement Remarks;
	
	@FindBy(id="Address1")
	public WebElement Address1;
	
	@FindBy(id="Address2")
	public WebElement Address2;
	
	@FindBy(id="Address3")
	public WebElement Address3;
	
	@FindBy(id="City")
	public WebElement City;
	
	@FindBy(id="select2-StateId-container")
	public WebElement State;
	
	@FindBy(id="select2-CountryId-container")
	public WebElement Country;
	
	@FindBy(id="PostalCode")
	public WebElement PostalCode;
	
	@FindBy(xpath="//a[text()='Contact']")
	public WebElement ContactTab;
	
	@FindBy(id="Phone1")
	public WebElement PhoneNo1;
	
	@FindBy(id="Phone2")
	public WebElement PhoneNo2;
	
	@FindBy(id="Phone3")
	public WebElement PhoneNo3;

	@FindBy(id="Fax")
	public WebElement Fax;
	
	@FindBy(id="URL")
	public WebElement URL;
	
	@FindBy(id="Email")
	public WebElement Email;
	
	@FindBy(id="paymentsTab")
	public WebElement paymentsTab;
	
	@FindBy(id="select2-PaymentTermsId-container")
	public WebElement PaymentTerms;
	
	@FindBy(id="DebitLimit")
	public WebElement DebitLimit;
	
	//Details
	@FindBy(id = "GeneralLedger")
	public WebElement GeneralLedger;
	
	@FindBy(xpath = "//input[@value='Edit']")
	public WebElement Edit;
	
	@FindBy(xpath = "//a[text()='Info']")
	public WebElement InfoTab;
	
	@FindBy(xpath = "//a[@class='ProductTab']")
	public WebElement ProductTab;
	
	@FindBy(xpath = "//a[@class='ServiceTab']")
	public WebElement ServiceTab;
	
	@FindBy(xpath = "//a[@class='PriceTab']")
	public WebElement PriceGroupTab;
	
	@FindBy(xpath = "//a[@class='PurchaseOrderTab']")
	public WebElement PurchaseOrderTab;
	
	@FindBy(xpath = "//a[@class='PurchaseInvoiceTab']")
	public WebElement PurchaseInvoiceTab;
	
	@FindBy(xpath = "//a[@class='PurchaseReturnTab']")
	public WebElement PuchaseReturnTab;
	
	@FindBy(xpath = "//a[text()='Transaction Details']")
	public WebElement TransactionDetailsTab;
	
	@FindBy(xpath = "Add Purchase")
	public WebElement AddPurchase;
	
	@FindBy(xpath = "//a[text()='Payment']")
	public WebElement Payment;
	
	@FindBy(id = "from")
	public WebElement FromDate;
	
	@FindBy(id = "to")
	public WebElement ToDate;
	
	@FindBy(id = "invoiceFetch")
	public WebElement DetailsInvoiceFetch;
	
	
	public Vendors(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

}
