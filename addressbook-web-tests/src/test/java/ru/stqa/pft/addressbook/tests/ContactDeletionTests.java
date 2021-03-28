package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

    @Test
    public void ContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToContactCreation();
            app.getContactHelper().createContact(new ContactData("CreateNewContact",
                    "Ivanov",
                    "Intech",
                    "Moscow",
                    "79201111111",
                    "example@yandex.com",
                    "Test1"));
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().acceptAlert();
    }
}
