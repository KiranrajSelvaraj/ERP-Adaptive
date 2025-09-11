package com.Demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BaseClass.BaseClass;
import com.PomClass.PurchaseOrder;
import com.PomClass.SalesInvoice;
import com.PomClass.SalesOrder;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StockMismatchTest1 extends BaseClass {

	private String url;


	@Test(priority = 7)
	private void SalesOrdertoInvoice() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://erp.dev1.adaptivegroups.asia/");
		url = "https://erp.dev1.adaptivegroups.asia/ERP/";

		String ActURL = driver.getCurrentUrl();
		boolean equals = url.equalsIgnoreCase(ActURL);

		PurchaseOrder po = new PurchaseOrder(driver);

		Sendkeys(po.CompanyCode, "UITDEMO1");
		Sendkeys(po.UserName, "Kiran01");
		Sendkeys(po.Password, "Adaptive*123");
		click(po.LoginButton);
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
		Thread.sleep(2000);


		click(so.Customer);
		driver.findElement(
				By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
		.sendKeys("C0001" + Keys.ENTER);
		Thread.sleep(2000);


		click(so.Product);
		driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
		.sendKeys("TRIPLECHOCOMILKSHAKE-B.W.C"+ Keys.ENTER);
		Thread.sleep(1000);

		click(so.Qty);
		Thread.sleep(1000);

		click(so.Uom);
		driver.findElement(
				By.xpath("//span[@id='select2-UOMId-container']//following::input[@class='select2-search__field']"))
		.sendKeys("1X1KG" + Keys.ENTER);
		Thread.sleep(2000);

		click(so.Qty);
		Sendkeys(so.Qty, "2");

		click(so.Foc);
		Sendkeys(so.Foc, "1");

		click(so.Price);
		so.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(so.Price, "60");
		Thread.sleep(2000);

		click(so.Add);
		Thread.sleep(2000);
		click(so.Uom);


		js.executeScript("arguments[0].scrollIntoView(true);", so.ConvertInvoice);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", so.ConvertInvoice);
		Thread.sleep(2000);
		click(so.PopupOk);
		Thread.sleep(3000);



		WebElement batchFile = driver.findElement(By.xpath(""
				+ "(//table[@id='SalesTable']//tbody//tr//td[2][normalize-space()='TRIPLECHOCOMILKSHAKE-B.W.C']//following::td[11]//a[@class='fa fa-folder-open Popup'])[1]"));
		js.executeScript("arguments[0].scrollIntoView(true);", batchFile);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", batchFile);

		String ctnQtyValue = driver.findElement(By.xpath("(//div//strong[contains(text(),'TRIPLECHOCOMILKSHAKE-B.W.C')]//following::input[@id='BQty'])[1]")).getAttribute("value");
		System.out.println("B.Qty Is:" + ctnQtyValue);
		String pcsQtyValue = driver.findElement(By.xpath("(//div//strong[contains(text(),'TRIPLECHOCOMILKSHAKE-B.W.C')]//following::input[@id='LQty'])[1]")).getAttribute("value");
		System.out.println("L.Qty Is:" + pcsQtyValue);
		Thread.sleep(1000);


		WebElement bQty = driver.findElement(By.xpath("(//div//strong[contains(text(),'TRIPLECHOCOMILKSHAKE-B.W.C')]//following::input[@id='BulkQty'])[1]"));
		js.executeScript("arguments[0].click();", bQty);
		bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		bQty.sendKeys(ctnQtyValue);



		WebElement lQty = driver.findElement(By.xpath("(//div//strong[contains(text(),'TRIPLECHOCOMILKSHAKE-B.W.C')]//following::input[@id='LooseQty'])[1]"));
		js.executeScript("arguments[0].click();", lQty);
		lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		lQty.sendKeys(pcsQtyValue);
		driver.findElement(By.xpath("(//div//strong[contains(.,'TRIPLECHOCOMILKSHAKE-B.W.C')]//following::button[text()='Add'])[1]")).click();
		Thread.sleep(3000);


		
		/////
		LocalDateTime now = LocalDateTime.now();

		// 🎯 Target minute and second (e.g., trigger at mm:ss = 12:55)
		int targetMinute = 24;
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
				

			}
			isWait = false;
			
		}

		try {
			Thread.sleep(500); // Check twice per second for better timing
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		click(si.Save);
		System.out.println("Sales Invoice Save Successfull");
		System.out.println("***");
		Thread.sleep(3000);

	}



	@Test(priority = 9)
	private void Test2() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://erp.dev1.adaptivegroups.asia/");
		url = "https://erp.dev1.adaptivegroups.asia/ERP/";

		String ActURL = driver.getCurrentUrl();
		boolean equals = url.equalsIgnoreCase(ActURL);

		PurchaseOrder po = new PurchaseOrder(driver);

		Sendkeys(po.CompanyCode, "UITDEMO1");
		Sendkeys(po.UserName, "Kiran02");
		Sendkeys(po.Password, "Adaptive*123");
		click(po.LoginButton);
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
		Thread.sleep(2000);



		click(so.Customer);
		driver.findElement(
				By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
		.sendKeys("C0002" + Keys.ENTER);
		Thread.sleep(2000);


		click(so.Product);
		driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
		.sendKeys("GRAPESJUICE-B.W.C" + Keys.ENTER);
		Thread.sleep(1000);

		click(so.Qty);
		Thread.sleep(1000);

		click(so.Uom);
		driver.findElement(
				By.xpath("//span[@id='select2-UOMId-container']//following::input[@class='select2-search__field']"))
		.sendKeys("1X1KG" + Keys.ENTER);
		Thread.sleep(2000);

		click(so.Qty);
		Sendkeys(so.Qty, "2");

		click(so.Foc);
		Sendkeys(so.Foc, "1");

		click(so.Price);
		so.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		Sendkeys(so.Price, "70");
		Thread.sleep(2000);

		click(so.Add);
		Thread.sleep(2000);
		click(so.Uom);



		js.executeScript("arguments[0].scrollIntoView(true);", so.ConvertInvoice);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", so.ConvertInvoice);
		Thread.sleep(2000);
		click(so.PopupOk);
		Thread.sleep(3000);



		WebElement batchFile = driver.findElement(By.xpath(""
				+ "(//table[@id='SalesTable']//tbody//tr//td[2][normalize-space()='GRAPESJUICE-B.W.C']//following::td[11]//a[@class='fa fa-folder-open Popup'])[1]"));
		js.executeScript("arguments[0].scrollIntoView(true);", batchFile);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", batchFile);

		String ctnQtyValue = driver.findElement(By.xpath("(//div//strong[contains(text(),'GRAPESJUICE-B.W.C')]//following::input[@id='BQty'])[1]")).getAttribute("value");
		System.out.println("B.Qty Is:" + ctnQtyValue);
		String pcsQtyValue = driver.findElement(By.xpath("(//div//strong[contains(text(),'GRAPESJUICE-B.W.C')]//following::input[@id='LQty'])[1]")).getAttribute("value");
		System.out.println("L.Qty Is:" + pcsQtyValue);
		Thread.sleep(1000);



		WebElement bQty = driver.findElement(By.xpath("(//div//strong[contains(text(),'GRAPESJUICE-B.W.C')]//following::input[@id='BulkQty'])[1]"));
		js.executeScript("arguments[0].click();", bQty);
		bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		bQty.sendKeys(ctnQtyValue);



		WebElement lQty = driver.findElement(By.xpath("(//div//strong[contains(text(),'GRAPESJUICE-B.W.C')]//following::input[@id='LooseQty'])[1]"));
		js.executeScript("arguments[0].click();", lQty);
		lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		lQty.sendKeys(pcsQtyValue);
		driver.findElement(By.xpath("(//div//strong[contains(.,'GRAPESJUICE-B.W.C')]//following::button[text()='Add'])[1]")).click();
		Thread.sleep(3000);


		LocalDateTime now = LocalDateTime.now();

		// 🎯 Target minute and second (e.g., trigger at mm:ss = 12:55)
		int targetMinute = 24;
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

				
			}

			isWait = false;
		}

		try {
			Thread.sleep(500); // Check twice per second for better timing
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		click(si.Save);
		System.out.println("Sales Invoice Save Successfull");
		System.out.println("***");
		Thread.sleep(3000);
	}
	

}
