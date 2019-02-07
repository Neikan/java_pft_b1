package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Джон").withMiddlename("Джошович").withLastname("Смит").withAddress("Вашингтон").withMobilePhone("+1119188900075").withEmail("smite@outlook.com").withBday("17").withBmonth("Декабрь").withByear("1980").withAddress2("DC").withGroup("[не выбрано]");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));//Проверка на основе хеширования
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
  }
}
