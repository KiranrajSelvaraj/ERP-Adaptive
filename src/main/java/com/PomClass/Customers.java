package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Customers {
	
	public static WebDriver driver;
	
	//System Settings
	
	@FindBy(id="SearchString")
	public WebElement SystemsSettingsParamCodeSearchField;
	
	@FindBy(id="searchstring")
	public WebElement SystemSettingsFetch;
	
	//Customer Create
	@FindBy(id="Create")
	public WebElement Create;
	
	@FindBy(id="Create")
	public WebElement Save;
	
	@FindBy(xpath="//input[@onclick='btnBack()']")
	public WebElement Back;
	
	@FindBy(id="popup_cancel")
	public WebElement AlertCancel;
	
	@FindBy(id="Name")
	public WebElement Name;
	
	@FindBy(id="AliasName")
	public WebElement Alias;
	
	@FindBy(id="RegNo")
	public WebElement CompRegNo;
	
	@FindBy(id="IsGST")
	public WebElement GSTReg;
	
	@FindBy(id="GSTRegistrationNo")
	public WebElement GSTRegNo;
	
	@FindBy(id="select2-PaymentTermsId-container")
	public WebElement CreditTerms;
	
	@FindBy(id="select2-ToleranceId-container")
	public WebElement TermsTolerance;
	
	@FindBy(id="select2-RateType-container")
	public WebElement RateType;
	
	@FindBy(id="CreditLimit")
	public WebElement CreditLimit;
	
	@FindBy(id="select2-ChartOfAccountsId-container")
	public WebElement ARAccount;
	
	@FindBy(id="select2-CurrencyId-container")
	public WebElement Currency;
	
	@FindBy(id="IsMultipleLocation")
	public WebElement MultiLoc;
	
	@FindBy(id="LocationCode")
	public WebElement LocationCode;
	
	@FindBy(id="LocationName")
	public WebElement LocationName;
	
	@FindBy(id="select2-SalesManId-container")
	public WebElement Salesman;
	
	@FindBy(id="select2-ZoneId-container")
	public WebElement Zone;
	
	@FindBy(id="select2-DeliveryManId-container")
	public WebElement DeliveryMan;	
	
	@FindBy(id="select2-DeliveryDay-container")
	public WebElement DeliveryDay;
	
	@FindBy(id="select2-ScheduleDay-container")
	public WebElement ScheduleDay;
	
	@FindBy(id="Address1")
	public WebElement Address1;
	
	@FindBy(id="Address2")
	public WebElement Address2;
	
	@FindBy(id="Address3")
	public WebElement Address3;
	
	@FindBy(id="City")
	public WebElement City;
	
	@FindBy(id="select2-CountryId-container")
	public WebElement Country;
	
	@FindBy(id="PostalCode")
	public WebElement PostalCode;
	
	@FindBy(id="DeliveryAddress1")
	public WebElement DeliveryAddress1;
	
	@FindBy(id="DeliveryAddress2")
	public WebElement DeliveryAddress2;
	
	@FindBy(id="DeliveryAddress3")
	public WebElement DeliveryAddress3;
	
	@FindBy(id="DeliveryCity")
	public WebElement DeliveryCity;
	
	@FindBy(id="select2-DeliveryCountryId-container")
	public WebElement DeliveryCountry;
	
	@FindBy(id="DeliveryPostalCode")
	public WebElement DeliveryPostalCode;
	
	
	
	
	
	
	
	public Customers(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
