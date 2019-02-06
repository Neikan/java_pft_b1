package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;

public class ContactDeletionTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {//Добавлена проверка и обеспечение предусловий выполнения ContactModificationTests и ContactDeletionTests
      app.getContactHelper().createContact(new ContactData("Евгений", "Михайлович", "Сладков", "Ростов-на-Дону, бул. Комарова, 1М", "+79188900075", "sladkov.e.m.@outlook.com", "12", "Ноябрь", "1987", "Ростов-на-Дону", "[не выбрано]"), true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().acceptDeletionContacts();
    app.getContactHelper().messageCompleteDeletionContacts();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}