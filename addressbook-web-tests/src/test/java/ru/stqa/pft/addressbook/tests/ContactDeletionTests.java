package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().groupAll().size() == 0){
            app.group().create(new GroupData().withName("DeletedGroupName").withHeader("DeletedGroupHeader").withFooter("GroupForDeletionFooter"));
        }

        app.goTo().homePage();
        if (app.contact().contactAll().size() == 0) {
            app.goTo().creation();
            app.contact().create(new ContactData().withName("CreateNewContact")
                    .withLastName("Ivanov")
                    .withCompany("Intech")
                    .withCity("Moscow")
                    .withPhoneNumber("79201111111")
                    .withEmail("example@yandex.com")
                    .withGroup("Test1"));
            app.goTo().homePage();
        }
    }

    @Test
    public void ContactDeletion() {
        Set<ContactData> before = app.contact().contactAll();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().contactAll();
        Assert.assertEquals(before.size(), after.size() + 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}
