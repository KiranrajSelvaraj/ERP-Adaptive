package com.Purchase;

import java.util.ArrayList;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import com.Utility.Util1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UtilPOTest extends BaseClass {
	
	@BeforeTest
	public void POLogin() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://erp.dev1.adaptivegroups.asia/ERP/Account/Login");
		
	}
	
	@DataProvider
	public Object[][] Util2(){
		Object[][] data = Util1.getTestData("C:\\Users\\kiran\\OneDrive\\Documents\\ERP Wholesale.xlsx", "Sheet1");
		return data;
		
		
	}
	
	class ExcelData {
		
		private String VendorName;
		private String InvoiceNo;
		private String GstType;
		private String CurrencyCode;
		private String Terms;
		private String CurrencyRate;
		private String Warehouse;
		private String ProductName;
		private String UOM;
		private String Qty;
		private String Price;
		private String UnitDisc;
		private String Discount;
		private String DiscountAmount;
		private String Batch;
		private String BatchNumber;
		private String BatchLocation;
		private String BatchMfgDate;
		private String BatchValidPeriod;
		private String DiscountModeForAllProduct;
		private String DiscountAmountPercentage;
		private String RoundOff;
		private String GSTPercentage;
		
		public ExcelData(String VendorName, String InvoiceNo, String GstType, String CurrencyCode, String Terms, String CurrencyRate, String Warehouse, String ProductName, String UOM,
				String Qty, String Price, String UnitDisc, String Discount, String DiscountAmount, String Batch, String BatchNumber, String BatchLocation, String BatchMfgDate, String BatchValidPeriod,
				String DiscountModeForAllProduct, String DiscountAmountPercentage, String RoundOff, String GSTPercentage) {
			super();
			
			this.VendorName = VendorName;
			this.InvoiceNo = InvoiceNo;
			this.GstType = GstType;
			this.CurrencyCode = CurrencyCode;
			this.Terms = Terms;
			this.CurrencyRate = CurrencyRate;
			this.Warehouse = Warehouse;
			this.ProductName = ProductName;
			this.UOM = UOM;
			this.Qty = Qty;
			this.Price = Price;
			this.UnitDisc = UnitDisc;
			this.Discount = Discount;
			this.DiscountAmount = DiscountAmount;
			this.Batch = Batch;
			this.BatchNumber = BatchNumber;
			this.BatchLocation = BatchLocation;
			this.BatchMfgDate = BatchMfgDate;
			this.BatchValidPeriod = BatchValidPeriod;
			this.DiscountModeForAllProduct = DiscountModeForAllProduct;
			this.DiscountAmountPercentage = DiscountAmountPercentage;
			this.RoundOff = RoundOff;
			this.GSTPercentage = GSTPercentage;
						
		}
			
	}
	
	
	ArrayList<ExcelData> dataList = new ArrayList<>();
	@Test(priority = 7, dataProvider = "Util2")
	public void GetData(String VendorName, String InvoiceNo, String GstType, String CurrencyCode, String Terms, String CurrencyRate, String Warehouse, String ProductName, String UOM,
			String Qty, String Price, String UnitDisc, String Discount, String DiscountAmount, String Batch, String BatchNumber, String BatchLocation, String BatchMfgDate, String BatchValidPeriod,
			String DiscountModeForAllProduct, String DiscountAmountPercentage, String RoundOff, String GSTPercentage) {
		
		ExcelData data = new ExcelData(VendorName, InvoiceNo, GstType, CurrencyCode, Terms, CurrencyRate, Warehouse, ProductName, UOM, Qty, Price, UnitDisc, Discount, DiscountAmount, 
				Batch, BatchNumber, BatchLocation, BatchMfgDate, BatchValidPeriod, DiscountModeForAllProduct, DiscountAmountPercentage, RoundOff, GSTPercentage);
		
		dataList.add(data);
	}
	
	@Test(priority = 9)
	public void ArrayList() {
		
		int size = dataList.size();
		
		for (int i = 0; i < size; i++) {
			
			ExcelData excelData = dataList.get(i);	
			System.out.println(excelData.VendorName);
			System.out.println(excelData.InvoiceNo);
			System.out.println(excelData.GstType);
			System.out.println(excelData.CurrencyCode);
			System.out.println(excelData.Terms);
			System.out.println(excelData.CurrencyRate);
			System.out.println(excelData.Warehouse);
			System.out.println(excelData.ProductName);
			System.out.println(excelData.UOM);
			System.out.println(excelData.Qty);
			System.out.println(excelData.Price);
			System.out.println(excelData.UnitDisc);
			System.out.println(excelData.Discount);
			System.out.println(excelData.DiscountAmount);
			System.out.println(excelData.Batch);
			System.out.println(excelData.BatchNumber);
			System.out.println(excelData.BatchLocation);
			System.out.println(excelData.BatchMfgDate);
			System.out.println(excelData.BatchValidPeriod);
			System.out.println(excelData.DiscountModeForAllProduct);
			System.out.println(excelData.DiscountAmountPercentage);
			System.out.println(excelData.RoundOff);
			System.out.println(excelData.GSTPercentage);
			
			System.out.println("**");
			
		}
	}

}
