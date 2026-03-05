package com.Demo;



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
import com.PomClass.Product;
import com.PomClass.SalesInvoice;
import com.PomClass.SalesOrder;
import com.PomClass.SystemSettings;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DemoProgram extends BaseClass {

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

		} else if (env.equals("prod")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();

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

		Object data[][] = Util1.getTestData("C:\\Adaptive\\ERP\\SalesOrderExcelTemp1.xlsx", "Sheet1");
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


	public class ProductData {
		private String ProductCode;
		private String ProductName;
		private String ProductType;
		private String SellingPrice;
		private String AveragePrice;
		private String LPP;
		private String CurrentStock;
		private String TotalStock;
		private String Type;

		public ProductData(String ProductCode, String ProductName, String ProductType, String SellingPrice,
				String AveragePrice, String LPP, String CurrentStock, String TotalStock, String Type) {
			super();

			this.ProductCode = ProductCode;
			this.ProductName = ProductName;
			this.ProductType = ProductType;
			this.SellingPrice = SellingPrice;
			this.AveragePrice = AveragePrice;
			this.LPP = LPP;
			this.CurrentStock = CurrentStock;
			this.TotalStock = TotalStock;
			this.Type = Type;
		}
	}

	@SuppressWarnings("unused")
	class ProductUomData {

		private String ProductCode;
		private String SubUom;
		private String UomCurrentStock;
		private String UomLPP;
		private String UomSP;
		private String UomAvgCost;
		private String Type;

		public ProductUomData(String ProductCode, String SubUom, String UomCurrentStock, String UomLPP, String UomSP,
				String UomAvgCost, String Type) {
			super();

			this.ProductCode = ProductCode;
			this.SubUom = SubUom;
			this.UomCurrentStock = UomCurrentStock;
			this.UomLPP = UomLPP;
			this.UomSP = UomSP;
			this.UomAvgCost = UomAvgCost;
			this.Type = Type;
		}
	}

	public void getProductData(String ProductCode, String ProductName, String ProductType, String SellingPrice,
			String AveragePrice, String LPP, String CurrentStock, String TotalStock, String Type) {
		ProductData ListData = new ProductData(ProductCode, ProductName, ProductType, SellingPrice, AveragePrice, LPP,
				CurrentStock, TotalStock, Type);
		ProductDataList.add(ListData);
	}

	public void getProductUomData(String ProductCode, String SubUom, String UomCurrentStock, String UomLPP,
			String UomSP, String UomAvgCost, String Type) {
		ProductUomData ListData = new ProductUomData(ProductCode, SubUom, UomCurrentStock, UomLPP, UomSP, UomAvgCost, Type);
		ProductUomDataList.add(ListData);
	}

	ArrayList<ProductData> ProductDataList = new ArrayList<>();
	ArrayList<ProductUomData> ProductUomDataList = new ArrayList<>();
	//@Ignore
	public void stockCheckInProduct(String Type) throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Product pro = new Product(driver);
		driver.navigate().to(url + "SalesPurchases/Product");
		Thread.sleep(4000);

		String ActTotalStock = "";
		String ActCurrentStock = "";
		String ActProCode = "";
		String ActProName = "";
		String StrActProType = "";
		String ProUom = "";
		String SellingPrice = "";
		String AverageCost = "";
		String LastPurchasePrice = "";

		for (String Product : ExcelUniqueProductDataSet) {
			Thread.sleep(3000);
			click(pro.Clear);
			click(pro.FindProductorCode);
			Sendkeys(pro.FindProductorCode, Product);
			click(pro.Fetch);
			Thread.sleep(3000);
			click(pro.DetailsIcon);

			String ProCode = driver.findElement(By.xpath("//div[@class='col-lg-7']//h2//child::small")).getText();
			ActProCode = ProCode.split(" - UOM")[0];
			// System.out.println("Product Code :" + ActProCode);

			ActProName = driver.findElement(By.xpath("//div[@class='col-lg-7']//h2//child::b")).getText();
			// System.out.println("Product Name :" + ActProName);

			ActCurrentStock = driver
					.findElement(
							By.xpath("(//div//dl//child::dt[contains(text(),'Current Stock - HQ')]//following::dd)[1]"))
					.getText();
			// System.out.println("Current Stock :" + ActCurrentStock);

			ActTotalStock = driver
					.findElement(By.xpath("//div//dl//child::dt[contains(text(),'Total Stock')]//following::dd[1]"))
					.getText();
			// System.out.println("Total Stock :" + ActTotalStock);

			js.executeScript("arguments[0].scrollIntoView(true);", pro.InfoTab);
			click(pro.InfoTab);
			Thread.sleep(2000);

			String Carton = driver
					.findElement(By.xpath("(//div//dl//child::dt[contains(text(),'Carton')]//following::dd)[1]"))
					.getText();
			// System.out.println("Carton :" + Carton);

			SellingPrice = driver
					.findElement(By.xpath("(//div//dl//child::dt[contains(text(),'Selling Price')]//following::dd)[1]"))
					.getText();
			// System.out.println("Selling Price :" + SellingPrice);

			AverageCost = driver
					.findElement(By.xpath("(//div//dl//child::dt[contains(text(),'Average Cost')]//following::dd)[1]"))
					.getText();
			// System.out.println("Average Cost :" + AverageCost);

			LastPurchasePrice = driver
					.findElement(By
							.xpath("(//div//dl//child::dt[contains(text(),'Last Purchase Price')]//following::dd)[1]"))
					.getText();
			// System.out.println("Last Purchase Price :" + LastPurchasePrice);

			click(pro.StockTab);
			Thread.sleep(2000);
			ProUom = driver.findElements(By.id("ProductPartialUOM")).size() > 0 ? "True" : "False";

			if (ProUom.equals("True")) {
				List<WebElement> rows = driver.findElements(By.xpath("//table[@id='ProductPartialUOM']//tbody//tr"));
				for (int i = 1; i <= rows.size(); i++) {
					String SubUOM = driver
							.findElement(By.xpath("(//table[@id='ProductPartialUOM']//tbody//tr//td[2])[" + i + "]"))
							.getText();
					// String
					// SubUomUnits=driver.findElement(By.xpath("//tbody[@id='headertbody']//tr["+i+"]//td[1]//input[@id='Units']")).getAttribute("value");
					// System.out.println(SubUomUnits);
					String UomCurrentStock = driver
							.findElement(By.xpath("(//table[@id='ProductPartialUOM']//tbody//tr//td[4])[" + i + "]"))
							.getText();
					String UomLPP = driver.findElement(By.xpath(
							"(//table[@id='ProductPartialUOM']//tbody//tr//td[5]//input[@id='LastPurchasePrice'])[" + i
							+ "]"))
							.getAttribute("value");
					String UOMSP = driver.findElement(
							By.xpath("(//table[@id='ProductPartialUOM']//tbody//tr//td[6]//input[@id='SellingPrice'])["
									+ i + "]"))
							.getAttribute("value");
					String UOMAvgCost = driver.findElement(
							By.xpath("(//table[@id='ProductPartialUOM']//tbody//tr//td[8]//input[@id='AverageCost'])["
									+ i + "]"))
							.getAttribute("value");

					getProductUomData(ActProCode, SubUOM, UomCurrentStock, UomLPP, UOMSP, UOMAvgCost, Type);
				}

			}
			// System.out.println("***");

			if (Carton.equals("True")) {
				StrActProType = "Carton";
			} else if (Carton.equals("False") && ProUom.equals("True")) {
				StrActProType = "NonCarton";
			} else if (Carton.equals("False") && ProUom.equals("False")) {
				StrActProType = "Base";
			} else {
				System.out.println("Product Type is not correct");
			}
			getProductData(ActProCode, ActProName, StrActProType, SellingPrice, AverageCost, LastPurchasePrice,
					ActCurrentStock, ActTotalStock, Type);
			click(pro.Back);
		}
		System.out.println("************************************");
		System.out.println("Stock of the products before the sales");
		int ProductDatatSize = ProductDataList.size();
		for (int i = 0; i < ProductDatatSize; i++) {
			ProductData data = ProductDataList.get(i);
			System.out.println("S.No: " + (i + 1));
			System.out.println("Product Code: " + data.ProductCode);
			System.out.println("Product Name: " + data.ProductName);
			System.out.println("Product Type: " + data.ProductType);
			System.out.println("Product Selling Price: " + data.SellingPrice);
			System.out.println("Product Average Cost: " + data.AveragePrice);
			System.out.println("Product LPP: " + data.LPP);
			System.out.println("Current Stock: " + data.CurrentStock);
			System.out.println("Total Stock: " + data.TotalStock);
			System.out.println();
		}


	}		

	public class salesOrderData {

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
	@Test(priority = 8)
	public void SalesToInvoice() throws InterruptedException {

		stockCheckInProduct("Before");

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

			int ProductDatatSize = ProductDataList.size();
			for (int j = 0; j < ProductDatatSize; j++) {
				ProductData data = ProductDataList.get(j);
				if (data.ProductName.equalsIgnoreCase(excelData.ProductName)) {

					System.out.println("Current Stock: " + data.CurrentStock);
					System.out.println("Product Name: "+data.ProductName);

					soft.assertEquals(data.CurrentStock, qohStock, 
							"Actual and Expected QOH Mismatched for Product: " + data.ProductName);

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

			if (IsFOCManagementInSO == true) {
				System.out.println("IsFOCManagementInSO: " + IsFOCManagementInSO);
				click(so.IsFoc);
				Sendkeys(so.Foc, excelData.Foc);

			} else {
				System.out.println("Foc Field Is Not Displayed");

			}
			click(so.Price);
			so.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(so.Price, excelData.Price);
			Thread.sleep(1000);


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


				if (excelData.DiscPercent.isEmpty() == false) {
					double discountPercentDouble = Double.parseDouble(excelData.DiscPercent);

					double discountPercentPrice = (discountPercentDouble / 100) * priceDouble;
					double discountAmountRound = Math.round(discountPercentPrice);
					double discAmountforPercent = discountAmountRound / 100;

					Total = (priceDouble - discAmountforPercent) * qtyDouble;					
					System.out.println("Discount Percent Total: "+Total);

				} else if (excelData.DiscAmt.isEmpty() == false) {
					double discAmtDouble = Double.parseDouble(excelData.DiscAmt);

					Total = (priceDouble - discAmtDouble) * qtyDouble;
					System.out.println("Discount Amount Total: "+Total);
				}

			} else {

				if (excelData.DiscPercent.isEmpty() == false) {

					double discPercentDouble = Double.parseDouble(excelData.DiscPercent);
					double discountPerAmt = (discPercentDouble / 100) * Total;
					double discPerAmtRound = Math.round(discountPerAmt * 100);
					double discAmtforPer = discPerAmtRound / 100;

					Total = Total - discAmtforPer;		

				} else if (excelData.DiscAmt.isEmpty() == false) {

					double discAmtDouble1 = Double.parseDouble(excelData.DiscAmt);

					Total = Total - discAmtDouble1;

				}

			} 

			click(so.Add);
			Thread.sleep(2000);
			click(so.Qty);

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
		//	BigDecimal expectedGstAmt = new BigDecimal(ExpectedGstAmount).setScale(2, RoundingMode.HALF_UP);
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
		/*
		 * String saveAlertText = driver.findElement(By.id("popup_message")).getText();
		 * System.out.println("Save Alert Text is: "+saveAlertText); Thread.sleep(1000);
		 */
		click(so.PopupOk);    
		//	String currentStockAlert = driver.findElement(By.id("popup_message")).getText();
		//	System.out.println(currentStockAlert);
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

	@Test(priority = 9)
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


	@Ignore
	@Test(priority = 12)
	public void SalesOrderToCustomizedInvoice() throws InterruptedException {

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


			click(so.Gst);
			WebElement GstSearchInput = driver.findElement(By.xpath("//span[@id='select2-GSTTypeId-container']//following::input[@type='search']"));
			GstSearchInput.sendKeys(excelData.GstType +Keys.ENTER);

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

			Sendkeys(so.Qty, excelData.Qty);

			if (IsFOCManagementInSO == true) {
				System.out.println("IsFOCManagementInSO: " + IsFOCManagementInSO);
				click(so.IsFoc);
				Sendkeys(so.Foc, excelData.Foc);

			} else {
				System.out.println("Foc Field Is Not Displayed");

			}
			click(so.Price);
			so.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(so.Price, excelData.Price);
			Thread.sleep(1000);

			double Total = 0;

			double priceDouble = Double.parseDouble(excelData.Price);
			double qtyDouble = Double.parseDouble(excelData.Qty);

			if (excelData.UnitDisc.equalsIgnoreCase("True")) {


				if (excelData.DiscPercent.isEmpty() == false) {
					double discountPercentDouble = Double.parseDouble(excelData.DiscPercent);

					double discountPercentPrice = (discountPercentDouble / 100) * priceDouble;
					double discountAmountRound = Math.round(discountPercentPrice);
					double discAmountforPercent = discountAmountRound / 100;

					Total = (priceDouble - discAmountforPercent) * qtyDouble;					
					System.out.println("Discount Percent Total: "+Total);

				} else if (excelData.DiscAmt.isEmpty() == false) {
					double discAmtDouble = Double.parseDouble(excelData.DiscAmt);

					Total = (priceDouble - discAmtDouble) * qtyDouble;
					System.out.println("Discount Amount Total: "+Total);
				}

			} else {

				if (excelData.DiscPercent.isEmpty() == false) {

					double discPercentDouble = Double.parseDouble(excelData.DiscPercent);
					double discountPerAmt = (discPercentDouble / 100) * Total;
					double discPerAmtRound = Math.round(discountPerAmt * 100);
					double discAmtforPer = discPerAmtRound / 100;

					Total = Total - discAmtforPer;		

				} else if (excelData.DiscAmt.isEmpty() == false) {

					double discAmtDouble1 = Double.parseDouble(excelData.DiscAmt);

					Total = Total - discAmtDouble1;

				}

			} 

			click(so.Add);
			Thread.sleep(2000);
			click(so.Qty);



		}


	}
	
	@Ignore
	@Test(priority = 12)
	public void SalesOrderToConvertInvoice() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.navigate().to(url + "SalesPurchases/SalesOrderIndex");
		Thread.sleep(5000);
		SalesOrder so = new SalesOrder(driver);

		click(so.AddOrder);
		Thread.sleep(3000);

		int SOSize = dataList.size();
		for (int i = 0; i < SOSize; i++) {
			ExcelData excelData = dataList.get(i);

			if (excelData.Customer.isEmpty() == false) {

				click(so.Customer);
				driver.findElement(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
				.sendKeys(excelData.Customer + Keys.ENTER);
				Thread.sleep(2000);

			}

			if (IsSalesManManagement == true) {
				System.out.println("Sales Man Is Displayed");

			} else {
				System.out.println("Sales Man Is Not Displayed");
			}

			if (IsWarehouseManagement == true) {
				System.out.println("Warehouse Is Displayed");

			} else {
				System.out.println("Warehouse Is Not Displayed");
			}

			click(so.Product);
			driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
			.sendKeys(excelData.ProductName + Keys.ENTER);
			click(so.Qty);
			click(so.Uom);
			driver.findElement(By.xpath("//span[@id='select2-UOMId-container']//following::input[@type='search']"))
			.sendKeys(excelData.Uom + Keys.ENTER);
			Sendkeys(so.Qty, excelData.Qty);

			if (IsFOCManagementInSO == true) {
				click(so.IsFoc);
				Sendkeys(so.Foc, excelData.Foc);

			} else {
				System.out.println("Foc Field Is Not Displayed");
			}
			click(so.Price);
			so.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(so.Price, excelData.Price);
			Thread.sleep(1000);

			if (IsEnableItemLevelDiscountInSales == true) {
				System.out.println("DIscount Field Is Displayed");
				System.out.println("Unit Discount Field Is Displayed");

			} else {
				System.out.println("Discount Field Not Displayed");
				System.out.println("Unit Discount Field Not Displayed");
			}

			click(so.Add);
			Thread.sleep(2000);
			click(so.Qty);

		}

		Thread.sleep(2000);
		click(so.SaveDropDown);
		click(so.Save);
		Thread.sleep(3000);

		String orderNo = driver.findElement(By.id("OrderNumber")).getAttribute("value");
		System.out.println("Sales Order No :" + orderNo);
		Thread.sleep(1000);
		click(so.Back);

		Thread.sleep(2000);
		click(so.FindOrderNo);
		Sendkeys(so.FindOrderNo, orderNo);
		click(so.Fetch);

		Thread.sleep(2000);
		WebElement CheckBox = driver
				.findElement(By.xpath("//table[@id='ordertable']//tbody//tr//td[3][contains(text(),'" + orderNo
						+ "')]//preceding::td[2]//input[@id='check']"));
		CheckBox.click();

		Thread.sleep(2000);
		click(so.Convert);
		String alertText1 = driver.findElement(By.id("popup_message")).getText();
		System.out.println("Alert Message: " + alertText1);
		click(so.PopupOk);
		String alertText2 = driver.findElement(By.id("popup_message")).getText();
		System.out.println("Alert Message: " + alertText2);
		click(so.PopupOk);

		System.out.println("***");

	}

	@Ignore
	@Test(priority = 14)
	public void DirectSalesInvoice() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		SalesInvoice si = new SalesInvoice(driver);

		driver.navigate().to(url + "SalesPurchases/SalesInvoice");
		Thread.sleep(4000);
		System.out.println("**Sales Invoice Page**");

		click(si.AddInvoice);
		Thread.sleep(2000);

		int excelDataListSize = dataList.size();
		System.out.println("Excel Data List Size Is:" + excelDataListSize);
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = dataList.get(i);
			Thread.sleep(2000);

			if (excelData.Customer.isEmpty() == false) {

				click(si.Customer);
				driver.findElement(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
				.sendKeys(excelData.Customer + Keys.ENTER);
				Thread.sleep(2000);
			}

			click(si.ChooseProduct);
			driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
			.sendKeys(excelData.ProductName + Keys.ENTER);
			click(si.Qty);

			click(si.ChooseUom);
			List<WebElement> subUomOptions = driver.findElements(By.xpath(
					"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
			for (WebElement option : subUomOptions) {
				if (option.getText().trim().equals(excelData.Uom)) {
					option.click();
					break;
				}
			}

			click(si.Qty);
			Sendkeys(si.Qty, excelData.Qty);
			click(si.Price);
			si.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(si.Price, excelData.Price);
			Thread.sleep(2000);
			click(si.Add);
			Thread.sleep(1000);

			if (excelData.Batch.equalsIgnoreCase("true")) {

				driver.findElement(By.xpath("(//div//strong[contains(.,'" + excelData.ProductName
						+ "')]//following::button[text()='Add'])[1]")).click();

			}

			click(si.Qty);

		}

		Thread.sleep(2000);
		js.executeScript("arguments[0].scrollIntoView(true);", si.Save);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", si.Save);
		System.out.println("*Sales Invoice Save Successfull*");
		System.out.println("***");

	}




	@Test(priority = 30, dependsOnMethods = "ERPLoginPage")
	private void Exception() throws InterruptedException {
		soft.assertAll();

	} 




}


