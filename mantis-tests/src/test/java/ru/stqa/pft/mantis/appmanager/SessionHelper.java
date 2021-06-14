package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {
        super(app);
    }
    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "login_page.php");
        type(By.name("username"),username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public void usersPage() {
        wd.get(app.getProperty("web.baseUrl") + "manage_user_page.php");
    }

    public void selectUser(int userId) {
        wd.get(app.getProperty("web.baseUrl") + String.format("manage_user_edit_page.php?user_id=%s",userId));
    }

    public void resetPassword(){
        click(By.xpath("//form[@id = 'manage-user-reset-form']//input[@type='submit']"));
    }

    public void updatePasswors(String confirmationLink, String password){
        setNewPassword(confirmationLink, password);
        click(By.cssSelector("button[type = 'submit']"));
    }

    public void setNewPassword(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
    }
}
