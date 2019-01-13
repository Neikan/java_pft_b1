package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {//Добавлена проверка и обеспечение предусловий выполнения ContactModificationTests и ContactDeletionTests
      app.getContactHelper().createContact(new ContactData("Евгений", "Михайлович", "Сладков", "Ростов-на-Дону, бул. Комарова, 1М", "+79188900075", "sladkov.e.m.@outlook.com", "12", "Ноябрь", "1987", "Ростов-на-Дону", "[не выбрано]"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Евгений", "Михайлович", "Воронов", "Москва, ул. Ленина, 209", "+79188900077", "voronov.e.m@outlook.com", "13", "Ноябрь", "1980", "Москва", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}