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
import com.PomClass.PurchaseOrder;
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

		PurchaseOrder po = new PurchaseOrder(driver);

		Sendkeys(po.CompanyCode, "UITDEMO1");
		Sendkeys(po.UserName, "Kiran01");
		Sendkeys(po.Password, "Adaptive*123");
		click(po.LoginButton);
		Thread.sleep(2000);

		String ActURL = driver.getCurrentUrl();
		boolean equals = url.equalsIgnoreCase(ActURL);

		if (equals == false) {

			Navigate_to(ActURL);
			Sendkeys(po.CompanyCode, "UITDEMO1");
			Sendkeys(po.UserName, "Kiran02");
			Sendkeys(po.Password, "Adaptive*123");
			Thread.sleep(1000);
			click(po.LoginButton);

		}

		String ActURL1 = driver.getCurrentUrl();
		boolean equals1 = url.equalsIgnoreCase(ActURL1);

		if (equals1 == false) {

			Navigate_to(ActURL1);
			Sendkeys(po.CompanyCode, "UITDEMO1");
			Sendkeys(po.UserName, "Kiran03");
			Sendkeys(po.Password, "Adaptive*123");
			Thread.sleep(1000);
			click(po.LoginButton);

		}

		System.out.println("*ERP Login Page*");
	}

	@DataProvider
	public Object[][] Util2() {
		Object[][] data = Util1.getTestData("C:\\Adaptive\\ERP\\PurchaseOrder5.xlsx", "Sheet1");
		return data;

	}
	
	@SuppressWarnings("unused")
	public
	class ExcelData {

		private String VendorCode;
		private String InvoiceNo;
		private String GstType;
		private String CurrencyCode;
		private String Terms;
		private String CurrencyRate;
		private String Warehouse;
		private String ProductCode;
		private String UOM;
		private String Qty;
		private String Price;
		private String UnitDisc;
		private String Discount;
		private String DiscountAmount;
		private String Batch;
		private String BatchNumber;
		private String BatchLocation;
		private String BatchMfgDate;
		private String BatchValidPeriod;
		private String DiscountModeForAllProduct;
		private String DiscountAmountPercentage;
		private String RoundOff;
		private String GSTPercentage;

		public ExcelData(String VendorCode, String InvoiceNo, String GstType, String CurrencyCode, String Terms,
				String CurrencyRate, String Warehouse, String ProductCode, String UOM, String Qty, String Price,
				String UnitDisc, String Discount, String DiscountAmount, String Batch, String BatchNumber,
				String BatchLocation, String BatchMfgDate, String BatchValidPeriod, String DiscountModeForAllProduct,
				String DiscountAmountPercentage, String RoundOff, String GSTPercentage) {
			super();

			this.VendorCode = VendorCode;
			this.InvoiceNo = InvoiceNo;
			this.GstType = GstType;
			this.CurrencyCode = CurrencyCode;
			this.Terms = Terms;
			this.CurrencyRate = CurrencyRate;
			this.Warehouse = Warehouse;
			this.ProductCode = ProductCode;
			this.UOM = UOM;
			this.Qty = Qty;
			this.Price = Price;
			this.UnitDisc = UnitDisc;
			this.Discount = Discount;
			this.DiscountAmount = DiscountAmount;
			this.Batch = Batch;
			this.BatchNumber = BatchNumber;
			this.BatchLocation = BatchLocation;
			this.BatchMfgDate = BatchMfgDate;
			this.BatchValidPeriod = BatchValidPeriod;
			this.DiscountModeForAllProduct = DiscountModeForAllProduct;
			this.DiscountAmountPercentage = DiscountAmountPercentage;
			this.RoundOff = RoundOff;
			this.GSTPercentage = GSTPercentage;

		}

	}

	ArrayList<ExcelData> dataList = new ArrayList<>();

	List<String> ProductList = new ArrayList<String>();
	Set<String> ProductSet = new LinkedHashSet<String>();
	List<String> VendorList = new ArrayList<String>();
	Set<String> VendorSet = new LinkedHashSet<String>();

	@Test(priority = 4, dataProvider = "Util2", dependsOnMethods = "ERPLoginPage")
	public void GetData(String VendorCode, String InvoiceNo, String GstType, String CurrencyCode, String Terms,
			String CurrencyRate, String Warehouse, String ProductCode, String UOM, String Qty, String Price,
			String UnitDisc, String Discount, String DiscountAmount, String Batch, String BatchNumber,
			String BatchLocation, String BatchMfgDate, String BatchValidPeriod, String DiscountModeForAllProduct,
			String DiscountAmountPercentage, String RoundOff, String GSTPercentage) {

		ExcelData data = new ExcelData(VendorCode, InvoiceNo, GstType, CurrencyCode, Terms, CurrencyRate, Warehouse,
				ProductCode, UOM, Qty, Price, UnitDisc, Discount, DiscountAmount, Batch, BatchNumber, BatchLocation,
				BatchMfgDate, BatchValidPeriod, DiscountModeForAllProduct, DiscountAmountPercentage, RoundOff,
				GSTPercentage);

		dataList.add(data);
		ProductSet.add(ProductCode);
		VendorSet.add(VendorCode);

	}

	Map<String, List<String>> ProductUomMap = new HashedMap<>();

	@Test(priority = 7, dependsOnMethods = "ERPLoginPage")
	public void ProductUOMData() throws InterruptedException {
		ProductList.addAll(ProductSet);

		int ProductListSize = ProductList.size();
		System.out.println("Product size :" + ProductListSize);

		for (int i = 0; i < ProductListSize; i++) {
			Set<String> uomSet = new LinkedHashSet<>();
			String Productcode = ProductList.get(i);
			System.out.println("Productcode :" + Productcode);
			List<String> uomList1 = new ArrayList<>();
			int dataListSize = dataList.size();
			System.out.println("Data Size: " + dataListSize);
			for (int j = 0; j < dataListSize; j++) {
				ExcelData excelData = dataList.get(j);

				if (excelData.ProductCode.equals(Productcode)) {
					System.out.println("UOM: " + excelData.UOM);
					uomSet.add(excelData.UOM);

				}

			}
			uomList1.addAll(uomSet);
			ProductUomMap.put(Productcode, uomList1);

		}

		System.out.println("######");

		Set<String> Products = ProductUomMap.keySet();
		for (String Product : Products) {
			System.out.println("Product :" + Product);
			List<String> uom = ProductUomMap.get(Product);
			int uomSize = uom.size();
			for (int i = 0; i < uomSize; i++) {
				String string = uom.get(i);
				System.out.println("UOM: " + string);

				System.out.println("***");

			}

		}

	}

	private boolean IsEnableItemLevelDiscountInPurchase;

	@Ignore
	@Test(priority = 10, dependsOnMethods = "ERPLoginPage")
	public void SystemSettingsPage() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		PurchaseOrder po = new PurchaseOrder(driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.navigate().to(url + "SystemSetting");
		Thread.sleep(4000);
		System.out.println("*System Settings Page*");

		po.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(po.SystemsSettingsParamCodeSearchField, "IsEnableItemLevelDiscountInPurchase" + Keys.ENTER);
		Thread.sleep(1000);
		click(po.FetchButton);
		Thread.sleep(1000);
		String IsEnableItemLevelDiscountInPurchaseString = driver
				.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[5])[1]")).getText();
		IsEnableItemLevelDiscountInPurchase = Boolean.parseBoolean(IsEnableItemLevelDiscountInPurchaseString);
		System.out.println("IsEnableItemLevelDiscountInPurchase Value in System Setting :" + IsEnableItemLevelDiscountInPurchase);

		System.out.println("***");

	}
	@SuppressWarnings("unused")
	public
	class Product {

		private String productCode1;
		// private String productNameValue;
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

		public Product(String productCode1, String productNameValue, String departmentValue, String categoryValue,
				String brandValue, String profitMarginValue, String marginToleranceValue, String vendorNameValue,
				String purchaseCOAValue, String salesCOAValue, String currentStockValue, String uomValue,
				boolean IsCarton, String CartonPrice, boolean IsNonCarton, boolean IsBase, String SellingPrice,
				String LPPrice, String RetailPrice) {
			super();

			this.productCode1 = productCode1;
			// this.productNameValue = productNameValue;
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
	public
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

	//@Ignore
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

			/*
			 * WebElement productName = driver
			 * .findElement(By.xpath("//b[normalize-space()='KitKat Choco Cake/CTN']"));
			 * String productNameValue = productName.getText();
			 * System.out.println("Product Name: " + productNameValue);
			 */

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

	//@Ignore
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
	
	@SuppressWarnings("unused")
	class Vendor {

		private String GSTType;
		private String APAccount;

		public Vendor(String GSTType, String APAccount) {
			super();

			this.GSTType = GSTType;
			this.APAccount = APAccount;
		}

	}

	ArrayList<Vendor> vendorDetailsList = new ArrayList<>();

	//@Ignore
	@Test(priority = 19, dependsOnMethods = "ERPLoginPage")
	public void VendorPage() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		VendorList.addAll(VendorSet);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.navigate().to(url + "SalesPurchases/Vendor");
		Thread.sleep(4000);
		System.out.println("*Vendor Page*");

		int VendorListSize = VendorList.size();
		System.out.println("VendorListSize: " + VendorListSize);

		for (int j = 0; j < VendorListSize; j++) {

			String VendorCode = VendorList.get(j);

			if (VendorCode != null) {

				WebElement VendorSearchField = driver.findElement(By.id("SearchString"));
				VendorSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				VendorSearchField.sendKeys(VendorCode);
				Thread.sleep(1000);

				WebElement fetchBtn = driver.findElement(By.id("searchstring"));
				js.executeScript("arguments[0].click();", fetchBtn);
				Thread.sleep(3000);

				WebElement EditIcon = driver.findElement(
						By.xpath("//table[@id='vendortable']//tbody//tr//td[2]//following::td[6]//a[@title='Edit']"));
				js.executeScript("arguments[0].click();", EditIcon);

				String GSTType = driver
						.findElement(By.xpath(
								"//label[text()='GST Type']//following::span[@id='select2-GSTTypeId-container']"))
						.getAttribute("title").trim();
				System.out.println("GST Type: " + GSTType);

				String APAccount = driver.findElement(By.xpath(
						"//label[normalize-space()='A/P Account']//following::span[@id='select2-ChartOfAccountsId-container']"))
						.getText().trim();
				System.out.println("A/P Account: " + APAccount);
				System.out.println("***");

				Vendor VendorDetails = new Vendor(GSTType, APAccount);
				vendorDetailsList.add(VendorDetails);

				driver.navigate().back();

			}

		}

	}

	//@Ignore
	@Test(priority = 22, dependsOnMethods = "ERPLoginPage")
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

		int DataSize = dataList.size();

		double ExpSubtotal = 0;

		String GstType = null;
		String DiscountMode;
		String DiscAmtorPercentage;
		int Size = 0;

		String VendorCode = null;

		for (int i = 0; i < DataSize; i++) {
			ExcelData excelData = dataList.get(i);

			boolean Vendorname = excelData.VendorCode.isBlank();

			if (Vendorname == false) {

				System.out.println("Vendor Code: " + excelData.VendorCode);
				if (i != 0 || Size == dataList.size()) {

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
					if (po.PopupAlert.isDisplayed() == true) {
						click(po.PopupAlert);
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
				Sendkeys(po.VendorSearch, excelData.VendorCode + Keys.ENTER);
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

				Product product = ProductDetailsList.get(j);

				if (product.productCode1.equals(excelData.ProductCode)) {

					LPPfromProduct = product.LPPrice;

					if (product.IsBase == false) {
						int PrUOMsize = ProductUOMDetailsList.size();

						for (int k = 0; k < PrUOMsize; k++) {
							ProductUOM productUOM = ProductUOMDetailsList.get(k);

							if (productUOM.productCode1.equals(excelData.ProductCode)
									&& productUOM.SubUOM.equalsIgnoreCase(excelData.UOM)) {

								LPPfromProduct = productUOM.LPP;
								break;
							}

						}

					}

					System.out.println("Product Code: " + excelData.ProductCode);

					DiscountMode = excelData.DiscountModeForAllProduct;
					DiscAmtorPercentage = excelData.DiscountAmountPercentage;

					WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Qty")));
					quantity.click();
					Thread.sleep(2000);
					click(po.ChooseproductName);
					System.out.println("Product: " + excelData.ProductCode);
					Sendkeys(po.ProductSearch, excelData.ProductCode + Keys.ENTER);
					Thread.sleep(1000);
					quantity.click();
					click(po.UOM);
					Sendkeys(po.UOMSearch, excelData.UOM + Keys.ENTER);
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
					if (IsEnableItemLevelDiscountInPurchase == true) {

						if (excelData.Discount.isEmpty() == false) {
							click(po.Discount);
							po.Discount.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
							po.Discount.sendKeys(excelData.Discount);

						} else if (excelData.DiscountAmount.isEmpty() == false) {

							click(po.DiscountAmount);
							po.DiscountAmount.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
							po.DiscountAmount.sendKeys(excelData.DiscountAmount);

						}
					}

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

						if (excelData.Discount.isEmpty() == false) {

							double ExptotalPrice = OriginalPrice * excelQtydouble;
							double DiscountAmt = excelQtydouble * (Double.parseDouble(excelData.Discount) / 100);

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
									+ " and Vendor " + excelData.VendorCode);
					ExpSubtotal = Double.parseDouble(ExpProductAmountString) + ExpSubtotal;
					Size++;

					System.out.println("***");
				}
			}
		}
		System.out.println(Size);
		System.out.println(Size - 1);
		System.out.println(dataList.size());
		if (Size == dataList.size()) {

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
			if (po.PopupAlert.isDisplayed() == true) {
				click(po.PopupAlert);
			}

			Thread.sleep(1000);
			click(po.InvoiceNo);
			Sendkeys(po.InvoiceNo, formatedTimestamp);
			Thread.sleep(3000);
			System.out.println("InvoiceNo :" + formatedTimestamp);
			System.out.println("Convert invoice Successfull 1");

			click(po.SaveButton);
			Thread.sleep(5000);
			System.out.println("Purchase Order Save Successfull 1");

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

	// @Ignore
	@Test(priority = 25, dependsOnMethods = "ERPLoginPage")
	public void ProductValidations() throws InterruptedException {

		System.out.println("*Product Validations*");

		Set<String> Products = ProductUomMap.keySet();
		for (String Product : Products) {
			List<String> uom = ProductUomMap.get(Product);
			int uomSize1 = uom.size();
			for (int i = 0; i < uomSize1; i++) {
				int ProductQty = 0;

				String UOM = uom.get(i);
				int DataSize = dataList.size();
				for (int j = 0; j < DataSize; j++) {
					ExcelData excelData = dataList.get(j);

					if (excelData.ProductCode.equals(Product) && excelData.UOM.equals(UOM)) {

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

	// @Ignore
	@Test(priority = 28, dependsOnMethods = "ERPLoginPage")
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

			int size = dataList.size();
			 System.out.println("size :" + size);
			for (int i = 0; i < size; i++) {
				ExcelData excelData1 = dataList.get(i);
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
								Product ProductDetails = ProductDetailsList.get(l);
								System.out.println("ProductDetails :"+ProductDetails);
								if (ProductDetails.productCode1.equals(Product)) {
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

	//@Ignore
	@Test(priority = 34, dependsOnMethods = "ERPLoginPage")
	public void ProductMovementPage() throws InterruptedException {

		driver.navigate().to(url + "SalesPurchases/Product/ProductMovementsIndex");
		Thread.sleep(4000);

		System.out.println("*Product Movement Page*");

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		PurchaseOrder po = new PurchaseOrder(driver);

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

			int size = dataList.size();
			for (int i = 0; i < size; i++) {
				ExcelData excelData1 = dataList.get(i);
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
								Product ProductDetails = ProductDetailsList.get(l);
								if (ProductDetails.productCode1.equals(Product)) {
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
