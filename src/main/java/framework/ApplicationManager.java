package framework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class ApplicationManager{

    String browser;
    WebDriver driver;
    UserHelper user;
    ContactHelper contact;
    HeaderHelper header;
    HomePageHelper homepage;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }


    public UserHelper getUser() {
        return user;
    }

    public ContactHelper getContact() {
        return contact;
    }

    public HeaderHelper getHeader() {
        return header;
    }

    public HomePageHelper getHomepage() {
        return homepage;
    }
    public void init() {
        if(browser.equals(BrowserType.CHROME)){
            driver = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        }

        driver.get("https://contacts-app.tobbymarshall815.vercel.app/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        user = new UserHelper(driver);
        contact = new ContactHelper(driver);
        header = new HeaderHelper(driver);
        homepage = new HomePageHelper(driver);
    }

    public void stop() {
        driver.quit();
    }
}
