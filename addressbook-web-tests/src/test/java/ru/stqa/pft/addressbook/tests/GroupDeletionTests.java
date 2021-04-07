package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{
  @BeforeMethod
  public  void ensurePreconditions() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
    app.getGroupHelper().createGroup(new GroupData("GroupForDeletion", "DeletedGroup", "TEST3"));
   }
  }

  @Test
  public void testGroupDeletion() {

    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);
    app.getNavigationHelper().goToHomePage();

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
