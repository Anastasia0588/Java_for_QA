package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
      click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectContact(){ click(By.name("selected[]"));}

    public void fillContactData(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getCity());
        type(By.name("mobile"), contactData.getPhoneNumber());
        type(By.name("email"), contactData.getEmail());

        if (isElementPresent(By.name("new group"))) {
            new Select(wd.findElement(By.name("new group"))).selectByVisibleText(ContactData.getGroup());
        }
    }


    public void deleteContact(){
        click(By.xpath("//input[@value='Delete']"));
    }

    public void updateContact(){
        click(By.xpath("(//input[@name='update'])[2]"));
    }

}
