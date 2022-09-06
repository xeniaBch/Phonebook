package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderHelper extends  HelperBase{

    public HeaderHelper(WebDriver driver) {
        super(driver);
    }

    public boolean isAboutLinkPresent() {
        return isElementPresent(By.xpath("//a[contains(.,'ABOUT')]"));
    }

    public boolean isHomeLinkPresent() {
        return isElementPresent2(By.xpath("//a[contains(.,'HOME')]"));
    }

    public boolean isLogoPresent() {
        return isElementPresent(By.cssSelector("h1"));
    }

    public boolean isContactLinkPresent() {
        return isElementPresent(By.xpath("//a[contains(.,'CONTACTS')]"));
    }

    public boolean isAddLinkPresent() {
        return isElementPresent(By.xpath("//a[contains(.,'ADD')]"));
    }

    public void clickOnAddLink() {
        click(By.cssSelector("a:nth-child(5)"));
    }
}
