package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerPayment {
	
	public static WebDriver driver;
	
	//index
	@FindBy(id = "Create")
	public WebElement CreateNew;
	
	@FindBy(xpath = "//input[@placeholder='From Date']")
	public WebElement FromDate;
	
	@FindBy(xpath = "//input[@placeholder='To Date']")
	public WebElement ToDate;
	
	@FindBy(id = "a_CustomerPaymentNumber")
	public WebElement CustomerPaymentNo;
	
	@FindBy(id = "a_AmountRecieved")
	public WebElement Amount;
	
	@FindBy(id = "filter_load")
	public WebElement Fetch;
	
	@FindBy(id = "filter_clear")
	public WebElement Clear;
	
	//edit
	@FindBy(id = "select2-CustomerId-container")
	public WebElement Customer;
	
	@FindBy(id = "PaymentDate")
	public WebElement PaymentDate;
	
	@FindBy(id = "select2-SalesManId-container")
	public WebElement Salesman;
	
	@FindBy(id = "select2-DeliveryManId-container")
	public WebElement Deliveryman;
	
	@FindBy(id = "select2-InvoiceCurrencyId-container")
	public WebElement InvoiceCurrency;
	
	@FindBy(id = "btnAttachment")
	public WebElement Attachment;
	
	@FindBy(id = "select2-CurrencyId-container")
	public WebElement PaymodeCurrency;
	
	@FindBy(id = "PaymentToAccountCurrencyRate")
	public WebElement CurrencyRate;
	
	@FindBy(id = "select2-PaymodeId-container")
	public WebElement Paymode;
	
	@FindBy(id = "Remarks")
	public WebElement Remarks;
	
	@FindBy(xpath = "//button[@value='Save & Close']")
	public WebElement Save;
	
	@FindBy(xpath = "//input[@value='Hold']")
	public WebElement Hold;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
	
	@FindBy(id = "popup_ok")
	public WebElement PopupOk;
	
	@FindBy(id = "checkall")
	public WebElement CheckBoxAll;
	
	
	public CustomerPayment (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

}
