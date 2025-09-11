package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeliveryVehicleAssign {
	
	public static WebDriver driver;
	
	@FindBy(id = "select2-TripId-container")
	public WebElement ChooseTrip;
	
	@FindBy(id = "TripDate")
	public WebElement TripDate;
	
	@FindBy(id = "select2-VehicleId-container")
	public WebElement ChooseVehicle;
	
	@FindBy(xpath = "//select[@id='DriverId']//following::span[@class='select2-selection select2-selection--multiple']")
	public WebElement CHooseDriver;
	
	@FindBy(id = "assign")
	public WebElement Assign;
	
	@FindBy(id = "startDate")
	public WebElement FromDate;
	
	@FindBy(id = "endDate")
	public WebElement ToDate;
	
	@FindBy(id = "select2-Status-container")
	public WebElement ChooseStatus;
	
	@FindBy(id = "FindInvoiceNumber")
	public WebElement FindInvoiceNumber;
	
	@FindBy(id = "fetch")
	public WebElement Fetch;
	
	@FindBy(id = "clear")
	public WebElement Clear;
	
	@FindBy(id = "popup_ok")
	public WebElement PopupOK;
	
	
	public DeliveryVehicleAssign(WebDriver driver) {		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

}
