package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws MessagingException, IOException {
        long now = System.currentTimeMillis();
        String adminLogin = "Administrator";
        String adminPassword = "root";
        String newPassword = "newPassword" + now;

        app.sessionHelper().login(adminLogin, adminPassword);
        app.sessionHelper().usersPage();

        Users users = app.db().users();
        UserData selectUser = users.iterator().next();
        String emailFromDb = selectUser.getEmail();
        String usernameFromDb = selectUser.getUsername();

        app.sessionHelper().selectUser(Integer.valueOf(selectUser.getId()));
        app.sessionHelper().resetPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, emailFromDb);
        app.sessionHelper().updatePasswors(confirmationLink, newPassword);
        assertTrue(app.newSession().login(usernameFromDb, newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get(); //найти среди всех писем то, кот отправлено на нужный адрес. В рез-те фильтрации останутся только те объекты, кот. пришли по нужному адресу
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build(); //построение регулярного выражения
        return regex.getText(mailMessage.text); //возвращает тот кусок текста, кот соответвтует построенному регулярному выражению
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

}
