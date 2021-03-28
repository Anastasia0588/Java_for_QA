package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToContactCreation();
    app.getContactHelper().fillContactData(new ContactData("Anastasia", "Drozdovskaia", "Intech", "NN", "79201111111", "example@yandex.com", "test1"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();

  }

}