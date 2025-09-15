package com.Purchase;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import com.PomClass.Customers;
import com.PomClass.Login;
import com.PomClass.PurchaseOrder;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomersTest extends BaseClass {



	private String url;

	@Test(priority = 1)
	public void ERPLoginPage() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://erp.dev1.adaptivegroups.asia/ERP/Account/Login");
		url = "https://erp.dev1.adaptivegroups.asia/ERP/";

		Login lo = new Login(driver);

		Sendkeys(lo.CompanyCode, "smm01");
		Sendkeys(lo.UserName, "Kiran01");
		Sendkeys(lo.Password, "Adaptive*123");
		click(lo.LoginButton);
		Thread.sleep(2000);

		String ActURL = driver.getCurrentUrl();
		boolean equals = url.equalsIgnoreCase(ActURL);

		if (equals == false) {

			Navigate_to(ActURL);
			Sendkeys(lo.CompanyCode, "smm01");
			Sendkeys(lo.UserName, "kiran02");
			Sendkeys(lo.Password, "Adaptive*123");
			Thread.sleep(1000);
			click(lo.LoginButton);

		}
		String ActURL1 = driver.getCurrentUrl();
		boolean equals1 = url.equalsIgnoreCase(ActURL1);

		if (equals1 == false) {

			Navigate_to(ActURL1);
			Sendkeys(lo.CompanyCode, "smm01");
			Sendkeys(lo.UserName, "kiran");
			Sendkeys(lo.Password, "Adaptive*123");
			Thread.sleep(1000);
			click(lo.LoginButton);

		}

	}
	@DataProvider
	public Object[][] Util2() {
		Object[][] data = Util1.getTestData("C:\\Adaptive\\ERP\\Customer.xlsx", "Sheet1");
		return data;
	}
	
	@SuppressWarnings("unused")
	class ExcelData {
		private String CODE;
		private String NAME;
		private String ALIAS;
		private String COMPANYREGNO;
		private String ISGST;
		private String GSTREGNO;
		private String CREDITTERMS;
		private String TERMSTOLERANCE;
		private String RATETYPE;
		private String CREDITLIMIT;
		private String ARACCOUNT;
		private String CURRENCY;
		private String LOCATIONCODE;
		private String LOCATIONNAME;
		private String SALESMAN;
		private String SALESMANGROUP;
		private String BRANCHNAME;
		private String ZONE;
		private String DELIVERYMAN;
		private String DELIVERYDAY;
		private String SCHEDUALDAY;
		private String ADDRESS1;
		private String ADDRESS2;
		private String ADDRESS3;
		private String CITY;
		private String COUNTRY;
		private String POSTALCODE;
		private String DELIVERYADDRESS1;
		private String DELIVERYADDRESS2;
		private String DELIVERYADDRESS3;
		private String DELIVERYCITY;
		private String DELIVERYCOUNTRY;
		private String DELIVERYPOSTALCODE;
		private String PERSON1;
		private String PERSON2;
		private String OFFICENO;
		private String PHONENO1;
		private String PHONENO2;
		private String FAXNO;
		private String EMAIL;
		private String REMARKS;
		private String DEVICENO;
		private String BILLINGPERSONNAME;
		private String BILLINGCONTACTNO;
		private String BILLINGADDRESS;
		private String BILLINGEMAIL;
		private String CompRegNo;


		public ExcelData(String Code, String Name, String Alias, String CompanyRegNo, String IsGst, String GstRegNo, String CreditTerms, String TermsTolerance, String RateType, String CreditLimit, 
				String ARAccount, String Currency, String LocationCode, String LocationName, String SalesMan, String SalesManGroup, String BranchName, String Zone, String DeliveryMan, String DeliveryDay,
				String SchedualDay, String Address1, String Address2, String Address3, String City, String Country, String PostalCode, String DeliveryAddress1, String DeliveryAddress2, String DeliveryAddress3,
				String DeliveryCity, String DeliveryCountry, String DeliveryPostalCode, String Person1, String Person2, String OfficeNo, String PhoneNo1, String PhoneNo2, String FaxNo, String Email,
				String Remarks, String DeviceNo, String BillingPersonName, String BillingContactNo, String BillingAddress, String BillingEmail) {
			super();

			this.CODE = Code;
			this.NAME = Name;
			this.ALIAS = Alias;
			this.COMPANYREGNO = CompanyRegNo;
			this.ISGST = IsGst;
			this.GSTREGNO = GstRegNo;
			this.CREDITTERMS = CreditTerms;
			this.TERMSTOLERANCE = TermsTolerance;
			this.RATETYPE = RateType;
			this.CREDITLIMIT = CreditLimit;
			this.ARACCOUNT = ARAccount;
			this.CURRENCY = Currency;
			this.LOCATIONCODE = LocationCode;
			this.LOCATIONNAME = LocationName;
			this.SALESMAN = SalesMan;
			this.SALESMANGROUP = SalesManGroup;
			this.BRANCHNAME = BranchName;
			this.ZONE = Zone;
			this.DELIVERYMAN = DeliveryMan;
			this.DELIVERYDAY = DeliveryDay;
			this.SCHEDUALDAY = SchedualDay;
			this.ADDRESS1 = Address1;
			this.ADDRESS2 = Address2;
			this.ADDRESS3 = Address3;
			this.CITY = City;
			this.COUNTRY = Country;
			this.POSTALCODE = PostalCode;
			this.DELIVERYADDRESS1 = DeliveryAddress1;
			this.DELIVERYADDRESS2 = DeliveryAddress2;
			this.DELIVERYADDRESS3 = DeliveryAddress3;
			this.DELIVERYCITY = DeliveryCity;
			this.DELIVERYCOUNTRY = DeliveryCountry;
			this.DELIVERYPOSTALCODE = DeliveryPostalCode;
			this.PERSON1 = Person1;
			this.PERSON2 = Person2;
			this.OFFICENO = OfficeNo;
			this.PHONENO1 = PhoneNo1;
			this.PHONENO2 = PhoneNo2;
			this.FAXNO = FaxNo;
			this.EMAIL = Email;
			this.REMARKS = Remarks;
			this.DEVICENO = DeviceNo;
			this.BILLINGPERSONNAME = BillingPersonName;
			this.BILLINGCONTACTNO = BillingContactNo;
			this.BILLINGADDRESS = BillingAddress;
			this.BILLINGEMAIL = BillingEmail;

		}



	}

	ArrayList<ExcelData> DataList = new ArrayList<>();

	List<String> CustomerList = new ArrayList<String>();
	Set<String> CustomerSet = new LinkedHashSet<String>();

	@Test(priority = 8, dataProvider = "Util2")
	public void GetData(String Code, String Name, String Alias, String CompanyRegNo, String IsGst, String GstRegNo, String CreditTerms, String TermsTolerance, String RateType, String CreditLimit, 
			String ARAccount, String Currency, String LocationCode, String LocationName, String SalesMan, String SalesManGroup, String BranchName, String Zone, String DeliveryMan, String DeliveryDay,
			String SchedualDay, String Address1, String Address2, String Address3, String City, String Country, String PostalCode, String DeliveryAddress1, String DeliveryAddress2, String DeliveryAddress3,
			String DeliveryCity, String DeliveryCountry, String DeliveryPostalCode, String Person1, String Person2, String OfficeNo, String PhoneNo1, String PhoneNo2, String FaxNo, String Email,
			String Remarks, String DeviceNo, String BillingPersonName, String BillingContactNo, String BillingAddress, String BillingEmail) {

		ExcelData data = new ExcelData(Code, Name, Alias, CompanyRegNo, IsGst, GstRegNo, CreditTerms, TermsTolerance, RateType, CreditLimit, 
				ARAccount, Currency, LocationCode, LocationName, SalesMan, SalesManGroup, BranchName, Zone, DeliveryMan, DeliveryDay, 
				SchedualDay, Address1, Address2, Address3, City, Country, PostalCode, DeliveryAddress1, DeliveryAddress2, DeliveryAddress3, 
				DeliveryCity, DeliveryCountry, DeliveryPostalCode, Person1, Person2, OfficeNo, PhoneNo1, PhoneNo2, FaxNo, Email, 
				Remarks, DeviceNo, BillingPersonName, BillingContactNo, BillingAddress, BillingEmail);

		DataList.add(data);

	}
	@Ignore
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

	//@Ignore
	@Test(priority = 10)
	public void CustomerCreate() throws InterruptedException {

		CustomerList.addAll(CustomerSet);

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Customers cc = new Customers(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;


		int DataListsize = DataList.size();
		System.out.println("DataListsize:" +DataListsize);

		for (int i = 0; i < DataListsize; i++) {
			ExcelData excelData = DataList.get(i);

			driver.navigate().to(url + "SalesPurchases/Customer");
			Thread.sleep(4000);

			click(cc.Create);
			Thread.sleep(2000);
			Sendkeys(cc.Name, excelData.NAME);
			Sendkeys(cc.Alias, excelData.ALIAS);
			Sendkeys(cc.CompRegNo, excelData.COMPANYREGNO);
			WebElement GstRegCheckbox = driver.findElement(By.id("IsGST"));
			js.executeScript("arguments[0].click();", GstRegCheckbox);
			Thread.sleep(1000);
			Sendkeys(cc.GSTRegNo, excelData.GSTREGNO);
			click(cc.CreditTerms);
			WebElement CreditTermsSearchField = driver.findElement
					(By.xpath("//span[@id='select2-PaymentTermsId-container']//following::input[@type='search']"));
			CreditTermsSearchField.sendKeys(excelData.CREDITTERMS +Keys.ENTER);
			click(cc.TermsTolerance);
			WebElement TermsToleranceSearchField = driver.findElement
					(By.xpath("//span[@id='select2-ToleranceId-container']//following::input[@type='search']"));
			TermsToleranceSearchField.sendKeys(excelData.CREDITTERMS +Keys.ENTER);			
			click(cc.ARAccount);
			WebElement ARAccountSearchField = driver.findElement
					(By.xpath("(//span[@id='select2-ChartOfAccountsId-container']//following::input[@type='search'])[1]"));
			ARAccountSearchField.sendKeys(excelData.ARACCOUNT +Keys.ENTER);
			click(cc.Currency);
			WebElement CurrencySearchField = driver.findElement
					(By.xpath("//span[@id='select2-CurrencyId-container']//following::input[@type='search']"));
			CurrencySearchField.sendKeys(excelData.CURRENCY +Keys.ENTER);
			WebElement MultiLoccheckbox = driver.findElement(By.xpath("//input[@id='IsMultipleLocation']")); 
			js.executeScript("arguments[0].click();", MultiLoccheckbox);
			click(cc.LocationCode);
			cc.LocationCode.sendKeys(Keys.CONTROL +"a");
			cc.LocationCode.sendKeys(Keys.DELETE); 
			Sendkeys(cc.LocationCode, excelData.LOCATIONCODE);
			click(cc.LocationName);
			cc.LocationName.sendKeys(Keys.CONTROL +"a");
			cc.LocationName.sendKeys(Keys.DELETE);
			Sendkeys(cc.LocationName, excelData.LOCATIONNAME);
			click(cc.Salesman);
			WebElement SalesManSearchField = driver.findElement
					(By.xpath("(//span[@id='select2-SalesManId-container']//following::input[@type='search'])[1]"));
			SalesManSearchField.sendKeys(excelData.SALESMAN +Keys.ENTER);
			click(cc.Zone);
			WebElement ZoneSearchField = driver.findElement
					(By.xpath("(//span[@id='select2-ZoneId-container']//following::input[@type='search'])[1]"));
			ZoneSearchField.sendKeys(excelData.ZONE +Keys.ENTER);
			click(cc.DeliveryMan);
			WebElement DeliverymanSearchField = driver.findElement
					(By.xpath("//span[@id='select2-DeliveryManId-container']//following::input[@type='search']"));
			DeliverymanSearchField.sendKeys(excelData.DELIVERYMAN +Keys.ENTER);
			click(cc.DeliveryDay);
			WebElement DeliveryDaySearchField = driver.findElement
					(By.xpath("//span[@id='select2-DeliveryDay-container']//following::input[@type='search']"));
			DeliveryDaySearchField.sendKeys(excelData.DELIVERYDAY +Keys.ENTER);
			click(cc.ScheduleDay);
			WebElement ScheduleDaySearchField = driver.findElement
					(By.xpath("//span[@id='select2-ScheduleDay-container']//following::input[@type='search']"));
			ScheduleDaySearchField.sendKeys(excelData.SCHEDUALDAY +Keys.ENTER);
			Sendkeys(cc.Address1, excelData.ADDRESS1);
			Sendkeys(cc.Address2, excelData.ADDRESS2);
			Sendkeys(cc.City, excelData.CITY);			
			click(cc.Country);
			WebElement CountrySearchField = driver.findElement
					(By.xpath("(//span[@id='select2-CountryId-container']//following::input[@type='search'])[1]"));
			CountrySearchField.sendKeys(excelData.COUNTRY +Keys.ENTER);
			Sendkeys(cc.PostalCode, excelData.POSTALCODE);
			Sendkeys(cc.DeliveryAddress1, excelData.DELIVERYADDRESS1);
			Sendkeys(cc.DeliveryAddress2, excelData.DELIVERYADDRESS2);
			Sendkeys(cc.DeliveryCity, excelData.DELIVERYCITY);
			click(cc.DeliveryCountry);
			WebElement DelCountrySearchField = driver.findElement
					(By.xpath("(//span[@id='select2-DeliveryCountryId-container']//following::input[@type='search'])[1]"));
			DelCountrySearchField.sendKeys(excelData.DELIVERYCOUNTRY +Keys.ENTER);
			Sendkeys(cc.DeliveryPostalCode, excelData.DELIVERYPOSTALCODE);
			
			
		






		}



	}




































}
