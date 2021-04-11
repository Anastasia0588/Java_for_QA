package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase{

  @Test (enabled = false)
  public void testGroupCreation() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().groupAll();
    GroupData group = new GroupData().withName("Test1");
    app.group().create(group);
    Set<GroupData> after = app.group().groupAll();
    Assert.assertEquals(after.size(), before.size() + 1);
    app.goTo().homePage();

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }
}
