package com.Sales;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import com.PomClass.Login;
import com.PomClass.SalesInvoice;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesInvoiceProductTotalsValidationTest extends BaseClass {

	private static final String LOGIN_URL = "https://erpauto.dev1.adaptivebizapp.com/account/login";
	private static final String ERP_URL = "https://erpauto.dev1.adaptivebizapp.com/ERP/";
	private static final String COMPANY_CODE = "uitdemo1";
	private static final String USER_NAME = "kiran03";
	private static final String PASSWORD = "Adaptive*123";
	private static final String SALES_INVOICE_URL = ERP_URL + "SalesPurchases/SalesInvoice";
	private static final String EXPECTED_QTY = "1";
	private static final String EXPECTED_PRICE = "243";
	private static final BigDecimal GST_RATE = new BigDecimal("0.09");

	private WebDriverWait wait;
	private JavascriptExecutor js;

	private final By customerDropdown = By.id("select2-CustomerId-container");
	private final By productDropdown = By.id("select2-ProductId-container");
	private final By uomDropdown = By.id("select2-UOMId-container");
	private final By addInvoiceButton = By.id("Create");
	private final By resultsOptions = By.xpath(
			"//span[contains(@class,'select2-container--open')]//li[contains(@class,'select2-results__option')"
					+ " and not(contains(@class,'loading-results')) and not(contains(@class,'disabled'))"
					+ " and normalize-space()]");
	private final By resultsSearchBox = By.xpath(
			"//span[contains(@class,'select2-container--open')]//input[contains(@class,'select2-search__field')]");
	private final By subtotalValue = By.xpath("//table[@id='SalesTable']//tfoot//p[@id='tSubtotal']");
	private final By gstValue = By.id("GST");
	private final By grandTotalValue = By.id("Amount");
	private final By gstTypeLabel = By.id("select2-GSTTypeId-container");

	@Test(priority = 1)
	public void ERPLoginPage() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		wait = new WebDriverWait(driver, 20);
		js = (JavascriptExecutor) driver;

		driver.get(LOGIN_URL);

		Login login = new Login(driver);
		type(login.CompanyCode, COMPANY_CODE);
		type(login.UserName, USER_NAME);
		type(login.Password, PASSWORD);
		clickElement(login.LoginButton);

		wait.until(ExpectedConditions.urlContains("/ERP"));
		Assert.assertTrue(driver.getCurrentUrl().startsWith(ERP_URL), "Login failed. Current URL: " + driver.getCurrentUrl());
	}

	@Test(priority = 2, dependsOnMethods = "ERPLoginPage")
	public void createSalesInvoiceAndValidateTotals() {
		SalesInvoice salesInvoice = new SalesInvoice(driver);
		driver.navigate().to(SALES_INVOICE_URL);

		wait.until(ExpectedConditions.visibilityOfElementLocated(addInvoiceButton));
		clickElement(salesInvoice.AddInvoice);

		selectFirstAvailableOption(customerDropdown);

		List<String> productNames = fetchFirstDropdownOptions(productDropdown, 2);
		Assert.assertEquals(productNames.size(), 2, "Unable to identify the first two products from the product dropdown.");

		for (String productName : productNames) {
			selectDropdownValue(productDropdown, productName);
			selectFirstAvailableOption(uomDropdown);
			clearAndType(By.id("ItemQty"), EXPECTED_QTY);
			clearAndType(By.id("ItemPrice"), EXPECTED_PRICE);
			clickElement(By.id("btn_add"));
			waitForInvoiceRow(productName);
		}

		String currentGstType = getElementText(gstTypeLabel);
		BigDecimal expectedSubtotal = new BigDecimal(EXPECTED_PRICE).multiply(new BigDecimal(productNames.size()));
		BigDecimal expectedGst = calculateExpectedGst(expectedSubtotal, currentGstType);
		BigDecimal expectedGrandTotal = calculateExpectedGrandTotal(expectedSubtotal, expectedGst, currentGstType);

		waitForTotalsToStabilize(expectedSubtotal);

		BigDecimal actualSubtotal = getAmountFromText(subtotalValue);
		BigDecimal actualGst = getAmountFromValue(gstValue);
		BigDecimal actualGrandTotal = getAmountFromValue(grandTotalValue);

		Assert.assertEquals(actualSubtotal, expectedSubtotal.setScale(2, RoundingMode.HALF_UP),
				"Subtotal calculation mismatch.");
		Assert.assertEquals(actualGst, expectedGst, "GST calculation mismatch for GST type: " + currentGstType);
		Assert.assertEquals(actualGrandTotal, expectedGrandTotal, "Grand Total calculation mismatch.");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	private List<String> fetchFirstDropdownOptions(By dropdownLocator, int count) {
		List<String> optionTexts = new ArrayList<String>();
		openDropdown(dropdownLocator);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(resultsOptions, count - 1));

		List<WebElement> options = driver.findElements(resultsOptions);
		for (WebElement option : options) {
			String text = option.getText().trim();
			if (!text.isEmpty() && !optionTexts.contains(text)) {
				optionTexts.add(text);
			}
			if (optionTexts.size() == count) {
				break;
			}
		}

		clickOutside();
		return optionTexts;
	}

	private void selectFirstAvailableOption(By dropdownLocator) {
		openDropdown(dropdownLocator);
		WebElement firstOption = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsOptions));
		clickElement(firstOption);
	}

	private void selectDropdownValue(By dropdownLocator, String value) {
		openDropdown(dropdownLocator);

		List<WebElement> searchFields = driver.findElements(resultsSearchBox);
		if (!searchFields.isEmpty()) {
			WebElement searchField = searchFields.get(0);
			searchField.sendKeys(Keys.CONTROL + "a");
			searchField.sendKeys(Keys.DELETE);
			searchField.sendKeys(value);
		}

		By targetOption = By.xpath("//span[contains(@class,'select2-container--open')]//li[contains(@class,'select2-results__option')"
				+ " and not(contains(@class,'loading-results')) and not(contains(@class,'disabled'))"
				+ " and normalize-space()=\"" + escapeXpath(value) + "\"]");
		clickElement(targetOption);
	}

	private void waitForInvoiceRow(String productName) {
		By rowLocator = By.xpath("//table[@id='SalesTable']//tbody//tr[.//textarea[contains(normalize-space(),\""
				+ escapeXpath(productName) + "\")] or .//strong[contains(normalize-space(),\"" + escapeXpath(productName)
				+ "\")]]");
		wait.until(ExpectedConditions.presenceOfElementLocated(rowLocator));
	}

	private void waitForTotalsToStabilize(final BigDecimal expectedSubtotal) {
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(org.openqa.selenium.WebDriver webDriver) {
				try {
					return getAmountFromText(subtotalValue).compareTo(expectedSubtotal.setScale(2, RoundingMode.HALF_UP)) == 0;
				} catch (RuntimeException e) {
					return false;
				}
			}
		});
	}

	private BigDecimal calculateExpectedGst(BigDecimal subtotal, String gstType) {
		BigDecimal normalizedSubtotal = subtotal.setScale(2, RoundingMode.HALF_UP);

		if ("Inclusive".equalsIgnoreCase(gstType)) {
			return normalizedSubtotal.multiply(new BigDecimal("9"))
					.divide(new BigDecimal("109"), 2, RoundingMode.HALF_UP);
		}

		if ("Exclusive".equalsIgnoreCase(gstType)) {
			return normalizedSubtotal.multiply(GST_RATE).setScale(2, RoundingMode.HALF_UP);
		}

		return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
	}

	private BigDecimal calculateExpectedGrandTotal(BigDecimal subtotal, BigDecimal gst, String gstType) {
		if ("Exclusive".equalsIgnoreCase(gstType)) {
			return subtotal.add(gst).setScale(2, RoundingMode.HALF_UP);
		}

		return subtotal.setScale(2, RoundingMode.HALF_UP);
	}

	private String getElementText(By locator) {
		return retryingFind(locator).getText().trim();
	}

	private BigDecimal getAmountFromText(By locator) {
		String amount = retryingFind(locator).getText();
		return parseAmount(amount);
	}

	private BigDecimal getAmountFromValue(By locator) {
		String amount = retryingFind(locator).getAttribute("value");
		return parseAmount(amount);
	}

	private BigDecimal parseAmount(String value) {
		String normalizedValue = value == null ? "0" : value.replace(",", "").replaceAll("[^0-9.\\-]", "").trim();
		if (normalizedValue.isEmpty() || ".".equals(normalizedValue) || "-".equals(normalizedValue)) {
			return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}
		return new BigDecimal(normalizedValue).setScale(2, RoundingMode.HALF_UP);
	}

	private void clearAndType(By locator, String value) {
		WebElement element = retryingFind(locator);
		clickElement(element);
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
		element.sendKeys(value);
	}

	private void type(WebElement element, String value) {
		for (int attempt = 0; attempt < 3; attempt++) {
			try {
				wait.until(ExpectedConditions.visibilityOf(element));
				element.clear();
				element.sendKeys(value);
				return;
			} catch (StaleElementReferenceException e) {
				if (attempt == 2) {
					throw e;
				}
			}
		}
	}

	private void clickElement(By locator) {
		for (int attempt = 0; attempt < 3; attempt++) {
			try {
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
				scrollIntoView(element);
				element.click();
				return;
			} catch (StaleElementReferenceException e) {
				if (attempt == 2) {
					throw e;
				}
			} catch (TimeoutException e) {
				if (attempt == 2) {
					throw e;
				}
			}
		}
	}

	private void clickElement(WebElement element) {
		for (int attempt = 0; attempt < 3; attempt++) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element));
				scrollIntoView(element);
				element.click();
				return;
			} catch (StaleElementReferenceException e) {
				if (attempt == 2) {
					throw e;
				}
			} catch (RuntimeException e) {
				scrollIntoView(element);
				js.executeScript("arguments[0].click();", element);
				return;
			}
		}
	}

	private WebElement retryingFind(By locator) {
		for (int attempt = 0; attempt < 4; attempt++) {
			try {
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			} catch (StaleElementReferenceException e) {
				if (attempt == 3) {
					throw e;
				}
			} catch (NoSuchElementException e) {
				if (attempt == 3) {
					throw e;
				}
			}
		}
		throw new NoSuchElementException("Unable to locate element: " + locator);
	}

	private void openDropdown(By dropdownLocator) {
		clickElement(dropdownLocator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(resultsOptions));
	}

	private void clickOutside() {
		js.executeScript("document.body.click();");
	}

	private void scrollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	}

	private String escapeXpath(String value) {
		return value.replace("\"", "\\\"");
	}
}
