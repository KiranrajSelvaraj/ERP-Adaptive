package com.Sales;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import com.PomClass.CreditNotes;
import com.PomClass.Login;
import com.PomClass.Product;
import com.PomClass.SalesInvoice;
import com.PomClass.SalesReturn;
import com.PomClass.SystemSettings;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreditNotesTest extends BaseClass{

	private String url;

	SoftAssert soft = new SoftAssert();

	@Test(priority = 1)
	public void ERPLoginPage() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://erp.dev1.adaptivegroups.asia/ERP/Account/Login");
		url = "https://erp.dev1.adaptivegroups.asia/ERP/";

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

		Object data[][] = Util1.getTestData("C:\\Adaptive\\ERP\\CreditNotes.xlsx", "Sheet1");
		return data;

	}


	@SuppressWarnings("unused")
	class ExcelData {
		private String Customer;
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

		public ExcelData(String Customer, String CurrencyCode, String CurrencyRate, String GstType, String Type,
				String ProductCode, String ProductName, String Uom, String Qty, String Foc, String DiscountPercentage,
				String DiscountAmount, String UnitDiscCheckbox, String UnitDiscPercentage, String UnitDiscAmount,
				String Price, String IsSpecialPriceCheckbox, String SpecialPrice, String OverAllDiscountType,
				String OverAllDiscountAmount, String OverAllDiscountPercentage, String GstPercentage, String ZeroGst,
				String BatchProduct, String CreditNotesQty) {
			super();

			this.Customer = Customer;
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
	public void GetData(String Customer, String CurrencyCode, String CurrencyRate, String GstType, String Type,
			String ProductCode, String ProductName, String Uom, String Qty, String Foc, String DiscountPercentage,
			String DiscountAmount, String UnitDiscCheckbox, String UnitDiscPercentage, String UnitDiscAmount,
			String Price, String IsSpecialPriceCheckbox, String SpecialPrice, String OverAllDiscountType,
			String OverAllDiscountAmount, String OverAllDiscountPercentage, String GstPercentage, String ZeroGst,
			String BatchProduct, String CreditNotesQty) {

		ExcelData data = new ExcelData(Customer, CurrencyCode, CurrencyRate, GstType, Type, ProductCode, ProductName,
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
	private boolean IsFOCManagementInCN;
	private String BulkQtyMeasurement;
	private String LooseQtyMeasurement;
	private boolean IsAllowToEditSpecialPrice;
	private float DecimalCalculationForSales;
	private boolean IsOpenItemManagementInsales;
	private boolean IsHeaderManagementInSO;
	private boolean IsReturnManagementInSI;

	@Ignore
	@Test(priority = 8, dependsOnMethods = "ERPLoginPage")
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
		//	click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsSalesManManagementString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsSalesManManagement = Boolean.parseBoolean(IsSalesManManagementString);
		System.out.println("IsSalesManManagement :" + IsSalesManManagement);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsWarehouseManagement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsWarehouseManagementString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsWarehouseManagement = Boolean.parseBoolean(IsWarehouseManagementString);
		System.out.println("IsWarehouseManagement :" + IsWarehouseManagement);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsWarehouseStorageManagement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsWarehouseStorageManagementString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsWarehouseStorageManagement = Boolean.parseBoolean(IsWarehouseStorageManagementString);
		System.out.println("IsWarehouseStorageManagement :" + IsWarehouseStorageManagement);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsBarcodeEnabled");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsBarcodeEnabledString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsBarcodeEnabled = Boolean.parseBoolean(IsBarcodeEnabledString);
		System.out.println("IsBarcodeEnabled :" + IsBarcodeEnabled);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsBarcodeManagementInsales");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsBarcodeManagementInsalesString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsBarcodeManagementInsales = Boolean.parseBoolean(IsBarcodeManagementInsalesString);
		System.out.println("IsBarcodeManagementInsales :" + IsBarcodeManagementInsales);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsMultiWordSearchInProduct");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsMultiWordSearchInProductString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsMultiWordSearchInProduct = Boolean.parseBoolean(IsMultiWordSearchInProductString);
		System.out.println("IsMultiWordSearchInProduct :" + IsMultiWordSearchInProduct);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsDuplicateProductsInInvoice");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsDuplicateProductsInInvoiceString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsDuplicateProductsInInvoice = Boolean.parseBoolean(IsDuplicateProductsInInvoiceString);
		System.out.println("IsDuplicateProductsInInvoice :" + IsDuplicateProductsInInvoice);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsCartonManagement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsCartonManagementString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsCartonManagement = Boolean.parseBoolean(IsCartonManagementString);
		System.out.println("IsCartonManagement :" + IsCartonManagement);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsEnableItemLevelDiscountInSales");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsEnableItemLevelDiscountInSalesString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsEnableItemLevelDiscountInSales = Boolean.parseBoolean(IsEnableItemLevelDiscountInSalesString);
		System.out.println("IsEnableItemLevelDiscountInSales :" + IsEnableItemLevelDiscountInSales);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsFOCManagementInCN");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsFOCManagementInCNString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsFOCManagementInCN = Boolean.parseBoolean(IsFOCManagementInCNString);
		System.out.println("IsFOCManagementInCN :" + IsFOCManagementInCN);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "BulkQtyMeasurement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		Thread.sleep(1000);
		BulkQtyMeasurement = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		System.out.println("BulkQtyMeasurement :" + BulkQtyMeasurement);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "LooseQtyMeasurement");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		Thread.sleep(1000);
		LooseQtyMeasurement = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		System.out.println("LooseQtyMeasurement :" + LooseQtyMeasurement);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsAllowToEditSpecialPrice");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsAllowToEditSpecialPriceString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsAllowToEditSpecialPrice = Boolean.parseBoolean(IsAllowToEditSpecialPriceString);
		System.out.println("IsAllowToEditSpecialPrice :" + IsAllowToEditSpecialPrice);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "DecimalCalculationForSales");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		String DecimalCalculationForSalesString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		DecimalCalculationForSales = Float.parseFloat(DecimalCalculationForSalesString);
		System.out.println("DecimalCalculationForSales :" + DecimalCalculationForSales);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsOpenItemManagementInsales");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		String IsOpenItemManagementInsalesString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsOpenItemManagementInsales = Boolean.parseBoolean(IsOpenItemManagementInsalesString);
		System.out.println("IsOpenItemManagementInsales :" + IsOpenItemManagementInsales);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsHeaderManagementInSO");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		String IsHeaderManagementInSOString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsHeaderManagementInSO = Boolean.parseBoolean(IsHeaderManagementInSOString);
		System.out.println("IsHeaderManagementInSO :" + IsHeaderManagementInSO);
		//	click(ss.Back);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.Clear);
		js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsReturnManagementInSI");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		//	click(ss.EditSystemSetting);
		String IsReturnManagementInSIString = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]"))
				.getText();
		IsReturnManagementInSI = Boolean.parseBoolean(IsReturnManagementInSIString);
		System.out.println("IsReturnManagementInSI :" + IsReturnManagementInSI);
		//	click(ss.Back);

		System.out.println();
	}

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

	// Product Page:-
	@Ignore
	@Test(priority = 10, dependsOnMethods = "ERPLoginPage")
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

	// UOM PAGE
	@Ignore
	@Test(priority = 12, dependsOnMethods = "ERPLoginPage")
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
	}

	@Ignore
	@Test(priority = 14, dependsOnMethods = "ERPLoginPage")
	public void DirecteCreditNote() throws InterruptedException, IOException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);

		CreditNotes cn = new CreditNotes(driver);

		driver.navigate().to(url + "Sales/CreditNoteIndex");
		Thread.sleep(5000);		
		System.out.println("*** Credit Notes Page ***");

		click(cn.AddCreditNote);
		Thread.sleep(3000);

		String getExcelGstType = "";
		String getexcelOverAllDiscountType = "";
		String getExcelOverAllDiscountPercentage = "";
		String getExcelOverAllDiscountAmount = "";
		String getExcelGstPercentage = "";
		String getExcelCurrencyRate = "";
		String getProductAmount = "";
		//	String ExpDiscountProductPriceFormat = "";

		double ExpSubTotal = 0;
		double ExpZeroGstProductamount = 0;
		double expectedDiscountAmount = 0;

		int excelDataListSize = excelDataList.size();
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			if (excelData.Customer.isEmpty() == false) {

				Thread.sleep(2000);
				click(cn.Customer);
				WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search'][1]")));
				searchInput.sendKeys(excelData.Customer + Keys.ENTER);
				Thread.sleep(2000);

			}	

			click(cn.GstType);
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

			// Choose Product:-
			if (excelData.Type.equalsIgnoreCase("Product")) {

				String productCheckbox = driver.findElement(By.id("ProductCheck")).getAttribute("checked");
				System.out.println("Product Check Box is: " + productCheckbox);
				if (!productCheckbox.equalsIgnoreCase("true")) {
					click(cn.ProductCheckbox);

				}

			} else if (excelData.Type.equals("Service")) {

				if (!cn.ServiceCheckbox.isSelected()) {
					click(cn.ServiceCheckbox);
				}

			} else if (excelData.Type.equals("Open")) {

				if (!cn.OpenCheckbox.isSelected()) {
					click(cn.OpenCheckbox);
				}
			}


			if (excelData.Type.equalsIgnoreCase("Product")) {

				click(cn.ChooseProduct);
				driver.findElement(
						By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
				.sendKeys(excelData.ProductName + Keys.ENTER);

			} else if (excelData.Type.equalsIgnoreCase("Service")) {

				driver.findElement(
						By.xpath("//span[@id='select2-ServiceId-container']//following::input[@type='search']"))
				.sendKeys(excelData.ProductName + Keys.ENTER);

			} else if (excelData.Type.equalsIgnoreCase("Open")) {

				cn.OpenProduct.sendKeys(excelData.ProductName + Keys.ENTER);

			}
			click(cn.Qty);

			if (excelData.Type.equalsIgnoreCase("Product")) {

				click(cn.ChooseUom);
				List<WebElement> subUomOption = driver.findElements(By.xpath(
						"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
				for (WebElement option : subUomOption) {
					if (option.getText().trim().equals(excelData.Uom)) {
						option.click();
						break;
					}

				}

			} else if (excelData.Type.equalsIgnoreCase("Service")) {

				click(cn.ChooseUom);
				List<WebElement> subUomOption = driver.findElements(By.xpath(
						"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
				for (WebElement option : subUomOption) {
					if (option.getText().trim().equals(excelData.Uom)) {
						option.click();
						break;
					}

				}

			} else if (excelData.Type.equalsIgnoreCase("Open")) {

				click(cn.ChooseUom);
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

						if (excelData.Uom.equalsIgnoreCase("1KG")) {

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
			Sendkeys(cn.Qty, excelData.Qty);

			// Foc:-
			if (IsFOCManagementInCN == true) {

				if (excelData.Type.equalsIgnoreCase("Product")) {

					click(cn.Foc);
					Sendkeys(cn.Foc, excelData.Foc);

				}  else if (excelData.Type.equalsIgnoreCase("Service")) {

					click(cn.Foc);
					Sendkeys(cn.Foc, excelData.Foc);

				}

			} else {

				System.out.println("Foc Field Is Not Displayed");
			}

			//Price:-
			click(cn.Price);
			cn.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(cn.Price, excelData.Price);

			//Item Level Discount:-
			if (IsEnableItemLevelDiscountInSales == true) {

				click(cn.DiscountAmount);
				Sendkeys(cn.DiscountAmount, Keys.CONTROL + "a" + Keys.DELETE);
				Sendkeys(cn.DiscountAmount, excelData.DiscountAmount);

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
			System.out.println();

			//Add:-
			Thread.sleep(1000);
			click(cn.Add);
			Thread.sleep(2000);

			//Batch Details:-
			if (excelData.BatchProduct.equalsIgnoreCase("true")) {

				String bQty = driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::input[@id='BQty']")).getAttribute("value");
				System.out.println("B.Qty is: "+bQty);

				String lQty = driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::input[@id='LQty']")).getAttribute("value");
				System.out.println("L.Qty is: "+lQty);

				WebElement bulkQty = driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::input[@id='BulkQty']"));
				bulkQty.click();
				bulkQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				bulkQty.sendKeys(bQty);

				WebElement looseQty = driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::input[@id='LooseQty']"));
				looseQty.click();
				looseQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				looseQty.sendKeys(lQty);

				Thread.sleep(2000);
				driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::button[text()='Add']")).click();

			}

			getProductAmount = driver.findElement(By.xpath("//table[@id='CreditNoteTable']//tbody//tr//td[2]//div//textarea"
					+ "[contains(text(),'"+excelData.ProductName+"')]//following::td[@id='totaldetailamount']")).getAttribute("data-value");
			double getProductAmountDouble = Double.parseDouble(getProductAmount);
			System.out.println("Actual Discount Amount is: "+getProductAmountDouble);
			//	System.out.println("Expected Discount Amount is: "+expectedDiscountAmount);

			/*
			 * soft.assertEquals(getProductAmountDouble, expectedDiscountAmount,
			 * "Actual and Expected Discount Amount Mismatched For "+excelData.ProductName);
			 */

			ExpSubTotal = ExpSubTotal + getProductAmountDouble;

			if (excelData.ZeroGst.equals("TRUE")) {
				ExpZeroGstProductamount = getProductAmountDouble + ExpZeroGstProductamount;
				System.out.println("ExpZeroGstProductamount: "+ExpZeroGstProductamount);

			}
		}

		click(cn.OverAllDiscountType);
		Select overAllDiscTypeSelect = new Select(cn.OverAllDiscountType);
		overAllDiscTypeSelect.selectByVisibleText(getexcelOverAllDiscountType);

		WebElement overAllDiscountType = driver.findElement(By.id("DiscountType"));
		Select overAllDiscountTypeSelect = new Select(overAllDiscountType);
		String getOverAllDiscountType = overAllDiscountTypeSelect.getFirstSelectedOption().getText();
		System.out.println("Over all Discount Type is: " + getOverAllDiscountType);

		Thread.sleep(1000);
		click(cn.OverAllDiscount);
		if (getOverAllDiscountType.equals("$")) {
			cn.OverAllDiscount.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(cn.OverAllDiscount, getExcelOverAllDiscountAmount + Keys.ENTER);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@id='GSt']")).click();

		} else if (getOverAllDiscountType.equals("%")) {
			cn.OverAllDiscount.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(cn.OverAllDiscount, getExcelOverAllDiscountPercentage + Keys.ENTER);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@id='GSt']")).click();

		} else {
			System.out.println("No Over All Discount Amounr and Percentage");
		}
		System.out.println();

		System.out.println("*** Sub Total Calculation ***");
		String getSubtotalString = driver.findElement(By.xpath("//table[@id='CreditNoteTable']//tfoot//tr[1]//td[13]//p[@id='tSubtotal']")).getText();
		double getSubtotalDouble = Double.parseDouble(getSubtotalString);
		System.out.println("Actual Subtotal is: "+getSubtotalDouble);
		System.out.println("Expected SubTotal is: "+ExpSubTotal);
		System.out.println();

		soft.assertEquals(getSubtotalDouble, ExpSubTotal, "Actual and Expected SubTotal Mismatched");

		//Over All Discount Calculation:-
		System.out.println("*** Grand Total Calculation With Over All Discount and Percentage ***");

		double discountAmountDouble = Double.parseDouble(getExcelOverAllDiscountAmount);
		double discountPercentageDouble = Double.parseDouble(getExcelOverAllDiscountPercentage);
		double gstPercentage = Double.parseDouble(getExcelGstPercentage);
		double discountPercentageAmount = 0;
		double discountTotalAmount = 0;

		if (getOverAllDiscountType.equalsIgnoreCase("$")) {
			System.out.println("Current Discount Type is: $");

			discountTotalAmount = getSubtotalDouble - discountAmountDouble;
			System.out.println("Discount Amount is: "+discountTotalAmount);
			System.out.println("Over All Discount Amount is: " + discountTotalAmount);

		} else if (getOverAllDiscountType.equalsIgnoreCase("%")) {
			System.out.println("Current Discount Type is: %");

			discountTotalAmount = (getSubtotalDouble * discountPercentageDouble / 100);
			discountTotalAmount = (getSubtotalDouble - discountTotalAmount);

			System.out.println("Discount Percentage Amount is: " + discountTotalAmount);
			System.out.println("Over All Discount Percentage Amount is: " + discountTotalAmount);
		}

		double divOverAllDisc = discountAmountDouble / getSubtotalDouble;
		double finalWithoutGstAmount = 0;

		for (ExcelData excelData : excelDataList) {

			if (excelData.ZeroGst.equalsIgnoreCase("true") && getOverAllDiscountType.equalsIgnoreCase("$")) {

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

		// GST Calculation:-
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

		String ActualGstAmount = driver.findElement(By.xpath("//table[@id='CreditNoteTable']//tfoot//tr[6]//td[13]//input[@id='GSt']")).getAttribute("value");
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

		// Grand Total Amount:-
		System.out.println("*** Grand Total Amount ***");

		String finalTotalAmount = driver.findElement(By.xpath("//table[@id='CreditNoteTable']//tfoot//tr[9]//td[12]//input[@id='Amount']")).getAttribute("value");
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

		// Currency Calculation:-
		System.out.println("*** Grand Total Calculation With Currency ***");
		System.out.println("Sub Total in Double: " + finalTotalAmountDouble);

		String currencyName = cn.CurrencyCode.getText().trim();
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

		Thread.sleep(3000);
		click(cn.Save);
		//	driver.findElement(By.xpath("(//button[text()='Save & Close']//following::span[@class='caret'])[1]")).click();
		//	driver.findElement(By.xpath("//span[@class='caret']//following::ul//li//input[@id='SaveNew']")).click();
		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("Alert Text: " + alertText);

		} catch (Exception e) {
			System.out.println("No alert appeared after save.");
		}	
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now1 = LocalDateTime.now();
		String timestamp1 = dtf1.format(now1).replace(":", ";").replace("/", "-");
		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File s1 = ts1.getScreenshotAs(OutputType.FILE);
		File s11 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes SaveandClose "
				+ timestamp1 + ".png");
		FileUtils.copyFile(s1, s11);

		System.out.println("*** Credit Notes Save Successfull ***");

		// Edit
		driver.findElement(By.xpath("(//table[@id='credittable']//tbody//tr//td//a[@title='Edit'])[1]")).click();

		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now2 = LocalDateTime.now();
		String timestamp2 = dtf2.format(now2).replace(":", ";").replace("/", "-");
		TakesScreenshot ts2 = (TakesScreenshot) driver;
		File s2 = ts2.getScreenshotAs(OutputType.FILE);
		File s21 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes Edit "
				+ timestamp2 + ".png");
		FileUtils.copyFile(s2, s21);

		click(cn.Back);
		Thread.sleep(4000);

		DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now3 = LocalDateTime.now();
		String timestamp3 = dtf3.format(now3).replace(":", ";").replace("/", "-");
		TakesScreenshot ts3 = (TakesScreenshot) driver;
		File s3 = ts3.getScreenshotAs(OutputType.FILE);
		File s31 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes Back "
				+ timestamp3 + ".png");
		FileUtils.copyFile(s3, s31);
		System.out.println("** Edit Successful **");

		// Details
		driver.findElement(By.xpath("(//table[@id='credittable']//tbody//tr//td//a[@title='Details'])[1]")).click();

		DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now4 = LocalDateTime.now();
		String timestamp4 = dtf4.format(now4).replace(":", ";").replace("/", "-");
		TakesScreenshot ts4 = (TakesScreenshot) driver;
		File s4 = ts4.getScreenshotAs(OutputType.FILE);
		File s41 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes Details "
				+ timestamp4 + ".png");
		FileUtils.copyFile(s4, s41);

		click(cn.Back);
		Thread.sleep(4000);

		DateTimeFormatter dtf5 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now5 = LocalDateTime.now();
		String timestamp5 = dtf5.format(now5).replace(":", ";").replace("/", "-");
		TakesScreenshot ts5 = (TakesScreenshot) driver;
		File s5 = ts5.getScreenshotAs(OutputType.FILE);
		File s51 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes Back "
				+ timestamp5 + ".png");
		FileUtils.copyFile(s5, s51);
		System.out.println("** Details Successfull **");

		// Delete
		driver.findElement(By.xpath("(//table[@id='credittable']//tbody//tr//td//a[@title='Delete'])[1]")).click();

		DateTimeFormatter dtf6 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now6 = LocalDateTime.now();
		String timestamp6 = dtf6.format(now6).replace(":", ";").replace("/", "-");
		TakesScreenshot ts6 = (TakesScreenshot) driver;
		File s6 = ts6.getScreenshotAs(OutputType.FILE);
		File s61 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes Delete "
				+ timestamp6 + ".png");
		FileUtils.copyFile(s6, s61);

		click(cn.Delete);
		Thread.sleep(1000);
		click(cn.PopupOk);
		Thread.sleep(3000);

		DateTimeFormatter dtf7 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now7 = LocalDateTime.now();
		String timestamp7 = dtf7.format(now7).replace(":", ";").replace("/", "-");
		TakesScreenshot ts7 = (TakesScreenshot) driver;
		File s7 = ts7.getScreenshotAs(OutputType.FILE);
		File s71 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes Delete "
				+ timestamp7 + ".png");
		FileUtils.copyFile(s7, s71);
		System.out.println("** Delete Successfull **");


	}

	@Ignore
	@Test(priority = 15, dependsOnMethods = "ERPLoginPage")
	public void SalesInvoicetoCreditNotes() throws IOException, InterruptedException {

		driver.navigate().to(url + "Sales/SalesInvoiceIndex");
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		CreditNotes cn = new CreditNotes(driver);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		SalesInvoice si = new SalesInvoice(driver);		
		Thread.sleep(4000);
		System.out.println("* Sales Invoice Page *");

		click(si.AddInvoice);
		Thread.sleep(4000);

		int excelDataListSize = excelDataList.size();
		for (int i = 0; i < excelDataListSize; i++) {	
			ExcelData excelData = excelDataList.get(i);

			if (excelData.Customer.isEmpty() == false) {

				Thread.sleep(2000);
				click(si.Customer);
				WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search'][1]")));
				searchInput.sendKeys(excelData.Customer + Keys.ENTER);
				Thread.sleep(2000);

			}

			click(si.Qty);
			Thread.sleep(1000);
		/*	click(si.Price);
			Thread.sleep(3000);
			WebElement findElement = driver.findElement(By.xpath("//select[@id='ProductId']"));
			Select select=new Select(findElement);
			for (WebElement option : select.getOptions()) {

				if (option.getText().contains(excelData.ProductCode)) {
					System.out.println("%");
					option.click();
					break;
				}
			} */
			
			driver.findElement(By.id("select2-ProductId-container")).click();
			driver.findElement(By.xpath("//input[@type='search']")).sendKeys(excelData.ProductCode +Keys.ENTER);

			click(si.Qty);
			Thread.sleep(1000);
			click(si.DiscountAmount);
			Thread.sleep(1000);

			if (excelData.Type.equalsIgnoreCase("Product")) {

				click(si.ChooseUom);
				List<WebElement> subUomOption = driver.findElements(By.xpath(
						"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
				for (WebElement option : subUomOption) {
					if (option.getText().trim().equals(excelData.Uom)) {
						option.click();
						break;
					}

				}

			}

			// Qty:-
			Sendkeys(si.Qty, excelData.Qty);

			// Price:-
			click(si.Price);
			si.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(si.Price, excelData.Price);
			Thread.sleep(3000);

			// Add
			wait.until(ExpectedConditions.visibilityOf(si.Add)).click();
			Thread.sleep(2000);

			// Batch Details:-
			if (excelData.BatchProduct.equalsIgnoreCase("true")) {

				String bQty = driver.findElement(By.xpath(
						"//strong[contains(text(),'" + excelData.ProductName + "')]//following::input[@id='BQty']"))
						.getAttribute("value");
				System.out.println("B.Qty is: " + bQty);

				String lQty = driver.findElement(By.xpath(
						"//strong[contains(text(),'" + excelData.ProductName + "')]//following::input[@id='LQty']"))
						.getAttribute("value");
				System.out.println("L.Qty is: " + lQty);

				WebElement bulkQty = driver.findElement(By.xpath(
						"//strong[contains(text(),'" + excelData.ProductName + "')]//following::input[@id='BulkQty']"));
				bulkQty.click();
				bulkQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				bulkQty.sendKeys(bQty);

				WebElement looseQty = driver.findElement(By.xpath("//strong[contains(text(),'" + excelData.ProductName
						+ "')]//following::input[@id='LooseQty']"));
				looseQty.click();
				looseQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				looseQty.sendKeys(lQty);

				Thread.sleep(2000);
				driver.findElement(By.xpath(
						"//strong[contains(text(),'" + excelData.ProductName + "')]//following::button[text()='Add']"))
				.click();
				Thread.sleep(1000);
				System.out.println("** Batch File Closed **");

			}
		}// excel

		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@id='Create']")).click();
		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("Alert Text: " + alertText);

		} catch (Exception e) {

			System.out.println("No alert appeared after save.");

		}

		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now1 = LocalDateTime.now();
		String timestamp1 = dtf1.format(now1).replace(":", ";").replace("/", "-");
		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File s1 = ts1.getScreenshotAs(OutputType.FILE);
		File s11 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " Invoice SaveandClose "
				+ timestamp1 + ".png");
		FileUtils.copyFile(s1, s11);

		Thread.sleep(3000);
		driver.findElement(By.xpath("(//table[@id='Invoicetable']//tbody//tr//td//a[@title='Details'])[1]")).click();

		Thread.sleep(2000);
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now2 = LocalDateTime.now();
		String timestamp2 = dtf2.format(now2).replace(":", ";").replace("/", "-");
		TakesScreenshot ts2 = (TakesScreenshot) driver;
		File s2 = ts2.getScreenshotAs(OutputType.FILE);
		File s21 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " SalesInvoice Details "
				+ timestamp2 + ".png");
		FileUtils.copyFile(s2, s21);

		click(si.IssueCreditNote);
		Thread.sleep(2000);
		click(si.PopupOk);
		DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now3 = LocalDateTime.now();
		String timestamp3 = dtf3.format(now3).replace(":", ";").replace("/", "-");
		TakesScreenshot ts3 = (TakesScreenshot) driver;
		File s3 = ts3.getScreenshotAs(OutputType.FILE);
		File s31 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes Back "
				+ timestamp3 + ".png");
		FileUtils.copyFile(s3, s31);

		click(cn.Save);
		Thread.sleep(2000);

		DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now4 = LocalDateTime.now();
		String timestamp4 = dtf4.format(now4).replace(":", ";").replace("/", "-");
		TakesScreenshot ts4 = (TakesScreenshot) driver;
		File s4 = ts4.getScreenshotAs(OutputType.FILE);
		File s41 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes save "
				+ timestamp4 + ".png");
		FileUtils.copyFile(s4, s41);

	}
	
	@Ignore
	@Test(priority = 16, dependsOnMethods = "ERPLoginPage")
	public void SalesReturntoCreditNotes() throws InterruptedException, IOException {

		driver.navigate().to(url + "SalesPurchases/SalesReturn");
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		SalesReturn sr = new SalesReturn(driver);
		CreditNotes cn = new CreditNotes(driver);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
				("//input[@id='Create' and @value='[+] Add  Sales Return']"))).click();
	//	driver.findElement(By.xpath("//input[@id='Create' and @value='[+] Add  Sales Return']")).click();
		Thread.sleep(4000);

		int excelDataListSize = excelDataList.size();
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			if (excelData.Customer.isEmpty() == false) {

				Thread.sleep(2000);
				click(sr.Customer);
				WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search'][1]")));
				searchInput.sendKeys(excelData.Customer + Keys.ENTER);
				Thread.sleep(2000);

			}

			WebElement findElement = driver
					.findElement(By.xpath("//span[@aria-labelledby='select2-ProductId-container']"));
			findElement.click();
			Thread.sleep(2000);

			driver.findElement(By.xpath(
					"//span[@aria-labelledby='select2-ProductId-container']//following::input[@type='search']"))
			.sendKeys(excelData.ProductName + Keys.ENTER);

			click(sr.Qty);
			click(sr.Uom);
			List<WebElement> subUomOption = driver.findElements(By.xpath(
					"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
			for (WebElement option : subUomOption) {
				if (option.getText().trim().equals(excelData.Uom)) {
					option.click();
					break;
				}
			}
			// Qty:-
			Sendkeys(sr.Qty, excelData.Qty);

			// Price:-
			click(sr.Price);
			sr.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(sr.Price, excelData.Price);

			// Add:-
			Thread.sleep(1000);
			click(sr.Add);
			Thread.sleep(2000);

			if (excelData.BatchProduct.equalsIgnoreCase("true")) {

				String bQty = driver.findElement(By.xpath(
						"//strong[contains(text(),'" + excelData.ProductName + "')]//following::strong//input[@id='BQty1']"))
						.getAttribute("value");
				System.out.println("B.Qty is: " + bQty);

				String lQty = driver.findElement(By.xpath(
						"//strong[contains(text(),'" + excelData.ProductName + "')]//following::strong//input[@id='LQty1']"))
						.getAttribute("value");
				System.out.println("L.Qty is: " + lQty);

				WebElement bulkQty = driver.findElement(By.xpath(
						"//strong[contains(text(),'" + excelData.ProductName + "')]//following::input[@id='BulkQty']"));
				bulkQty.click();
				bulkQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				bulkQty.sendKeys(bQty);

				WebElement looseQty = driver.findElement(By.xpath("//strong[contains(text(),'" + excelData.ProductName
						+ "')]//following::input[@id='LooseQty']"));
				looseQty.click();
				looseQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				looseQty.sendKeys(lQty);

				Thread.sleep(2000);
				driver.findElement(By.xpath(
						"//strong[contains(text(),'" + excelData.ProductName + "')]//following::button[text()='Add']"))
				.click();

			}
		} //excel

		Thread.sleep(3000);
		click(sr.convertcreditnote);
		click(sr.PopupOk);

		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now1 = LocalDateTime.now();
		String timestamp1 = dtf1.format(now1).replace(":", ";").replace("/", "-");
		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File s1 = ts1.getScreenshotAs(OutputType.FILE);
		File s11 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " SalesReturn to CreditNotes "
				+ timestamp1 + ".png");
		FileUtils.copyFile(s1, s11);
		System.out.println("** SalesReturn Convert to CreditNotes **");

		Thread.sleep(2000);
		click(cn.Save);
		Thread.sleep(3000);
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now2 = LocalDateTime.now();
		String timestamp2 = dtf2.format(now2).replace(":", ";").replace("/", "-");
		TakesScreenshot ts2 = (TakesScreenshot) driver;
		File s2 = ts2.getScreenshotAs(OutputType.FILE);
		File s21 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " Return CreditNotes Save "
				+ timestamp2 + ".png");
		FileUtils.copyFile(s2, s21);

	}
	
	@Ignore
	@Test(priority = 17, dependsOnMethods = "ERPLoginPage")
	public void CreditNotesPost() throws InterruptedException, IOException {

		driver.navigate().to(url + "Sales/CreditNoteIndex");
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		CreditNotes cn = new CreditNotes(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		click(cn.AddCreditNote);
		Thread.sleep(3000);

		int excelDataListSize = excelDataList.size();
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			if (excelData.Customer.isEmpty() == false) {

				Thread.sleep(2000);
				click(cn.Customer);
				WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search'][1]")));
				searchInput.sendKeys(excelData.Customer + Keys.ENTER);
				Thread.sleep(2000);

			}	
			click(cn.Qty);
			Thread.sleep(1000);
			click(cn.Price);
			System.out.println(i);
			Thread.sleep(3000);

			WebElement findElement = driver.findElement(By.xpath("//select[@id='ProductId']"));
			Select select=new Select(findElement);
			for (WebElement option : select.getOptions()) {

				if (option.getText().contains(excelData.ProductCode)) {
					System.out.println("%");
					option.click();
					break;
				}
			}
			
			click(cn.Qty);
			click(cn.ChooseUom);
			List<WebElement> subUomOption = driver.findElements(By.xpath(
					"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
			for (WebElement option : subUomOption) {
				if (option.getText().trim().equals(excelData.Uom)) {
					option.click();
					break;
				}

			}

			//Qty:-
			Sendkeys(cn.Qty, excelData.Qty);

			//Price:-
			click(cn.Price);
			cn.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(cn.Price, excelData.Price);

			//Add:-
			Thread.sleep(1000);
			click(cn.Add);
			Thread.sleep(2000);

			//Batch Details:-
			if (excelData.BatchProduct.equalsIgnoreCase("true")) {
				
				String bQty = driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::input[@id='BQty']")).getAttribute("value");
				System.out.println("B.Qty is: "+bQty);
				
				String lQty = driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::input[@id='LQty']")).getAttribute("value");
				System.out.println("L.Qty is: "+lQty);
				
				WebElement bulkQty = driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::input[@id='BulkQty']"));
				bulkQty.click();
				bulkQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				bulkQty.sendKeys(bQty);
				
				WebElement looseQty = driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::input[@id='LooseQty']"));
				looseQty.click();
				looseQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				looseQty.sendKeys(lQty);

				Thread.sleep(2000);
				driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::button[text()='Add']")).click();

			}
		
		} //excel			
		
		Thread.sleep(3000);
		click(cn.Post);
		Thread.sleep(1000);
		click(cn.PopupOk);
		Thread.sleep(1000);
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now1 = LocalDateTime.now();
		String timestamp1 = dtf1.format(now1).replace(":", ";").replace("/", "-");
		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File s1 = ts1.getScreenshotAs(OutputType.FILE);
		File s11 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes Post "
				+ timestamp1 + ".png");
		FileUtils.copyFile(s1, s11);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Cancel']//preceding::input[@value='Post']")).click();
		Thread.sleep(3000);
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now2 = LocalDateTime.now();
		String timestamp2 = dtf2.format(now2).replace(":", ";").replace("/", "-");
		TakesScreenshot ts2 = (TakesScreenshot) driver;
		File s2 = ts2.getScreenshotAs(OutputType.FILE);
		File s21 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes Post Complete "
				+ timestamp2 + ".png");
		FileUtils.copyFile(s2, s21);

	}
	
	@Ignore
	@Test(priority = 18, dependsOnMethods = "ERPLoginPage")
	public void CreditNotesHold() throws InterruptedException, IOException {
		
		driver.navigate().to(url + "Sales/CreditNoteIndex");
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		CreditNotes cn = new CreditNotes(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		click(cn.AddCreditNote);
		Thread.sleep(3000);

		int excelDataListSize = excelDataList.size();
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			if (excelData.Customer.isEmpty() == false) {

				Thread.sleep(2000);
				click(cn.Customer);
				WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search'][1]")));
				searchInput.sendKeys(excelData.Customer + Keys.ENTER);
				Thread.sleep(2000);

			}	
			click(cn.Qty);
			Thread.sleep(1000);
			click(cn.Price);
			System.out.println(i);
			Thread.sleep(3000);

			WebElement findElement = driver.findElement(By.xpath("//select[@id='ProductId']"));
			Select select=new Select(findElement);
			for (WebElement option : select.getOptions()) {

				if (option.getText().contains(excelData.ProductCode)) {
					System.out.println("%");
					option.click();
					break;
				}
			}
			
			click(cn.Qty);
			click(cn.ChooseUom);
			List<WebElement> subUomOption = driver.findElements(By.xpath(
					"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
			for (WebElement option : subUomOption) {
				if (option.getText().trim().equals(excelData.Uom)) {
					option.click();
					break;
				}

			}

			//Qty:-
			Sendkeys(cn.Qty, excelData.Qty);

			//Price:-
			click(cn.Price);
			cn.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(cn.Price, excelData.Price);

			//Add:-
			Thread.sleep(1000);
			click(cn.Add);
			Thread.sleep(2000);

			//Batch Details:-
			if (excelData.BatchProduct.equalsIgnoreCase("true")) {
				
				String bQty = driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::input[@id='BQty']")).getAttribute("value");
				System.out.println("B.Qty is: "+bQty);
				
				String lQty = driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::input[@id='LQty']")).getAttribute("value");
				System.out.println("L.Qty is: "+lQty);
				
				WebElement bulkQty = driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::input[@id='BulkQty']"));
				bulkQty.click();
				bulkQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				bulkQty.sendKeys(bQty);
				
				WebElement looseQty = driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::input[@id='LooseQty']"));
				looseQty.click();
				looseQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				looseQty.sendKeys(lQty);

				Thread.sleep(2000);
				driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ProductName+"')]//following::button[text()='Add']")).click();

			}
		
		} //excel			
		
		Thread.sleep(3000);
		click(cn.Hold);
		Thread.sleep(2000);
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now1 = LocalDateTime.now();
		String timestamp1 = dtf1.format(now1).replace(":", ";").replace("/", "-");
		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File s1 = ts1.getScreenshotAs(OutputType.FILE);
		File s11 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes Hold "
				+ timestamp1 + ".png");
		FileUtils.copyFile(s1, s11);
		
		
	}
	
	@Test(priority = 19, dependsOnMethods = "ERPLoginPage")
	public void CreditNotesPrint() throws InterruptedException, IOException {
		
		driver.navigate().to(url + "Sales/CreditNoteIndex");
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//table[@id='credittable']//tbody//tr//td//a[@title='Edit'])[1]")).click();
		Thread.sleep(3000);
		WebElement editprint = driver.findElement(By.id("btnPrint"));
		js.executeScript("arguments[0].scrollIntoView", editprint);
		click(editprint);
		
		Thread.sleep(3000);
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now1 = LocalDateTime.now();
		String timestamp1 = dtf1.format(now1).replace(":", ";").replace("/", "-");
		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File s1 = ts1.getScreenshotAs(OutputType.FILE);
		File s11 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes Edit Print "
				+ timestamp1 + ".png");
		FileUtils.copyFile(s1, s11);
		
		driver.navigate().back();
		driver.navigate().refresh();
		
		driver.findElement(By.xpath("(//table[@id='credittable']//tbody//tr//td//a[@title='Details'])[1]")).click();

		Thread.sleep(3000);
		WebElement detailsprint = driver.findElement(By.id("btnPrint"));
		js.executeScript("arguments[0].scrollIntoView", detailsprint);
		click(detailsprint);
		
		Thread.sleep(3000);
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now2 = LocalDateTime.now();
		String timestamp2 = dtf2.format(now2).replace(":", ";").replace("/", "-");
		TakesScreenshot ts2 = (TakesScreenshot) driver;
		File s2 = ts2.getScreenshotAs(OutputType.FILE);
		File s21 = new File("C:\\Adaptive\\Automation\\Payroll\\Login Error\\" + " CreditNotes Details Print "
				+ timestamp2 + ".png");
		FileUtils.copyFile(s2, s21);
		
		
		
		
	}

	@Test(priority = 30, dependsOnMethods = "ERPLoginPage")
	private void Exception() throws InterruptedException {
		soft.assertAll();

	}




}
