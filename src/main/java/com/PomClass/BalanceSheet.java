package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BalanceSheet {
	
	public static WebDriver driver;
	
	
	@FindBy(id = "select2-Format-container")
	public WebElement Format;
	
	@FindBy(id = "from")
	public WebElement Date;
	
	@FindBy(id = "Fetch")
	public WebElement Fetch;
	
	@FindBy(id = "Print")
	public WebElement Print;
	
	@FindBy(id = "btnExport")
	public WebElement ExportExcel;
	
	
	public BalanceSheet(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
