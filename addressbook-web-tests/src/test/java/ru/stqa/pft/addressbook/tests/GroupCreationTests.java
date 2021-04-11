package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().groupAll();
    GroupData group = new GroupData().withName("Test1");
    app.group().create(group);
    Groups after = app.group().groupAll();
    assertThat(after.size(), equalTo(before.size() + 1));
    app.goTo().homePage();

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
