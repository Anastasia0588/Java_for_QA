package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    initGroupCreation();
    fillGruopForm(new GroupData("Test1", "TEST2", "TEST3"));
    submitGroupCreation();
    returnToGroupPage();

  }


}
