package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Product {
	
	public static WebDriver driver;
	
	//Index
	@FindBy(id="Create")
	public WebElement AddProduct;
	
	@FindBy(id="import")
	public WebElement ImportExcel;
	
	@FindBy(id="SearchString")
	public WebElement FindProductorCode;
	
	@FindBy(id="searchstring")
	public WebElement Fetch;
	
	@FindBy(id="clear")
	public WebElement Clear;
	
	@FindBy(xpath = "(//table[@id='producttable']//tbody//tr//td[10]//a[@title='Details'])[1]")
	public WebElement DetailsIcon;
	
	//Create Page
	@FindBy(id = "Code")
	public WebElement ProductCode;
	
	@FindBy(id = "Name")
	public WebElement ProductName;
	
	@FindBy(id = "select2-DepartmentId-container")
	public WebElement Department;
	
	@FindBy(id = "select2-CategoryId-container")
	public WebElement Category;
	
	@FindBy(id = "select2-BrandId-container")
	public WebElement Brand;
	
	@FindBy(xpath="//input[@value='Back']")
	public WebElement Back;
	
	@FindBy(id = "btnCreate")
	public WebElement Save;
	
	//Info Tab
	@FindBy(xpath = "//a[contains(text(),'Info') and @data-toggle='tab']")
	public WebElement InfoTab;
	
	@FindBy(id = "select2-VendorId-container")
	public WebElement VendorName;
	
	//Stock Tab
	@FindBy(xpath = "//a[contains(text(),'Stock') and @class='prodStockTab']")
	public WebElement StockTab;
	
	@FindBy(id = "select2-UOMId-container")
	public WebElement UOM;
	
	
	
	
	public Product(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

}
