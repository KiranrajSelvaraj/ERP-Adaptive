package com.Sales;

import java.io.IOException;
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
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BaseClass.BaseClass;
import com.PomClass.CreditNotes;
import com.PomClass.Login;
import com.PomClass.Product;
import com.PomClass.ProductMovement;
import com.PomClass.SystemSettings;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreditNotesVoidTest extends BaseClass {

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
	}

	@DataProvider
	public Object[][] Util1() {

		Object data[][] = Util1.getTestData("C:\\Adaptive\\Automation\\Bizapp\\CreditNotesVoid.xlsx", "Sheet1");
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

	// @Ignore
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
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsFOCManagementInCN");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ss.EditSystemSetting);
		Thread.sleep(1000);
		IsFOCManagementInCN = ss.BooleanValue.isSelected();
		System.out.println("IsFOCManagementInCN :" + IsFOCManagementInCN);
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
		BulkQtyMeasurement = driver.findElement(By.xpath("//input[@id='StringValue']")).getAttribute("value");
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
		LooseQtyMeasurement = driver.findElement(By.xpath("//input[@id='StringValue']")).getAttribute("value");
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
		String DecimalCalculationForSalesString = driver.findElement(By.xpath("//input[@id='DecimalValue']"))
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
		IsReturnManagementInSI = ss.BooleanValue.isSelected();
		System.out.println("IsReturnManagementInSI :" + IsReturnManagementInSI);
		click(ss.Back);

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

	//@Ignore
	@Test(priority = 8, dependsOnMethods = "ERPLoginPage")
	public void ProductPage() throws InterruptedException {

		ProductList.addAll(ProductSet);

		driver.navigate().to(url + "SalesPurchases/Product");
		Thread.sleep(7000);
		System.out.println("*Product Details Page*");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Product prod = new Product(driver);

		int productSetSize = ProductSet.size();
		System.out.println("Product Set Size: " + productSetSize);
		for (String product : ProductSet) {

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
			click(prod.Back);
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

	//@Ignore
	@Test(priority = 10, dependsOnMethods = "ERPLoginPage")
	public void UomPage() throws InterruptedException {

		UOMList.addAll(UOMSet);
		driver.navigate().to(url + "SalesPurchases/UOM");
		Thread.sleep(4000);
		System.out.println("*UOM Page*");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int uomSetSize = UOMSet.size();
		System.out.println("Uom Set Size: " + uomSetSize);

		for (String uom : UOMSet) {

			WebElement selectUom = driver.findElement(By.xpath("//span[@id='select2-DropDown-container']"));
			selectUom.click();
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

	@Test(priority = 12, dependsOnMethods = "ERPLoginPage")
	public void DirecteCreditNote() throws InterruptedException, IOException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Actions action = new Actions(driver);
		CreditNotes cn = new CreditNotes(driver);

		driver.navigate().to(url + "SalesPurchases/CreditNotes");
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

		double ExpSubTotal = 0;
		double ExpZeroGstProductamount = 0;
		double expectedDiscountAmount = 0;
		double withoutZeroGstAmount = 0;

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

			if (i == 0) {

				click(cn.GstType);
				WebElement GstSearchInput = driver.findElement(
						By.xpath("//span[@id='select2-GSTTypeId-container']//following::input[@type='search']"));
				GstSearchInput.sendKeys(excelData.GstType + Keys.ENTER);				
			}

			if (i == 0) {

				getExcelGstType = excelData.GstType;
				getexcelOverAllDiscountType = excelData.OverAllDiscountType;
				getExcelOverAllDiscountPercentage = excelData.OverAllDiscountPercentage;
				getExcelOverAllDiscountAmount = excelData.OverAllDiscountAmount;
				getExcelGstPercentage = excelData.GstPercentage;
				getExcelCurrencyRate = excelData.CurrencyRate;
			}

			// Choose Product:-
			/*
			 * if (excelData.Type.equalsIgnoreCase("Product")) {
			 * 
			 * String productCheckbox =
			 * driver.findElement(By.id("ProductCheck")).getAttribute("checked"); if
			 * (!productCheckbox.equalsIgnoreCase("true")) { click(cn.ProductCheckbox);
			 * 
			 * }
			 * 
			 * } else if (excelData.Type.equals("Service")) {
			 * 
			 * if (!cn.ServiceCheckbox.isSelected()) { click(cn.ServiceCheckbox); }
			 * 
			 * } else if (excelData.Type.equals("Open")) {
			 * 
			 * if (!cn.OpenCheckbox.isSelected()) { click(cn.OpenCheckbox); } }
			 */

			/*
			 * if (excelData.Type.equalsIgnoreCase("Product")) {
			 * 
			 * if (i == 0) { click(cn.ChooseProduct);
			 * 
			 * }
			 * 
			 * WebElement productSearch =
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
			 * ("//span[@id='select2-ProductId-container']//following::input[@type='search']"
			 * ))); productSearch.click(); productSearch.sendKeys(excelData.ProductCode +
			 * Keys.ENTER);
			 * 
			 * } else if (excelData.Type.equalsIgnoreCase("Service")) {
			 * 
			 * driver.findElement( By.xpath(
			 * "//span[@id='select2-ServiceId-container']//following::input[@type='search']"
			 * )) .sendKeys(excelData.ProductCode + Keys.ENTER);
			 * 
			 * } else if (excelData.Type.equalsIgnoreCase("Open")) {
			 * 
			 * cn.OpenProduct.sendKeys(excelData.ProductCode + Keys.ENTER);
			 * 
			 * }
			 */

			js.executeScript("window.scrollBy(0, 1000)", "");
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
			}

			/*
			 * else if (excelData.Type.equalsIgnoreCase("Service")) {
			 * 
			 * click(cn.ChooseUom); List<WebElement> subUomOption =
			 * driver.findElements(By.xpath(
			 * "//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"
			 * )); for (WebElement option : subUomOption) { if
			 * (option.getText().trim().equals(excelData.Uom)) { option.click(); break; }
			 * 
			 * }
			 * 
			 * } else if (excelData.Type.equalsIgnoreCase("Open")) {
			 * 
			 * click(cn.ChooseUom); List<WebElement> subUomOption =
			 * driver.findElements(By.xpath(
			 * "//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"
			 * )); for (WebElement option : subUomOption) { if
			 * (option.getText().trim().equals(excelData.Uom)) { option.click(); break; }
			 * 
			 * }
			 * 
			 * }
			 */

			// Qoh Calculation:-
			System.out.println("*** Grand Total Calculation With QOH ***");

			String qohStock1 = driver.findElement(By.id("QOH")).getAttribute("value");

			for (product productDetails : ProductDetailsList) {

				if (productDetails.productName.equalsIgnoreCase(excelData.ProductName)) {

					if (productDetails.IsCartonSelected.equalsIgnoreCase("true")) {

						if (excelData.Uom.equalsIgnoreCase("1KG") || excelData.Uom.equalsIgnoreCase("1X10KG")
								|| excelData.Uom.equalsIgnoreCase("1X1KG") || excelData.Uom.equalsIgnoreCase("1X1X1KG")
								|| excelData.Uom.equalsIgnoreCase("KG")) {

							String[] split = productDetails.currentStockValue.split("[ B/L]+");
							int boxStock = Integer.parseInt(split[0]);
							int looseStock = Integer.parseInt(split[1]);

							int multipleBoxStock = (boxStock * 10);
							int addLooseStock = (multipleBoxStock + looseStock);
							// System.out.println("Lower Uom Stock: "+addLooseStock);

							String stock = "0 B/" + addLooseStock + " L";
							// System.out.println("Lower Uom Current Stock: "+stock);

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
							System.out.println("current Stock: " + productDetails.currentStockValue);

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
							System.out.println("Calculated QOH: " + replacetotalCalculatedStock);
							System.out.println("Current Stock from List: " + productDetails.currentStockValue);
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

			// Qty:-
			Sendkeys(cn.Qty, excelData.Qty);

			// Foc:-
			if (IsFOCManagementInCN == true) {

				if (excelData.Type.equalsIgnoreCase("Product")) {

					click(cn.Foc);
					Sendkeys(cn.Foc, excelData.Foc);

				} else if (excelData.Type.equalsIgnoreCase("Service")) {

					click(cn.Foc);
					Sendkeys(cn.Foc, excelData.Foc);
				}

			} else {

				System.out.println("Foc Field Is Not Displayed");
			}

			// Price:-
			click(cn.Price);
			cn.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(cn.Price, excelData.Price);

			// Item Level Discount:-
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
					System.out.println("Discount Percentage Amount Total: " + Total);

				} else if (excelData.DiscountAmount.isBlank() == false) {

					double discAmtDouble1 = Double.parseDouble(excelData.DiscountAmount);
					double excelPriceDouble = Double.parseDouble(excelData.Price);
					double discountAmount = (excelPriceDouble * excelQtyDouble);

					expectedDiscountAmount = (discountAmount - discAmtDouble1);

					Total = expectedDiscountAmount - Total;
					System.out.println("Discount Amount Total: " + Total);

				}
			}
			System.out.println();

			Thread.sleep(1000);
			getProductAmount = driver.findElement(By.id("ItemAmount")).getAttribute("value");
			double getProductAmountDouble = Double.parseDouble(getProductAmount);
			System.out.println("Actual Amount: " + getProductAmountDouble);

			// Add:-
			Thread.sleep(2000);
			click(cn.Add);
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

				Thread.sleep(4000);
				click(cn.Qty);

			}

			/*
			 * WebElement productamount =
			 * wait.until(ExpectedConditions.presenceOfElementLocated( By.xpath(
			 * "//table[@id='CreditNoteTable']//tbody//tr//td[2]//div//textarea[contains(text(),'"
			 * + excelData.ProductName + "')]//following::td[@id='totaldetailamount']")));
			 * getProductAmount = productamount.getAttribute("data-value");
			 * 
			 * double getProductAmountDouble = Double.parseDouble(getProductAmount);
			 * System.out.println("Actual Discount Amount is: " + getProductAmountDouble);
			 */

			ExpSubTotal = ExpSubTotal + getProductAmountDouble;

			if (excelData.ZeroGst.equals("TRUE")) {
				ExpZeroGstProductamount = getProductAmountDouble + ExpZeroGstProductamount;
				System.out.println("ExpZeroGstProductamount: " + ExpZeroGstProductamount);

			}
			Thread.sleep(4000);
			// js.executeScript("window.scrollBy(0, -500);");
			// click(cn.Qty);

		} // Excel data list loop

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
			click(cn.Qty);

		} else if (getOverAllDiscountType.equals("%")) {
			cn.OverAllDiscount.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(cn.OverAllDiscount, getExcelOverAllDiscountPercentage + Keys.ENTER);
			Thread.sleep(1000);
			click(cn.Qty);

		} else {
			System.out.println("No Over All Discount Amounr and Percentage");
		}
		System.out.println();

		System.out.println("*** Sub Total Calculation ***");
		String getSubtotalString = driver
				.findElement(By.xpath("//table[@id='CreditNoteTable']//tfoot//tr//td//input[@id='order']"))
				.getAttribute("value");
		double getSubtotalDouble = Double.parseDouble(getSubtotalString);
		System.out.println("Actual Subtotal is: " + getSubtotalDouble);
		System.out.println("Expected SubTotal is: " + ExpSubTotal);
		System.out.println();

		soft.assertEquals(getSubtotalDouble, ExpSubTotal, "Actual and Expected SubTotal Mismatched");

		// Over All Discount Calculation:-
		System.out.println("*** Grand Total Calculation With Over All Discount and Percentage ***");

		double discountAmountDouble = Double.parseDouble(getExcelOverAllDiscountAmount);
		double discountPercentageDouble = Double.parseDouble(getExcelOverAllDiscountPercentage);
		double gstPercentage = Double.parseDouble(getExcelGstPercentage);
		double discountPercentageAmount = 0;
		double discountTotalAmount = 0;

		if (getOverAllDiscountType.equalsIgnoreCase("$")) {
			System.out.println("Current Discount Type is: $");

			discountTotalAmount = getSubtotalDouble - discountAmountDouble;
			System.out.println("Discount Amount is: " + discountTotalAmount);
			System.out.println("Over All Discount Amount is: " + discountTotalAmount);

		} else if (getOverAllDiscountType.equalsIgnoreCase("%")) {
			System.out.println("Current Discount Type is: %");

			discountTotalAmount = (getSubtotalDouble * discountPercentageDouble / 100);
			discountPercentageAmount = (getSubtotalDouble - discountTotalAmount);

			System.out.println("Discount Percentage Amount is: " + discountTotalAmount);
			System.out.println("Over All Discount Percentage Amount is: " + discountPercentageAmount);
		}

		double divOverAllDisc = discountAmountDouble / getSubtotalDouble;
		double finalWithoutGstAmount = 0;

		for (ExcelData excelData : excelDataList) {

			if (excelData.ZeroGst.equalsIgnoreCase("true") && getOverAllDiscountType.equalsIgnoreCase("$")) {

				double withoutGstAmount = divOverAllDisc * ExpZeroGstProductamount;
				finalWithoutGstAmount = ExpZeroGstProductamount - withoutGstAmount;
				String formatFinalWithoutGstAmount = String.format("%.2f", finalWithoutGstAmount);
				System.out.println("Without Gst Product Amount: " + formatFinalWithoutGstAmount);

			} else if (getOverAllDiscountType.equalsIgnoreCase("%")) {

				double gstProductAmount = ExpSubTotal + ExpZeroGstProductamount;
				System.out.println("gstProductAmount: " + gstProductAmount);
				double zeroGstProductDiscountAmount = (ExpZeroGstProductamount * discountPercentageDouble / 100);
				double subrationZerGstAmount = (ExpZeroGstProductamount - zeroGstProductDiscountAmount);
				System.out.println("After Discount Zero Gst Product Amount is: " + subrationZerGstAmount);
				withoutZeroGstAmount = (discountPercentageAmount - subrationZerGstAmount);
				System.out.println("Without Zero Gst Amount: " + withoutZeroGstAmount);
				System.out.println();

			}
			break;
		}

		double subWithoutGstAmount = discountTotalAmount - finalWithoutGstAmount;
		String formatSubWithoutGstAmount = String.format("%.2f", subWithoutGstAmount);
		System.out.println("With Gst Product SubTotal: " + formatSubWithoutGstAmount);
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

		String ActualGstAmount = driver
				.findElement(By.xpath("//table[@id='CreditNoteTable']//tfoot//tr//td//input[@id='GSt']"))
				.getAttribute("value");
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

		String finalTotalAmount = driver
				.findElement(By.xpath("//table[@id='CreditNoteTable']//tfoot//tr//td//input[@id='Amount']"))
				.getAttribute("value");
		String replaceAllFinalTotalAmount = finalTotalAmount.replaceAll(",", "");
		double finalTotalAmountDouble = Double.parseDouble(replaceAllFinalTotalAmount);
		String finalTotalAmountFormat = String.format("%.2f", finalTotalAmountDouble);
		System.out.println("Actual Grand Total Amount is: " + finalTotalAmountFormat);

		double ExpectedGrandTotalAmount = (discountPercentageAmount + finalExpectedGstAmount);
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

		String getCreditNotesNo = driver.findElement(By.id("CreditNoteNumber")).getAttribute("value");
		System.out.println("getCreditNotesNo: " + getCreditNotesNo);

		Thread.sleep(3000);
		click(cn.Save);

		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("Alert Text: " + alertText);

		} catch (Exception e) {

			System.out.println("No alert appeared after save.");

		}
		System.out.println("** Credit Notes Save Successfull **");
		System.out.println();

		Thread.sleep(3000);
		WebElement delete = driver.findElement(By.xpath("//table[@id='credittable']//tbody//tr//td"
				+ "[normalize-space()='" + getCreditNotesNo + "']//following::td[7]//a[@title='Delete']"));
		delete.click();
		Thread.sleep(2000);
		click(cn.Delete);
		Thread.sleep(2000);
		click(cn.PopupOk);
		try {

			String alertText = driver.findElement(By.id("popup_message")).getText();
			System.out.println("Alert Text: " + alertText);

		} catch (Exception e) {

			System.out.println("No alert appeared after save.");

		}
		System.out.println("*** Credit Notes Delete Successfull ***");
		System.out.println();
	}

	class StockCalculation {

		private String calculateStock;
		private String productName;

		public StockCalculation(String calculateStock, String productName) {
			super();

			this.calculateStock = calculateStock;
			this.productName = productName;

		}
	}

	ArrayList<StockCalculation> StockCalculationList = new ArrayList<>();

	//@Ignore
	@Test(priority = 14, dependsOnMethods = "ERPLoginPage")
	public void StockCalculation() {

		System.out.println("* Stock Calculation *");
		int excelDataListSize = excelDataList.size();
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			double doubleExcelQty = 0;
			double doubleUomUnit = 0;
			double multipleQty = 0;

			int UomDetailsListSize = UomDetailsList.size();
			for (int j = 0; j < UomDetailsListSize; j++) {
				UOM uomData = UomDetailsList.get(j);

				if (excelData.Uom.equals(uomData.UomCodeValue)) {

					doubleExcelQty = Double.parseDouble(excelData.Qty);
					doubleUomUnit = Double.parseDouble(uomData.UomUnits);

					multipleQty = doubleExcelQty * doubleUomUnit;
					System.out.println("multipleQty is: " + multipleQty);
					break;
				}

			} // Uom details list loop

			int ProductDetailsListSize = ProductDetailsList.size();
			for (int k = 0; k < ProductDetailsListSize; k++) {
				product productData = ProductDetailsList.get(k);

				double calculateStockDouble = 0;
				String calculateStock = "0";

				if (productData.productName.equalsIgnoreCase(excelData.ProductName)) {

					String productName = excelData.ProductName;

					if (productData.IsCarton) {

						double multipleBoxStock = 0;
						double doubleLooseCurrentStock = 0;

						String[] splitCurrentStockValue = productData.currentStockValue.split("/");
						for (String currentStock : splitCurrentStockValue) {
							if (currentStock.contains("B")) {
								String replaceAllBoxCurrentStock = currentStock.replaceAll("[A-Za-z]", "");
								double doubleBoxCurrentStock = Double.parseDouble(replaceAllBoxCurrentStock);

								multipleBoxStock = doubleBoxCurrentStock * 10;
								// System.out.println("multipleBoxStock is: "+multipleBoxStock);

							} else if (currentStock.contains("L")) {
								String replaceAllLooseCurrentStock = currentStock.replaceAll("[A-Za-z]", "");
								doubleLooseCurrentStock = Double.parseDouble(replaceAllLooseCurrentStock);
								// System.out.println("doubleLooseCurrentStock is: "+doubleLooseCurrentStock);

							} // Current stock loop

							double additionBoxandLooseStock = multipleBoxStock + doubleLooseCurrentStock;
							calculateStockDouble = additionBoxandLooseStock + multipleQty;
							calculateStockDouble = calculateStockDouble - multipleQty;

						}

					} else {

						double doubleCurrentStock = Double.parseDouble(productData.currentStockValue);
						calculateStockDouble = doubleCurrentStock + multipleQty;
						calculateStockDouble = calculateStockDouble - multipleQty;
						int intcalculateStock = (int) calculateStockDouble;
						calculateStock = String.valueOf(intcalculateStock);

					}

					// calculateStock = String.valueOf(calculateStockDouble);

					if (productData.IsCarton) {

						double diviedStock = calculateStockDouble / 10;
						String stringCalculateStock = String.valueOf(diviedStock);
						String[] split = stringCalculateStock.split("\\.");
						String boxQty = split[0];
						String looseQty = "0";

						if (split.length > 1) {
							looseQty = split[1];
						}

						calculateStock = boxQty + " B/" + looseQty + " L";

						System.out.println("Product Name: " + productName);
						System.out.println("calculateCartonStock is: " + calculateStock);

					} else {

						System.out.println("Product Name: " + productName);
						System.out.println("CalculateBaseandNonCartonStock is: " + calculateStock);

					}

					System.out.println();

					StockCalculation StockCalculation = new StockCalculation(calculateStock, productName);
					StockCalculationList.add(StockCalculation);

				}

			} // Product data loop

		} // Excel data list loop

		System.out.println("** Stock Claculation Completed **");
		System.out.println();

	} // Method loop

	//@Ignore
	@Test(priority = 16, dependsOnMethods = "ERPLoginPage")
	public void ExpectedProduct() throws InterruptedException {

		driver.navigate().back();
		driver.navigate().refresh();
		Thread.sleep(2000);
		driver.navigate().to(url + "SalesPurchases/Product");
		Thread.sleep(7000);
		System.out.println("*** Product Page ***");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Product prod = new Product(driver);

		for (String product : ProductSet) {

			WebElement productcode = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//input[@value='Fetch']//preceding::input[@placeholder='Find a product or code ']")));
			Thread.sleep(1000);
			productcode.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			productcode.sendKeys(product);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(prod.Fetch)).click();
			Thread.sleep(3000);

			WebElement DetailsIcon = driver
					.findElement(By.xpath("//table[@id='producttable']//tbody//tr//td[1][normalize-space()='" + product
							+ "']//following::td[9]//a[@title='Details'][1]"));
			js.executeScript("arguments[0].click();", DetailsIcon);
			Thread.sleep(3000);

			String productName = driver.findElement(By.xpath("//div[@class='col-lg-7']//h2//b")).getText();
			System.out.println("ProductName: " + productName);

			String afterCurrentStockValue = driver
					.findElement(By.xpath("//dt[normalize-space()='Current Stock - HQ']//following-sibling::dd[1]"))
					.getText();

			for (StockCalculation stock : StockCalculationList) {

				if (stock.productName.equalsIgnoreCase(productName)) {

					System.out.println("Actual Current Stock: " + afterCurrentStockValue);
					System.out.println("Expected current Stock: " + stock.calculateStock);
					System.out.println();

					soft.assertEquals(afterCurrentStockValue, stock.calculateStock,
							"Actual and Expected Product Stock Mismatched for Product " + stock.productName);

				}

			} // Stock calculation loop

			js.executeScript("arguments[0].click();", prod.Back);
			Thread.sleep(2000);

		}

		System.out.println();
	}

	//@Ignore
	@Test(priority = 18, dependsOnMethods = "ERPLoginPage")
	public void ProductMovementPage() throws InterruptedException {

		driver.navigate().to(url + "SalesPurchases/Product/ProductMovementsIndex");
		Thread.sleep(7000);
		System.out.println("*** Product Movement Page ***");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ProductMovement pm = new ProductMovement(driver);

		for (ExcelData excelData : excelDataList) {

			click(pm.ChooseProduct);
			WebElement productcode = driver.findElement(
					By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"));
			Thread.sleep(1000);
			productcode.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			productcode.sendKeys(excelData.ProductCode + Keys.ENTER);
			Thread.sleep(1000);

			/*
			 * boolean enabledUom =
			 * driver.findElement(By.xpath("//select[@id='UOM']")).isEnabled();
			 * System.out.println("enabledUom: "+enabledUom); if (enabledUom == true ) {
			 * 
			 * click(pm.UOM); List<WebElement> subUomOption = driver.findElements(By.xpath(
			 * "//span[@id='select2-UOM-container']//following::input[@type='search']//following::ul//li"
			 * )); for (WebElement option : subUomOption) { if
			 * (option.getText().trim().equals(excelData.Uom)) { option.click(); break; } }
			 * }
			 */

			js.executeScript("arguments[0].click();", pm.Fetch);
			Thread.sleep(3000);

			String productMovementName = driver.findElement(By.xpath("//span[@id='select2-ProductId-container']"))
					.getAttribute("title");
			String[] split = productMovementName.split(" - ", 2);
			String trimProductName = split[1].trim();

			String balanceQty = driver
					.findElement(By.xpath("(//div[@id='TableData']//following::table[1]//tbody//tr//td[4])[1]"))
					.getText();

			for (StockCalculation stock : StockCalculationList) {

				if (stock.productName.equalsIgnoreCase(trimProductName)) {

					if (stock.calculateStock.contains("/0 L")) {

						String replaceProductStock = stock.calculateStock.replaceAll("/0 L", "");

						System.out.println("Product Movement Name: " + trimProductName);
						System.out.println("Actual Product Movement Stock: " + balanceQty);
						System.out.println("Expected Product Movement Stock: " + replaceProductStock);
						System.out.println();

						soft.assertEquals(balanceQty, replaceProductStock,
								"Actual and Expected Product Movement Stock Mismatched for Product " + trimProductName);

					} else {

						System.out.println("Product Movement Name: " + trimProductName);
						System.out.println("Actual Product Movement Stock: " + balanceQty);
						System.out.println("Expected Product Movement Stock: " + stock.calculateStock);
						System.out.println();

						soft.assertEquals(balanceQty, stock.calculateStock,
								"Actual and Expected Product Movement Stock Mismatched for Product " + trimProductName);

					}
					break;
				}

			} // Stock calculation list loop

		}
		System.out.println();
	}

	@Test(priority = 30, dependsOnMethods = "ERPLoginPage")
	private void Exception() throws InterruptedException {
		soft.assertAll();

	}

	// @Ignore
	@Test(priority = 40, dependsOnMethods = "ERPLoginPage")
	private void quit() throws InterruptedException {
		driver.quit();

	}

}
