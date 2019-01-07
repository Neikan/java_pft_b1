package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTests {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testContactCreationTests() throws Exception {
    driver.get("http://localhost/addressbook/");
    driver.findElement(By.name("user")).click();
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("secret");
    driver.findElement(By.xpath("//input[@value='Войти']")).click();
    driver.findElement(By.linkText("Добавить контакт")).click();
    driver.findElement(By.name("firstname")).click();
    driver.findElement(By.name("firstname")).clear();
    driver.findElement(By.name("firstname")).sendKeys("Евгений");
    driver.findElement(By.name("middlename")).click();
    driver.findElement(By.name("middlename")).clear();
    driver.findElement(By.name("middlename")).sendKeys("Михайлович");
    driver.findElement(By.name("lastname")).click();
    driver.findElement(By.name("lastname")).clear();
    driver.findElement(By.name("lastname")).sendKeys("Сладков");
    driver.findElement(By.name("address")).click();
    driver.findElement(By.name("address")).clear();
    driver.findElement(By.name("address")).sendKeys("Ростов-на-Дону, бул. Комарова, 1М");
    driver.findElement(By.name("mobile")).click();
    driver.findElement(By.name("mobile")).clear();
    driver.findElement(By.name("mobile")).sendKeys("+79188900075");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("sladkov.m.@outlook.com");
    driver.findElement(By.name("bday")).click();
    new Select(driver.findElement(By.name("bday"))).selectByVisibleText("12");
    driver.findElement(By.name("bday")).click();
    driver.findElement(By.name("bmonth")).click();
    new Select(driver.findElement(By.name("bmonth"))).selectByVisibleText("Ноябрь");
    driver.findElement(By.name("bmonth")).click();
    driver.findElement(By.name("byear")).click();
    driver.findElement(By.name("byear")).clear();
    driver.findElement(By.name("byear")).sendKeys("1987");
    driver.findElement(By.name("address2")).click();
    driver.findElement(By.name("address2")).clear();
    driver.findElement(By.name("address2")).sendKeys("Ростов-на-Дону");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Примечание:'])[1]/following::input[1]")).click();
    driver.findElement(By.linkText("home page")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
