package com.Demo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import com.PomClass.DeliveryOrder;
import com.PomClass.Login;
import com.PomClass.Product;
import com.PomClass.ProductMovement;
import com.PomClass.PurchaseOrder;
import com.PomClass.SalesOrder;
import com.PomClass.SalesReturn;
import com.PomClass.StockAdjustment;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ConcurrencyConvertTest extends BaseClass {

	private String URL;

	@Test(priority = 5)
	public void ConcurrencyTest() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://spmtest.adaptivebizapp.com/Account/Login");
		URL = "https://spmtest.adaptivebizapp.com/ERP/";
		String ActURL = driver.getCurrentUrl();
		boolean equals = URL.equalsIgnoreCase(ActURL);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		Login lo = new Login(driver);
		Product pr = new Product(driver);
		ProductMovement pm = new ProductMovement(driver);
		PurchaseOrder po = new PurchaseOrder(driver);
		SalesOrder so = new SalesOrder(driver);
		SalesReturn sr = new SalesReturn(driver);
		DeliveryOrder dr = new DeliveryOrder(driver);
		StockAdjustment sa = new StockAdjustment(driver);

		Sendkeys(lo.CompanyCode, "SPMTEST");
		Sendkeys(lo.UserName, "Kiran");
		Sendkeys(lo.Password, "Adaptive*123");
		Thread.sleep(1000);
		click(lo.LoginButton);
		Thread.sleep(3000);


		String[] products = {"RED PEN-B.W.C", "GREEN PEN-B.W.N"};

		//Product Page 
		driver.navigate().to(URL + "SalesPurchases/Product");
		Thread.sleep(4000);

		for (String product : products) {

			js.executeScript("arguments[0].click();", pr.FindProductorCode);
			pr.FindProductorCode.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
			Sendkeys(pr.FindProductorCode, product +Keys.ENTER);
			js.executeScript("arguments[0].click();", pr.Fetch); 
			Thread.sleep(3000);
			String beforeProductStock = driver.findElement(By.xpath( "//table[@id='producttable']//tbody//tr//td[4]//label")).getText();
			System.out.println("Product Name: "+product);
			System.out.println("Before Concurrency Convert Product Stock Is:"+beforeProductStock); 
			System.out.println("***");

		}

		for (String product : products) {

			//Product Movement Page 
			Thread.sleep(2000); 
			driver.navigate().to(URL + "SalesPurchases/Product/ProductMovementsIndex"); 
			Thread.sleep(4000);

			click(pm.ChooseProduct); 
			driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']")) 
			.sendKeys(product +Keys.ENTER); 
			click(pm.Fetch); 
			Thread.sleep(2000);
			String beforeBalanceQty = driver.findElement(
					By.xpath("(//table[@class='table table-striped table-bordered cell-border']//tbody//tr//td[4])[1]")).getText(); 
			System.out.println("Product Name: "+product);
			System.out.println("Before Concurrency Convert Product Movement Stock Is: "+beforeBalanceQty); 
			System.out.println("***"); }


		// Purchase Order to Invoice Page
		driver.navigate().to(URL + "SalesPurchases/PurchaseOrder");
		Thread.sleep(4000);

		Sendkeys(po.FindOrderNo, "SPM-03371-PO");
		Thread.sleep(2000);

		FluentWait<WebDriver> wait1 = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)) // Max wait time
				.pollingEvery(Duration.ofMillis(500)) // Check every 500ms
				.ignoring(NoSuchElementException.class);

		WebElement purchaseFetch = wait1.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//table[@id='POtable']//preceding::input[@id='searchstring'])[1]")));
		js.executeScript("arguments[0].click();", purchaseFetch);
		Thread.sleep(2000);

		WebElement PurchaseDetailsIcon = driver.findElement(By.xpath("(//table[@id='POtable']//tbody//tr//td[2]"
				+ "[normalize-space()='SPM-03371-PO']//following::td[5]//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", PurchaseDetailsIcon);
		Thread.sleep(3000);

		WebElement copyOrder = driver.findElement(By.xpath("//a[text()='Copy Order']"));
		js.executeScript("arguments[0].scrollIntoView(true);", copyOrder);
		Thread.sleep(2000);
		copyOrder.click();
		Thread.sleep(2000);

		click(po.ConvertInvoice);
		click(po.PopupAlertOk);
		Thread.sleep(2000);

		LocalDateTime TimeStamp = LocalDateTime.now();
		DateTimeFormatter DateTimeFormate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatedTimestamp = TimeStamp.format(DateTimeFormate);
		js.executeScript("arguments[0].click();", po.InvoiceNo);
		Sendkeys(po.InvoiceNo, formatedTimestamp);
		Thread.sleep(2000);

		int batchFileSize = driver.findElements(By.xpath("//a[@id='BatchFolder']")).size();
		System.out.println("Purchase Batch File Size is :" + batchFileSize);
		int k = 1;
		for (int i = 1; i <= batchFileSize; i++) {

			if (i != 1) {
				k =k+1;

			}

			System.out.println("k "+k);

			try {

				String BQty = driver
						.findElement(By.xpath(
								"((//a[@id='BatchFolder'])[" + i + "]//preceding::td[8])//input[@id='detailBQty']"))
						.getAttribute("value");
				System.out.println("BQty Is:" + BQty);
				String LQty = driver
						.findElement(By.xpath(
								"((//a[@id='BatchFolder'])[" + i + "]//preceding::td[7])//input[@id='detailLQty']"))
						.getAttribute("value");
				System.out.println("LQty Is:" + LQty);
				Thread.sleep(2000);

				/*
				 * String Qty = driver.findElement(By.xpath("((//a[@id='BatchFolder'])["+i+
				 * "]//preceding::td[6])//input[@id='detailQty']")).getAttribute("value");
				 * System.out.println("Total Qty Is: "+Qty);
				 */

				WebElement batchFile = driver.findElement(
						By.xpath("(//table[@id='PurchaseTable']//tbody//tr//td//a[@id='BatchFolder'])[" + i + "]"));

				js.executeScript("arguments[0].scrollIntoView(true);", batchFile);
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", batchFile);
				Thread.sleep(2000);

				// String totalBQty =
				// driver.findElement(By.xpath("(//input[@id='TotalBQty'])["+(i+1)+"]")).getAttribute("value");
				// System.out.println("Total BQty Is:"+totalBQty);

				if (!BQty.equals("0")) {

					WebElement bQty = driver.findElement(By.xpath(
							"(//table[@class='table table-bordered Mytable']//tbody//tr//td//input[@class='form-control BQty  QtyTextBox'])["
									+ i + "]"));
					js.executeScript("arguments[0].click();", bQty);
					bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
					bQty.sendKeys(BQty);
					Thread.sleep(2000);
				}

				// String lQtyValue = driver.findElement(By.xpath("(//input[@id='LQty'])[" + (i
				// + 1) + "]")).getAttribute("value");
				// System.out.println("Total LQty Is:" + lQtyValue);

				WebElement lQty = driver.findElement(By.xpath(
						"(//table[@class='table table-bordered Mytable']//tbody//tr//td//input[@class='form-control LQty  QtyTextBox'])["
								+ i + "]"));
				js.executeScript("arguments[0].click();", lQty);
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(LQty);

				/*
				 * WebElement qty = driver.findElement( By.
				 * xpath("(//table[@class='table table-bordered Mytable']//tbody//tr//td[6]//input[@class='form-control Qty  QtyTextBox'])["
				 * +i+"]")); js.executeScript("arguments[0].click();", qty);
				 * qty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE); qty.sendKeys(Qty);
				 * Thread.sleep(2000);
				 */

				WebElement addbtn = driver.findElement(By.xpath("(//button[text()='Add'])[" + (i + k) + "]"));
				js.executeScript("arguments[0].click();", addbtn);
				System.out.println("@@@");

			} catch (NoSuchElementException | ElementClickInterceptedException e) {

			}
		}
		Thread.sleep(2000);

		js.executeScript("arguments[0].click();", po.Save);
		System.out.println("Purchase Invoice Save Successfull");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now2 = LocalDateTime.now();
		String formattedTime = now2.format(formatter);
		System.out.println("Current Time: " + formattedTime);
		System.out.println("***");



		// Sales Order to Invoice Page

		driver.navigate().to(URL + "SalesPurchases/SalesOrderIndex");
		Thread.sleep(7000);

		Sendkeys(so.FindOrderNo, "SPM-148464-SO");
		Thread.sleep(2000);

		WebElement salesFetch = driver .findElement(By.xpath("(//table[@id='ordertable']//preceding::input[@id='searchstring'])[1]"));

		js.executeScript("arguments[0].scrollIntoView(true);", salesFetch);
		Thread.sleep(2000); js.executeScript("arguments[0].click();", salesFetch);
		Thread.sleep(2000);

		WebElement salesDetailsIcon =driver.findElement(By.xpath("(//table[@id='ordertable']//tbody//tr//td[3]" +
				"[normalize-space()='SPM-148464-SO']//following::td[7]//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", salesDetailsIcon);
		Thread.sleep(2000);

		click(po.CopyOrder); 
		Thread.sleep(1000); 
		click(po.ConvertInvoice);
		click(po.PopupAlertOk); 
		Thread.sleep(2000);

		int batchFileSize1 =
				driver.findElements(By.xpath("//a[@class='fa fa-folder-open Popup']")).size(); 
		System.out.println("Sales Batch File Size Is :" + batchFileSize1); 
		for (int i = 1; i <= batchFileSize1; i++) {

			try {

				WebElement batchFile = driver.findElement(
						By.xpath("(//table[@id='SalesTable']//tbody//tr//td//a[@title='Batch'])["+i+ "]"));

				js.executeScript("arguments[0].scrollIntoView(true);", batchFile);
				Thread.sleep(2000); js.executeScript("arguments[0].click();", batchFile);
				Thread.sleep(2000);

				String ctnQtyValue = driver.findElement(By.xpath("(//div[text()='CTN.Qty : ']//input[@id='BQty'])["+i+"]")).getAttribute("value"); 
				System.out.println("CTNQty Is:"+ctnQtyValue); 
				String pcsQtyValue = driver.findElement(By.xpath("(//div[text()=' PCS.Qty : ']//input[@id='LQty'])["+i+"]")).getAttribute("value"); 
				System.out.println("CTNQty Is:" + pcsQtyValue);
				Thread.sleep(1000);

				if (!ctnQtyValue.equals("0")) {

					WebElement bQty = driver .findElement(By.xpath("(//table[@class='table table-bordered Mytable']//tbody//tr//td[6])[" +i + "]//input[@id='BulkQty']")); 
					js.executeScript("arguments[0].click();",bQty);
					bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
					bQty.sendKeys(ctnQtyValue); 

				}

				WebElement lQty = driver .findElement(By.xpath("(//table[@class='table table-bordered Mytable']//tbody//tr//td[7])[" +i + "]//input[@id='LooseQty']")); 
				js.executeScript("arguments[0].click();", lQty); 
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(pcsQtyValue); 
				driver.findElement(By.xpath("(//table[@class='table table-bordered Mytable']//tbody//tr//td[7])[" +i + "]//input[@id='LooseQty']//following::button[text()='Add']")).click();
				Thread.sleep(3000); 
				System.out.println("$$");




			} catch (NoSuchElementException | ElementClickInterceptedException e) {

			}

		}

		click(so.Save); 
		System.out.println("Sales Invoice Save Successfull");
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now3 = LocalDateTime.now(); String formattedTime1 = now3.format(formatter1);
		System.out.println("Current Time: " + formattedTime1);
		System.out.println("***");


		// sales Return to Credit Notes

		driver.navigate().to(URL + "SalesPurchases/SalesReturn"); 
		Thread.sleep(4000);

		Sendkeys(sr.FindSalesReturnNo, "14374"); 
		Thread.sleep(2000);

		FluentWait<WebDriver> wait2 = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)) 
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(NoSuchElementException.class);

		WebElement sRetuenFetch = wait2.until(ExpectedConditions.elementToBeClickable( By.xpath(
				"(//table[@id='salesreturntable']//preceding::input[@id='searchstring'])[1]") )); 
		sRetuenFetch.click(); 
		Thread.sleep(2000);

		//   WebElement detailsIcon = driver.findElement(By.xpath(
		//  "(//table[@id='salesreturntable']//tbody//tr//td[2][normalize-space()='14362']//following::td[7]//a[@title='Details'])[1]"));

		WebElement detailsIcon = driver.findElement(By.xpath(
				"(//table[@id='salesreturntable']//tbody//tr//td[2][normalize-space()='14374']//following::td[6]//a[@title='Details'])[1]")); 
		js.executeScript("arguments[0].click();", detailsIcon);
		Thread.sleep(2000);
		click(sr.CopyReturn); 
		Thread.sleep(2000);
		click(sr.convertcreditnote); 
		click(sr.PopupOk); 
		Thread.sleep(2000);

		int batchFileSize2 = driver.findElements(By.xpath("//a[@class='fa fa-folder-open Popup']")).size();
		System.out.println("Sales Return Batch File Size Is:" +batchFileSize2); 
		for(int i = 1; i <= batchFileSize2; i++) {

			try {

				WebElement batchFile = driver.findElement( By.
						xpath("(//table[@id='CreditNoteTable']//tbody//tr//td//a[@class='fa fa-folder-open Popup'])[" +i+"]"));

				batchFile.click(); 
				Thread.sleep(1000);

				String BQty = driver.findElement(By.xpath("(//div[text()='CTN.Qty : ']//input[@id='BQty'])["+i+"]")).getAttribute("value"); 
				System.out.println("BQty Is:"+BQty); 
				String lQtyValue =driver.findElement(By.xpath("(//div[text()=' PCS.Qty : ']//input[@id='LQty'])["+i+"]")).getAttribute("value"); 
				System.out.println("LQty Is:"+lQtyValue);
				Thread.sleep(2000);

				if (!BQty.equals("0")) {

					WebElement bQty = driver.findElement(
							By.xpath("(//table[@class='table table-bordered Mytable']//tbody//tr//td[6])["+i +"]//input[@id='BulkQty']")); 
					bQty.click(); bQty.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE); 
					bQty.sendKeys(BQty);

				}

				WebElement lQty = driver.findElement( By.xpath("(//table[@class='table table-bordered Mytable']//tbody//tr/td[7])["+i+"]//input[@id='LooseQty']")); 
				lQty.click(); 
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE); 
				lQty.sendKeys(lQtyValue); 
				driver.findElement(By.xpath("(//table[@class='table table-bordered Mytable']//tbody//tr/td[7])["+i+ "]//input[@id='LooseQty']//following::button[text()='Add'][1]")) .click();
				System.out.println("###");

				Thread.sleep(3000);

			} catch (ElementClickInterceptedException | NoSuchElementException e) {

			}

		}



		click(sr.Save); 
		System.out.println("Credit Notes Save Successfull");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		LocalDateTime now4 = LocalDateTime.now(); 
		String formattedTime2 = now4.format(formatter2);
		System.out.println("Current Time: " + formattedTime2);
		System.out.println("***");


		// Delivery Order Page

		driver.navigate().to(URL + "SalesPurchases/DeliveryOrder");
		Thread.sleep(4000);

		Sendkeys(dr.FindCustomerDONo, "0015"); 
		Thread.sleep(2000);

		WebElement Fetch = driver .findElement(By.xpath(
				"(//table[@id='DeliveryOrderTable']//preceding::input[@id='searchstring'])[1]" ));

		js.executeScript("arguments[0].scrollIntoView(true);", Fetch);
		Thread.sleep(2000); 
		Fetch.click(); 
		Thread.sleep(2000);

		WebElement deliveryDetailsIcon = driver.findElement(By.xpath(
				"(//table[@id='DeliveryOrderTable']//tbody//tr//td[3][normalize-space()='0015']//following::td[3]//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", deliveryDetailsIcon);
		Thread.sleep(1000);

		click(dr.CopyOrder); 
		Thread.sleep(1000);

		click(dr.Save); System.out.println("Delivery Order Save Successfull");
		DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		LocalDateTime now5 = LocalDateTime.now(); String formattedTime3 = now2.format(formatter3);
		System.out.println("Current Time: " + formattedTime3);
		System.out.println("***");



		//Stock Adjustment page 
		Thread.sleep(2000); driver.navigate().to(URL + "SalesPurchases/StockAdjustment"); 
		Thread.sleep(4000);

		Sendkeys(sa.FindStockNo, "0024"); 
		Thread.sleep(2000);

		WebElement sadjustmentFetch = driver.findElement(
				By.xpath("(//table[@id='StockAdjustmenttable']//preceding::input[@id='searchstring'])[1]" )); 
		js.executeScript("arguments[0].scrollIntoView(true);", sadjustmentFetch);
		Thread.sleep(2000); 
		js.executeScript("arguments[0].click();", sadjustmentFetch);
		Thread.sleep(2000);


		WebElement sAdjustmentDetailsIcon = driver.findElement(By.xpath(
				"(//table[@id='StockAdjustmenttable']//tbody//tr//td[2][normalize-space()='0024']//following::td[4]//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", sAdjustmentDetailsIcon);
		Thread.sleep(2000);

		click(sa.CopyStockAdjustment); 
		Thread.sleep(2000);

		click(sa.Save); 
		System.out.println("Stock Adjustment Save Successfull");
		DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now6 = LocalDateTime.now(); 
		String formattedTime4 = now2.format(formatter4);
		System.out.println("Current Time: " + formattedTime4);
		System.out.println("***");


		// Product Page

		Thread.sleep(3000);
		driver.navigate().to(URL + "SalesPurchases/Product");
		Thread.sleep(4000);

		for (String product : products) {

			click(pr.FindProductorCode); 
			pr.FindProductorCode.sendKeys(Keys.CONTROL +"a"+Keys.DELETE); 
			Sendkeys(pr.FindProductorCode, product +Keys.ENTER);
			click(pr.Fetch); 
			Thread.sleep(4000); 
			String beforeProductStock = driver.findElement(By.xpath("//table[@id='producttable']//tbody//tr//td[4]")).getText(); 
			System.out.println("Product Name: "+product);
			System.out.println("After Concurrency Convert Product Stock Is:" +beforeProductStock); 
			System.out.println("***");

		}

		for (String product : products) {

			//Product Movement Page 
			Thread.sleep(2000); 
			driver.navigate().to(URL + "SalesPurchases/Product/ProductMovementsIndex"); 
			Thread.sleep(4000);

			click(pm.ChooseProduct); 
			driver.findElement(By.xpath( "//span[@id='select2-ProductId-container']//following::input[@type='search']"))
			.sendKeys(product +Keys.ENTER); 
			click(pm.Fetch); 
			Thread.sleep(2000);
			String beforeBalanceQty = driver.findElement( By.xpath("(//table[@class='table table-striped table-bordered cell-border']//tbody//tr//td[4])[1]")).getText(); 
			System.out.println("Product Name: "+product);
			System.out.println("After Concurrency Convert Product Movement Stock Is: " +beforeBalanceQty); 
			System.out.println("***"); }


	}
}
