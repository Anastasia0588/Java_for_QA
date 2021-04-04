package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test

    public void ContactModification() {
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
        int before = app.getContactHelper().getContactCount();
        app.getNavigationHelper().goToEditContact();
        app.getContactHelper().fillContactData(new ContactData("Marat", "Drozdovskaia", "Magnit", "NN", "79201111111", "example@yandex.com", null), false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(before, after);
    }
}
