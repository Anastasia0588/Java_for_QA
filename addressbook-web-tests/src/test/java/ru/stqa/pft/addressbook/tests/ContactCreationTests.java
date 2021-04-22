package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().groupAll().size() == 0) {
      app.group().create(new GroupData().withName("DeletedGroupName").withHeader("DeletedGroupHeader").withFooter("GroupForDeletionFooter"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().contactAll();
    app.goTo().creation();
    ContactData contact = new ContactData()
            .withName("CreateNewContact")
            .withLastName("Ivanov")
            .withCompany("Intech")
            .withAddress("Moscow")
            .withHomephone("333")
            .withMobilephone("111")
            .withWorkphone("111")
            .withEmail("example@yandex.com")
            .withGroup("Test1");

    app.contact().create(contact);
    app.goTo().homePage();
    assertThat(before.size(), equalTo(app.contact().count() - 1));
    Contacts after = app.contact().contactAll();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }
}