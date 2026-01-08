package com.Purchase;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BaseClass.BaseClass;
import com.PomClass.GoodReceivingNote;
import com.PomClass.Login;
import com.PomClass.Product;
import com.PomClass.PurchaseInvoice;
import com.PomClass.PurchaseOrder;
import com.PomClass.SystemSettings;
import com.PomClass.Vendors;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PurchaseGRNTest extends BaseClass {

	private String url;

	SoftAssert soft = new SoftAssert();

	@Test(priority = 1)
	public void ERPLoginPage() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://erpauto.dev1.adaptivebizapp.com/account/login");
		url = "https://erpauto.dev1.adaptivebizapp.com/ERP/";
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
	} // ERP login page

	@DataProvider
	public Object[][] Util2() {
		Object[][] data = Util1.getTestData("C:\\Adaptive\\Automation\\Bizapp\\PurchaseGRN.xlsx", "Sheet1");
		return data;

	}

	//Excel Data:-
	@SuppressWarnings("unused")
	class ExcelData {
		private String Vendor;
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
		private String ReturnQty;

		public ExcelData(String Vendor, String CurrencyCode, String CurrancyRate, String GstType, String Type,
				String ProductCode, String ProductName, String Uom, String Qty, String Foc, String DiscountPercentage,
				String DiscountAmount, String UnitDiscCheckbox, String UnitDiscPercentage, String UnitDiscAmount,
				String Price, String IsSpecialPriceCheckbox, String SpecialPrice, String OverAllDiscountType,
				String OverAllDiscountAmount, String OverAllDiscountPercentage, String GstPercentage,
				String ZeroGst, String BatchProduct, String ReturnQty) {
			super();

			this.Vendor = Vendor;
			this.CurrencyCode = CurrencyCode;
			this.CurrencyRate = CurrancyRate;
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
			this.ReturnQty = ReturnQty;

		}

	}

	ArrayList<ExcelData> excelDataList = new ArrayList<>();

	List<String> ProductList = new ArrayList<String>();
	Set<String> ProductSet = new LinkedHashSet<String>();
	List<String> VendorList = new ArrayList<String>();
	Set<String> VendorSet = new LinkedHashSet<String>();

	@Test(priority = 4, dataProvider = "Util2", dependsOnMethods = "ERPLoginPage")
	public void GetData(String Vendor, String CurrencyCode, String CurrancyRate, String GstType, String Type,
			String ProductCode, String ProductName, String Uom, String Qty, String Foc, String DiscountPercentage,
			String DiscountAmount, String UnitDiscCheckbox, String UnitDiscPercentage, String UnitDiscAmount,
			String Price, String IsSpecialPriceCheckbox, String SpecialPrice, String OverAllDiscountType,
			String OverAllDiscountAmount, String OverAllDiscountPercentage, String GstPercentage, 
			String ZeroGst, String BatchProduct, String ReturnQty) {

		ExcelData data = new ExcelData(Vendor, CurrencyCode, CurrancyRate, GstType, Type, ProductCode, ProductName,
				Uom, Qty, Foc, DiscountPercentage, DiscountAmount, UnitDiscCheckbox, UnitDiscPercentage, UnitDiscAmount,
				Price, IsSpecialPriceCheckbox, SpecialPrice, OverAllDiscountType, OverAllDiscountAmount,
				OverAllDiscountPercentage, GstPercentage, ZeroGst, BatchProduct, ReturnQty);

		excelDataList.add(data);
		ProductSet.add(ProductCode);
		VendorSet.add(Vendor);

	}

	//System Settings:-
	private boolean IsZeroQtyPurchase;
	private boolean IsZeroGSTManagement;
	private boolean IsMultipleProductForPurchase;
	private boolean IsGSTManagement;
	private boolean IsMultipleServiceForPurchase;
	private boolean IsCurrencyEnabled;
	private boolean IsWarehouseManagement;
	private boolean IsBarcodeManagementInPurchase;
	private boolean IsOpenItemManagementInPurchase;
	private float DecimalCalculationForPurchase;
	private boolean IsEnableDirectPOtoGRN;
	private boolean IsEnableDirectPOtoSO;
	private boolean IsEnableItemLevelDiscountInPurchase;
	private boolean IsMultiWordSearchInProduct;
	private boolean IsFOCManagementInPI;

	//@Ignore
	@Test(priority = 10, dependsOnMethods = "ERPLoginPage")
	public void SystemSettingsPage() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		SystemSettings ss = new SystemSettings(driver);

		driver.navigate().to(url + "SystemSetting");
		Thread.sleep(4000);
		System.out.println("*System Settings Page*");

		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsZeroQtyPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsZeroQtyPurchase = ss.BooleanValue.isSelected();
		System.out.println("IsZeroQtyPurchase: "+IsZeroQtyPurchase);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsZeroGSTManagement" + Keys.ENTER);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsZeroGSTManagement = ss.BooleanValue.isSelected();
		System.out.println("IsZeroGSTManagement: "+IsZeroGSTManagement);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsMultipleProductForPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsMultipleProductForPurchase = ss.BooleanValue.isSelected();
		System.out.println("IsMultipleProductForPurchase : "+IsMultipleProductForPurchase);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsGSTManagement" + Keys.ENTER);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsGSTManagement = ss.BooleanValue.isSelected();
		System.out.println("IsGSTManagement: "+IsGSTManagement);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsMultipleServiceForPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsGSTManagement = ss.BooleanValue.isSelected();
		System.out.println("IsMultipleServiceForPurchase: "+IsMultipleServiceForPurchase);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsCurrencyEnabled" + Keys.ENTER);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsCurrencyEnabled = ss.BooleanValue.isSelected();
		System.out.println("IsCurrencyEnabled: "+IsCurrencyEnabled);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsWarehouseManagement" + Keys.ENTER);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsWarehouseManagement = ss.BooleanValue.isSelected();
		System.out.println("IsWarehouseManagement: "+IsWarehouseManagement);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsBarcodeManagementInPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsBarcodeManagementInPurchase = ss.BooleanValue.isSelected();
		System.out.println("IsBarcodeManagementInPurchase: "+IsBarcodeManagementInPurchase);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsOpenItemManagementInPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsOpenItemManagementInPurchase = ss.BooleanValue.isSelected();
		System.out.println("IsOpenItemManagementInPurchase: "+IsOpenItemManagementInPurchase);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "DecimalCalculationForPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		String DecimalCalculationForPurchaseString = driver
				.findElement(By.xpath("//input[@id='DecimalValue']")).getAttribute("value");
		DecimalCalculationForPurchase = Float.parseFloat(DecimalCalculationForPurchaseString);
		System.out.println("DecimalCalculationForPurchase: "+DecimalCalculationForPurchase);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsEnableDirectPOtoGRN" + Keys.ENTER);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsEnableDirectPOtoGRN = ss.BooleanValue.isSelected();
		System.out.println("IsEnableDirectPOtoGRN: "+IsEnableDirectPOtoGRN);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsEnableDirectPOtoSO" + Keys.ENTER);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsEnableDirectPOtoSO = ss.BooleanValue.isSelected();
		System.out.println("IsEnableDirectPOtoSO: "+IsEnableDirectPOtoSO);
		click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsEnableItemLevelDiscountInPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsEnableItemLevelDiscountInPurchase = ss.BooleanValue.isSelected();
		System.out.println("IsEnableItemLevelDiscountInPurchase:" +IsEnableItemLevelDiscountInPurchase);
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
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsFOCManagementInPI");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsFOCManagementInPI = ss.BooleanValue.isSelected();
		System.out.println("IsFOCManagementInPI:" +IsFOCManagementInPI);
		click(ss.Back);
		System.out.println();

	} // System settings


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
		private String batch;
		private String purchaseCOAValue;
		private String salesCOAValue;
		private String currentStockValue;
		private String TotalStock;
		private String uomValue;
		private String IsCartonSelected;
		private boolean IsCarton;
		private String CartonPrice;
		private boolean IsNonCarton;
		private boolean IsBase;
		private String SellingPrice;
		private String LPPrice;

		public product(String productCode, String productName, String departmentValue, String categoryValue,
				String brandValue, String profitMarginValue, String marginToleranceValue, String vendorNameValue,
				String batch, String purchaseCOAValue, String salesCOAValue, String currentStockValue,
				String TotalStock, String uomValue, String IsCartonSelected, boolean IsCarton, String CartonPrice,
				boolean IsNonCarton, boolean IsBase, String SellingPrice, String LPPrice) {
			super();

			this.productCode = productCode;
			this.productName = productName;
			this.departmentValue = departmentValue;
			this.categoryValue = categoryValue;
			this.brandValue = brandValue;
			this.profitMarginValue = profitMarginValue;
			this.marginToleranceValue = marginToleranceValue;
			this.vendorNameValue = vendorNameValue;
			this.batch = batch;
			this.purchaseCOAValue = purchaseCOAValue;
			this.salesCOAValue = salesCOAValue;
			this.currentStockValue = currentStockValue;
			this.TotalStock = TotalStock;
			this.uomValue = uomValue;
			this.IsCartonSelected = IsCartonSelected;
			this.IsCarton = IsCarton;
			this.CartonPrice = CartonPrice;
			this.IsNonCarton = IsNonCarton;
			this.IsBase = IsBase;
			this.SellingPrice = SellingPrice;
			this.LPPrice = LPPrice;

		}

	}

	@SuppressWarnings("unused")
	class ProductUOM {

		private String productCode1;
		private String uomValue;
		private boolean IsCarton;
		private String CartonPrice;
		private boolean IsNonCarton;
		private boolean IsBase;
		private int ProductUOMtablesize;
		private String SubUOM;
		private String CurStock;
		private String LPP;
		private String SP;

		public ProductUOM(String productCode1, String uomValue, boolean IsCarton, String CartonPrice,
				boolean IsNonCarton, boolean IsBase, int ProductUOMtablesize, String SubUOM, String CurStock,
				String LPP, String SP) {
			super();

			this.productCode1 = productCode1;
			this.uomValue = uomValue;
			this.IsCarton = IsCarton;
			this.CartonPrice = CartonPrice;
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

	//@Ignore
	@Test(priority = 12, dependsOnMethods = "ERPLoginPage")
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
					.findElement(By.xpath("//table[@id='producttable']//tbody//tr//td[1][normalize-space()='" + product
							+ "']//following::td[9]//a[@title='Details'][1]"));
			js.executeScript("arguments[0].click();", DetailsIcon);
			Thread.sleep(3000);

			String productCodeUom = driver.findElement(By.xpath("//div[@class='col-lg-7']//following::small"))
					.getText();
			String productCode = productCodeUom.split(" - UOM")[0];
			System.out.println("ProductCode: " + productCode);

			String productName = driver.findElement(By.xpath("//div[@class='col-lg-7']//h2//b")).getText();
			System.out.println("ProductName: " + productName);

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
					.findElement(By.xpath("(//dt[normalize-space()='Current Stock - HQ']//following-sibling::dd)[1]"))
					.getText();
			System.out.println("CurrentStock :" + currentStockValue);

			String totalStock = driver
					.findElement(By.xpath("//dt[normalize-space()='Total Stock']//following-sibling::dd[1]")).getText();
			System.out.println("Total STock: " + totalStock);

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

			String batch = driver.findElement(By.xpath("(//dt[normalize-space()='Batch']//following::dd)[1]"))
					.getText();
			System.out.println("Batch: " + batch);

			String IsCartonSelected = driver
					.findElement(By.xpath("//dt[normalize-space()='Carton']//following-sibling::dd[1]")).getText()
					.trim();
			System.out.println("Is Carton Selected: " + IsCartonSelected);

			Thread.sleep(3000);
			WebElement stockTab = driver.findElement(By.xpath("//a[text()='Stock']"));
			stockTab.click();
			Thread.sleep(2000);

			boolean IsCarton = false;
			boolean IsNonCarton = false;
			boolean IsBase = false;
			int ProductUOMtablesize = 0;

			ProductUOMtablesize = driver.findElements(By.xpath("//table[@id='ProductPartialUOM']//tbody//tr//td[2]"))
					.size();
			System.out.println("ProductUOMtablesize " + ProductUOMtablesize);
			String CartonPrice = null;

			if (IsCartonSelected.equalsIgnoreCase("True")) {
				IsCarton = true;

			} else if (IsCartonSelected.equalsIgnoreCase("False") && ProductUOMtablesize > 0) {
				IsNonCarton = true;

			} else if (IsCartonSelected.equalsIgnoreCase("False") && ProductUOMtablesize == 0) {
				IsBase = true;
			}

			String SellingPrice = null;
			String LPPrice = null;

			if (ProductUOMtablesize == 0) {

				Thread.sleep(1000);
				click(prod.InfoTab);
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

					ProductUOM pruom = new ProductUOM(productName, uomValue, IsCarton, CartonPrice, IsNonCarton, IsBase,
							ProductUOMtablesize, SubUOM, CurStock, LPP, SP);
					ProductUOMDetailsList.add(pruom);
					UOMSet.add(uomValue);

				}

			}

			product pr = new product(productCode, productName, departmentValue, categoryValue, brandValue,
					profitMarginValue, marginToleranceValue, vendorNameValue, batch, purchaseCOAValue, salesCOAValue,
					currentStockValue, totalStock, uomValue, IsCartonSelected, IsCarton, CartonPrice, IsNonCarton,
					IsBase, SellingPrice, LPPrice);
			ProductDetailsList.add(pr);

			Thread.sleep(2000);
			driver.navigate().back();
			System.out.println("***");

		}

	} // Product page

	//Vendor Page:-
	@SuppressWarnings("unused")
	class Vendor {
		private String VendorName;
		private String GSTType;
		private String APAccount;

		public Vendor(String VendorName, String GSTType, String APAccount) {
			super();

			this.VendorName = VendorName;
			this.GSTType = GSTType;
			this.APAccount = APAccount;
		}
	}

	ArrayList<Vendor> vendorDetailsList = new ArrayList<>();
	//@Ignore
	@Test(priority = 14, dependsOnMethods = "ERPLoginPage")
	public void VendorPage() throws InterruptedException {

		VendorList.addAll(VendorSet);

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		Vendors vd = new Vendors(driver);

		driver.navigate().to(url + "SalesPurchases/Vendor");
		Thread.sleep(4000);
		System.out.println("*Vendor Page*");

		for (String vendor : VendorSet) {

			if (vendor != null) {

				WebElement VendorSearchField = driver.findElement(By.id("SearchString"));
				VendorSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				VendorSearchField.sendKeys(vendor);
				Thread.sleep(1000);

				WebElement fetchBtn = driver.findElement(By.id("searchstring"));
				js.executeScript("arguments[0].click();", fetchBtn);
				Thread.sleep(3000);

				WebElement DetailsIcon = driver.findElement(
						By.xpath("//table[@id='vendortable']//tbody//tr//td[2]//following::td[6]//a[@title='Details']"));
				js.executeScript("arguments[0].click();", DetailsIcon);

				String VendorName = driver.findElement(By.xpath("(//div[@class='col-lg-8']//child::h2//b)[1]"))
						.getText();
				System.out.println("Vendar Name: "+VendorName);

				String APAccount = driver.findElement(By.xpath("//dt[normalize-space()='A/P Account']//following-sibling::dd[1]"))
						.getText().trim();
				System.out.println("A/P Account: " + APAccount);

				WebElement InfoTab = driver.findElement(By.xpath("//a[text()='Info']"));
				js.executeScript("arguments[0].click();", InfoTab);
				Thread.sleep(2000);

				String GSTType = driver.findElement(By.xpath("//dt[normalize-space()='GST Type']//following-sibling::dd[1]"))
						.getText().trim();
				System.out.println("GST Type: " + GSTType);			
				System.out.println("***");

				Vendor VendorDetails = new Vendor(VendorName, GSTType, APAccount);
				vendorDetailsList.add(VendorDetails);

				click(vd.Back);
			}
		}
	} // Vendor page


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
	//@Ignore
	@Test(priority = 16, dependsOnMethods = "ERPLoginPage")
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
			WebElement UOMSearchField = driver.findElement(
					By.xpath("//span[@id='select2-DropDown-container']//following::input[@type='search']"));
			UOMSearchField.sendKeys(uom + Keys.ENTER);

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
	} // Uom page
	
	
	public class PurchaseOrderData {
		
		public String PurchaseOrderNo;
		
		public PurchaseOrderData(String PurchaseOrderNo) {
			super();
			
			this.PurchaseOrderNo = PurchaseOrderNo;
		}	
	}
	
	ArrayList<PurchaseOrderData> purchaseOrderDataList = new ArrayList<>();

	@Test(priority = 18, dependsOnMethods = "ERPLoginPage")
	public void PurchaseOrderToInvoice() throws InterruptedException, IOException {
		
		driver.navigate().to(url + "Purchases/PurchaseOrderIndex");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("*Purchase Form Page*");
		System.out.println();
		JavascriptExecutor js = (JavascriptExecutor) driver;
	//	WebDriverWait wait = new WebDriverWait(driver, 30);
		PurchaseOrder po = new PurchaseOrder(driver);
		
		js.executeScript("arguments[0].click();", po.AddPurchaseOrder);
		Thread.sleep(3000);		

		String getExcelGstType = "";
		String getexcelOverAllDiscountType = "";
		String getExcelOverAllDiscountPercentage = "";
		String getExcelOverAllDiscountAmount = "";
		String getExcelGstPercentage = "";
		String getExcelCurrencyRate = "";
		String productPrice = "";
		double ExpSubTotal = 0;
		double ExpZeroGstProductamount = 0;
		double expectedDiscountAmount = 0;
		
		String getPurchaseNo = driver.findElement(By.id("PurchaseOrderNo")).getAttribute("value");
		System.out.println("getPurchaseNo: "+getPurchaseNo);
		
		PurchaseOrderData purchaseData = new PurchaseOrderData(getPurchaseNo);
		purchaseOrderDataList.add(purchaseData);
		
		int excelDataListSize = excelDataList.size();
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			if (excelData.Vendor.isEmpty() == false) {
				Thread.sleep(2000);
				click(po.Vendor);
				click(po.VendorSearch);
				Sendkeys(po.VendorSearch, excelData.Vendor +Keys.ENTER);
				Thread.sleep(2000);

			}

			click(po.GSTType);
			WebElement GstSearchInput = driver.findElement(
					By.xpath("//span[@id='select2-GSTTypeId-container']//following::input[@type='search']"));
			GstSearchInput.sendKeys(excelData.GstType + Keys.ENTER);

			if (i == 0) {

				getExcelGstType = excelData.GstType;
				getexcelOverAllDiscountType = excelData.OverAllDiscountType;
				getExcelOverAllDiscountPercentage = excelData.OverAllDiscountPercentage;
				getExcelOverAllDiscountAmount = excelData.OverAllDiscountAmount;
				getExcelGstPercentage = excelData.GstPercentage;
				getExcelCurrencyRate = excelData.CurrencyRate;
			}


			if (excelData.Type.equalsIgnoreCase("Product")) {

				String productCheckbox = driver.findElement(By.id("ProductCheck")).getAttribute("checked");
				if (!productCheckbox.equalsIgnoreCase("true")) {
					click(po.ProductCheckBox);

				}

			} else if (excelData.Type.equals("Service")) {

				if (!po.ServiceCheckBox.isSelected()) {
					click(po.ServiceCheckBox);
				}

			} else if (excelData.Type.equals("Open")) {

				if (!po.OpenCheckBox.isSelected()) {
					click(po.OpenCheckBox);
				}

			}

			if (excelData.Type.equalsIgnoreCase("Product")) {

				click(po.ChooseproductName);
				driver.findElement(
						By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
				.sendKeys(excelData.ProductCode + Keys.ENTER);

			} else if (excelData.Type.equalsIgnoreCase("Service")) {

				driver.findElement(By.xpath("//span[@id='select2-ServiceId-container']//following::input[@type='search']"))
				.sendKeys(excelData.ProductName + Keys.ENTER);

			} else if (excelData.Type.equalsIgnoreCase("Open")) {

				po.OpenProduct.sendKeys(excelData.ProductName + Keys.ENTER);

			}

			js.executeScript("arguments[0].click();", po.Quantity);
			if (excelData.Type.equalsIgnoreCase("Product")) {

				click(po.UOM);
				List<WebElement> subUomOption = driver.findElements(By.xpath(
						"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
				for (WebElement option : subUomOption) {
					if (option.getText().trim().equals(excelData.Uom)) {
						option.click();
						break;
					}

				}

			} else if (excelData.Type.equalsIgnoreCase("Service")) {

				click(po.UOM);
				List<WebElement> subUomOption = driver.findElements(By.xpath(
						"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
				for (WebElement option : subUomOption) {
					if (option.getText().trim().equals(excelData.Uom)) {
						option.click();
						break;
					}

				}

			} else if (excelData.Type.equalsIgnoreCase("Open")) {

				click(po.UOM);
				List<WebElement> subUomOption = driver.findElements(By.xpath(
						"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
				for (WebElement option : subUomOption) {
					if (option.getText().trim().equals(excelData.Uom)) {
						option.click();
						break;
					}
				}
			}

			// Qoh Calculation:-
			System.out.println("*** Grand Total Calculation With QOH ***");

			String qohStock1 = driver.findElement(By.id("QOH")).getAttribute("value");

			for (product productDetails : ProductDetailsList) {

				if (productDetails.productName.equalsIgnoreCase(excelData.ProductName)) {

					if (productDetails.IsCartonSelected.equalsIgnoreCase("true")) {

						if (excelData.Uom.equalsIgnoreCase("1KG") ||
								excelData.Uom.equalsIgnoreCase("1X10KG")||
								excelData.Uom.equalsIgnoreCase("1X1KG")||
								excelData.Uom.equalsIgnoreCase("1X1X1KG")||
								excelData.Uom.equalsIgnoreCase("KG")) {

							String[] split = productDetails.currentStockValue.split("[ B/L]+");
							int boxStock = Integer.parseInt(split[0]);
							int looseStock = Integer.parseInt(split[1]);

							int multipleBoxStock = (boxStock * 10);
							int addLooseStock = (multipleBoxStock + looseStock);
							//	System.out.println("Lower Uom Stock: "+addLooseStock);

							String stock = "0 B/" + addLooseStock + " L";
							//	System.out.println("Lower Uom Current Stock: "+stock);

							System.out.println("carton Product");
							System.out.println("Product Name: " + productDetails.productName);
							System.out.println("Qoh Stock: " + qohStock1);
							System.out.println("current Stock: " + stock);
							soft.assertEquals(qohStock1, stock,
									"Actual and Expected QOH Mismatched for Product: " + productDetails.productName);

						} else {

							System.out.println("carton Product");
							System.out.println("Product Name: " + productDetails.productName);
							System.out.println("Qoh Stock: " + qohStock1);
							System.out.println("current Stock: " +productDetails.currentStockValue);

							soft.assertEquals(qohStock1, productDetails.currentStockValue,
									"Actual and Expected QOH Mismatched for Product: " + productDetails.productName);

						} 		
					}

					else if (productDetails.IsCartonSelected.equalsIgnoreCase("false")) {

						boolean isNonCartonWithUOM = false;
						double totalCalculatedStock = 0;

						for (ProductUOM productUom : ProductUOMDetailsList) {
							if (productUom.ProductUOMtablesize > 0
				&& productUom.productCode1.equals(productDetails.productName)) {

								for (UOM uomDetails : UomDetailsList) {
									if (uomDetails.UomCodeValue.equalsIgnoreCase(productUom.SubUOM)) {

										double curStockDouble = Double.parseDouble(productUom.CurStock.trim());
										double uomUnitDouble = Double.parseDouble(uomDetails.UomUnits.trim());

										totalCalculatedStock += (curStockDouble * uomUnitDouble);
										isNonCartonWithUOM = true;
									}
								}
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
					}
					break;
				}
			}
			System.out.println();

			//Qty:-
			Sendkeys(po.Quantity, excelData.Qty);

			// Foc:-
			if (excelData.Type.equalsIgnoreCase("Product")) {

				if (IsFOCManagementInPI == true) {
					System.out.println("IsFOCManagementInSO: " + IsFOCManagementInPI);
					click(po.Foc);
					Sendkeys(po.Foc, excelData.Foc);

				}

			} else if (excelData.Type.equalsIgnoreCase("Service")) {

				click(po.Foc);
				Sendkeys(po.Foc, excelData.Foc);

			} else {

				System.out.println("Foc Field Is Not Displayed");
			}

			//Price:-
			click(po.SGD);
			po.SGD.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(po.SGD, excelData.Price);
			Thread.sleep(1000);

			//Item Level Discount:-
			if (IsEnableItemLevelDiscountInPurchase == true) {

				click(po.DiscountAmount);
				Sendkeys(po.DiscountAmount, Keys.CONTROL + "a" + Keys.DELETE);
				Sendkeys(po.DiscountAmount, excelData.DiscountAmount);

			} else {

				System.out.println("Unit Discount Fields Not Displayed");
			}

			double Total = 0;
			double excelQtyDouble = Double.parseDouble(excelData.Qty);
			double priceDouble = Double.parseDouble(excelData.Price);

			boolean unitDiscCheckboxBoolean = Boolean.parseBoolean(excelData.UnitDiscCheckbox);

			if (unitDiscCheckboxBoolean == true) {

				if (excelData.DiscountPercentage.isBlank() == false) {
					double discountPercentDouble = Double.parseDouble(excelData.DiscountPercentage);

					double discountPercentPrice = (discountPercentDouble / 100) * priceDouble;
					double discountAmountRound = Math.round(discountPercentPrice);
					double discAmountforPercent = discountAmountRound / 100;

					Total = (priceDouble - discAmountforPercent) * excelQtyDouble;
					System.out.println("Item Level Discount Percentage Total: " + Total);

				} else if (excelData.DiscountAmount.isBlank() == false) {
					double discAmtDouble = Double.parseDouble(excelData.DiscountAmount);

					Total = (priceDouble - discAmtDouble) * excelQtyDouble;
					System.out.println("Item Level Discount Amount Total: " + Total);
				}

			} else {

				if (excelData.DiscountPercentage.isBlank() == false) {

					double discPercentDouble = Double.parseDouble(excelData.DiscountPercentage);					
					double discountPerAmt = (discPercentDouble / 100) * Total;
					double discPerAmtRound = Math.round(discountPerAmt * 100);
					double discAmtforPer = discPerAmtRound / 100;



					Total = Total - discAmtforPer;
					System.out.println("Discount Percentage Amount Total: "+Total);

				} else if (excelData.DiscountAmount.isBlank() == false) {

					double discAmtDouble1 = Double.parseDouble(excelData.DiscountAmount);
					double excelPriceDouble = Double.parseDouble(excelData.Price);					
					double discountAmount = (excelPriceDouble * excelQtyDouble);

					expectedDiscountAmount = (discountAmount - discAmtDouble1);

					Total = expectedDiscountAmount - Total;
					System.out.println("Discount Amount Total: "+Total);

				}

			}

			//Add Button:-
			click(po.AddButton);
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", po.Quantity);
			System.out.println();

			productPrice = driver.findElement(By.xpath("//table[@id='PurchaseOrderTable']//tbody//tr//td[2]"
					+ "//div//textarea[contains(text(),'"+excelData.ProductName+"')]//following::td[@class='orderTotal']")).getText();
			String replaceAllProductPrice = productPrice.replaceAll(",", "");
			double productPriceDouble = Double.parseDouble(replaceAllProductPrice);
			System.out.println("Actual Discount Amount: "+productPriceDouble);
			System.out.println("Expected Discount Amount: "+expectedDiscountAmount);
			System.out.println();	

			soft.assertEquals(productPriceDouble, expectedDiscountAmount, 
					"Actual and Expected Discount Amount Mismatched for "+excelData.ProductName);

			ExpSubTotal = ExpSubTotal + productPriceDouble;

			if (excelData.ZeroGst.equals("TRUE")) {
				ExpZeroGstProductamount = Double.parseDouble(replaceAllProductPrice) + ExpZeroGstProductamount;
				System.out.println("ExpZeroGstProductamount: "+ExpZeroGstProductamount);
			}

		}

		//Over All Discount:-
		click(po.OverAllDiscountType);
		Select overAllDiscTypeSelect = new Select(po.OverAllDiscountType);
		overAllDiscTypeSelect.selectByVisibleText(getexcelOverAllDiscountType);

		WebElement overAllDiscountType = driver.findElement(By.id("DiscountType"));
		Select overAllDiscountTypeSelect = new Select(overAllDiscountType);
		String getOverAllDiscountType = overAllDiscountTypeSelect.getFirstSelectedOption().getText();
		System.out.println("Over all Discount Type is: " + getOverAllDiscountType);

		Thread.sleep(1000);
		click(po.OverAllDiscount);
		if (getOverAllDiscountType.equals("$")) {
			po.OverAllDiscount.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(po.OverAllDiscount, getExcelOverAllDiscountAmount + Keys.ENTER);
			Thread.sleep(1000);
			click(po.Quantity);

		} else if (getOverAllDiscountType.equals("%")) {
			po.OverAllDiscount.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(po.OverAllDiscount, getExcelOverAllDiscountPercentage + Keys.ENTER);
			Thread.sleep(1000);
			click(po.Quantity);

		} else {
			System.out.println("No Over All Discount Amounr and Percentage");
		}
		System.out.println();

		// Sub Total Calculation:-
		System.out.println("*** Sub Total Calculation ***");
		String subTotalAmountString = driver.findElement(By.id("Subtotal")).getText();
		String replaceAllSubTotalAmountString = subTotalAmountString.replaceAll(",", "");
		double subTotalAmountDouble = Double.parseDouble(replaceAllSubTotalAmountString);
		System.out.println("Actual Sub Total Amount: " + subTotalAmountDouble);
		System.out.println("Expected Sub Total Amount: " + ExpSubTotal);
		System.out.println();

		soft.assertEquals(subTotalAmountDouble, ExpSubTotal, 
				"Actual and Expected SubTotal Mismatched");

		//Over All Discount Calculation:-
		System.out.println("*** Grand Total Calculation With Over All Discount and Percentage ***");

		double discountAmountDouble = Double.parseDouble(getExcelOverAllDiscountAmount);
		double discountPercentageDouble = Double.parseDouble(getExcelOverAllDiscountPercentage);
		double gstPercentage = Double.parseDouble(getExcelGstPercentage);
		double discountPercentageAmount = 0;
		double discountTotalAmount = 0;

		if (getOverAllDiscountType.equalsIgnoreCase("$")) {
			System.out.println("Current Discount Type is: $");

			discountTotalAmount = subTotalAmountDouble - discountAmountDouble;
			System.out.println("Discount Amount is: "+discountTotalAmount);
			System.out.println("Over All Discount Amount is: " + discountTotalAmount);

		} else if (getOverAllDiscountType.equalsIgnoreCase("%")) {
			System.out.println("Current Discount Type is: %");

			discountTotalAmount = (subTotalAmountDouble * discountPercentageDouble / 100);
			discountPercentageAmount = (subTotalAmountDouble - discountTotalAmount);

			System.out.println("Discount Percentage Amount is: " + discountTotalAmount);
			System.out.println("Over All Discount Percentage Amount is: " + discountPercentageAmount);
		}

		double divOverAllDisc = discountAmountDouble / subTotalAmountDouble;
		double finalWithoutGstAmount = 0;

		for (ExcelData excelData : excelDataList) {

			if (getOverAllDiscountType.equalsIgnoreCase("$")) {

				double withoutGstAmount = divOverAllDisc * ExpZeroGstProductamount;
				finalWithoutGstAmount = ExpZeroGstProductamount - withoutGstAmount;
				String formatFinalWithoutGstAmount = String.format("%.2f", finalWithoutGstAmount);
				System.out.println("Without Gst Product Amount: "+formatFinalWithoutGstAmount);

			} else if (excelData.ZeroGst.equalsIgnoreCase("true") && getOverAllDiscountType.equalsIgnoreCase("%")) {

				double zeroGstProductDiscountAmount = (ExpZeroGstProductamount * discountPercentageDouble / 100);
				double subrationZerGstAmount = (ExpZeroGstProductamount - zeroGstProductDiscountAmount);
				System.out.println("After Discount Zero Gst Product Amount is: " + subrationZerGstAmount);
				double withoutZeroGstAmount = (discountPercentageAmount - subrationZerGstAmount);
				System.out.println("Without Zero Gst Amount: "+withoutZeroGstAmount);
				System.out.println();


			}	
			break;
		}

		//double formatFinalWithoutGstAmountDouble = Double.parseDouble(formatFinalWithoutGstAmount);
		double subWithoutGstAmount = discountTotalAmount - finalWithoutGstAmount;
		String formatSubWithoutGstAmount = String.format("%.2f", subWithoutGstAmount);
		System.out.println("With Gst Product SubTotal: "+formatSubWithoutGstAmount);
		System.out.println();

		// GST CALCULATION
		System.out.println("*** Grand Total Calculation With GST ***");

		// double ExpectedGstAmount = 0;
		// double afterExpectedGstAmount = 0;
		double finalExpectedGstAmount = 0;

		System.out.println("Gst Type is: " + getExcelGstType);
		if (getExcelGstType.equalsIgnoreCase("Inclusive")) {

			finalExpectedGstAmount = (subWithoutGstAmount * gstPercentage) / 109;
			System.out.println("Inclusive Gst Amount is: " + finalExpectedGstAmount);

		} else if (getExcelGstType.equalsIgnoreCase("Exclusive")) {

			finalExpectedGstAmount = (subWithoutGstAmount * gstPercentage) / 100;
			System.out.println("Exclusive Gst Amount is: " + finalExpectedGstAmount);

		} else if (getExcelGstType.equalsIgnoreCase("Zero") || getExcelGstType.equalsIgnoreCase("Overseas")) {

			finalExpectedGstAmount = (subWithoutGstAmount * gstPercentage) / 100;
			System.out.println("Zero and Overseas Gst Amount is: " + finalExpectedGstAmount);

		}	

		String ActualGstAmount = driver.findElement(By.xpath
				("//table[@id='PurchaseOrderTable']//tfoot//tr//td//p[@id='FooterGST']")).getText();
		System.out.println("Actual Gst Amount is: " + ActualGstAmount);

		String ExpectedGstAmountFormat = String.format("%.2f", finalExpectedGstAmount);

		if (getExcelGstType.equalsIgnoreCase("Inclusive")) {

			finalExpectedGstAmount = 0;		
			System.out.println("Expected Gst Amount: " + ExpectedGstAmountFormat);

			soft.assertEquals(ActualGstAmount, ExpectedGstAmountFormat, "Actual and Expected Gst Amount Mismatched");
			System.out.println();

		} else {

			System.out.println("Expected Gst Amount is: " + ExpectedGstAmountFormat);

			soft.assertEquals(ActualGstAmount, ExpectedGstAmountFormat, "Actual and Expected Gst Amount Mismatched");
			System.out.println();
		}		

		// GRAND TOTAL AMOUNT
		System.out.println("*** Grand Total Amount ***");

		String finalTotalAmount = driver.findElement(By.xpath
				("//table[@id='PurchaseOrderTable']//tfoot//tr//td//p[@id='FooterTotal']")).getText();
		String replaceAllFinalTotalAmount = finalTotalAmount.replaceAll(",", "");
		double finalTotalAmountDouble = Double.parseDouble(replaceAllFinalTotalAmount);
		String finalTotalAmountFormat = String.format("%.2f", finalTotalAmountDouble);
		System.out.println("Actual Grand Total Amount is: " + finalTotalAmountFormat);

		double ExpectedGrandTotalAmount = (discountTotalAmount + finalExpectedGstAmount);
		String ExpectedGrandTotalAmountFormat = String.format("%.2f", ExpectedGrandTotalAmount);
		System.out.println("Expected Grand Total Amount is: " + ExpectedGrandTotalAmountFormat);
		System.out.println();

		soft.assertEquals(finalTotalAmountFormat, ExpectedGrandTotalAmountFormat, 
				"Actual and Expected Grand Total Mismatched");

		// CURRENCY CALCULATION
		System.out.println("*** Grand Total Calculation With Currency ***");
		System.out.println("Sub Total in Double: " + finalTotalAmountDouble);

		String currencyName = po.CurrencyCode.getText().trim();
		System.out.println("Currency Name is: " + currencyName);

		double total = 0;
		if (currencyName.equalsIgnoreCase("INR")) {
			double currencyRate = Double.parseDouble(getExcelCurrencyRate);
			total = (finalTotalAmountDouble * currencyRate);
			System.out.println("Total Amount in INR ₹:" + total);

		} else if (currencyName.equalsIgnoreCase("SGD")) {
			double currencyRate = Double.parseDouble(getExcelCurrencyRate);
			total = (finalTotalAmountDouble * currencyRate);
			System.out.println("Total Amount in SGD S$:" + total);

		} else if (currencyName.equalsIgnoreCase("USD")) {
			double currencyRate = Double.parseDouble(getExcelCurrencyRate);
			total = (finalTotalAmountDouble * currencyRate);
			System.out.println("Total Amount in USD $:" + total);

		} else {
			System.out.println("Invalid or unsupported currency: " + currencyName);
		}

		soft.assertEquals(finalTotalAmountDouble, total, "Actual and Expected Currency Mismatched");

		System.out.println();

		// DECIMAL PLACE (2 or 4)
		System.out.println("*** Decimal Place ***");

		if (DecimalCalculationForPurchase == 2) {
			System.out.println("2 Decimal Place Amount is: " + finalTotalAmount);

		} else if (DecimalCalculationForPurchase == 4) {
			System.out.println("4 Decimal Place Amount is: " + finalTotalAmount);

		} else {
			System.out.println("Invalid Decimal Format");
		}
		System.out.println();

		// Convert GRN Process:-
		click(po.ConvertGRN);
		click(po.PopupAlertOk);
		Thread.sleep(4000);

	} // Purchase oreder
	
	@Test(priority = 20, dependsOnMethods = "ERPLoginPage")
	public void GoodReceivingNote() {
		
		driver.navigate().to(url +"SalesPurchases/GoodsReceivingNotes");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		GoodReceivingNote grn = new GoodReceivingNote(driver);
		
		LocalDateTime TimeStamp = LocalDateTime.now();
		DateTimeFormatter DateTimeFormate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatedTimestamp = TimeStamp.format(DateTimeFormate);
		
		int purchaseOrderDataListSize = purchaseOrderDataList.size();
		for (int i = 0; i < purchaseOrderDataListSize; i++) {
			PurchaseOrderData purchaseOrderData = purchaseOrderDataList.get(i);
			
			WebElement edit = driver.findElement(By.xpath
					("(//table[@id='GoodsReceivingTable']//tbody//tr//td[normalize-space()='"+purchaseOrderData.PurchaseOrderNo+"']//following::td//a[@title='Edit'])[1]"));
			wait.until(ExpectedConditions.elementToBeClickable(edit)).click();
			
			String getGrnNo = driver.findElement(By.id("GRNNo")).getAttribute("value");
			System.out.println("getGrnNo: "+getGrnNo);
			
			wait.until(ExpectedConditions.elementToBeClickable(grn.Complete)).click();
			click(grn.AlertOK);
			
		} // Purchase order data list loop
		
		WebElement details = driver.findElement(By.xpath
				("(//table[@id='GoodsReceivingTable']//tbody//tr//td[normalize-space()='GRN-0015']//following::td//a[@title='Details'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(details)).click();
		
		WebElement convertInvoice = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ConvertInvoice")));
		js.executeScript("arguments[0].click();", convertInvoice);
		click(grn.AlertOK);
		
		WebElement invoiceNo = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ReferenceNo")));
		js.executeScript("arguments[0].click();", invoiceNo);
		invoiceNo.sendKeys(formatedTimestamp);
		
		
		for (ExcelData excelData : excelDataList) {
			
		
			
			
			if ("true".equalsIgnoreCase(excelData.BatchProduct)) {
				
				
				
			}
			
		} // Excel data list loop
		
		
		
	} // Good receiving note


} // Purchase GRN test
