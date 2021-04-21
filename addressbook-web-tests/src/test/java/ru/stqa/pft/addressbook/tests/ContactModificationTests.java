package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().groupAll().size() == 0){
            app.group().create(new GroupData().withName("DeletedGroupName").withHeader("DeletedGroupHeader").withFooter("GroupForDeletionFooter"));
        }

        app.goTo().homePage();
        if (app.contact().contactAll().size() == 0) {
            app.goTo().creation();
            app.contact().create(new ContactData()
                    .withName("CreateNewContact")
                    .withLastName("Ivanov")
                    .withCompany("Intech")
                    .withAddress("Moscow")
                    .withMobilephone("79201111111")
                    .withEmail("example@yandex.com")
                    .withGroup("Test1"));
                 }
        app.goTo().homePage();
    }

    @Test
    public void ContactModification() {
        Contacts before = app.contact().contactAll();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withName("Umberto")
                .withLastName("Gonsalez")
                .withCompany("Amigos")
                .withAddress("LA")
                .withMobilephone("777")
                .withEmail("example@yandex.com");
        app.contact().modify(contact);
        app.goTo().homePage();
        assertEquals(before.size(), app.contact().count());
        Contacts after = app.contact().contactAll();
        assertThat(after,
                equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
