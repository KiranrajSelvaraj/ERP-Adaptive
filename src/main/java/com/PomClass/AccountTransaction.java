package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountTransaction {

	public static WebDriver driver;

	//index
	@FindBy(xpath = "//input[@placeholder='From Date']")
	public WebElement FromDate;

	@FindBy(xpath = "//input[@placeholder='To Date']")
	public WebElement ToDate;

	@FindBy(id = "a_No")
	public WebElement InvoiceNo;

	@FindBy(id = "a_Amount")
	public WebElement Amount;

	@FindBy(id = "a_ReferenceNumber")
	public WebElement ReferenceNo;

	@FindBy(id = "a_Action")
	public WebElement Action;

	@FindBy(xpath = "//input[@placeholder=' Choose Parent Account']")
	public WebElement AccountSearch;

	@FindBy(id = "filter_load")
	public WebElement Fetch;

	@FindBy(id = "filter_clear")
	public WebElement Clear;

	@FindBy(id = "filter_Gl")
	public WebElement GLReport;

	@FindBy(id = "filter_Print")
	public WebElement Print;

	@FindBy(id = "filter_Excel")
	public WebElement Excel;

	//edit
	@FindBy(xpath = "//label[text()='Journal Date']//following::input[@id='date']")
	public WebElement JournalDate;

	@FindBy(xpath = "//input[@id='Back']")
	public WebElement Back;

	@FindBy(xpath = "//input[@value='Recurring']")
	public WebElement Recurring;

	public AccountTransaction (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

}
