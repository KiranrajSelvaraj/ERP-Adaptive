package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Service {
	
	public static WebDriver driver;
	
	//INDEX
	@FindBy(id = "Create")
	public WebElement AddService;
	
	@FindBy(id = "import")
	public WebElement ImportExcel;
	
	@FindBy(id = "SearchString")
	public WebElement SearchField;
	
	@FindBy(xpath = "//button[@Id='searchstring']")
	public WebElement Fetch;
	
	@FindBy(id = "clear")
	public WebElement Clear;
	
	//EDIT
	@FindBy(id = "Name")
	public WebElement Name;
	
	@FindBy(id = "select2-DepartmentId-container")
	public WebElement Department;
	
	@FindBy(id = "select2-CategoryId-container")
	public WebElement Category;
	
	@FindBy(id = "Description")
	public WebElement Description;
	
	@FindBy(id = "btnpopup")
	public WebElement VendorNamePopup;
	
	@FindBy(id = "SearchStringVendor")
	public WebElement VendorSearchField;
	
	@FindBy(id = "select2-UOMId-container")
	public WebElement Uom;
	
	@FindBy(id = "select2-CurrencyId-container")
	public WebElement Currency;
	
	@FindBy(id = "UnitPrice")
	public WebElement UnitPrice;
	
	@FindBy(id = "IsZeroRatedGST")
	public WebElement IsZeroRatedGSTCheckbox;
	
	
	public Service (WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}


}
