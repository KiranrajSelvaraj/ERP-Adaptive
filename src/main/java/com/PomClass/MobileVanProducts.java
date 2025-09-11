package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MobileVanProducts {
	
	public static WebDriver driver;
	
	@FindBy(id = "select2-MobileVan-container")
	public WebElement ChooseMobileVan;
	
	@FindBy(id = "SearchString")
	public WebElement FindNameandCode;
	
	@FindBy(id = "searchstring")
	public WebElement Fetch;
	
	@FindBy(id = "clear")
	public WebElement clear;
	
	public MobileVanProducts(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
