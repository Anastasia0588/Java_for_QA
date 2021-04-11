package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{
  @BeforeMethod
  public  void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().groupList().size() == 0){
    app.group().create(new GroupData().withName("DeletedGroupName").withHeader("DeletedGroupHeader").withFooter("GroupForDeletionFooter"));
   }
  }

  @Test
  public void testGroupDeletion() {
    List<GroupData> before = app.group().groupList();
    int index = before.size() - 1;
    app.group().delete(index);
    List<GroupData> after = app.group().groupList();
    Assert.assertEquals(after.size(), before.size() - 1);
    app.goTo().homePage();

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
