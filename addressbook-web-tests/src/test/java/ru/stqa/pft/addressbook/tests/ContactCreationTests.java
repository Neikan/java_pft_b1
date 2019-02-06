package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Джон").withMiddlename("Джошович").withLastname("Смит").withAddress("Вашингтон").withMobile("+1119188900075").withEmail("smite@outlook.com").withBday("17").withBmonth("Декабрь").withByear("1980").withAddress2("DC").withGroup("[не выбрано]");
    app.contact().create(contact, true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    contact.withId(after.stream().mapToInt(c -> contact.getId()).max().getAsInt());
    Assert.assertEquals(before, after);
  }
}
