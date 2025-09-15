package com.Demo;

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
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import com.Demo.SalesInvoiceCreateTest.ExcelData;
import com.PomClass.Login;
import com.PomClass.PurchaseOrder;
import com.PomClass.SalesInvoice;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesInvoiceCreateTest extends BaseClass{

	private String url;

	@Test(priority = 1)
	public void ERPLoginPage() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://erp.dev1.adaptivegroups.asia/ERP/Account/Login");
		url = "https://erp.dev1.adaptivegroups.asia/ERP/";

		Login lo = new Login(driver);

		Sendkeys(lo.CompanyCode, "UITDEMO1");
		Sendkeys(lo.UserName, "Kiran01");
		Sendkeys(lo.Password, "Adaptive*123");
		click(lo.LoginButton);
		Thread.sleep(2000);

		String ActURL = driver.getCurrentUrl();
		boolean equals = url.equalsIgnoreCase(ActURL);

		if (equals == false) {

			Navigate_to(ActURL);
			Sendkeys(lo.CompanyCode, "UITDEMO1");
			Sendkeys(lo.UserName, "Kiran02");
			Sendkeys(lo.Password, "Adaptive*123");
			Thread.sleep(1000);
			click(lo.LoginButton);

		}

		String ActURL1 = driver.getCurrentUrl();
		boolean equals1 = url.equalsIgnoreCase(ActURL1);

		if (equals1 == false) {

			Navigate_to(ActURL1);
			Sendkeys(lo.CompanyCode, "UITDEMO1");
			Sendkeys(lo.UserName, "Kiran03");
			Sendkeys(lo.Password, "Adaptive*123");
			Thread.sleep(1000);
			click(lo.LoginButton);

		}

		System.out.println("*ERP Login Page*");
	}


	@DataProvider
	public Object[][] Util2() {
		Object[][] data = Util1.getTestData("C:\\Adaptive\\ERP\\SalesInvoice.xlsx", "Sheet1");
		return data;

	}

	class ExcelData {

		private String Customer;
		private String Product;
		private String Uom;
		private String Qty;
		private String Price;
		private String BatchProduct;

		public ExcelData(String Customer, String Product, String Uom, String Qty, String Price, String BatchProduct) {
			super();

			this.Customer = Customer;
			this.Product = Product;
			this.Uom = Uom;
			this.Qty = Qty;
			this.Price = Price;
			this.BatchProduct = BatchProduct;

		}

	}

	ArrayList<ExcelData> dataList = new ArrayList<>();

	@Test(priority = 4, dataProvider = "Util2", dependsOnMethods = "ERPLoginPage")
	public void GetData(String Customer, String Product, String Uom, String Qty, String Price, String BatchProduct) {

		ExcelData data = new ExcelData(Customer, Product, Uom, Qty, Price, BatchProduct);

		dataList.add(data);

	}

	@Test(priority = 6, dependsOnMethods = "ERPLoginPage")
	public void SalesInvoice() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		SalesInvoice si = new SalesInvoice(driver);

		driver.navigate().to(url + "SalesPurchases/SalesInvoice");
		Thread.sleep(4000);
		System.out.println("**Sales Invoice Page**");

		click(si.AddInvoice);
		Thread.sleep(2000);

		int excelDataListSize = dataList.size();
		System.out.println("Excel Data List Size Is:"+excelDataListSize);
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = dataList.get(i);
			Thread.sleep(2000);

			if (excelData.Customer.isEmpty() == false) {

				click(si.Customer);
				driver.findElement(By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
				.sendKeys(excelData.Customer +Keys.ENTER);
				Thread.sleep(2000);
			}

			click(si.ChooseProduct);
			driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
			.sendKeys(excelData.Product +Keys.ENTER);
			click(si.Qty);	
			
			click(si.ChooseUom);
			List<WebElement> subUomOptions = driver.findElements(By.xpath("//span[@id='select2-UOMId-container']//following::input[@type='search']//following::ul//li"));
			for (WebElement option : subUomOptions) {
				if (option.getText().trim().equals(excelData.Uom)) {
					option.click();
					break;	
				}	
			}
						
			click(si.Qty);
			Sendkeys(si.Qty, excelData.Qty);
			click(si.Price);
			si.Price.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
			Sendkeys(si.Price, excelData.Price);
			Thread.sleep(2000);
			click(si.Add);
			Thread.sleep(1000);

			if (excelData.BatchProduct.equalsIgnoreCase("true")) {

				driver.findElement(By.xpath
						("(//div//strong[contains(.,'"+excelData.Product+"')]//following::button[text()='Add'])[1]")).click();

			}

			click(si.Qty);

		}
		
		Thread.sleep(2000);
		js.executeScript("arguments[0].scrollIntoView(true);", si.Save);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", si.Save);
		System.out.println("*Sales Invoice Save Successfull*");

	}

}
