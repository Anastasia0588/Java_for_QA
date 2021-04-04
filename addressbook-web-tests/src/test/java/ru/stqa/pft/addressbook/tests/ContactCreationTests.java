package ru.stqa.pft.addressbook.tests;

import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToContactCreation();
    ContactData contact = new ContactData("Anna", "Drozdovskaia", "Intech", "NN", "79201111111", "example@yandex.com", "Test1");
    app.getContactHelper().fillContactData(contact, true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size(), after.size() - 1);

    before.add(contact);
    int max = 0;
    for (ContactData c : after){
      if (c.getId() > max){
        max = c.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }
}