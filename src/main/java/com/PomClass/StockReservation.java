package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StockReservation {
	
	public static WebDriver driver;
	
	@FindBy(xpath = "//input[@id='Create' and @value='[+] Add Stock Reservation']")
	public WebElement AddStockReservation;
	
	@FindBy(id = "select2-Status-container")
	public WebElement Status;
	
	@FindBy(xpath = "//input[@id='SearchString']")
	public WebElement SearchCustomer;
	
	@FindBy(xpath = "//button[@id='searchstring']")
	public WebElement Fetch;
	
	@FindBy(id = "clear")
	public WebElement Clear;
	
	@FindBy(id = "select2-CustomerId-container")
	public WebElement Customer;
	
	@FindBy(id = "select2-SalesManId-container")
	public WebElement Salesman;
	
	@FindBy(id = "ReferenceNumber")
	public WebElement ReferenceNo;
	
	@FindBy(xpath = "//input[@id='ExpectedReleaseDate']")
	public WebElement ReleaseDate;
	
	@FindBy(id = "select2-CustomerLocationsId-container")
	public WebElement CustomerLocation;
	
	@FindBy(id = "select2-WarehouseId-container")
	public WebElement Warehouse;
	
	@FindBy(xpath = "//input[@id='ReservationDate']")
	public WebElement ReservationDate;
	
	@FindBy(id = "Remarks")
	public WebElement Remarks;
	
	@FindBy(id = "select2-ProductId-container")
	public WebElement Product;
	
	@FindBy(id = "select2-UOMId-container")
	public WebElement Uom;
	
	@FindBy(id = "ReservationQty")
	public WebElement Qty;
	
	@FindBy(id = "add-product-btn")
	public WebElement Add;
	
	@FindBy(xpath = "//input[@value='Save']")
	public WebElement Save;
	
	@FindBy(id = "Post")
	public WebElement Post;
	
	@FindBy(id = "Back")
	public WebElement Back;
	
	@FindBy(id = "popup_ok")
	public WebElement PopupOk;
	
	
	
	public StockReservation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
