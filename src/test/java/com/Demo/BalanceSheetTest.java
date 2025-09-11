package com.Demo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BaseClass.BaseClass;
import com.PomClass.BalanceSheet;
import com.PomClass.Login;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BalanceSheetTest extends BaseClass{

	private String URL;

	@Test
	public void BalanceSheetChecking() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://huc.adaptivebizapp.com/");
		URL = "https://huc.adaptivebizapp.com/ERP/";
		String ActURL = driver.getCurrentUrl();
		boolean equals = URL.equalsIgnoreCase(ActURL);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		SoftAssert soft = new SoftAssert();
		Login lo = new Login(driver);
		BalanceSheet bs = new BalanceSheet(driver);

		Sendkeys(lo.CompanyCode, "hucis");
		Sendkeys(lo.UserName, "admin");
		Sendkeys(lo.Password, "Adaptive*123");
		Thread.sleep(1000);
		click(lo.LoginButton);
		Thread.sleep(2000);


		driver.navigate().to(URL +"Accounts/BalanceSheet");
		Thread.sleep(4000);

		LocalDate CurrentDate = LocalDate.of(2025, 04, 21);
		LocalDate EndDate = LocalDate.of(2025, 01, 01); 
		boolean rerun=false;

		System.out.println("#Current Date: "+CurrentDate);
		while (!rerun && !CurrentDate.isBefore(EndDate)) {
			System.out.println("Currnet Date:" +CurrentDate);

			click(bs.Format);
			driver.findElement(By.xpath("//span[@id='select2-Format-container']//following::input[@type='search']"))
			.sendKeys("Current Period" +Keys.ENTER);

			click(bs.Date);
			bs.Date.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			String formatedDate = CurrentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));			
			Sendkeys(bs.Date, formatedDate +Keys.ENTER);

			click(bs.Fetch);
			Thread.sleep(7000);

			By totalAssetsLocator = By.xpath("//td[normalize-space()='Total Assets']//following::th[6]");
			wait.until(ExpectedConditions.visibilityOfElementLocated(totalAssetsLocator));
			String totalAssets = driver.findElement(totalAssetsLocator).getText();
			System.out.println("Total Assets:" +totalAssets);

			By totalLiabandEqtLocator = By.xpath("//td[normalize-space()='Total Liablities & Equity']//following::th[4]");
			wait.until(ExpectedConditions.visibilityOfElementLocated(totalLiabandEqtLocator));
			String totalLiabandEqt = driver.findElement(totalLiabandEqtLocator).getText();
			System.out.println("Total Liablities and Equity:" +totalLiabandEqt);

			if (totalAssets.equals(totalLiabandEqt)) {
				rerun=true;
				System.out.println(CurrentDate);
				soft.assertEquals(totalAssets, totalLiabandEqt, "Total Assets and Liabilities & Equity matched on: " +CurrentDate);
				break;

			}else {

				CurrentDate = CurrentDate.minusDays(1);

			}

			System.out.println("Before Date:" +CurrentDate);  
			System.out.println("***");
		}

	}

}
