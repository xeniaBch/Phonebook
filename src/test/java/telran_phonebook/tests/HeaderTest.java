package telran_phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class HeaderTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (app.getUser().isSignOutButtonPresent()) {
           app.getUser().clickOnSignOutButton();
        } else {
           app.getUser().clickOnLoginLink();
        }
        app.getUser().login("monketester13@gmail.com", "1q2W3e4R_");
        sleep(600);
    }

    @Test
    public void checkHeaderElementsAuthorized() {
        Assert.assertTrue(app.getHeader().isLogoPresent());
        Assert.assertTrue(app.getHeader().isHomeLinkPresent());
        Assert.assertTrue(app.getHeader().isAboutLinkPresent());
        Assert.assertTrue(app.getHeader().isContactLinkPresent());
        Assert.assertTrue(app.getHeader().isAddLinkPresent());
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void checkHeaderElementsUnauthorized() {
        app.getUser().clickOnSignOutButton();
        Assert.assertTrue(app.getHeader().isLogoPresent());
        Assert.assertTrue(app.getHeader().isHomeLinkPresent());
        Assert.assertTrue(app.getHeader().isAboutLinkPresent());
        Assert.assertTrue(app.getUser().isLoginLinkPresent());

    }
}
