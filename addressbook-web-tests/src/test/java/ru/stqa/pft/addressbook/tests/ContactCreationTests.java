package ru.stqa.pft.addressbook.tests;

import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToContactCreation();
    ContactData contact = new ContactData("Alice", "Drozdovskaia", "Intech", "NN", "79201111111", "example@yandex.com", "Test1");
    app.getContactHelper().fillContactData(contact, true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size(), after.size() - 1);

    /*
    исопльзование цикла для поиска максимального id:
    int max = 0;
    for (ContactData c : after){
      if (c.getId() > max){
        max = c.getId();
      }
    }

    использование компаратора:
    Comparator<? super ContactData> byId = new Comparator<ContactData>() {
      @Override
      public int compare(ContactData o1, ContactData o2) {
        return Integer.compare(o1.getId(), o2.getId());
      }
    };
    */

    //сравнение остортированных списков:
    after.remove(after.size() - 1);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}