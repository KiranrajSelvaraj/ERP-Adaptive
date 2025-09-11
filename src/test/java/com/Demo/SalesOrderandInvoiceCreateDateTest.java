package com.Demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.BaseClass.BaseClass;
import com.PomClass.PurchaseOrder;
import com.PomClass.SalesOrder;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesOrderandInvoiceCreateDateTest extends BaseClass{
	
	private String url;
	
	private String companyName;
	
	@Parameters({"Company" })
	@BeforeTest
	private void Environment(String Company) {
 
		if (Company.equals("cnh")) {
				
			companyName = "cnh";
			System.out.println("Login into " + companyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://cnh.adaptivebizapp.com/");
			url = "https://cnh.adaptivebizapp.com/";
 
		}else if (Company.equals("hwc")) {
			
			companyName = "hwc";
			System.out.println("Login into " + companyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://hwc.adaptivebizapp.com/Account/Login?ReturnUrl=%2f");
			url = "http://hwc.adaptivebizapp.com/";
 
		}else if (Company.equals("sanmar")) {
			
			companyName = "sanmar";
			System.out.println("Login into " + companyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://sanmar.adaptivebizapp.com/account/login");
			url = "https://sanmar.adaptivebizapp.com/erp/";
			
		}else if (Company.equals("sanaf")) {
			
			companyName = "sanaf";
			System.out.println("Login into " + companyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://sanaf.adaptivebizapp.com/account/login?ReturnUrl=%2f");
			url = "https://sanaf.adaptivebizapp.com/erp/";
			
		}else if (Company.equals("artdecor")) {
			
			companyName = "artdecor";
			System.out.println("Login into " + companyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://artdeco.adaptivebizapp.com/account/login?ReturnUrl=%2f");
			url = "https://artdeco.adaptivebizapp.com/erp/";
			
		}else if (Company.equals("encore")) {
			
			companyName = "encore";
			System.out.println("Login into " + companyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://encore.adaptivebizapp.com/account/login?ReturnUrl=%2f");
			url = "https://encore.adaptivebizapp.com/erp/";
			
		}else if (Company.equals("spm")) {
			
			companyName = "spm";
			System.out.println("Login into " + companyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://spm.adaptivebizapp.com/account/login?ReturnUrl=%2f");
			url = "https://spm.adaptivebizapp.com/erp/";
			
		}else if (Company.equals("smu")) {
			
			companyName = "smu";
			System.out.println("Login into " + companyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://smu.adaptivebizapp.com/Account/Login");
			url = "http://smu.adaptivebizapp.com/";
			
		}else if (Company.equals("sass")) {
			
			companyName = "sass";
			System.out.println("Login into " + companyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://sass.adaptivebizapp.com/");
			url = "https://sass.adaptivebizapp.com/";
			
		}else if (Company.equals("wst")) {
			
			companyName = "wst";
			System.out.println("Login into " + companyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://wst.adaptivebizapp.com/account/login?ReturnUrl=%2f");
			url = "https://wst.adaptivebizapp.com/erp/";
			
		}else if (Company.equals("osm")) {
			
			companyName = "osm";
			System.out.println("Login into " + companyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://osm.adaptivebizapp.com/Account/Login");
			url = "https://osm.adaptivebizapp.com/erp/";
			
		}else if (Company.equals("sae25")) {
			
			companyName = "sae25";
			System.out.println("Login into " + companyName + " Company");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://shreeammannew.adaptivebizapp.com/account/login");
			url = "https://shreeammannew.adaptivebizapp.com/erp/";
			
		}	
 
	}

	@Test(priority = 1)
	public void ERPLoginPage() throws InterruptedException {

		PurchaseOrder po = new PurchaseOrder(driver);

		Sendkeys(po.CompanyCode, companyName);
		Sendkeys(po.UserName, "support");	
		Thread.sleep(1000);
		if (companyName.equalsIgnoreCase("hwc")) {
			Sendkeys(po.Password, "Adaptive*123");
		}else {
			Sendkeys(po.Password, "#Apr^l6$up51Y22k5");
		}	
		Thread.sleep(1000);
		click(po.LoginButton);
		Thread.sleep(2000);
		
		WebElement Skipelement = driver.findElement(By.xpath("(//input[@name='SkipValidateCode'])[1]"));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Skipelement);
		Thread.sleep(1000);
		
		driver.findElement(By.id("login")).click();
		Thread.sleep(4000);

		System.out.println("*ERP Login Page*");
		
		if (companyName.equals("artdecor")) {
			
			WebElement artDecorCmpny = driver.findElement(
					By.xpath("//center//img[@alt='companylogo']//following::div//h5[contains(text(),'ARTDECO - Artdecor Design Studio Pte Ltd')]"));
			artDecorCmpny.click();
			Thread.sleep(4000);
		}
	}
	
//	@Ignore
	@Test(priority = 4, dependsOnMethods = "ERPLoginPage")
	public void SalesOrderDate() throws InterruptedException {
		
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		SalesOrder so = new SalesOrder(driver);

		driver.navigate().to(url + "SalesPurchases/SalesOrderIndex");
		Thread.sleep(4000);
		System.out.println("**Sales Order Page**");
		
		if (companyName.equals("artdecor")) {
			
			String companyName = driver.findElement(By.xpath("//select[@id='CompanyList']//option[@selected='selected']")).getText();
			System.out.println("Company Name :"+companyName);
			
		}else {
			
			String companyName = driver.findElement(By.xpath("(//a//child::span)[4]")).getText();
			System.out.println("Company Name :"+companyName);
		}
		
		
		if (companyName.equals("smu")) {
			
			driver.findElement(By.id("select2-CurrencyCode-container")).click();
			driver.findElement(By.xpath("//span[@id='select2-CurrencyCode-container']//following::input[@type='search'][2]"))
			.sendKeys("All Currency" +Keys.ENTER);
			Thread.sleep(1000);
			click(so.Fetch);
			Thread.sleep(2000);
		}
					
		WebElement soDetails = driver.findElement(By.xpath("(//table[@id='ordertable']//tbody//tr[1]//following::td//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", soDetails);
		Thread.sleep(1000);
		
		String createdBy = driver.findElement(By.xpath("//dt[contains(text(),'Created By')]//following::dd[1]")).getText();
		System.out.println("Sales Order Created By :"+createdBy);
		
		String createdDate = driver.findElement(By.xpath("//dt[contains(text(),'Created Date')]//following::dd[1]")).getText();
		System.out.println("Sales Order Created Date :"+createdDate);
		
		System.out.println("***");
	}
//	@Ignore
	@Test(priority = 6, dependsOnMethods = "ERPLoginPage")
	public void SalesInvoiceDate() throws InterruptedException {
		
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		SalesOrder so = new SalesOrder(driver);

		driver.navigate().to(url + "SalesPurchases/SalesInvoice");
		Thread.sleep(4000);
		System.out.println("**Sales Invoice Page**");
		
		if (companyName.equals("smu")) {
			
			driver.findElement(By.id("select2-CurrencyCode-container")).click();
			driver.findElement(By.xpath("//span[@id='select2-CurrencyCode-container']//following::input[@type='search'][2]"))
			.sendKeys("All Currency" +Keys.ENTER);
			Thread.sleep(1000);
			click(so.Fetch);
			Thread.sleep(2000);
		}
		
		WebElement siDetails = driver.findElement(By.xpath("(//table[@id='Invoicetable']//tbody//tr[1]//following::td//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", siDetails);
		Thread.sleep(1000);
		
		String date = driver.findElement(By.xpath("(//div//dl//dt[contains(text(),'Date')])[1]//following::dd[1]")).getText();
		System.out.println("Sales Invoice Date :"+date);
		
		String createdBy = driver.findElement(By.xpath("//dt[contains(text(),'Created By')]//following::dd[1]")).getText();
		System.out.println("Sales Invoice Created By :"+createdBy);
		
		String createdDate = driver.findElement(By.xpath("//dt[contains(text(),'Created Date')]//following::dd[1]")).getText();
		System.out.println("Sales Inoice Created Date :"+createdDate);
		
		System.out.println("***");
		Thread.sleep(3000);
	}
	
	
	

}
