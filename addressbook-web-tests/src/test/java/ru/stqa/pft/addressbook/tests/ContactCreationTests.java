package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.Set;

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
    Set<ContactData> before = app.contact().contactAll();
    app.goTo().creation();
    ContactData contact = new ContactData()
            .withName("Alice")
            .withLastName("Drozdovskaia")
            .withCompany("Intech")
            .withCity("NN")
            .withPhoneNumber("79201111111")
            .withEmail("example@yandex.com")
            .withGroup("Test1");
    app.contact().create(contact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().contactAll();
    Assert.assertEquals(before.size(), after.size() - 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }
}