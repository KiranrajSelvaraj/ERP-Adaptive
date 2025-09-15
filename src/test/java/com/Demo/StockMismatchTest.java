package com.Demo;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BaseClass.BaseClass;
import com.PomClass.Login;
import com.PomClass.PurchaseOrder;
import com.PomClass.SalesInvoice;
import com.PomClass.SalesOrder;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StockMismatchTest extends BaseClass{

	private String url;
	private String Username;

	SoftAssert soft = new SoftAssert();
//	@Ignore
	@Parameters({"env"})
	@BeforeTest
	private void Environment(String UserName) {

	
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://erp.dev1.adaptivegroups.asia/ERP/Account/Login");
			url = "https://erp.dev1.adaptivegroups.asia/ERP/";

		
		if (UserName.equalsIgnoreCase("Kiran01")) {
			Username="Kiran01";
		}
		else {
			Username="Kiran02";
		}

	}

	@Test(priority = 1)
	private void ERPLoginPage() throws InterruptedException {

		Login lo = new Login(driver);

		Sendkeys(lo.CompanyCode, "UITDEMO1");
		Sendkeys(lo.UserName, Username);
		Sendkeys(lo.Password, "Adaptive*123");
		click(lo.LoginButton);
		Thread.sleep(2000);

		System.out.println("*ERP Login Page*");
	}



	@DataProvider 
	private Object[][] Util2() {

		if (Username.equalsIgnoreCase("Kiran01")) { Object[][] data =
				Util1.getTestData("C:\\Adaptive\\ERP\\SalesOrder3.xlsx", "Sheet1"); 
		return data;

		} else { 
			Object[][] data = Util1.getTestData("C:\\Adaptive\\ERP\\SalesOrder4.xlsx", "Sheet1"); 
			return data; 
		}

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
		private String Foc;
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
				String ProductService, String UOM, String Qty, String Foc, String Price, String BatchProduct,
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
			this.Foc = Foc;
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
			String ProductService, String UOM, String Qty, String Foc, String Price, String BatchProduct,
			String IsSpecialPriceCheckbox, String CtnSplPrice, String PcsSplPrice, String UnitDisc, String DiscountPer,
			String DiscountAmt, String DiscountModeForAllProduct, String GSTPercentage) {

		ExcelData data = new ExcelData(CustomerCode, CurrencyCode, GstType, Terms, CurrencyRate, ProductService, UOM,
				Qty, Foc, Price, BatchProduct, IsSpecialPriceCheckbox, CtnSplPrice, PcsSplPrice, UnitDisc, DiscountPer,
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
		SalesInvoice si = new SalesInvoice(driver);

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

			click(so.Foc);
			Sendkeys(so.Foc, excelData.Foc);

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

		click(si.Save);
		System.out.println("Sales Invoice Save Successfull");
		System.out.println("***");
		Thread.sleep(3000);
		
		if (Username.equalsIgnoreCase("Kiran01")) {
			
			WebElement deleteIcon = driver.findElement(By.xpath("(//table[@id='Invoicetable']//tbody//tr//td[9]//following::a[@title='Delete'])[1]"));
			deleteIcon.click();
			Thread.sleep(1000);
			WebElement deleteButton = driver.findElement(By.xpath("//input[@value='Delete']"));
			deleteButton.click();
			Thread.sleep(1000);
			click(si.PopupOk);
			System.out.println("Sales Invoice Delete Successfull");
		}

	}
	

	
	


}
