package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {//Добавлена проверка и обеспечение предусловий выполнения ContactModificationTests и ContactDeletionTests
      app.contact().create(new ContactData()
              .withFirstname("Евгений").withMiddlename("Михайлович").withLastname("Сладков").withAddress("Ростов-на-Дону, бул. Комарова, 1М").withMobile("+79188900075").withEmail("sladkov.e.m.@outlook.com").withBday("12").withBmonth("Ноябрь").withByear("1987").withAddress2("Ростов-на-Дону").withGroup("[не выбрано]"), true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1));//Проверка на основе хеширования
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}