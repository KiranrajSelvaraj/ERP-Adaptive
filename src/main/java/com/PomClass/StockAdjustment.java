package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StockAdjustment {
	
	public static WebDriver driver;
	
	// V2 Index:-
	@FindBy(id = "Create")
	public WebElement CreateV2;
	
	@FindBy(id = "getTemplate")
	public WebElement GetTemplate;
	
	@FindBy(id = "SearchString")
	public WebElement FindStockNo;
	
	@FindBy(id = "from")
	public WebElement FromDate;
	
	@FindBy(id = "to")
	public WebElement ToDate;
	
	@FindBy(id = "searchstring")
	public WebElement Fetch;
	
	@FindBy(id = "clear")
	public WebElement clear;
	
	@FindBy(xpath = "//select[@name='StockAdjustmenttable_length']//following-sibling::option[4]")
	public WebElement ShowTableLength;
	
	@FindBy(xpath = "//li[@id='StockAdjustmenttable_previous']//child::a[text()='Previous']")
	public WebElement Pervious;
	
	@FindBy(xpath = "//li[@id='StockAdjustmenttable_next']//child::a[text()='Next']")
	public WebElement Next;
	
	// V2 Edit page:-
	@FindBy(id = "SANumber")
	public WebElement StockAdjustmentNumberV2;
	
	@FindBy(id = "Date")
	public WebElement StockAdjustmentDateV2;
	
	@FindBy(id = "select2-StockAdjustmentTypeId-container")
	public WebElement StockAdjustmentTypeV2;
	
	@FindBy(id = "select2-WarehouseId-container")
	public WebElement WarehouseV2;
	
	@FindBy(id = "Remarks")
	public WebElement RemarksV2;
	
	@FindBy(id = "select2-ProductId-container")
	public WebElement ChooseProductV2;
	
	@FindBy(id = "select2-UOMId-container")
	public WebElement UOMV2;
	
	@FindBy(id = "BuQty")
	public WebElement BQtyV2;
	
	@FindBy(id = "LoQty")
	public WebElement LQtyV2;
	
	@FindBy(id = "btn_add")
	public WebElement AddV2;
	
	@FindBy(id = "Save")
	public WebElement SaveV2;
	
	@FindBy(id = "Hold")
	public WebElement HoldV2;
	
	@FindBy(id = "Back")
	public WebElement BackV2;
	
	@FindBy(id = "popup_ok")
	public WebElement AlertPopupOK;
	
	
	
	// V1 page:-
	@FindBy(id = "IsDisposal")
	public WebElement IsDisposal;
	
	@FindBy(id = "IsOpeningStock")
	public WebElement IsOpeningStock;
	
	@FindBy(id = "select2-ProductId-container")
	public WebElement ChooseProduct;
	
	@FindBy(xpath = "//button[text()='Add']")
	public WebElement Add;
		
	@FindBy(xpath = "//a[text()='Copy Stock Adjustment']")
	public WebElement CopyStockAdjustment;
	
	@FindBy(id = "btnsave")
	public WebElement Save;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
		
	
	public StockAdjustment(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	
	
}
