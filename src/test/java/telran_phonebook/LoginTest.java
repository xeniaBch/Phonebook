package telran_phonebook;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions()  {
        if (isSignOutButtonPresent()) {
            click(By.xpath("//button[contains(.,'Sign Out')]"));
        } else {
            clickOnLoginLink();
        }
    }


    @Test
    public void loginPositiveTest(){
        //assert is registration form displayed
        Assert.assertTrue(isElementPresent2(By.cssSelector(".login_login__3EHKB")));
        login("monketester13@gmail.com", "1q2W3e4R_");
        pause(600);
        Assert.assertTrue(isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));
    }

    @Test
    public void loginNegativeTest(){
        //assert is registration form displayed
        Assert.assertTrue(isElementPresent2(By.cssSelector(".login_login__3EHKB")));
        login("monketester@gmail.com", "1q2W3e4R_");
        pause(600);
        Assert.assertTrue(isAlertPresent());
        Assert.assertTrue(isElementPresent2(By.xpath("//div[contains(.,'Login Failed with code 500')]")));
    }

}

