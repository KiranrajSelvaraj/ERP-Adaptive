package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductAssembly {
	
	public static WebDriver driver;
	
	//Index Page
	@FindBy(id = "Create")
	public WebElement Create;
	
	@FindBy(xpath = "//input[@id='SearchString']")
	public WebElement FindProduct;
	
	@FindBy(id = "ProductAssemblyNo")
	public WebElement FindAssemblyNo;
	
	@FindBy(id = "from")
	public WebElement FromDate;
	
	@FindBy(id = "to")
	public WebElement ToDate;
	
	@FindBy(xpath = "//input[@id='searchstring'and@value='Fetch'] ")
	public WebElement Fetch;
	
	@FindBy(id = "clear")
	public WebElement Clear;
	
	// Edit page
	
	@FindBy(id = "select2-AssemblyTypeId-container")
	public WebElement AssemblyType;
	
	@FindBy(id = "Date")
	public WebElement Date;
	
	@FindBy(id = "select2-Type-container")
	public WebElement Type;
	
	@FindBy(id = "select2-FromProductId-container")
	public WebElement FromProduct;
	
	@FindBy(id = "select2-ToProductId-container")
	public WebElement ToProduct;
	
	@FindBy(id = "select2-FromUOMId-container")
	public WebElement FromUom;
	
	@FindBy(id = "select2-ToUOMId-container")
	public WebElement ToUom;
	
	@FindBy(xpath = "//input[@value='Add' and @onclick='AddFromProduct()']")
	public WebElement FromAdd;
	
	@FindBy(xpath = "//input[@value='Add' and @onclick='AddToProduct()']")
	public WebElement ToAdd;
	
	@FindBy(id = "btnSave")
	public WebElement Hold;
	
	@FindBy(id = "btnComplete")
	public WebElement Completed;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
	
	@FindBy(id = "btnPrint")
	public WebElement Print;
	
	
	public ProductAssembly(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

}
