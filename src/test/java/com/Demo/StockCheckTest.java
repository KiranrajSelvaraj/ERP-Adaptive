package com.Demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BaseClass.BaseClass;
import com.PomClass.CreditNotes;
import com.PomClass.Login;
import com.PomClass.PurchaseOrder;
import com.PomClass.SalesInvoice;
import com.PomClass.SalesOrder;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StockCheckTest extends BaseClass{

	private String url;

	private String Username;

	SoftAssert soft = new SoftAssert();

	@DataProvider(name = "user1", parallel = true)
	private Object[][] Kiran01() {

		Object[][] data = Util1.getTestData("C:\\Adaptive\\ERP\\SalesOrder4.xlsx", "Sheet1");
		return data;

	}

	@DataProvider(name = "user2", parallel = true)
	private Object[][] Kiran02() {

		Object[][] data = Util1.getTestData("C:\\Adaptive\\ERP\\CreditNotes.xlsx", "Sheet1");
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
		private String BatchNo;

		public ExcelData(String CustomerCode, String CurrencyCode, String GstType, String Terms, String CurrencyRate,
				String ProductService, String UOM, String Qty, String Foc, String Price, String BatchProduct,
				String IsSpecialPriceCheckbox, String CtnSplPrice, String PcsSplPrice, String UnitDisc,
				String DiscountPer, String DiscountAmt, String DiscountModeForAllProduct, String GSTPercentage, String BatchNo) {
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
			this.BatchNo = BatchNo;

		}

	}

	//Excel 3
	private ArrayList<ExcelData> dataList = new ArrayList<>();

	@Test(priority = 2, dataProvider = "user1")
	public void GetData(String CustomerCode, String CurrencyCode, String GstType, String Terms, String CurrencyRate,
			String ProductService, String UOM, String Qty, String Foc, String Price, String BatchProduct,
			String IsSpecialPriceCheckbox, String CtnSplPrice, String PcsSplPrice, String UnitDisc, String DiscountPer,
			String DiscountAmt, String DiscountModeForAllProduct, String GSTPercentage, String BatchNo) {


		ExcelData data = new ExcelData(CustomerCode, CurrencyCode, GstType, Terms, CurrencyRate, ProductService, UOM,
				Qty, Foc, Price, BatchProduct, IsSpecialPriceCheckbox, CtnSplPrice, PcsSplPrice, UnitDisc, DiscountPer,
				DiscountAmt, DiscountModeForAllProduct, GSTPercentage,BatchNo);

		dataList.add(data);

	}


	@Test(priority = 3)
	private void SalesOrdertoInvoice() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		//driver.manage().window().maximize();

		driver.get("https://erp.dev1.adaptivegroups.asia/");
		url = "https://erp.dev1.adaptivegroups.asia/ERP/";

		String ActURL = driver.getCurrentUrl();
		boolean equals = url.equalsIgnoreCase(ActURL);

		Login lo = new Login(driver);

		Sendkeys(lo.CompanyCode, "UITDEMO1");
		Sendkeys(lo.UserName, "Kiran01");
		Sendkeys(lo.Password, "Adaptive*123");
		click(lo.LoginButton);
		Thread.sleep(2000);

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
		Thread.sleep(3000);

		int excelDataListSize = dataList.size();
		System.out.println("Excel Data List Size Is1:" + excelDataListSize);
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = dataList.get(i);
			Thread.sleep(3000);
			
		//	System.out.println("excelData.CustomerCodeorName.isEmpty() "+excelData.CustomerCode.isEmpty() +driver.getWindowHandle());
			if (!excelData.CustomerCode.isEmpty()) {
				System.out.println("%");
				wait.until(ExpectedConditions.elementToBeClickable(so.Customer));
			//	js.executeScript("arguments[0].click();", so.Customer);
				Actions actions = new Actions(driver);
				actions.moveToElement(so.Customer).click().perform();
				driver.findElement(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
				.sendKeys("C0001" + Keys.ENTER);
				Thread.sleep(2000);

			}

			/*
			 * System.out.println(excelData.CustomerCode +Thread.currentThread().getName());
			 * System.out.println(excelData.ProductService
			 * +Thread.currentThread().getName());
			 */

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


		if (Username.equalsIgnoreCase("Kiran01")) {

			/////
			LocalDateTime now = LocalDateTime.now();

			// 🎯 Target minute and second (e.g., trigger at mm:ss = 12:55)
			int targetMinute = 40;
			int targetSecond = 10;

			boolean isWait = true;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			while (isWait) {
				LocalDateTime now2 = LocalDateTime.now();
				String formattedTime = now2.format(formatter);

				System.out.println("Current Time: " + formattedTime);

				if (now2.getMinute() == targetMinute && now2.getSecond() == targetSecond) {
					System.out.println("⏰ Reached target time: " + now2.format(formatter));

					// 🖱️ Simulate save button click

					click(si.Save);
					System.out.println("Sales Invoice Save Successfull");
					System.out.println("***");
				}

				isWait = false;
			}

			try {
				Thread.sleep(500); // Check twice per second for better timing
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}



	}

	@SuppressWarnings("unused")
	class ExcelData1 {

		private String CustomerCodeorName;
		private String Product;
		private String Uom;
		private String Qty;
		private String Price;
		private String BatchProduct;
		private String BatchNo;

		public ExcelData1(String CustomerCodeorName, String Product, String Uom, String Qty, String Price,
				String BatchProduct, String BatchNo) {
			super();

			this.CustomerCodeorName = CustomerCodeorName;
			this.Product = Product;
			this.Uom = Uom;
			this.Qty = Qty;
			this.Price = Price;
			this.BatchProduct = BatchProduct;
			this.BatchNo = BatchNo;

		}

	}

	private ArrayList<ExcelData1> dataList1 = new ArrayList<>();

	@Test(priority = 5, dataProvider = "user2")
	public void GetData(String CustomerCodeorName, String Product, String Uom, String Qty, String Price,
			String BatchProduct, String BatchNo) {

		ExcelData1 data1 = new ExcelData1(CustomerCodeorName, Product, Uom, Qty, Price, BatchProduct, BatchNo);
	//	System.out.println("CustomerCodeorName: "+CustomerCodeorName);
		dataList1.add(data1);

	}


	@Test(priority = 6)
	public void CreateCreditNotes() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		//driver.manage().window().maximize();

		driver.get("https://erp.dev1.adaptivegroups.asia/");
		url = "https://erp.dev1.adaptivegroups.asia/ERP/";

		String ActURL = driver.getCurrentUrl();
		boolean equals = url.equalsIgnoreCase(ActURL);

		Login lo = new Login(driver);
		Sendkeys(lo.CompanyCode, "UITDEMO1");
		Sendkeys(lo.UserName, "Kiran02");
		Sendkeys(lo.Password, "Adaptive*123");
		click(lo.LoginButton);
		Thread.sleep(2000);

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.navigate().to(url + "SalesPurchases/CreditNotes");
		Thread.sleep(4000);
		CreditNotes cn = new CreditNotes(driver);

		click(cn.AddCreditNote);
		Thread.sleep(3000);

		int excelDataListSize = dataList1.size();
		System.out.println("Excel Data List Size Is 2:" + excelDataListSize);
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData1 excelData1 = dataList1.get(i);
			Thread.sleep(2000);
		//	System.out.println("excelData1.CustomerCodeorName: "+excelData1.CustomerCodeorName);
		//	System.out.println("excelData1.CustomerCodeorName.isEmpty() "+excelData1.CustomerCodeorName.isEmpty() +driver.getWindowHandle());

			if (!excelData1.CustomerCodeorName.isEmpty()) {
				System.out.println("$");
				wait.until(ExpectedConditions.elementToBeClickable(cn.Customer));
			//	js.executeScript("arguments[0].click();", cn.Customer);
				Actions actions = new Actions(driver);
				actions.moveToElement(cn.Customer).click().perform();
				driver.findElement(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
				.sendKeys("C0002" + Keys.ENTER);
				Thread.sleep(2000);
			}

			click(cn.ChooseProduct);
			driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
			.sendKeys(excelData1.Product + Keys.ENTER);
			click(cn.Qty);
			click(cn.ChooseUom);
			List<WebElement> subUomOptions = driver.findElements(By.xpath(
					"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
			for (WebElement option : subUomOptions) {
				if (option.getText().trim().equals(excelData1.Uom)) {
					option.click();
					break;
				}
			}
			Sendkeys(cn.Qty, excelData1.Qty);
			click(cn.Price);
			cn.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(cn.Price, excelData1.Price);
			Thread.sleep(1000);
			click(cn.Add);
			Thread.sleep(2000);

			if (excelData1.BatchProduct.isEmpty() == false) {

				String BQty = driver.findElement(
						By.xpath("(//div[text()='B.Qty : ']//following::input[@id='BQty'])[" + (i + 1) + "]")).getAttribute("value");						
				System.out.println("BQty Is:" + BQty);
				String lQtyValue = driver.findElement(
						By.xpath("(//div[text()='L.Qty : ']//following::input[@id='LQty'])[" + (i + 1) + "]")).getAttribute("value");				
				System.out.println("LQty Is:" + lQtyValue);
				Thread.sleep(2000);

				WebElement showAllBtn = driver.findElement(By.id("showall"));
				showAllBtn.click();
				Thread.sleep(1000);

				WebElement bQty = driver.findElement(By
						.xpath("((//table[@class='table table-bordered Mytable']//input[@value='"+excelData1.BatchNo+"']//following::td)[5])//input[@id='BulkQty']"));								
				bQty.click();
				bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				bQty.sendKeys(BQty);

				WebElement lQty = driver.findElement(By
						.xpath("((//table[@class='table table-bordered Mytable']//input[@value='"+excelData1.BatchNo+"']//following::td)[6])//input[@id='LooseQty']"));								
				lQty.click();
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(lQtyValue);
				driver.findElement(By.xpath("((//table[@class='table table-bordered Mytable']//tbody//tr/td[7])"
						+ "//input[@id='LooseQty']//following::button[text()='Add'][1])[" + (i + 1) + "]")).click();

			}

			System.out.println("###");
			click(cn.Qty);

		}


		if (Username.equalsIgnoreCase("Kiran02")) {

			/////
			LocalDateTime now = LocalDateTime.now();

			// 🎯 Target minute and second (e.g., trigger at mm:ss = 12:55)
			int targetMinute = 40;
			int targetSecond = 10;

			boolean isWait = true;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			while (isWait) {
				LocalDateTime now2 = LocalDateTime.now();
				String formattedTime = now2.format(formatter);

				System.out.println("Current Time: " + formattedTime);

				if (now2.getMinute() == targetMinute && now2.getSecond() == targetSecond) {
					System.out.println("⏰ Reached target time: " + now2.format(formatter));

					// 🖱️ Simulate save button click

					click(cn.Save);
					System.out.println("*Credit Notes Save Successfull*");
					System.out.println("***");
				}

				isWait = false;
			}

			try {
				Thread.sleep(500); // Check twice per second for better timing
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}




}
