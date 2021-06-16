package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager();

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
    }

    public boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException {
        if (app.rest().getIssuesStatus(issueId) == "Closed") {
            return false;
        }
        return true;
    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}