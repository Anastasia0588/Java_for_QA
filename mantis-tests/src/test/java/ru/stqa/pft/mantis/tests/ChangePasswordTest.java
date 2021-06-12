package ru.stqa.pft.mantis.tests;

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

    @Test
    public void testChangePassword() throws MessagingException, IOException {
        String adminLogin = "Administrator";
        String adminPassword = "root";
        String adminEmail = "root@localhost";
        String newPassword = "newPassword";
        app.sessionHelper().login(adminLogin, adminPassword);
        app.sessionHelper().usersList();
        Users userList = app.db().users();
        UserData selectUser = userList.iterator().next();
        String emailFromDb = selectUser.getEmail();
        String usernameFromDb = selectUser.getUsername();
        app.sessionHelper().selectUser(Integer.valueOf(selectUser.getId()));
        app.sessionHelper().resetPassword();
        List<MailMessage> mailMessages = app.james().waitForMail(adminLogin, adminPassword, 60000);
        String confirmationLink = app.sessionHelper().findConfirmationLink(mailMessages, adminEmail);
        app.sessionHelper().setNewPassword(confirmationLink, newPassword);
        assertTrue(app.newSession().login(usernameFromDb, newPassword));
    }
}
