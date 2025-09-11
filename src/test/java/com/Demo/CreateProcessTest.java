package com.Demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BaseClass.BaseClass;
import com.Demo.CreateProcessTest.ExcelData;
import com.PomClass.PurchaseOrder;
import com.PomClass.SalesOrder;
import com.Utility.Util1;
import com.PomClass.StockAdjustment;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProcessTest extends BaseClass {

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
		Object[][] data = Util1.getTestData("C:\\Adaptive\\ERP\\SalesOrder.xlsx", "Sheet1");
		return data;

	}
	
	@SuppressWarnings("unused")
	class ExcelData {

		private String CustomerCode;
		private String CurrencyCode;
		private String GstType;
		private String Terms;
		private String CurrencyRate;
		private String ProductService;
		private String UOM;
		private String Qty;
		private String Price;
		private String BatchProduct;
		private String IsSpecialPriceCheckbox;
		private String CtnSplPrice;
		private String PcsSplPrice;
		private String UnitDisc;
		private String DiscountPer;
		private String DiscountAmt;
		private String DiscountModeForAllProduct;
		private String GSTPercentage;

		public ExcelData(String CustomerCode, String CurrencyCode, String GstType, String Terms, String CurrencyRate,
				String ProductService, String UOM, String Qty, String Price, String BatchProduct,
				String IsSpecialPriceCheckbox, String CtnSplPrice, String PcsSplPrice, String UnitDisc,
				String DiscountPer, String DiscountAmt, String DiscountModeForAllProduct, String GSTPercentage) {
			super();

			this.CustomerCode = CustomerCode;
			this.CurrencyCode = CurrencyCode;
			this.GstType = GstType;
			this.Terms = Terms;
			this.CurrencyRate = CurrencyRate;
			this.ProductService = ProductService;
			this.UOM = UOM;
			this.Qty = Qty;
			this.Price = Price;
			this.BatchProduct = BatchProduct;
			this.IsSpecialPriceCheckbox = IsSpecialPriceCheckbox;
			this.CtnSplPrice = CtnSplPrice;
			this.PcsSplPrice = PcsSplPrice;
			this.UnitDisc = UnitDisc;
			this.DiscountPer = DiscountPer;
			this.DiscountAmt = DiscountAmt;
			this.DiscountModeForAllProduct = DiscountModeForAllProduct;
			this.GSTPercentage = GSTPercentage;

		}

	}

	ArrayList<ExcelData> dataList = new ArrayList<>();

	@Test(priority = 4, dataProvider = "Util2", dependsOnMethods = "ERPLoginPage")
	public void GetData(String CustomerCode, String CurrencyCode, String GstType, String Terms, String CurrencyRate,
			String ProductService, String UOM, String Qty, String Price, String BatchProduct,
			String IsSpecialPriceCheckbox, String CtnSplPrice, String PcsSplPrice, String UnitDisc, String DiscountPer,
			String DiscountAmt, String DiscountModeForAllProduct, String GSTPercentage) {

		ExcelData data = new ExcelData(CustomerCode, CurrencyCode, GstType, Terms, CurrencyRate, ProductService, UOM,
				Qty, Price, BatchProduct, IsSpecialPriceCheckbox, CtnSplPrice, PcsSplPrice, UnitDisc, DiscountPer,
				DiscountAmt, DiscountModeForAllProduct, GSTPercentage);

		dataList.add(data);

	}

	//@Ignore
	@Test(priority = 7, dependsOnMethods = "ERPLoginPage")
	public void SalesOrdertoInvoice() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		SalesOrder so = new SalesOrder(driver);

		driver.navigate().to(url + "SalesPurchases/SalesOrderIndex");
		Thread.sleep(4000);
		System.out.println("**Sales Order Page**");

		click(so.AddOrder);
		Thread.sleep(2000);

		int excelDataListSize = dataList.size();
		System.out.println("Excel Data List Size Is:" + excelDataListSize);
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = dataList.get(i);
			Thread.sleep(2000);

			if (excelData.CustomerCode.isEmpty() == false) {

				click(so.Customer);
				driver.findElement(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
						.sendKeys(excelData.CustomerCode + Keys.ENTER);
				Thread.sleep(2000);

			}

			click(so.Product);
			driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
					.sendKeys(excelData.ProductService + Keys.ENTER);
			Thread.sleep(1000);

			click(so.Qty);
			Thread.sleep(1000);

			click(so.Uom);
			driver.findElement(
					By.xpath("//span[@id='select2-UOMId-container']//following::input[@class='select2-search__field']"))
					.sendKeys(excelData.UOM + Keys.ENTER);
			Thread.sleep(2000);

			click(so.Qty);
			Sendkeys(so.Qty, excelData.Qty);

			click(so.Price);
			so.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(so.Price, excelData.Price);
			Thread.sleep(2000);

			click(so.Add);
			Thread.sleep(2000);
			click(so.Uom);

		}

		js.executeScript("arguments[0].scrollIntoView(true);", so.ConvertInvoice);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", so.ConvertInvoice);
		Thread.sleep(2000);
		click(so.PopupOk);
		Thread.sleep(3000);

		int excelDataListSize1 = dataList.size();
		for (int j = 0; j < excelDataListSize1; j++) {
			ExcelData excelData = dataList.get(j);

			if (excelData.BatchProduct.equalsIgnoreCase("true")) {

				WebElement batchFile = driver.findElement(By.xpath(""
						+ "(//table[@id='SalesTable']//tbody//tr//td[2][normalize-space()= '" + excelData.ProductService
						+ "']//following::td[11]//a[@class='fa fa-folder-open Popup'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true);", batchFile);
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", batchFile);

				String ctnQtyValue = driver.findElement(By.xpath("(//div//strong[contains(text(),'"
						+ excelData.ProductService + "')]//following::input[@id='BQty'])[1]")).getAttribute("value");
				System.out.println("B.Qty Is:" + ctnQtyValue);
				String pcsQtyValue = driver.findElement(By.xpath("(//div//strong[contains(text(),'"
						+ excelData.ProductService + "')]//following::input[@id='LQty'])[1]")).getAttribute("value");
				System.out.println("L.Qty Is:" + pcsQtyValue);
				Thread.sleep(1000);

				if (!ctnQtyValue.equals("0")) {

					WebElement bQty = driver.findElement(By.xpath("(//div//strong[contains(text(),'"
							+ excelData.ProductService + "')]//following::input[@id='BulkQty'])[1]"));
					js.executeScript("arguments[0].click();", bQty);
					bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
					bQty.sendKeys(ctnQtyValue);

				}

				WebElement lQty = driver.findElement(By.xpath("(//div//strong[contains(text(), '"
						+ excelData.ProductService + "')]//following::input[@id='LooseQty'])[1]"));
				js.executeScript("arguments[0].click();", lQty);
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(pcsQtyValue);
				driver.findElement(By.xpath("(//div//strong[contains(.,'" + excelData.ProductService
						+ "')]//following::button[text()='Add'])[1]")).click();
				Thread.sleep(3000);
			}

		}

	//	click(so.Save);
		System.out.println("Sales Invoice Save Successfull");
		System.out.println("***");

	}

	// @Ignore
	@Test(priority = 10, dependsOnMethods = "ERPLoginPage")
	public void StockAdjustment() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		StockAdjustment sa = new StockAdjustment(driver);

		driver.navigate().to(url + "SalesPurchases/StockAdjustmentV2");
		Thread.sleep(4000);
		System.out.println("**Stock Adjustment Page**");

		click(sa.CreateV2);
		Thread.sleep(2000);

		js.executeScript("document.body.style.zoom='90%'");

		int ExcelDataListSize = dataList.size();
		System.out.println("Excel Data List Size Is:" + ExcelDataListSize);
		for (int i = 0; i < ExcelDataListSize; i++) {
			ExcelData excelData = dataList.get(i);

			click(sa.StockAdjustmentTypeV2);
			driver.findElement(
					By.xpath("//span[@id='select2-StockAdjustmentTypeId-container']//following::input[@type='search']"))
					.sendKeys("Opening" + Keys.ENTER);

			click(sa.ChooseProductV2);
			driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
					.sendKeys(excelData.ProductService + Keys.ENTER);
			Thread.sleep(1000);

			click(sa.UOMV2);
			driver.findElement(By.xpath("//span[@id='select2-UOMId-container']//following::input[@type='search']"))
					.sendKeys(excelData.UOM + Keys.ENTER);
			Thread.sleep(1000);

			WebElement bQtyBox = driver.findElement(By.xpath("//input[@id='BuQty']"));
			String isDisabled = bQtyBox.getAttribute("disabled");
			System.out.println("isDisabled " + isDisabled);
			
			if ("true".equalsIgnoreCase(isDisabled)) {
				click(sa.LQtyV2);
				Sendkeys(sa.LQtyV2, "100");
				Thread.sleep(2000);

			} else {
				click(sa.BQtyV2);
				Sendkeys(sa.BQtyV2, "100");
				Thread.sleep(1000);

			}

			click(sa.AddV2);

		}

		int excelDataListSize1 = dataList.size();
		for (int j = 0; j < excelDataListSize1; j++) {
			ExcelData excelData = dataList.get(j);
			int xpathIndex = j+1;

			if (excelData.BatchProduct.equalsIgnoreCase("true")) {

				String bQtyValue = driver.findElement(
						By.xpath("(//table[@id='StockAdjustmentDetails']//tbody//tr//td[7]//a[@id='linkBatch']//preceding::td[3]//input[@id='BQty'])["+xpathIndex+"]"))
						.getAttribute("value");
				System.out.println("B.Qty Is:" + bQtyValue);
				String lQtyValue = driver.findElement(
						By.xpath("(//table[@id='StockAdjustmentDetails']//tbody//tr//td[7]//a[@id='linkBatch']//preceding::td[3]//input[@id='LQty'])["+xpathIndex+"]"))
						.getAttribute("value");
				System.out.println("L.Qty Is:" + lQtyValue);
				Thread.sleep(1000);
				
				WebElement batchFile = driver.findElement(
						By.xpath("(//table[@id='StockAdjustmentDetails']//tbody//tr//td[2]//div//following::td[5]//a[@id='linkBatch'])["+xpathIndex+"]"));
				js.executeScript("arguments[0].scrollIntoView(true);", batchFile);
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", batchFile);
				
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				int BqtySize = driver.findElements(
						By.xpath("(//label[text()='"+excelData.ProductService+"']//following::input[@id='batchLQty'])[1]//preceding::input[1][@id='batchBQty']")).size();
				System.out.println("BQty Size Is: "+BqtySize);
				
				if (BqtySize >= 1) {
					System.out.println("#");
					WebElement BQty = driver.findElement(By.xpath("//table[@id='StockAdjustmentBatchDetails']//tbody//tr//td//input[@id='batchBQty']"));
					BQty.click();
					BQty.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
					BQty.sendKeys(bQtyValue);
					Thread.sleep(1000);
				}
					System.out.println("##");
					WebElement LQty = driver.findElement(By.xpath("(//table[@id='StockAdjustmentBatchDetails']//tbody//tr//td//input[@class='form-control QtyTextBox batchlqty'])["+xpathIndex+"]"));
					LQty.click();
					LQty.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
					LQty.sendKeys(lQtyValue);
					
			
				Thread.sleep(2000);
				WebElement closeBtn = driver.findElement(
						By.xpath("(//table[@id='StockAdjustmentBatchDetails']//preceding::span[contains(text(),'×')])["+xpathIndex+"]")); 
				js.executeScript("arguments[0].click();", closeBtn);
				Thread.sleep(3000);
			}

		}

		js.executeScript("arguments[0].scrollIntoView(true);", sa.SaveV2);
		Thread.sleep(2000);
	//	js.executeScript("arguments[0].click();", sa.SaveV2);
		click(sa.AlertPopupOK);
		Thread.sleep(1000);
		click(sa.AlertPopupOK);
		System.out.println("Stock Adjustment Save Successfull");
		System.out.println("***");

	}

}
