package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApprovalSetting {
	
	public static WebDriver driver;
	
	@FindBy(id = "select2-Model-container")
	public WebElement SelectModel;
	
	@FindBy(id = "select2-9c7z-container")
	public WebElement ChooseAttribute;
	
	@FindBy(id = "select2-Stat-container")
	public WebElement SelectStatus;
	
	@FindBy(id = "Fetchrecords")
	public WebElement Fetch;
	
	@FindBy(id = "Create")
	public WebElement Save;
	
	@FindBy(id = "select2-UserId-container")
	public WebElement ChooseApproverandRecommender;
	
	@FindBy(id = "select2-StatusId-container")
	public WebElement ChooseStatus;
	
	@FindBy(id = "IsRules")
	public WebElement IsRulesCheckBox;
	
	@FindBy(id = "Rules")
	public WebElement Rules;
	
	@FindBy(id = "IsEmailNotification")
	public WebElement IsEmailCheckBox;
	
	@FindBy(id = "NotificationEmail")
	public WebElement Email;
	
	@FindBy(xpath = "//a[@class='fa fa-trash-o Delete']")
	public WebElement DeleteIcon;
	
	@FindBy(id = "SalesApprover")
	public WebElement AddRow;
	
	
	public ApprovalSetting(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
