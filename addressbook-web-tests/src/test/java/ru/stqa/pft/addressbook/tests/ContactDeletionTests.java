package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("DeletedGroupName").withHeader("DeletedGroupHeader").withFooter("GroupForDeletionFooter"));
        }
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.goTo().creation();
            app.contact().create(new ContactData().withName("CreateNewContact")
                    .withLastName("Ivanov")
                    .withCompany("Intech")
                    .withAddress("Moscow")
                    .withMobilephone("79201111111")
                    .withEmail("example@yandex.com")
             //       .withGroup("Test1")
            );
            app.goTo().homePage();
        }
    }

    @Test
    public void ContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        assertEquals(before.size(), app.contact().count() + 1);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListInUI();
    }
}
