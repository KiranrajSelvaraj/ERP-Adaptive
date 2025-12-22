package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WarehouseTransfer {
	
	public static WebDriver driver;
	
	// index
	@FindBy(xpath = "//input[@id='Create' and @value='[+] Create New']")
	public WebElement CreateNew;
	
	@FindBy(id = "TrsanferNo")
	public WebElement TransferNo;
	
	@FindBy(id = "select2-FromWarehouse-container")
	public WebElement IndexFromWarehouse;
	
	@FindBy(id = "select2-ToWarehouse-container")
	public WebElement IndexToWarehouse;
	
	@FindBy(id = "Status")
	public WebElement WarehouseTransferStatus;
	
	@FindBy(id = "fromdate")
	public WebElement FromDate;
	
	@FindBy(id = "todate")
	public WebElement ToDate;
	
	@FindBy(id = "btn_search")
	public WebElement Fetch;
	
	@FindBy(id = "btn_clear")
	public WebElement Clear;
	
	// create
	@FindBy(id = "select2-FromWarehouseId-container")
	public WebElement CreateFromWarehouse;
	
	@FindBy(xpath = "//textarea[@id='Remarks']")
	public WebElement Remarks;
	
	@FindBy(id = "ddt_date")
	public WebElement CreateDate;
	
	@FindBy(id = "select2-ToWarehouseId-container")
	public WebElement createToWarehouse;
	
	@FindBy(id = "ReferenceNo")
	public WebElement ReferenceNo;
	
	@FindBy(id = "//textarea[@id='Subject']")
	public WebElement Subject;
	
	@FindBy(id = "select2-ProductId-container")
	public WebElement Product;
	
	@FindBy(id = "select2-UOM-container")
	public WebElement Uom;
	
	@FindBy(xpath = "//input[@id='Qty' and @class='form-control QtyTextBox numericCheck']")
	public WebElement Qty;
	
	@FindBy(id = "btnAdd")
	public WebElement Add;
	
	@FindBy(xpath = "//button[@id='Create']")
	public WebElement Save;
	
	@FindBy(id = "btnhold")
	public WebElement Hold;
	
	@FindBy(id = "btnback")
	public WebElement Back;
	
	
	public WarehouseTransfer(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

}
