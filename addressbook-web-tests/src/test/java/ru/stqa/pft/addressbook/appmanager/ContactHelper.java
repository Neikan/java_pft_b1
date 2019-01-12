package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void submitContactCreation() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
    wd.findElement(By.name("bday")).click();
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
    wd.findElement(By.name("bday")).click();
    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBmonth());
    wd.findElement(By.name("bmonth")).click();
    type(By.name("byear"), contactData.getByear());
    type(By.name("address2"), contactData.getAddress2());
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Удалить']"));
  }

  public void acceptDeletionContacts() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Изменить']"));
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void messageCompleteDeletionContacts() {
    wd.findElement(By.xpath("//*[contains(text(), 'Record successful deleted')]"));
  }
}