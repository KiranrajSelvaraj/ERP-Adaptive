package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MobileVanTransfers {
	
	public static WebDriver driver;
	
	@FindBy(id = "Create")
	public WebElement CreateNew;
	
	@FindBy(id = "TrsanferNo")
	public WebElement FindTransferNo;
	
	@FindBy(id = "btn_search")
	public WebElement Fetch;
	
	@FindBy(id = "btn_clear")
	public WebElement Clear;
	
	@FindBy(id = "select2-FromWarehouseId-container")
	public WebElement FromWarehouse;
	
	@FindBy(id = "Remarks")
	public WebElement Remarks;
	
	@FindBy(id = "ddt_date")
	public WebElement Date;
	
	@FindBy(id = "select2-ToMobileVanId-container")
	public WebElement ToMobileVan;
	
	@FindBy(id = "select2-Department-container")
	public WebElement Department;
	
	@FindBy(id = "select2-Category-container")
	public WebElement Category;
	
	@FindBy(id = "select2-Product-container")
	public WebElement Product;
	
	@FindBy(id = "select2-UOM-container")
	public WebElement UOM;
	
	@FindBy(id = "Qty")
	public WebElement Qty;
	
	@FindBy(id = "btnAdd")
	public WebElement Add;
	
	@FindBy(id = "btnShowall")
	public WebElement AddItems;
	
	@FindBy(id = "Create")
	public WebElement Save;
	
	@FindBy(id = "btnhold")
	public WebElement Hold;
	
	@FindBy(id = "btnback")
	public WebElement Back;
	
	//Add Items Popup Elements
	
	@FindBy(id = "select2-DepartmentId-container")
	public WebElement AddItemsDepartment;
	
	@FindBy(id = "add-products")
	public WebElement AddItemsAdd;
	
	
	
	public MobileVanTransfers(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
