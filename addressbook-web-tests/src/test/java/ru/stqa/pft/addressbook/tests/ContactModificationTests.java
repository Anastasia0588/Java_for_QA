package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

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
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToEditContact(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Umberto", "Gonsalez", "Amigos", "NN", "79201111111", "example@yandex.com", null);
        app.getContactHelper().fillContactData(contact, false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(), after.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
