package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBmonth());
    type(By.name("byear"), contactData.getByear());
    type(By.name("address2"), contactData.getAddress2());

    if (creation) {//добавлена проверка наличия/отсутствия элементов на форме
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());

    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void gotoNewContactPage() {
    click(By.linkText("Добавить контакт"));
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id + "']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Удалить']"));
  }

  public void acceptDeletionContacts() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int id) {
    //wd.findElement(By.cssSelector("input[value='"+ id + "']")).findElement(By.xpath("//a[@href='edit.php?id="+ id +"']")).click();  //Способ 1. Рожден через боль и страдание
    wd.findElement(By.xpath("//a[@href='edit.php?id="+ id +"']")).click();  //Способ 2. Более простой способ перехода на страницу редактирования контакта - удалено лишнее
    //wd.findElement(By.cssSelector((String.format("a[@href='edit.php?id=%s']", id)))); //Способ 3. Другая запись "Способа 2"
    //wd.findElement((By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id)))); //Способ 4
    //wd.findElement((By.cssSelector(String.format("a[href='edit.php?id=%s']", id))));  //Способ 5

    //Способ 6. Работал бы в старой реализации с индексами
    //WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    //WebElement row = checkbox.findElement(By.xpath("./../.."));
    //List<WebElement> cells = row.findElements(By.tagName("td"));
    //cells.get(7).findElement(By.tagName("a")).click();
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void messageCompleteDeletionContacts() {
    wd.findElement(By.xpath("//*[contains(text(), 'Record successful deleted')]"));
  }

  public void create(ContactData contactData, boolean creation) {
    gotoNewContactPage();
    fillContactForm(contactData, creation);
    submitContactCreation();
    contactCashe = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCashe = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    acceptDeletionContacts();
    contactCashe = null;
    messageCompleteDeletionContacts();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCashe = null;

  public Contacts all() {
    if (contactCashe != null) {
      return new Contacts(contactCashe);
    }

    contactCashe = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = element.findElement(By.xpath("td[2]")).getText();
      String firstname = element.findElement(By.xpath("td[3]")).getText();
      contactCashe.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return new Contacts(contactCashe);
  }
}