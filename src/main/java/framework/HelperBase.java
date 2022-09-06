package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static java.lang.Thread.sleep;

public class HelperBase {
    WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
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

    public void clickWithAction(By locator){
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
        element.click();
    }
}
