package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void TestGroupModification() {
        app.getNavigationHelper().goToGroupPage();
               if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("Test1", null, "TEST3"));
        }
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModifacation();
        app.getGroupHelper().fillGroupForm(new GroupData("RenameGroup", "Test11", "Test22"));
        app.getGroupHelper().submitModification();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(before, after);
        app.getNavigationHelper().goToHomePage();
    }
}
