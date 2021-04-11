package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresentMy(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresentMy(By.name("new"))){
        return;
        }
       click(By.linkText("groups"));
    }

    public void homePage() {
        if (isElementPresentMy(By.id("maintable"))){
            return;
        }
      click(By.linkText("home"));
    }

    public void creation() {
      click(By.linkText("add new"));
    }

}
