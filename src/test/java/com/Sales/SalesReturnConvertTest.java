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
import com.PomClass.CreditNotes;
import com.PomClass.Customer;
import com.PomClass.Login;
import com.PomClass.SalesReturn;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesReturnConvertTest extends BaseClass {
	
	
	private String URL;
	
	@Test
	public void Test1() throws InterruptedException {
				
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.get("https://erp.dev1.adaptivegroups.asia/");
		URL = "https://erp.dev1.adaptivegroups.asia/ERP/";
		String ActURL = driver.getCurrentUrl();
		boolean equals = URL.equalsIgnoreCase(ActURL);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Login lo = new Login(driver);
		Customer cu = new Customer(driver);
		SalesReturn sr = new SalesReturn(driver);
		CreditNotes cn = new CreditNotes(driver);
		
		Sendkeys(lo.CompanyCode, "UITDEMO1");  
		Sendkeys(lo.UserName, "Kiran02");
		Sendkeys(lo.Password, "Adaptive*123");
		Thread.sleep(1000);
		click(lo.LoginButton);
		Thread.sleep(1000);
		Thread.sleep(2000);
		driver.navigate().to(URL + "SalesPurchases/SalesReturn");
		Thread.sleep(4000);
		
		WebElement editIcon = driver.findElement(By.xpath("(//table[@id='salesreturntable']"
				+ "//tbody//tr//td[2][contains(text(),'smm-01788-sr')]//following::td[8]//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", editIcon);
		Thread.sleep(1000);
		click(sr.IssueCreditNote);
		click(sr.PopupOk);
		Thread.sleep(2000);
		
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
		
		click(sr.Save);
		
		
	}
	
	@Test
	public void Test2() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		driver.get("https://erp.dev1.adaptivegroups.asia/");
		URL = "https://erp.dev1.adaptivegroups.asia/ERP/";
		String ActURL = driver.getCurrentUrl();
		boolean equals = URL.equalsIgnoreCase(ActURL);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Login lo = new Login(driver);
		Customer cu = new Customer(driver);
		SalesReturn sr = new SalesReturn(driver);
		CreditNotes cn = new CreditNotes(driver);
		
		Sendkeys(lo.CompanyCode, "UITDEMO1");  
		Sendkeys(lo.UserName, "Kiran02");
		Sendkeys(lo.Password, "Adaptive*123");
		Thread.sleep(1000);
		click(lo.LoginButton);
		Thread.sleep(1000);
		Thread.sleep(2000);
		driver.navigate().to(URL + "SalesPurchases/SalesReturn");
		Thread.sleep(4000);
		
		WebElement editIcon = driver.findElement(By.xpath("(//table[@id='salesreturntable']"
				+ "//tbody//tr//td[2][contains(text(),'smm-01788-sr')]//following::td[8]//a[@title='Details'])[1]"));
		js.executeScript("arguments[0].click();", editIcon);
		Thread.sleep(1000);
		click(sr.IssueCreditNote);
		click(sr.PopupOk);
		Thread.sleep(2000);
		
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
		
		click(sr.Save);
		
	}
	
	
}
