package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test1", null, null);
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    //Добавлено сравнение списков групп до и после создания
    int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }

    //Добавлен второй способ поиска максимального элемента в коллекции после создания (без лямбда-вырежания)
    Comparator<? super GroupData> byId = new Comparator<GroupData>() {
      @Override
      public int compare(GroupData o1, GroupData o2) {
        return Integer.compare(o1.getId(), o2.getId());
      }
    };
    int max1 = after.stream().max(byId).get().getId();

    group.setId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
