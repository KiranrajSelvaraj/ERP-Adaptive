package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductMovement {
	
	public static WebDriver driver;
	
	@FindBy(id = "select2-WarehouseId-container")
	public WebElement Warehouse;
	
	@FindBy(id = "DataType")
	public WebElement DataType;

	@FindBy(id = "select2-ProductId-container")
	public WebElement ChooseProduct;
	
	@FindBy(id = "Type")
	public WebElement Type;
	
	@FindBy(id = "select2-UOM-container")
	public WebElement UOM;
	
	@FindBy(id = "fromDate")
	public WebElement FromDate;
	
	@FindBy(id = "toDate")
	public WebElement ToDate;

	@FindBy(id="searchstring")
	public WebElement Fetch;
	
	@FindBy(id = "btnPrint")
	public WebElement Print;

	@FindBy(id="clear")
	public WebElement Clear;

	
	public ProductMovement(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
}
