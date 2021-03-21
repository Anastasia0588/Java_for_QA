package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void ContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().acceptAlert();
    }
}
