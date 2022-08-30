package telran_phonebook;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class HeaderTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (!isElementPresent(By.xpath("//a[contains(.,'LOGIN')]"))) {
            driver.findElement(By.xpath("//button[contains(.,'Sign Out')]")).click();
        } else {
            //click on LOGIN link
            driver.findElement(By.xpath("//a[contains(.,'LOGIN')]")).click();
        }
    }


    public void loginExistedUser() throws InterruptedException {
        //assert is registration form displayed
        Assert.assertTrue(isElementPresent2(By.cssSelector(".login_login__3EHKB")));
        //fill registration form
        driver.findElement(By.cssSelector("[placeholder='Email']")).click();
        driver.findElement(By.cssSelector("[placeholder='Email']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Email']")).sendKeys("monketester23@gmail.com");
        driver.findElement(By.cssSelector("[placeholder='Password']")).click();
        driver.findElement(By.cssSelector("[placeholder='Password']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Password']")).sendKeys("1q2W3e4R_");
        //click on registration button
        driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
        sleep(100);
    }

    @Test
    public void checkHeaderElementsAuthorized() throws InterruptedException {
        loginExistedUser();
        Assert.assertTrue(isElementPresent(By.cssSelector("h1")));
        Assert.assertTrue(isElementPresent2(By.xpath("//a[contains(.,'HOME')]")));
        Assert.assertTrue(isElementPresent(By.xpath("//a[contains(.,'ABOUT')]")));
        Assert.assertTrue(isElementPresent(By.xpath("//a[contains(.,'CONTACTS')]")));
        Assert.assertTrue(isElementPresent(By.xpath("//a[contains(.,'ADD')]")));
        Assert.assertTrue(isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));
        driver.findElement(By.xpath("//button[contains(.,'Sign Out')]")).click();

    }

    @Test
    public void checkHeaderElementsUnauthorized() {
        Assert.assertTrue(isElementPresent(By.cssSelector("h1")));
        Assert.assertTrue(isElementPresent2(By.xpath("//a[contains(.,'HOME')]")));
        Assert.assertTrue(isElementPresent(By.xpath("//a[contains(.,'ABOUT')]")));
        Assert.assertTrue(isElementPresent(By.xpath("//a[contains(.,'LOGIN')]")));

    }



}
