package com.Sales;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import com.PomClass.Customer;
import com.PomClass.Login;
import com.PomClass.SmmPI;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SOconvert extends BaseClass {

	private String URL;

	@Test
	public void test1() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.get("https://erp.dev1.adaptivegroups.asia/");
		URL = "https://erp.dev1.adaptivegroups.asia/ERP/";
		String ActURL = driver.getCurrentUrl();
		boolean equals = URL.equalsIgnoreCase(ActURL);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Login lo = new Login(driver);
		Customer cu = new Customer(driver);
		SmmPI spi = new SmmPI(driver);

		Sendkeys(lo.CompanyCode, "UITDEMO1");  
		Sendkeys(lo.UserName, "Kiran02");
		Sendkeys(lo.Password, "Adaptive*123");
		Thread.sleep(1000);
		click(lo.LoginButton);
		Thread.sleep(1000);
		Thread.sleep(2000);
		driver.navigate().to(URL + "SalesPurchases/SalesInvoice");
		Thread.sleep(4000);

		WebElement soedit = driver.findElement(By.xpath(
				"(//table[@id='Invoicetable']//tbody//tr//td[3][contains(text(),'SFS/INV/2024-00481')]//following::td[9]//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", soedit);
		Thread.sleep(1000);
		
		/*
		 * click(cu.Copyinvoice); Thread.sleep(2000);
		 */
		
	/*	WebElement convertinvoice = driver.findElement(By.id("ConvertInvoice"));
		convertinvoice.click();  
		
		click(spi.Ok); */
		
		LocalDateTime now = LocalDateTime.now();
		 
        // Target: Wait until seconds reach 55
        int targetSecond = 30;
        
        boolean isWait = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
 
        while (isWait) {
            LocalDateTime now2 = LocalDateTime.now();
            String formattedTime = now2.format(formatter);
 
            System.out.println("Current Time: " + formattedTime);
 
            // Compare seconds value instead of formatted strings
            if (now2.getSecond() == targetSecond) {
                System.out.println("Reached target second: " + targetSecond);
                isWait = false;  // Exit loop
            }
 
            try {
                Thread.sleep(1000); // Wait 1 second to avoid CPU overuse
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
 

		WebElement savebtn = driver.findElement(By.xpath("//div[@class='btn-group']//button[text()='Save & Close']"));
		js.executeScript("arguments[0].click();", savebtn);
		Thread.sleep(1000);
	//	click(spi.Ok);


	}

	@Test
	public void test2() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.get("https://erp.dev1.adaptivegroups.asia/");
		URL = "https://erp.dev1.adaptivegroups.asia/ERP/";
		String ActURL = driver.getCurrentUrl();
		boolean equals = URL.equalsIgnoreCase(ActURL);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Login lo = new Login(driver);
		Customer cu = new Customer(driver);
		SmmPI spi = new SmmPI(driver);

		Sendkeys(lo.CompanyCode, "UITDEMO1");  
		Sendkeys(lo.UserName, "Kiran02");
		Sendkeys(lo.Password, "Adaptive*123");
		Thread.sleep(1000);
		click(lo.LoginButton);
		Thread.sleep(1000);
		Thread.sleep(2000);
		
		driver.navigate().to(URL + "SalesPurchases/SalesInvoice");
		Thread.sleep(4000);
		
		

		WebElement soedit = driver.findElement(By.xpath(
				"(//table[@id='Invoicetable']//tbody//tr//td[3][contains(text(),'SFS/INV/2024-00481')]//following::td[9]//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", soedit);
		Thread.sleep(1000);
		
		/*
		 * click(cu.Copyinvoice); Thread.sleep(2000);
		 */
		
	/*	WebElement convertinvoice = driver.findElement(By.id("ConvertInvoice"));
		convertinvoice.click();  	
		click(spi.Ok); */
		
		LocalDateTime now = LocalDateTime.now();
		 
        // Target: Wait until seconds reach 55
        int targetSecond = 30;
         
        
        boolean isWait = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
 
        while (isWait) {
            LocalDateTime now2 = LocalDateTime.now();
            String formattedTime = now2.format(formatter);
 
            System.out.println("Current Time: " + formattedTime);
 
            // Compare seconds value instead of formatted strings
            if (now2.getSecond() == targetSecond) {
                System.out.println("Reached target second: " + targetSecond);
                isWait = false;  // Exit loop
            }
 
            try {
                Thread.sleep(1000); // Wait 1 second to avoid CPU overuse
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
		WebElement savebtn = driver.findElement(By.xpath("//div[@class='btn-group']//button[text()='Save & Close']"));
		js.executeScript("arguments[0].click();", savebtn);
		Thread.sleep(1000);
//		click(spi.Ok);
	


	}

}
