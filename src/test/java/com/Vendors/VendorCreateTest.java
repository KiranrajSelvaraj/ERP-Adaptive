package com.Vendors;

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
import com.PomClass.Login;
import com.PomClass.Vendors;
import com.Utility.Util1;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VendorCreateTest extends BaseClass {


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

		Object data[][] = Util1.getTestData("C:\\Adaptive\\Automation\\Bizapp\\Vendor.xlsx", "Sheet1");
		return data;

	}

	@SuppressWarnings("unused")
	class ExcelData {

		private String VendorName;
		private String Currency;
		private String GstType;
		private String VendorType;

		public ExcelData(String VendorName, String Currency, String GstType, String VendorType) {
			super();

			this.VendorName = VendorName;
			this.Currency = Currency;
			this.GstType = GstType;
			this.VendorType = VendorType;
		}
	}

	ArrayList<ExcelData> excelDataList = new ArrayList<>();
	List<String> VendorList = new ArrayList<String>();
	Set<String> VendorSet = new LinkedHashSet<String>();

	@Test(priority = 4, dataProvider = "Util1", dependsOnMethods = "ERPLoginPage")
	public void GetData(String VendorName, String Currency, String GstType, String VendorType) {

		ExcelData data = new ExcelData(VendorName, Currency, GstType, VendorType);

		excelDataList.add(data);	
	}
	
	@Test(priority = 9)
	public void VendorCreate() throws InterruptedException {
		VendorList.addAll(VendorSet);
		
		driver.navigate().to(url +"SalesPurchases/Vendor");
		Thread.sleep(4000);
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		Vendors vd = new Vendors(driver);
		
		click(vd.AddVendor);
		Thread.sleep(3000);

		int dataListSize = excelDataList.size();
		System.out.println("dataListSize: " +dataListSize);
		for (int i = 0; i < dataListSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			click(vd.VendorName);
			Sendkeys(vd.VendorName, excelData.VendorName);
			click(vd.Currency);
			driver.findElement(By.xpath("//span[@id='select2-CurrencyId-container']//following::input[@type='search']"))
			.sendKeys(excelData.Currency +Keys.ENTER);
			click(vd.GSTType);
			driver.findElement(By.xpath("//span[@id='select2-GSTTypeId-container']//following::input[@type='search']"))
			.sendKeys(excelData.GstType +Keys.ENTER);
			click(vd.VendorType);
			driver.findElement(By.xpath("//span[@id='select2-VendorTypeId-container']//following::input[@type='search']"))
			.sendKeys(excelData.VendorType +Keys.ENTER);
			Thread.sleep(3000);
			
		//	click(vd.Save);
			try {
				String alertText = driver.findElement(By.id("popup_message")).getText();
				System.out.println("alertText: "+alertText);

			} catch (Exception e) {
				System.out.println("No alert appeared after save");
			}


		} // Excel data loop

	} // Method loop


} // Main class loop
