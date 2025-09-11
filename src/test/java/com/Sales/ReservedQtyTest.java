package com.Sales;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BaseClass.BaseClass;
import com.PomClass.Login;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReservedQtyTest extends BaseClass{
	
	private String url;

	SoftAssert soft = new SoftAssert();

	@Parameters({ "env" })
	@BeforeTest
	public void Environment(String env) {

		if (env.equals("Dev")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://erp.dev1.adaptivegroups.asia/ERP/Account/Login");
			url = "https://erp.dev1.adaptivegroups.asia/ERP/";

		}

	}

	@Test(priority = 1)
	public void ERPLoginPage() throws InterruptedException {

		Login lo = new Login(driver);

		Sendkeys(lo.CompanyCode, "UITDEMO1");
		Sendkeys(lo.UserName, "Kiran01");
		Sendkeys(lo.Password, "Adaptive*123");
		click(lo.LoginButton);
		Thread.sleep(2000);

		String ActURL = driver.getCurrentUrl();
		boolean equals = url.equalsIgnoreCase(ActURL);

		if (equals == false) {

			Navigate_to(ActURL);
			Sendkeys(lo.CompanyCode, "UITDEMO1");
			Sendkeys(lo.UserName, "Kiran02");
			Sendkeys(lo.Password, "Adaptive*123");
			Thread.sleep(1000);
			click(lo.LoginButton);

		}

		String ActURL1 = driver.getCurrentUrl();
		boolean equals1 = url.equalsIgnoreCase(ActURL1);

		if (equals1 == false) {

			Navigate_to(ActURL1);
			Sendkeys(lo.CompanyCode, "UITDEMO1");
			Sendkeys(lo.UserName, "Kiran03");
			Sendkeys(lo.Password, "Adaptive*123");
			Thread.sleep(1000);
			click(lo.LoginButton);

		}

		System.out.println("*ERP Login Page*");
	}

	@DataProvider
	public Object[][] Util1() {

		Object data[][] = Util1.getTestData("C:\\Adaptive\\ERP\\SalesOrderExcelTemp.xlsx", "Sheet1");
		return data;

	}

	@SuppressWarnings("unused")
	class ExcelData {
		private String Customer;
		private String CurrencyCode;
		private String CurrencyRate;
		private String GstType;
		private String Type;
		private String ProductName;
		private String Uom;
		private String Qty;
		private String Foc;
		private String DiscountPercentage;
		private String DiscountAmount;
		private String UnitDiscCheckbox;
		private String UnitDiscPercentage;
		private String UnitDiscAmount;
		private String Price;
		private String IsSpecialPriceCheckbox;
		private String SpecialPrice;
		private String OverAllDiscountType;
		private String OverAllDiscountAmount;
		private String OverAllDiscPercentage;
		private String GstPercentage;
		private String ZeroGst;

		public ExcelData(String Customer, String CurrencyCode, String CurrancyRate, String GstType, String Type, String ProductName, String Uom,
				String Qty, String Foc, String DiscountPercentage, String DiscountAmount, String UnitDiscCheckbox, String UnitDiscPercentage,
				String UnitDiscAmount, String Price, String IsSpecialPriceCheckbox, String SpecialPrice, String OverAllDiscountType, String OverAllDiscountAmount,
				String OverAllDiscPercentage, String GstPercentage, String ZeroGst) {
			super();

			this.Customer = Customer;
			this.CurrencyCode = CurrencyCode;
			this.CurrencyRate = CurrancyRate;
			this.GstType = GstType;
			this.Type = Type;
			this.ProductName = ProductName;
			this.Uom = Uom;
			this.Qty = Qty;
			this.Foc = Foc;
			this.DiscountPercentage = DiscountPercentage;
			this.DiscountAmount = DiscountAmount;
			this.UnitDiscCheckbox = UnitDiscCheckbox;
			this.UnitDiscPercentage = UnitDiscPercentage;
			this.UnitDiscAmount = UnitDiscAmount;
			this.Price = Price;
			this.IsSpecialPriceCheckbox = IsSpecialPriceCheckbox;
			this.SpecialPrice = SpecialPrice;
			this.OverAllDiscountType = OverAllDiscountType;
			this.OverAllDiscountAmount = OverAllDiscountAmount;
			this.OverAllDiscPercentage = OverAllDiscPercentage;
			this.GstPercentage = GstPercentage;
			this.ZeroGst = ZeroGst;

		}

	}

	Set<String> ExcelUniqueProductDataSet = new LinkedHashSet<String>();
	ArrayList<ExcelData> excelDataList = new ArrayList<>();

	List<String> ProductList = new ArrayList<String>();
	Set<String> ProductSet = new LinkedHashSet<String>();
	List<String> ServiceList = new ArrayList<>();
	Set<String> ServiceSet = new LinkedHashSet<>();

	@Test(priority = 4, dataProvider = "Util1", dependsOnMethods = "ERPLoginPage")
	public void GetData(String Customer, String CurrencyCode, String CurrancyRate, String GstType, String Type, String ProductName, String Uom,
			String Qty, String Foc, String DiscountPercentage, String DiscountAmount, String UnitDiscCheckbox, String UnitDiscPercentage,
			String UnitDiscAmount, String Price, String IsSpecialPriceCheckbox, String SpecialPrice, String OverAllDiscountType, String OverAllDiscountAmount,
			String OverAllDiscPercentage, String GstPercentage, String ZeroGst) {

		ExcelData data = new ExcelData(Customer, CurrencyCode, CurrancyRate, GstType, Type, ProductName, Uom, Qty, Foc, DiscountPercentage, 
				DiscountAmount, UnitDiscCheckbox, UnitDiscPercentage, UnitDiscAmount, Price, IsSpecialPriceCheckbox, SpecialPrice, 
				OverAllDiscountType, OverAllDiscountAmount, OverAllDiscPercentage, GstPercentage, ZeroGst);

		excelDataList.add(data);
		ExcelUniqueProductDataSet.add(ProductName);
		if (Type.contains("Product")) {
			ProductSet.add(ProductName);

		}

		if (Type.contains("Service")) {
			ServiceSet.add(ProductName);

		}

	}
	
	
	


}
