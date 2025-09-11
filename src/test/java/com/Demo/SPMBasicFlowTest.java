package com.Demo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import com.PomClass.CreditNotes;
import com.PomClass.Customer;
import com.PomClass.Product;
import com.PomClass.PurchaseOrder;
import com.PomClass.SalesOrder;
import com.PomClass.SalesInvoice;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SPMBasicFlowTest extends BaseClass{

	private String url;

	@Test(priority = 1)
	public void ERPLoginPage() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://erp.dev1.adaptivegroups.asia/");
		url = "https://erp.dev1.adaptivegroups.asia/ERP/";

		PurchaseOrder po = new PurchaseOrder(driver);

		Sendkeys(po.CompanyCode, "MMS");
		Sendkeys(po.UserName, "admin");
		Sendkeys(po.Password, "Admin*123");
		click(po.LoginButton);
		Thread.sleep(2000);

	}

	@Ignore
	@Test(priority = 4)
	public void CustomerCreate() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.navigate().to(url + "SalesPurchases/Customer");
		Thread.sleep(4000);
		System.out.println("**Customer Page**");

		Customer cu = new Customer(driver);

		driver.findElement(By.id("Create")).click();
		Thread.sleep(2000);
		click(cu.code);
		Sendkeys(cu.code, "CUS-1209");
		click(cu.Name);
		Sendkeys(cu.Name, "Sona Malligai");
		Thread.sleep(1000);
		click(cu.salesman);
		driver.findElement(By.xpath("//span[@id='select2-SalesManId-container']//following::input[@type='search']"))
		.sendKeys("HARI" +Keys.ENTER);
		Thread.sleep(3000);
		click(cu.save);

	}

	@Ignore
	@Test(priority = 6)
	public void ProductCreate() throws InterruptedException, IOException {

		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.navigate().to(url +"SalesPurchases/Product");
		Thread.sleep(4000); 
		Product pr = new Product(driver);

		click(pr.AddProduct);
		Thread.sleep(2000);
		click(pr.ProductCode);
		Sendkeys(pr.ProductCode, "CUS-1209");
		click(pr.ProductName);
		Sendkeys(pr.ProductName, "KITKAT CHOCOLATE");
		click(pr.Department);
		driver.findElement(By.xpath("//span[@id='select2-DepartmentId-container']//following::input[@type='search']"))
		.sendKeys("SNACKS" +Keys.ENTER);
		Thread.sleep(2000);
		click(pr.Category);
		driver.findElement(By.xpath("//span[@id='select2-CategoryId-container']//following::input[@type='search']"))
		.sendKeys("BISCUITS" +Keys.ENTER);
		Thread.sleep(2000);
		click(pr.Brand);
		driver.findElement(By.xpath("//span[@id='select2-BrandId-container']//following::input[@type='search']"))
		.sendKeys("HORLICKS" +Keys.ENTER);
		Thread.sleep(2000);
		//Info Tab
		click(pr.VendorName);
		driver.findElement(By.xpath("//span[@id='select2-VendorId-container']//following::input[@type='search']"))
		.sendKeys("BARKATH STORES PTE LTD" +Keys.ENTER);
		Thread.sleep(1000);	
		/*	WebElement BatchCheckBox = driver.findElement(By.className("icheckbox_square-blue icheck-item icheck[5lbnz] checked"));
		System.out.println("Batch Is:"+BatchCheckBox);
		if (!BatchCheckBox.isSelected()) {

			BatchCheckBox.click();

		}*/
		Thread.sleep(2000);
		//Stock Tab
		driver.findElement(By.xpath("//a[text()='Stock']")).click();
		Thread.sleep(1000);
		click(pr.UOM);
		driver.findElement(By.xpath("//span[@id='select2-UOMId-container']//following::input[@type='search']"))
		.sendKeys("CAN" +Keys.ENTER);
		//	WebElement CartonCheckBox = driver.findElement(By.className("icheckbox_square-blue icheck-item icheck[tgdwi]"));
		//	System.out.println("Carton Is:"+CartonCheckBox);
		WebElement SubUom = driver.findElement(By.xpath("//table[@id='ProductPartialUOM']//tbody//tr//td[2]//span[@id='select2-uom-container']"));
		SubUom.click();
		driver.findElement(By.xpath("//span[@id='select2-uom-container']//following::input[@type='search']"))
		.sendKeys("1X180ML" +Keys.ENTER);
		driver.findElement(By.xpath("(//table[@id='ProductPartialUOM']//tbody//tr//td//a[@onclick='addRow()'])[1]"))
		.click();
		WebElement SubUom1 = driver.findElement(By.xpath("//table[@id='ProductPartialUOM']//tbody//tr[2]//td[2]//span[@id='select2-uom-container']"));
		SubUom1.click();
		driver.findElement(By.xpath("//span[@id='select2-uom-container']//following::input[@type='search']"))
		.sendKeys("48X180ML" +Keys.ENTER);
		Thread.sleep(3000);
		click(pr.Save);

	}

	@Ignore
	@Test(priority = 8)
	public void SalesOrderCreateSplPriceandPackingPrint() throws InterruptedException, IOException {		

		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		System.out.println("*Sales Order Create Special Price and Packing Print*");

		driver.navigate().to(url +"SalesPurchases/SalesOrderIndex");
		Thread.sleep(4000); 
		SalesOrder so = new SalesOrder(driver);

		click(so.AddOrder);
		//	WebElement createbtn = driver.findElement(By.xpath("//input[@id='Create']"));
		//	js.executeScript("arguments[0].click();", createbtn);
		Thread.sleep(2000);
		js.executeScript("document.body.style.zoom='90%'");

		click(so.Customer);
		driver.findElement(By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
		.sendKeys("CUS-1209" +Keys.ENTER);
		Thread.sleep(2000);
		click(so.Product);
		driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
		.sendKeys("KITKAT CHOCOLATE" +Keys.ENTER);
		click(so.Qty);
		Thread.sleep(1000);
		click(so.Uom);
		driver.findElement(By.xpath("//span[@id='select2-UOMId-container']//following::input[@type='search']"))
		.sendKeys("1X180ML" +Keys.ENTER);
		Thread.sleep(2000);
		click(so.Qty);
		Sendkeys(so.Qty, "2");
		Thread.sleep(2000);
		click(so.UnitPrice);
		so.UnitPrice.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
		Sendkeys(so.UnitPrice, "50"); 
		Thread.sleep(2000);
		click(so.Add);
		Thread.sleep(3000);

		WebElement IsSpecialPriceCheckBox = driver.findElement(By.xpath("//table[@id='OrderTable']//tbody//tr//td[2]//following-sibling::td[8]//input[@id='IsSpecialPrice']"));
		IsSpecialPriceCheckBox.click();

		WebElement LSpecialPrice = driver.findElement(By.xpath("//table[@id='OrderTable']//tbody//tr//td[2]//following-sibling::td[11]//input[@id='LSpecialPrice']"));
		LSpecialPrice.click();
		LSpecialPrice.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
		LSpecialPrice.sendKeys("30" +Keys.ENTER);

		Thread.sleep(3000);
		click(so.Save);

	/*	Thread.sleep(3000);
		driver.findElement(By.xpath("//table[@id='ordertable']//tbody//tr[1]//td[12]//a[@title='Details']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm dropdown-toggle']")).click();
		
		WebElement PackingPrint = driver.findElement(By.xpath("//ul[@class='dropdown-menu rows']//li//a[@id='btnpackprint']"));
		PackingPrint.click();
		Thread.sleep(4000);*/
		
	
	}


	@Ignore
	@Test(priority = 10)
	public void Proforma() throws InterruptedException, IOException {

		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		System.out.println("*Proforma*");

		driver.navigate().to(url +"SalesPurchases/SalesOrderIndex");
		Thread.sleep(4000);

		SalesOrder so = new SalesOrder(driver);

		click(so.AddOrder);
		Thread.sleep(3000);
		WebElement ProformaInvoiceCheckBox = driver.findElement(By.id("IsProforma"));
		ProformaInvoiceCheckBox.click();
		Thread.sleep(2000);

		click(so.Customer);
		driver.findElement(By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
		.sendKeys("CUS-1209" +Keys.ENTER);

		Thread.sleep(1000);
		click(so.Product);
		driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
		.sendKeys("CUS-1209" +Keys.ENTER);
		click(so.Qty);
		click(so.Uom);
		driver.findElement(By.xpath("//span[@id='select2-UOMId-container']//following::input[@type='search']"))
		.sendKeys("1X180ML" +Keys.ENTER);
		click(so.Qty);
		Sendkeys(so.Qty, "2");
		click(so.UnitPrice);
		so.UnitPrice.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
		Sendkeys(so.UnitPrice, "50"); 
		click(so.Add);
		Thread.sleep(3000);
		click(so.Save);


	}
	@Ignore
	@Test(priority = 12)
	public void SalesOrdertoInvoiceDotMatrix() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		System.out.println("*Sales Order to Invoice Dot Matrix Print*");

		driver.navigate().to(url +"SalesPurchases/SalesOrderIndex");
		Thread.sleep(4000);

		SalesOrder so = new SalesOrder(driver);
		SalesInvoice si = new SalesInvoice(driver);

		driver.findElement(By.xpath("(//table[@id='ordertable']//tbody//tr//td//a[@title='Edit'])[1]")).click();
		Thread.sleep(2000);
		click(so.ConvertInvoice);
		Thread.sleep(2000);
		WebElement batchIcon = driver.findElement(By.xpath("//table[@id='SalesTable']//tbody//tr//td[13]//a[@class='fa fa-folder-open Popup']"));
		js.executeScript("arguments[0].click();", batchIcon);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//table[@class='table table-bordered Mytable']/following::button[text()='Add']")).click();
		Thread.sleep(2000);
		click(si.Save);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//table[@id='Invoicetable']//tbody//tr//td[11]//a[@title='Details'])[1]")).click();
		Thread.sleep(2000);
	//	click(si.DotMatrix);
	//	click(so.PopupOkAlert);
	//	Thread.sleep(1000);
	/*	WebElement DotMatrix = driver.findElement(By.id("popup_prompt"));
		DotMatrix.click();
		DotMatrix.sendKeys("123");
		click(so.PopupOkAlert);*/


	}

	@Ignore
	@Test(priority = 14)
	public void SalesInvoicetoCreditNote() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		System.out.println("*Sales Invoice to Credit Notes*");

		driver.navigate().to(url +"SalesPurchases/SalesInvoice");
		Thread.sleep(4000);

		SalesInvoice si = new SalesInvoice(driver);
		CreditNotes cn = new CreditNotes(driver);

		driver.findElement(By.xpath("//table[@id='Invoicetable']//tbody//tr//td[11]//a[@title='Details']")).click();
		Thread.sleep(2000);
		click(si.IssueCreditNote);
		click(si.PopupOk);
		Thread.sleep(3000); 
		click(cn.Save);

	}

	@Ignore
	@Test(priority = 16)
	public void QuestionMarkArea() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		System.out.println("*Question Mark Area*");

		//Sales Order
		driver.navigate().to(url +"SalesPurchases/SalesOrderIndex");
		Thread.sleep(4000);
		js.executeScript("document.body.style.zoom='90%'");

		driver.findElement(By.xpath("(//table[@id='ordertable']//tbody//tr//td[12]//a[@title='Edit'])[1]")).click();
		Thread.sleep(3000);
		boolean SOQusMarkSymbole = driver.findElement(By.xpath("//table[@id='OrderTable']//tbody//tr//td[2]//following-sibling::td[14]//i[@class='fa fa-question-circle fa-lg']")).isDisplayed();
		System.out.println("Sales Order Question Mark Symbole Is:"+SOQusMarkSymbole);
		Thread.sleep(1000);
		String SoQusMarkValue = driver.findElement(By.xpath("//span[@class='large-tooltip']")).getAttribute("title");
		System.out.println("Sales Order Question Mark Value Is :"+SoQusMarkValue.trim());
		Thread.sleep(3000);

		//Sales Invoice
		/*	driver.navigate().to(url +"SalesPurchases/SalesInvoice");
		Thread.sleep(4000);
		js.executeScript("document.body.style.zoom='90%'");

		driver.findElement(By.xpath("(//table[@id='Invoicetable']//tbody//tr//td[11]//a[@title='Edit'])[1]"));
		Thread.sleep(3000);*/


		//Credit Note
		driver.navigate().to(url +"SalesPurchases/CreditNotes");
		Thread.sleep(4000);
		js.executeScript("document.body.style.zoom='90%'");

		driver.findElement(By.xpath("(//table[@id='credittable']//tbody//tr//td[11]//a[@title='Edit'])[1]")).click();
		Thread.sleep(3000);
		boolean CNQusMarkSymbole = driver.findElement(By.xpath("//table[@id='CreditNoteTable']//tbody//tr//td[2]//following-sibling::td//i[@class='fa fa-question-circle fa-lg']")).isDisplayed();
		System.out.println("Credit Notes Question Mark Symbole Is:"+CNQusMarkSymbole);
		Thread.sleep(1000);
		String CNQusMarkValue = driver.findElement(By.xpath("//span[@class='large-tooltip']")).getAttribute("title");
		System.out.println("Credit Notes Question Mark Value Is :"+CNQusMarkValue.trim());
		Thread.sleep(3000);

	}

	@Ignore
	@Test(priority = 18)
	public void SalesOrderTabandEnter() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		System.out.println("*Sales Order Tab and Enter*");

		SalesOrder so = new SalesOrder(driver);
		driver.navigate().to(url +"SalesPurchases/SalesOrderIndex");
		Thread.sleep(4000);

		click(so.AddOrder);
		Thread.sleep(2000);

		click(so.Customer);
		driver.findElement(By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
		.sendKeys("CUS-1209" +Keys.ENTER);
		Thread.sleep(2000);

		click(so.Product);
		driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
		.sendKeys("KITKAT CHOCOLATE" +Keys.ENTER);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//span[@id='select2-UOMId-container']//following::input[@type='search']"))
		.sendKeys("1X180ML" +Keys.ENTER);
		Thread.sleep(2000);

		WebElement qty = driver.switchTo().activeElement();
		qty.sendKeys("2");

		WebElement activeElement = driver.switchTo().activeElement();
		activeElement.sendKeys(Keys.TAB);

		WebElement unitprice = driver.switchTo().activeElement();
		unitprice.sendKeys("20");

		WebElement activeElement1 = driver.switchTo().activeElement();
		activeElement1.sendKeys(Keys.TAB);

		WebElement activeElement2 = driver.switchTo().activeElement();
		activeElement2.sendKeys(Keys.TAB);

		WebElement activeElement3 = driver.switchTo().activeElement();
		activeElement3.sendKeys(Keys.TAB);

		WebElement activeElement4 = driver.switchTo().activeElement();
		activeElement4.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		WebElement add = driver.switchTo().activeElement();
		add.sendKeys(Keys.ENTER);

		click(so.Product);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table[@id='OrderTable']//tbody//tr//td[2]")).click();

		Thread.sleep(1000);
		WebElement activeElement5 = driver.switchTo().activeElement();
		activeElement5.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		WebElement activeElement6 = driver.switchTo().activeElement();
		activeElement6.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		WebElement activeElement7 = driver.switchTo().activeElement();
		activeElement7.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		WebElement activeElement8 = driver.switchTo().activeElement();
		activeElement8.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		WebElement activeElement9 = driver.switchTo().activeElement();
		activeElement9.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		WebElement activeElement10 = driver.switchTo().activeElement();
		activeElement10.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		WebElement activeElement11 = driver.switchTo().activeElement();
		activeElement11.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		WebElement activeElement12 = driver.switchTo().activeElement();
		activeElement12.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		WebElement activeElement13 = driver.switchTo().activeElement();
		activeElement13.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		WebElement activeElement14 = driver.switchTo().activeElement();
		activeElement14.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		WebElement activeElement15 = driver.switchTo().activeElement();
		activeElement15.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		WebElement activeElement16 = driver.switchTo().activeElement();
		activeElement16.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		WebElement activeElement17 = driver.switchTo().activeElement();
		activeElement17.sendKeys(Keys.TAB);

		Thread.sleep(3000);
		click(so.Save);






	}


}
