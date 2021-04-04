package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().goToContactCreation();
    app.getContactHelper().fillContactData(new ContactData("Anna", "Drozdovskaia", "Intech", "NN", "79201111111", "example@yandex.com", "Test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(before, after - 1);

  }
}