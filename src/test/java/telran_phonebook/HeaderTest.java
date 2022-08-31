package telran_phonebook;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class HeaderTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (isSignOutButtonPresent()) {
            click(By.xpath("//button[contains(.,'Sign Out')]"));
        } else {
           clickOnLoginLink();
        }
        login("monketester13@gmail.com", "1q2W3e4R_");
        sleep(600);
    }


    @Test
    public void checkHeaderElementsAuthorized() {
        Assert.assertTrue(isElementPresent(By.cssSelector("h1")));
        Assert.assertTrue(isElementPresent2(By.xpath("//a[contains(.,'HOME')]")));
        Assert.assertTrue(isElementPresent(By.xpath("//a[contains(.,'ABOUT')]")));
        Assert.assertTrue(isElementPresent(By.xpath("//a[contains(.,'CONTACTS')]")));
        Assert.assertTrue(isElementPresent(By.xpath("//a[contains(.,'ADD')]")));
        Assert.assertTrue(isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));
    }

    @Test
    public void checkHeaderElementsUnauthorized() {
        click(By.xpath("//button[contains(.,'Sign Out')]"));
        Assert.assertTrue(isElementPresent(By.cssSelector("h1")));
        Assert.assertTrue(isElementPresent2(By.xpath("//a[contains(.,'HOME')]")));
        Assert.assertTrue(isElementPresent(By.xpath("//a[contains(.,'ABOUT')]")));
        Assert.assertTrue(isElementPresent(By.xpath("//a[contains(.,'LOGIN')]")));

    }



}
