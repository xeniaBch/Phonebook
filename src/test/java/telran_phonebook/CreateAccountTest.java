package telran_phonebook;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CreateAccountTest extends TestBase {

    //pre-condition: user must be logged out
    //need to click on the login link
    @BeforeMethod
    public void ensurePreconditions() {
        if (isSignOutButtonPresent()) {
            click(By.xpath("//button[contains(.,'Sign Out')]"));
        } else {
            clickOnLoginLink();
        }
    }

    //positive test
    @Test
    public void createAccountPositiveTest(){
        //assert is registration form displayed
        Assert.assertTrue(isElementPresent2(By.cssSelector(".login_login__3EHKB")));
        registration("monketester33@gmail.com", "1q2W3e4R_");
        pause(1000);
        Assert.assertTrue(isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));
    }

    //negative test
    @Test
    public void createAccountNegativeTest(){
        //assert is registration form displayed
        Assert.assertTrue(isElementPresent2(By.cssSelector(".login_login__3EHKB")));
        registration("monketester33@gmail.com", "1111111");
        pause(500);
        Assert.assertTrue(isAlertPresent());
        Assert.assertTrue(isElementPresent2(By.xpath("//div[contains(.,'Registration failed with code 400')]")));
    }


}
