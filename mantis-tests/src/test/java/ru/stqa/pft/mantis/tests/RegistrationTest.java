package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTest extends TestBase{
    //@BeforeMethod т.к. собираемся использовать отдельностоящий почтовый сервер
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String email = String.format("user%s@localhost", now);
        String password = "password";
        app.james().createUser(user, password);
        app.registration().start(user, email);
        //List<MailMessage> mailMessages = app.mail().waitForMail(2,10000);
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 100000);//на внешний почтовый сервер почта идет еще дольше
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get(); //найти среди всех писем то, кот отправлено на нужный адрес. В рез-те фильтрации останутся только те объекты, кот. пришли по нужному адресу
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build(); //построение регулярного выражения
        return regex.getText(mailMessage.text); //возвращает тот кусок текста, кот соответвтует построенному регулярному выражению
    }

    //@AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
