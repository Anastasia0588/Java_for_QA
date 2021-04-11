package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().groupList().size() == 0){
            app.group().create(new GroupData().withName("Test-1").withFooter("Test-3"));
        }
    }

    @Test
    public void TestGroupModification() {

        List<GroupData> before = app.group().groupList();
        int index = before.size() - 1;
        GroupData groupData = new GroupData().withId(before.get(index).getId())
                .withName("Test1").withHeader("Test11").withFooter("Test22");

        app.group().modify(index, groupData);
        List<GroupData> after = app.group().groupList();
        Assert.assertEquals(before.size(), after.size());
        app.goTo().homePage();

        before.remove(index);
        before.add(groupData);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
