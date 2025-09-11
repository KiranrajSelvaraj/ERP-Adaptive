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
import org.testng.asserts.SoftAssert;

import com.BaseClass.BaseClass;
import com.PomClass.MobileVanTransfers;
import com.PomClass.PurchaseOrder;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileVanTransferCreateTest extends BaseClass{

	private String url;

	SoftAssert soft = new SoftAssert();

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
		Object[][] data = Util1.getTestData("C:\\Adaptive\\ERP\\MobileVanTransfers.xlsx", "Sheet1");
		return data;

	}

	class ExcelData {

		private String SNo;
		private String FromWarehouse;
		private String ToMobileVan;
		private String Product;
		private String Uom;
		private String Qty;
		private String BatchProduct;


		public ExcelData(String SNo, String FromWarehouse, String ToMobileVan, String Product, String Uom, String Qty, String BatchProduct) {
			super();

			this.SNo = SNo;
			this.FromWarehouse = FromWarehouse;
			this.ToMobileVan = ToMobileVan;
			this.Product = Product;
			this.Uom = Uom;
			this.Qty = Qty;
			this.BatchProduct = BatchProduct;

		}

	}

	ArrayList<ExcelData> dataList = new ArrayList<>();

	@Test(priority = 4, dataProvider = "Util2", dependsOnMethods = "ERPLoginPage")
	public void GetData(String SNo, String FromWarehouse, String ToMobileVan, String Product, String Uom, String Qty, String BatchProduct) {

		ExcelData data = new ExcelData(SNo, FromWarehouse, ToMobileVan, Product, Uom, Qty, BatchProduct);

		dataList.add(data);

	}

	@Test(priority = 6, dependsOnMethods = "ERPLoginPage")
	public void MobileTransfers() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		MobileVanTransfers mt = new MobileVanTransfers(driver);

		driver.navigate().to(url + "SalesPurchases/MobileVanTransfers");
		Thread.sleep(4000);
		System.out.println("**MobileVan Transfers Page**");

		click(mt.CreateNew);
		Thread.sleep(2000);

		int excelDataListSize = dataList.size();
		System.out.println("Excel Data List Size Is:" + excelDataListSize);
		for (int i = 0; i < excelDataListSize; i++) {
			ExcelData excelData = dataList.get(i);

			/*	click(mt.FromWarehouse);
				driver.findElement(By.xpath("//span[@id='select2-FromWarehouseId-container']//following::input[@type='search']"))
				.sendKeys(excelData.FromWarehouse +Keys.ENTER); 
			 */

			String toMobileVanText = driver.findElement(By.id("select2-ToMobileVanId-container")).getAttribute("title");
			System.out.println("To MobileVan Text Is: "+toMobileVanText);
			if (!toMobileVanText.equalsIgnoreCase(excelData.ToMobileVan)) {

				click(mt.ToMobileVan);
				driver.findElement(By.xpath("//span[@id='select2-ToMobileVanId-container']//following::input[@type='search']"))
				.sendKeys(excelData.ToMobileVan +Keys.ENTER);
				Thread.sleep(1000);
			}
			
			if (i < 4) {
				
				click(mt.Product);
				driver.findElement(By.xpath("//span[@id='select2-Product-container']//following::input[@type='search']"))
				.sendKeys(excelData.Product +Keys.ENTER);
				click(mt.Qty);
				click(mt.UOM);
				List<WebElement> subUomOptions = driver.findElements(By.xpath("//span[@id='select2-UOM-container']//following::ul//li"));
				for (WebElement option : subUomOptions) {
					if (option.getText().trim().equals(excelData.Uom)) {
						option.click();
						break;	
					}	
				}
				click(mt.Qty);
				Sendkeys(mt.Qty, excelData.Qty);
				Thread.sleep(2000);
				click(mt.Add);
				Thread.sleep(2000);

				if (excelData.BatchProduct.equalsIgnoreCase("true")) {

					driver.findElement(By.xpath("(//div//strong[contains(text(),'"+excelData.Product
							+"')]//following::button[text()='Add'])[1]")).click();
				}
				
			}else {
				
				if (i >= 4) {
					
					Thread.sleep(2000);
					click(mt.AddItems);
					Thread.sleep(2000);
					click(mt.AddItemsDepartment);
					driver.findElement(By.xpath("//span[@id='select2-DepartmentId-container']//following::input[@type='search']"))
					.sendKeys("DRINK" +Keys.ENTER);
					Thread.sleep(1000);
					WebElement addItemSearchBox = driver.findElement(By.xpath("//div//child::input[@placeholder='Search...']"));
					addItemSearchBox.click();
					addItemSearchBox.sendKeys(excelData.Product);
					Thread.sleep(1000);
					WebElement lQtyBox = driver.findElement(By.xpath("(//table[@id='show-product-table']//tbody//tr//td[contains(text(),'"
					+excelData.Product+"')]//following::td[contains(text(),'"+excelData.Uom+"')]//following::input[@id='LQty'])[1]"));
					lQtyBox.click();
					lQtyBox.sendKeys(excelData.Qty);
					Thread.sleep(2000);
					click(mt.AddItemsAdd);
					Thread.sleep(2000);
				}
					
			}
	
		}
		Thread.sleep(2000);
	//	click(mt.Save);
		System.out.println("**Mobile Van Transfers Save Successfull**");

	}





}
