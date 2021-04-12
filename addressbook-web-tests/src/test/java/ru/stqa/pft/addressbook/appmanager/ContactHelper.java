package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void editContactById() {
        wd.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();;
    }

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

    public void submit() {
        click(By.xpath("(//input[@name='submit'])"));
    }

    public void update(){
        click(By.xpath("//input[@value='Update']"));
    }

    public void deleteContact(){
        click(By.xpath("//input[@value='Delete']"));
    }


    public void acceptAlert(){
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        fillContactData(contact, true);
        submit();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        contactCache = null;
        acceptAlert();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        editContactById();
        fillContactData(contact, false);
        update();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresentMy(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts contactAll() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement e : elements){
            String firstname = e.findElement(By.xpath(".//td[3]")).getText();
            String lastname = e.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute(("id")));
            ContactData contact = new ContactData().withId(id).withName(firstname).withLastName(lastname);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }
}
