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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import com.PomClass.CreditNotes;
import com.PomClass.Customer;
import com.PomClass.PurchaseOrder;
import com.PomClass.SalesOrder;
import com.PomClass.SalesReturn;
import com.PomClass.StockAdjustment;
import com.PomClass.DeliveryOrder;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ConvertProcessTest extends BaseClass {

	private String URL;

	//	@Ignore
	@Test
	public void PurchaseOrderToInvoice() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://erp.dev1.adaptivegroups.asia/");
		URL = "https://erp.dev1.adaptivegroups.asia/ERP/";
		String ActURL = driver.getCurrentUrl();
		boolean equals = URL.equalsIgnoreCase(ActURL);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		Customer cu = new Customer(driver);
		PurchaseOrder po = new PurchaseOrder(driver);

		Sendkeys(cu.companyname, "UITDEMO1");
		Sendkeys(cu.username, "kiran01");
		Sendkeys(cu.password, "Adaptive*123");
		Thread.sleep(1000);
		click(cu.login);
		Thread.sleep(1000);
		Thread.sleep(2000);
		driver.navigate().to(URL + "SalesPurchases/PurchaseOrder");
		Thread.sleep(4000);

		Sendkeys(po.FindOrderNo, "0211");
		Thread.sleep(2000);

		FluentWait<WebDriver> wait1 = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)) // Max wait time
				.pollingEvery(Duration.ofMillis(500)) // Check every 500ms
				.ignoring(NoSuchElementException.class);

		WebElement fetch = wait1.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//table[@id='POtable']//preceding::input[@id='searchstring'])[1]")));
		fetch.click();
		Thread.sleep(2000);

		WebElement detailsIcon = driver.findElement(By.xpath("(//table[@id='POtable']//tbody//tr//td[2]"
				+ "[normalize-space()='0211']//following::td[7]//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", detailsIcon);
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
		click(po.InvoiceNo);
		Sendkeys(po.InvoiceNo, formatedTimestamp);
		Thread.sleep(2000);

		List<WebElement> table = driver.findElements(By.xpath("//table[@id='PurchaseTable']//tbody//tr//td[11]"));
		int TableSize = table.size();
		System.out.println("Purchase TableSize is :" + TableSize);
		for (int i = 1; i <= TableSize; i++) {

			try {
				
				String BQty = driver.findElement(By.xpath("(//table[@id='PurchaseTable']//tbody//tr//td[3])["+i+"]//input[@id='detailBQty']")).getAttribute("value");
				System.out.println("BQty Is:"+BQty);
				String LQty = driver.findElement(By.xpath("(//table[@id='PurchaseTable']//tbody//tr//td[4])["+i+"]//input[@id='detailLQty']")).getAttribute("value");
				System.out.println("BQty Is:"+LQty);
				
				WebElement batchFile = driver.findElement(
						By.xpath("(//table[@id='PurchaseTable']//tbody//tr//td[11])[" + i + "]//a[@id='BatchFolder']"));
				js.executeScript("arguments[0].scrollIntoView(true);", batchFile);
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", batchFile);
				Thread.sleep(2000);

				//		String totalBQty = driver.findElement(By.xpath("(//input[@id='TotalBQty'])["+(i+1)+"]")).getAttribute("value");
				//		System.out.println("Total BQty Is:"+totalBQty);

				WebElement bQty = driver
						.findElement(By.xpath("(//table[@class='table table-bordered Mytable']//tbody//tr//td[6])["+i+"]//input[@id='BQty']"));
				js.executeScript("arguments[0].click();", bQty);
				bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				bQty.sendKeys(BQty);
				Thread.sleep(2000);
				
				//		String lQtyValue = driver.findElement(By.xpath("(//input[@id='LQty'])[" + (i + 1) + "]")).getAttribute("value");					
				//		System.out.println("L Qty Is:" + lQtyValue);

				WebElement lQty = driver
						.findElement(By.xpath("(//table[@class='table table-bordered Mytable']//tbody//tr//td[7])[" + i
								+ "]//input[@id='LQty']"));
				js.executeScript("arguments[0].click();", lQty);
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(LQty);
				driver.findElement(By.xpath("(//button[text()='Add'])[" + (i + 1) + "]")).click();
				System.out.println("@@@");

			} catch (NoSuchElementException e) {

			}

		} 

	/*	LocalDateTime now = LocalDateTime.now();

		// Target: Wait until seconds reach 55
		int targetSecond = 55;

		boolean isWait = true;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		while (isWait) {
			LocalDateTime now2 = LocalDateTime.now();
			String formattedTime = now2.format(formatter);

			System.out.println("Current Time: " + formattedTime);

			// Compare seconds value instead of formatted strings
			if (now2.getSecond() == targetSecond) {
				System.out.println("Reached target second: " + targetSecond);
				
				js.executeScript("arguments[0].click();", cu.save);
				System.out.println("Purchase Invoice Save Successfull");
				
				isWait = false; // Exit loop
			}

			try {
				Thread.sleep(1000); // Wait 1 second to avoid CPU overuse
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		

		 LocalDateTime now = LocalDateTime.now();

	        // 🎯 Target minute and second (e.g., trigger at mm:ss = 12:55)
	        int targetMinute = 43;
	        int targetSecond = 55;

	        boolean isWait = true;
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	        while (isWait) {
	            LocalDateTime now2 = LocalDateTime.now();
	            String formattedTime = now2.format(formatter);

	            System.out.println("Current Time: " + formattedTime);

	            if (now2.getMinute() == targetMinute && now2.getSecond() == targetSecond) {
	                System.out.println("⏰ Reached target time: " + now2.format(formatter));
	                
	                // 🖱️ Simulate save button click
	                click(cu.save);
					System.out.println("Purchase Invoice Save Successfull");

	                isWait = false;
	            }

	            try {
	                Thread.sleep(500); // Check twice per second for better timing
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		
	}

	//	@Ignore
	@Test
	public void SalesOrderToInvoice() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://erp.dev1.adaptivegroups.asia/");
		URL = "https://erp.dev1.adaptivegroups.asia/ERP/";
		String ActURL = driver.getCurrentUrl();
		boolean equals = URL.equalsIgnoreCase(ActURL);

		WebDriverWait wait = new WebDriverWait(driver, 60);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Customer cu = new Customer(driver);
		PurchaseOrder po = new PurchaseOrder(driver);
		SalesOrder so = new SalesOrder(driver);

		Sendkeys(cu.companyname, "UITDEMO1");  
		Sendkeys(cu.username, "Kiran02");
		Sendkeys(cu.password, "Adaptive*123");
		Thread.sleep(1000);
		click(cu.login);
		Thread.sleep(1000);
		Thread.sleep(2000);
		driver.navigate().to(URL + "SalesPurchases/SalesOrderIndex");
		Thread.sleep(7000);

		Sendkeys(so.FindOrderNo, "0096");
		Thread.sleep(2000);

		WebElement Fetch = driver
				.findElement(By.xpath("(//table[@id='ordertable']//preceding::input[@id='searchstring'])[1]"));

		js.executeScript("arguments[0].scrollIntoView(true);", Fetch);
		Thread.sleep(2000);
		Fetch.click();
		Thread.sleep(2000);

		WebElement detailsIcon = driver.findElement(By.xpath("(//table[@id='ordertable']//tbody//tr//td[3]"
				+ "[normalize-space()='0096']//following::td[7]//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", detailsIcon);
		Thread.sleep(2000);

		click(po.CopyOrder);
		Thread.sleep(1000);
		click(po.ConvertInvoice);
		click(po.PopupAlertOk);
		Thread.sleep(2000);

		List<WebElement> tableRow = driver.findElements(By.xpath("//table[@id='SalesTable']//tbody//tr//td[13]"));
		int TableSize = tableRow.size();
		System.out.println("Sales Table Row Size Is :" + TableSize);
		for (int i = 1; i <= TableSize; i++) {

			try {

				WebElement batchFile = driver.findElement(
						By.xpath("(//table[@id='SalesTable']//tbody//tr//td[13])[" + i + "]//a[@title='Batch']"));
				js.executeScript("arguments[0].scrollIntoView(true);", batchFile);
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", batchFile);
				Thread.sleep(2000);

				String BQty = driver.findElement(By.xpath("(//input[@id='BQty'])["+(i+1)+"]")).getAttribute("value");
				System.out.println("BQty Is:"+BQty);
				
				WebElement bQty = driver
						.findElement(By.xpath("(//table[@class='table table-bordered Mytable']//tbody//tr//td[6])[" + i
								+ "]//input[@id='BulkQty']"));
				js.executeScript("arguments[0].click();", bQty);
				bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				bQty.sendKeys(BQty);

				String lQtyValue = driver.findElement(By.xpath("(//input[@id='LQty'])[" + (i+1) + "]")).getAttribute("value");
				System.out.println("LQty Is:" + lQtyValue);

				WebElement lQty = driver
						.findElement(By.xpath("(//table[@class='table table-bordered Mytable']//tbody//tr//td[7])[" + i
								+ "]//input[@id='LooseQty']"));
				js.executeScript("arguments[0].click();", lQty);
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(lQtyValue);
				driver.findElement(By.xpath("(//button[text()='Add'])[" + (i + 1) + "]")).click();

				Thread.sleep(3000);
				System.out.println("$$");

			} catch (NoSuchElementException e) {

			}

		} 

	/*	LocalDateTime now = LocalDateTime.now();

		// Target: Wait until seconds reach 55
		int targetSecond = 55;

		boolean isWait = true;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		while (isWait) {
			LocalDateTime now2 = LocalDateTime.now();
			String formattedTime = now2.format(formatter);

			System.out.println("Current Time: " + formattedTime);

			// Compare seconds value instead of formatted strings
			if (now2.getSecond() == targetSecond) {
				System.out.println("Reached target second: " + targetSecond);
				
				click(cu.save);
				System.out.println("Sales Invoice Save Successfull");
				
				isWait = false; // Exit loop
			}

			try {
				Thread.sleep(1000); // Wait 1 second to avoid CPU overuse
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		
		 LocalDateTime now = LocalDateTime.now();

	        // 🎯 Target minute and second (e.g., trigger at mm:ss = 12:55)
	        int targetMinute = 43;
	        int targetSecond = 55;

	        boolean isWait = true;
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	        while (isWait) {
	            LocalDateTime now2 = LocalDateTime.now();
	            String formattedTime = now2.format(formatter);

	            System.out.println("Current Time: " + formattedTime);

	            if (now2.getMinute() == targetMinute && now2.getSecond() == targetSecond) {
	                System.out.println("⏰ Reached target time: " + now2.format(formatter));
	                
	                // 🖱️ Simulate save button click
	                click(cu.save);
					System.out.println("Sales  Invoice Save Successfull");

	                isWait = false;
	            }

	            try {
	                Thread.sleep(500); // Check twice per second for better timing
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	
	}

	//	@Ignore
	@Test
	public void SalesReturnToCreditNote() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://erp.dev1.adaptivegroups.asia/");
		URL = "https://erp.dev1.adaptivegroups.asia/ERP/";
		String ActURL = driver.getCurrentUrl();
		boolean equals = URL.equalsIgnoreCase(ActURL);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Customer cu = new Customer(driver);
		SalesReturn sr = new SalesReturn(driver);
		CreditNotes cn = new CreditNotes(driver);

		Sendkeys(cu.companyname, "UITDEMO1");
		Sendkeys(cu.username, "kiran03");
		Sendkeys(cu.password, "Adaptive*123");
		Thread.sleep(1000);
		click(cu.login);
		Thread.sleep(1000);
		Thread.sleep(2000);
		driver.navigate().to(URL + "SalesPurchases/SalesReturn");
		Thread.sleep(4000);

		Sendkeys(sr.FindSalesReturnNo, "SR-0071");
		Thread.sleep(2000);

		FluentWait<WebDriver> wait1 = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)) // Max wait time
				.pollingEvery(Duration.ofMillis(500)) // Check every 500ms
				.ignoring(NoSuchElementException.class);

		WebElement fetch = wait1.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//table[@id='salesreturntable']//preceding::input[@id='searchstring'])[1]")));
		fetch.click();
		Thread.sleep(2000);

		WebElement detailsIcon = driver.findElement(By.xpath("(//table[@id='salesreturntable']//tbody//tr//td[2]"
				+ "[normalize-space()='SR-0071']//following::td[7]//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", detailsIcon);
		Thread.sleep(2000);
		click(sr.CopyReturn);
		Thread.sleep(2000);
		click(sr.convertcreditnote);
		click(sr.PopupOk);
		Thread.sleep(2000);

		List<WebElement> tableRow = driver.findElements(By.xpath("//table[@id='CreditNoteTable']//tbody//tr/td[13]"));
		int TableSize = tableRow.size();
		System.out.println("Sales Return Table Size Is:" +TableSize);
		for (int i = 1; i <= TableSize; i++) {

			try {

				WebElement batchFile = driver.findElement(
						By.xpath("(//table[@id='CreditNoteTable']//tbody//tr//td[13])["+i+"]//a[@class='fa fa-folder-open Popup']"));
				batchFile.click();
				Thread.sleep(1000);

				String BQty = driver.findElement(By.xpath("(//input[@id='BQty'])["+(i+1)+"]")).getAttribute("value");
				System.out.println("BQty Is:"+BQty);

				WebElement bQty = driver.findElement(By.xpath("(//table[@class='table table-bordered Mytable']//tbody//tr//td[6])["+i+"]//input[@id='BulkQty']"));
				bQty.click();
				bQty.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
				bQty.sendKeys(BQty);

				String lQtyValue = driver.findElement(By.xpath("(//input[@id='LQty'])[" + (i + 1) + "]")).getAttribute("value");
				System.out.println("LQty Is:"+lQtyValue);

				WebElement lQty = driver.findElement(
						By.xpath("(//table[@class='table table-bordered Mytable']//tbody//tr/td[7])["+i+"]//input[@id='LooseQty']"));
				lQty.click();
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(lQtyValue);
				driver.findElement(By.xpath("(//button[text()='Add'])[" + (i + 1) + "]")).click();
				System.out.println("###");

			} catch (ElementClickInterceptedException e) {

			}

		}  



	/*	LocalDateTime now = LocalDateTime.now();

		// Target: Wait until seconds reach 55
		int targetSecond = 55;

		boolean isWait = true;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		while (isWait) {
			LocalDateTime now2 = LocalDateTime.now();
			String formattedTime = now2.format(formatter);

			System.out.println("Current Time: " + formattedTime);

			// Compare seconds value instead of formatted strings
			if (now2.getSecond() == targetSecond) {
				System.out.println("Reached target second: " + targetSecond);
				
				click(sr.Save);
				System.out.println("Credit Notes Save Successfull");
				
				isWait = false; // Exit loop
			}

			try {
				Thread.sleep(1000); // Wait 1 second to avoid CPU overuse
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} */
		
		 LocalDateTime now = LocalDateTime.now();

	        // 🎯 Target minute and second (e.g., trigger at mm:ss = 12:55)
	        int targetMinute = 43;
	        int targetSecond = 55;

	        boolean isWait = true;
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	        while (isWait) {
	            LocalDateTime now2 = LocalDateTime.now();
	            String formattedTime = now2.format(formatter);

	            System.out.println("Current Time: " + formattedTime);

	            if (now2.getMinute() == targetMinute && now2.getSecond() == targetSecond) {
	                System.out.println("⏰ Reached target time: " + now2.format(formatter));
	                
	                // 🖱️ Simulate save button click
	                click(sr.Save);
					System.out.println("Credit Notes Save Successfull");

	                isWait = false;
	            }

	            try {
	                Thread.sleep(500); // Check twice per second for better timing
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		
	}

	//	@Ignore
	@Test
	public void StockAdjustment() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://erp.dev1.adaptivegroups.asia/");
		URL = "https://erp.dev1.adaptivegroups.asia/ERP/";
		String ActURL = driver.getCurrentUrl();
		boolean equals = URL.equalsIgnoreCase(ActURL);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Customer cu = new Customer(driver);
		StockAdjustment sa = new StockAdjustment(driver);

		Sendkeys(cu.companyname, "UITDEMO1");
		Sendkeys(cu.username, "Kiran04");
		Sendkeys(cu.password, "Adaptive*123");
		Thread.sleep(1000);
		click(cu.login);
		Thread.sleep(1000);
		Thread.sleep(2000);
		driver.navigate().to(URL + "SalesPurchases/StockAdjustment");
		Thread.sleep(6000);

		Sendkeys(sa.FindStockNo, "SA-0091");
		Thread.sleep(2000);

		WebElement Fetch = driver.findElement(
				By.xpath("(//table[@id='StockAdjustmenttable']//preceding::input[@id='searchstring'])[1]"));

		js.executeScript("arguments[0].scrollIntoView(true);", Fetch);
		Thread.sleep(2000);
		Fetch.click();
		Thread.sleep(2000);


		WebElement detailsIcon = driver.findElement(By.xpath("(//table[@id='StockAdjustmenttable']//tbody//tr//td[2]"
				+ "[normalize-space()='SA-0091']//following::td[4]//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", detailsIcon);
		Thread.sleep(2000);

		click(sa.CopyStockAdjustment);
		Thread.sleep(2000);

	/*	LocalDateTime now = LocalDateTime.now();

		// Target: Wait until seconds reach 55
		int targetSecond = 55;

		boolean isWait = true;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		while (isWait) {
			LocalDateTime now2 = LocalDateTime.now();
			String formattedTime = now2.format(formatter);

			System.out.println("Current Time: " + formattedTime);

			// Compare seconds value instead of formatted strings
			if (now2.getSecond() == targetSecond) {
				System.out.println("Reached target second: " + targetSecond);
				
				click(sa.Save);
				System.out.println("Stock Adjustment Save Successfull");
				
				isWait = false; // Exit loop
			}

			try {
				Thread.sleep(1000); // Wait 1 second to avoid CPU overuse
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		
		 LocalDateTime now = LocalDateTime.now();

	        // 🎯 Target minute and second (e.g., trigger at mm:ss = 12:55)
	        int targetMinute = 43;
	        int targetSecond = 55;

	        boolean isWait = true;
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	        while (isWait) {
	            LocalDateTime now2 = LocalDateTime.now();
	            String formattedTime = now2.format(formatter);

	            System.out.println("Current Time: " + formattedTime);

	            if (now2.getMinute() == targetMinute && now2.getSecond() == targetSecond) {
	                System.out.println("⏰ Reached target time: " + now2.format(formatter));
	                
	                // 🖱️ Simulate save button click
	                click(sa.Save);
					System.out.println("Stock Adjustment Save Successfull");

	                isWait = false;
	            }

	            try {
	                Thread.sleep(500); // Check twice per second for better timing
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		
	}

	//	@Ignore
	@Test
	public void DeliveryOrder() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://erp.dev1.adaptivegroups.asia/");
		URL = "https://erp.dev1.adaptivegroups.asia/ERP/";
		String ActURL = driver.getCurrentUrl();
		boolean equals = URL.equalsIgnoreCase(ActURL);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Customer cu = new Customer(driver);
		DeliveryOrder dr = new DeliveryOrder(driver);

		Sendkeys(cu.companyname, "UITDEMO1");
		Sendkeys(cu.username, "Kiran05");
		Sendkeys(cu.password, "Adaptive*123");
		Thread.sleep(1000);
		click(cu.login);
		Thread.sleep(1000);
		Thread.sleep(2000);
		driver.navigate().to(URL + "SalesPurchases/DeliveryOrder");
		Thread.sleep(4000);

		Sendkeys(dr.FindCustomerDONo, "DO-0047");
		Thread.sleep(2000);

		WebElement Fetch = driver
				.findElement(By.xpath("(//table[@id='DeliveryOrderTable']//preceding::input[@id='searchstring'])[1]"));

		js.executeScript("arguments[0].scrollIntoView(true);", Fetch);
		Thread.sleep(2000);
		Fetch.click();
		Thread.sleep(2000);

		WebElement detailsIcon = driver.findElement(By.xpath("(//table[@id='DeliveryOrderTable']//tbody//tr//td[3]"
				+ "[normalize-space()='DO-0047']//following::td[3]//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", detailsIcon);
		Thread.sleep(1000);

		click(dr.CopyOrder);
		Thread.sleep(1000);

	/*	LocalDateTime now = LocalDateTime.now();

		// Target: Wait until seconds reach 55
		int targetSecond = 55;

		boolean isWait = true;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		while (isWait) {
			LocalDateTime now2 = LocalDateTime.now();
			String formattedTime = now2.format(formatter);

			System.out.println("Current Time: " + formattedTime);

			// Compare seconds value instead of formatted strings
			if (now2.getSecond() == targetSecond) {
				System.out.println("Reached target second: " + targetSecond);
				
				click(dr.Save);
				System.out.println("Delivery Order Save Successfull");
				
				isWait = false; // Exit loop
			}

			try {
				Thread.sleep(1000); // Wait 1 second to avoid CPU overuse
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		
        LocalDateTime now = LocalDateTime.now();

        // 🎯 Target minute and second (e.g., trigger at mm:ss = 12:55)
        int targetMinute = 43;
        int targetSecond = 55;

        boolean isWait = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        while (isWait) {
            LocalDateTime now2 = LocalDateTime.now();
            String formattedTime = now2.format(formatter);

            System.out.println("Current Time: " + formattedTime);

            if (now2.getMinute() == targetMinute && now2.getSecond() == targetSecond) {
                System.out.println("⏰ Reached target time: " + now2.format(formatter));
                
                // 🖱️ Simulate save button click
                click(dr.Save);
				System.out.println("Delivery Order Save Successfull");

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
