package com.Demo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import com.PomClass.Login;
import com.PomClass.MobileVanTransfers;
import com.PomClass.Product;
import com.PomClass.ProductMovement;
import com.PomClass.PurchaseOrder;
import com.PomClass.GoodReceivingNote;
import com.PomClass.ApprovalSetting;
import com.PomClass.DeliveryVehicleAssign;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StockAffectedCheckTest extends BaseClass{

	private String URL;

	@Ignore
	@Test
	public void MobileVanTransfer() throws InterruptedException {

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
		Login lo = new Login(driver);
		MobileVanTransfers mvt = new MobileVanTransfers(driver);
		Product pd = new Product(driver);
	

		Sendkeys(lo.CompanyCode, "UITDEMO1");
		Sendkeys(lo.UserName, "kiran01");
		Sendkeys(lo.Password, "Adaptive*123");
		Thread.sleep(1000);
		click(lo.LoginButton);
		Thread.sleep(2000);

		//Products
		driver.navigate().to(URL +"SalesPurchases/Product?StockFilter=All");
		Thread.sleep(4000);
		click(pd.FindProductorCode);
		Sendkeys(pd.FindProductorCode, "APPLEJUICE-B" +Keys.ENTER);
		click(pd.Fetch);
		Thread.sleep(2000);
		String productStock = driver.findElement(By.xpath("//table[@id='producttable']//tbody//tr//td[4]")).getText();
		System.out.println("Before Transfer Product Stock Is:"+productStock);

		//Mobile Van Products
		Thread.sleep(3000);
		driver.navigate().to(URL + "SalesPurchases/MobileVanProducts");
		Thread.sleep(4000);

		String stock = driver.findElement(By.xpath("//table[@id='Mobilevantable']//tbody//tr//td[6]")).getText();
		System.out.println("Before Mobile Van Transfer Stock Is:"+stock);

		//Mobile Van Transfers
		driver.navigate().to(URL + "SalesPurchases/MobileVanTransfers");
		Thread.sleep(4000);

		click(mvt.CreateNew);
		Thread.sleep(1000);
		click(mvt.ToMobileVan);
		driver.findElement(By.xpath("//span[@id='select2-ToMobileVanId-container']//following::input[@type='search']"))
		.sendKeys("V001-Van 001" +Keys.ENTER);
		Thread.sleep(2000);
		click(mvt.Product);
		driver.findElement(By.xpath("//span[@id='select2-Product-container']//following::input[@type='search']"))
		.sendKeys("APPLEJUICE-B" +Keys.ENTER);
		driver.findElement(By.xpath("//span[@id='select2-UOM-container']//following::input[@type='search']"))
		.sendKeys("Packets" +Keys.ENTER);
		Sendkeys(mvt.Qty, "10");
		click(mvt.Add);
		click(mvt.Remarks);
		Thread.sleep(2000);


		LocalDateTime now = LocalDateTime.now();

		// 🎯 Target minute and second (e.g., trigger at mm:ss = 12:55)
		int targetMinute = 28;
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
				click(mvt.Save);
				System.out.println("Mobile Van Transfer Save Successfull");

				isWait = false;
			}

			try {
				Thread.sleep(500); // Check twice per second for better timing
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


		//Mobile Van Products
		Thread.sleep(3000);
		driver.navigate().to(URL + "SalesPurchases/MobileVanProducts");
		Thread.sleep(4000);

		String stock1 = driver.findElement(By.xpath("//table[@id='Mobilevantable']//tbody//tr//td[6]")).getText();
		System.out.println("After Mobile Van Transfer Stock Is:"+stock1);


		//Products
		driver.navigate().to(URL +"SalesPurchases/Product?StockFilter=All");
		Thread.sleep(4000);
		click(pd.FindProductorCode);
		Sendkeys(pd.FindProductorCode, "APPLEJUICE-B" +Keys.ENTER);
		click(pd.Fetch);
		Thread.sleep(2000);
		String productStock1 = driver.findElement(By.xpath("//table[@id='producttable']//tbody//tr//td[4]")).getText();
		System.out.println("After Transfer Product Stock Is:"+productStock1);

	}

//	@Ignore
	@Test
	public void GoodReceivingNote() throws InterruptedException {

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
		Login lo = new Login(driver);
		PurchaseOrder po = new PurchaseOrder(driver);
		GoodReceivingNote grn = new GoodReceivingNote(driver);

		Sendkeys(lo.CompanyCode, "UITDEMO1");
		Sendkeys(lo.UserName, "kiran02");
		Sendkeys(lo.Password, "Adaptive*123");
		Thread.sleep(1000);
		click(lo.LoginButton);
		Thread.sleep(2000);

		//Purchase Order
		driver.navigate().to(URL +"SalesPurchases/PurchaseOrder");
		Thread.sleep(4000);

		Sendkeys(po.FindOrderNo, "0186");
		Thread.sleep(2000);

		FluentWait<WebDriver> wait1 = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)) // Max wait time
				.pollingEvery(Duration.ofMillis(500)) // Check every 500ms
				.ignoring(NoSuchElementException.class);

		WebElement fetch = wait1.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//table[@id='POtable']//preceding::input[@id='searchstring'])[1]")));
		fetch.click();
		Thread.sleep(2000);

		WebElement detailsIcon = driver.findElement(By.xpath("(//table[@id='POtable']//tbody//tr//td[2]"
				+ "[normalize-space()='0186']//following::td[7]//a[@title='Edit'])[1]"));
		js.executeScript("arguments[0].click();", detailsIcon);
		Thread.sleep(3000);

		click(po.ConvertGRN);
		click(po.PopupAlertOk);
		Thread.sleep(3000);

		//Good Receiving Note
		driver.navigate().to(URL +"SalesPurchases/GoodsReceivingNotes");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//table[@id='GoodsReceivingTable']//tbody//tr//td[8]//a[@title='Edit']")).click();
		Thread.sleep(2000);
		click(grn.Complete);
		click(grn.AlertOK);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//table[@id='GoodsReceivingTable']//tbody//tr//td[8]//a[@title='Details'])[1]")).click();
		click(grn.ConvertInvoice);
		click(grn.AlertOK);
		Thread.sleep(2000);

		LocalDateTime TimeStamp = LocalDateTime.now();
		DateTimeFormatter DateTimeFormate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatedTimestamp = TimeStamp.format(DateTimeFormate);
		click(po.InvoiceNo);
		Sendkeys(po.InvoiceNo, formatedTimestamp);
		Thread.sleep(2000);


		LocalDateTime now = LocalDateTime.now();

		// 🎯 Target minute and second (e.g., trigger at mm:ss = 12:55)
		int targetMinute = 02;
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
				click(po.SaveButton);
				System.out.println("Good Receiving Notes Save Successfull");

				isWait = false;
			}

			try {
				Thread.sleep(500); // Check twice per second for better timing
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Ignore
	@Test
	public void ApprovalSetting() throws InterruptedException {

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
		Login lo = new Login(driver);
		ApprovalSetting as = new ApprovalSetting(driver);

		Sendkeys(lo.CompanyCode, "UITDEMO1");
		Sendkeys(lo.UserName, "kiran03");
		Sendkeys(lo.Password, "Adaptive*123");
		Thread.sleep(1000);
		click(lo.LoginButton);
		Thread.sleep(2000);

		driver.navigate().to(URL +"SalesPurchases/Approvers/Create");
		Thread.sleep(4000);

		click(as.SelectModel);
		driver.findElement(By.xpath("//span[@id='select2-Model-container']//following::input[@type='search']"))
		.sendKeys("Sales Order" +Keys.ENTER);
		click(as.Fetch);
		/*	Thread.sleep(2000);
		click(as.ChooseApproverandRecommender);
		driver.findElement(By.xpath("//span[@id='select2-UserId-container']//following::input[@type='search']"))
		.sendKeys("Kiran01" +Keys.ENTER);
		click(as.ChooseStatus);
		driver.findElement(By.xpath("//span[@id='select2-StatusId-container']//following::input[@type='search']"))
		.sendKeys("Approver" +Keys.ENTER);*/
		Thread.sleep(2000);

		LocalDateTime now = LocalDateTime.now();

		// 🎯 Target minute and second (e.g., trigger at mm:ss = 12:55)
		int targetMinute = 28;
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
				click(as.Save);
				System.out.println("Approval Setting Save Successfull");

				isWait = false;
			}

			try {
				Thread.sleep(500); // Check twice per second for better timing
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Ignore
	@Test
	public void DeliveryVehicleAssign() throws InterruptedException {

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
		Login lo = new Login(driver);
		DeliveryVehicleAssign dva = new DeliveryVehicleAssign(driver);

		Sendkeys(lo.CompanyCode, "UITDEMO1");
		Sendkeys(lo.UserName, "kiran04");
		Sendkeys(lo.Password, "Adaptive*123");
		Thread.sleep(1000);
		click(lo.LoginButton);
		Thread.sleep(2000);

		driver.navigate().to(URL +"SalesPurchases/DeliveryVehicleAssign/DeliveryVehicleAssignIndex");
		Thread.sleep(4000);

		click(dva.Clear);
		click(dva.Fetch);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table[@id='DeliveryVehicleAssignTable']//tbody//tr//td[1]")).click();
		click(dva.ChooseVehicle);
		driver.findElement(By.xpath("//span[@id='select2-VehicleId-container']//following::input[@type='search'][3]"))
		.sendKeys("TN 45 AF 8888" +Keys.ENTER);
		Thread.sleep(2000);
		click(dva.ChooseDriver);
		Sendkeys(dva.ChooseDriver, "Siva" +Keys.ENTER);
		Thread.sleep(2000);


		LocalDateTime now = LocalDateTime.now();

		// 🎯 Target minute and second (e.g., trigger at mm:ss = 12:55)
		int targetMinute = 28;
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
				click(dva.Assign);
				click(dva.PopupOK);
				System.out.println("Delivery Vehicle Assign Save Successfull");

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
