package telran_phonebook.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions()  {
        if (app.getUser().isSignOutButtonPresent()) {
            app.getUser().clickOnSignOutButton();
        } else {
            app.getUser().clickOnLoginLink();
        }
    }

    @Test (priority = 2)
    public void loginPositiveTest(){
        Assert.assertTrue(app.getUser().isRegistrationFormPresent());
        app.getUser().login("monketester13@gmail.com", "1q2W3e4R_");
        app.getUser().pause(600);
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test (priority = 1)
    public void loginNegativeTest(){
        Assert.assertTrue(app.getUser().isRegistrationFormPresent());
        app.getUser().login("monketester13@gmail.com", "1q2W3e4KK_");
        app.getUser().pause(600);
        Assert.assertTrue(app.getUser().isAlertPresent());
        Assert.assertTrue(app.getUser().is500ErrorMessagePresent());
    }


}

