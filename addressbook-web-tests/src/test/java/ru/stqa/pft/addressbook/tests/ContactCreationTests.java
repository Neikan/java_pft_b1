package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData().withFirstname("Евгений").withMiddlename("Михайлович").withLastname("Волков").withBday("12").withBmonth("Ноябрь").withByear("1987").withGroup("[не выбрано]")});
    list.add(new Object[] {new ContactData().withFirstname("Дмитрий").withMiddlename("Дмитриевич").withLastname("Воронов").withBday("13").withBmonth("Май").withByear("1988").withGroup("[не выбрано]")});
    list.add(new Object[] {new ContactData().withFirstname("Михаил").withMiddlename("Евгеньевич").withLastname("Кабанов").withBday("14").withBmonth("Июль").withByear("1989").withGroup("[не выбрано]")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/Batman.jpg");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));//Проверка на основе хеширования
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)//Определен относительный пункт к файлу фотографии, добавлен тест на пр…
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/Batman.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
