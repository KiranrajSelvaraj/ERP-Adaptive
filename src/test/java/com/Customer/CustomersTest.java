package com.Customer;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.BaseClass.BaseClass;
import com.PomClass.Customers;
import com.PomClass.Login;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomersTest extends BaseClass {

	private String url;

	@Test(priority = 1)
	public void ERPLoginPage() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://payauto.dev.adaptivegroups.asia/Account/Login");
		url = "https://payauto.dev.adaptivegroups.asia/ERP/";

		Login lo = new Login(driver);

		Sendkeys(lo.CompanyCode, "UITDEMO1");
		Sendkeys(lo.UserName, "Kiran1");
		Sendkeys(lo.Password, "Adaptive*123");
		click(lo.LoginButton);
		Thread.sleep(2000);

		String ActURL = driver.getCurrentUrl();
		boolean equals = url.equalsIgnoreCase(ActURL);

		if (equals == false) {

			Navigate_to(ActURL);
			Sendkeys(lo.CompanyCode, "UITDEMO1");
			Sendkeys(lo.UserName, "kiran2");
			Sendkeys(lo.Password, "Adaptive*123");
			Thread.sleep(1000);
			click(lo.LoginButton);

		}
		String ActURL1 = driver.getCurrentUrl();
		boolean equals1 = url.equalsIgnoreCase(ActURL1);

		if (equals1 == false) {

			Navigate_to(ActURL1);
			Sendkeys(lo.CompanyCode, "UITDEMO1");
			Sendkeys(lo.UserName, "kiran3");
			Sendkeys(lo.Password, "Adaptive*123");
			Thread.sleep(1000);
			click(lo.LoginButton);

		}

	}

	@DataProvider
	public Object[][] Util1() {

		Object data[][] = Util1.getTestData("C:\\Adaptive\\Automation\\Bizapp\\Customer.xlsx", "Sheet1");
		return data;

	}

	class ExcelData {

		private String CustomerName;
		private String GstType;
		private String Currency;
		private String CreditLimit;
		private String LocationCode;
		private String LocationName;
		private String Salesman;

		public ExcelData(String CustomerName, String GstType, String Currency, String CreditLimit,
				String LocationCode, String LocationName, String Salesman) {
			super();

			this.CustomerName = CustomerName;
			this.GstType = GstType;
			this.Currency = Currency;
			this.CreditLimit = CreditLimit;
			this.LocationCode = LocationCode;
			this.LocationName = LocationName;
			this.Salesman = Salesman;
		}
	}

	ArrayList<ExcelData> excelDataList = new ArrayList<>();
	List<String> CustomerList = new ArrayList<String>();
	Set<String> CustomerSet = new LinkedHashSet<String>();

	@Test(priority = 4, dataProvider = "Util1", dependsOnMethods = "ERPLoginPage")
	public void GetData(String CustomerName, String GstType, String Currency, String CreditLimit,
			String LocationCode, String LocationName, String Salesman) {

		ExcelData data = new ExcelData(CustomerName, GstType, Currency, CreditLimit, LocationCode, LocationName, Salesman);

		excelDataList.add(data);	
	}

	@Test(priority = 9)
	public void SystemSettings() throws InterruptedException {

		System.out.println("**System Settings Values**");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Customers cc = new Customers(driver);
		driver.navigate().to(url +"SystemSetting");
		Thread.sleep(4000);

		cc.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(cc.SystemsSettingsParamCodeSearchField, "DefaultCreditLimitForCustomer" +Keys.ENTER );
		Thread.sleep(1000);
		click(cc.SystemSettingsFetch);
		Thread.sleep(1000);
		String DefaultCreditLimitForCustomer = driver.findElement
				(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]")).getText();
		System.out.println("DefaultCreditLimitForCustomer Value in system setttings:" +DefaultCreditLimitForCustomer);

		Thread.sleep(1000);
		cc.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(cc.SystemsSettingsParamCodeSearchField, "IsBlockByCreditLimitInSI" +Keys.ENTER );
		Thread.sleep(1000);
		click(cc.SystemSettingsFetch);
		Thread.sleep(1000);
		String IsBlockByCreditLimitInSI = driver.findElement
				(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]")).getText();
		System.out.println("IsBlockByCreditLimitInSI Value in system setttings:" +IsBlockByCreditLimitInSI);

		Thread.sleep(1000);
		cc.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(cc.SystemsSettingsParamCodeSearchField, "IsBlockByCreditLimitInSO" +Keys.ENTER );
		Thread.sleep(1000);
		click(cc.SystemSettingsFetch);
		Thread.sleep(1000);
		String IsBlockByCreditLimitInSO = driver.findElement
				(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]")).getText();
		System.out.println("IsBlockByCreditLimitInSO Value in system setttings:" +IsBlockByCreditLimitInSO);

		Thread.sleep(1000);
		cc.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(cc.SystemsSettingsParamCodeSearchField, "IsCreditLimitAllowed" +Keys.ENTER );
		Thread.sleep(1000);
		click(cc.SystemSettingsFetch);
		Thread.sleep(1000);
		String 	IsCreditLimitAllowed = driver.findElement
				(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]")).getText();
		System.out.println("IsCreditLimitAllowed Value in system setttings:" +	IsCreditLimitAllowed);

		Thread.sleep(1000);
		cc.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(cc.SystemsSettingsParamCodeSearchField, "IsCreditLimitManagement" +Keys.ENTER );
		Thread.sleep(1000);
		click(cc.SystemSettingsFetch);
		Thread.sleep(1000);
		String 	IsCreditLimitManagement = driver.findElement
				(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]")).getText();
		System.out.println("IsCreditLimitManagement Value in system setttings:" +IsCreditLimitManagement);

		Thread.sleep(1000);
		cc.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(cc.SystemsSettingsParamCodeSearchField, "IsCreditLimitManagementInWave" +Keys.ENTER );
		Thread.sleep(1000);
		click(cc.SystemSettingsFetch);
		Thread.sleep(1000);
		String IsCreditLimitManagementInWave = driver.findElement
				(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]")).getText();
		System.out.println("IsCreditLimitManagementInWave Value in system setttings:" +IsCreditLimitManagementInWave);



	}

	@Test(priority = 10)
	public void CustomerCreate() throws InterruptedException {
		CustomerList.addAll(CustomerSet);

		driver.navigate().to(url + "SalesPurchases/Customer");
		Thread.sleep(4000);
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Customers cc = new Customers(driver);

		click(cc.Create);
		Thread.sleep(3000);

		int DataListsize = excelDataList.size();
		System.out.println("DataListsize:" +DataListsize);
		for (int i = 0; i < DataListsize; i++) {
			ExcelData excelData = excelDataList.get(i);

			click(cc.Name);
			Sendkeys(cc.Name, excelData.CustomerName);
			click(cc.GstType);
			driver.findElement(By.xpath("//span[@id='select2-GSTTypeId-container']//following::input[@type='search']"))
			.sendKeys(excelData.GstType +Keys.ENTER);
			click(cc.Currency);
			driver.findElement(By.xpath("//span[@id='select2-CurrencyId-container']//following::input[@type='search']"))
			.sendKeys(excelData.Currency +Keys.ENTER);
			click(cc.CreditLimit);
			cc.CreditLimit.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
			Sendkeys(cc.CreditLimit, excelData.CreditLimit);

			click(cc.LocationCode);
			cc.LocationCode.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
			Sendkeys(cc.LocationCode, excelData.LocationCode);
			click(cc.LocationName);
			cc.LocationName.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
			Sendkeys(cc.LocationName, excelData.LocationName);
			click(cc.Salesman);
			driver.findElement(By.xpath("//span[@id='select2-SalesManId-container']//following::input[@type='search']"))
			.sendKeys(excelData.Salesman +Keys.ENTER);

			Thread.sleep(3000);
			click(cc.Save);
			try {

				String alertText = driver.findElement(By.id("popup_message")).getText();
				System.out.println("alertText: "+alertText);

			} catch(Exception e){
				System.out.println("No alert appeared after save");	
			}

		} // Excel data loop

	} // Method loop


} // Main class loop
