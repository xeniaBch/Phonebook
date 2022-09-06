package telran_phonebook.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CreateAccountTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getUser().isSignOutButtonPresent()) {
            app.getUser().clickOnSignOutButton();
        } else {
            app.getUser().clickOnLoginLink();
        }
    }

    @Test
    public void createAccountPositiveTest(){
        Assert.assertTrue(app.getUser().isRegistrationFormPresent());
        app.getUser().registration("monketester34@gmail.com", "1q2W3e4R_");
        app.getUser().pause(1000);
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void createAccountNegativeTest(){
        Assert.assertTrue(app.getUser().isRegistrationFormPresent());
        app.getUser().registration("monketester34@gmail.com", "1111111");
        app.getUser().pause(1000);
        Assert.assertTrue(app.getUser().isAlertPresent());
        Assert.assertTrue(app.getUser().isErrorMessagePresent());
    }


}
