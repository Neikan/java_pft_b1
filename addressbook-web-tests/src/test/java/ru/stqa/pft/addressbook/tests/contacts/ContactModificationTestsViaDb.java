package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTestsViaDb extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      if (app.contact().all().size() == 0) {//Добавлена проверка и обеспечение предусловий выполнения ContactModificationTests и ContactDeletionTests
        app.contact().create(new ContactData()
                .withFirstname("Евгений").withMiddlename("Михайлович").withLastname("Сладков").withAddress("Ростов-на-Дону, бул. Комарова, 1М").withPhoneMobile("+79188900075").withEmailOne("sladkov.e.m.@outlook.com").withBday("12").withBmonth("Ноябрь").withByear("1987").withAddress2("Ростов-на-Дону").withGroup("[не выбрано]"), true);
      }
    }
  }

  @Test
  public void testContactModification() throws Exception {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Евгений").withMiddlename("Михайлович").withLastname("Сладков").withAddress("Ростов-на-Дону, бул. Комарова, 1М").withPhoneMobile("+79188900075").withEmailOne("sladkov.e.m.@outlook.com").withBday("12").withBmonth("Ноябрь").withByear("1987").withAddress2("Ростов-на-Дону").withGroup("[не выбрано]").withPhoto(new File("src/test/resources/Batman.jpg"));
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));//Проверка на основе хеширования
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
