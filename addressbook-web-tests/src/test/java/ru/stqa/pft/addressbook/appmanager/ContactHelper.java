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

    public void editContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void fillContactData(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("work"), contactData.getWorkphone());
        type(By.name("email"), contactData.getEmail());
        attach(By.name("photo"), contactData.getPhoto());

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
        editContactById(contact.getId());
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
            String lastname = e.findElement(By.xpath(".//td[2]")).getText();
            String firstname = e.findElement(By.xpath(".//td[3]")).getText();
            String allPhones = e.findElement(By.xpath(".//td[6]")).getText();
            String allEmails = e.findElement(By.xpath(".//td[5]")).getText();
            String address = e.findElement(By.xpath(".//td[4]")).getText();
            int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute(("id")));
            ContactData contact = new ContactData().withId(id).withName(firstname).withLastName(lastname).
                    withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String company = wd.findElement(By.name("company")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workphone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstname)
                .withLastName(lastname).withCompany(company).withAddress(address)
                .withHomephone(homephone).withMobilephone(mobilephone).withWorkphone(workphone)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    private void initContactModificationById(int id){
        WebElement chekbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = chekbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }
}
