package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddContactIntoGroup extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.goTo().creation();
            app.contact().create(new ContactData().withName("CreateNewContact")
                            .withLastName("Ivanov")
                            .withCompany("Intech")
                            .withAddress("Moscow")
                            .withMobilephone("79201111111")
                            .withEmail("example@yandex.com")

            );
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddContactIntoGroup(){
        Contacts before = app.db().contacts();
        app.goTo().groupPage();
        GroupData groupForAdd = new GroupData().withName("ForAddContact");
        app.group().create(groupForAdd);
        String nameGroup = groupForAdd.getName();
        app.goTo().homePage();
        ContactData addedContact = before.iterator().next();
        Groups beforeGroups = addedContact.getGroups();
        int idContact = addedContact.getId();
        app.contact().addInGroup(addedContact, groupForAdd);

        ContactData afterContact = selectContactById(idContact);
        Groups afterGroups = afterContact.getGroups();
        assertThat(afterGroups, equalTo(beforeGroups.withAdded(groupForAdd)));

        app.goTo().groupPage();
        GroupData deletedGroup = selectGroupByName(nameGroup);
        app.group().delete(deletedGroup);

    }

    private ContactData selectContactById(int id) {
        Contacts contactsById = app.db().contacts();
        return contactsById.iterator().next().withId(id);
    }

    private GroupData selectGroupByName(String name) {
        Groups groupsById = app.db().groups();
        return groupsById.iterator().next().withName(name);
    }
}
