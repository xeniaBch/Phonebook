package telran_phonebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AddContactTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!isSignOutButtonPresent()) {
            clickOnLoginLink();
            login("monketester13@gmail.com", "1q2W3e4R_");
            pause(600);
            click(By.cssSelector("a:nth-child(5)"));
        }
    }

    @Test
    public void addContactPositiveTest() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        addContact("John"+i,"Smith", "017300000"+i, "johnsmith"+i+"@test.com", "Krakow", "good person");
        Assert.assertTrue(isContactCreated("John"+i));
    }

    @AfterMethod
    public void removeContactPositiveTest(){
        deleteContact();
    }
}
