package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Евгений", "Михайлович", "Сладков", "Ростов-на-Дону, бул. Комарова, 1М", "+79188900075", "sladkov.m.@outlook.com", "12", "Ноябрь", "1987", "Ростов-на-Дону"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }
}
