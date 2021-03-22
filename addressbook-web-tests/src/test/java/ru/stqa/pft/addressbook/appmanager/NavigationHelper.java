package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
       click(By.linkText("groups"));
    }

    public void goToHomePage() {
      click(By.linkText("home"));
    }

    public void goToContactCreation() {
      click(By.linkText("add new"));
    }

    public void acceptAlert(){
        wd.switchTo().alert().accept();
    }

    public void goToEditContact() {
        click(By.xpath("(//img[@alt='Edit'])[2]"));
    }
}
