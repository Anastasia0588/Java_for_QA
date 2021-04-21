package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().groupAll().size() == 0){
            app.group().create(new GroupData().withName("DeletedGroupName").withHeader("DeletedGroupHeader").withFooter("GroupForDeletionFooter"));
        }

        app.goTo().homePage();
        if (app.contact().contactAll().size() == 0) {
            app.goTo().creation();
            app.contact().create(new ContactData()
                    .withName("CreateNewContact")
                    .withLastName("Ivanov")
                    .withCompany("Intech")
                    .withAddress("Moscow")
                    .withHomephone("333")
                    .withMobilephone("111")
                    .withWorkphone("111")
                    .withEmail("example@yandex.com")
                    .withGroup("Test1"));
        }
        app.goTo().homePage();
    }

    @Test
    public void  testContactPhone(){
        app.goTo().homePage();
        ContactData contact = app.contact().contactAll().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(cleanedAddress(contactInfoFromEditForm.getAddress())));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
                .stream().filter(s -> ! s.equals(""))
                .map(ContactPhoneTests::cleanedPhones)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter(s -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhones(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    public static String cleanedAddress(String address){
        return address.replaceAll("\n", "");
    }
}
