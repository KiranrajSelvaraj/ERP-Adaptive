package com.Purchase;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.HashedMap;
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
import com.PomClass.Login;
import com.PomClass.Product;
import com.PomClass.PurchaseOrder;
import com.PomClass.SystemSettings;
import com.PomClass.Vendors;

import com.Utility.Util1;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PurchaseOrderTest extends BaseClass {

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
	public Object[][] Util2() {
		Object[][] data = Util1.getTestData("C:\\Adaptive\\ERP\\PurchaseOrder7.xlsx", "Sheet1");
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

		public ExcelData(String Vendor, String CurrencyCode, String CurrancyRate, String GstType, String Type,
				String ProductCode, String ProductName, String Uom, String Qty, String Foc, String DiscountPercentage,
				String DiscountAmount, String UnitDiscCheckbox, String UnitDiscPercentage, String UnitDiscAmount,
				String Price, String IsSpecialPriceCheckbox, String SpecialPrice, String OverAllDiscountType,
				String OverAllDiscountAmount, String OverAllDiscountPercentage, String GstPercentage, String ZeroGst, String BatchProduct) {
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
			String OverAllDiscountAmount, String OverAllDiscountPercentage, String GstPercentage, String ZeroGst, String BatchProduct) {

		ExcelData data = new ExcelData(Vendor, CurrencyCode, CurrancyRate, GstType, Type, ProductCode, ProductName,
				Uom, Qty, Foc, DiscountPercentage, DiscountAmount, UnitDiscCheckbox, UnitDiscPercentage, UnitDiscAmount,
				Price, IsSpecialPriceCheckbox, SpecialPrice, OverAllDiscountType, OverAllDiscountAmount,
				OverAllDiscountPercentage, GstPercentage, ZeroGst, BatchProduct);

		excelDataList.add(data);
		ProductSet.add(ProductCode);
		VendorSet.add(Vendor);

	}

	Map<String, List<String>> ProductUomMap = new HashedMap<>();

	@Test(priority = 7, dependsOnMethods = "ERPLoginPage")
	public void ProductUomData() throws InterruptedException {
		ProductList.addAll(ProductSet);

		int ProductListSize = ProductList.size();
		System.out.println("Product size :" + ProductListSize);

		for (int i = 0; i < ProductListSize; i++) {
			Set<String> uomSet = new LinkedHashSet<>();
			String Productcode = ProductList.get(i);
			System.out.println("Productcode :" + Productcode);
			List<String> uomList1 = new ArrayList<>();
			int dataListSize = excelDataList.size();
			System.out.println("Data Size: " + dataListSize);
			for (int j = 0; j < dataListSize; j++) {
				ExcelData excelData = excelDataList.get(j);

				if (excelData.ProductCode.equals(Productcode)) {
					System.out.println("UOM: " + excelData.Uom);
					uomSet.add(excelData.Uom);

				}

			}
			uomList1.addAll(uomSet);
			ProductUomMap.put(Productcode, uomList1);

		}

		/*	Set<String> Products = ProductUomMap.keySet();
		for (String Product : Products) {
			System.out.println("Product :" + Product);
			List<String> uom = ProductUomMap.get(Product);
			int uomSize = uom.size();
			for (int i = 0; i < uomSize; i++) {
				String string = uom.get(i);
				System.out.println("UOM: " + string);

				System.out.println("***");
			}
		}*/

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

		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsZeroQtyPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		click(ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsZeroQtyPurchaseString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsZeroQtyPurchase = Boolean.parseBoolean(IsZeroQtyPurchaseString);
		System.out.println("IsZeroQtyPurchase: "+IsZeroQtyPurchase);
		click(ss.Back);

		Thread.sleep(2000);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsZeroGSTManagement" + Keys.ENTER);
		Thread.sleep(1000);
		click(ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsZeroGSTManagementString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsZeroGSTManagement = Boolean.parseBoolean(IsZeroGSTManagementString);
		System.out.println("IsZeroGSTManagement: "+IsZeroGSTManagement);
		click(ss.Back);

		Thread.sleep(2000);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsMultipleProductForPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		click(ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsMultipleProductForPurchaseString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsMultipleProductForPurchase = Boolean.parseBoolean(IsMultipleProductForPurchaseString);
		System.out.println("IsMultipleProductForPurchase : "+IsMultipleProductForPurchase);
		click(ss.Back);

		Thread.sleep(2000);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsGSTManagement" + Keys.ENTER);
		Thread.sleep(1000);
		click(ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsGSTManagementString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsGSTManagement = Boolean.parseBoolean(IsGSTManagementString);
		System.out.println("IsGSTManagement: "+IsGSTManagement);
		click(ss.Back);

		Thread.sleep(2000);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsMultipleServiceForPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		click(ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsMultipleServiceForPurchaseString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsGSTManagement = Boolean.parseBoolean(IsMultipleServiceForPurchaseString);
		System.out.println("IsMultipleServiceForPurchase: "+IsMultipleServiceForPurchase);
		click(ss.Back);

		Thread.sleep(2000);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsCurrencyEnabled" + Keys.ENTER);
		Thread.sleep(1000);
		click(ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsCurrencyEnabledString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsCurrencyEnabled = Boolean.parseBoolean(IsCurrencyEnabledString);
		System.out.println("IsCurrencyEnabled: "+IsCurrencyEnabled);
		click(ss.Back);

		Thread.sleep(2000);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsWarehouseManagement" + Keys.ENTER);
		Thread.sleep(1000);
		click(ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsWarehouseManagementString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsWarehouseManagement = Boolean.parseBoolean(IsWarehouseManagementString);
		System.out.println("IsWarehouseManagement: "+IsWarehouseManagement);
		click(ss.Back);

		Thread.sleep(2000);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsBarcodeManagementInPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		click(ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsBarcodeManagementInPurchaseString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsBarcodeManagementInPurchase = Boolean.parseBoolean(IsBarcodeManagementInPurchaseString);
		System.out.println("IsBarcodeManagementInPurchase: "+IsBarcodeManagementInPurchase);
		click(ss.Back);

		Thread.sleep(2000);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsOpenItemManagementInPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		click(ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsOpenItemManagementInPurchaseString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsOpenItemManagementInPurchase = Boolean.parseBoolean(IsOpenItemManagementInPurchaseString);
		System.out.println("IsOpenItemManagementInPurchase: "+IsOpenItemManagementInPurchase);
		click(ss.Back);

		Thread.sleep(2000);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "DecimalCalculationForPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		click(ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String DecimalCalculationForPurchaseString = driver
				.findElement(By.xpath("//input[@id='DecimalValue']")).getAttribute("value");
		DecimalCalculationForPurchase = Float.parseFloat(DecimalCalculationForPurchaseString);
		System.out.println("DecimalCalculationForPurchase: "+DecimalCalculationForPurchase);
		click(ss.Back);

		Thread.sleep(2000);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsEnableDirectPOtoGRN" + Keys.ENTER);
		Thread.sleep(1000);
		click(ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsEnableDirectPOtoGRNString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsEnableDirectPOtoGRN = Boolean.parseBoolean(IsEnableDirectPOtoGRNString);
		System.out.println("IsEnableDirectPOtoGRN: "+IsEnableDirectPOtoGRN);
		click(ss.Back);

		Thread.sleep(2000);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsEnableDirectPOtoSO" + Keys.ENTER);
		Thread.sleep(1000);
		click(ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsEnableDirectPOtoSOString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsEnableDirectPOtoSO = Boolean.parseBoolean(IsEnableDirectPOtoSOString);
		System.out.println("IsEnableDirectPOtoSO: "+IsEnableDirectPOtoSO);
		click(ss.Back);

		Thread.sleep(2000);
		ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(ss.SystemsSettingsParamCodeSearchField, "IsEnableItemLevelDiscountInPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		click(ss.SystemSettingsFetch);
		Thread.sleep(2000);
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsEnableItemLevelDiscountInPurchaseString = driver
				.findElement(By.xpath("//input[@id='BitValue']")).getAttribute("value");
		IsEnableItemLevelDiscountInPurchase = Boolean.parseBoolean(IsEnableItemLevelDiscountInPurchaseString);
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
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsMultiWordSearchInProductString = driver.findElement(By.xpath("(//input[@name='BitValue'])[2]"))
				.getAttribute("value");
		IsMultiWordSearchInProduct = Boolean.parseBoolean(IsMultiWordSearchInProductString);
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
		click(ss.EditSystemSetting);
		Thread.sleep(1000);
		String IsFOCManagementInPIString = driver.findElement(By.xpath("//input[@id='BitValue']"))
				.getAttribute("value");
		IsFOCManagementInPI = Boolean.parseBoolean(IsFOCManagementInPIString);
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

	// PRODUCT PAGE
	// @Ignore
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
			// String RetailPrice = null;

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
					 * "(//table[@id='ProductPartialUOM']//tbody//tr//td[2])[" + j
					 * +"]//following::td[5]//input")) .getAttribute("value").trim();
					 * System.out.println("RP Value: " + RP);
					 */

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

				WebElement EditIcon = driver.findElement(
						By.xpath("//table[@id='vendortable']//tbody//tr//td[2]//following::td[6]//a[@title='Details']"));
				js.executeScript("arguments[0].click();", EditIcon);

				String VendorName = driver.findElement(By.xpath("(//div[@class='col-lg-8']//child::h2//b)[1]"))
						.getText();
				System.out.println("Vendar Name: "+VendorName);

				String APAccount = driver.findElement(By.xpath("//dt[normalize-space()='A/P Account']//following-sibling::dd[1]"))
						.getText().trim();
				System.out.println("A/P Account: " + APAccount);

				WebElement InfoTab = driver.findElement(By.xpath("//a[text()='Info']"));
				js.executeScript("arguments[0].click();", InfoTab);
				//	InfoTab.click();
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
	// @Ignore
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
	}
	@Test(priority = 19, dependsOnMethods = "ERPLoginPage")
	public void PurchaseOrderToInvoice() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		LocalDateTime TimeStamp = LocalDateTime.now();
		DateTimeFormatter DateTimeFormate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatedTimestamp = TimeStamp.format(DateTimeFormate);

		PurchaseOrder po = new PurchaseOrder(driver);

		driver.navigate().to(url + "Purchases/PurchaseOrderIndex");
		Thread.sleep(4000);
		System.out.println("*Purchase Form Page*");
		System.out.println();

		click(po.AddPurchaseOrder);
		Thread.sleep(3000);		

		String getExcelGstType = "";
		String getexcelOverAllDiscountType = "";
		String getExcelOverAllDiscountPercentage = "";
		String getExcelOverAllDiscountAmount = "";
		String getExcelGstPercentage = "";
		String getExcelCurrencyRate = "";
		String productPrice = "";
		String ExpDiscountProductPriceFormat = "";

		// double ZeroGstProductTotalAmount = 0;
		double ActDiscountProductPriceDouble = 0;
		double ExpSubTotal = 0;
		double ExpZeroGstProductamount = 0;
		double expectedDiscountAmount = 0;

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
				.sendKeys(excelData.ProductName + Keys.ENTER);

			} else if (excelData.Type.equalsIgnoreCase("Service")) {

				driver.findElement(By.xpath("//span[@id='select2-ServiceId-container']//following::input[@type='search']"))
				.sendKeys(excelData.ProductName + Keys.ENTER);

			} else if (excelData.Type.equalsIgnoreCase("Open")) {

				po.OpenProduct.sendKeys(excelData.ProductName + Keys.ENTER);

			}

			click(po.Quantity);
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

						}

					}
					break;
				}
			}
			System.out.println();

			//Qty:-
			Sendkeys(po.Quantity, excelData.Qty);

			//Price:-
			click(po.SGD);
			po.SGD.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(po.SGD, excelData.Price);
			Thread.sleep(1000);

			// Foc:-
			/*		if (excelData.Type.equalsIgnoreCase("Product")) {

				if (IsFOCManagementInPI == true) {
					System.out.println("IsFOCManagementInSO: " + IsFOCManagementInPI);
					click(po.IsFoc);
					Sendkeys(po.Foc, excelData.Foc);

				}

			} else if (excelData.Type.equalsIgnoreCase("Service")) {

				click(po.Foc);
				Sendkeys(po.Foc, excelData.Foc);

			} else {
				System.out.println("Foc Field Is Not Displayed");

			}*/


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

					expectedDiscountAmount = (excelPriceDouble - discAmtDouble1);

					Total = Total - discAmtDouble1;
					System.out.println("Discount Amount Total: "+expectedDiscountAmount);

				}

			}

			//Add Button:-
			click(po.AddButton);
			Thread.sleep(2000);
			click(po.Quantity);
			System.out.println();

			String amount = driver.findElement(By.xpath("//table[@id='PurchaseOrderTable']//tbody//tr//td[2]"
					+ "//div//textarea[contains(text(),'"+excelData.ProductName+"')]//following::td[@class='orderTotal']")).getText();
			double amountDouble = Double.parseDouble(amount);
			System.out.println("Actual Discount Amount: "+amountDouble);
			System.out.println("Expected Discount Amount: "+expectedDiscountAmount);
			System.out.println();	

			ExpSubTotal = ExpSubTotal + amountDouble;

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

		} else if (getOverAllDiscountType.equals("%")) {
			po.OverAllDiscount.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(po.OverAllDiscount, getExcelOverAllDiscountPercentage + Keys.ENTER);

		} else {
			System.out.println("No Over All Discount Amounr and Percentage");
		}
		System.out.println();

		// Sub Total Calculation:-
		System.out.println("*** Sub Total Calculation ***");
		String subTotalAmountString = driver.findElement(By.id("Subtotal")).getText();
		double subTotalAmountDouble = Double.parseDouble(subTotalAmountString);
		System.out.println("Actual Sub Total Amount: " + subTotalAmountDouble);
		System.out.println("Expected Sub Total Amount: " + ExpSubTotal);
		System.out.println();

		//Over All Discount Calculation:-
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

		// GST CALCULATION
		System.out.println("*** Grand Total Calculation With GST ***");

		// double ExpectedGstAmount = 0;
		// double afterExpectedGstAmount = 0;
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

		String ExpectedGstAmountFormat = String.format("%.2f", finalExpectedGstAmount);
		System.out.println("Expected Gst Amount is: " + ExpectedGstAmountFormat);

		String ActualGstAmount = driver.findElement(By.id("GST")).getAttribute("value");
		System.out.println("Actual Gst Amount is: " + ActualGstAmount);

		soft.assertEquals(ActualGstAmount, ExpectedGstAmountFormat, "Actual and Expected Gst Amount Mismatched");
		System.out.println();

		if (getExcelGstType.equalsIgnoreCase("Inclusive")) {

			finalExpectedGstAmount = 0;
			System.out.println("After Inclusive Gst Amount: " + finalExpectedGstAmount);

		}

		// GRAND TOTAL AMOUNT
		System.out.println("*** Grand Total Amount ***");
		double ExpectedGrandTotalAmount = (discountPercentageAmount + finalExpectedGstAmount);
		String ExpectedGrandTotalAmountFormat = String.format("%.2f", ExpectedGrandTotalAmount);
		System.out.println("Expected Grand Total Amount is: " + ExpectedGrandTotalAmountFormat);

		String finalTotalAmount = driver.findElement(By.xpath("//input[@id='Amount']")).getAttribute("value");
		double finalTotalAmountDouble = Double.parseDouble(finalTotalAmount);
		String finalTotalAmountFormat = String.format("%.2f", finalTotalAmountDouble);
		System.out.println("Actual Grand Total Amount is: " + finalTotalAmountFormat);
		System.out.println();

		// CURRENCY CALCULATION
		System.out.println("*** Grand Total Calculation With Currency ***");
		System.out.println("Sub Total in Double: " + finalTotalAmountDouble);

		String currencyName = po.CurrencyCode.getText().trim();
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

		//Convert Invoice Button:-
		click(po.ConvertInvoice);
		click(po.PopupAlertOk);
		Thread.sleep(3000);

		//Invoice No:-
		Thread.sleep(1000);
		click(po.InvoiceNo);
		Sendkeys(po.InvoiceNo, formatedTimestamp);
		Thread.sleep(3000);
		System.out.println("InvoiceNo :" + formatedTimestamp);
		System.out.println("Convert invoice Successfull");

		for (ExcelData excelData : excelDataList) {
			if (excelData.BatchProduct.equalsIgnoreCase("true")) {
				System.out.println("batch product: "+excelData.ProductName);

				int tableSize = driver.findElements(By.xpath("//table[@id='PurchaseTable']//tbody//tr[@class='productTR']")).size();
				for (int i = 1; i <= tableSize; i++) {
					
					String getBatchProductName = driver.findElement(By.xpath("(//a[@id='BatchFolder'])["+i+"]//preceding::td[7]"))
							.getAttribute("data-value");
					if (getBatchProductName.equalsIgnoreCase(excelData.BatchProduct)) {
						
						WebElement batchFiles = driver.findElement(By.xpath("//table[@id='PurchaseTable']//tbody//tr["+i+"]//td[2]"
								+ "//div//textarea[contains(text(),'"+getBatchProductName+"')]//following::td[7]//a[@id='BatchFolder']"));
						js.executeScript("arguments[0].click();", batchFiles);
						
					}

				}
			}
		}

		//Purchase Order Data Equals to Invoice:-

		String getActualProductName = driver.findElement(By.xpath("//table[@id='PurchaseTable']//tbody//tr//td[2]"))
				.getAttribute("data-value");
		System.out.println("Actual Product Name: "+getActualProductName);

		String getActualQty = driver.findElement(By.xpath("//table[@id='PurchaseTable']//tbody//tr//td[3]//input[@id='detailQty']"))
				.getAttribute("value");
		System.out.println("Actual Qty: "+getActualQty);

		String getActualPrice = driver.findElement(By.xpath("//table[@id='PurchaseTable']//tbody//tr//td[4]"))
				.getAttribute("data-value");
		System.out.println("Actual Price: "+getActualPrice);

		String getActualUom = driver.findElement(By.xpath("//table[@id='PurchaseTable']//tbody//tr//td[5]//p[@id='uomText']")).getText();
		System.out.println("Actual Uom: "+getActualUom);

		String getActualDiscount = driver.findElement(By.xpath("//table[@id='PurchaseTable']//tbody//tr//td[7]")).getAttribute("data-value");
		System.out.println("Actual Discount: "+getActualDiscount);

		String getActualAmount = driver.findElement(By.xpath("//table[@id='PurchaseTable']//tbody//tr//td[8]//p[@id='DetailPurchaseDetailTotal']")).getText();
		System.out.println("Actual Amount: "+getActualAmount);	

		//Save:-
		click(po.SaveButton);
		Thread.sleep(5000);
		System.out.println("Purchase Order Save Successfull");

		System.out.println("***");






	}


	@Ignore
	@Test(priority = 20, dependsOnMethods = "ERPLoginPage")
	public void PurchaseForms() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		PurchaseOrder po = new PurchaseOrder(driver);

		LocalDateTime TimeStamp = LocalDateTime.now();
		DateTimeFormatter DateTimeFormate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatedTimestamp = TimeStamp.format(DateTimeFormate);

		driver.navigate().to(url + "SalesPurchases/PurchaseOrder/Index");
		Thread.sleep(4000);
		System.out.println("*Purchase Form Page*");

		int DataSize = excelDataList.size();

		double ExpSubtotal = 0;

		String GstType = null;
		String DiscountMode;
		String DiscAmtorPercentage;
		int Size = 0;

		String VendorCode = null;

		for (int i = 0; i < DataSize; i++) {
			ExcelData excelData = excelDataList.get(i);

			boolean Vendorname = excelData.Vendor.isBlank();

			if (Vendorname == false) {

				System.out.println("Vendor Code: " + excelData.Vendor);
				if (i != 0 || Size == excelDataList.size()) {

					String SubTotal = driver.findElement(By.id("Subtotal")).getText().replace(",", "").trim();
					String ExpSubtotalString = String.format("%.2f", ExpSubtotal);

					System.out.println("Actual SubTotal : " + SubTotal);
					System.out.println("Expected Subtotal : " + ExpSubtotalString);

					soft.assertEquals(SubTotal, ExpSubtotalString,
							"Actual and Expected Subtotal Mismatched for the Vendor " + VendorCode);

					double ExpTotalWithGst = 0.0;
					double ExpGstAmt = 0.0;

					if (GstType.contains("Inclusive")) {

						ExpGstAmt = (ExpSubtotal * 9) / 109;
						ExpTotalWithGst = ExpSubtotal;

					} else if (GstType.contains("Exclusive")) {

						ExpGstAmt = (ExpSubtotal * 9) / 100;
						ExpTotalWithGst = ExpSubtotal + ExpGstAmt;
					}

					String GSTAmount = driver.findElement(By.id("FooterGST")).getText().replace(",", "").trim();
					System.out.println("##Gst Amount :" + GSTAmount);
					long roundActGstAmt = Math.round(Double.parseDouble(GSTAmount));
					double actGstAmtValue = roundActGstAmt;
					DecimalFormat df = new DecimalFormat("0.00");
					String formatActGstAmtValue = df.format(actGstAmtValue);
					System.out.println("Actual GST Amount :" + formatActGstAmtValue);

					long roundGSTAmount = Math.round(ExpGstAmt);
					double GstAmtvalue = roundGSTAmount;
					DecimalFormat df1 = new DecimalFormat("0.00");
					String formateGstAmtValue = df1.format(GstAmtvalue);

					System.out.println("Expected GST Amount :" + formateGstAmtValue);

					soft.assertEquals(formatActGstAmtValue.replace(",", "").trim(), formateGstAmtValue,
							"Actual and Expected GST Amount Mismatched for the vendor " + VendorCode);

					String FinalTotalAmount = driver.findElement(By.id("FooterTotal")).getText();
					System.out.println("FinalTotalAmount : " + FinalTotalAmount);

					System.out.println("***");

					Thread.sleep(4000);
					click(po.ConvertInvoice);
					Thread.sleep(2000);
					if (po.PopupAlertOk.isDisplayed() == true) {
						click(po.PopupAlertOk);
					}

					Thread.sleep(1000);
					click(po.InvoiceNo);
					Sendkeys(po.InvoiceNo, formatedTimestamp);
					Thread.sleep(3000);
					System.out.println("InvoiceNo :" + formatedTimestamp);
					System.out.println("Convert invoice Successfull");

					click(po.SaveButton);
					Thread.sleep(5000);
					System.out.println("Purchase Order Save Successfull");

					System.out.println("***");

				}

				ExpSubtotal = 0;

				driver.navigate().to(url + "SalesPurchases/PurchaseOrder/Create");

				Thread.sleep(7000);

				click(po.Vendor);
				Sendkeys(po.VendorSearch, excelData.Vendor + Keys.ENTER);
				Thread.sleep(1000);
				click(po.GSTType);
				Sendkeys(po.GSTTypeSearch, excelData.GstType + Keys.ENTER);

			}

			System.out.println("Is Vendor name Enabled: " + Vendorname);

			GstType = po.GSTType.getText().trim();

			VendorCode = po.Vendor.getText().trim();

			int Productsize = ProductDetailsList.size();

			String LPPfromProduct = null;

			for (int j = 0; j < Productsize; j++) {

				product product = ProductDetailsList.get(j);

				if (product.productCode.equals(excelData.ProductCode)) {

					LPPfromProduct = product.LPPrice;

					if (product.IsBase == false) {
						int PrUOMsize = ProductUOMDetailsList.size();

						for (int k = 0; k < PrUOMsize; k++) {
							ProductUOM productUOM = ProductUOMDetailsList.get(k);

							if (productUOM.productCode1.equals(excelData.ProductCode)
									&& productUOM.SubUOM.equalsIgnoreCase(excelData.Uom)) {

								LPPfromProduct = productUOM.LPP;
								break;
							}

						}

					}

					System.out.println("Product Code: " + excelData.ProductCode);

					DiscountMode = excelData.OverAllDiscountType;
					DiscAmtorPercentage = excelData.DiscountAmount;

					WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Qty")));
					quantity.click();
					Thread.sleep(2000);
					click(po.ChooseproductName);
					System.out.println("Product: " + excelData.ProductCode);
					Sendkeys(po.ProductSearch, excelData.ProductCode + Keys.ENTER);
					Thread.sleep(1000);
					quantity.click();
					click(po.UOM);
					Sendkeys(po.UOMSearch, excelData.Uom + Keys.ENTER);
					Thread.sleep(2000);
					quantity.click();
					Sendkeys(quantity, excelData.Qty);
					Thread.sleep(1000);
					click(po.SGD);
					po.SGD.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
					Sendkeys(po.SGD, excelData.Price);

					/*
					 * if (Double.parseDouble(LPPfromProduct) == 0.00) { click(po.SGD);
					 * po.SGD.sendKeys(Keys.CONTROL + "a" + Keys.DELETE); Sendkeys(po.SGD,
					 * excelData.Price); LPPfromProduct = excelData.Price;
					 * 
					 * }else if (Double.parseDouble(LPPfromProduct) > 0) {
					 * System.out.println("lpp value is present"); click(po.SGD);
					 * po.SGD.sendKeys(Keys.CONTROL + "a" + Keys.DELETE); Sendkeys(po.SGD,
					 * LPPfromProduct); }
					 */
					/*	if (IsEnableItemLevelDiscountInPurchase == true) {

						if (excelData.DiscountPercentage.isEmpty() == false) {
							click(po.DiscountPercentage);
							po.DiscountPercentage.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
							po.DiscountPercentage.sendKeys(excelData.DiscountPercentage);

						} else if (excelData.DiscountAmount.isEmpty() == false) {

							click(po.DiscountAmount);
							po.DiscountAmount.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
							po.DiscountAmount.sendKeys(excelData.DiscountAmount);

						}
					}*/

					click(po.AddButton);
					Thread.sleep(3000);

					/*
					 * String ActualLPPValue = driver .findElement(By.xpath(
					 * "(//table[@id='PurchaseOrderTable']//tbody//tr/td[4]//input)[1]"))
					 * .getAttribute("value").replace(",", "").trim();
					 * 
					 * System.out.println("Actual LPP Value :" + ActualLPPValue);
					 * System.out.println("Expected LPP Value :" + LPPfromProduct);
					 * 
					 * soft.assertEquals(ActualLPPValue.replace(",", "").trim(), LPPfromProduct,
					 * "Actual and Expected LPP Amount Mismatched for the Product Code " +
					 * excelData.ProductCode + " and for the Vendor " + excelData.VendorCode);
					 */

					String ActProductTotalAmt = driver
							.findElement(By.xpath("//table[@id='PurchaseOrderTable']//tbody//tr//td[8]")).getText().replace(",", "").trim();

					String originalPriceString = excelData.Price;
					String discountPriceString = ActProductTotalAmt;

					System.out.println("#LPPfromProduct:" + excelData.Price);
					System.out.println("#Price:" + excelData.Price);
					System.out.println("#Qty:" + excelData.Qty);

					double ExpProductAmount = Double.parseDouble(excelData.Price);
					double excelQtydouble = Double.parseDouble(excelData.Qty.replace(",", ""));
					double OriginalPrice = Double.parseDouble(excelData.Price);

					if (IsEnableItemLevelDiscountInPurchase == false) {
						ExpProductAmount = ExpProductAmount * excelQtydouble;
					}

					if (IsEnableItemLevelDiscountInPurchase == true) {

						if (excelData.DiscountPercentage.isEmpty() == false) {

							double ExptotalPrice = OriginalPrice * excelQtydouble;
							double DiscountAmt = excelQtydouble * (Double.parseDouble(excelData.DiscountPercentage) / 100);

							ExpProductAmount = DiscountAmt * OriginalPrice;
							ExpProductAmount = ExptotalPrice - ExpProductAmount;

							System.out.println("@Exp Total Aount With Discount " + ExpProductAmount);

						} else if (excelData.DiscountAmount.isEmpty() == false) {

							ExpProductAmount = (OriginalPrice * excelQtydouble)
									- Double.parseDouble(excelData.DiscountAmount);

						}
					}

					long roundExpAmt = Math.round(ExpProductAmount);
					DecimalFormat df = new DecimalFormat("0.00");
					String ExpProductAmountString = df.format(roundExpAmt);

					System.out.println("Actual Product Total Amount : " + ActProductTotalAmt);
					System.out.println("Expected Product Total Amount : " + ExpProductAmountString);

					soft.assertEquals(ActProductTotalAmt, ExpProductAmountString,
							"Actual and Expected Amount Mismatched for Product Code " + excelData.ProductCode
							+ " and Vendor " + excelData.Vendor);
					ExpSubtotal = Double.parseDouble(ExpProductAmountString) + ExpSubtotal;
					Size++;

					System.out.println("***");
				}
			}
		}
		System.out.println(Size);
		System.out.println(Size - 1);
		System.out.println(excelDataList.size());
		if (Size == excelDataList.size()) {

			String SubTotal = driver.findElement(By.id("Subtotal")).getText().replace(",", "").trim();
			String ExpSubtotalString = String.format("%.2f", ExpSubtotal);

			System.out.println("Actual SubTotal : " + SubTotal);
			System.out.println("Expected Subtotal : " + ExpSubtotalString);

			soft.assertEquals(SubTotal, ExpSubtotalString,
					"Actual and Expected Subtotal Mismatched for the Vendor " + VendorCode);

			double ExpTotalWithGst = 0.0;
			double ExpGstAmt = 0.0;

			System.out.println("GstType :" + GstType);

			if (GstType.contains("Inclusive")) {
				ExpGstAmt = (ExpSubtotal * 9) / 109;
				ExpTotalWithGst = ExpSubtotal;

			} else if (GstType.contains("Exclusive")) {
				ExpGstAmt = (ExpSubtotal * 9) / 100;
				ExpTotalWithGst = ExpSubtotal + ExpGstAmt;

			}

			String GSTAmount = driver.findElement(By.id("FooterGST")).getText().replace(",", "").trim();
			System.out.println("#Gst Amount :" + GSTAmount);
			long roundGstAmt = Math.round(Double.parseDouble(GSTAmount));
			double actGstAmt = roundGstAmt;
			DecimalFormat df = new DecimalFormat("0.00");
			String formatActGstAmt = df.format(actGstAmt);
			System.out.println("Actual GST Amount :" + formatActGstAmt);

			long roundGSTAmount = Math.round(ExpGstAmt);
			double GstAmtvalue = roundGSTAmount;
			DecimalFormat df1 = new DecimalFormat("0.00");
			String formateGstAmtValue = df1.format(GstAmtvalue);

			System.out.println("Expected GST Amount :" + formateGstAmtValue);

			soft.assertEquals(GSTAmount, formateGstAmtValue,
					"Actual and Expected GST Amount Mismatched for the vendor " + VendorCode);

			String FinalTotalAmount = driver.findElement(By.id("FooterTotal")).getText();
			System.out.println("FinalTotalAmount : " + FinalTotalAmount);

			System.out.println("***");

			Thread.sleep(4000);
			click(po.ConvertInvoice);
			Thread.sleep(2000);
			if (po.PopupAlertOk.isDisplayed() == true) {
				click(po.PopupAlertOk);
			}

			Thread.sleep(1000);
			click(po.InvoiceNo);
			Sendkeys(po.InvoiceNo, formatedTimestamp);
			Thread.sleep(3000);
			System.out.println("InvoiceNo :" + formatedTimestamp);
			System.out.println("Convert invoice Successfull");

			click(po.SaveButton);
			Thread.sleep(5000);
			System.out.println("Purchase Order Save Successfull");

			System.out.println("***");
		}
	}

	class ProductValidation {

		String ProductCode;
		String UOM;
		int ProductQty;

		public ProductValidation(String ProductCode, String UOM, int ProductQty) {
			super();

			this.ProductCode = ProductCode;
			this.UOM = UOM;
			this.ProductQty = ProductQty;
		}
	}

	ArrayList<ProductValidation> ProductValidationsList = new ArrayList<>();

	@Ignore
	@Test(priority = 22, dependsOnMethods = "ERPLoginPage")
	public void ProductValidations() throws InterruptedException {

		System.out.println("*Product Validations*");

		Set<String> Products = ProductUomMap.keySet();
		for (String Product : Products) {
			List<String> uom = ProductUomMap.get(Product);
			int uomSize1 = uom.size();
			for (int i = 0; i < uomSize1; i++) {
				int ProductQty = 0;

				String UOM = uom.get(i);
				int DataSize = excelDataList.size();
				for (int j = 0; j < DataSize; j++) {
					ExcelData excelData = excelDataList.get(j);

					if (excelData.ProductCode.equals(Product) && excelData.Uom.equals(UOM)) {

						ProductQty = ProductQty + Integer.parseInt(excelData.Qty);

					}

				}

				System.out.println("Product Code :" + Product);
				System.out.println("UOM :" + UOM);
				System.out.println("Product Qty :" + ProductQty);

				ProductValidation ProdValidationDetails = new ProductValidation(Product, UOM, ProductQty);
				ProductValidationsList.add(ProdValidationDetails);

				System.out.println("***");
			}

		}

	}

	@Ignore
	@Test(priority = 24, dependsOnMethods = "ERPLoginPage")
	public void ExpectedProductList() throws InterruptedException {

		driver.navigate().to(url + "SalesPurchases/Product");
		Thread.sleep(5000);

		System.out.println("*Expected Product List*");

		PurchaseOrder po = new PurchaseOrder(driver);

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		Set<String> ProductsandUOMS = ProductUomMap.keySet();
		for (String Product : ProductsandUOMS) {
			System.out.println("Product :" + Product);

			WebElement productcode = driver.findElement(By.xpath("(//input[@id='SearchString'])[1]"));
			Thread.sleep(1000);
			productcode.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			productcode.sendKeys(Product);
			Thread.sleep(1000);
			WebElement fetchBtn = driver.findElement(By.xpath("//input[@id='searchstring' and @value='Fetch']"));
			js.executeScript("arguments[0].click();", fetchBtn);
			Thread.sleep(3000);
			WebElement DetailsIcon = driver
					.findElement(By.xpath("//table[@id='producttable']//tbody//tr//td[1][normalize-space()='" + Product
							+ "']//following::td[9]//a[@title='Details']"));
			DetailsIcon.click();

			String CurrentStock = driver
					.findElement(By.xpath("//dt[normalize-space()='Current Stock - HQ']//following-sibling::dd[1]"))
					.getText();
			System.out.println("Actual Product Stock: "+ CurrentStock);

			Thread.sleep(2000);

			int size = excelDataList.size();
			System.out.println("size :" + size);
			for (int i = 0; i < size; i++) {
				ExcelData excelData1 = excelDataList.get(i);
				String ProductCode1 = excelData1.ProductCode;
				System.out.println("product Code :" + ProductCode1);

				List<String> UOMList = ProductUomMap.get(Product);
				int UOMListSize = UOMList.size();

				for (int j = 0; j < UOMListSize; j++) {
					String UOM = UOMList.get(j);
					System.out.println("#UOM :"+UOM);

					int ProductValidationsListSize = ProductValidationsList.size();
					System.out.println("ProductValidationsListSize :"+ProductValidationsListSize);

					for (int k = 0; k < ProductValidationsListSize; k++) {
						ProductValidation productValidation = ProductValidationsList.get(k);
						System.out.println("productValidation :"+productValidation);

						if (Product.equals(productValidation.ProductCode) && UOM.equals(productValidation.UOM)) {

							int ProductSize = ProductDetailsList.size();
							System.out.println("ProductSize :"+ProductSize);

							for (int l = 0; l < ProductSize; l++) {
								product ProductDetails = ProductDetailsList.get(l);
								System.out.println("ProductDetails :"+ProductDetails);
								if (ProductDetails.productCode.equals(Product)) {
									if (ProductDetails.IsBase == true) {
										System.out.println("Is Base");
										int PreviousStock = Integer.parseInt(ProductDetails.currentStockValue);
										int ExpectedStock = PreviousStock + productValidation.ProductQty;
										System.out.println("ExpectedStock :" + ExpectedStock);

									} else {
										int ProductUomSize = ProductUOMDetailsList.size();
										for (int m = 0; m < ProductUomSize; m++) {
											ProductUOM productUOMDetails = ProductUOMDetailsList.get(m);

											if (productUOMDetails.productCode1.equals(Product)
													&& UOM.equals(productUOMDetails.SubUOM)) {
												System.out.println("Is Carton");
												int previousCurStock = Integer.parseInt(productUOMDetails.CurStock);
												int ExpectedCurStock = previousCurStock + productValidation.ProductQty;
												System.out.println("ExpectedCurStock :" + ExpectedCurStock);


											}

										}

									}

								}

							}

						}
						break;
					}

				}
				//	System.out.println("UOMListSize :" + UOMListSize);
				//	System.out.println("UOMList :" + UOMList);

				System.out.println("***");

			}

			Thread.sleep(2000);
			click(po.Back);

		}
	}

	@Ignore
	@Test(priority = 26, dependsOnMethods = "ERPLoginPage")
	public void ProductMovementPage() throws InterruptedException {

		driver.navigate().to(url + "SalesPurchases/Product/ProductMovementsIndex");
		Thread.sleep(4000);

		System.out.println("*Product Movement Page*");

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		LocalDateTime TimeStamp = LocalDateTime.now();
		DateTimeFormatter DateTimeFormate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatedTimestamp = TimeStamp.format(DateTimeFormate);

		Set<String> ProductsandUOMS = ProductUomMap.keySet();
		for (String Product : ProductsandUOMS) {
			System.out.println("Product :" + Product);

			driver.findElement(By.xpath("//span[@id='select2-productid-container']")).click();
			WebElement search = driver.findElement(By.xpath(
					"(//span[@id='select2-productid-container']//following::input[@class='select2-search__field'])[1]"));
			search.sendKeys(Product + Keys.ENTER);
			Thread.sleep(3000);
			driver.findElement(By.id("searchstring")).click();

			String balanceQty = driver
					.findElement(By.xpath("(//div[@id='TableData']//following::table[1]//tbody//tr//td[4])[1]"))
					.getText();
			System.out.println("ActualStock:" + balanceQty);

			int size = excelDataList.size();
			for (int i = 0; i < size; i++) {
				ExcelData excelData1 = excelDataList.get(i);
				String ProductCode1 = excelData1.ProductCode;

				List<String> UOMList = ProductUomMap.get(Product);
				int UOMListSize = UOMList.size();

				for (int j = 0; j < UOMListSize; j++) {
					String UOM = UOMList.get(j);

					int ProductValidationsListSize = ProductValidationsList.size();
					for (int k = 0; k < ProductValidationsListSize; k++) {

						ProductValidation productValidation = ProductValidationsList.get(k);

						if (Product.equals(productValidation.ProductCode) && UOM.equals(productValidation.UOM)) {
							int ProductSize = ProductDetailsList.size();
							for (int l = 0; l < ProductSize; l++) {
								product ProductDetails = ProductDetailsList.get(l);
								if (ProductDetails.productCode.equals(Product)) {
									if (ProductDetails.IsBase == true) {
										System.out.println("Is Base");
										int PreviousStock = Integer.parseInt(ProductDetails.currentStockValue);

										int ExpectedStock = PreviousStock + productValidation.ProductQty;
										System.out.println("ExpectedStock " + ExpectedStock);

									} else {
										int ProductUomSize = ProductUOMDetailsList.size();
										for (int m = 0; m < ProductUomSize; m++) {
											ProductUOM productUOMDetails = ProductUOMDetailsList.get(m);

											if (productUOMDetails.productCode1.equals(Product)
													&& UOM.equals(productUOMDetails.SubUOM)) {
												System.out.println("Is Carton");
												int previousCurStock = Integer.parseInt(productUOMDetails.CurStock);
												int ExpectedCurStock = previousCurStock + productValidation.ProductQty;
												System.out.println("ExpectedCurStock :" + ExpectedCurStock);

											}

										}

									}

								}

							}

						}
					}

				}

				/*
				 * Thread.sleep(2000); driver.navigate().back();
				 */
				System.out.println("***");

			}
		}

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
