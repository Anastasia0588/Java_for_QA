package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (! app.contact().isThereAContact()) {
            app.goTo().creation();
            app.contact().create(new ContactData("CreateNewContact",
                    "Ivanov",
                    "Intech",
                    "Moscow",
                    "79201111111",
                    "example@yandex.com",
                    "Test1"));
            app.goTo().homePage();
        }
    }


    @Test

    public void ContactModification() {
        List<ContactData> before = app.contact().contactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(), "Umberto", "Gonsalez", "Amigos", "NN", "79201111111", "example@yandex.com", null);
        app.goTo().editContact(index);
        app.contact().fillContact(contact, false);
        app.goTo().homePage();
        List<ContactData> after = app.contact().contactList();
        Assert.assertEquals(before.size(), after.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
