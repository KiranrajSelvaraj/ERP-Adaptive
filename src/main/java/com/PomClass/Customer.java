package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Customer {
	
	public static WebDriver driver;
	
	//index
	@FindBy(xpath = "//input[@value='[+] Add Customer']")
	public WebElement AddCustomer;
	
	@FindBy(id = "import")
	public WebElement ImportExcel;
	
	@FindBy(id = "select2-FindSalesman-container")
	public WebElement IndexSalesman;
	
	@FindBy(xpath = "//input[@placeholder='Find a code/name/cont. name/number']")
	public WebElement SearchField;
	
	@FindBy(xpath = "//input[@id='searchstring']")
	public WebElement Fetch;
	
	@FindBy(id = "clear")
	public WebElement Clear;
	
	//edit
	@FindBy(xpath = "//input[@id='Name']")
	public WebElement CustomerName;
	
	@FindBy(id = "AliasName")
	public WebElement AliasName;
	
	@FindBy(id = "RegNo")
	public WebElement CompanyRegNo;
	
	@FindBy(id = "IsGST")
	public WebElement GstCheckBox;
	
	@FindBy(id = "select2-GSTTypeId-container")
	public WebElement GstType;
	
	@FindBy(id = "IsBlocked")
	public WebElement IsBlockedCheckBox;
	
	@FindBy(id = "CustomerRemarks")
	public WebElement CustomerRemarks;
	
	@FindBy(id = "CustomerNote")
	public WebElement CustomerNote;
	
	@FindBy(id = "select2-PaymentTermsId-container")
	public WebElement CreditTerms;
	
	@FindBy(id = "select2-ToleranceId-container")
	public WebElement TermsTolerance;
	
	@FindBy(id = "BankName")
	public WebElement BankName;
	
	@FindBy(id = "BankAccountNo")
	public WebElement BankAccountNo;
	
	@FindBy(id = "Website")
	public WebElement Website;
	
	@FindBy(id = "select2-ChartOfAccountsId-container")
	public WebElement ARAccount;
	
	@FindBy(id = "select2-CurrencyId-container")
	public WebElement Currency;
	
	@FindBy(id = "CreditLimit")
	public WebElement CreditLimit;
	
	@FindBy(id = "IsCustomerHQ")
	public WebElement IsCustomerHQCheckBox;
	
	@FindBy(id = "IsDiscount")
	public WebElement AllowBillDiscountCheckBox;
	
	//details
	@FindBy(xpath = "//a[text()='Info']")
	public WebElement InfoTab;
	
	@FindBy(xpath = "//a[@class='LocationTab']")
	public WebElement LocationTab;
	
	@FindBy(xpath = "//a[@class='ProjectTab']")
	public WebElement ProjectTab;
	
	@FindBy(xpath = "//a[@class='SalesQuotationTab']")
	public WebElement SalesQuotationTab;
	
	@FindBy(xpath = "//a[@class='SalesOrderTab']")
	public WebElement SalesOrderTab;
	
	@FindBy(xpath = "//a[@class='DeliveryOrderTab']")
	public WebElement DeliveryOrderTab;
	
	@FindBy(xpath = "//a[@class='SalesInvoiceTab']")
	public WebElement SalesInvoiceTab;
	
	@FindBy(xpath = "//a[@class='CreditNoteTab']")
	public WebElement CreditNoteTab;
	
	@FindBy(xpath = "//a[@class='ServiceJobCustomer']")
	public WebElement ServiceJobTab;
	
	@FindBy(xpath = "//a[@class='TransactionDetailsTab']")
	public WebElement TransactionDetailsTab;
	
	@FindBy(xpath = "//a[text()='Receive Payment']")
	public WebElement ReceivePayment;
	
	@FindBy(id = "from")
	public WebElement FromDate;
	
	@FindBy(id = "")
	public WebElement ToDate;
	
	@FindBy(id = "invoiceFetch")
	public WebElement InvoiceFetch;
	
	//hq
	@FindBy(id = "LocationCode")
	public WebElement LocationCode;
	
	@FindBy(id = "LocationName")
	public WebElement LocationName;
	
	@FindBy(id = "select2-SalesManId-container")
	public WebElement EditSalesman;
	
	@FindBy(id = "select2-DeliveryManId-container")
	public WebElement EditDeliveryman;
	
	@FindBy(id = "select2-ScheduleDay-container")
	public WebElement ScheduleDay;
	
	@FindBy(id = "select2-CountryId-container")
	public WebElement Country;
	
	@FindBy(id = "select2-DeliveryCountryId-container")
	public WebElement DeliveryCountry;
	
	@FindBy(xpath = "//button[@value='Save & Close']")
	public WebElement Save;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
	
	

	public Customer(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

}
