package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{
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
    public void ContactDeletion() {
        List<ContactData> before = app.contact().contactList();
        int index = before.size() - 1;
        app.contact().selectContact(index);
        app.contact().deleteContact();
        app.goTo().acceptAlert();
        app.goTo().homePage();
        List<ContactData> after = app.contact().contactList();
        Assert.assertEquals(before.size(), after.size() + 1);

        before.remove(index);
        Assert.assertEquals(before, after);


    }
}
