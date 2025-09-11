package com.Demo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import com.Demo.SalesInvoiceCreateTest.ExcelData;
import com.PomClass.CreditNotes;
import com.PomClass.PurchaseOrder;
import com.PomClass.SalesInvoice;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreditNotesTest extends BaseClass {

	private String url;

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
		Object[][] data = Util1.getTestData("C:\\Adaptive\\ERP\\CreditNotes1.xlsx", "Sheet1");
		return data;

	}

	class ExcelData {

		private String CustomerCodeorName;
		private String Product;
		private String Uom;
		private String Qty;
		private String Price;
		private String BatchProduct;

		public ExcelData(String CustomerCodeorName, String Product, String Uom, String Qty, String Price,
				String BatchProduct) {
			super();

			this.CustomerCodeorName = CustomerCodeorName;
			this.Product = Product;
			this.Uom = Uom;
			this.Qty = Qty;
			this.Price = Price;
			this.BatchProduct = BatchProduct;

		}

	}

	ArrayList<ExcelData> dataList = new ArrayList<>();

	@Test(priority = 3, dataProvider = "Util2", dependsOnMethods = "ERPLoginPage")
	public void GetData(String CustomerCodeorName, String Product, String Uom, String Qty, String Price,
			String BatchProduct) {

		ExcelData data = new ExcelData(CustomerCodeorName, Product, Uom, Qty, Price, BatchProduct);

		dataList.add(data);

	}

	@Ignore
	@Test(priority = 4)
	public void CreateCreditNotes() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.navigate().to(url + "SalesPurchases/CreditNotes");
		Thread.sleep(4000);
		CreditNotes cn = new CreditNotes(driver);

		click(cn.AddCreditNote);
		Thread.sleep(3000);

		int excelDataListSize = dataList.size();
		System.out.println("Excel Data List Size Is:" + excelDataListSize);
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = dataList.get(i);
			Thread.sleep(2000);

			if (excelData.CustomerCodeorName.isEmpty() == false) {

				click(cn.Customer);
				driver.findElement(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
				.sendKeys(excelData.CustomerCodeorName + Keys.ENTER);
				Thread.sleep(2000);
			}

			click(cn.ChooseProduct);
			driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
			.sendKeys(excelData.Product + Keys.ENTER);
			click(cn.Qty);
			click(cn.ChooseUom);
			List<WebElement> subUomOptions = driver.findElements(By.xpath(
					"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
			for (WebElement option : subUomOptions) {
				if (option.getText().trim().equals(excelData.Uom)) {
					option.click();
					break;
				}
			}
			Sendkeys(cn.Qty, excelData.Qty);
			click(cn.Price);
			cn.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(cn.Price, excelData.Price);
			Thread.sleep(1000);
			click(cn.Add);
			Thread.sleep(2000);
			
			if (excelData.BatchProduct.isEmpty() == false) {
				
				String BQty = driver.findElement(
						By.xpath("(//div[text()='B.Qty : ']//following::input[@id='BQty'])[" + (i + 1) + "]")).getAttribute("value");						
				System.out.println("BQty Is:" + BQty);
				String lQtyValue = driver.findElement(
						By.xpath("(//div[text()='L.Qty : ']//following::input[@id='LQty'])[" + (i + 1) + "]")).getAttribute("value");				
				System.out.println("LQty Is:" + lQtyValue);
				Thread.sleep(2000);

				WebElement bQty = driver.findElement(By
						.xpath("((//table[@class='table table-bordered Mytable']//tbody//tr//td[6])//input[@id='BulkQty'])["+ (i + 1) + "]"));								
				bQty.click();
				bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				bQty.sendKeys(BQty);

				WebElement lQty = driver.findElement(By
						.xpath("((//table[@class='table table-bordered Mytable']//tbody//tr/td[7])//input[@id='LooseQty'])["+ (i + 1) + "]"));								
				lQty.click();
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(lQtyValue);
				driver.findElement(By.xpath("((//table[@class='table table-bordered Mytable']//tbody//tr/td[7])"
						+ "//input[@id='LooseQty']//following::button[text()='Add'][1])[" + (i + 1) + "]")).click();
				
			}

			System.out.println("###");
			click(cn.Qty);

		}

		Thread.sleep(3000);
		// click(cn.Save);
		System.out.println("*Credit Notes Save Successfull*");
		System.out.println("***");

	}

	@Ignore
	@Test(priority = 6)
	public void SalesInvoiceNotoCreditNotes() throws InterruptedException, AWTException {

		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.navigate().to(url + "SalesPurchases/CreditNotes");
		Thread.sleep(4000);
		CreditNotes cn = new CreditNotes(driver);

		click(cn.AddCreditNote);
		Thread.sleep(3000);
		int excelDataListSize = dataList.size();
		System.out.println("Excel Data List Size Is:" + excelDataListSize);
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = dataList.get(i);
			Thread.sleep(2000);

			if (excelData.CustomerCodeorName.isEmpty() == false) {

				click(cn.Customer);
				driver.findElement(
						By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
				.sendKeys(excelData.CustomerCodeorName + Keys.ENTER);
				Thread.sleep(3000);
				click(cn.InvoiceNo);

				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);

			}

			Thread.sleep(2000);
			click(cn.ChooseProduct);
			driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
			.sendKeys(excelData.Product + Keys.ENTER);
			click(cn.Qty);
			click(cn.ChooseUom);
			List<WebElement> subUomOptions = driver.findElements(By.xpath(
					"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
			for (WebElement option : subUomOptions) {
				if (option.getText().trim().equals(excelData.Uom)) {
					option.click();
					break;
				}
			}
			Sendkeys(cn.Qty, excelData.Qty);
			click(cn.Price);
			cn.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(cn.Price, excelData.Price);
			Thread.sleep(1000);
			click(cn.Add);
			Thread.sleep(2000);
			
			if (excelData.BatchProduct.isEmpty() == false) {
				
				String BQtyValue = driver.findElement(
								By.xpath("(//div[text()='B.Qty : ']//following::input[@id='BQty'])[" + (i + 1) + "]")).getAttribute("value");
				System.out.println("BQty Is:" + BQtyValue);
				String lQtyValue = driver.findElement(
								By.xpath("(//div[text()='L.Qty : ']//following::input[@id='LQty'])[" + (i + 1) + "]")).getAttribute("value");			
				System.out.println("LQty Is:" + lQtyValue);
				Thread.sleep(2000);

				WebElement bQty = driver.findElement(
						By.xpath("((//table[@class='table table-bordered Mytable']//tbody//tr//td[6])//input[@id='BulkQty'])["+ (i + 1) + "]"));
				bQty.click();
				bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				bQty.sendKeys(BQtyValue);

				WebElement lQty = driver.findElement(
						By.xpath("((//table[@class='table table-bordered Mytable']//tbody//tr/td[7])//input[@id='LooseQty'])["+ (i + 1) + "]"));
				lQty.click();
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(lQtyValue);
				driver.findElement(By.xpath(
						"((//table[@class='table table-bordered Mytable']//tbody//tr/td[7])//input[@id='LooseQty']//following::button[text()='Add'][1])["
								+ (i + 1) + "]"))
				.click();
				
			}

			System.out.println("###");
			click(cn.Qty);

		}

		Thread.sleep(3000);
		// click(cn.Save);
		System.out.println("*Sales Invoice No To Credit Notes Save Successfull*");
		System.out.println("***");

	}

	@Ignore
	@Test(priority = 8)
	public void SalesInvoicetoCreditNotesWithProduct() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.navigate().to(url + "SalesPurchases/SalesInvoice");
		Thread.sleep(4000);
		SalesInvoice si = new SalesInvoice(driver);
		CreditNotes cn = new CreditNotes(driver);

		driver.findElement(By.xpath("(//table[@id='Invoicetable']//tbody//tr//td[10]//a[@title='Details'])[1]"))
		.click();
		Thread.sleep(2000);
		click(si.IssueCreditNote);
		Thread.sleep(1000);
		click(si.PopupOk);
		Thread.sleep(3000);
		// click(cn.Save);
		System.out.println("*Sales Invoice To Credit Notes With Product Save Successfull*");
		System.out.println("***");

	}
	
	@Ignore
	@Test(priority = 10)
	public void SalesInvoicetoCreditNotesWithoutProduct() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.navigate().to(url + "SalesPurchases/SalesInvoice");
		Thread.sleep(4000);
		SalesInvoice si = new SalesInvoice(driver);
		CreditNotes cn = new CreditNotes(driver);

		driver.findElement(By.xpath("(//table[@id='Invoicetable']//tbody//tr//td[10]//a[@title='Details'])[1]"))
		.click();
		Thread.sleep(2000);
		click(si.IssueCreditNote);
		Thread.sleep(1000);
		click(si.PopupNo);
		Thread.sleep(3000);

		int excelDataListSize = dataList.size();
		System.out.println("Excel Data List Size Is:" + excelDataListSize);
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = dataList.get(i);
			Thread.sleep(2000);

			click(cn.ChooseProduct);
			driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
			.sendKeys(excelData.Product + Keys.ENTER);
			click(cn.Qty);
			click(cn.ChooseUom);
			List<WebElement> subUomOptions = driver.findElements(By.xpath(
					"//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
			for (WebElement option : subUomOptions) {
				if (option.getText().trim().equals(excelData.Uom)) {
					option.click();
					break;
				}
			}
			Sendkeys(cn.Qty, excelData.Qty);
			click(cn.Price);
			cn.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(cn.Price, excelData.Price);
			Thread.sleep(1000);
			click(cn.Add);
			Thread.sleep(2000);

			if (excelData.BatchProduct.isEmpty() == false) {

				String BQtyValue = driver.findElement(
						By.xpath("(//div[text()='B.Qty : ']//following::input[@id='BQty'])[" + (i + 1) + "]")).getAttribute("value");
				System.out.println("BQty Is:" + BQtyValue);
				String lQtyValue = driver.findElement(
						By.xpath("(//div[text()='L.Qty : ']//following::input[@id='LQty'])[" + (i + 1) + "]")).getAttribute("value");			
				System.out.println("LQty Is:" + lQtyValue);
				Thread.sleep(2000);

				WebElement bQty = driver.findElement(
						By.xpath("((//table[@class='table table-bordered Mytable']//tbody//tr//td[6])//input[@id='BulkQty'])["+ (i + 1) + "]"));
				bQty.click();
				bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				bQty.sendKeys(BQtyValue);

				WebElement lQty = driver.findElement(
						By.xpath("((//table[@class='table table-bordered Mytable']//tbody//tr/td[7])//input[@id='LooseQty'])["+ (i + 1) + "]"));
				lQty.click();
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(lQtyValue);
				driver.findElement(By.xpath(
						"((//table[@class='table table-bordered Mytable']//tbody//tr/td[7])//input[@id='LooseQty']//following::button[text()='Add'][1])["
								+ (i + 1) + "]"))
				.click();

			}

			System.out.println("###");
			click(cn.Qty);

		}

		Thread.sleep(3000);
		// click(cn.Save);
		System.out.println("*Sales Invoice To Credit Notes Without Product Save Successfull*");
		System.out.println("***");

	}

	@Ignore
	@Test(priority = 12)
	public void SalesInvoicetoCreditNotesValuesChanges() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.navigate().to(url + "SalesPurchases/SalesInvoice");
		Thread.sleep(4000);
		SalesInvoice si = new SalesInvoice(driver);
		CreditNotes cn = new CreditNotes(driver);

		driver.findElement(By.xpath("(//table[@id='Invoicetable']//tbody//tr//td[10]//a[@title='Details'])[1]"))
		.click();
		Thread.sleep(2000);
		click(si.IssueCreditNote);
		Thread.sleep(1000);
		click(si.PopupOk);
		Thread.sleep(3000);

		int tableSize = driver.findElements(By.xpath("//table[@id='CreditNoteTable']//tbody//tr//td[3]")).size();
		System.out.println("Table Size Is :" + tableSize);
		for (int j = 1; j <= tableSize; j++) {

			if (j == 1 || j == 2) {

				String BQtyValue = driver
						.findElement(By.xpath("(//table[@id='CreditNoteTable']//tbody//tr//td[6]//input)[" + j + "]"))
						.getAttribute("value");
				System.out.println("B.Qty Value Is :" + BQtyValue);
				String LQtyValue = driver
						.findElement(By.xpath("(//table[@id='CreditNoteTable']//tbody//tr//td[7]//input)[" + j + "]"))
						.getAttribute("value");
				System.out.println("L.Qty Value Is :" + LQtyValue);

				if (!BQtyValue.equals("0")) {

					WebElement BQtyField = driver.findElement(
							By.xpath("(//table[@id='CreditNoteTable']//tbody//tr//td[6]//input)[" + j + "]"));
					BQtyField.click();
					BQtyField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
					BQtyField.sendKeys("1");

				} else {

					WebElement LQtyField = driver.findElement(
							By.xpath("(//table[@id='CreditNoteTable']//tbody//tr//td[7]//input)[" + j + "]"));
					LQtyField.click();
					LQtyField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
					LQtyField.sendKeys("1");
				}	

			}

			Thread.sleep(2000);
			if (j == 3 || j == 4) {

				String BPrice = driver.findElement(By.xpath("(//table[@id='CreditNoteTable']//tbody//tr//td[9]//input)[" + j + "]"))
						.getAttribute("value");
				System.out.println("B.Qty Price Is :" + BPrice);
				String LPrice = driver.findElement(By.xpath("(//table[@id='CreditNoteTable']//tbody//tr//td[10]//input)[" + j + "]"))
						.getAttribute("value");
				System.out.println("L.Qty Price Is :" + LPrice);

				if (!BPrice.equals("0.00")) {

					WebElement BPriceField = driver.findElement(By.xpath("(//table[@id='CreditNoteTable']//tbody//tr//td[9]//input)[" + j + "]"));
					BPriceField.click();
					BPriceField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
					BPriceField.sendKeys("50");

				} else {

					WebElement LPriceField = driver.findElement(By.xpath("(//table[@id='CreditNoteTable']//tbody//tr//td[10]//input)[" + j + "]"));
					LPriceField.click();
					LPriceField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
					LPriceField.sendKeys("55");

				}

				System.out.println("***");

			}

			int Batchsize = driver.findElements(
					By.xpath("(//table[@id='CreditNoteTable']//tbody//tr//td[14])[" + j + "]//a[@class='fa fa-folder-open Popup']")).size();	
			System.out.println("Batch Size Is :"+Batchsize);

			if (Batchsize != 0) {

				WebElement batchFile = driver
						.findElement(By.xpath("(//table[@id='CreditNoteTable']//tbody//tr//td[14])[" + j
								+ "]//a[@class='fa fa-folder-open Popup']"));
				batchFile.click();
				Thread.sleep(1000);
				System.out.println("#");

				String BQty = driver
						.findElement(
								By.xpath("(//div[text()='B.Qty : ']//following::input[@id='BQty'])[" + j + "]"))
						.getAttribute("value");
				System.out.println("BQty Is:" + BQty);
				String LQty = driver
						.findElement(
								By.xpath("(//div[text()='L.Qty : ']//following::input[@id='LQty'])[" + j + "]"))
						.getAttribute("value");
				System.out.println("LQty Is:" + LQty);
				Thread.sleep(2000);

				WebElement bQty = driver.findElement(By.xpath(
						"((//table[@class='table table-bordered Mytable']//tbody//tr//td[6])//input[@id='BulkQty'])["
								+ j + "]"));
				bQty.click();
				bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				bQty.sendKeys(BQty);

				WebElement lQty = driver.findElement(By.xpath(
						"((//table[@class='table table-bordered Mytable']//tbody//tr/td[7])//input[@id='LooseQty'])["
								+ j + "]"));
				lQty.click();
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(LQty);
				driver.findElement(By.xpath(
						"((//table[@class='table table-bordered Mytable']//tbody//tr/td[7])//input[@id='LooseQty']//following::button[text()='Add'][1])["
								+ j + "]"))
				.click();
				System.out.println("***");
			}
			
			if (j == 4) {
				
				break;
				
			}

		}
		
		Thread.sleep(3000);
	//	click(cn.Save);
		System.out.println("*Sales Invoice to Credit Notes Values Changes*");
		System.out.println("***");

	}

}


