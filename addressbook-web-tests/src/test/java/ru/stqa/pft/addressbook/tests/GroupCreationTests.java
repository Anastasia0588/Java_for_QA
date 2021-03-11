package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.initGroupCreation();
    app.fillGruopForm(new GroupData("Test1", "TEST2", "TEST3"));
    app.submitGroupCreation();
    app.returnToGroupPage();

  }


}
