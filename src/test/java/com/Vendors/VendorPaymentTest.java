package com.Vendors;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BaseClass.BaseClass;
import com.PomClass.*;
import com.Utility.Util1;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VendorPaymentTest extends BaseClass {

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
		Thread.sleep(3000);
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
	public Object[][] Util2() {
		Object[][] data = Util1.getTestData("C:\\Adaptive\\Automation\\Bizapp\\DirectPurchaseInvoice1.xlsx", "Sheet1");
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
		private String BatchNumber;
		private String MfgDate;
		private String ValPeriod;
		private String ExpDate;


		public ExcelData(String Vendor, String CurrencyCode, String CurrancyRate, String GstType, String Type,
				String ProductCode, String ProductName, String Uom, String Qty, String Foc, String DiscountPercentage,
				String DiscountAmount, String UnitDiscCheckbox, String UnitDiscPercentage, String UnitDiscAmount,
				String Price, String IsSpecialPriceCheckbox, String SpecialPrice, String OverAllDiscountType,
				String OverAllDiscountAmount, String OverAllDiscountPercentage, String GstPercentage,
				String ZeroGst, String BatchProduct, String BatchNumber, String MfgDate, String ValPeriod, String ExpDate) {
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
			this.BatchNumber = BatchNumber;
			this.MfgDate = MfgDate;
			this.ValPeriod = ValPeriod;
			this.ExpDate = ExpDate;

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
			String ZeroGst, String BatchProduct, String BatchNumber, String MfgDate, String ValPeriod, String ExpDate) {

		ExcelData data = new ExcelData(Vendor, CurrencyCode, CurrancyRate, GstType, Type, ProductCode, ProductName,
				Uom, Qty, Foc, DiscountPercentage, DiscountAmount, UnitDiscCheckbox, UnitDiscPercentage, UnitDiscAmount,
				Price, IsSpecialPriceCheckbox, SpecialPrice, OverAllDiscountType, OverAllDiscountAmount,
				OverAllDiscountPercentage, GstPercentage, ZeroGst, BatchProduct, BatchNumber, MfgDate, ValPeriod, ExpDate);

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
		click(ss.SystemSettingsFetch);
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
		click(ss.SystemSettingsFetch);
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
		click(ss.SystemSettingsFetch);
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
		click(ss.SystemSettingsFetch);
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
		click(ss.SystemSettingsFetch);
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
		click(ss.SystemSettingsFetch);
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
		click(ss.SystemSettingsFetch);
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
		click(ss.SystemSettingsFetch);
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
		click(ss.SystemSettingsFetch);
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
		click(ss.SystemSettingsFetch);
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
		click(ss.SystemSettingsFetch);
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
		click(ss.SystemSettingsFetch);
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
		click(ss.SystemSettingsFetch);
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

		System.out.println("***");

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
	//@Ignore
	@Test(priority = 14, dependsOnMethods = "ERPLoginPage")
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

	//Vendor Page:-
	@SuppressWarnings("unused")
	class Vendor {
		private String vendorName;
		private String currency;
		private String totalPayment;
		private String duePayment;
		private String apAccount;
		private String debitLimit;
		private String debitOnHold;
		private String nonTradeCreditor;
		private String trVendor;
		private String gst;
		private String gstType;

		public Vendor(String vendorName, String currency, String totalPayment, String duePayment, String apAccount, String debitLimit,
				String debitOnHold, String nonTradeCreditor, String trVendor, String gst, String gstType) {
			super();

			this.vendorName = vendorName;
			this.currency = currency;
			this.totalPayment = totalPayment;
			this.duePayment = duePayment;
			this.apAccount = apAccount;
			this.debitLimit = debitLimit;
			this.debitOnHold = debitOnHold;
			this.nonTradeCreditor = nonTradeCreditor;
			this.trVendor = trVendor;
			this.gst = gst;
			this.gstType = gstType;
		}
	}

	ArrayList<Vendor> vendorDetailsList = new ArrayList<>();
	//@Ignore
	@Test(priority = 16, dependsOnMethods = "ERPLoginPage")
	public void VendorPage() throws InterruptedException {

		VendorList.addAll(VendorSet);

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		Vendors vd = new Vendors(driver);

		driver.navigate().to(url + "SalesPurchases/Vendor");
		Thread.sleep(4000);
		System.out.println("*Vendor Page*");

		int vendorListSize = VendorList.size();
		System.out.println("vendorListSize: "+vendorListSize);
		for (int i = 0; i < vendorListSize; i++) {
			String vendor = VendorList.get(i);

			if (vendor != null) {

				WebElement VendorSearchField = driver.findElement(By.id("SearchString"));
				VendorSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				VendorSearchField.sendKeys(vendor);
				Thread.sleep(1000);

				WebElement fetchBtn = driver.findElement(By.id("searchstring"));
				js.executeScript("arguments[0].click();", fetchBtn);
				Thread.sleep(3000);

				WebElement detailIcon = driver.findElement(
						By.xpath("//table[@id='vendortable']//tbody//tr//td[2]//following::td[6]//a[@title='Details']"));
				js.executeScript("arguments[0].click();", detailIcon);

				String vendorName = driver.findElement(By.xpath("(//div[@class='col-lg-8']//child::h2//b)[1]"))
						.getText();
				System.out.println("Vendar Name: "+vendorName);

				String currency = driver.findElement(By.xpath("//dt[normalize-space()='Currency']//following-sibling::dd[1]"))
						.getText().trim();
				System.out.println("Currency: "+currency);

				String totalPayment = driver.findElement(By.xpath("//span[text()=' Total Payment']//following-sibling::b//span[@id='TotalPaymentText']"))
						.getText().trim();
				System.out.println("Total Payment: "+totalPayment);

				String duePayment = driver.findElement(By.xpath("//span[text()=' Due']//following-sibling::b//span[@id='DueAmountText']"))
						.getText().trim();
				System.out.println("Due Payment: "+duePayment);

				String apAccount = driver.findElement(By.xpath("//dt[normalize-space()='A/P Account']//following-sibling::dd[1]"))
						.getText().trim();
				System.out.println("A/P Account: " + apAccount);

				WebElement InfoTab = driver.findElement(By.xpath("//a[text()='Info']"));
				js.executeScript("arguments[0].click();", InfoTab);
				//	InfoTab.click();
				Thread.sleep(2000);

				String debitLimit = driver.findElement(By.xpath("//dt[normalize-space()='Debit Limit']//following-sibling::dd[1]"))
						.getText().trim();
				System.out.println("Debit Limit: " + debitLimit);		

				String debitOnHold = driver.findElement(By.xpath("//dt[normalize-space()='Debit On Hold']//following-sibling::dd[1]"))
						.getText().trim();
				System.out.println("Debit On Hold: " + debitOnHold);		

				String nonTradeCreditor = driver.findElement(By.xpath("//dt[normalize-space()='Non-Trade Creditor']//following-sibling::dd[1]"))
						.getText().trim();
				System.out.println("Non Trade Creditor: " + nonTradeCreditor);		

				String trVendor = driver.findElement(By.xpath("//dt[normalize-space()='TR Vendor']//following-sibling::dd[1]"))
						.getText().trim();
				System.out.println("VR Vendor: " + trVendor);		

				String gst = driver.findElement(By.xpath("//dt[normalize-space()='GST']//following-sibling::dd[1]"))
						.getText().trim();
				System.out.println("GST: " + gst);		

				String gstType = driver.findElement(By.xpath("//dt[normalize-space()='GST Type']//following-sibling::dd[1]"))
						.getText().trim();
				System.out.println("GST Type: " + gstType);							

				System.out.println("***");

				Vendor VendorDetails = new Vendor(vendorName, currency, totalPayment, duePayment, apAccount, 
						debitLimit, debitOnHold, nonTradeCreditor, trVendor, gst, gstType);							
				vendorDetailsList.add(VendorDetails);

				click(vd.Back);
			}
			break;
		}
	}

	String invoiceNo;
	//@Ignore
	@Test(priority = 18, dependsOnMethods = "ERPLoginPage")
	public void PurchaseInvoice() throws InterruptedException, ParseException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		LocalDateTime TimeStamp = LocalDateTime.now();
		DateTimeFormatter DateTimeFormate = DateTimeFormatter.ofPattern("HHmmss");
		String formatedTimestamp = TimeStamp.format(DateTimeFormate);
		invoiceNo = "INV"+formatedTimestamp;

		PurchaseInvoice pi = new PurchaseInvoice(driver);

		driver.navigate().to(url + "Purchases/PurchaseInvoiceIndex");
		Thread.sleep(4000);
		System.out.println("* Purchase Invoice Page *");

		click(pi.AddPurchaseInvoice);
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


		int excelDataListSize = excelDataList.size();
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			if (excelData.Vendor.isEmpty() == false) {

				click(pi.Vendor);
				WebElement vendorSearchField = driver.findElement(By.xpath("//span[@id='select2-VendorId-container']//following::input[@type='search']"));
				vendorSearchField.click();
				vendorSearchField.sendKeys(excelData.Vendor +Keys.ENTER);
				Thread.sleep(2000);

				click(pi.InvoiceNo);
				Sendkeys(pi.InvoiceNo, invoiceNo);

			}

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
					click(pi.ProductCheckBox);

				}

			} else if (excelData.Type.equals("Service")) {

				if (!pi.ServiceCheckBox.isSelected()) {
					click(pi.ServiceCheckBox);
				}

			} else if (excelData.Type.equals("Open")) {

				if (!pi.OpenCheckBox.isSelected()) {
					click(pi.OpenCheckBox);
				}

			}

			if (excelData.Type.equalsIgnoreCase("Product")) {

				click(pi.Chooseproduct);
				driver.findElement(
						By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
				.sendKeys(excelData.ProductCode + Keys.ENTER);

			}
			Thread.sleep(1000);
			if (excelData.Type.equalsIgnoreCase("Product")) {
				click(pi.Qty);
				click(pi.Uom);
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
			Thread.sleep(2000);
			Sendkeys(pi.Qty, excelData.Qty);

			//Price:-
			click(pi.Price);
			pi.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(pi.Price, excelData.Price +Keys.ENTER);
			Thread.sleep(1000);

			//Item Level Discount:-
			if (IsEnableItemLevelDiscountInPurchase == true) {

				click(pi.DiscountAmount);
				Sendkeys(pi.DiscountAmount, Keys.CONTROL + "a" + Keys.DELETE);
				Sendkeys(pi.DiscountAmount, excelData.DiscountAmount);

			} else {

				System.out.println("Unit Discount Fields Not Displayed");
			}

			double Total = 0;
			double excelQtyDouble = Double.parseDouble(excelData.Qty);
			double priceDouble = Double.parseDouble(excelData.Price);
			double expAmount = 0;

			boolean unitDiscCheckboxBoolean = Boolean.parseBoolean(excelData.UnitDiscCheckbox);

			if (unitDiscCheckboxBoolean == true) {

				if (excelData.DiscountPercentage.isEmpty() == false) {
					double discountPercentDouble = Double.parseDouble(excelData.DiscountPercentage);

					double discountPercentPrice = (discountPercentDouble / 100) * priceDouble;
					double discountAmountRound = Math.round(discountPercentPrice);
					double discAmountforPercent = discountAmountRound / 100;

					Total = (priceDouble - discAmountforPercent) * excelQtyDouble;
					System.out.println("Item Level Discount Percentage Total: " + Total);

				} else if (excelData.DiscountAmount.isEmpty() == false) {
					double discAmtDouble = Double.parseDouble(excelData.DiscountAmount);

					Total = (priceDouble - discAmtDouble) * excelQtyDouble;
					System.out.println("Item Level Discount Amount Total: " + Total);
				}

			} else {

				if (excelData.DiscountPercentage.isEmpty() == false) {

					double discPercentDouble = Double.parseDouble(excelData.DiscountPercentage);
					double discountPerAmt = (discPercentDouble / 100) * Total;
					double discPerAmtRound = Math.round(discountPerAmt * 100);
					double discAmtforPer = discPerAmtRound / 100;

					Total = Total - discAmtforPer;
					System.out.println("Discount Percentage Amount Total: "+Total);

				} else if (excelData.DiscountAmount.isEmpty() == false) {

					double discAmtDouble1 = Double.parseDouble(excelData.DiscountAmount);
					double excelPriceDouble = Double.parseDouble(excelData.Price);					
					double discountAmount = (excelPriceDouble * excelQtyDouble);

					expectedDiscountAmount = (discountAmount - discAmtDouble1);

					Total = expectedDiscountAmount - Total;
					System.out.println("Discount Amount Total: "+Total);

				} else {

					expAmount = excelQtyDouble * priceDouble;
				}
			}

			//Add Button:-
			js.executeScript("arguments[0].click();", pi.Add);
			Thread.sleep(2000);
			System.out.println();

			//Batch Details:-
			if (excelData.BatchProduct.equalsIgnoreCase("true")) {

				Thread.sleep(2000);
				String totalQty = driver.findElement(By.xpath
						("(//div//strong[contains(normalize-space(),'"+excelData.ProductName.trim()+"')]//following::input[@id='TotalQty'])[1]"))
						.getAttribute("value");
				System.out.println("Total Qty: "+totalQty);

				Thread.sleep(1000);
				WebElement batchNo = driver.findElement(By.xpath
						("(//div//strong[contains(text(),'"+excelData.ProductName.trim()+"')]//following::input[@id='BatchNumber'])[1]"));
				batchNo.click();
				batchNo.sendKeys(excelData.BatchNumber +Keys.ENTER);
				Thread.sleep(1000);

				String inputDate = excelData.MfgDate; 
				SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yy");
				SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date date = inputFormat.parse(inputDate);
				String formattedDate = outputFormat.format(date);

				WebElement mfgDate = driver.findElement(By.xpath
						("(//div//strong[contains(text(),'"+excelData.ProductName.trim()+"')]//following::input[@class='ManufactureDate datepick form-control hasDatepicker'])[1]"));
				mfgDate.click();
				mfgDate.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				mfgDate.sendKeys(formattedDate +Keys.ENTER);
				Thread.sleep(1000);

				WebElement valPeriod = driver.findElement(By.xpath
						("(//div//strong[contains(text(),'"+excelData.ProductName.trim()+"')]//following::input[@id='ValidPeriod'])[1]"));
				valPeriod.click();
				valPeriod.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				valPeriod.sendKeys(excelData.ValPeriod +Keys.ENTER);
				Thread.sleep(1000); 

				WebElement totalQtyField = driver.findElement(By.xpath
						("(//div//strong[contains(text(),'"+excelData.ProductName.trim()+"')]//following::input[@id='Qty'])[1]"));
				totalQtyField.click();
				totalQtyField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				totalQtyField.sendKeys(totalQty);
				Thread.sleep(1000);

				WebElement batchAdd = driver.findElement(By.xpath
						("(//div//strong[contains(text(),'"+excelData.ProductName.trim()+"')]//following::button[text()='Add'])[1]"));
				js.executeScript("arguments[0].click();", batchAdd);
				Thread.sleep(2000);

			}

			productPrice = driver.findElement(By.xpath
					("//table[@id='PurchaseTable']//tbody//tr//td//child::div//textarea[contains(text(),'"+excelData.ProductName+"')]//following::td//child::p[@id='DetailPurchaseDetailTotal']"))
					.getText();
			String replaceAllProductPrice = productPrice.replaceAll(",", "");
			double productPriceDouble = Double.parseDouble(replaceAllProductPrice);
			System.out.println("Actual Discount Amount: "+productPriceDouble);

			if (IsEnableItemLevelDiscountInPurchase == true) {

				System.out.println("Expected Discount Amount: "+Total);
				System.out.println();	
				soft.assertEquals(productPriceDouble, Total,
						"Actual and Expected Discount Amount Mismatched for "+excelData.ProductName);

			} else {

				System.out.println("Expected Discount Amount: "+expAmount);
				System.out.println();
				soft.assertEquals(productPriceDouble, expAmount,
						"Actual and Expected Discount Amount Mismatched for "+excelData.ProductName);

			}

			ExpSubTotal = ExpSubTotal + productPriceDouble;

			if (excelData.ZeroGst.equals("TRUE")) {
				ExpZeroGstProductamount = Double.parseDouble(replaceAllProductPrice) + ExpZeroGstProductamount;
				System.out.println("ExpZeroGstProductamount: "+ExpZeroGstProductamount);
			}

			Actions action = new Actions(driver);
			action.doubleClick(pi.Qty).perform();
			Thread.sleep(1000);

		} //ExcelDataList Loop

		//Over All Discount:-
		click(pi.OverAllDiscountType);
		Select overAllDiscTypeSelect = new Select(pi.OverAllDiscountType);
		overAllDiscTypeSelect.selectByVisibleText(getexcelOverAllDiscountType);

		WebElement overAllDiscountType = driver.findElement(By.id("DiscountType"));
		Select overAllDiscountTypeSelect = new Select(overAllDiscountType);
		String getOverAllDiscountType = overAllDiscountTypeSelect.getFirstSelectedOption().getText();
		System.out.println("Over all Discount Type is: " + getOverAllDiscountType);

		Thread.sleep(1000);
		click(pi.OverAllDiscount);
		if (getOverAllDiscountType.equals("$")) {
			pi.OverAllDiscount.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(pi.OverAllDiscount, getExcelOverAllDiscountAmount + Keys.ENTER);
			Thread.sleep(1000);
			click(pi.Qty);

		} else if (getOverAllDiscountType.equals("%")) {
			pi.OverAllDiscount.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(pi.OverAllDiscount, getExcelOverAllDiscountPercentage + Keys.ENTER);
			Thread.sleep(1000);
			click(pi.Qty);

		} else {
			System.out.println("No Over All Discount Amount and Percentage");
		}
		System.out.println(); 

		// Sub Total Calculation:-
		System.out.println("*** Sub Total Calculation ***");
		String subTotalAmountString = driver.findElement(By.xpath
				("//table[@id='PurchaseTable']//tfoot//tr//td//p[@id='FooterSubTotal']")).getText();
		String replaceAllSubTotalAmountString = subTotalAmountString.replaceAll(",", "");
		double subTotalAmountDouble = Double.parseDouble(replaceAllSubTotalAmountString);
		System.out.println("Actual Sub Total Amount: " + subTotalAmountDouble);
		System.out.println("Expected Sub Total Amount: " + ExpSubTotal);
		System.out.println();

		soft.assertEquals(subTotalAmountDouble, ExpSubTotal, "Actual and Expected SubTotal Mismatched");

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

		double subWithoutGstAmount = discountTotalAmount - finalWithoutGstAmount;
		String formatSubWithoutGstAmount = String.format("%.2f", subWithoutGstAmount);
		System.out.println("With Gst Product SubTotal: "+formatSubWithoutGstAmount);
		System.out.println();

		// GST CALCULATION
		System.out.println("*** Grand Total Calculation With GST ***");

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

		String ActualGstAmount = driver.findElement(By.xpath("//table[@id='PurchaseTable']//tfoot//tr//td//p[@id='FooterGST']")).getText();
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

		String finalTotalAmount = driver.findElement(By.xpath("//table[@id='PurchaseTable']//tfoot//tr//td//p[@id='FooterTotal']")).getText();
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

		String currencyName = pi.CurrencyCode.getText().trim();
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

		Thread.sleep(4000);
		click(pi.Save);
		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("Alert Text: "+alertText);

		} catch (Exception e) {
			System.out.println("No alert appeared after save.");
		}
		System.out.println("** Purchase Invoice Save Successfull **");
		System.out.println();

		String invoiceStatus = driver.findElement(By.xpath
				("//table[@id='purchasetable']//tbody//tr//td[normalize-space()='"+invoiceNo+"']//preceding-sibling::td[1]//span"))
				.getText();
		System.out.println("Invoice Status: "+invoiceStatus);


		Thread.sleep(2000);
		WebElement checkBox = driver.findElement(By.xpath
				("//table[@id='purchasetable']//tbody//tr//td[normalize-space()='"+invoiceNo+"']//preceding-sibling::td//input[@id='check']"));
		checkBox.click();

		click(pi.Post);
		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("Alert Text: "+alertText);

		} catch (Exception e) {
			System.out.println("No alert appeared after Post.");

		}
		click(pi.PopupOk);
		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("Alert Text: "+alertText);

		} catch (Exception e) {
			System.out.println("No alert appeared after Post.");

		}
		click(pi.PopupOk);
		System.out.println("** Purchase Invoice Post Successfull **");
		System.out.println();
		String invoiceStatus1 = driver.findElement(By.xpath
				("//table[@id='purchasetable']//tbody//tr//td[normalize-space()='"+invoiceNo+"']//preceding-sibling::td[1]//span"))
				.getText();
		System.out.println("Invoice Status1: "+invoiceStatus1);
		System.out.println();
	}

	//@Ignore
	@Test(priority = 20, dependsOnMethods = "ERPLoginPage")
	public void VendorPayment() throws InterruptedException {

		driver.navigate().to(url +"SalesPurchases/VendorPayments/IndexBuilder_VP");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("** Vendor Payment Page **");

		VendorPaymentV2 vp = new VendorPaymentV2(driver);

		click(vp.CreateNew);

		int excelDataListSize = excelDataList.size();
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			click(vp.Vendor);
			driver.findElement(By.xpath("//span[@id='select2-VendorId-container']//following::input[@type='search']"))
			.sendKeys(excelData.Vendor +Keys.ENTER);			
			try {

				String alertText = driver.findElement(By.id("popup_message")).getText();
				System.out.println("alertText: "+alertText);

			} catch (Exception e) {

				System.out.println("No Alert Appear in Vendor");
			}

			int vendorPaymentTableSize = driver.findElements(By.xpath
					("//table[@id='vendorpaymenttable']//tbody//tr")).size();
			System.out.println("vendorPaymentTableSize: "+vendorPaymentTableSize);

			if (vendorPaymentTableSize > 1) {

				WebElement checkBox = driver.findElement(By.xpath
						("//table[@id='vendorpaymenttable']//tbody//tr//td[contains(text(),'"+invoiceNo+"')]//preceding-sibling::td//input[@id='check']"));
				checkBox.click();

			} else {

				System.out.println("No payment data in the table");
			}

			String amount = driver.findElement(By.xpath
					("//table[@id='vendorpaymenttable']//tbody//tr//td[contains(text(),'"+invoiceNo+"')]//following-sibling::td[1]"))
					.getText();
			System.out.println("Amount: "+amount);

			String openingBalance = driver.findElement(By.xpath("//table[@id='vendorpaymenttable']//tbody//tr//td[contains(text(),'"+invoiceNo+"')]//following-sibling::td[@id='FCOpnBal']"))
					.getText();
			System.out.println("Opening Balance: "+openingBalance);

			String payment = driver.findElement(By.xpath("//table[@id='vendorpaymenttable']//tbody//tr//td[contains(text(),'"+invoiceNo+"')]//following-sibling::td//input[@id='Payment']"))
					.getAttribute("Value");
			System.out.println("Payment: "+payment);

			String balanceAmount = driver.findElement(By.xpath("//table[@id='vendorpaymenttable']//tbody//tr//td[contains(text(),'"+invoiceNo+"')]//following-sibling::td//label[@id='FCBalanceAmount']"))
					.getText();
			System.out.println("Balance Amount: "+balanceAmount);

			String amountPaid = driver.findElement(By.id("AmountRecieved")).getAttribute("value");
			System.out.println("Amount Paid: "+amountPaid);

			soft.assertEquals(payment, amountPaid, "Payment and Amount Paid Mismatched");
			break;
		}

		click(vp.Save);
		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("alertText: "+alertText);

		} catch (Exception e) {

			System.out.println("No Alert Appear in Vendor");
		}
		click(vp.PopupOk);
		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("alertText: "+alertText);

		} catch (Exception e) {

			System.out.println("No Alert Appear in Vendor");
		}
		click(vp.PopupOk);
		System.out.println("** Vendor Payment Save Successfull **");
		System.out.println();
		Thread.sleep(2000);
		click(vp.Back);

	}
	//@Ignore
	@Test(priority = 22, dependsOnMethods = "ERPLoginPage")
	public void Vendor() throws InterruptedException {

		driver.navigate().to(url +"SalesPurchases/Vendor");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("** Vendor Page **");
		Vendors vd = new Vendors(driver);
		DecimalFormat df = new DecimalFormat("#,##0.00");

		double expNetTotal = 0;
		double expFCTotal = 0;
		String formatNetTotal = "";
		String formatFcTotal = "0.00";

		int excelDataListSize = excelDataList.size();
		System.out.println("excelDataListSize: "+excelDataListSize);
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			if (i == 0) {

				WebElement details = driver.findElement(By.xpath
						("//table[@id='vendortable']//tbody//tr//td[normalize-space()='"+excelData.Vendor+"']//following-sibling::td//a[@title='Details']"));
				details.click();

				Thread.sleep(2000);
				WebElement purchaseInvoiceTab = driver.findElement(By.xpath
						("//ul[@id='myTab']//li//a[@class='PurchaseInvoiceTab']"));
				purchaseInvoiceTab.click();

				Thread.sleep(2000);
				click(vd.FromDate);
				Sendkeys(vd.FromDate, Keys.CONTROL +"a"+ Keys.DELETE);
				Thread.sleep(1000);
				click(vd.ToDate);
				Sendkeys(vd.ToDate, Keys.CONTROL +"a"+ Keys.DELETE);
				Thread.sleep(1000);
				click(vd.DetailsInvoiceFetch);

			}			
			break;
		}

		Thread.sleep(2000);
		int tableSize = driver.findElements(By.xpath
				("//table[@class='table table-bordered table-striped']//tbody//tr")).size();
		System.out.println("Table Size: "+tableSize);
		Thread.sleep(2000);
		for (int j = 1; j < tableSize; j++) {

			String invoiceStatus = driver.findElement(By.xpath
					("(//table[@class='table table-bordered table-striped']//tbody//tr//td[@id='piNetTotal']//preceding::td[1])["+j+"]"))
					.getText().trim();
			System.out.println("Invoice Status: "+invoiceStatus);

			if (invoiceStatus.equalsIgnoreCase("Posted") || invoiceStatus.equalsIgnoreCase("Paid")) {

				String netTotal = driver.findElement(By.xpath
						("//table[@class='table table-bordered table-striped']//tbody//tr["+(j+1)+"]//td[contains(text(),'Posted') or contains(text(),'Paid')]//following-sibling::td[@id='piNetTotal']"))
						.getText();
				System.out.println("Net Total: "+netTotal);

				String replaceNetTotal = netTotal.replaceAll("\\$", "").trim();	
				String replaceAllNetTotal = replaceNetTotal.replaceAll(",", "");
				double netTotalDouble = Double.parseDouble(replaceAllNetTotal);
				expNetTotal = expNetTotal + netTotalDouble;	
				formatNetTotal = df.format(expNetTotal);

			} else {

				System.out.println("Invoice Status New");
			}
		}

		System.out.println();
		int unpaidStatusSize = driver.findElements(By.xpath
				("//table[@class='table table-bordered table-striped']//tbody//tr//td//span[text()='UnPaid']")).size();
		System.out.println("Unpaid Status Size: "+unpaidStatusSize);
		for (int k = 1; k <= unpaidStatusSize; k++) {

			String invoiceStatus = driver.findElement(By.xpath
					("(//table[@class='table table-bordered table-striped']//tbody//tr//td[@id='piNetTotal']//preceding::td[1])["+k+"]"))
					.getText().trim();
			System.out.println("Invoice Status: "+invoiceStatus);

			if (invoiceStatus.equalsIgnoreCase("Posted")) {

				String fcTotal = driver.findElement(By.xpath
						("(//table[@class='table table-bordered table-striped']//tbody//tr//td//span[text()='UnPaid']//following::td[@id='piNetTotal']//following-sibling::td)["+k+"]"))
						.getText();
				System.out.println("FC Total: "+fcTotal);

				String replaceFcTotal = fcTotal.replaceAll("\\$", "").trim();
				String replaceAllFcTotal = replaceFcTotal.replaceAll(",", "");
				double expFCTotalDouble = Double.parseDouble(replaceAllFcTotal);
				expFCTotal = expFCTotal + expFCTotalDouble;	
				formatFcTotal = df.format(expFCTotal);
			} else {

				System.out.println("Invoice Status New");
			}

		}
		System.out.println();

		String actTotalPayment = driver.findElement(By.xpath
				("//span[text()=' Total Payment']//following::span[@id='TotalPaymentText']")).getText();
		String replaceTotalPayment= actTotalPayment.replaceAll("\\$", "");
		System.out.println("Actual Total Payment: "+replaceTotalPayment);
		System.out.println("Expected Total Payment: "+formatNetTotal);
		
		soft.assertEquals(replaceTotalPayment, formatNetTotal, "Actual and Expected Total Paymeny Mismatched");
		System.out.println();

		String actDue = driver.findElement(By.xpath
				("//span[text()=' Due']//following::span[@id='DueAmountText']")).getText();
		String replaceActDue = actDue.replaceAll("\\$", "");
		System.out.println("Actual Due: "+replaceActDue);
		System.out.println("Expected Due: "+formatFcTotal);
		
		soft.assertEquals(replaceActDue, formatFcTotal, "Actual and Expected Due Mismatched");
		System.out.println();

	}

	String replaceVendorPaymentNo = "0.0";
	//@Ignore
	@Test(priority = 24, dependsOnMethods = "ERPLoginPage")
	public void MultipleInvoiceVendorPayment() throws InterruptedException {

		driver.navigate().to(url +"SalesPurchases/VendorPayments/IndexBuilder_VP");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("** Vendor Payment page For Multiple Invoice **");

		VendorPaymentV2 vp = new VendorPaymentV2(driver);
		DecimalFormat df = new DecimalFormat("#,##0.00");

		double expPayment = 0;
		String formatExpPayment = "null";

		click(vp.CreateNew);

		int excelDataListSize = excelDataList.size();
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			if (i == 0) {	

				click(vp.Vendor);
				driver.findElement(By.xpath("//span[@id='select2-VendorId-container']//following::input[@type='search']"))
				.sendKeys(excelData.Vendor +Keys.ENTER);			
				try {

					String alertText = driver.findElement(By.id("popup_message")).getText();
					System.out.println("alertText: "+alertText);

				} catch (Exception e) {

					System.out.println("No Alert Appear in Vendor");
				}

				click(vp.CheckBoxAll);
			}
		}
		Thread.sleep(2000);
		int vendorPaymentTableSize = driver.findElements(By.xpath
				("//table[@id='vendorpaymenttable']//tbody//tr")).size();
		System.out.println("vendorPaymentTableSize: "+vendorPaymentTableSize);

		for (int j = 1; j < vendorPaymentTableSize; j++) {			

			String amount = driver.findElement(By.xpath
					("(//table[@id='vendorpaymenttable']//tbody//tr//td[@id='FCOpnBal']//preceding::td[1])["+j+"]"))
					.getText();
			System.out.println("Amount: "+amount);

			String openingBalance = driver.findElement(By.xpath("(//table[@id='vendorpaymenttable']//tbody//tr//td[@id='FCOpnBal'])["+j+"]"))
					.getText();
			System.out.println("Opening Balance: "+openingBalance);

			Thread.sleep(2000);
			String payment = driver.findElement(By.xpath("(//table[@id='vendorpaymenttable']//tbody//tr//td//input[@id='Payment'])["+j+"]"))
					.getAttribute("value");
			System.out.println("Payment: "+payment);
			String replaceAllPayment = payment.replaceAll(",", "");
			double paymentDouble = Double.parseDouble(replaceAllPayment);
			expPayment = expPayment + paymentDouble;
			formatExpPayment = df.format(expPayment);

			String balanceAmount = driver.findElement(By.xpath("(//table[@id='vendorpaymenttable']//tbody//tr//td//label[@id='FCBalanceAmount'])["+j+"]"))
					.getText();
			System.out.println("Balance Amount: "+balanceAmount);

		}

		String totalAmountToPay = driver.findElement(By.xpath
				("//table[@id='vendorpaymenttable']//tbody//tr//td[@id='amounttopay']")).getText();
		System.out.println("Actual Total Amount To Pay: "+totalAmountToPay);
		System.out.println("Expected Total Amount To Pay: "+formatExpPayment);
		
		soft.assertEquals(totalAmountToPay, formatExpPayment, "Actual and Expected Total Amount to Pay Mismatched");
		System.out.println();
		
		String amountPaid = driver.findElement(By.id("AmountRecieved")).getAttribute("value");
		System.out.println("Actual Amount Paid: "+amountPaid);
		System.out.println("Expected Amount Paid: "+formatExpPayment);
		
		soft.assertEquals(amountPaid, formatExpPayment, "Actual and Expected Amount Paid Mismatched");
		System.out.println();
		
		Thread.sleep(2000);
		click(vp.Save);
		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("alertText: "+alertText);

		} catch (Exception e) {

			System.out.println("No Alert Appear in Vendor");
		}
		click(vp.PopupOk);
		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("alertText: "+alertText);

		} catch (Exception e) {

			System.out.println("No Alert Appear in Vendor");
		}
		click(vp.PopupOk);
		click(vp.PopupOk);

		String vendorPaymentNo = driver.findElement(By.xpath
				("//div[@class='alert alert-success alert-dismissable']//child::b")).getText();
		replaceVendorPaymentNo = vendorPaymentNo.replaceAll("[^0-9]", "");
		System.out.println("Vendor Payment No: "+replaceVendorPaymentNo);
		System.out.println();
		System.out.println("** Multiple Invoice Vendor Payment Save Successfull **");
		System.out.println();
		Thread.sleep(3000);
		click(vp.Back);

	}

	//@Ignore
	@Test(priority = 26, dependsOnMethods = "ERPLoginPage")
	public void Vendor1() throws InterruptedException {

		Vendor();

	}

	String splitReverseInvoiceNo;

	//@Ignore
	@Test(priority = 28, dependsOnMethods = "ERPLoginPage")
	public void VendorPaymentReverse() throws InterruptedException {

		driver.navigate().to(url +"SalesPurchases/VendorPayments/IndexBuilder_VP");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("** Vendor Payment page For Reverse **");

		VendorPaymentV2 vp = new VendorPaymentV2(driver);

		click(vp.VendorPaymentNo);
		Sendkeys(vp.VendorPaymentNo, replaceVendorPaymentNo);

		Thread.sleep(2000);
		click(vp.Fetch);

		Thread.sleep(2000);
		WebElement options = driver.findElement(By.xpath
				("//table[@id='acs-index-table']//tbody//tr//td[text()='"+replaceVendorPaymentNo+"']//following-sibling::td//a[@title='Option']"));
		options.click();

		WebElement details = driver.findElement(By.xpath
				("//div[@id='menu']//child::a//i[@class='fa fa-file-text-o']"));
		details.click();

		String reverseInvoiceNo = driver.findElement(By.xpath
				("(//table[@id='vendorpaymenttable']//tbody//tr//td[3])[1]")).getText();
		splitReverseInvoiceNo = reverseInvoiceNo.split(" ")[0].trim();
		System.out.println("Reverse Invoice No: " + splitReverseInvoiceNo);

		Thread.sleep(1000);
		click(vp.Back);

		Thread.sleep(3000);
		click(vp.VendorPaymentNo);
		Sendkeys(vp.VendorPaymentNo, replaceVendorPaymentNo);

		Thread.sleep(2000);
		click(vp.Fetch);

		Thread.sleep(2000);
		WebElement options1 = driver.findElement(By.xpath
				("//table[@id='acs-index-table']//tbody//tr//td[text()='"+replaceVendorPaymentNo+"']//following-sibling::td//a[@title='Option']"));
		options1.click();

		WebElement reverse = driver.findElement(By.xpath
				("//div[@id='menu']//child::a//i[@class='fa fa-square-o']"));
		reverse.click();
		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("alertText: "+alertText);

		} catch (Exception e) {

			System.out.println("No Alert Appear in Vendor");
		}
		click(vp.PopupOk);
		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("alertText: "+alertText);

		} catch (Exception e) {

			System.out.println("No Alert Appear in Vendor");
		}
		click(vp.PopupOk);

	}

	@Test(priority = 30, dependsOnMethods = "ERPLoginPage")
	public void AccountTransaction() throws InterruptedException {

		driver.navigate().to(url +"Accounts/AccountTransactions/IndexBuilder");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("** Account Transaction Page **");

		AccountTransaction at = new AccountTransaction(driver);

		click(at.InvoiceNo);
		Sendkeys(at.InvoiceNo, splitReverseInvoiceNo);

		Thread.sleep(1000);
		click(at.Fetch);

		WebElement option = driver.findElement(By.xpath
				("//table[@id='acs-index-table']//tbody//tr//td//a[@title='Option']"));
		option.click();
		WebElement edit = driver.findElement(By.xpath("//div[@id='menu']//child::a"));
		edit.click();

		String purchaseProductDebit = driver.findElement(By.xpath
				("(//table[@id='example1']//tbody//tr//td//span[text()=' 51002-Purchases-Products']//following::td//input[@id='Debit'])[1]"))
				.getAttribute("value");
		System.out.println("Purchase Product Debit: "+purchaseProductDebit);

		String gstInputTaxDebit = driver.findElement(By.xpath
				("(//table[@id='example1']//tbody//tr//td//span[text()=' 23002-GST Input Tax']//following::td//input[@id='Debit'])[1]"))
				.getAttribute("value");
		System.out.println("Gst Input Tax Debit: "+gstInputTaxDebit);

		String tradeCreditorsCredit = driver.findElement(By.xpath
				("(//table[@id='example1']//tbody//tr//td//span[text()=' 21001-Trade Creditors']//following::td//input[@id='Credit'])[1]"))
				.getAttribute("value");
		System.out.println("Trade Creditors Credit: "+tradeCreditorsCredit);

		String debitTotal = driver.findElement(By.xpath
				("//table[@id='example1']//tfoot//tr//td[@id='DebitFooter']")).getText();
		System.out.println("Debit Total: "+debitTotal);

		String creditTotal = driver.findElement
				(By.xpath("//table[@id='example1']//tfoot//tr//td[@id='CreditFooter']")).getText();
		System.out.println("Credit Total: "+creditTotal);

	}


	@Test(priority = 45, dependsOnMethods = "ERPLoginPage")
	private void Exception() throws InterruptedException {
		soft.assertAll();

	}

	@Ignore
	@Test(priority = 46, dependsOnMethods = "ERPLoginPage")
	private void quit() throws InterruptedException {
		driver.quit();

	}



}
