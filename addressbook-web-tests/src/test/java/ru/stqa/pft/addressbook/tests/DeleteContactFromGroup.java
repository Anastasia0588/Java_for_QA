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
        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next();
        Groups beforeGroups = contact.getGroups();
        GroupData groupForAdd = new GroupData();
        if (beforeGroups.size() == 0) {
            groupForAdd = app.db().groups().iterator().next();
            app.contact().addInGroup(contact, groupForAdd);
        } else {
            groupForAdd = beforeGroups.iterator().next();
        }
        app.contact().groupFilter(groupForAdd);
        app.contact().removeContactFromGroup(contact);
        Groups afterGroups = contact.getGroups();
        assertThat(afterGroups, equalTo(beforeGroups.without(groupForAdd)));

    }
}
