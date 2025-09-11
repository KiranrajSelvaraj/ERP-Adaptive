package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	
	public static WebDriver driver;
	
	@FindBy(id="CompanyName")
	public WebElement CompanyCode;
	
	@FindBy(id="UserName")
	public WebElement UserName;
	
	@FindBy(id="Password")
	public WebElement Password;
	
	@FindBy(id="login")
	public WebElement LoginButton;
	
	
	
	
	public Login(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

}
