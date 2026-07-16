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
	
	@FindBy(id="PasswordInput")
	public WebElement Password;
		
	@FindBy(xpath="//input[contains(@value,'Log In')]")
	public WebElement LoginButton;
	
	
	public Login(WebDriver driver) {
		this.driver = driver;		
		PageFactory.initElements(driver, this);
	}

}
