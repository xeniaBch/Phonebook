package telran_phonebook;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.*;


public class CreateAccountTest extends TestBase {

    //pre-condition: user must be logged out
    //need to click on the login link
    @BeforeMethod
    public void ensurePreconditions() {
        if (!isElementPresent(By.xpath("//a[contains(.,'LOGIN')]"))) {
            driver.findElement(By.xpath("//button[contains(.,'Sign Out')]")).click();
        } else {
            //click on LOGIN link
            driver.findElement(By.xpath("//a[contains(.,'LOGIN')]")).click();
        }
    }

    //positive test
    @Test
    public void createAccountPositiveTest() throws InterruptedException {
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
        driver.findElement(By.xpath("//button[contains(.,'Registration')]")).click();
        sleep(500);
        Assert.assertTrue(isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));
    }

    //negative test
    @Test
    public void createAccountNegativeTest() throws InterruptedException {
        //assert is registration form displayed
        Assert.assertTrue(isElementPresent2(By.cssSelector(".login_login__3EHKB")));
        //fill registration form
        driver.findElement(By.cssSelector("[placeholder='Email']")).click();
        driver.findElement(By.cssSelector("[placeholder='Email']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Email']")).sendKeys("monketester23@gmail.com");
        driver.findElement(By.cssSelector("[placeholder='Password']")).click();
        driver.findElement(By.cssSelector("[placeholder='Password']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Password']")).sendKeys("111111 ");
        //click on registration button
        driver.findElement(By.xpath("//button[contains(.,'Registration')]")).click();
        sleep(500);
        Assert.assertTrue(isAlertPresent());
        Assert.assertTrue(isElementPresent2(By.xpath("//div[contains(.,'Registration failed with code 400')]")));
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, 20).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert();
            alert.accept();
            return true;
        }
    }
}
