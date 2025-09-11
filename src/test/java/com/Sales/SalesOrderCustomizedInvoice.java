package com.Sales;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BaseClass.BaseClass;
import com.PomClass.Login;
import com.PomClass.PurchaseOrder;
import com.PomClass.SalesOrder;
import com.PomClass.SystemSettings;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesOrderCustomizedInvoice extends BaseClass{

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

	@DataProvider(name = "Util2")
	public Object[][] Util1() {

		Object data[][] = Util1.getTestData("C:\\Adaptive\\ERP\\SalesOrder2.xlsx", "Sheet1");
		return data;

	}

	@SuppressWarnings("unused")
	public
	class ExcelData {
		private String Customer;
		private String Type;
		private String ProductCode;
		private String ProductName;
		private String Uom;
		private String Units;
		private String HigherUnits;
		private String Qty;
		private String Foc;
		private String BulkFoc;
		private String Price;
		private String DiscPercent;
		private String DiscAmt;
		private String UnitDisc;
		private String BillDisc;
		private String Batch;
		private String GstType;
		private String GstPercentage;

		public ExcelData(String Customer, String Type, String ProductCode, String ProductName, String Uom, String Units,
				String HigherUnits, String Qty, String Foc, String BulkFoc, String Price, String DiscPercent,
				String DiscAmt, String UnitDisc, String BillDisc, String Batch, String GstType, String GstPercentage) {
			super();

			this.Customer = Customer;
			this.Type = Type;
			this.ProductCode = ProductCode;
			this.ProductName = ProductName;
			this.Uom = Uom;
			this.Units = Units;
			this.HigherUnits = HigherUnits;
			this.Qty = Qty;
			this.Foc = Foc;
			this.BulkFoc = BulkFoc;
			this.Price = Price;
			this.DiscPercent = DiscPercent;
			this.DiscAmt = DiscAmt;
			this.UnitDisc = UnitDisc;
			this.BillDisc = BillDisc;
			this.Batch = Batch;
			this.GstType = GstType;
			this.GstPercentage = GstPercentage;

		}

	}

	Set<String> ExcelUniqueProductDataSet = new LinkedHashSet<String>();
	ArrayList<ExcelData> dataList = new ArrayList<>();

	List<String> ProductList = new ArrayList<String>();
	Set<String> ProductSet = new LinkedHashSet<String>();

	@Test(priority = 4, dataProvider = "Util2", dependsOnMethods = "ERPLoginPage")
	public void GetData(String Customer, String Type, String ProductCode, String ProductName, String Uom, String Units,
			String HigherUnits, String Qty, String Foc, String BulkFoc, String Price, String DiscPercent,
			String DiscAmt, String UnitDisc, String BillDisc, String Batch, String GstType, String GstPercentage) {

		ExcelData data = new ExcelData(Customer, Type, ProductCode, ProductName, Uom, Units, HigherUnits, Qty, Foc,
				BulkFoc, Price, DiscPercent, DiscAmt, UnitDisc, BillDisc, Batch, GstType, GstPercentage);

		dataList.add(data);
		ExcelUniqueProductDataSet.add(ProductName);

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
	private Float DecimalCalculationForSales;
	private boolean IsOpenItemManagementInsales;
	private boolean IsHeaderManagementInSO;

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
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsSalesManManagementString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsSalesManManagement = Boolean.parseBoolean(IsSalesManManagementString);
		System.out.println("IsSalesManManagement :" + IsSalesManManagement);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsWarehouseManagement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsWarehouseManagementString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsWarehouseManagement = Boolean.parseBoolean(IsWarehouseManagementString);
		System.out.println("IsWarehouseManagement :" + IsWarehouseManagement);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsWarehouseStorageManagement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsWarehouseStorageManagementString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsWarehouseStorageManagement = Boolean.parseBoolean(IsWarehouseStorageManagementString);
		System.out.println("IsWarehouseStorageManagement :" + IsWarehouseStorageManagement);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsBarcodeEnabled");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsBarcodeEnabledString = driver
				.findElement(By.xpath("(//input[@name='BitValue'])[2]")).getAttribute("value");
		IsBarcodeEnabled = Boolean.parseBoolean(IsBarcodeEnabledString);
		System.out.println("IsBarcodeEnabled :" + IsBarcodeEnabled);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsBarcodeManagementInsales");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsBarcodeManagementInsalesString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsBarcodeManagementInsales = Boolean.parseBoolean(IsBarcodeManagementInsalesString);
		System.out.println("IsBarcodeManagementInsales :" + IsBarcodeManagementInsales);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsMultiWordSearchInProduct");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsMultiWordSearchInProductString = driver
				.findElement(By.xpath("(//input[@name='BitValue'])[2]")).getAttribute("value");
		IsMultiWordSearchInProduct = Boolean.parseBoolean(IsMultiWordSearchInProductString);
		System.out.println("IsMultiWordSearchInProduct :" + IsMultiWordSearchInProduct);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsDuplicateProductsInInvoice");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsDuplicateProductsInInvoiceString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsDuplicateProductsInInvoice = Boolean.parseBoolean(IsDuplicateProductsInInvoiceString);
		System.out.println("IsDuplicateProductsInInvoice :" + IsDuplicateProductsInInvoice);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsCartonManagement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsCartonManagementString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsCartonManagement = Boolean.parseBoolean(IsCartonManagementString);
		System.out.println("IsCartonManagement :" + IsCartonManagement);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsEnableItemLevelDiscountInSales");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsEnableItemLevelDiscountInSalesString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsEnableItemLevelDiscountInSales = Boolean.parseBoolean(IsEnableItemLevelDiscountInSalesString);
		System.out.println("IsEnableItemLevelDiscountInSales :" + IsEnableItemLevelDiscountInSales);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsFOCManagementInSO");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsFOCManagementInSOString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsFOCManagementInSO = Boolean.parseBoolean(IsFOCManagementInSOString);
		System.out.println("IsFOCManagementInSO :" + IsFOCManagementInSO);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "BulkQtyMeasurement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		BulkQtyMeasurement = driver.findElement(By.xpath("//input[@id='StringValue']")).getAttribute("value");
		System.out.println("BulkQtyMeasurement :" + BulkQtyMeasurement);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "LooseQtyMeasurement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		LooseQtyMeasurement = driver.findElement(By.xpath("//input[@id='StringValue']")).getAttribute("value");
		System.out.println("LooseQtyMeasurement :" + LooseQtyMeasurement);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsAllowToEditSpecialPrice");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsAllowToEditSpecialPriceString = driver.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsAllowToEditSpecialPrice = Boolean.parseBoolean(IsAllowToEditSpecialPriceString);
		System.out.println("IsAllowToEditSpecialPrice :" + IsAllowToEditSpecialPrice);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "DecimalCalculationForSales");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		String DecimalCalculationForSalesString = driver.findElement(By.xpath("//input[@id='DecimalValue']"))
				.getAttribute("value");
		DecimalCalculationForSales = Float.parseFloat(DecimalCalculationForSalesString);
		System.out.println("DecimalCalculationForSales :" + DecimalCalculationForSales);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsOpenItemManagementInsales");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		String IsOpenItemManagementInsalesString = driver.findElement(By.xpath("//input[@id='BitValue']"))
				.getAttribute("value");
		IsOpenItemManagementInsales = Boolean.parseBoolean(IsOpenItemManagementInsalesString);
		System.out.println("IsOpenItemManagementInsales :" + IsOpenItemManagementInsales);
		click(ss.Back);

		Thread.sleep(2000);
		click(ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsHeaderManagementInSO");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		String IsHeaderManagementInSOString = driver.findElement(By.xpath("//input[@id='BitValue']"))
				.getAttribute("value");
		IsHeaderManagementInSO = Boolean.parseBoolean(IsHeaderManagementInSOString);
		System.out.println("IsHeaderManagementInSO :" + IsHeaderManagementInSO);
		click(ss.Back);

		System.out.println();
	}


	@SuppressWarnings("unused")
	class Product {

		private String productCode1;
		private String productName;
		private String departmentValue;
		private String categoryValue;
		private String brandValue;
		private String profitMarginValue;
		private String marginToleranceValue;
		private String vendorNameValue;
		private String purchaseCOAValue;
		private String salesCOAValue;
		private String stockAdjustmentCOAValue;
		private String stockCOAValue;
		private String COGSCOAValue;
		private String currentStockValue;
		private String uomValue;
		private boolean IsCarton;
		private String CartonPrice;
		private boolean IsNonCarton;
		private boolean IsBase;
		private String SellingPrice;
		private String LPPrice;
		private String RetailPrice;

		public Product(String productCode1, String productName, String departmentValue, String categoryValue,
				String brandValue, String profitMarginValue, String marginToleranceValue, String vendorNameValue,
				String purchaseCOAValue, String salesCOAValue, String currentStockValue, String uomValue,
				boolean IsCarton, String CartonPrice, boolean IsNonCarton, boolean IsBase, String SellingPrice,
				String LPPrice, String RetailPrice) {
			super();

			this.productCode1 = productCode1;
			this.productName = productName;
			this.departmentValue = departmentValue;
			this.categoryValue = categoryValue;
			this.brandValue = brandValue;
			this.profitMarginValue = profitMarginValue;
			this.marginToleranceValue = marginToleranceValue;
			this.vendorNameValue = vendorNameValue;
			this.purchaseCOAValue = purchaseCOAValue;
			this.salesCOAValue = salesCOAValue;
			// this.stockAdjustmentCOAValue = stockAdjustmentCOAValue;
			// this.stockCOAValue = stockCOAValue;
			// this.COGSCOAValue = COGSCOAValue;
			this.currentStockValue = currentStockValue;
			this.uomValue = uomValue;
			this.IsCarton = IsCarton;
			this.CartonPrice = CartonPrice;
			this.IsNonCarton = IsNonCarton;
			this.IsBase = IsBase;
			this.SellingPrice = SellingPrice;
			this.LPPrice = LPPrice;
			this.RetailPrice = RetailPrice;

		}

	}
	@SuppressWarnings("unused")
	class ProductUOM {

		private String productCode1;
		// private String productNameValue;
		private String currentStockValue;
		private String uomValue;
		private boolean IsCarton;
		private String CartonPrice;
		private boolean IsNonCarton;
		private boolean IsBase;
		private String SubUOM;
		private String CurStock;
		private String LPP;
		private String SP;
		// private String RP;

		public ProductUOM(String productCode1, String currentStockValue, String uomValue, boolean IsCarton,
				String CartonPrice, boolean IsNonCarton, boolean IsBase, String SubUOM, String CurStock, String LPP,
				String SP) {
			super();

			this.productCode1 = productCode1;
			// this.productNameValue = productNameValue;
			this.currentStockValue = currentStockValue;
			this.uomValue = uomValue;
			this.IsCarton = IsCarton;
			this.CartonPrice = CartonPrice;
			this.IsNonCarton = IsNonCarton;
			this.IsBase = IsBase;
			this.SubUOM = SubUOM;
			this.CurStock = CurStock;
			this.LPP = LPP;
			this.SP = SP;
			// this.RP = RP;

		}

	}

	List<String> UOMList = new ArrayList<String>();
	Set<String> UOMSet = new LinkedHashSet<String>();

	ArrayList<Product> ProductDetailsList = new ArrayList<>();
	ArrayList<ProductUOM> ProductUOMDetailsList = new ArrayList<>();

	@Ignore
	@Test(priority = 13, dependsOnMethods = "ERPLoginPage")
	public void ProductDetails() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		PurchaseOrder po = new PurchaseOrder(driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		int ProductListSize = ProductList.size();
		System.out.println("Product: " + ProductListSize);

		for (int i = 0; i < ProductListSize; i++) {
			ExcelData excelData = dataList.get(i);

			String productCode1 = excelData.ProductCode;

			driver.navigate().to(url + "SalesPurchases/Product");
			Thread.sleep(7000);
			System.out.println("*Product Details Page*");

			WebElement productcode = driver.findElement(By.xpath("(//input[@id='SearchString'])[1]"));
			Thread.sleep(1000);
			productcode.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			productcode.sendKeys(excelData.ProductCode);
			Thread.sleep(1000);
			WebElement fetchBtn = driver.findElement(By.xpath("//input[@id='searchstring' and @value='Fetch']"));
			js.executeScript("arguments[0].click();", fetchBtn);
			Thread.sleep(3000);

			WebElement DetailsIcon = driver
					.findElement(By.xpath("//table[@id='producttable']//tbody//tr//td[1][normalize-space()='"
							+ productCode1 + "']//following::td[9]//a[@title='Details']"));
			js.executeScript("arguments[0].click();", DetailsIcon);
			Thread.sleep(3000);

			System.out.println("Product Code: " + productCode1);

			String productName = driver.findElement(By.xpath("//div[@class='col-lg-7']//h2//b")).getText();
			System.out.println("ProductName: "+productName);

			WebElement department = driver
					.findElement(By.xpath("//dt[normalize-space()='Department']//following-sibling::dd[1]"));
			String departmentValue = department.getText();
			System.out.println("Department: " + departmentValue);

			WebElement category = driver
					.findElement(By.xpath("//dt[normalize-space()='Category']//following-sibling::dd[1]"));
			String categoryValue = category.getText();
			System.out.println("Category: " + categoryValue);

			WebElement brand = driver
					.findElement(By.xpath("//dt[normalize-space()='Brand']//following-sibling::dd[1]"));
			String brandValue = brand.getText();
			System.out.println("Brand: " + brandValue);

			String currentStockValue = driver
					.findElement(By.xpath("//dt[normalize-space()='Current Stock - HQ']//following-sibling::dd[1]"))
					.getText();
			System.out.println("CurrentStock :" + currentStockValue);

			String totalStock = driver.findElement(By.xpath("//dt[normalize-space()='Total Stock']//following-sibling::dd[1]")).getText();
			System.out.println("Total STock: "+totalStock);

			WebElement profitMargin = driver
					.findElement(By.xpath("//dt[normalize-space()='Profit Margin %']//following-sibling::dd[1]"));
			String profitMarginValue = profitMargin.getText();
			System.out.println("Profit Margin: " + profitMarginValue);

			WebElement marginTolerance = driver
					.findElement(By.xpath("//dt[normalize-space()='Margin Tolerance %']//following-sibling::dd[1]"));
			String marginToleranceValue = marginTolerance.getText();
			System.out.println("Margin Tolerance: " + marginToleranceValue);

			WebElement InfoTab = driver.findElement(By.xpath("//a[text()='Info']"));
			InfoTab.click();
			Thread.sleep(2000);

			WebElement purchaseCOA = driver
					.findElement(By.xpath("//dt[normalize-space()='Purchase COA / GL']//following-sibling::dd[1]"));
			String purchaseCOAValue = purchaseCOA.getText().trim();
			System.out.println("Purchase COA: " + purchaseCOAValue);

			WebElement salesCOA = driver
					.findElement(By.xpath("//dt[normalize-space()='Sales COA / GL']//following-sibling::dd[1]"));
			String salesCOAValue = salesCOA.getText().trim();
			System.out.println("Sales COA: " + salesCOAValue);

			WebElement uom = driver.findElement(By.xpath("//dt[normalize-space()='UOM']//following-sibling::dd[1]"));
			String uomValue = uom.getText();
			UOMSet.add(uomValue);
			System.out.println("UOM: " + uomValue);

			WebElement vendorName = driver
					.findElement(By.xpath("//dt[normalize-space()='Vendor Name']//following-sibling::dd[1]"));
			String vendorNameValue = vendorName.getText();
			System.out.println("Vendor Name: " + vendorNameValue);

			boolean IsCartonSelected = driver
					.findElement(By.xpath("//dt[normalize-space()='Carton']//following-sibling::dd[1]")).isSelected();
			System.out.println("Is Carton Selected: " + IsCartonSelected);

			/*
			 * WebElement stockAdjustmentCOA = driver.findElement(By.xpath(
			 * "//label[normalize-space()='Stock Adjustment COA / GL']//following::span[@id='select2-StockAdjustmentCOAId-container']"
			 * )); String stockAdjustmentCOAValue =
			 * stockAdjustmentCOA.getAttribute("title").trim();
			 * System.out.println("Stock Adjustment COA: " + stockAdjustmentCOAValue);
			 * 
			 * WebElement stockCOA = driver.findElement(By.xpath(
			 * "//label[normalize-space()='Stock COA / GL']//following::span[@id='select2-GRNCOAId-container']"
			 * )); String stockCOAValue = stockCOA.getAttribute("title").trim();
			 * System.out.println("Stock COA: " + stockCOAValue);
			 * 
			 * WebElement COGSCOA = driver.findElement(By.xpath(
			 * "//label[normalize-space()='COGS COA / GL']//following::span[@id='select2-COGSCOAId-container']"
			 * )); String COGSCOAValue = COGSCOA.getAttribute("title").trim();
			 * System.out.println("COGS COA: " + COGSCOAValue);
			 */
			Thread.sleep(3000);
			WebElement stockTab = driver.findElement(By.xpath("//a[text()='Stock']"));
			stockTab.click();
			Thread.sleep(2000);

			boolean ISCarton = false;
			boolean IsNonCarton = false;
			boolean IsBase = false;

			int ProductUOMtablesize = driver
					.findElements(By.xpath("//table[@id='ProductPartialUOM']//tbody//tr//td[2]")).size();
			System.out.println("ProductUOMtablesize " + ProductUOMtablesize);
			String CartonPrice = null;

			if (IsCartonSelected == true) {
				ISCarton = true;

			} else if (IsCartonSelected == false && ProductUOMtablesize > 0) {
				IsNonCarton = true;

			} else if (IsCartonSelected == false && ProductUOMtablesize == 0) {
				IsBase = true;
			}

			String SellingPrice = null;
			String LPPrice = null;
			String RetailPrice = null;

			if (ProductUOMtablesize == 0) {

				Thread.sleep(1000);
				click(po.InfoTab);
				Thread.sleep(1000);

				SellingPrice = driver
						.findElement(By.xpath("//dt[normalize-space()='Selling Price']//following-sibling::dd[1]"))
						.getText();
				System.out.println("Selling Price: " + SellingPrice);

				LPPrice = driver
						.findElement(
								By.xpath("//dt[normalize-space()='Last Purchase Price']//following-sibling::dd[1]"))
						.getText();
				System.out.println("LP Price: " + LPPrice);

				/*
				 * RetailPrice = driver .findElement(By.
				 * xpath("//label[text()='Retail Price']//following::input[@id='RetailPrice']"))
				 * .getAttribute("value"); System.out.println("Retail Price: " + RetailPrice);
				 */

			} else {

				for (int j = 1; j <= ProductUOMtablesize; j++) {
					String SubUOM = driver
							.findElement(By.xpath("(//table[@id='ProductPartialUOM']//tbody//tr//td[2])[" + j + "]"))
							.getAttribute("data-value");
					System.out.println("SubUOM Value: " + SubUOM);

					String CurStock = driver.findElement(By
							.xpath("(//table[@id='ProductPartialUOM']//tbody//tr//td[2])[" + j + "]//following::td[2]"))
							.getText().trim();
					System.out.println("CurStockValue: " + CurStock);

					String LPP = driver.findElement(By.xpath(
							"(//table[@id='ProductPartialUOM']//tbody//tr//td[2])[" + j + "]//following::td[3]//input"))
							.getAttribute("value").trim();
					System.out.println("LPP Value: " + LPP);

					String SP = driver.findElement(By.xpath(
							"(//table[@id='ProductPartialUOM']//tbody//tr//td[2])[" + j + "]//following::td[4]//input"))
							.getAttribute("value");
					System.out.println("SP Value: " + SP);

					/*
					 * String RP = driver.findElement(By.xpath(
					 * "(//table[@id='ProductPartialUOM']//tbody//tr//td[2])[" + j +
					 * "]//following::td[5]//input")) .getAttribute("value").trim();
					 * System.out.println("RP Value: " + RP);
					 */

					ProductUOM pruom = new ProductUOM(productCode1, currentStockValue, uomValue, IsCartonSelected,
							CartonPrice, IsNonCarton, IsBase, SubUOM, CurStock, LPP, SP);
					ProductUOMDetailsList.add(pruom);

				}

			}

			Product pr = new Product(productCode1, vendorNameValue, departmentValue, categoryValue, brandValue,
					profitMarginValue, marginToleranceValue, vendorNameValue, purchaseCOAValue, salesCOAValue,
					currentStockValue, uomValue, ISCarton, CartonPrice, IsNonCarton, IsBase, SellingPrice, LPPrice,
					RetailPrice);

			ProductDetailsList.add(pr);

			Thread.sleep(2000);
			driver.navigate().back();
			System.out.println("***");

		}

	}

	@SuppressWarnings("unused")
	public
	class UOM {
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
	@Test(priority = 16, dependsOnMethods = "ERPLoginPage")
	public void UOMPAGE() throws InterruptedException {
		UOMList.addAll(UOMSet);

		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		PurchaseOrder po = new PurchaseOrder(driver);

		driver.navigate().to(url + "SalesPurchases/UOM");
		Thread.sleep(4000);
		System.out.println("*UOM Page*");

		int uomListSize = UOMList.size();
		System.out.println("uomListSize: " + uomListSize);

		for (int i = 0; i < uomListSize; i++) {

			String BaseUom = UOMList.get(i);

			driver.findElement(By.id("select2-DropDown-container")).click();
			WebElement UOMSearchField = driver.findElement(
					By.xpath("//span[@id='select2-DropDown-container']//following::input[@type='search']"));
			UOMSearchField.sendKeys(BaseUom + Keys.ENTER);

			Thread.sleep(1000);
			WebElement fetchBtn = driver.findElement(By.xpath("//input[@id='searchstring' and @value='Fetch']"));
			js.executeScript("arguments[0].click();", fetchBtn);
			Thread.sleep(3000);

			WebElement SelectDropdown = driver.findElement(By.name("UOMTable_length"));
			Select dropdown = new Select(SelectDropdown);
			dropdown.selectByValue("100");
			Thread.sleep(2000);

			int UOMListTableSize = driver.findElements(By.xpath("//table[@id='UOMTable']//tbody//tr")).size();
			System.out.println("UOMListTableSize: " + UOMListTableSize);

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

				UOM uomdetails = new UOM(UOMListName, UOMListBaseUom, BaseUom, UOMListUnits);
				UomDetailsList.add(uomdetails);

			}
		}
	}

	//SALES ORDER PAGE DATA
	class salesOrderData {

		private String productName;
		private String ReservedQty;

		public salesOrderData (String productName, String ReservedQty) {
			super();

			this.productName = productName;
			this.ReservedQty = ReservedQty;

		}
	}

	ArrayList<salesOrderData> salesOrderDataList = new ArrayList<>();
	// @Ignore
	@Test(priority = 10, dependsOnMethods = "ERPLoginPage")
	public void SalesToInvoice() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.navigate().to(url + "SalesPurchases/SalesOrderIndex");
		Thread.sleep(5000);
		SalesOrder so = new SalesOrder(driver);
		System.out.println("****************************************");

		click(so.AddOrder);
		Thread.sleep(3000);

		String getExcelGstType = "";

		int SOSize = dataList.size();
		for (int i = 0; i < SOSize; i++) {
			ExcelData excelData = dataList.get(i);

			if (excelData.Customer.isEmpty() == false) {

				Thread.sleep(2000);
				// js.executeScript("arguments[0].click();", so.Customer);
				click(so.Customer);
				WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search'][1]")));
				searchInput.sendKeys(excelData.Customer + Keys.ENTER);
				Thread.sleep(2000);

			}

			click(so.Gst);
			WebElement GstSearchInput = driver.findElement(By.xpath("//span[@id='select2-GSTTypeId-container']//following::input[@type='search']"));
			GstSearchInput.sendKeys(excelData.GstType +Keys.ENTER);

			if (i == 0) {
				getExcelGstType = excelData.GstType;
			}

			//PRODUCT
			if (excelData.Type.equalsIgnoreCase("Product")) {	

				String productCheckbox = driver.findElement(By.id("ProductCheck")).getAttribute("checked");
				System.out.println("Product Check Box is: "+productCheckbox);
				if (!productCheckbox.equalsIgnoreCase("true")) {
					click(so.ProductCheckBox);

				}

			} else if (excelData.Type.equalsIgnoreCase("Service")) {

				String serviceCheckbox = driver.findElement(By.id("ServiceCheck")).getAttribute("checked");
				System.out.println("Service Check Box is: "+serviceCheckbox);
				if (!serviceCheckbox.equalsIgnoreCase("true")) {
					click(so.ServiceCheckBox);

				}

			} else if (excelData.Type.equalsIgnoreCase("Open")) {

				String openCheckbox = driver.findElement(By.id("OpenCheck")).getAttribute("checked");
				System.out.println("Open Check Box is: "+openCheckbox);
				if (!openCheckbox.equalsIgnoreCase("true")) {
					click(so.OpenCheckBox);

				}

			} else if (excelData.Type.equalsIgnoreCase("Header")) {

				String HearedCheckbox = driver.findElement(By.id("HeaderCheck")).getAttribute("checked");
				System.out.println("Heared Check Box is: "+HearedCheckbox);
				if (!HearedCheckbox.equalsIgnoreCase("true")) {
					click(so.HeaderCheckBox);

				}

			}

			click(so.Product);
			driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
			.sendKeys(excelData.ProductName + Keys.ENTER);
			click(so.Qty);
			if (excelData.Type.equalsIgnoreCase("Product")) {

				click(so.Uom);
				List<WebElement> subUomOption = driver.findElements(By.xpath("//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
				for (WebElement option : subUomOption) {
					if (option.getText().trim().equals(excelData.Uom)) {
						option.click();
						break;
					}

				}

			} else if (excelData.Type.equalsIgnoreCase("Service") || excelData.Type.equalsIgnoreCase("Open")) {

				click(so.Uom);
				List<WebElement> subUomOption = driver.findElements(By.xpath("//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
				for (WebElement option : subUomOption) {
					if (option.getText().trim().equals(excelData.Uom)) {
						option.click();
						break;
					}

				}
			}

			System.out.println();

			//QOH CALCULATION
			System.out.println("*** Grand Total Calculation With QOH ***");

			String qohStock = driver.findElement(By.id("QOH")).getAttribute("value");
			System.out.println("QOH Stock is: "+qohStock);

			int ProductDatatSize = ProductDetailsList.size();
			for (int j = 0; j < ProductDatatSize; j++) {
				Product data = ProductDetailsList.get(j);
				if (data.productName.equalsIgnoreCase(excelData.ProductName)) {

					System.out.println("Current Stock: " + data.currentStockValue);
					System.out.println("Product Name: "+data.productName);

					soft.assertEquals(data.currentStockValue, qohStock, 
							"Actual and Expected QOH Mismatched for Product: " + data.productName);

				} 

			}
			System.out.println();

			//RESERVED QTY CALCULATION
			System.out.println("*** Grand Total Calculation Wuth Reserved Qty ***");
			String ReservedQty = driver.findElement(By.id("Reserved")).getAttribute("value");
			System.out.println("Before Reserved Qty is: "+ReservedQty);

			salesOrderData salesOrderList = new salesOrderData(excelData.ProductName, ReservedQty);
			salesOrderDataList.add(salesOrderList);

			//QTY
			Sendkeys(so.Qty, excelData.Qty);

			//FOC
			if (IsFOCManagementInSO == true) {
				System.out.println("IsFOCManagementInSO: " + IsFOCManagementInSO);
				click(so.IsFoc);
				Sendkeys(so.Foc, excelData.Foc);

			} else {
				System.out.println("Foc Field Is Not Displayed");

			}

			//PRICE
			click(so.Price);
			so.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(so.Price, excelData.Price);
			Thread.sleep(1000);

			//(DISCOUNT AMOUNT,PERCENTAGE) AND (UNIT DISCOUNT AND PERCENTAGE)
			if (IsEnableItemLevelDiscountInSales == true) {
				System.out.println("DIscount Field Is Displayed");
				System.out.println("Unit Discount Field Is Displayed");

				/*	if (excelData.DiscPercent.isBlank() == false) {

						double multiplyAmountDouble = (priceDouble * qtyDouble);
						double multiplyAmountDiscountDouble = (multiplyAmountDouble); 

					} else if (excelData.DiscAmt.isBlank() == false) {

					} else if (excelData.UnitDisc.equalsIgnoreCase("True")) {

						click(so.UnitDiscCheckBox);
						click(so.UnitDisc);
						so.UnitDisc.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
						Sendkeys(so.UnitDisc, excelData.UnitDisc);

					}*/

			} else {
				System.out.println("Discount Field Not Displayed");
				System.out.println("Unit Discount Field Not Displayed");

			}


			double Total = 0;

			double priceDouble = Double.parseDouble(excelData.Price);
			double qtyDouble = Double.parseDouble(excelData.Qty);

			if (excelData.UnitDisc.equalsIgnoreCase("True")) {


				if (excelData.DiscPercent.isBlank() == false) {
					double discountPercentDouble = Double.parseDouble(excelData.DiscPercent);

					double discountPercentPrice = (discountPercentDouble / 100) * priceDouble;
					double discountAmountRound = Math.round(discountPercentPrice);
					double discAmountforPercent = discountAmountRound / 100;

					Total = (priceDouble - discAmountforPercent) * qtyDouble;					
					System.out.println("Discount Percent Total: "+Total);

				} else if (excelData.DiscAmt.isBlank() == false) {
					double discAmtDouble = Double.parseDouble(excelData.DiscAmt);

					Total = (priceDouble - discAmtDouble) * qtyDouble;
					System.out.println("Discount Amount Total: "+Total);
				}

			} else {

				if (excelData.DiscPercent.isBlank() == false) {

					double discPercentDouble = Double.parseDouble(excelData.DiscPercent);
					double discountPerAmt = (discPercentDouble / 100) * Total;
					double discPerAmtRound = Math.round(discountPerAmt * 100);
					double discAmtforPer = discPerAmtRound / 100;

					Total = Total - discAmtforPer;		

				} else if (excelData.DiscAmt.isBlank() == false) {

					double discAmtDouble1 = Double.parseDouble(excelData.DiscAmt);

					Total = Total - discAmtDouble1;

				}

			} 

			click(so.Add);
			Thread.sleep(2000);
			click(so.Qty);
			System.out.println();
			//OLD HISTORY
			System.out.println("*** Old History ***");
			if (so.OldHistoryQuestionMark.isDisplayed()) {

				click(so.OldHistoryQuestionMark);
				String oldHistory = driver.findElement(By.xpath("//span[@data-toggle='tooltip']")).getAttribute("data-original-title");
				System.out.println("Product Old History is: "+oldHistory);
			}

			System.out.println();
			System.out.println("*** Grand Calculation With Special Price ***");

			String productPrice = driver.findElement(By.xpath
					("//table[@id='OrderTable']//tbody//tr//td[2]//div//textarea[contains(text(),'"+excelData.ProductName+" ')]//following::td[14]//p[@class='totaldetailamount']"))
					.getText();
			double productPriceDouble = Double.parseDouble(productPrice);
			System.out.println("Product Price is: "+productPriceDouble);

			//SPECIAL PRICE
			String getBQty = driver.findElement(By.xpath("//table[@id='OrderTable']//tbody//tr//td[5]//input[@type='number']")).getAttribute("value");
			System.out.println("B.Qty is: "+getBQty);
			int bQtyInt = Integer.parseInt(getBQty);

			String getlQty = driver.findElement(By.xpath("//table[@id='OrderTable']//tbody//tr//td[6]//input[@id='itemQty']")).getAttribute("value");
			System.out.println("L.Qty is: "+getlQty);
			int lQtyInt = Integer.parseInt(getlQty);


			if (IsAllowToEditSpecialPrice == true) {

				System.out.println("Special Price is Displayed");
				click(so.IsSpecialPriceCheckBox);

				if (bQtyInt > 0) {

					WebElement bSplPriceValueField = driver.findElement(By.xpath("//table[@id='OrderTable']//tbody//tr//td[12]//input[@id='BSpecialPrice']"));
					bSplPriceValueField.click();
					bSplPriceValueField.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
					bSplPriceValueField.sendKeys("100" +Keys.ENTER);

				} else if (lQtyInt > 0) {

					WebElement lSplPriceValueField = driver.findElement(By.xpath("//table[@id='OrderTable']//tbody//tr//td[13]//input[@id='LSpecialPrice']"));
					lSplPriceValueField.click();
					lSplPriceValueField.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
					lSplPriceValueField.sendKeys("100" +Keys.ENTER);
				} 

			} else {

				System.out.println("Special Price is Not Displayed");
			}

			String ActDiscountProductPrice = driver.findElement(By.xpath
					("//table[@id='OrderTable']//tbody//tr//td[2]//div//textarea[contains(text(),'"+excelData.ProductName+" ')]//following::td[14]//p[@class='totaldetailamount']"))
					.getText();
			double ActDiscountProductPriceDouble = Double.parseDouble(ActDiscountProductPrice);
			System.out.println("Actual Discount Product Price is: "+ActDiscountProductPriceDouble);

			double ExpDiscountProductPrice = (qtyDouble * 100);
			System.out.println("Expected Discount Product Price is: "+ExpDiscountProductPrice);

			soft.assertEquals(ExpDiscountProductPrice, ActDiscountProductPriceDouble, 
					"Actual and Expected Special Price Discount Mismatched for Product "+excelData.ProductName);

		}

		//OVER ALL DISCOUNT
		Thread.sleep(2000);
		click(so.OverAllDiscountType);
		Select overAllDiscTypeSelect = new Select(so.OverAllDiscountType);
		overAllDiscTypeSelect.selectByVisibleText("%");

		WebElement overAllDiscountType = driver.findElement(By.id("DiscountType"));		
		Select overAllDiscountTypeSelect =new Select(overAllDiscountType);
		String getOverAllDiscountType = overAllDiscountTypeSelect.getFirstSelectedOption().getText();				
		System.out.println("Over all Discount Type is: "+getOverAllDiscountType);

		Thread.sleep(1000);
		click(so.OverAllDiscount);
		if (getOverAllDiscountType.equals("$")) {
			so.OverAllDiscount.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
			Sendkeys(so.OverAllDiscount, "10" +Keys.ENTER);

		} else if (getOverAllDiscountType.equals("%")) {
			so.OverAllDiscount.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
			Sendkeys(so.OverAllDiscount, "15" +Keys.ENTER);

		} else {
			System.out.println("No Over All Discount Amounr and Percentage");
		}

		System.out.println();

		//OVER ALL DISCOUNT CALCULATION
		System.out.println("*** Grand Total Calculation With Over All Discount and Percentage ***");

		String subTotalAmountString = driver.findElement(By.id("tSubtotal")).getText();
		double subTotalAmountDouble = Double.parseDouble(subTotalAmountString);
		System.out.println("Sub Total in Double: "+subTotalAmountDouble);

		double discountAmountDouble = 10; 
		double gstPercentage = 9;
		double subTotalAmount = 0;
		double discountPercentageAmount = 0;


		if (getOverAllDiscountType.equalsIgnoreCase("$")) {
			System.out.println("Current Discount Type is: $");

			subTotalAmount = subTotalAmountDouble - discountAmountDouble;
			System.out.println("Over All Discount Amount is: "+subTotalAmountDouble);

		} else if (getOverAllDiscountType.equalsIgnoreCase("%")) {
			System.out.println("Current Discount Type is: %");

			discountPercentageAmount = (subTotalAmountDouble * discountAmountDouble / 100) ;
			subTotalAmount = subTotalAmountDouble - discountPercentageAmount;
			System.out.println("Discount Percentage Amount is: "+discountPercentageAmount);
			System.out.println("Over All Discount Percentage Amount is: "+subTotalAmount);
		}

		String finalTotalAmount = driver.findElement(By.xpath("//input[@id='Amount']")).getAttribute("value");
		double finalTotalAmountDouble = Double.parseDouble(finalTotalAmount);
		System.out.println("Final Total Amount is: "+finalTotalAmountDouble);
		System.out.println();

		//GST CALCULATION
		System.out.println("*** Grand Total Calculation With GST ***");

		double ExpectedGstAmount = 0;

		System.out.println("Gst Type is: "+getExcelGstType);
		if (getExcelGstType.equalsIgnoreCase("Inclusive")) {

			ExpectedGstAmount = (finalTotalAmountDouble * gstPercentage) / 109;
			System.out.println("Inclusive Gst Amount is: "+ExpectedGstAmount);

		} else if (getExcelGstType.equalsIgnoreCase("Exclusive")) {

			ExpectedGstAmount = (finalTotalAmountDouble * gstPercentage) / 100;
			System.out.println("Exclusive Gst Amount is: "+ExpectedGstAmount);

		} else if (getExcelGstType.equalsIgnoreCase("Zero") || getExcelGstType.equalsIgnoreCase("Overseas")) {


			System.out.println("Zero and Overseas Gst Amount");

		} 

		String ExpectedGstAmountFormat = String.format("%.2f", ExpectedGstAmount);
		System.out.println("Expected Gst Amount is: "+ExpectedGstAmountFormat);

		String ActualGstAmount = driver.findElement(By.id("GST")).getAttribute("value");
		System.out.println("Actual Gst Amount is: "+ActualGstAmount);

		soft.assertEquals(ActualGstAmount, ExpectedGstAmountFormat, 
				"Actual and Expected Gst Amount Mismatched");

		System.out.println();

		//CURRENCY CALCULATION
		System.out.println("*** Grand Total Calculation With Currency ***");
		System.out.println("Sub Total in Double: "+finalTotalAmountDouble);

		String currencyName = so.CurrencyCode.getText().trim();
		System.out.println("Currency Name is: " +currencyName);

		if (currencyName.equalsIgnoreCase("INR")) {
			double currencyRate = 0.18;
			double total = (finalTotalAmountDouble * currencyRate);
			System.out.println("Total Amount in INR ₹:" + total);

		} else if (currencyName.equalsIgnoreCase("SGD")) {
			double currencyRate = 1;
			double total = (finalTotalAmountDouble * currencyRate);
			System.out.println("Total Amount in SGD S$:" + total);

		} else if (currencyName.equalsIgnoreCase("USD")) {
			double currencyRate = 1.35;
			double total = (finalTotalAmountDouble * currencyRate);
			System.out.println("Total Amount in USD $:" +total);

		} else {
			System.out.println("Invalid or unsupported currency: " + currencyName);
		}
		System.out.println();

		//DECIMAL PLACE (2 or 4)
		System.out.println("*** Decimal Place ***");
		if (DecimalCalculationForSales == 2) {
			System.out.println("2 Decimal Place Amount is: "+finalTotalAmount);

		} else if (DecimalCalculationForSales == 4) {
			System.out.println("4 Decimal Place Amount is: "+finalTotalAmount);

		} else {
			System.out.println("Invalid Decimal Format");
		}

		System.out.println();

		click(so.ConvertInvoice);		
		click(so.PopupOk);    
		Thread.sleep(3000);

		int SISize = dataList.size();
		for (int j = 0; j < SISize; j++) {
			ExcelData excelData = dataList.get(j);

			WebElement batchFile = driver.findElement(
					By.xpath("(//table[@id='SalesTable']//tbody//tr//td[2][normalize-space()= '" + excelData.ProductName
							+ "']//following::td[11]//a[@class='fa fa-folder-open Popup'])[1]"));
			js.executeScript("arguments[0].click();", batchFile);

			String ctnQtyValue = driver.findElement(By.xpath("(//div//strong[contains(text(),'" + excelData.ProductName
					+ "')]//following::input[@id='BQty'])[1]")).getAttribute("value");
			System.out.println("B.Qty Is:" + ctnQtyValue);
			String pcsQtyValue = driver.findElement(By.xpath("(//div//strong[contains(text(),'" + excelData.ProductName
					+ "')]//following::input[@id='LQty'])[1]")).getAttribute("value");
			System.out.println("L.Qty Is:" + pcsQtyValue);
			Thread.sleep(1000);

			if (IsCartonManagement == false) {

				WebElement lQty = driver.findElement(By.xpath("(//div//strong[contains(text(), '"
						+ excelData.ProductName + "')]//following::input[@id='LooseQty'])[1]"));
				js.executeScript("arguments[0].click();", lQty);
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(pcsQtyValue);
				driver.findElement(By.xpath("(//div//strong[contains(.,'" + excelData.ProductName
						+ "')]//following::button[text()='Add'])[1]")).click();
				Thread.sleep(3000);

			} else {

				if (!ctnQtyValue.equals("0")) {

					WebElement bQty = driver.findElement(By.xpath("(//div//strong[contains(text(),'"
							+ excelData.ProductName + "')]//following::input[@id='BulkQty'])[1]"));
					js.executeScript("arguments[0].click();", bQty);
					bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
					bQty.sendKeys(ctnQtyValue);

				}

				WebElement lQty = driver.findElement(By.xpath("(//div//strong[contains(text(), '"
						+ excelData.ProductName + "')]//following::input[@id='LooseQty'])[1]"));
				js.executeScript("arguments[0].click();", lQty);
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(pcsQtyValue);
				driver.findElement(By.xpath("(//div//strong[contains(.,'" + excelData.ProductName
						+ "')]//following::button[text()='Add'])[1]")).click();
				Thread.sleep(3000);

			}

		}

		js.executeScript("arguments[0].click();", so.SaveandClose);

		/*
		 * if (IsCartonManagement != true) {
		 * 
		 * String alertText = driver.findElement(By.id("popup_message")).getText();
		 * System.out.println("Alert Message: " + alertText);
		 * 
		 * }
		 */
		System.out.println("*Sales Invoice Save Successfull*");

		System.out.println("**********************************");

		Thread.sleep(3000);
	}

	@Test(priority = 12, dependsOnMethods = "ERPLoginPage")
	public void ReserverQtyCalculation() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.navigate().to(url + "SalesPurchases/SalesOrderIndex");
		Thread.sleep(5000);
		SalesOrder so = new SalesOrder(driver);
		System.out.println("****************************************");

		click(so.AddOrder);
		Thread.sleep(3000);

		int SOSize = dataList.size();
		for (int i = 0; i < SOSize; i++) {
			ExcelData excelData = dataList.get(i);

			if (!excelData.Customer.isEmpty()) {
				Thread.sleep(2000);
				click(so.Customer);
				WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search'][1]")));
				searchInput.sendKeys(excelData.Customer + Keys.ENTER);
				Thread.sleep(2000);
			}

			click(so.Product);
			driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
			.sendKeys(excelData.ProductName + Keys.ENTER);

			click(so.Qty);
			click(so.Uom);

			List<WebElement> subUomOption = driver.findElements(By.xpath("//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
			for (WebElement option : subUomOption) {
				if (option.getText().trim().equals(excelData.Uom)) {
					option.click();
					break;
				}
			}

			String actualReservedQtyStr = driver.findElement(By.id("Reserved")).getAttribute("value");
			System.out.println("Actual Reserved Qty is: " + actualReservedQtyStr);

			int salesOrderDataListSize = salesOrderDataList.size();
			for (int j = 0; j < salesOrderDataListSize; j++) {
				salesOrderData salesOrderData = salesOrderDataList.get(j);

				if (excelData.ProductName.equals(salesOrderData.productName)) {

					String[] Reserverqtysplit = salesOrderData.ReservedQty.split("/");
					for (String Reserve : Reserverqtysplit) {
						if (Reserve.contains("B")) {
							String replaceAll = Reserve.replaceAll("[A-Za-z]", "");
							System.out.println("Replace Value is: "+replaceAll);
						}
						else {


						}


					}




					double reservedQtyDouble = Double.parseDouble(salesOrderData.ReservedQty);


					double qtyDouble = Double.parseDouble(excelData.Qty);
					double expectedReservedQty = reservedQtyDouble + qtyDouble;
					System.out.println("Expected Reserved Qty is: " + expectedReservedQty);

					soft.assertEquals(actualReservedQtyStr, expectedReservedQty,
							"Actual and Expected Reserved Qty is Mismatched for Product " + excelData.ProductName);

					break;


				}
			}

		}
	}
	
	class ExcelData1 {
		
		private String Customer;
		private String CurrencyCode;
		private String CurrencyRate;
		private String GstType;
		private String ProductName;
		private String Uom;
		private String Qty;
		private String Foc;
		private String DiscountPercentage;
		private String DiscountAmount;
		private boolean UnitDiscCheckbox;
		private String UnitDiscPercentage;
		private String UnitDiscAmount;
		private String Price;
		private boolean IsSpecialPriceCheckbox;
		private String BSplPrice;
		private String LSplPrice;
		private String OverAllDiscountAmount;
		private String OverAllDiscPercentage;
		private String GstPercentage;
		private boolean ZeroGst;
		
		public ExcelData1(String Customer, String CurrencyCode, String CurrancyRate, String GstType,String ProductName, String Uom,
				String Qty, String Foc, String DiscountPercentage, String DiscountAmount, boolean UnitDiscCheckbox, String UnitDiscPercentage,
				String UnitDiscAmount, String Price, boolean IsSpecialPriceCheckbox, String BSplPrice, String LSplPrice, String OverAllDiscountAmount,
				String OverAllDiscPercentage, String GstPercentage, boolean ZeroGst) {
			super();
			
			this.Customer = Customer;
			this.CurrencyCode = CurrencyCode;
			this.CurrencyRate = CurrancyRate;
			this.GstType = GstType;
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
			this.BSplPrice = BSplPrice;
			this.LSplPrice = LSplPrice;
			this.OverAllDiscountAmount = OverAllDiscountAmount;
			this.OverAllDiscPercentage = OverAllDiscPercentage;
			this.GstPercentage = GstPercentage;
			this.ZeroGst = ZeroGst;
			
			
		}
		
		
		
	}





}
