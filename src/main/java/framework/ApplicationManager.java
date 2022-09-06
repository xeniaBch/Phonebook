package framework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class ApplicationManager{
    WebDriver driver;
    UserHelper user;
    ContactHelper contact;
    HeaderHelper header;
    HomaPageHelper homepage;


    public UserHelper getUser() {
        return user;
    }

    public ContactHelper getContact() {
        return contact;
    }

    public HeaderHelper getHeader() {
        return header;
    }

    public HomaPageHelper getHomepage() {
        return homepage;
    }
    public void init() {
        driver = new ChromeDriver();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        user = new UserHelper(driver);
        contact = new ContactHelper(driver);
        header = new HeaderHelper(driver);
        homepage = new HomaPageHelper(driver);
    }

    public void stop() {
        driver.quit();
    }
}
