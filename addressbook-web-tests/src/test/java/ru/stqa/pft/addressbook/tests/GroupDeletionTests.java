package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase{
  @BeforeMethod
  public  void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().groupAll().size() == 0){
    app.group().create(new GroupData().withName("DeletedGroupName").withHeader("DeletedGroupHeader").withFooter("GroupForDeletionFooter"));
   }
  }

  @Test (enabled = false)
  public void testGroupDeletion() {
    Set<GroupData> before = app.group().groupAll();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().groupAll();
    Assert.assertEquals(after.size(), before.size() - 1);
    app.goTo().homePage();

    before.remove(deletedGroup);
    Assert.assertEquals(before, after);
  }

}
