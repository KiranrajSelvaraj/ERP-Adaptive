package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Customer {
	public static WebDriver driver;

	@FindBy(id = "CompanyName")
	public WebElement companyname;

	@FindBy(id = "UserName")
	public WebElement username;

	@FindBy(id = "Password")
	public WebElement password;

	@FindBy(id = "login")
	public WebElement login;

	@FindBy(xpath = "(//a[@class='leave_page'])[3]")
	public WebElement customers;

	@FindBy(xpath = "//a[text()='Customer']")
	public WebElement customer;

	@FindBy(xpath = "//input[@value='[+] Add Customer']")
	public WebElement addcustomer;

	@FindBy(xpath = "//button[text()='Save & Close']")
	public WebElement saveandclose;

	@FindBy(xpath = "//button[@data-toggle='dropdown']")
	public WebElement saveandclosedropdown;

	@FindBy(id = "Create")
	public WebElement save;

	@FindBy(xpath = "//a[text()='Save & New']")
	public WebElement saveandnew;

	@FindBy(xpath = "//input[@value='Back']")
	public WebElement back;

	@FindBy(id = "Code")
	public WebElement code;

	@FindBy(id = "Name")
	public WebElement Name;

	@FindBy(id = "AliasName")
	public WebElement Alias;

	@FindBy(id = "RegNo")
	public WebElement Compregno;

	@FindBy(xpath = "//*[@id=\"CustomerCreateForm\"]/div[1]/fieldset/div[1]/div[1]/div[5]/div/div/div")
	public WebElement Isgstregcheckbox;

	@FindBy(xpath = "//input[@name='GSTRegistrationNo']")
	public WebElement Gstregno;

	@FindBy(xpath = "//*[@id=\"select2-GSTTypeId-container\"]")
	public WebElement Gsttype;

	@FindBy(xpath = "//li[text()='Exclusive']//preceding::li[text()='Inclusive']")
	public WebElement Gsttypetopvalue;

	@FindBy(xpath = "/html/body/span/span/span[1]/input")
	public WebElement Gsttypeinput;

	@FindBy(xpath = "//*[@id=\"CustomerCreateForm\"]/div[1]/fieldset/div[1]/div[1]/div[8]/div/div/div")
	public WebElement Isblockedcheckbox;

	@FindBy(id = "BlockedRemarks")
	public WebElement Blockedremarks;

	@FindBy(xpath = "//*[@id=\"select2-PaymentTermsId-container\"]")
	public WebElement Creditterms;

	@FindBy(xpath = "//li[text()='30 Days']//preceding::li[text()='COD']")
	public WebElement Credittermstopvalue;

	@FindBy(xpath = "/html/body/span/span/span[1]/input")
	public WebElement Credittermsinput;

	@FindBy(xpath = "//*[@id=\"select2-ToleranceId-container\"]")
	public WebElement termstolerance;

	@FindBy(xpath = "//li[text()='30 Days']//preceding::li[text()='COD']")
	public WebElement termstolerancetopvalue;

	@FindBy(xpath = "/html/body/span/span/span[1]/input")
	public WebElement Termstoleranceinput;

	@FindBy(id = "BankName")
	public WebElement Bankname;

	@FindBy(id = "BankAccountNo")
	public WebElement Bankaccno;

	@FindBy(id = "Website")
	public WebElement Website;

	@FindBy(xpath = "//span[text()='Default']")
	public WebElement Ratetype;

	@FindBy(xpath = "(//span[@role='presentation'])[4]")
	public WebElement Ratetypedropdown;

	@FindBy(xpath = "//span[@id='select2-ChartOfAccountsId-container']")
	public WebElement Araccount;

	@FindBy(xpath = "(//span[@role='presentation'])[5]")
	public WebElement Araccountdropdown;

	@FindBy(xpath = "//*[@id=\"CustomerCreateForm\"]/div[1]/fieldset/div[1]/div[2]/div[8]/div/div/div")
	public WebElement Isactivecheckbox;

	@FindBy(xpath = "//*[@id=\"select2-CurrencyId-container\"]")
	public WebElement Currency;

	@FindBy(id = "CreditLimit")
	public WebElement Creditlimit;

	@FindBy(id = "PeppolBuyerID")
	public WebElement Peppolbuyerid;

	@FindBy(xpath = "//*[@id=\"CustomerCreateForm\"]/div[1]/fieldset/div[1]/div[3]/div[4]/div/div/div")
	public WebElement ismultiloccheckbox;

	@FindBy(xpath = "//*[@id=\"CustomerCreateForm\"]/div[1]/fieldset/div[1]/div[3]/div[5]/div/div/div")
	public WebElement billingtohqcheckbox;

	@FindBy(xpath = "//span[text()='Choose Customer Type']")
	public WebElement customertype;

	@FindBy(xpath = "(//span[@role='presentation'])[7]")
	public WebElement customertypedropdown;

	@FindBy(xpath = "//*[@id=\"CustomerCreateForm\"]/div[1]/fieldset/div[1]/div[3]/div[7]/div/div/div")
	public WebElement hqcustomercheckbox;

	@FindBy(xpath = "//*[@id=\"Isdiscountdiv\"]/div/div/div")
	public WebElement allowbilldiscountcheckbox;

	@FindBy(id = "MaxAmountForDiscount")
	public WebElement maxamount;

	@FindBy(id = "DiscountPercentage")
	public WebElement discount;

	@FindBy(xpath = "//*[@id=\"CustomerCreateForm\"]/div[1]/fieldset/div[1]/div[3]/div[11]/div/div")
	public WebElement isshippingcustomercheckbox;

	@FindBy(xpath = "//a[text()=' HQ']")
	public WebElement hq;

	@FindBy(xpath = "//a[@class='glyphicon glyphicon-remove del']")
	public WebElement hqdismiss;

	@FindBy(id = "popup_ok")
	public WebElement alertok;

	@FindBy(id = "popup_cancel")
	public WebElement alertcancel;

	@FindBy(xpath = "//a[text()=' + Add Location']")
	public WebElement addlocation;

	@FindBy(id = "LocationCode")
	public WebElement hqcode;

	@FindBy(id = "LocationName")
	public WebElement hqname;

	@FindBy(id = "select2-SalesManId-container")
	public WebElement salesman;

	@FindBy(xpath = "(//span[@role='presentation'])[8]")
	public WebElement salesmandropdown;

	@FindBy(xpath = "(//span[@class='select2-selection__rendered'])[9]")
	public WebElement deliveryman;

	@FindBy(xpath = "(//span[@role='presentation'])[9]")
	public WebElement deliverymandropdown;

	@FindBy(xpath = "(//span[@class='select2-selection__rendered'])[10]")
	public WebElement zone;

	@FindBy(xpath = "(//span[@role='presentation'])[10]")
	public WebElement zonedropdown;

	@FindBy(xpath = "(//span[@class='select2-selection__rendered'])[11]")
	public WebElement deliveryday;

	@FindBy(xpath = "(//span[@role='presentation'])[11]")
	public WebElement deliverydaydropdown;

	@FindBy(xpath = "(//span[@class='select2-selection__rendered'])[12]")
	public WebElement scheduleday;

	@FindBy(xpath = "(//span[@role='presentation'])[12]")
	public WebElement scheduledaydropdown;

	@FindBy(id = "Address1")
	public WebElement address1;

	@FindBy(id = "Address2")
	public WebElement address2;

	@FindBy(id = "Address3")
	public WebElement address3;

	@FindBy(id = "City")
	public WebElement city;

	@FindBy(xpath = "//*[@id=\"select2-CountryId-container\"]")
	public WebElement country;

	@FindBy(xpath = "(//span[@role='presentation'])[13]")
	public WebElement countrydropdown;

	@FindBy(id = "PostalCode")
	public WebElement postalcode;

	@FindBy(id = "SearchPostalCode")
	public WebElement searchpostalcode;

	@FindBy(xpath = "//*[@id=\"section_HQ\"]/div[2]/div[2]/div[4]/div[1]/div")
	public WebElement deladdresscheckbox;

	@FindBy(id = "DeliveryAddress1")
	public WebElement deladdress1;

	@FindBy(id = "DeliveryAddress2")
	public WebElement deladdress2;

	@FindBy(id = "DeliveryAddress3")
	public WebElement deladdress3;

	@FindBy(id = "DeliveryCity")
	public WebElement delcity;

	@FindBy(xpath = "//*[@id=\"select2-DeliveryCountryId-container\"]")
	public WebElement delcountry;

	@FindBy(xpath = "(//span[@role='presentation'])[14]")
	public WebElement delcountrydropdown;

	@FindBy(id = "DeliveryPostalCode")
	public WebElement delpostalcode;

	@FindBy(id = "SearchDeliveryPostalCode")
	public WebElement searchdelpostalcode;

	@FindBy(id = "ContactPerson")
	public WebElement person1;

	@FindBy(id = "ContactNumber")
	public WebElement phoneno1;

	@FindBy(id = "Email")
	public WebElement email;

	@FindBy(id = "ContactPerson1")
	public WebElement person2;

	@FindBy(id = "ContactNumber1")
	public WebElement phoneno2;

	@FindBy(id = "Remarks")
	public WebElement remarks;

	@FindBy(id = "OfficeNumber")
	public WebElement officeno;

	@FindBy(id = "Fax")
	public WebElement faxno;

	@FindBy(id = "SortCode")
	public WebElement sortcode;

	@FindBy(xpath = "//*[@id=\"section_HQ\"]/div[4]/div[3]/div[4]/div[1]/div/div")
	public WebElement ishqcheckbox;

	@FindBy(xpath = "//*[@id=\"section_HQ\"]/div[4]/div[3]/div[4]/div[3]/div")
	public WebElement hqisactivecheckbox;

	@FindBy(id = "BillingPersonName")
	public WebElement billingpersonname;

	@FindBy(id = "BillingEmail")
	public WebElement billingpersonemail;

	@FindBy(id = "BillingContactNumber")
	public WebElement billingpersoncontactno;

	@FindBy(id = "BillingAddress")
	public WebElement billingpersonaddress;
	
	//Sales Invoice
	@FindBy(xpath = "//a[text()='Copy Invoice']")
	public WebElement Copyinvoice;

	public Customer(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

}
