package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
      click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectContact(){ click(By.name("selected[]"));}

    public void fillContactData(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getCity());
        type(By.name("mobile"), contactData.getPhoneNumber());
        type(By.name("email"), contactData.getEmail());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            } else {
            Assert.assertFalse(isElementPresentMy(By.name("new_group")));
            }

    }

    public void deleteContact(){
        click(By.xpath("//input[@value='Delete']"));
    }

    public void updateContact(){
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void createContact(ContactData contact) {
        fillContactData(contact, true);
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresentMy(By.name("selected[]"));
    }
}
