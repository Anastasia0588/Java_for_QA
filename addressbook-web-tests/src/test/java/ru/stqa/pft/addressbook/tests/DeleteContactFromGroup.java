package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactFromGroup extends TestBase{
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
    public void testDeleteContactFromGroup() {
        ContactData contact = selectContact();
        GroupData groupForRemoved = selectGroup(contact);
        Groups before = contact.getGroups();
        app.goTo().homePage();
        app.contact().selectGroup(groupForRemoved.getId());
        app.contact().removeContactFromGroup(contact);

        ContactData contactsAfter = selectContactById(contact);
        Groups after = contactsAfter.getGroups();
        assertThat(after, equalTo(before.without(groupForRemoved)));
    }

    private ContactData selectContactById(ContactData contact) {
        Contacts contactsById = app.db().contacts();
        return contactsById.iterator().next().withId(contact.getId());
    }

    private GroupData selectGroup(ContactData removeContact) {
        ContactData contact = selectContactById(removeContact);
        Groups removedContact = contact.getGroups();
        return removedContact.iterator().next();

    }

    private ContactData selectContact() {
        Contacts allContacts = app.db().contacts();
        for (ContactData contact : allContacts) {
            if (contact.getGroups().size() > 0) {
                return contact;
            }
        }
        ContactData addContact = app.db().contacts().iterator().next();
        app.contact().addInGroup(addContact, app.db().groups().iterator().next());
        return addContact;
    }
}
