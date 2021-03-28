package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test

    public void ContactModification() {
        app.getNavigationHelper().goToHomePage();
        app.getNavigationHelper().goToEditContact();
        app.getContactHelper().fillContactData(new ContactData("Marat", "Drozdovskaia", "Magnit", "NN", "79201111111", "example@yandex.com", null));
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
    }
}
