package com.Demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BaseClass.BaseClass;
import com.PomClass.*;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoProjSalesOrderToInvoiceTest extends BaseClass {

	private String CompanyCode;
	private String Environment;
	private String CompanyName;

	private String url;
	SoftAssert soft = new SoftAssert();

	@org.testng.annotations.Parameters({"env", "Company"})
	@BeforeTest
	public void Environment(String env, String Company) {

		if (env.equals("demo") && Company.equals("amc")) {
			Environment = "demo";
			CompanyCode = "amc";
			CompanyName = "amc";
			System.out.println("Login into " + Environment + " Site for " + CompanyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://demo.adaptivebizapp.com/Account/Login");
			url = "https://demo.adaptivebizapp.com/ERP/";

		} else if (env.equals("dev") && Company.equals("Uitdemo1")) {
			Environment = "dev";
			CompanyCode = "Uitdemo1";
			CompanyName = "Uitdemo1";
			System.out.println("Login into " + Environment + " Site for " + CompanyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://demo.adaptivebizapp.com/Account/Login");
			url = "https://demo.adaptivebizapp.com/ERP/";

		}

	}

	@Test(priority = 1)
	public void ERPLoginPage() throws InterruptedException {

		Login lo = new Login(driver);

		Sendkeys(lo.CompanyCode, CompanyCode);
		Sendkeys(lo.UserName, "chitra");
		Sendkeys(lo.Password, "Adaptive*123");
		Thread.sleep(3000);
		click(lo.LoginButton);
		Thread.sleep(2000);

		String ActURL = driver.getCurrentUrl();
		boolean equals = url.equalsIgnoreCase(ActURL);

		if (equals == false) {

			Navigate_to(ActURL);
			Sendkeys(lo.CompanyCode, CompanyCode);
			Sendkeys(lo.UserName, "Kiran");
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

		Object data[][] = Util1.getTestData("C:\\Adaptive\\Automation\\Bizapp\\DemoSalesExcel3.xlsx", "Sheet1");
		return data;

	}

	@SuppressWarnings("unused")
	class ExcelData {
		private String Customer;
		private String ProjectName;
		private String CurrencyCode;
		private String CurrencyRate;
		private String GstType;
		private String Type;
		private String ProductCode;
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
		private String OverAllDiscountPercentage;
		private String GstPercentage;
		private String ZeroGst;
		private String BatchProduct;
		private String CreditNotesQty;

		public ExcelData(String Customer, String ProjectName, String CurrencyCode, String CurrencyRate, String GstType, String Type,
				String ProductCode, String ProductName, String Uom, String Qty, String Foc, String DiscountPercentage,
				String DiscountAmount, String UnitDiscCheckbox, String UnitDiscPercentage, String UnitDiscAmount,
				String Price, String IsSpecialPriceCheckbox, String SpecialPrice, String OverAllDiscountType,
				String OverAllDiscountAmount, String OverAllDiscountPercentage, String GstPercentage, 
				String ZeroGst, String BatchProduct, String CreditNotesQty) {
			super();

			this.Customer = Customer;
			this.ProjectName = ProjectName;
			this.CurrencyCode = CurrencyCode;
			this.CurrencyRate = CurrencyRate;
			this.GstType = GstType;
			this.Type = Type;
			this.ProductCode = ProductCode;
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
			this.OverAllDiscountPercentage = OverAllDiscountPercentage;
			this.GstPercentage = GstPercentage;
			this.ZeroGst = ZeroGst;
			this.BatchProduct = BatchProduct;
			this.CreditNotesQty = CreditNotesQty;

		}

	}

	Set<String> ExcelUniqueProductDataSet = new LinkedHashSet<String>();
	ArrayList<ExcelData> excelDataList = new ArrayList<>();

	List<String> ProductList = new ArrayList<String>();
	Set<String> ProductSet = new LinkedHashSet<String>();
	List<String> ServiceList = new ArrayList<>();
	Set<String> ServiceSet = new LinkedHashSet<>();

	@Test(priority = 4, dataProvider = "Util1", dependsOnMethods = "ERPLoginPage")
	public void GetData(String Customer, String ProjectName, String CurrencyCode, String CurrencyRate, String GstType, String Type,
			String ProductCode, String ProductName, String Uom, String Qty, String Foc, String DiscountPercentage,
			String DiscountAmount, String UnitDiscCheckbox, String UnitDiscPercentage, String UnitDiscAmount,
			String Price, String IsSpecialPriceCheckbox, String SpecialPrice, String OverAllDiscountType,
			String OverAllDiscountAmount, String OverAllDiscountPercentage, String GstPercentage, 
			String ZeroGst, String BatchProduct, String CreditNotesQty) {

		ExcelData data = new ExcelData(Customer, ProjectName, CurrencyCode, CurrencyRate, GstType, Type, ProductCode, ProductName,
				Uom, Qty, Foc, DiscountPercentage, DiscountAmount, UnitDiscCheckbox, UnitDiscPercentage, UnitDiscAmount,
				Price, IsSpecialPriceCheckbox, SpecialPrice, OverAllDiscountType, OverAllDiscountAmount,
				OverAllDiscountPercentage, GstPercentage, ZeroGst, BatchProduct, CreditNotesQty);

		excelDataList.add(data);
		ExcelUniqueProductDataSet.add(ProductCode);
		if (Type.contains("Product")) {
			ProductSet.add(ProductCode);

		}

		if (Type.contains("Service")) {
			ServiceSet.add(ProductCode);

		}

	}


	private boolean IsSalesManManagement;
	private boolean IsWarehouseManagement;
	private boolean IsWarehouseStorageManagement;
	private boolean IsBarcodeEnabled;
	private boolean IsBarcodeManagementInsales;
	private boolean IsMultiWordSearchInProduct;
	private boolean IsDuplicateProductsInInvoice;
	private boolean IsCartonManagement;
	private boolean IsEnableItemLevelDiscountInSales;
	private boolean IsFOCManagementInSO;
	private String BulkQtyMeasurement;
	private String LooseQtyMeasurement;
	private boolean IsAllowToEditSpecialPrice;
	private float DecimalCalculationForSales;
	private boolean IsOpenItemManagementInsales;
	private boolean IsHeaderManagementInSO;
	private boolean IsReturnManagementInSI;

	//@Ignore
	@Test(priority = 6, dependsOnMethods = "ERPLoginPage")
	public void SystemSettings() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.navigate().to(url + "SystemSetting");
		Thread.sleep(5000);
		SystemSettings ss = new SystemSettings(driver);

		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsSalesManManagement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsSalesManManagement = ss.BooleanValue.isSelected();
		System.out.println("IsSalesManManagement :" + IsSalesManManagement);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsWarehouseManagement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsWarehouseManagement = ss.BooleanValue.isSelected();
		System.out.println("IsWarehouseManagement :" + IsWarehouseManagement);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsWarehouseStorageManagement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsWarehouseStorageManagement = ss.BooleanValue.isSelected();
		System.out.println("IsWarehouseStorageManagement :" + IsWarehouseStorageManagement);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsBarcodeEnabled");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsBarcodeEnabled = ss.BooleanValue.isSelected();
		System.out.println("IsBarcodeEnabled :" + IsBarcodeEnabled);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsBarcodeManagementInsales");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsBarcodeManagementInsales = ss.BooleanValue.isSelected();
		System.out.println("IsBarcodeManagementInsales :" + IsBarcodeManagementInsales);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsMultiWordSearchInProduct");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsMultiWordSearchInProduct = ss.BooleanValue.isSelected();
		System.out.println("IsMultiWordSearchInProduct :" + IsMultiWordSearchInProduct);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsDuplicateProductsInInvoice");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsDuplicateProductsInInvoice = ss.BooleanValue.isSelected();
		System.out.println("IsDuplicateProductsInInvoice :" + IsDuplicateProductsInInvoice);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsCartonManagement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsCartonManagement = ss.BooleanValue.isSelected();
		System.out.println("IsCartonManagement :" + IsCartonManagement);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsEnableItemLevelDiscountInSales");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsEnableItemLevelDiscountInSales = ss.BooleanValue.isSelected();
		System.out.println("IsEnableItemLevelDiscountInSales :" + IsEnableItemLevelDiscountInSales);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsFOCManagementInSO");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsFOCManagementInSO = ss.BooleanValue.isSelected();
		System.out.println("IsFOCManagementInSO :" + IsFOCManagementInSO);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "BulkQtyMeasurement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		BulkQtyMeasurement = driver.findElement(By.id("StringValue"))
				.getAttribute("value");
		System.out.println("BulkQtyMeasurement :" + BulkQtyMeasurement);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "LooseQtyMeasurement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		LooseQtyMeasurement = driver.findElement(By.id("StringValue"))
				.getAttribute("value");
		System.out.println("LooseQtyMeasurement :" + LooseQtyMeasurement);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsAllowToEditSpecialPrice");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsAllowToEditSpecialPrice = ss.BooleanValue.isSelected();
		System.out.println("IsAllowToEditSpecialPrice :" + IsAllowToEditSpecialPrice);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "DecimalCalculationForSales");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		String DecimalCalculationForSalesString = driver.findElement(By.id("DecimalValue"))
				.getAttribute("value");
		DecimalCalculationForSales = Float.parseFloat(DecimalCalculationForSalesString);
		System.out.println("DecimalCalculationForSales :" + DecimalCalculationForSales);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsOpenItemManagementInsales");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsOpenItemManagementInsales = ss.BooleanValue.isSelected();
		System.out.println("IsOpenItemManagementInsales :" + IsOpenItemManagementInsales);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsHeaderManagementInSO");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsHeaderManagementInSO = ss.BooleanValue.isSelected();
		System.out.println("IsHeaderManagementInSO :" + IsHeaderManagementInSO);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsReturnManagementInSI");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsReturnManagementInSI = ss.BooleanValue.isSelected();
		System.out.println("IsReturnManagementInSI :" + IsReturnManagementInSI);
		click(ss.Back);

		System.out.println();
	}

	//Product Page:-
	@SuppressWarnings("unused")
	class product {

		private String productCode;
		private String productName;
		private String departmentValue;
		private String categoryValue;
		private String brandValue;
		private String profitMarginValue;
		private String marginToleranceValue;
		private String vendorNameValue;
		private String purchaseCOAValue;
		private String salesCOAValue;
		private String currentStockValue;
		private String TotalStock;
		private String uomValue;


		public product(String productCode, String productName, String departmentValue, String categoryValue,
				String brandValue, String profitMarginValue, String marginToleranceValue, String vendorNameValue,
				String purchaseCOAValue, String salesCOAValue, String currentStockValue,
				String TotalStock, String uomValue) {
			super();

			this.productCode = productCode;
			this.productName = productName;
			this.departmentValue = departmentValue;
			this.categoryValue = categoryValue;
			this.brandValue = brandValue;
			this.profitMarginValue = profitMarginValue;
			this.marginToleranceValue = marginToleranceValue;
			this.vendorNameValue = vendorNameValue;
			this.purchaseCOAValue = purchaseCOAValue;
			this.salesCOAValue = salesCOAValue;
			this.currentStockValue = currentStockValue;
			this.TotalStock = TotalStock;
			this.uomValue = uomValue;


		}

	}

	@SuppressWarnings("unused")
	class ProductUOM {

		private boolean IsNonCarton;
		private boolean IsBase;
		private int ProductUOMtablesize;
		private String SubUOM;
		private String CurStock;
		private String LPP;
		private String SP;

		public ProductUOM(boolean IsNonCarton, boolean IsBase, int ProductUOMtablesize, String SubUOM, String CurStock,
				String LPP, String SP) {			
			super();

			this.IsNonCarton = IsNonCarton;
			this.IsBase = IsBase;
			this.ProductUOMtablesize = ProductUOMtablesize;
			this.SubUOM = SubUOM;
			this.CurStock = CurStock;
			this.LPP = LPP;
			this.SP = SP;

		}

	}


	List<String> UOMList = new ArrayList<String>();
	Set<String> UOMSet = new LinkedHashSet<String>();

	ArrayList<product> ProductDetailsList = new ArrayList<>();
	ArrayList<ProductUOM> ProductUOMDetailsList = new ArrayList<>();

	@Ignore
	@Test(priority = 8, dependsOnMethods = "ERPLoginPage")
	public void ProductPage() throws InterruptedException {

		ProductList.addAll(ProductSet);

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		Product prod = new Product(driver);

		int productSetSize = ProductSet.size();
		System.out.println("Product Set Size: " + productSetSize);
		for (String product : ProductSet) {

			driver.navigate().to(url + "SalesPurchases/Product");
			Thread.sleep(7000);
			System.out.println("*Product Details Page*");

			WebElement productcode = driver.findElement(By.xpath("(//input[@id='SearchString'])[1]"));
			Thread.sleep(1000);
			productcode.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			productcode.sendKeys(product);
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", prod.Fetch);
			Thread.sleep(3000);

			WebElement DetailsIcon = driver
					.findElement(By.xpath("(//table[@id='producttable']//tbody//tr//td[normalize-space()='"+product+"']//following::td//a[@title='Details'])[1]"));
			js.executeScript("arguments[0].click();", DetailsIcon);
			Thread.sleep(3000);

			String productCodeUom = driver.findElement(By.xpath("//div[@class='col-lg-7']//following::small"))
					.getText();
			String productCode = productCodeUom.split(" - UOM")[0];
			System.out.println("ProductCode: " + productCode);

			String productName = driver.findElement(By.xpath("//div[@class='col-lg-7']//h2//b")).getText();
			System.out.println("ProductName: " + productName);

			String department = driver
					.findElement(By.xpath("//dt[normalize-space()='Department']//following-sibling::dd[1]")).getText();
			System.out.println("Department: " + department);

			String category = driver
					.findElement(By.xpath("//dt[normalize-space()='Category']//following-sibling::dd[1]")).getText();
			System.out.println("Category: " + category);

			String brand = driver
					.findElement(By.xpath("//dt[normalize-space()='Brand']//following-sibling::dd[1]")).getText();
			System.out.println("Brand: " + brand);

			String currentStock = driver
					.findElement(By.xpath("(//dt[normalize-space()='Current Stock - HQ']//following-sibling::dd)[1]")).getText();
			System.out.println("CurrentStock :" + currentStock);

			String totalStock = driver
					.findElement(By.xpath("//dt[normalize-space()='Total Stock']//following-sibling::dd[1]")).getText();
			System.out.println("Total STock: " + totalStock);

			WebElement InfoTab = driver.findElement(By.xpath("//a[text()='Info']"));
			InfoTab.click();
			Thread.sleep(2000);

			String purchaseCOA = driver
					.findElement(By.xpath("//dt[normalize-space()='Purchase COA / GL']//following-sibling::dd[1]")).getText().trim();
			System.out.println("Purchase COA: " + purchaseCOA);

			String salesCOA = driver
					.findElement(By.xpath("//dt[normalize-space()='Sales COA / GL']//following-sibling::dd[1]")).getText().trim();
			System.out.println("Sales COA: " + salesCOA);

			String uom = driver.findElement(By.xpath("//dt[normalize-space()='UOM']//following-sibling::dd[1]")).getText();
			UOMSet.add(uom);
			System.out.println("UOM: " + uom);

			String vendorName = driver
					.findElement(By.xpath("//dt[normalize-space()='Vendor Name']//following-sibling::dd[1]")).getText();		
			System.out.println("Vendor Name: " + vendorName);

			Thread.sleep(3000);
			WebElement stockTab = driver.findElement(By.xpath("//a[text()='Stock']"));
			stockTab.click();
			Thread.sleep(2000);

			boolean IsNonCarton = false;
			boolean IsBase = false;
			int ProductUOMtablesize = 0;

			ProductUOMtablesize = driver.findElements(By.xpath("//table[@id='ProductPartialUOM']//tbody//tr//td[2]"))
					.size();
			System.out.println("ProductUOMtablesize " + ProductUOMtablesize);
			if (ProductUOMtablesize > 0) {
				IsNonCarton = true;

			} else if (ProductUOMtablesize == 0) {
				IsBase = true;
			}

			for (int j = 1; j <= ProductUOMtablesize; j++) {
				String SubUOM = driver
						.findElement(By.xpath("(//table[@id='ProductPartialUOM']//tbody//tr//td[2])[" + j + "]"))
						.getAttribute("data-value");
				System.out.println("SubUOM Value: " + SubUOM);

				String CurStock = driver.findElement(By
						.xpath("(//table[@id='ProductPartialUOM']//tbody//tr//td[3])["+j+"]"))
						.getText().trim();
				System.out.println("CurStockValue: " + CurStock);

				String LPP = driver.findElement(By.xpath(
						"(//table[@id='ProductPartialUOM']//tbody//tr//td//following-sibling::input[@id='LastPurchasePrice'])["+j+"]"))
						.getAttribute("value").trim();
				System.out.println("LPP Value: " + LPP);

				String SP = driver.findElement(By.xpath(
						"(//table[@id='ProductPartialUOM']//tbody//tr//td//following-sibling::input[@id='SellingPrice'])["+j+"]"))
						.getAttribute("value");
				System.out.println("SP Value: " + SP);

				ProductUOM pruom = new ProductUOM(IsNonCarton, IsBase,
						ProductUOMtablesize, SubUOM, CurStock, LPP, SP);
				ProductUOMDetailsList.add(pruom);
				UOMSet.add(uom);

			}

			product pr = new product(productCode, productName, department, category, brand, uom, brand, vendorName, purchaseCOA, 
					salesCOA, currentStock, totalStock, currentStock);										
			ProductDetailsList.add(pr);

			Thread.sleep(2000);
			driver.navigate().back();
			System.out.println("***");

		}
	}

	//Uom Page:-
	@SuppressWarnings("unused")
	public class UOM {
		private String UomCodeValue;
		private String UomNameValue;
		private String UomBaseUomValue;
		private String UomUnits;

		public UOM(String UomCodeValue, String UomNameValue, String UomBaseUomValue, String UomUnits) {
			super();

			this.UomCodeValue = UomCodeValue;
			this.UomNameValue = UomNameValue;
			this.UomBaseUomValue = UomBaseUomValue;
			this.UomUnits = UomUnits;
		}
	}

	ArrayList<UOM> UomDetailsList = new ArrayList<>();
	@Ignore
	@Test(priority = 10, dependsOnMethods = "ERPLoginPage")
	public void UomPage() throws InterruptedException {
		UOMList.addAll(UOMSet);

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.navigate().to(url + "SalesPurchases/UOM");
		Thread.sleep(4000);
		System.out.println("*UOM Page*");

		int uomSetSize = UOMSet.size();
		System.out.println("Uom Set Size: " + uomSetSize);
		for (String uom : UOMSet) {

			driver.findElement(By.id("select2-DropDown-container")).click();
			/*	WebElement UOMSearchField = driver.findElement(
						By.xpath("//span[@id='select2-DropDown-container']//following::input[@type='search']"));
				UOMSearchField.sendKeys(uom + Keys.ENTER);*/

			List<WebElement> uomOption = driver.findElements(By.xpath(
					"//span[@id='select2-DropDown-container']//following::input[@type='search']/following::ul//li"));
			for (WebElement option : uomOption) {
				if (option.getText().trim().equals(uom)) {
					option.click();
					break;
				}
			}

			Thread.sleep(1000);
			WebElement fetchBtn = driver.findElement(By.xpath("//input[@id='searchstring' and @value='Fetch']"));
			js.executeScript("arguments[0].click();", fetchBtn);
			Thread.sleep(3000);

			WebElement SelectDropdown = driver.findElement(By.name("UOMTable_length"));
			Select dropdown = new Select(SelectDropdown);
			dropdown.selectByValue("100");
			Thread.sleep(2000);

			int UOMListTableSize = driver.findElements(By.xpath("//table[@id='UOMTable']//tbody//tr")).size();
			System.out.println("UOM List Table Size: " + UOMListTableSize);

			for (int j = 1; j <= UOMListTableSize; j++) {

				String UOMListCode = driver
						.findElement(By.xpath("(//table[@id='UOMTable']//tbody//tr//td[1])[" + j + "]")).getText()
						.trim();
				System.out.println("UOMListCode: " + UOMListCode);

				String UOMListName = driver
						.findElement(By.xpath("(//table[@id='UOMTable']//tbody//tr//td[2])[" + j + "]")).getText()
						.trim();
				System.out.println("UOMListName: " + UOMListName);

				String UOMListBaseUom = driver
						.findElement(By.xpath("(//table[@id='UOMTable']//tbody//tr//td[3])[" + j + "]")).getText()
						.trim();
				System.out.println("UOMListBaseUom: " + UOMListBaseUom);

				String UOMListUnits = driver
						.findElement(By.xpath("(//table[@id='UOMTable']//tbody//tr//td[4])[" + j + "]")).getText()
						.trim();
				System.out.println("UOMListUnits: " + UOMListUnits);

				System.out.println("***");

				UOM uomdetails = new UOM(UOMListCode, UOMListName, UOMListBaseUom, UOMListUnits);
				UomDetailsList.add(uomdetails);

			}
		}
	}

	// @Ignore
	@Test(priority = 12, dependsOnMethods = "ERPLoginPage")
	public void SalesOrderToInvoice() throws InterruptedException, IOException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 20);

		SalesOrder so = new SalesOrder(driver);

		driver.navigate().to(url + "SalesPurchases/SalesOrderIndex");
		Thread.sleep(5000);		
		System.out.println("*** Sales To Invoice Page ***");

		js.executeScript("arguments[0].click();", so.AddOrder);
		Thread.sleep(3000);

		String getExcelGstType = "";
		String getexcelOverAllDiscountType = "";
		String getExcelOverAllDiscountPercentage = "";
		String getExcelOverAllDiscountAmount = "";
		String getExcelGstPercentage = "";
		String getExcelCurrencyRate = "";
		String productPrice = "";
		String ExpDiscountProductPriceFormat = "";

		double ActDiscountProductPriceDouble = 0;
		double ExpSubTotal = 0;
		double ExpZeroGstProductamount = 0;

		int excelDataListSize = excelDataList.size();
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			if (excelData.Customer.isEmpty() == false) {

				Thread.sleep(2000);
				click(so.Customer);
				WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search'][1]")));
				searchInput.sendKeys(excelData.Customer + Keys.ENTER);
				Thread.sleep(2000);

				driver.findElement(By.id("select2-ProjectId-container")).click();
				driver.findElement(By.xpath("//input[@role='textbox']")).sendKeys(excelData.ProjectName +Keys.ENTER);
			}	
				Thread.sleep(2000);
			driver.findElement(By.xpath("//span[@id='select2-GSTTypeId-container']")).click();
			WebElement GstSearchInput = driver.findElement(By.xpath("//input[@role='textbox']"));					
			GstSearchInput.sendKeys(excelData.GstType + Keys.ENTER);

			if (i == 0) {
				getExcelGstType = excelData.GstType;
				getexcelOverAllDiscountType = excelData.OverAllDiscountType;
				getExcelOverAllDiscountPercentage = excelData.OverAllDiscountPercentage;
				getExcelOverAllDiscountAmount = excelData.OverAllDiscountAmount;
				getExcelGstPercentage = excelData.GstPercentage;
				getExcelCurrencyRate = excelData.CurrencyRate;
			}

			// Choose Product:-
			if (excelData.Type.equalsIgnoreCase("Product")) {

				String productCheckbox = driver.findElement(By.id("ProductCheck")).getAttribute("checked");
				System.out.println("Product Check Box is: " + productCheckbox);
				if (!productCheckbox.equalsIgnoreCase("true")) {
					click(so.ProductCheckBox);

				}

			} else if (excelData.Type.equals("Service")) {

				if (!so.ServiceCheckBox.isSelected()) {
					click(so.ServiceCheckBox);
				}

			} else if (excelData.Type.equals("Open")) {

				if (!so.OpenCheckBox.isSelected()) {
					click(so.OpenCheckBox);
				}

			} else if (excelData.Type.equals("Header")) {

				if (!so.HeaderCheckBox.isSelected()) {
					click(so.HeaderCheckBox);
				}

			}

			if (excelData.Type.equalsIgnoreCase("Product")) {

				click(so.Product);
				driver.findElement(
						By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
				.sendKeys(excelData.ProductName + Keys.ENTER);

			} else if (excelData.Type.equalsIgnoreCase("Service")) {

				driver.findElement(
						By.xpath("//span[@id='select2-ServiceId-container']//following::input[@type='search']"))
				.sendKeys(excelData.ProductName + Keys.ENTER);

			} else if (excelData.Type.equalsIgnoreCase("Open")) {

				so.OpenProduct.sendKeys(excelData.ProductName + Keys.ENTER);

			}
			click(so.Qty);

			if (excelData.Type.equalsIgnoreCase("Product")) {

				click(so.Uom);
				List<WebElement> subUomOption = driver.findElements(By.xpath(
						"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
				for (WebElement option : subUomOption) {
					if (option.getText().trim().equals(excelData.Uom)) {
						option.click();
						break;
					}

				}

			} else if (excelData.Type.equalsIgnoreCase("Service")) {

				click(so.Uom);
				List<WebElement> subUomOption = driver.findElements(By.xpath(
						"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
				for (WebElement option : subUomOption) {
					if (option.getText().trim().equals(excelData.Uom)) {
						option.click();
						break;
					}

				}

			} else if (excelData.Type.equalsIgnoreCase("Open")) {

				click(so.Uom);
				List<WebElement> subUomOption = driver.findElements(By.xpath(
						"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
				for (WebElement option : subUomOption) {
					if (option.getText().trim().equals(excelData.Uom)) {
						option.click();
						break;
					}

				}

			}

			System.out.println();

			// Qoh Calculation:-
			System.out.println("*** Grand Total Calculation With QOH ***");

			String qohStock1 = driver.findElement(By.id("QOH")).getAttribute("value");

			for (product productDetails : ProductDetailsList) {

				if (productDetails.productName.equalsIgnoreCase(excelData.ProductName)) {					

					boolean isNonCartonWithUOM = false;
					double totalCalculatedStock = 0;

					for (UOM uomDetails : UomDetailsList) {
						if (uomDetails.UomNameValue.equalsIgnoreCase(productDetails.uomValue)) {

							double curStockDouble = Double.parseDouble(productDetails.currentStockValue.trim());
							double uomUnitDouble = Double.parseDouble(uomDetails.UomUnits.trim());

							totalCalculatedStock += (curStockDouble * uomUnitDouble);
							isNonCartonWithUOM = true;

						}
					}						

					String totalCalculatedStockString = String.valueOf(totalCalculatedStock);
					String replacetotalCalculatedStock = totalCalculatedStockString.replaceAll("\\.0$", "");

					if (isNonCartonWithUOM) {

						System.out.println("Non Carton Product");
						System.out.println("Product Name: " + productDetails.productName);
						System.out.println("Calculated QOH: " +replacetotalCalculatedStock);
						System.out.println("Current Stock from List: " +productDetails.currentStockValue);
						System.out.println();

						soft.assertEquals(replacetotalCalculatedStock, productDetails.currentStockValue.trim(),
								"Actual and Expected QOH Mismatched for Product: " + productDetails.productName);

					} else {

						System.out.println("Base Product");
						System.out.println("Product Name: " + productDetails.productName);
						System.out.println("QOH from Page: " + qohStock1);
						System.out.println("Current Stock from List: " + productDetails.currentStockValue);
						System.out.println();

						soft.assertEquals(qohStock1, productDetails.currentStockValue,
								"Actual and Expected QOH Mismatched for Product: " + productDetails.productName);

					}


					break;
				}
			}
			System.out.println();


			// Qty:-
			Sendkeys(so.Qty, excelData.Qty);

			// Foc:-
			if (excelData.Type.equalsIgnoreCase("Product")) {

				if (IsFOCManagementInSO == true) {
					System.out.println("IsFOCManagementInSO: " + IsFOCManagementInSO);
					//	click(so.IsFoc);
					//	Sendkeys(so.Foc, excelData.Foc);
				}

			} else if (excelData.Type.equalsIgnoreCase("Service")) {

				click(so.Foc);
				Sendkeys(so.Foc, excelData.Foc);

			} else {
				System.out.println("Foc Field Is Not Displayed");

			}

			// Price:-
			WebElement price = driver.findElement(By.id("ItemPrice"));
			price.click();
			price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			price.sendKeys(excelData.Price);
			Thread.sleep(1000);

			// Discount Amount,Percentage and Unit Discount and Percentage:-
			if (IsEnableItemLevelDiscountInSales == true) {
				System.out.println("Discount Field Is Displayed");
				System.out.println("Unit Discount Field Is Displayed");


			} else {
				System.out.println("Discount Field Not Displayed");
				System.out.println("Unit Discount Field Not Displayed");

			}

			double Total = 0;

			double priceDouble = Double.parseDouble(excelData.Price);
			double qtyDouble = Double.parseDouble(excelData.Qty);

			boolean unitDiscCheckboxBoolean = Boolean.parseBoolean(excelData.UnitDiscCheckbox);

			if (unitDiscCheckboxBoolean == true) {

				if (excelData.DiscountPercentage.isEmpty() == false) {
					double discountPercentDouble = Double.parseDouble(excelData.DiscountPercentage);

					double discountPercentPrice = (discountPercentDouble / 100) * priceDouble;
					double discountAmountRound = Math.round(discountPercentPrice);
					double discAmountforPercent = discountAmountRound / 100;

					Total = (priceDouble - discAmountforPercent) * qtyDouble;
					System.out.println("Discount Percent Total: " + Total);

				} else if (excelData.DiscountAmount.isEmpty() == false) {
					double discAmtDouble = Double.parseDouble(excelData.DiscountAmount);

					Total = (priceDouble - discAmtDouble) * qtyDouble;
					System.out.println("Discount Amount Total: " + Total);
				}

			} else {

				if (excelData.DiscountPercentage.isEmpty() == false) {

					double discPercentDouble = Double.parseDouble(excelData.DiscountPercentage);
					double discountPerAmt = (discPercentDouble / 100) * Total;
					double discPerAmtRound = Math.round(discountPerAmt * 100);
					double discAmtforPer = discPerAmtRound / 100;

					Total = Total - discAmtforPer;

				} else if (excelData.DiscountAmount.isEmpty() == false) {

					double discAmtDouble1 = Double.parseDouble(excelData.DiscountAmount);

					Total = Total - discAmtDouble1;

				}

			}

			click(so.Add);
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", so.Qty);
			System.out.println();

			productPrice = driver
					.findElement(By.xpath("//table[@id='OrderTable']//tbody//tr//td//input[@id='inpPrice']"))
					.getAttribute("value");
			double productPriceDouble = Double.parseDouble(productPrice);
			System.out.println("Product Price is: " + productPriceDouble);

			// Special Price:-
			if (IsAllowToEditSpecialPrice == true) {
				
				System.out.println("*** Grand Calculation With Special Price ***");
				String getBQty = driver
						.findElement(By.xpath("//table[@id='OrderTable']//tbody//tr//td[5]//input[@type='number']"))
						.getAttribute("value");
				System.out.println("B.Qty is: " + getBQty);
				int bQtyInt = Integer.parseInt(getBQty);

				String getlQty = driver
						.findElement(By.xpath("//table[@id='OrderTable']//tbody//tr//td[6]//input[@id='itemQty']"))
						.getAttribute("value");
				System.out.println("L.Qty is: " + getlQty);
				int lQtyInt = Integer.parseInt(getlQty);

				System.out.println("Special Price is Displayed");
				click(so.IsSpecialPriceCheckBox);

				if (bQtyInt > 0) {

					WebElement bSplPriceValueField = driver.findElement(
							By.xpath("//table[@id='OrderTable']//tbody//tr//td[12]//input[@id='BSpecialPrice']"));
					bSplPriceValueField.click();
					bSplPriceValueField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
					bSplPriceValueField.sendKeys(excelData.SpecialPrice + Keys.ENTER);

				} else if (lQtyInt > 0) {

					WebElement lSplPriceValueField = driver.findElement(
							By.xpath("//table[@id='OrderTable']//tbody//tr//td[13]//input[@id='LSpecialPrice']"));
					lSplPriceValueField.click();
					lSplPriceValueField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
					lSplPriceValueField.sendKeys(excelData.SpecialPrice + Keys.ENTER);
				}

			} else {

				System.out.println("Special Price is Not Displayed");

			}

			if (IsAllowToEditSpecialPrice == true) {

				double expSubtotal = Double.parseDouble(excelData.SpecialPrice) * qtyDouble;
				ExpSubTotal = ExpSubTotal + expSubtotal;

				if (excelData.ZeroGst.equals("TRUE")) {
					ExpZeroGstProductamount = Double.parseDouble(excelData.SpecialPrice) + ExpZeroGstProductamount;
				}

			} else {

				double expSubtotal = Double.parseDouble(excelData.Price) * qtyDouble;
				ExpSubTotal = ExpSubTotal + expSubtotal;

				if (excelData.ZeroGst.equals("TRUE")) {
					ExpZeroGstProductamount = Double.parseDouble(excelData.Price) + ExpZeroGstProductamount;
				}				
			}

			String ActSplPriceProductAmount = driver
					.findElement(By.xpath("//table[@id='OrderTable']//tbody//tr//td//span[@id='spanOrderTotal']"))
					.getText();
			ActDiscountProductPriceDouble = Double.parseDouble(ActSplPriceProductAmount);
			String ActDiscountProductPriceFormat = String.format("%.2f", ActDiscountProductPriceDouble);

			System.out.println("Actual Discount Product Price is: " + ActDiscountProductPriceFormat);

			if (IsAllowToEditSpecialPrice == true) {

				double specialPriceDouble = Double.parseDouble(excelData.SpecialPrice);
				double ExpDiscountProductPrice = (qtyDouble * specialPriceDouble);
				ExpDiscountProductPriceFormat = String.format("%.2f", ExpDiscountProductPrice);
				System.out.println("Expected Discount Product Price is: " + ExpDiscountProductPriceFormat);

			} else {

				double PriceDouble = Double.parseDouble(excelData.Price);
				double ExpDiscountProductPrice = (qtyDouble * PriceDouble);
				ExpDiscountProductPriceFormat = String.format("%.2f", ExpDiscountProductPrice);
				System.out.println("Expected Discount Product Price is: " + ExpDiscountProductPriceFormat);
			}

			soft.assertEquals(ActDiscountProductPriceFormat, ExpDiscountProductPriceFormat,
					"Actual and Expected Special Price Discount Mismatched for Product " + excelData.ProductName);
		}

		// Over All Discount:-
		Thread.sleep(2000);
		click(so.OverAllDiscountType);
		Select overAllDiscTypeSelect = new Select(so.OverAllDiscountType);
		overAllDiscTypeSelect.selectByVisibleText(getexcelOverAllDiscountType);

		WebElement overAllDiscountType = driver.findElement(By.id("DiscountType"));
		Select overAllDiscountTypeSelect = new Select(overAllDiscountType);
		String getOverAllDiscountType = overAllDiscountTypeSelect.getFirstSelectedOption().getText();
		System.out.println("Over all Discount Type is: " + getOverAllDiscountType);

		Thread.sleep(1000);
		click(so.OverAllDiscount);
		if (getOverAllDiscountType.equals("$")) {
			so.OverAllDiscount.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(so.OverAllDiscount, getExcelOverAllDiscountAmount + Keys.ENTER);
			click(so.OverAllDiscount);

		} else if (getOverAllDiscountType.equals("%")) {
			so.OverAllDiscount.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(so.OverAllDiscount, getExcelOverAllDiscountPercentage + Keys.ENTER);
			click(so.OverAllDiscount);

		} else {
			System.out.println("No Over All Discount Amounr and Percentage");
		}
		System.out.println();

		// Sub Total Calculation:-
		System.out.println("*** Sub Total Calculation ***");
		String subTotalAmountString = driver.findElement(By.xpath
				("//table[@id='OrderTable']//tfoot//tr//td//p[@class='footersubtotal']"))
				.getText();
		double subTotalAmountDouble = Double.parseDouble(subTotalAmountString);
		System.out.println("Actual Sub Total Amount: " + subTotalAmountDouble);
		System.out.println("Expected Sub Total Amount: " + ExpSubTotal);
		System.out.println();

		// Over All Discount Calculation:-
		System.out.println("*** Grand Total Calculation With Over All Discount and Percentage ***");

		double discountAmountDouble = Double.parseDouble(getExcelOverAllDiscountAmount);
		double discountPercentageDouble = Double.parseDouble(getExcelOverAllDiscountPercentage);
		double gstPercentage = Double.parseDouble(getExcelGstPercentage);
		double discountPercentageAmount = 0;
		double discountTotalAmount = 0;
		// double finalSubTotalAmount = 0;

		if (getOverAllDiscountType.equalsIgnoreCase("$")) {
			System.out.println("Current Discount Type is: $");

			discountTotalAmount = subTotalAmountDouble - discountAmountDouble;
			System.out.println("Over All Discount Amount is: " + discountTotalAmount);

		} else if (getOverAllDiscountType.equalsIgnoreCase("%")) {
			System.out.println("Current Discount Type is: %");

			discountTotalAmount = (subTotalAmountDouble * discountPercentageDouble / 100);
			discountPercentageAmount = (subTotalAmountDouble - discountTotalAmount);

			System.out.println("Discount Percentage Amount is: " + discountTotalAmount);
			System.out.println("Over All Discount Percentage Amount is: " + discountPercentageAmount);
		}

		double zeroGstProductDiscountAmount = (ExpZeroGstProductamount * discountPercentageDouble / 100);
		double subrationZerGstAmount = (ExpZeroGstProductamount - zeroGstProductDiscountAmount);
		System.out.println("After Discount Zero Gst Product Amount is: " + subrationZerGstAmount);
		double withoutZeroGstAmount = (discountPercentageAmount - subrationZerGstAmount);
		System.out.println();

		// GST Calculation:-
		System.out.println("*** Grand Total Calculation With GST ***");

		double finalExpectedGstAmount = 0;

		System.out.println("Gst Type is: " + getExcelGstType);
		if (getExcelGstType.equalsIgnoreCase("Inclusive")) {

			finalExpectedGstAmount = (withoutZeroGstAmount * gstPercentage) / 109;
			System.out.println("Inclusive Gst Amount is: " + finalExpectedGstAmount);

		} else if (getExcelGstType.equalsIgnoreCase("Exclusive")) {

			finalExpectedGstAmount = (withoutZeroGstAmount * gstPercentage) / 100;
			System.out.println("Exclusive Gst Amount is: " + finalExpectedGstAmount);

		} else if (getExcelGstType.equalsIgnoreCase("Zero") || getExcelGstType.equalsIgnoreCase("Overseas")) {

			finalExpectedGstAmount = (withoutZeroGstAmount * gstPercentage) / 100;
			System.out.println("Zero and Overseas Gst Amount is: " + finalExpectedGstAmount);

		}

		String ActualGstAmount = driver.findElement(By.id("GST")).getAttribute("value");
		System.out.println("Actual Gst Amount is: " + ActualGstAmount);

		String ExpectedGstAmountFormat = String.format("%.2f", finalExpectedGstAmount);

		if (getExcelGstType.equalsIgnoreCase("Inclusive")) {

			finalExpectedGstAmount = 0;
			System.out.println("Expected Gst Amount: " + ExpectedGstAmountFormat);

			soft.assertEquals(ActualGstAmount, ExpectedGstAmountFormat, 
					"Actual and Expected Gst Amount Mismatched");

		} else {

			System.out.println("Expected Gst Amount is: " + ExpectedGstAmountFormat);

			soft.assertEquals(ActualGstAmount, ExpectedGstAmountFormat, 
					"Actual and Expected Gst Amount Mismatched");
		}
		System.out.println();

		// Grand Total Amount:-
		System.out.println("*** Grand Total Amount ***");

		String finalTotalAmount = driver.findElement(By.xpath("//input[@id='Amount']")).getAttribute("value");
		double finalTotalAmountDouble = Double.parseDouble(finalTotalAmount);
		String finalTotalAmountFormat = String.format("%.2f", finalTotalAmountDouble);
		System.out.println("Actual Grand Total Amount is: " + finalTotalAmountFormat);

		double ExpectedGrandTotalAmount = (discountPercentageAmount + finalExpectedGstAmount);
		String ExpectedGrandTotalAmountFormat = String.format("%.2f", ExpectedGrandTotalAmount);
		System.out.println("Expected Grand Total Amount is: " + ExpectedGrandTotalAmountFormat);
		System.out.println();

		soft.assertEquals(finalTotalAmountFormat, ExpectedGrandTotalAmountFormat, 
				"Actual and Expected Grand Total Amount Mismatched");

		// CURRENCY CALCULATION
		System.out.println("*** Grand Total Calculation With Currency ***");
		System.out.println("Sub Total in Double: " + finalTotalAmountDouble);

		String currencyName = so.CurrencyCode.getText().trim();
		System.out.println("Currency Name is: " + currencyName);

		if (currencyName.equalsIgnoreCase("INR")) {
			double currencyRate = Double.parseDouble(getExcelCurrencyRate);
			double total = (finalTotalAmountDouble * currencyRate);
			System.out.println("Total Amount in INR ₹:" + total);

		} else if (currencyName.equalsIgnoreCase("SGD")) {
			double currencyRate = Double.parseDouble(getExcelCurrencyRate);
			double total = (finalTotalAmountDouble * currencyRate);
			System.out.println("Total Amount in SGD S$:" + total);

		} else if (currencyName.equalsIgnoreCase("USD")) {
			double currencyRate = Double.parseDouble(getExcelCurrencyRate);
			double total = (finalTotalAmountDouble * currencyRate);
			System.out.println("Total Amount in USD $:" + total);

		} else {
			System.out.println("Invalid or unsupported currency: " + currencyName);
		}
		System.out.println();

		// Decimal Place (2 or 4):-
		System.out.println("*** Decimal Place ***");

		if (DecimalCalculationForSales == 2) {
			System.out.println("2 Decimal Place Amount is: " + finalTotalAmount);

		} else if (DecimalCalculationForSales == 4) {
			System.out.println("4 Decimal Place Amount is: " + finalTotalAmount);

		} else {
			System.out.println("Invalid Decimal Format");
		}
		System.out.println("**************************************");
		System.out.println();

		click(so.ConvertInvoice);
		click(so.PopupOk);
		Thread.sleep(3000);		
		System.out.println("** Sales Invoice Page **");
		for (ExcelData excelData : excelDataList) {

			if (excelData.BatchProduct.equalsIgnoreCase("true")) {

				System.out.println("batch product: "+excelData.ProductName);

				int tableSize = driver.findElements(By.xpath("//table[@id='SalesTable']//tbody//tr")).size();
				for (int i = 1; i <= tableSize; i++) {

					String getProductName = driver.findElement(By
							.xpath("(//a[@class='fa fa-pencil-square-o editInvoiceDetails op'])[" + i + "]//preceding::td[8]"))
							.getAttribute("data-value");

					if (getProductName.equalsIgnoreCase(excelData.ProductName)) {

						WebElement batchFiles = driver.findElement(By.xpath("(//table[@id='SalesTable']//tbody//tr)[2]//td["+i+"]//following::td[12]//a[@class='fa fa-folder-open Popup']"));
						js.executeScript("arguments[0].click();", batchFiles);

						String ctnQtyValue = driver.findElement(By.xpath("(//div//textarea[contains(text(),'" + getProductName
								+ "')]//following::input[@id='BQty'])[" + i + "]")).getAttribute("value");
						System.out.println("B.Qty Is:" + ctnQtyValue);

						String pcsQtyValue = driver.findElement(By.xpath("(//div//textarea[contains(text(),'" + getProductName
								+ "')]//following::input[@id='LQty'])[" + i + "]")).getAttribute("value");
						System.out.println("L.Qty Is:" + pcsQtyValue);
						Thread.sleep(1000);

						if (IsCartonManagement == false) {

							WebElement lQty = driver.findElement(By.xpath("(//div//strong[contains(text(), '" + getProductName
									+ "')]//following::input[@id='LooseQty'])[1]"));
							js.executeScript("arguments[0].click();", lQty);
							lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
							lQty.sendKeys(pcsQtyValue);
							driver.findElement(By.xpath(
									"(//div//strong[contains(.,'" + getProductName + "')]//following::button[text()='Add'])[1]"))
							.click();
							Thread.sleep(3000);

						} else {

							if (!ctnQtyValue.equals("0")) {

								WebElement bQty = driver.findElement(By.xpath("(//div//strong[contains(text(),'" + getProductName
										+ "')]//following::input[@id='BulkQty'])[1]"));
								js.executeScript("arguments[0].click();", bQty);
								bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
								bQty.sendKeys(ctnQtyValue);

							}

							WebElement lQty = driver.findElement(By.xpath("(//div//strong[contains(text(), '" + getProductName
									+ "')]//following::input[@id='LooseQty'])[1]"));
							js.executeScript("arguments[0].click();", lQty);
							lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
							lQty.sendKeys(pcsQtyValue);
							driver.findElement(By.xpath(
									"(//div//strong[contains(.,'" + getProductName + "')]//following::button[text()='Add'])[1]"))
							.click();
							Thread.sleep(3000);
							break;
						}
					}
				}
			}
		}

		System.out.println();
		Collections.reverse(excelDataList);

		int excleDataListSize = excelDataList.size();
		for (int i = 0; i < excleDataListSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			String getProductName = driver
					.findElement(By.xpath("//table[@id='SalesTable']//tbody//tr[" + (i + 1) + "]//td[2]"))
					.getAttribute("data-value");
			System.out.println("Actual Product Name: " + getProductName);

			if (getProductName.trim().replaceAll("\\s+", "")
					.contains(excelData.ProductName.trim().replaceAll("\\s+", ""))) {
				System.out.println("Expected Product Name: " + excelData.ProductName);
			}

			String getUom = driver.findElement(By.xpath("//table[@id='SalesTable']//tbody//tr[" + (i + 1) + "]//td[3]"))
					.getText();
			System.out.println("Actual Uom: " + getUom);

			if (getUom.trim().replaceAll("\\s+", "").equalsIgnoreCase(excelData.Uom.trim().replaceAll("\\s+", ""))) {
				System.out.println("Expected uom: " + excelData.Uom);
			}

			String getFoc = driver
					.findElement(By.xpath(
							"//table[@id='SalesTable']//tbody//tr[" + (i + 1) + "]//td[7]//input[@id='ItemFOC']"))
					.getAttribute("value");
			System.out.println("Actual Foc: " + getFoc);

			if (getFoc.equalsIgnoreCase(excelData.Foc)) {
				System.out.println("Expected Foc: " + excelData.Foc);
			} 

			String getActualTotalAmount = driver.findElement(By.xpath(
					"//table[@id='SalesTable']//tbody//tr[" + (i + 1) + "]//td[12]//p[@class='totaldetailamount']"))
					.getText();
			System.out.println("Actual Total Amount: " + getActualTotalAmount);

			if (getActualTotalAmount.equalsIgnoreCase(ExpDiscountProductPriceFormat)) {
				System.out.println("Expected Total Amount: " + ExpDiscountProductPriceFormat);

			}
			System.out.println();
		}

		String getSubTotal = driver.findElement(By.id("tSubtotal")).getText();
		System.out.println("Actual Sub Total: " + getSubTotal);

		if (getSubTotal.equalsIgnoreCase(subTotalAmountString)) {
			System.out.println("Expected Sub Total: " + subTotalAmountString);

		}

		String getGst = driver.findElement(By.id("GST")).getAttribute("value");
		System.out.println("Actual Gst: " + getGst);

		if (getGst.equalsIgnoreCase(ExpectedGstAmountFormat)) {
			System.out.println("Expected Gst: " + ExpectedGstAmountFormat);

		}

		String getGrandTotal = driver.findElement(By.xpath("//input[@id='Amount']")).getAttribute("value");
		System.out.println("Actual Grand Total: " + getGrandTotal);

		if (getGrandTotal.equalsIgnoreCase(ExpectedGrandTotalAmountFormat)) {
			System.out.println("Expected Grand Total: " + ExpectedGrandTotalAmountFormat);

		}

		js.executeScript("arguments[0].click();", so.SaveandClose);
		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("Alert Text: " + alertText);

		} catch (Exception e) {
			System.out.println("No alert appeared after save.");
		}	
		System.out.println("*** Sales Invoice Save Successfull ***");
		System.out.println("**************************************");
		Thread.sleep(3000);
		System.out.println();

	}




}
