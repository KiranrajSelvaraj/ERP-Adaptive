package com.BaseClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BaseClass {
	
public static WebDriver driver;
	
	public static WebDriver Browser_launch() {
		 
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Adaptive\\chromedriver\\chromedriver3.exe");
 
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;

	
	}
	
	
	public static void Url(String url) {
		driver.get(url);
	}
 
	// to Close
	public static void Close() {
		driver.close();
	}
 
	// to Quit
	public static void Quit() {
		driver.quit();
	}
 
	// to Navigate
	public static void Navigate_to(String url) {
		driver.navigate().to(url);
	}
 
	// to Navigate back
	public static void Navigate_back() {
		driver.navigate().back();
	}
 
	// to Navigate forward
	public static void Navigate_forward() {
		driver.navigate().forward();
	}
 
	// to Navigate refresh
	public static void Navigate_refresh() {
		driver.navigate().refresh();
	}
 
	// to Get Title
	public static void Get_title() {
		String title = driver.getTitle();
		System.out.println(title);
	}
 
	// to Get Current URL
	public static void Get_Current_url() {
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);
	}
 
	// to Get Text
	public static void Get_Text(WebElement w) {
		String GT = w.getText();
		System.out.println(GT);
	}
 
	// to Get Attribute
	public static void Get_Attribute(WebElement w, String name) {
		String attribute = w.getAttribute(name);
		System.out.println(attribute);
	}
 
	// to SendKeys
	public static void Sendkeys(WebElement w, String input) {
		w.sendKeys(input);
	}
 
	// to click
	public static void click(WebElement w) {
		w.click();
	}
 
	// to clear
	public static void clear(WebElement w) {
		w.clear();
	}
 
	// Is_displayed
	public static void Isdisplayed(WebElement w) {
		boolean dis = w.isDisplayed();
		System.out.println(dis);
	}
 
	// Is_enabled
	public static void Isenabled(WebElement w) {
		boolean enabled = w.isEnabled();
		System.out.println(enabled);
	}
 
	// Is_Selected
	public static void Isselected(WebElement w) {
		boolean selected = w.isSelected();
		System.out.println(selected);
	}
 
	// to Wait
	public static void Implicitywait(int i) {
		driver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);
	}
 
	// actions
	public static void actions(WebElement w, String s) {
		Actions a = new Actions(driver);
 
		if (s.equalsIgnoreCase("Drag")) {
			a.clickAndHold(w).build().perform();
		} else if (s.equalsIgnoreCase("drop")) {
			a.moveToElement(w).build().perform();
		} else if (s.equalsIgnoreCase("Click")) {
			a.click(w).build().perform();
		} else if (s.equalsIgnoreCase("Context click")) {
			a.contextClick(w).build().perform();
		} else if (s.equalsIgnoreCase("doubleclick")) {
			a.doubleClick(w).perform();
		}
	}
 
	// check deladdress
 
	public static void move(WebElement w, String val) {
		Actions Ac = new Actions(driver);
		if (val.equalsIgnoreCase("true")) {
			Ac.moveToElement(w);
 
		}
 
	}
 
	// Thread Sleep
	public static void Thread_Sleep(int s) throws InterruptedException {
		Thread.sleep(s);
	}
 
	// Single Drop_down
	public static void Single_Dropdown(WebElement w, String type, String value, int i) {
		Select s = new Select(w);
 
		if (type.equalsIgnoreCase("Select by Index")) {
			s.selectByIndex(i);
		} else if (type.equalsIgnoreCase("selectbyvalue")) {
			s.selectByValue(value);
 
		} else if (type.equalsIgnoreCase("selectbyvisibletext")) {
			s.selectByVisibleText(value);
		}
	}
 
	// Multiple_Drop_down
	public static void Multiple_dropdown(WebElement w, String name) {
		Select s = new Select(w);
 
		if (name.equalsIgnoreCase("Get Options")) {
			List<WebElement> options = s.getOptions();
			for (WebElement All : options) {
				String text = All.getText();
				System.out.println(text);
			}
		}
 
		else if (name.equalsIgnoreCase("Get All Selected Options")) {
			List<WebElement> allSelected = s.getAllSelectedOptions();
			for (WebElement All : allSelected) {
				String text = All.getText();
				System.out.println(text);
			}
		} else if (name.equalsIgnoreCase("Get First Selected Option")) {
			WebElement firstSelected = s.getFirstSelectedOption();
			String All = firstSelected.getText();
			System.out.println(All);
		} else if (name.equalsIgnoreCase("Is multiple")) {
			boolean multiple = s.isMultiple();
			System.out.println(multiple);
		}
 
	}
 
	// Scroll_down
	public static void Scrolldown(String s, WebElement w) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(s, w);
 
	}
 
	// screenshot
	public static void screenshot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File s1 = ts.getScreenshotAs(OutputType.FILE);
		File ss = new File("C:\\Users\\WELCOME\\eclipse-workspace\\Selenium_Testing\\Screenshot\\" + ".png");
		FileUtils.copyFile(s1, ss);
	}
 
	public static void robotdown(String n) throws AWTException {
		Robot r = new Robot();
		if (n.equalsIgnoreCase("1")) {
 
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		}
		if (n.equalsIgnoreCase("2")) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		}
		if (n.equalsIgnoreCase("3")) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		}
 
	}
 
	// frames
	public static void frame() {
		driver.switchTo().frame(0);
 
	}
 
	public static void frames() {
		driver.switchTo().defaultContent();
 
	}
 
	// Window_Handles
	public static void Window_Handles() {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String ID : windowHandles) {
			String title = driver.switchTo().window(ID).getTitle();
			System.out.println(title);
		}
	}
 
	// Is Active, Is HQ, HQ Is Active checkbox code
 
	public void isActivecheckbox(WebElement w, String value) {
		if (value.equalsIgnoreCase("0")) {
			w.click();
 
		} else if (value.equalsIgnoreCase("1")) {
			Isdisplayed(w);
		}
 
	}
 
	// checkbox
	public void checkbox(WebElement w, String value) {
		if (value.equalsIgnoreCase("1")) {
			w.click();
 
		} else if (value.equalsIgnoreCase("0")) {
			Isdisplayed(w);
		}
 
	}
 
	// checkbox with single textbox
	public void checkboxtextbox1(WebElement w, String val, WebElement textbox1, String input) {
		if (val.equalsIgnoreCase("1")) {
			w.click();
			if (textbox1.isDisplayed()) {
				Sendkeys(textbox1, input);
 
			} else {
				System.out.println("element not displayed");
			}
 
		} else {
			System.out.println("checkbox not required");
 
		}
 
	}
 
	// check box with 2 textbox
	public void checkboxtextbox2(WebElement w, String val, WebElement textbox1, String input1, WebElement textbox2,
			String input2) {
		if (val.equalsIgnoreCase("1")) {
			w.click();
			if (textbox1.isDisplayed()) {
				textbox1.clear();
				Sendkeys(textbox1, input1);
				if (textbox2.isDisplayed()) {
					textbox2.clear();
					Sendkeys(textbox2, input2);
				} else {
					System.out.println("2nd textbox nill");
				}
 
			} else {
				System.out.println("textbox nill");
			}
 
		} else {
			System.out.println("checkbox not required");
 
		}
 
	}
 
	// System Settings for CheckBox
	public void systemSettingCheckBox(WebElement w, String value) {
		if (w.isDisplayed()) {
			if (value.equalsIgnoreCase("0")) {
				w.click();
			} else if (value.equalsIgnoreCase("1")) {
				Isdisplayed(w);
			}
		}
	}
 
	public void removecomma(String s) {
 
		if (s.contains(",")) {
			String replace = s.replace(",", "");
			System.out.println(replace);
		} else {
			System.out.println(s);
		}
	}
 
	// System Settings for textBox
 
	public void systemSettingTextBox(WebElement w, String val) {
		if (w.isDisplayed()) {
			Sendkeys(w, val);
		}
 
	}


}
