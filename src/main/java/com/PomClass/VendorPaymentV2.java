package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class VendorPaymentV2 {
	
	public static WebDriver driver;
	
	//Index
	@FindBy(id = "setting")
	public WebElement Setting;
	
	@FindBy(id = "Create")
	public WebElement CreateNew;
	
	@FindBy(xpath = "//input[@placeholder='From Date']")
	public WebElement FromDate;
	
	@FindBy(xpath = "//input[@placeholder='To Date']")
	public WebElement ToDate;
	
	@FindBy(id = "a_VendorPaymentNumber")
	public WebElement VendorPaymentNo;
	
	@FindBy(id = "select2-b_VendorName-container")
	public WebElement IndexVendor;
	
	@FindBy(id = "select2-Status-container")
	public WebElement VendorPaymentStatus;
	
	@FindBy(id = "filter_load")
	public WebElement Fetch;
	
	@FindBy(id = "filter_clear")
	public WebElement Clear;
	
	//Edit
	@FindBy(id = "select2-VendorId-container")
	public WebElement Vendor;
	
	@FindBy(id = "PaymentDate")
	public WebElement PaymentDate;
	
	@FindBy(id = "Remarks")
	public WebElement Remarks;
	
	@FindBy(id = "select2-InvoiceCurrencyId-container")
	public WebElement InvoiceCurrency;
	
	@FindBy(id = "IsPaymentAccount")
	public WebElement PaymentToAccount;
	
	@FindBy(id = "select2-CurrencyId-container")
	public WebElement PaymodeCurrency;
	
	@FindBy(id = "PaymentToAccountCurrencyRate")
	public WebElement Rate;
	
	@FindBy(id = "select2-PaymodeId-container")
	public WebElement Paymode;
	
	@FindBy(id = "checkall")
	public WebElement CheckBoxAll;
	
	@FindBy(id = "saveBtn")
	public WebElement Save;
	
	@FindBy(id = "btnPrint")
	public WebElement Print;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
	
	@FindBy(id = "popup_ok")
	public WebElement PopupOk;
	
	
	public VendorPaymentV2(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	

}
