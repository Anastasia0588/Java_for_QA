package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.getGroupHelper().createGroup(new GroupData("Test1", "TEST2", "TEST3"));
    app.getNavigationHelper().goToHomePage();
  }


}
