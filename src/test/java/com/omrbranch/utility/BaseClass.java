package com.omrbranch.utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class BaseClass {

  public static WebDriver driver;
  private JavascriptExecutor js;
  private Select select;
  private Actions actions;
  private Robot robot;

  // ==========================================================
  // PROJECT & CONFIGURATION
  // ==========================================================

  // Returns the current project directory path
  public static String getProjectPath() {
    return System.getProperty("user.dir");
  }

  // Reads a value from Config.properties file based on the given key
  public static String getPropertyFileValue(String key) {
    String configPath = getProjectPath() + "\\src\\test\\resources\\config\\Config.properties";
    try (FileInputStream fis = new FileInputStream(configPath)) {
      Properties prop = new Properties();
      prop.load(fis);
      return prop.getProperty(key);
    } catch (IOException e) {
    	
      throw new RuntimeException("Property key not found or file missing: " + key, e);
    }
  }

  // ==========================================================
  // BROWSER ACTIONS
  // ==========================================================

  // Launches a browser based on the config file value
  public static void browserLaunch() {
    String browserType = getPropertyFileValue("browserType");
    int timeout = Integer.parseInt(getPropertyFileValue("timeout"));

    switch (browserType.toUpperCase()) {
    case "CHROME":
      driver = new ChromeDriver();
      break;
    case "FIREFOX":
      driver = new FirefoxDriver();
      break;
    case "EDGE":
      driver = new EdgeDriver();
      break;
    default:
      throw new IllegalArgumentException("Invalid Browser Type in Config: " + browserType);
    }

    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
  }

  // Opens the application URL from Config.properties
  public static void enterApplnUrl() {
    String url = getPropertyFileValue("url");
    driver.get(url);
  }

  // Closes the current browser tab
  public static void closeBrowser() {
    if (driver != null)
      driver.close();
  }

  // Quits the entire browser session
  public static void quitBrowser() {
    if (driver != null)
      driver.quit();
  }

  // ==========================================================
  // WAIT UTILITIES
  // ==========================================================

  // Sets an implicit wait for all elements
  public void implicitWait(int secs) {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(secs));
  }

  // Waits until the given element becomes visible
  public WebElement waitForVisible(WebElement element) {
    return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element));
  }

  // Waits until the given element becomes clickable
  public WebElement waitForClickable(WebElement element) {
    return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element));
  }

  // ==========================================================
  // ELEMENT INTERACTIONS
  // ==========================================================

  // Sends text input into a text box after clearing any existing value
  public void elementSendKeys(WebElement element, String data) {
    waitForVisible(element);
    element.clear();
    element.sendKeys(data);
  }

  // Sends text input followed by pressing the Enter key
  public void elementSendKeysEnter(WebElement element, String data) {
    waitForVisible(element);
    element.sendKeys(data, Keys.ENTER);
  }

  // Clicks on an element when it becomes clickable
  public void elementClick(WebElement element) {
    waitForClickable(element);
    element.click();
  }

  // Clears the value of an input field
  public void elementClear(WebElement element) {
    waitForVisible(element);
    element.clear();
  }

  // ==========================================================
  // DROPDOWN HANDLING
  // ==========================================================

  public void selectByVisibleText(WebElement element, String text) {
    waitForVisible(element);
    new Select(element).selectByVisibleText(text);
  }

  public void selectByValue(WebElement element, String value) {
    waitForVisible(element);
    new Select(element).selectByValue(value);
  }

  public void selectByIndex(WebElement element, int index) {
    waitForVisible(element);
    new Select(element).selectByIndex(index);
  }

  public List<String> getAllDropdownOptions(WebElement element) {
    select = new Select(element);
    List<String> optionTexts = new ArrayList<>();
    for (WebElement option : select.getOptions()) {
      optionTexts.add(option.getText());
    }
    return optionTexts;
  }

  // ==========================================================
  // ALERT HANDLING
  // ==========================================================

  public void acceptAlert() {
    driver.switchTo().alert().accept();
  }

  public void dismissAlert() {
    driver.switchTo().alert().dismiss();
  }

  public String getAlertText() {
    return driver.switchTo().alert().getText();
  }

  // ==========================================================
  // MOUSE, KEYBOARD & JS ACTIONS
  // ==========================================================

  public void dragAndDrop(WebElement src, WebElement dest) {
    actions = new Actions(driver);
    actions.dragAndDrop(src, dest).perform();
  }

  public void moveToElement(WebElement element) {
    actions = new Actions(driver);
    actions.moveToElement(element).perform();
  }

  public void enterKey() throws AWTException {
    robot = new Robot();
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
  }

  public void scrollIntoView(WebElement element) {
    js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(true)", element);
  }

  public void clickUsingJs(WebElement element) {
    js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", element);
  }

  // ==========================================================
  // SCREENSHOT CAPTURE
  // ==========================================================

  // Captures a screenshot and saves it with timestamp
  public void screenshotWithTimestamp(String fileName) throws IOException {
    String folder = getProjectPath() + getPropertyFileValue("screenshotPath");
    String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File dest = new File(folder + fileName + "_" + time + ".png");
    FileUtils.copyFile(src, dest);
  }

  // Returns the screenshot as byte array for reporting
  public byte[] getScreenshotAsBytes() {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }

  // ==========================================================
  // WINDOW HANDLING
  // ==========================================================

  public void switchToChildWindow() {
    String main = driver.getWindowHandle();
    for (String each : driver.getWindowHandles()) {
      if (!each.equals(main)) {
        driver.switchTo().window(each);
        break;
      }
    }
  }

  // ==========================================================
  // ELEMENT LOCATORS
  // ==========================================================

  public WebElement findById(String id) {
    return driver.findElement(By.id(id));
  }

  public WebElement findByName(String name) {
    return driver.findElement(By.name(name));
  }

  public WebElement findByXpath(String xpath) {
    return driver.findElement(By.xpath(xpath));
  }

  // ==========================================================
  // ELEMENT INFORMATION
  // ==========================================================

  public String getElementText(WebElement element) {
    waitForVisible(element);
    return element.getText();
  }

  public String getDomProperty(WebElement element, String propertyName) {
    waitForVisible(element);
    return element.getDomProperty(propertyName);
  }

  public boolean isElementSelected(WebElement element) {
    waitForVisible(element);
    return element.isSelected();
  }

  public boolean isElementEnabled(WebElement element) {
    waitForVisible(element);
    return element.isEnabled();
  }

  // ==========================================================
  // THREAD SLEEP (DEBUGGING PURPOSE)
  // ==========================================================

  public void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
