package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().groupAll().size() == 0){
            app.group().create(new GroupData().withName("Test-1").withFooter("Test-3"));
        }
    }

    @Test
    public void TestGroupModification() {

        Groups before = app.group().groupAll();
        GroupData modifiedGroup = before.iterator().next();
        GroupData groupData = new GroupData().withId(modifiedGroup.getId())
                .withName("Test1").withHeader("Test11").withFooter("Test22");

        app.group().modify(groupData);
        Groups after = app.group().groupAll();
        app.goTo().homePage();
        assertEquals(before.size(), after.size());
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(groupData)));
    }
}
