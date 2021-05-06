package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddContactIntoGroup extends TestBase{

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
                    .withEmail("example@yandex.com"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddContactIntoGroup(){
        app.goTo().homePage();
        ContactData addContact = selectContact();
        GroupData groupForAdd = selectGroup(addContact);
        Groups before = addContact.getGroups();
        app.goTo().homePage();
        app.contact().addInGroup(addContact, groupForAdd);
        app.goTo().homePage();
        ContactData addContactAfter = selectContactById(addContact);
        Groups after = addContactAfter.getGroups();
        assertThat(after, equalTo(before.withAdded(groupForAdd)));
    }

    private ContactData selectContactById(ContactData addContact) {
        Contacts contactsById = app.db().contacts();
        return contactsById.iterator().next().withId(addContact.getId());

    }

    private GroupData selectGroup(ContactData contact) {
        Groups allGroups = app.db().groups();
        Groups contactsInGroups = contact.getGroups();
        Collection<GroupData> contactGroups = new HashSet<>(contactsInGroups);
        Collection<GroupData> avaliableGroups = new HashSet<>(allGroups);
        avaliableGroups.removeAll(contactGroups);
        return avaliableGroups.iterator().next();
    }


    private ContactData selectContact() {

        Contacts allContacts = app.db().contacts();
        Groups allGroups = app.db().groups();
        for (ContactData contact : allContacts) {
            if (contact.getGroups().size() < allGroups.size()) {
                return contact;
            }
        }
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("ForAddContact").withHeader("Header"));
        return allContacts.iterator().next();
    }
}
