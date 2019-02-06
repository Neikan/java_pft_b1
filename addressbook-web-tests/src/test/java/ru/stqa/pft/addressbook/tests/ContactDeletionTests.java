package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;

public class ContactDeletionTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {//Добавлена проверка и обеспечение предусловий выполнения ContactModificationTests и ContactDeletionTests
      app.contact().create(new ContactData("Евгений", "Михайлович", "Сладков", "Ростов-на-Дону, бул. Комарова, 1М", "+79188900075", "sladkov.e.m.@outlook.com", "12", "Ноябрь", "1987", "Ростов-на-Дону", "[не выбрано]"), true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {

    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }
}