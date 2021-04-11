package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

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

        Set<GroupData> before = app.group().groupAll();
        GroupData modifiedGroup = before.iterator().next();
        GroupData groupData = new GroupData().withId(modifiedGroup.getId())
                .withName("Test1").withHeader("Test11").withFooter("Test22");

        app.group().modify(groupData);
        Set<GroupData> after = app.group().groupAll();
        Assert.assertEquals(before.size(), after.size());
        app.goTo().homePage();

        before.remove(modifiedGroup);
        before.add(groupData);
        Assert.assertEquals(before, after);
    }

}
