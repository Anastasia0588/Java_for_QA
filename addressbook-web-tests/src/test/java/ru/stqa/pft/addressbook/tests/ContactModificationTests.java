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
        if (app.contact().contactList().size() == 0) {
            app.goTo().creation();
            app.contact().create(new ContactData()
                    .withName("CreateNewContact")
                    .withLastName("Ivanov")
                    .withCompany("Intech")
                    .withCity("Moscow")
                    .withPhoneNumber("79201111111")
                    .withEmail("example@yandex.com")
                    .withGroup("Test1"));
            app.goTo().homePage();
        }
    }


    @Test (enabled = false)

    public void ContactModification() {
        List<ContactData> before = app.contact().contactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(before.get(index).getId())
                .withName("Umberto")
                .withLastName("Gonsalez")
                .withCompany("Amigos")
                .withCity("LA")
                .withPhoneNumber("777")
                .withEmail("example@yandex.com");
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
