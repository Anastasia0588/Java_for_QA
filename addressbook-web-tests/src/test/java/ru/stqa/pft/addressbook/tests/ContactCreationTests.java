package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.contact().contactList();
    app.goTo().creation();
    ContactData contact = new ContactData()
            .withName("Alice")
            .withLastName("Drozdovskaia")
            .withCompany("Intech")
            .withCity("NN")
            .withPhoneNumber("79201111111")
            .withEmail("example@yandex.com")
            .withGroup("Test1");
    app.contact().fillContact(contact, true);
    app.goTo().homePage();
    List<ContactData> after = app.contact().contactList();
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

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}