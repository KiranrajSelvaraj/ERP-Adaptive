package com.SystemSettings;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BaseClass.BaseClass;

import com.PomClass.Login;
import com.PomClass.SalesOrder;
import com.Utility.Util1;
import com.PomClass.SystemSettings;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SystemSettingsModifyandCreateTest extends BaseClass {

	String SSValue=null;

	private String url;

	SoftAssert soft = new SoftAssert();

	@BeforeTest
	@Parameters({ "env", "SS" })
	public void Environment(String env, String SS) {

		if (SS.equalsIgnoreCase("True")) {
			SSValue ="True";
		} else if (SS.equalsIgnoreCase("False")) {
			SSValue ="False";
		} else  {
			SSValue ="TandF";
		}

		if (env.equals("Dev")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://erp.dev1.adaptivegroups.asia/ERP/Account/Login");
			url = "https://erp.dev1.adaptivegroups.asia/ERP/";

		}

	}

	@Test(priority = 1)
	public void ERPLoginPage() throws InterruptedException {

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

	@DataProvider(name = "Util2")
	public Object[][] Util1() {

		Object data[][] = Util1.getTestData("C:\\Adaptive\\ERP\\SPM Full System Settings.xlsx", "Sheet1");
		return data;

	}

	@Ignore
	@DataProvider
	public Object[][] UtilSS() {

		if (SSValue.equalsIgnoreCase("True")) {
			Object data[][] = Util1.getTestData("C:\\Adaptive\\ERP\\SalesOrder.xlsx", "SST");
			return data;
		}
		else if (SSValue.equalsIgnoreCase("False")) {
			Object data[][] = Util1.getTestData("C:\\Adaptive\\ERP\\SalesOrder.xlsx", "SSF");
			return data;
		}
		else {
			Object data[][] = Util1.getTestData("C:\\Adaptive\\ERP\\SalesOrder.xlsx", "SSTF");
			return data;
		}



	}

	@SuppressWarnings("unused")
	class SystemSettingsExcelData {
		private String ParamCode;
		private String Description;
		private String ParamType;
		private String StringValue;
		private String TextArea;
		private String DateValue;
		private String BitValue;
		private String GuidValue;
		private String DecimalValue;
		private String IsCompany;
		private String Model;
		private String NumericValue;

		public SystemSettingsExcelData(String ParamCode, String Description, String ParamType, String StringValue, String TextArea, 
				String DateValue, String BitValue, String GuidValue, String DecimalValue, String IsCompany, String Model, String NumericValue) {
			super();

			this.ParamCode = ParamCode;
			this.Description = Description;
			this.ParamType = ParamType;
			this.StringValue = StringValue;
			this.TextArea = TextArea;
			this.DateValue = DateValue;
			this.BitValue = BitValue;
			this.GuidValue = GuidValue;
			this.DecimalValue = DecimalValue;
			this.IsCompany = IsCompany;
			this.Model = Model;
			this.NumericValue = NumericValue;

		}	
	}

	ArrayList<SystemSettingsExcelData> SSDataList = new ArrayList<>();

	@Test(priority = 3, dataProvider = "Util2", dependsOnMethods = "ERPLoginPage")
	public void SystemSettingsGetData(String ParamCode, String Description, String ParamType, String StringValue, String TextArea, 
			String DateValue, String BitValue, String GuidValue, String DecimalValue, String IsCompany, String Model, String NumericValue) {

		SystemSettingsExcelData SSData = new SystemSettingsExcelData(ParamCode, Description, ParamType, StringValue, TextArea, DateValue, 
				BitValue, GuidValue, DecimalValue, IsCompany, Model, NumericValue);

		SSDataList.add(SSData);

	}

	@SuppressWarnings("unused")
	class ExcelData {
		private String CustomerCode;
		private String CurrencyCode;
		private String GstType;
		private String Terms;
		private String CurrencyRate;
		private String ProductService;
		private String UOM;
		private String Qty;
		private String Foc;
		private String Price;
		private String BatchProduct;
		private String IsSpecialPriceCheckbox;
		private String CtnSplPrice;
		private String PcsSplPrice;
		private String UnitDisc;
		private String DiscountPer;
		private String DiscountAmt;
		private String DiscountModeForAllProduct;
		private String GSTPercentage;

		public ExcelData(String CustomerCode, String CurrencyCode, String GstType, String Terms, String CurrencyRate,
				String ProductService, String UOM, String Qty, String Foc, String Price, String BatchProduct,
				String IsSpecialPriceCheckbox, String CtnSplPrice, String PcsSplPrice, String UnitDisc,
				String DiscountPer, String DiscountAmt, String DiscountModeForAllProduct, String GSTPercentage) {
			super();

			this.CustomerCode = CustomerCode;
			this.CurrencyCode = CurrencyCode;
			this.GstType = GstType;
			this.Terms = Terms;
			this.CurrencyRate = CurrencyRate;
			this.ProductService = ProductService;
			this.UOM = UOM;
			this.Qty = Qty;
			this.Foc = Foc;
			this.Price = Price;
			this.BatchProduct = BatchProduct;
			this.IsSpecialPriceCheckbox = IsSpecialPriceCheckbox;
			this.CtnSplPrice = CtnSplPrice;
			this.PcsSplPrice = PcsSplPrice;
			this.UnitDisc = UnitDisc;
			this.DiscountPer = DiscountPer;
			this.DiscountAmt = DiscountAmt;
			this.DiscountModeForAllProduct = DiscountModeForAllProduct;
			this.GSTPercentage = GSTPercentage;

		}

	}

	ArrayList<ExcelData> dataList = new ArrayList<>();
	@Ignore
	@Test(priority = 4, dataProvider = "Util2", dependsOnMethods = "ERPLoginPage")
	public void GetData(String CustomerCode, String CurrencyCode, String GstType, String Terms, String CurrencyRate,
			String ProductService, String UOM, String Qty, String Foc, String Price, String BatchProduct,
			String IsSpecialPriceCheckbox, String CtnSplPrice, String PcsSplPrice, String UnitDisc, String DiscountPer,
			String DiscountAmt, String DiscountModeForAllProduct, String GSTPercentage) {

		ExcelData data = new ExcelData(CustomerCode, CurrencyCode, GstType, Terms, CurrencyRate, ProductService, UOM,
				Qty, Foc, Price, BatchProduct, IsSpecialPriceCheckbox, CtnSplPrice, PcsSplPrice, UnitDisc, DiscountPer,
				DiscountAmt, DiscountModeForAllProduct, GSTPercentage);

		dataList.add(data);

	}


	public boolean booleanselected;

	//	@Ignore
	@Test(priority = 6, dependsOnMethods = "ERPLoginPage")
	public void SystemSettings() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.navigate().to(url + "SystemSetting");
		Thread.sleep(5000);
		SystemSettings ss = new SystemSettings(driver);

		int SSListSize = SSDataList.size();
		System.out.println("Systems Settings List Size Is:" + SSListSize);
		for (int i = 0; i < SSListSize; i++) {
			SystemSettingsExcelData SSExcelData = SSDataList.get(i);

			js.executeScript("arguments[0].click();", ss.SystemsSettingsParamCodeSearchField);
			ss.SystemsSettingsParamCodeSearchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(ss.SystemsSettingsParamCodeSearchField, SSExcelData.ParamCode);
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", ss.SystemSettingsFetch);
			Thread.sleep(2000);

			//Create System Settings
			int settingExists = driver.findElements(By.xpath("//table[@id='systemsettingtable']//tbody//tr//td[2]")).size();
			System.out.println("System Settings Table Row Size :"+settingExists);
			
			if (settingExists == 0) {

				click(ss.SystemSettingsCreateNew);
				Thread.sleep(4000);

				WebElement modelDropdown = driver.findElement(By.id("Select"));
				Select select = new Select(modelDropdown);
				select.selectByValue(SSExcelData.Model);
				click(ss.ParamCode);
				Sendkeys(ss.ParamCode, SSExcelData.ParamCode);
				click(ss.Description);
				Sendkeys(ss.Description, SSExcelData.Description);

				WebElement paramValueTypeDropdown = driver.findElement(By.id("SelectType"));
				Select select1 = new Select(paramValueTypeDropdown);
				select1.selectByValue(SSExcelData.ParamType);
				Thread.sleep(2000);

				if (SSExcelData.ParamType.contains("B")) {

					int excelBitValue = Integer.parseInt(SSExcelData.BitValue);

					boolean isCheckboxSelected  = driver.findElement(By.xpath("(//input[@name='BitValue'])[1]")).isSelected();
					if (excelBitValue == 1 && !isCheckboxSelected  || excelBitValue == 0 && isCheckboxSelected ) {
						Thread.sleep(1000);
						js.executeScript("arguments[0].click();", ss.BooleanParamBitValue);
						Thread.sleep(1000);
						click(ss.Save);
					} else {
						click(ss.Save);
					}

				} else if (SSExcelData.ParamType.contains("S")) {

					String StringText = driver.findElement(By.xpath("(//input[@name='StringValue'])[1]")).getAttribute("value");
					if (SSExcelData.StringValue == null || SSExcelData.StringValue.trim().isEmpty()) {
						System.out.println("Excel string value is empty. Skipping system setting: "+SSExcelData.ParamCode);
						js.executeAsyncScript("arguments[0].click();", ss.Back);
						continue;
					}

					if (!StringText.contains(SSExcelData.StringValue)) {
						Thread.sleep(1000);
						js.executeScript("arguments[0].click();", ss.ParamStringValue);
					//	ss.ParamStringValue.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
						Sendkeys(ss.ParamStringValue, SSExcelData.StringValue);
						click(ss.Save);
					} else {
						js.executeScript("arguments[0].click();", ss.Save);
					}

				} else if (SSExcelData.ParamType.contains("F")) {
					String FloatText = driver.findElement(By.xpath("(//input[@name='DecimalValue'])[1]")).getAttribute("value");
					if (!FloatText.contains(SSExcelData.DecimalValue)) {
						click(ss.ParamDecimalValue);
					//	ss.ParamDecimalValue.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
						Sendkeys(ss.ParamDecimalValue, SSExcelData.TextArea);
						click(ss.Save);
					} else {
						js.executeScript("arguments[0].click();", ss.Save);
					}

				} else if (SSExcelData.ParamType.contains("N")) {
					String NumericText = driver.findElement(By.xpath("(//input[@name='NumericValue'])[1]")).getAttribute("value");
					if (!NumericText.contains(SSExcelData.NumericValue)) {
						click(ss.ParamNumericValue);
					//	ss.ParamNumericValue.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
						Sendkeys(ss.ParamNumericValue, SSExcelData.NumericValue);
						click(ss.Save);
					} else {
						js.executeScript("arguments[0].click();", ss.Save);
					}
				} else if (SSExcelData.ParamType.contains("D")) {
					String DateText = driver.findElement(By.xpath("(//input[@id='DateValue'])[1]")).getAttribute("value");
					if (!DateText.contains(SSExcelData.DateValue)) {
						click(ss.ParamDatevalue);
					//	ss.ParamDatevalue.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
						Sendkeys(ss.ParamDatevalue, SSExcelData.DateValue);
						click(ss.Save);
					} else {
						click(ss.Save);
					}

				} else if (SSExcelData.ParamType.contains("G")) {
					String GuidText = driver.findElement(By.xpath("(//input[@id='GuidValue'])[1]")).getAttribute("value");
					if (!GuidText.contains(SSExcelData.GuidValue)) {
						click(ss.ParamGuidValue);
						ss.ParamGuidValue.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
					//	Sendkeys(ss.ParamGuidValue, SSExcelData.GuidValue);
						click(ss.Save);
					} else {
						click(ss.Save);
					}

				} else if (SSExcelData.ParamType.contains("T")) {
					String TextAreaText = driver.findElement(By.xpath("(//textarea[@id='TextArea'])[1]")).getAttribute("value");
					if (!TextAreaText.contains(SSExcelData.TextArea)) {
						click(ss.ParamTextArea);
					//	ss.ParamTextArea.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
						Sendkeys(ss.ParamTextArea, SSExcelData.TextArea);
						click(ss.Save);
					} else {
						click(ss.Save);
					}
					System.out.println("*System Settings Create Successfull*");
				} 
			}

				WebElement editBtn = driver.findElement(By.xpath("(//table[@id='systemsettingtable']//tbody//tr//td[6]//a[@title='Edit'])[1]"));
				js.executeScript("arguments[0].click();", editBtn);
				Thread.sleep(2000);

				//Model
				/*	String modelValue = driver.findElement(By.xpath("//select[@id='Select']//child::option[@selected ='selected']")).getAttribute("value");		
			if (modelValue.equalsIgnoreCase(SSExcelData.Model)) {
				System.out.println("Model Is Equals :"+modelValue);
				soft.assertEquals(SSExcelData.Model, modelValue, "Actual and Expected Model Is Matched "+SSExcelData.ParamCode);
			} else {
				WebElement ModelDropdown = driver.findElement(By.id("Select"));
				Select modelSelect = new Select(ModelDropdown);
				modelSelect.selectByValue(SSExcelData.Model);
			}
			//	soft.assertEquals(SSExcelData.Model, modelValue, "Actual and Expected Model Is MisMatched "+SSExcelData.ParamCode);

			//ParamCode
			String paramCodeValue = driver.findElement(By.id("ParamCode")).getAttribute("value");
			if (paramCodeValue.equalsIgnoreCase(SSExcelData.ParamCode)) {
				System.out.println("Param Code Is :"+paramCodeValue);
				soft.assertEquals(SSExcelData.ParamCode, paramCodeValue, "Actual and Expected Model Is Matched "+SSExcelData.ParamCode);
			} else {
				click(ss.ParamCode);
				ss.ParamCode.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
				Sendkeys(ss.ParamCode, SSExcelData.ParamCode);
			}

			//Description
			String descriptionValue = driver.findElement(By.id("Description")).getText();
			if (descriptionValue.equalsIgnoreCase(SSExcelData.Description)) {
				System.out.println("Description Is :"+descriptionValue);
				soft.assertEquals(SSExcelData.Description, descriptionValue, "Actual and Expected Model Is Matched "+SSExcelData.ParamCode);
			} else {
				click(ss.Description);
				ss.Description.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
				Sendkeys(ss.Description, SSExcelData.Description);
			}

			//ParamType
			String paramValueType = driver.findElement(By.xpath("//select[@id='SelectType']//child::option[@selected='selected']")).getAttribute("value");
			if (paramValueType.equalsIgnoreCase(SSExcelData.ParamType)) {
				System.out.println("Pram Type Is :"+paramValueType);
				soft.assertEquals(SSExcelData.ParamType, paramValueType, "Actual and Expected Model Is Matched "+SSExcelData.ParamCode);	
			} else {
				WebElement paramValueTypeDropdown = driver.findElement(By.id("SelectType"));
				Select PVTSelect = new Select(paramValueTypeDropdown);	 
				PVTSelect.selectByValue(SSExcelData.ParamType);
			} */

				//ParamValue
				//	String paramValueTypeText = driver.findElement(By.xpath("//select[@id='SelectType']//child::option[@selected='selected']")).getText();
				if (SSExcelData.ParamType.contains("B")) {

					int excelBitValue = Integer.parseInt(SSExcelData.BitValue);

					boolean isCheckboxSelected  = driver.findElement(By.xpath("(//input[@name='BitValue'])[1]")).isSelected();
					if (excelBitValue == 1 && !isCheckboxSelected  || excelBitValue == 0 && isCheckboxSelected ) {
						Thread.sleep(1000);
						js.executeScript("arguments[0].click();", ss.BooleanParamBitValue);
						Thread.sleep(1000);
						click(ss.Save);
					} else {
						click(ss.Save);
					}

				} else if (SSExcelData.ParamType.contains("S")) {

					String StringText = driver.findElement(By.xpath("(//input[@name='StringValue'])[1]")).getAttribute("value");
					if (SSExcelData.StringValue == null || SSExcelData.StringValue.trim().isEmpty()) {
						System.out.println("Excel string value is empty. Skipping this row.");
						click(ss.Back);
						continue;
					}

					if (!StringText.contains(SSExcelData.StringValue)) {
						Thread.sleep(1000);
						js.executeScript("arguments[0].click();", ss.ParamStringValue);
						ss.ParamStringValue.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
						Sendkeys(ss.ParamStringValue, SSExcelData.StringValue);
						click(ss.Save);
					} else {
						js.executeScript("arguments[0].click();", ss.Save);
					}

				} else if (SSExcelData.ParamType.contains("F")) {
					String FloatText = driver.findElement(By.xpath("(//input[@name='DecimalValue'])[1]")).getAttribute("value");
					if (!FloatText.contains(SSExcelData.DecimalValue)) {
						click(ss.ParamDecimalValue);
						ss.ParamDecimalValue.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
						Sendkeys(ss.ParamDecimalValue, SSExcelData.TextArea);
						click(ss.Save);
					} else {
						js.executeScript("arguments[0].click();", ss.Save);
					}

				} else if (SSExcelData.ParamType.contains("N")) {
					String NumericText = driver.findElement(By.xpath("(//input[@name='NumericValue'])[1]")).getAttribute("value");
					if (!NumericText.contains(SSExcelData.NumericValue)) {
						click(ss.ParamNumericValue);
						ss.ParamNumericValue.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
						Sendkeys(ss.ParamNumericValue, SSExcelData.NumericValue);
						click(ss.Save);
					} else {
						js.executeScript("arguments[0].click();", ss.Save);
					}
				} else if (SSExcelData.ParamType.contains("D")) {
					String DateText = driver.findElement(By.xpath("(//input[@id='DateValue'])[1]")).getAttribute("value");
					if (!DateText.contains(SSExcelData.DateValue)) {
						click(ss.ParamDatevalue);
						ss.ParamDatevalue.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
						Sendkeys(ss.ParamDatevalue, SSExcelData.DateValue);
						click(ss.Save);
					} else {
						click(ss.Save);
					}

				} else if (SSExcelData.ParamType.contains("G")) {
					String GuidText = driver.findElement(By.xpath("(//input[@id='GuidValue'])[1]")).getAttribute("value");
					if (!GuidText.contains(SSExcelData.GuidValue)) {
						click(ss.ParamGuidValue);
						ss.ParamGuidValue.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
						Sendkeys(ss.ParamGuidValue, SSExcelData.GuidValue);
						click(ss.Save);
					} else {
						click(ss.Save);
					}

				} else if (SSExcelData.ParamType.contains("T")) {
					String TextAreaText = driver.findElement(By.xpath("(//textarea[@id='TextArea'])[1]")).getAttribute("value");
					if (!TextAreaText.contains(SSExcelData.TextArea)) {
						click(ss.ParamTextArea);
						ss.ParamTextArea.sendKeys(Keys.CONTROL +"a"+ Keys.DELETE);
						Sendkeys(ss.ParamTextArea, SSExcelData.TextArea);
						click(ss.Save);
					} else {
						click(ss.Save);
					}
				}
			}

		//	driver.navigate().back();
			Thread.sleep(2000);
		
	}


	//SalesOrderForm
	@Ignore
	@Test(priority = 8)
	public void SalesOrder() throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.navigate().to(url + "SalesPurchases/SalesOrderIndex");
		Thread.sleep(4000);
		SalesOrder so = new SalesOrder(driver);

		click(so.AddOrder);
		Thread.sleep(3000);

		int SOSize = dataList.size();
		for (int i = 0; i < SOSize; i++) {
			ExcelData excelData = dataList.get(i);

			if (excelData.CustomerCode.isEmpty() == false) {

				click(so.Customer);
				driver.findElement(By.xpath("//span[@id='select2-CustomerId-container']//following::input[@type='search']"))
				.sendKeys(excelData.CustomerCode + Keys.ENTER);
				Thread.sleep(2000);

			}

			if (so.Salesman.isDisplayed()) {
				System.out.println("Sales Man Is Editable");
			} else {
				System.out.println("Sales Man Is Non Editable");
			}

			if (so.Warehouse.isDisplayed()) {
				System.out.println("Warehouse Is Editable");
			} else {
				System.out.println("Warehouse Is Non Editable");
			}

			click(so.Product);
			driver.findElement(By.xpath("//span[@id='select2-ProductId-container']//following::input[@type='search']"))
			.sendKeys(excelData.ProductService + Keys.ENTER);
			click(so.Qty);
			click(so.Uom);
			driver.findElement(By.xpath("//span[@id='select2-UOMId-container']//following::input[@type='search']"))
			.sendKeys(excelData.UOM + Keys.ENTER);
			Sendkeys(so.Qty, excelData.Qty);

			click(so.IsFoc);
			String Foc = so.FocField.getAttribute("style").trim();
			if (Foc.contains("none")) {
				System.out.println("Foc is:" + Foc);
			} else {
				System.out.println("Foc Field Is Displayed");
				Sendkeys(so.Foc, excelData.Foc);
			}

			click(so.Price);
			so.Price.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			Sendkeys(so.Price, excelData.Price);
			Thread.sleep(1000);

			String Disc = driver.findElement(By.xpath("//div[contains(@class, 'showDiscountBlockHeader')][1]")).getAttribute("style");
			if (Disc.contains("none")) {
				System.out.println("DIscount Is:" + Disc);
			} else {
				System.out.println("Discount Field Is Displayed");
			}

			String UnitDisc = driver.findElement(By.xpath("//div[contains(@class, 'showDiscountBlockHeader')][2]")).getAttribute("style");
			if (UnitDisc.contains("none")) {
				System.out.println("Unit Discount Is:" + UnitDisc);
			} else {
				System.out.println("Unit Discount Field Is Displayed");
			}

			click(so.Add);
			Thread.sleep(2000);
			click(so.Qty);

		}

		click(so.ConvertInvoice);
		click(so.PopupOk);
		Thread.sleep(3000);

		int SISize = dataList.size();
		for (int j = 0; j < SISize; j++) {
			ExcelData excelData = dataList.get(j);

			WebElement batchFile = driver.findElement(
					By.xpath("(//table[@id='SalesTable']//tbody//tr//td[2][normalize-space()= '" + excelData.ProductService+"']//following::td[11]//a[@class='fa fa-folder-open Popup'])[1]"));
			js.executeScript("arguments[0].click();", batchFile); 

			String ctnQtyValue = driver
					.findElement(By.xpath(
							"(//div//strong[contains(text(),'"+excelData.ProductService+"')]//following::input[@id='BQty'])[1]"))
					.getAttribute("value");
			System.out.println("B.Qty Is:" + ctnQtyValue);
			String pcsQtyValue = driver
					.findElement(By.xpath(
							"(//div//strong[contains(text(),'"+excelData.ProductService+"')]//following::input[@id='LQty'])[1]"))
					.getAttribute("value");
			System.out.println("L.Qty Is:" + pcsQtyValue);
			Thread.sleep(1000);

			if (booleanselected == false) {

				WebElement lQty = driver.findElement(By.xpath(
						"(//div//strong[contains(text(), '"+excelData.ProductService+"')]//following::input[@id='LooseQty'])[1]"));
				js.executeScript("arguments[0].click();", lQty);
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(pcsQtyValue);
				driver.findElement(
						By.xpath("(//div//strong[contains(.,'"+excelData.ProductService+"')]//following::button[text()='Add'])[1]"))
				.click();
				Thread.sleep(3000);

			} else {

				if (!ctnQtyValue.equals("0")) {

					WebElement bQty = driver.findElement(By.xpath(
							"(//div//strong[contains(text(),'"+excelData.ProductService+"')]//following::input[@id='BulkQty'])[1]"));
					js.executeScript("arguments[0].click();", bQty);
					bQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
					bQty.sendKeys(ctnQtyValue);

				}

				WebElement lQty = driver.findElement(By.xpath(
						"(//div//strong[contains(text(), '"+excelData.ProductService+"')]//following::input[@id='LooseQty'])[1]"));
				js.executeScript("arguments[0].click();", lQty);
				lQty.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
				lQty.sendKeys(pcsQtyValue);
				driver.findElement(
						By.xpath("(//div//strong[contains(.,'"+excelData.ProductService+"')]//following::button[text()='Add'])[1]"))
				.click();
				Thread.sleep(3000);

			}

		}

		js.executeScript("arguments[0].click();", so.SaveandClose);
		String alertText = driver.findElement(By.id("popup_message")).getText();
		System.out.println("Alert Message: " + alertText);

		System.out.println("*Sales Invoice Save Successfull*");

	}

}
