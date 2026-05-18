package com.Purchase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PurchaseReturnAutomationTest {

	private static final String LOGIN_URL = "https://erpauto.dev1.adaptivebizapp.com/account/login";
	private static final String PURCHASE_RETURN_URL = "https://erpauto.dev1.adaptivebizapp.com/ERP/Purchases/PurchaseReturnIndex";
	private static final String COMPANY_CODE = "uitdemo1";
	private static final String USERNAME = "kiran03";
	private static final String PASSWORD = "Adaptive*123";
	private static final double PRODUCT_PRICE = 243.00;
	private static final int PRODUCT_QUANTITY = 1;
	private static final int PRODUCT_COUNT = 2;
	private static final double DEFAULT_GST_PERCENTAGE = 9.00;
	private static final int WAIT_SECONDS = 25;
	private static final int RETRY_COUNT = 3;

	private static final By COMPANY_CODE_INPUT = By.id("CompanyName");
	private static final By USERNAME_INPUT = By.id("UserName");
	private static final By PASSWORD_INPUT = By.id("PasswordInput");
	private static final By LOGIN_BUTTON = By.id("login");

	private static final By CREATE_PURCHASE_RETURN_BUTTON = By.xpath("//input[@id='Create']");
	private static final By VENDOR_DROPDOWN = By.id("select2-VendorId-container");
	private static final By VENDOR_SEARCH_INPUT = By.xpath("//span[@id='select2-VendorId-container']/following::input[@type='search'][1]");
	private static final By PRODUCT_CHECKBOX = By.id("ProductCheck");
	private static final By GST_TYPE_DROPDOWN = By.id("select2-GSTTypeId-container");

	private static final By PRODUCT_DROPDOWN = By.id("select2-ProductId-container");
	private static final By PRODUCT_SEARCH_INPUT = By.xpath("//span[@id='select2-ProductId-container']/following::input[@type='search'][1]");
	private static final By PRODUCT_OPTIONS = By.xpath("//ul[@id='select2-ProductId-results']/li[not(contains(@class,'loading-results')) and normalize-space()!='']");

	private static final By UOM_DROPDOWN = By.id("select2-UOMId-container");
	private static final By UOM_SEARCH_INPUT = By.xpath("//span[@id='select2-UOMId-container']/following::input[@type='search'][1]");
	private static final By UOM_OPTIONS = By.xpath("//ul[@id='select2-UOMId-results']/li[not(contains(@class,'loading-results')) and normalize-space()!='']");

	private static final By QUANTITY_INPUT = By.id("Qty");
	private static final By PRICE_INPUT = By.id("Price");
	private static final By ADD_BUTTON = By.id("btn_add");

	private static final By PURCHASE_RETURN_ROWS = By.xpath("//table[@id='PurchaseReturnTable']//tbody/tr[.//textarea]");
	private static final By PURCHASE_RETURN_LINE_TOTALS = By.xpath("//table[@id='PurchaseReturnTable']//tbody/tr[.//textarea]//td[contains(@class,'DetailTotal')]");
	private static final By SUBTOTAL_VALUE = By.xpath("//table[@id='PurchaseReturnTable']//tfoot//p[@id='FooterSubTotal']");
	private static final By GST_VALUE = By.xpath("//table[@id='PurchaseReturnTable']//tfoot//p[@id='FooterGST']");
	private static final By GRAND_TOTAL_VALUE = By.xpath("//table[@id='PurchaseReturnTable']//tfoot//p[@id='FCAmount']");

	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, WAIT_SECONDS);
	}

	@Test
	public void createPurchaseReturnAndValidateTotals() {
		login();
		openPurchaseReturnPage();
		selectFirstVendorIfAvailable();
		ensureProductMode();

		List<String> firstTwoProducts = captureFirstTwoProducts();
		Assert.assertEquals(firstTwoProducts.size(), PRODUCT_COUNT,
				"Unable to identify the first two products from the product dropdown.");

		for (int index = 0; index < firstTwoProducts.size(); index++) {
			addProduct(firstTwoProducts.get(index), index + 1);
		}

		validateFooterTotals();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	private void login() {
		driver.get(LOGIN_URL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(COMPANY_CODE_INPUT));

		clearAndType(COMPANY_CODE_INPUT, COMPANY_CODE);
		clearAndType(USERNAME_INPUT, USERNAME);
		clearAndType(PASSWORD_INPUT, PASSWORD);
		clickWhenReady(LOGIN_BUTTON);

		wait.until(ExpectedConditions.or(ExpectedConditions.urlContains("/ERP/"),
				ExpectedConditions.visibilityOfElementLocated(CREATE_PURCHASE_RETURN_BUTTON)));
	}

	private void openPurchaseReturnPage() {
		driver.navigate().to(PURCHASE_RETURN_URL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CREATE_PURCHASE_RETURN_BUTTON));
		clickWhenReady(CREATE_PURCHASE_RETURN_BUTTON);
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_DROPDOWN));
	}

	private void selectFirstVendorIfAvailable() {
		if (!isPresent(VENDOR_DROPDOWN)) {
			return;
		}

		String selectedVendor = getElementTextOrTitle(VENDOR_DROPDOWN);
		if (selectedVendor != null && !selectedVendor.trim().isEmpty()
				&& !selectedVendor.toLowerCase().contains("select")) {
			return;
		}

		clickWhenReady(VENDOR_DROPDOWN);
		wait.until(ExpectedConditions.visibilityOfElementLocated(VENDOR_SEARCH_INPUT));
		List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//ul[@id='select2-VendorId-results']/li[normalize-space()!='']")));

		for (WebElement option : options) {
			String text = option.getText().trim();
			if (!text.isEmpty() && !text.equalsIgnoreCase("No results found")) {
				clickElement(option);
				wait.until(new ExpectedCondition<Boolean>() {
					@Override
					public Boolean apply(WebDriver webDriver) {
						return !getElementTextOrTitle(VENDOR_DROPDOWN).isEmpty();
					}
				});
				return;
			}
		}
	}

	private void ensureProductMode() {
		WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(PRODUCT_CHECKBOX));
		if (!checkbox.isSelected()) {
			clickElement(checkbox);
		}
	}

	private List<String> captureFirstTwoProducts() {
		clickWhenReady(PRODUCT_DROPDOWN);
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_SEARCH_INPUT));
		List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_OPTIONS));

		List<String> productNames = new ArrayList<String>();
		for (WebElement option : options) {
			String text = option.getText().trim();
			if (!text.isEmpty() && !text.equalsIgnoreCase("No results found")) {
				productNames.add(text);
			}
			if (productNames.size() == PRODUCT_COUNT) {
				break;
			}
		}

		closeSelect2Dropdown();
		return productNames;
	}

	private void addProduct(String productLabel, int expectedRowCount) {
		selectProduct(productLabel);
		String selectedUom = selectFirstAvailableUom();
		Assert.assertFalse(selectedUom.isEmpty(), "No UOM option was available for product: " + productLabel);

		clearAndType(QUANTITY_INPUT, String.valueOf(PRODUCT_QUANTITY));
		clearAndType(PRICE_INPUT, formatCurrency(PRODUCT_PRICE));

		waitForGridToStabilize();
		int currentRowCount = getRowCount();
		clickWhenReady(ADD_BUTTON);
		wait.until(rowCountToBeGreaterThan(currentRowCount));
		Assert.assertEquals(getRowCount(), expectedRowCount,
				"Unexpected number of rows after adding product: " + productLabel);

		double actualLineTotal = getLastLineTotal();
		Assert.assertEquals(actualLineTotal, roundToTwoDecimals(PRODUCT_PRICE * PRODUCT_QUANTITY),
				"Line total mismatch for product: " + productLabel);
	}

	private void selectProduct(String productLabel) {
		clickWhenReady(PRODUCT_DROPDOWN);
		WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_SEARCH_INPUT));
		searchInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		searchInput.sendKeys(productLabel);

		By optionLocator = By.xpath("//ul[@id='select2-ProductId-results']/li[normalize-space()="
				+ toXPathLiteral(productLabel) + "]");
		clickWhenReady(optionLocator);
		waitForGridToStabilize();
	}

	private String selectFirstAvailableUom() {
		clickWhenReady(UOM_DROPDOWN);
		wait.until(ExpectedConditions.visibilityOfElementLocated(UOM_SEARCH_INPUT));
		List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(UOM_OPTIONS));

		for (WebElement option : options) {
			String text = option.getText().trim();
			if (!text.isEmpty() && !text.equalsIgnoreCase("No results found")) {
				clickElement(option);
				return text;
			}
		}

		closeSelect2Dropdown();
		return "";
	}

	private void validateFooterTotals() {
		double expectedSubtotal = roundToTwoDecimals(PRODUCT_COUNT * PRODUCT_PRICE * PRODUCT_QUANTITY);
		double actualSubtotal = parseAmount(getVisibleText(SUBTOTAL_VALUE));
		double actualGst = parseAmount(getVisibleText(GST_VALUE));
		double actualGrandTotal = parseAmount(getVisibleText(GRAND_TOTAL_VALUE));
		double lineTotalSum = getLineTotalsSum();

		Assert.assertEquals(lineTotalSum, expectedSubtotal, "Line totals do not match expected subtotal.");
		Assert.assertEquals(actualSubtotal, expectedSubtotal, "Subtotal mismatch.");

		String gstType = isPresent(GST_TYPE_DROPDOWN) ? getElementTextOrTitle(GST_TYPE_DROPDOWN) : "Exclusive";
		double expectedGst = calculateExpectedGst(expectedSubtotal, gstType);
		double expectedGrandTotal = calculateExpectedGrandTotal(expectedSubtotal, gstType, expectedGst);

		Assert.assertEquals(actualGst, expectedGst,
				"GST mismatch. GST type detected on screen: " + gstType + ".");
		Assert.assertEquals(actualGrandTotal, expectedGrandTotal,
				"Grand total mismatch. GST type detected on screen: " + gstType + ".");
	}

	private double calculateExpectedGst(double subtotal, String gstType) {
		if (gstType == null) {
			return roundToTwoDecimals(0);
		}

		String normalizedType = gstType.trim().toLowerCase();
		if (normalizedType.contains("inclusive")) {
			return roundToTwoDecimals((subtotal * DEFAULT_GST_PERCENTAGE) / (100 + DEFAULT_GST_PERCENTAGE));
		}
		if (normalizedType.contains("exclusive")) {
			return roundToTwoDecimals((subtotal * DEFAULT_GST_PERCENTAGE) / 100);
		}
		if (normalizedType.contains("zero") || normalizedType.contains("overseas")) {
			return roundToTwoDecimals(0);
		}
		return roundToTwoDecimals(parseAmount(getVisibleText(GST_VALUE)));
	}

	private double calculateExpectedGrandTotal(double subtotal, String gstType, double expectedGst) {
		if (gstType != null && gstType.trim().toLowerCase().contains("inclusive")) {
			return roundToTwoDecimals(subtotal);
		}
		return roundToTwoDecimals(subtotal + expectedGst);
	}

	private double getLineTotalsSum() {
		List<WebElement> totalCells = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PURCHASE_RETURN_LINE_TOTALS));
		double sum = 0;
		for (WebElement totalCell : totalCells) {
			String amountText = totalCell.getText().trim();
			if (!amountText.isEmpty()) {
				sum += parseAmount(amountText);
			}
		}
		return roundToTwoDecimals(sum);
	}

	private double getLastLineTotal() {
		List<WebElement> totalCells = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PURCHASE_RETURN_LINE_TOTALS));
		WebElement lastCell = totalCells.get(totalCells.size() - 1);
		return parseAmount(lastCell.getText());
	}

	private int getRowCount() {
		return driver.findElements(PURCHASE_RETURN_ROWS).size();
	}

	private ExpectedCondition<Boolean> rowCountToBeGreaterThan(final int previousCount) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return webDriver.findElements(PURCHASE_RETURN_ROWS).size() > previousCount;
			}

			@Override
			public String toString() {
				return "row count to be greater than " + previousCount;
			}
		};
	}

	private void waitForGridToStabilize() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(QUANTITY_INPUT));
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRICE_INPUT));
	}

	private void clearAndType(By locator, String value) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.click();
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		element.sendKeys(value);
	}

	private String getVisibleText(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
	}

	private String getElementTextOrTitle(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		String text = element.getText().trim();
		if (!text.isEmpty()) {
			return text;
		}
		String title = element.getAttribute("title");
		return title == null ? "" : title.trim();
	}

	private void clickWhenReady(By locator) {
		for (int attempt = 0; attempt < RETRY_COUNT; attempt++) {
			try {
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
				clickElement(element);
				return;
			} catch (StaleElementReferenceException staleElementReferenceException) {
				if (attempt == RETRY_COUNT - 1) {
					throw staleElementReferenceException;
				}
			}
		}
	}

	private void clickElement(WebElement element) {
		for (int attempt = 0; attempt < RETRY_COUNT; attempt++) {
			try {
				wait.until(ExpectedConditions.visibilityOf(element));
				wait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				return;
			} catch (Exception exception) {
				if (attempt == RETRY_COUNT - 1) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
					return;
				}
			}
		}
	}

	private void closeSelect2Dropdown() {
		new Actions(driver).sendKeys(Keys.ESCAPE).perform();
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(@class,'select2-container--open')]")));
		} catch (TimeoutException ignored) {
			// Non-blocking cleanup for select2 overlays.
		}
	}

	private boolean isPresent(By locator) {
		return !driver.findElements(locator).isEmpty();
	}

	private double parseAmount(String value) {
		String normalizedValue = value.replace(",", "").replaceAll("[^0-9.-]", "");
		if (normalizedValue.isEmpty()) {
			return 0;
		}
		return roundToTwoDecimals(Double.parseDouble(normalizedValue));
	}

	private double roundToTwoDecimals(double value) {
		return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	private String formatCurrency(double value) {
		return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).toPlainString();
	}

	private String toXPathLiteral(String value) {
		if (!value.contains("'")) {
			return "'" + value + "'";
		}
		if (!value.contains("\"")) {
			return "\"" + value + "\"";
		}

		StringBuilder literal = new StringBuilder("concat(");
		String[] parts = value.split("'");
		for (int index = 0; index < parts.length; index++) {
			if (index > 0) {
				literal.append(",\"'\",");
			}
			literal.append("'").append(parts[index]).append("'");
		}
		literal.append(")");
		return literal.toString();
	}
}
