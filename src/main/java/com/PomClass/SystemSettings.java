package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SystemSettings {
	
	public static WebDriver driver;
	
	@FindBy(xpath = "//input[@value='[+] Create New']")
	public WebElement SystemSettingsCreateNew;
	
	@FindBy(id="SearchString")
	public WebElement SystemsSettingsParamCodeSearchField;
	
	@FindBy(id="searchstring")
	public WebElement SystemSettingsFetch;
	
	@FindBy(id="clear")
	public WebElement Clear;
	
	@FindBy(id="Select")
	public WebElement Model;
	
	@FindBy(id="ParamCode")
	public WebElement ParamCode;
	
	@FindBy(id="Description")
	public WebElement Description;
	
	@FindBy(id="SelectType")
	public WebElement ParamValueType;
	
	@FindBy(id = "BitValue")
	public WebElement BooleanParamBitValue;
	
	@FindBy(id = "StringValue")
	public WebElement ParamStringValue;
	
	@FindBy(id = "DecimalValue")
	public WebElement ParamDecimalValue;
	
	@FindBy(id = "NumericValue")
	public WebElement ParamNumericValue;
	
	@FindBy(id = "DateValue")
	public WebElement ParamDatevalue;
	
	@FindBy(id = "GuidValue")
	public WebElement ParamGuidValue;
	
	@FindBy(id = "TextArea")
	public WebElement ParamTextArea;
	
	@FindBy(id="Create")
	public WebElement Save;
	
	@FindBy(id="btnback")
	public WebElement Back;
	
	@FindBy(id="icheckbox_square-blue icheck-item icheck[r679m]")
	public WebElement IsCompany;
	
	@FindBy(xpath = "(//table[@id='systemsettingtable']//tbody//tr//td[6]//a[@title='Edit'])[1]")
	public WebElement EditSystemSetting;
	
	
	
	
	
	
	
	public SystemSettings(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

}
