package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase{
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
                    .withCity("Moscow")
                    .withHomephone("333")
                    .withMobilephone("111")
                    .withWorkphone("111")
                    .withEmail("example@yandex.com")
                    .withGroup("Test1"));
        }
        app.goTo().homePage();
    }

    @Test
    public void  testContactPhone(){
        app.goTo().homePage();
        ContactData contact = app.contact().contactAll().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHomephone(), equalTo(contactInfoFromEditForm.getHomephone()));
        assertThat(contact.getMobilephone(), equalTo(contactInfoFromEditForm.getMobilephone()));
        assertThat(contact.getWorkphone(), equalTo(contactInfoFromEditForm.getWorkphone()));

    }
}
