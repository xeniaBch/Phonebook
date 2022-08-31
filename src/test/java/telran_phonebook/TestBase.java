package telran_phonebook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TestBase {
    WebDriver driver;

    //before -setUp()
    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    public boolean isElementPresent(By locator){
        return driver.findElements(locator).size()>0;
    }

    public boolean isElementPresent2(By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void pause(int value) {
        try {
            sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
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

    public void clickWithAction(By locator){
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
        element.click();
    }

    public void fillLoginRegistrationForm(String email, String password) {
        type(By.cssSelector("[placeholder='Email']"), email);
        type(By.cssSelector("[placeholder='Password']"), password);
    }

    public void login(String email, String password) {
        fillLoginRegistrationForm(email, password);
        click(By.xpath("//button[contains(.,'Login')]"));
    }
    public void addContact(String name, String lastname, String phone, String email, String city, String comment) {
        type(By.cssSelector("input:nth-child(1)"), name);
        type(By.cssSelector("input:nth-child(2)"), lastname);
        type(By.cssSelector("input:nth-child(3)"), phone);
        type(By.cssSelector("input:nth-child(4)"), email);
        type(By.cssSelector("input:nth-child(5)"), city);
        type(By.cssSelector("input:nth-child(6)"), comment);
        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
    }

    public void clickOnLoginLink() {
        click(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//a[contains(.,'Sign Out')]"));
    }
    public void deleteContact() {
        if(!isContactListEmpty()) {
            click(By.cssSelector(".contact-item_card__2SOIM"));
            click(By.xpath("//button[text()='Remove']"));
        }
    }
    public int sizeofContacts() {
        if(driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size() > 0) {
            return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        } else { return 0;
        }

    }

    public boolean isContactListEmpty() {
        return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
    }

    public boolean isContactCreated(String text) {
        List<WebElement> contacts = driver.findElements(By.xpath("//h2"));
        for (WebElement el : contacts) {
            if( el.getText().contains(text)) return true;
        }
        return false;
    }

    public void registration(String email, String password) {
        fillLoginRegistrationForm(email, password);
        click(By.xpath("//button[contains(.,'Registration')]"));
    }
        @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
