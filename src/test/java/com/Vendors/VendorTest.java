package com.Vendors;

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
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import com.PomClass.Login;
import com.PomClass.PurchaseOrder;
import com.PomClass.Vendors;
import com.Utility.Util1;
import com.Vendors.VendorTest.ExcelData;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VendorTest extends BaseClass {
	
	
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
	public Object[][] Util2(){
		Object[][] data = Util1.getTestData("C:\\Adaptive\\ERP\\Vendors1.xlsx", "sheet1");
		return data;
					
	}
	
	class ExcelData {
		private String CODE;
		private String NAME;
		private String REGNO;
		private String ISGST;
		private String GSTREGNO;
		private String GSTTYPE;
		private String CONTACTPERSON;
		private String MOBILENO;
		private String VENDORTYPE;
		private String NONTRADECREDITOR;
		private String APACCOUNT;
		private String REMARKS;
		private String ADDRESS1;
		private String ADDRESS2;
		private String ADDRESS3;
		private String CITY;
		private String STATE;
		private String COUNTRY;
		private String POSTALCODE;
		private String PHONENO1;
		private String PHONENO2;
		private String PHONENO3;
		private String FAX;
		private String URL;
		private String EMAIL;
		private String PAYMENTTERMS;
		private String DEBITLIMIT;
		
		public ExcelData(String Code, String Name, String RegNo, String IsGst, String GstRegNo, String GstType, String ContactPerson, String MobileNo, String VendorType, String NonTradeCreditor,
				String ApAccount, String Remarks, String Address1, String Address2, String Address3, String City, String State, String Country, String PostalCode,String PhoneNo1,
				String PhoneNo2, String PhoneNo3, String Fax, String Url, String Email, String PaymentTerms, String DebitLimit) {
			super();
			
			this.CODE = Code;
			this.NAME = Name;
			this.REGNO = RegNo;
			this.ISGST = IsGst;
			this.GSTREGNO = GstRegNo;
			this.GSTTYPE = GstType;
			this.CONTACTPERSON = ContactPerson;
			this.MOBILENO = MobileNo;
			this.VENDORTYPE = VendorType;
			this.NONTRADECREDITOR = NonTradeCreditor;
			this.APACCOUNT = ApAccount;
			this.REMARKS = Remarks;
			this.ADDRESS1 = Address1;
			this.ADDRESS2 = Address2;
			this.ADDRESS3 = Address3;
			this.CITY = City;
			this.STATE = State;
			this.COUNTRY = Country;
			this.POSTALCODE = PostalCode;
			this.PHONENO1 = PhoneNo1;
			this.PHONENO2 = PhoneNo2;
			this.PHONENO3 = PhoneNo3;
			this.FAX = Fax;
			this.URL = Url;
			this.EMAIL = Email;
			this.PAYMENTTERMS = PaymentTerms;
			this.DEBITLIMIT = DebitLimit;
			
		}	
		
	}
	
	ArrayList<ExcelData> DataList = new ArrayList<>();
	List<String> VendorList = new ArrayList<String>();
	Set<String> VendorSet = new LinkedHashSet<String>();
	
	@Test(priority = 8, dataProvider = "Util2")
	public void GetData(String Code, String Name, String RegNo, String IsGst, String GstRegNo, String GstType, String ContactPerson, String MobileNo, String VendorType, String NonTradeCreditor,
				String ApAccount, String Remarks, String Address1, String Address2, String Address3, String City, String State, String Country, String PostalCode,String PhoneNo1,
				String PhoneNo2, String PhoneNo3, String Fax, String Url, String Email, String PaymentTerms, String DebitLimit) {
		
		ExcelData data = new ExcelData(Code, Name, RegNo, IsGst, GstRegNo, GstType, ContactPerson, MobileNo, VendorType, NonTradeCreditor, 
				ApAccount, Remarks, Address1, Address2, Address3, City, State, Country, PostalCode, PhoneNo1, 
				PhoneNo2, PhoneNo3, Fax, Url, Email, PaymentTerms, DebitLimit);
		
		DataList.add(data);
					
	}
	
	@Test(priority = 9)
	public void VendorCreate() throws InterruptedException {
		
		VendorList.addAll(VendorSet);
		
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		Vendors vc = new Vendors(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		int dataListSize = DataList.size();
		System.out.println("dataListSize: " +dataListSize);
		
		for (int i = 0; i < dataListSize; i++) {
			ExcelData excelData = DataList.get(i);
			
			driver.navigate().to(url +"SalesPurchases/Vendor");
			Thread.sleep(4000);
			
			click(vc.Create);
			Sendkeys(vc.Code, excelData.CODE);
			Sendkeys(vc.VendorName, excelData.NAME);
			Sendkeys(vc.RegistrationNo, excelData.REGNO);
			WebElement IsGstCheckbox = driver.findElement(By.id("IsGST"));
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", IsGstCheckbox);
			Sendkeys(vc.GSTRegNo, excelData.GSTREGNO);
			click(vc.GSTType);
			WebElement GstTypesearchField = driver.findElement
					(By.xpath("//span[@id='select2-GSTTypeId-container']//following::input[@type='search']"));
			GstTypesearchField.click();
			Thread.sleep(1000);
			GstTypesearchField.sendKeys(excelData.GSTTYPE + Keys.ENTER);
			Sendkeys(vc.ContactPerson, excelData.CONTACTPERSON);
			Sendkeys(vc.MobileNo, excelData.MOBILENO);
			click(vc.VendorType);
			WebElement VendorTypesearchField = driver.findElement
					(By.xpath("//span[@id='select2-VendorTypeId-container']//following::input[@type='search']"));
			VendorTypesearchField.click();
			Thread.sleep(1000);
			VendorTypesearchField.sendKeys(excelData.VENDORTYPE + Keys.ENTER);
			click(vc.APAccount);
			WebElement APAccountsearchField = driver.findElement
					(By.xpath("//span[@id='select2-ChartOfAccountsId-container']//following::input[@type='search']"));
			APAccountsearchField.click();
			Thread.sleep(1000);
			APAccountsearchField.sendKeys(excelData.APACCOUNT + Keys.ENTER);
			Sendkeys(vc.Address1, excelData.ADDRESS1);
			Sendkeys(vc.Address2, excelData.ADDRESS2);
			Sendkeys(vc.Address3, excelData.ADDRESS3);
			Sendkeys(vc.City, excelData.CITY);
			click(vc.State);
			WebElement StatesearchField = driver.findElement
					(By.xpath("//span[@id='select2-StateId-container']//following::input[@type='search']"));
			StatesearchField.click();
			Thread.sleep(1000);
			StatesearchField.sendKeys(excelData.STATE + Keys.ENTER);
			click(vc.Country);
			WebElement CountrysearchField = driver.findElement
					(By.xpath("//span[@id='select2-CountryId-container']//following::input[@type='search']"));
			CountrysearchField.click();
			Thread.sleep(1000);
			CountrysearchField.sendKeys(excelData.COUNTRY + Keys.ENTER);
			Sendkeys(vc.PostalCode, excelData.POSTALCODE);
			Thread.sleep(1000);
			click(vc.ContactTab);
			Sendkeys(vc.PhoneNo1, excelData.PHONENO1);
			Sendkeys(vc.PhoneNo2, excelData.PHONENO2);
			Thread.sleep(1000);
			click(vc.paymentsTab);
			click(vc.PaymentTerms);
			WebElement PaymentTermssearchField = driver.findElement
					(By.xpath("//span[@id='select2-PaymentTermsId-container']//following::input[@type='search']"));
			PaymentTermssearchField.click();
			Thread.sleep(1000);
			PaymentTermssearchField.sendKeys(excelData.PAYMENTTERMS + Keys.ENTER);
			Sendkeys(vc.DebitLimit, excelData.DEBITLIMIT);
			
			
			
			
			
		}
		
		
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
