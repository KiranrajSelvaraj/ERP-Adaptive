package com.Demo;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SingaporeCompaniesDirectory extends BaseClass{

	@Ignore
	@Test(priority = 1)
	public void SingaporeCompaniesGetDetails() throws Exception {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.scal.com.sg/memberlistings");
		Thread.sleep(3000);

		// Create Excel workbook and sheet
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Company Data");

		// Header row
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Company Name");
		headerRow.createCell(1).setCellValue("Location");
		headerRow.createCell(2).setCellValue("Phone Number");
		headerRow.createCell(3).setCellValue("Slots");
		headerRow.createCell(4).setCellValue("Category");

		CellStyle wrapStyle = workbook.createCellStyle();
		wrapStyle.setWrapText(true);

		int rowNum = 1;

		// target page number
		String targetPage = "6";

		while (true) {
			// check target page visible
			List<WebElement> pageList = driver.findElements(By.xpath("//div[@id='pagination']//ul//li//a[@class='page-link' and text()='" + targetPage + "']"));

			if (pageList.size() > 0) {
				System.out.println("Page " + targetPage + " is now visible.");
				break; // visible loop stop
			}

			// check Next button available
			List<WebElement> nextBtnList = driver.findElements(By.xpath("//li[@class='page-item next']/a"));
			if (nextBtnList.size() == 0) {
				System.out.println("Reached last page, but page " + targetPage + " not found.");
				break;
			}

			// click Next
			WebElement nextBtn = nextBtnList.get(0);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
			Thread.sleep(1500);
		}


		List<WebElement> pageNumber = driver.findElements(By.xpath("//li[@class='page-item']//a[contains(text(),'6')]"));
		int pageNumberSize = pageNumber.size(); // exclude Prev & Next
		System.out.println("Total Pages: " + pageNumberSize);

		for (int j = 1; j <= pageNumberSize; j++) {

			// Click page j
			WebElement pageLink = driver.findElement(By.xpath("//li[@class='page-item']//a[contains(text(),'6')]"));
			String pageText = pageLink.getText();
			System.out.println("Navigating to Page: " + pageText);

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", pageLink);
			Thread.sleep(2000);

			// Get companies in this page
			List<WebElement> companies = driver.findElements(By.xpath("//ul[@id='members']//li//a"));
			int companiesCounts = companies.size();
			System.out.println("Companies in this page: " + companiesCounts);

			for (int k = 1; k <= companiesCounts; k++) {
				try {
					// re-locate company element fresh every time
					WebElement companyLink = driver.findElement(By.xpath("(//ul[@id='members']//li//a[1])[" + k + "]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", companyLink);
					Thread.sleep(1500);

					String companyName = driver.findElement(By.xpath("//h3[@class='name']")).getText();
					String Location = driver.findElement(By.xpath("(//ul[@class='basic-info']//li)[1]")).getText();            
					String PhoneNumber = driver.findElement(By.xpath("(//ul[@class='basic-info']//li)[2]")).getText();            
					String Slots = driver.findElement(By.xpath("(//ul[@class='regnheads']//li)[1]")).getText(); 
					String mainSlotsText = Slots.split("\n")[0];
					String Category = driver.findElement(By.xpath("(//ul[@class='basic-info']//li)[6]")).getText();            

					System.out.println("Company Name: " + companyName);
					System.out.println("Location: " + Location);
					System.out.println("PhoneNumber: " + PhoneNumber);
					System.out.println("Slots: " + mainSlotsText);
					System.out.println("Category: " + Category);
					System.out.println();

					// Write Excel
					Row row = sheet.createRow(rowNum++);
					row.createCell(0).setCellValue(companyName);
					row.createCell(1).setCellValue(Location);
					row.createCell(2).setCellValue(PhoneNumber);
					row.createCell(3).setCellValue(mainSlotsText);
					row.createCell(4).setCellValue(Category);

					try (FileOutputStream fos = new FileOutputStream("SingaporeCompanies.xlsx")) {
						workbook.write(fos);
					}

				} catch (Exception e) {

				}

				// Go back to company list
				driver.navigate().back();
				Thread.sleep(1000);

				// Reopen current page again (so k-th element is fresh)
				WebElement reopenPage = driver.findElement(By.xpath("//li[@class='page-item']//a[contains(text(),'6')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", reopenPage);
				Thread.sleep(1000);
			}

			// After finishing this page → move to NEXT page
			// Only if not last page
			if (j < pageNumberSize) {
				WebElement nextBtn = driver.findElement(By.xpath("//li[@class='page-item next']/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
				Thread.sleep(2000);
			}


			workbook.close();



			/*		List<WebElement> pageLinks = driver.findElements(By.xpath("//div[@id='pagination']//ul//li//a[@class='page-link']"));
			if (j <= pageLinks.size()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", pageLinks.get(j - 1));
				Thread.sleep(2000);
			}*/



			int size = driver.findElements(By.xpath("//a[text()='"+j+"']")).size();
			System.out.println("size: "+size);
			Thread.sleep(2000);

			//	while (size ==1) {

			while (true) {
				try {
					// check if "Next" button is enabled
					List<WebElement> nextBtnList = driver.findElements(By.xpath("//li[@class='page-item next']//a"));
					if (nextBtnList.size() == 0) {
						break; // no Next button -> last page reached
					}

					WebElement nextBtn = nextBtnList.get(0);

					// Scroll & Click safely
					//  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
					Thread.sleep(2000);

				} catch (Exception e) {
					System.out.println("Stale element found, retrying...");
					Thread.sleep(1000);
					//  continue; // retry loop
				}
				break;
			}


			//	}

			List<WebElement> pageLinks = driver.findElements(By.xpath("//div[@id='pagination']//ul//li//a[@class='page-link']"));
			if (j <= pageLinks.size()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", pageLinks.get(j + 1));
				Thread.sleep(2000);
			}



		}

		workbook.close();
		driver.quit();

		System.out.println("\n✅ Data saved to SingaporeCompanies.xlsx successfully!");
	}




	@Ignore
	@Test(priority = 3)
	public void SingaporeCompaniesScraper() throws Exception{

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.scal.com.sg/memberlistings");
		Thread.sleep(3000);

		// Create Excel workbook and sheet
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Company Data");

		// Header row
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Company Name");
		headerRow.createCell(1).setCellValue("Location");
		headerRow.createCell(2).setCellValue("Phone Number");
		headerRow.createCell(3).setCellValue("Slots");
		headerRow.createCell(4).setCellValue("Category");

		int rowNum = 1;


		//Page Number
		List<WebElement> pageNumber = driver.findElements(By.xpath("//div[@id='pagination']//ul//li//a[@class='page-link']"));
		int pageNumberSize = pageNumber.size();
		System.out.println("Total Pages: " + pageNumberSize);

		for (int j = 1; j <= pageNumberSize; j++) {

			pageNumber = driver.findElements(By.xpath("//div[@id='pagination']//ul//li//a[@class='page-link']"));
			WebElement pageLink = pageNumber.get(j - 1); 
			String pageText = pageLink.getText();
			System.out.println("Navigating to Page: " + pageText);

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", pageLink);
			Thread.sleep(2000);

			//Company Link
			List<WebElement> companies = driver.findElements(By.xpath("//ul[@id='members']//li//a"));
			int companiesCount = companies.size();
			System.out.println("Companies on this page: " + companiesCount);

			for (int k = 1; k <= companiesCount; k++) {
				try {

					WebElement companyLink = driver.findElement(By.xpath("(//ul[@id='members']//li//a[1])[" + k + "]"));

					// Click company
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", companyLink);
					Thread.sleep(1500);


					String companyName = getTextSafe(driver, "//h3[@class='name']");
					System.out.println("Company Name: "+companyName);
					String Location = getTextSafe(driver, "(//ul[@class='basic-info']//li)[1]");
					System.out.println("Location: "+Location);
					String PhoneNumber = getTextSafe(driver, "(//ul[@class='basic-info']//li)[2]");
					System.out.println("PhoneNumber: "+PhoneNumber);
					String Slots = getTextSafe(driver, "(//ul[@class='regnheads']//li)[1]");
					String mainSlotsText = Slots.contains("\n") ? Slots.split("\n")[0] : Slots;
					System.out.println("mainSlotsText: "+mainSlotsText);
					String Category = getTextSafe(driver, "(//ul[@class='basic-info']//li)[6]");
					System.out.println("Category: "+Category);
					System.out.println();

					// Write Excel
					Row row = sheet.createRow(rowNum++);
					row.createCell(0).setCellValue(companyName);
					row.createCell(1).setCellValue(Location);
					row.createCell(2).setCellValue(PhoneNumber);
					row.createCell(3).setCellValue(mainSlotsText);
					row.createCell(4).setCellValue(Category);

					// Save Excel after each company (for safety)
					try (FileOutputStream fos = new FileOutputStream("SingaporeCompanies.xlsx")) {
						workbook.write(fos);
					}

				} catch (Exception e) {

				}

				// Go back to company list
				try {
					driver.navigate().back();
					Thread.sleep(1000);
				} catch (Exception e) {

				}

				List<WebElement> pageLinks = driver.findElements(By.xpath("//div[@id='pagination']//ul//li//a[@class='page-link']"));
				if (j <= pageLinks.size()) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", pageLinks.get(j - 1));
					Thread.sleep(2000);
				}


			}

		}

		workbook.close();
		//	driver.quit();
		System.out.println("All data saved to SingaporeCompanies.xlsx successfully!");
	}

	// Helper method to safely get text
	private static String getTextSafe(WebDriver driver, String xpath) {
		try {
			return driver.findElement(By.xpath(xpath)).getText();
		} catch (Exception e) {
			return ""; // return blank if not found
		}
	}


	@Test(priority = 4)
	public void CompaniesDetails() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.scal.com.sg/memberlistings");
		Thread.sleep(3000);


		// Create Excel workbook and sheet
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Company Data");

		// Header row
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Company Name");
		headerRow.createCell(1).setCellValue("Location");
		headerRow.createCell(2).setCellValue("Phone Number");
		headerRow.createCell(3).setCellValue("Slots");
		headerRow.createCell(4).setCellValue("Category");
		
		int rowNum = 1;

		while (true) {
			try {
				// check if "Next" button is enabled
				List<WebElement> nextBtnList = driver.findElements(By.xpath("//li[@class='page-item next']/a"));
				if (nextBtnList.size() == 0) {
					break; // no Next button -> last page reached
				}

				WebElement nextBtn = nextBtnList.get(0);

				

				for (int i = 1; i <= 10; i++) {

					WebElement companyElement = driver.findElement(By.xpath("(//ul[@id='members']//li//a[1])[" + i + "]"));

					// Example in Java Selenium
					String clickLink = companyElement.getAttribute("href");

					// Open link in new tab
					((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');", clickLink);

					// Switch to new tab
					ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
					driver.switchTo().window(tabs.get(1));

					Thread.sleep(1500);
					try {

					String companyName = driver.findElement(By.xpath("//h3[@class='name']")).getText();
					String Location = driver.findElement(By.xpath("(//ul[@class='basic-info']//li)[1]")).getText();            
					String PhoneNumber = driver.findElement(By.xpath("(//ul[@class='basic-info']//li)[2]")).getText();            
					String Slots = driver.findElement(By.xpath("(//ul[@class='regnheads']//li)[1]")).getText(); 
					String mainSlotsText = Slots.split("\n")[0];
					String Category = driver.findElement(By.xpath("(//ul[@class='basic-info']//li)[6]")).getText();            

					System.out.println("Company Name: " + companyName);
					System.out.println("Location: " + Location);
					System.out.println("PhoneNumber: " + PhoneNumber);
					System.out.println("Slots: " + mainSlotsText);
					System.out.println("Category: " + Category);
					System.out.println();

					// Write Excel
					Row row = sheet.createRow(rowNum++);
					row.createCell(0).setCellValue(companyName);
					row.createCell(1).setCellValue(Location);
					row.createCell(2).setCellValue(PhoneNumber);
					row.createCell(3).setCellValue(mainSlotsText);
					row.createCell(4).setCellValue(Category);

					// Save Excel after each company (for safety)
					try (FileOutputStream fos = new FileOutputStream("SingaporeCompanies.xlsx")) {
						workbook.write(fos);
					}
					
					} catch (Exception e) {
						
					}

					// Close tab
					driver.close();

					// Switch back to original page
					driver.switchTo().window(tabs.get(0));

				}
				
				//Click safely
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
				Thread.sleep(2000);

				workbook.close();
				//	driver.quit();
				System.out.println("All data saved to SingaporeCompanies.xlsx successfully!");

			} catch (Exception e) {
				System.out.println("Stale element found, retrying...");
				Thread.sleep(1000);
				continue; // retry loop
			}

		}





	}
}




