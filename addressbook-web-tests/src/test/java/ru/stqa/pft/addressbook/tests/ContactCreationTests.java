package ru.stqa.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTests
{
  private WebDriver dw;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    dw = new FirefoxDriver();
    dw.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    dw.get("https://localhost/addressbook/");
    login("admin", "secret");
  }

  private void login(String login, String password) {
    dw.findElement(By.name("user")).click();
    dw.findElement(By.name("user")).clear();
    dw.findElement(By.name("user")).sendKeys(login);
    dw.findElement(By.name("pass")).clear();
    dw.findElement(By.name("pass")).sendKeys(password);
    dw.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testContactCreation() throws Exception {
    goToContactCreation();
    fillContactData(new ContactData("Anastasia", "Drozdovskaia", "Intech", "Nizhny Novgorod", "+79201111111", "example@yandex.ru"));
    submitContactCreation();
    returnToHomePage();

  }

  private void returnToHomePage() {
    dw.findElement(By.linkText("home")).click();
  }

  private void submitContactCreation() {
    dw.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  private void fillContactData(ContactData contactData) {
    dw.findElement(By.name("firstname")).click();
    dw.findElement(By.name("firstname")).clear();
    dw.findElement(By.name("firstname")).sendKeys(contactData.getName());
    dw.findElement(By.name("lastname")).click();
    dw.findElement(By.name("lastname")).clear();
    dw.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
    dw.findElement(By.name("company")).click();
    dw.findElement(By.name("company")).clear();
    dw.findElement(By.name("company")).sendKeys(contactData.getCompany());
    dw.findElement(By.name("address")).click();
    dw.findElement(By.name("address")).clear();
    dw.findElement(By.name("address")).sendKeys(contactData.getCity());
    dw.findElement(By.name("mobile")).click();
    dw.findElement(By.name("mobile")).clear();
    dw.findElement(By.name("mobile")).sendKeys(contactData.getPhoneNumber());
    dw.findElement(By.name("email")).click();
    dw.findElement(By.name("email")).clear();
    dw.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }

  private void goToContactCreation() {
    dw.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    dw.findElement(By.linkText("Logout")).click();
    dw.quit();
  }



}
