package com.StockAdjustment;

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
import com.PomClass.Login;
import com.PomClass.Product;
import com.PomClass.ProductMovement;

import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductAssemblyTest extends BaseClass{

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

		Object data[][] = Util1.getTestData("C:\\Adaptive\\Automation\\Bizapp\\ProductAssembly.xlsx", "Sheet1");
		return data;

	}

	
	class ExcelData {

		private String AssemblyType;
		private String Type;
		private String FromProduct;
		private String FromUom;
		private String FromQty;
		private String FromBatchProduct;
		private String ToProduct;
		private String ToUom;
		private String ToQty;
		private String ToBatchProduct;
		private String ToBatchNumber;

		public ExcelData(String AssemblyType, String Type, String FromProduct, String FromUom, String FromQty, 
				String FromBatchProduct, String ToProduct, String ToUom, String ToQty, String ToBatchProduct, String ToBatchNumber) {
			super();

			this.AssemblyType = AssemblyType;
			this.Type = Type;
			this.FromProduct = FromProduct;
			this.FromUom = FromUom;
			this.FromQty = FromQty;
			this.FromBatchProduct = FromBatchProduct;
			this.ToProduct = ToProduct;
			this.ToUom = ToUom;
			this.ToQty = ToQty;
			this.ToBatchProduct = ToBatchProduct;
			this.ToBatchNumber = ToBatchNumber;
		}
	}

	ArrayList<ExcelData> excelDataList = new ArrayList<>();
	List<String> ProductList = new ArrayList<String>();
	Set<String> ProductSet = new LinkedHashSet<String>();

	@Test(priority = 4, dataProvider = "Util1", dependsOnMethods = "ERPLoginPage")
	public void GetData(String AssemblyType, String Type, String FromProduct, String FromUom, String FromQty, 
			String FromBatchProduct, String ToProduct, String ToUom, String ToQty, String ToBatchProduct, String ToBatchNumber) {

		ExcelData data = new ExcelData(AssemblyType, Type, FromProduct, FromUom, FromQty, 
				FromBatchProduct, ToProduct, ToUom, ToQty, ToBatchProduct, ToBatchNumber);


		excelDataList.add(data);
		ProductSet.add(FromProduct);
		ProductSet.add(ToProduct);

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

	@Ignore
	@Test(priority = 6, dependsOnMethods = "ERPLoginPage")
	public void ProductPage() throws InterruptedException {

		ProductList.addAll(ProductSet);
		driver.navigate().to(url + "SalesPurchases/Product");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("*Product Details Page*");

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

	@Ignore
	@Test(priority = 8, dependsOnMethods = "ERPLoginPage")
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

	@Test(priority = 10, dependsOnMethods = "ERPLoginPage")
	public void ProductAssembly() {

		driver.navigate().to(url +"SalesPurchases/ProductAssemblies");
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		com.PomClass.ProductAssembly pa = new com.PomClass.ProductAssembly(driver);

		wait.until(ExpectedConditions.visibilityOf(pa.Create)).click();

		int excelDataListsize = excelDataList.size();
		for (int i = 0; i < excelDataListsize; i++) {
			ExcelData excelData = excelDataList.get(i);

			click(pa.AssemblyType);
			WebElement assemblyTypeSearch = driver.findElement(By.xpath("//span[@id='select2-AssemblyTypeId-container']//following::input[@type='search']"));
			assemblyTypeSearch.click();
			assemblyTypeSearch.sendKeys(excelData.AssemblyType +Keys.ENTER);

			click(pa.Type);
			WebElement typeSearch = driver.findElement(By.xpath("//span[@id='select2-Type-container']//following::input[@type='search']"));
			typeSearch.click();
			typeSearch.sendKeys(excelData.Type +Keys.ENTER);

			click(pa.FromProduct);
			WebElement fromProductSearch = driver.findElement(By.xpath("//span[@id='select2-FromProductId-container']//following::input[@type='search']"));
			fromProductSearch.click();
			fromProductSearch.sendKeys(excelData.FromProduct +Keys.ENTER);

			click(pa.FromUom);
			WebElement fromUomSearch = driver.findElement(By.xpath("//span[@id='select2-FromUOMId-container']//following::input[@type='search']"));
			fromUomSearch.click();
			fromUomSearch.sendKeys(excelData.FromUom +Keys.ENTER);

			click(pa.FromAdd);

			WebElement fromQtyField = driver.findElement(By.xpath
					("//table[@id='packageTable']//tbody//tr//td[contains(text(),'"+excelData.FromProduct+" ')]//following::td[6]//input[@id='myQty']"));
			fromQtyField.click();
			fromQtyField.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
			fromQtyField.sendKeys(excelData.FromQty +Keys.ENTER);

			if (excelData.FromBatchProduct.equalsIgnoreCase("true")) {
				
				WebElement batchFile = driver.findElement(By.xpath
						("//table[@id='packageTable']//tbody//tr//td[contains(text(),'"+excelData.FromProduct+" ')]//following::td[7]//a[@class='fa fa-folder-open Popup']"));
				batchFile.click();

				String totalQty = driver.findElement(By.xpath
						("//strong[contains(text(),'"+excelData.FromProduct+"')]//following::input[@id='BatchQty']")).getAttribute("value");
				System.out.println("totalQty is: "+totalQty);

				WebElement batchQty = driver.findElement(By.xpath
						("//strong[contains(text(),'"+excelData.FromProduct+"')]//following::input[@id='Qty']"));
				batchQty.click();
				batchQty.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
				batchQty.sendKeys(totalQty);

				WebElement batchAdd = driver.findElement(By.xpath
						("//strong[contains(text(),'"+excelData.FromProduct+"')]//following::button[text()='Add']"));
				batchAdd.click();

			}

			click(pa.ToProduct);
			WebElement toProductSearch = driver.findElement(By.xpath("//span[@id='select2-ToProductId-container']//following::input[@type='search']"));
			toProductSearch.click();
			toProductSearch.sendKeys(excelData.ToProduct +Keys.ENTER);

			click(pa.FromUom);
			WebElement toUomSearch = driver.findElement(By.xpath("//span[@id='select2-ToUOMId-container']//following::input[@type='search']"));
			toUomSearch.click();
			toUomSearch.sendKeys(excelData.ToUom +Keys.ENTER);

			click(pa.ToAdd);

			WebElement toQtyField = driver.findElement(By.xpath
					("//table[@id='assemblyTable']//tbody//tr//td[text()='"+excelData.ToProduct+"']//following::td[4]//input[@id='Qty']"));
			toQtyField.click();
			toQtyField.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
			toQtyField.sendKeys(excelData.ToQty +Keys.ENTER);

			if (excelData.ToBatchProduct.equalsIgnoreCase("true")) {

				String totalQty = driver.findElement(By.xpath
						("//strong[contains(text(),'"+excelData.ToProduct+"')]//following::input[@id='BatchQty']")).getAttribute("value");
				System.out.println("totalQty is: "+totalQty);
				
				WebElement batchNoField = driver.findElement(By.xpath("//strong[contains(text(),'"+excelData.ToProduct+"')]//following::input[@id='BatchNumber']"));
				batchNoField.click();
				batchNoField.sendKeys(excelData.ToBatchNumber +Keys.ENTER);

				WebElement batchQty = driver.findElement(By.xpath
						("//strong[contains(text(),'"+excelData.FromProduct+"')]//following::input[@id='Qty']"));
				batchQty.click();
				batchQty.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
				batchQty.sendKeys(totalQty);

				WebElement batchAdd = driver.findElement(By.xpath
						("//strong[contains(text(),'"+excelData.FromProduct+"')]//following::button[text()='Add']"));
				batchAdd.click();

			}


		} // Excel data list loop

		click(pa.Completed);
		System.out.println("** Product Assembly Completed Successfull **");


	} // Method loop

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

	@Test(priority = 12, dependsOnMethods = "ERPLoginPage")
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

				if (excelData.FromUom.equals(uomData.UomCodeValue) || excelData.ToUom.equals(uomData.UomCodeValue)) {

					doubleExcelQty = Double.parseDouble(excelData.FromQty);
					doubleExcelQty = Double.parseDouble(excelData.ToQty);
					doubleUomUnit = Double.parseDouble(uomData.UomUnits);

					multipleQty = doubleExcelQty * doubleUomUnit;
					System.out.println("multipleQty is: "+multipleQty);
					break;
				}	

			}  // Uom details list loop

			int ProductDetailsListSize = ProductDetailsList.size();
			for (int k = 0; k < ProductDetailsListSize; k++) {
				product productData = ProductDetailsList.get(k);

				double calculateStockDouble = 0;
				String calculateStock = "0";

				if (productData.productName.equalsIgnoreCase(excelData.FromProduct)) {

					String productName = excelData.FromProduct;

					if (productData.IsCarton) {

						double multipleBoxStock = 0;
						double doubleLooseCurrentStock = 0;

						String[] splitCurrentStockValue = productData.currentStockValue.split("/");
						for (String currentStock : splitCurrentStockValue) {
							if (currentStock.contains("B")) {
								String replaceAllBoxCurrentStock = currentStock.replaceAll("[A-Za-z]", "");
								double doubleBoxCurrentStock = Double.parseDouble(replaceAllBoxCurrentStock);

								multipleBoxStock = doubleBoxCurrentStock * 10;
								//	System.out.println("multipleBoxStock is: "+multipleBoxStock);


							} else if (currentStock.contains("L")) {
								String replaceAllLooseCurrentStock = currentStock.replaceAll("[A-Za-z]", "");
								doubleLooseCurrentStock = Double.parseDouble(replaceAllLooseCurrentStock);
								//	System.out.println("doubleLooseCurrentStock is: "+doubleLooseCurrentStock);

							} // Current stock loop

							double additionBoxandLooseStock = multipleBoxStock + doubleLooseCurrentStock;
							calculateStockDouble = additionBoxandLooseStock + multipleQty;
							calculateStockDouble = calculateStockDouble - multipleQty;

						}		

					} else {

						double doubleCurrentStock = Double.parseDouble(productData.currentStockValue);
						calculateStockDouble = doubleCurrentStock + multipleQty;
						calculateStockDouble = calculateStockDouble - multipleQty;

					}

					calculateStock = String.valueOf(calculateStockDouble);

					if (productData.IsCarton) {						

						double diviedStock = calculateStockDouble / 10;
						String stringCalculateStock = String.valueOf(diviedStock);						
						String[] split = stringCalculateStock.split("\\.");
						String boxQty = split[0];
						String looseQty = "0";

						if (split.length > 1) {
							looseQty = split[1];
						}

						calculateStock = boxQty +" B/"+ looseQty +" L";					

						System.out.println("Product Name: "+productName);
						System.out.println("calculateCartonStock is: "+calculateStock);


					} else {

						System.out.println("Product Name: "+productName);
						System.out.println("CalculateBaseandNonCartonStock is: "+calculateStockDouble);

					}

					System.out.println();

					StockCalculation StockCalculation = new StockCalculation(calculateStock, productName);
					StockCalculationList.add(StockCalculation);

				}

			} // Product data loop	

		} // Excel data list loop

		System.out.println("** Stock Claculation Completed **");
		System.out.println();

	} // Stock adjustment method

	@Ignore
	@Test(priority = 14, dependsOnMethods = "ERPLoginPage")
	public void ExpectedProduct() throws InterruptedException {

		driver.navigate().back();
		driver.navigate().refresh();
		Thread.sleep(2000);
		driver.navigate().to(url + "SalesPurchases/Product");
		System.out.println("*Product Details Page*");

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		Product prod = new Product(driver);

		Thread.sleep(5000);
		for (String product : ProductSet) {

			WebElement productcode = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//input[@value='Fetch']//preceding::input[@placeholder='Find a product or code ']")));
			Thread.sleep(1000);
			productcode.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			productcode.sendKeys(product);
			Thread.sleep(1000);
			click(prod.Fetch);
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

					System.out.println("Actual Current Stock: "+afterCurrentStockValue);
					System.out.println("Expected current Stock: "+stock.calculateStock);
					System.out.println();

					soft.assertEquals(afterCurrentStockValue, stock.calculateStock, 
							"Actual and Expected Product Stock Mismatched for Product "+stock.productName);

				}			

			} // Stock calculation loop

			click(prod.Back);
			Thread.sleep(2000);

		}

		System.out.println();
	} // Expected product method


	@Ignore
	@Test(priority = 16, dependsOnMethods = "ERPLoginPage")
	public void ProductMovementPage() throws InterruptedException {

		driver.navigate().to(url + "SalesPurchases/Product/ProductMovementsIndex");
		System.out.println("*Product Movement Page*");

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		ProductMovement pm = new ProductMovement(driver);

		Thread.sleep(5000);		
		for (String product : ProductSet) {

			click(pm.ChooseProduct);
			WebElement productcode = driver.findElement(
					By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"));
			Thread.sleep(1000);
			productcode.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			productcode.sendKeys(product + Keys.ENTER);
			Thread.sleep(1000);
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

						System.out.println("Product Movement Name: "+ trimProductName);
						System.out.println("Actual Product Movement Stock: "+ balanceQty);
						System.out.println("Expected Product Movement Stock: "+ replaceProductStock);


						soft.assertEquals(balanceQty, replaceProductStock,
								"Actual and Expected Product Movement Stock Mismatched for Product "
										+ trimProductName);

					} else {

						System.out.println("Product Movement Name: "+ trimProductName);
						System.out.println("Actual Product Movement Stock: "+ balanceQty);
						System.out.println("Expected Product Movement Stock: "+stock.calculateStock);


						soft.assertEquals(balanceQty, stock.calculateStock,
								"Actual and Expected Product Movement Stock Mismatched for Product "
										+ trimProductName);

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



} // Main class 
