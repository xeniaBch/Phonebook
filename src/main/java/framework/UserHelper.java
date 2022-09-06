package framework;

import model.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserHelper extends HelperBase{

    public UserHelper(WebDriver driver) {
        super(driver);
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

    public void fillLoginRegistrationForm(User user) {
        type(By.cssSelector("[placeholder='Email']"), user.getEmail());
        type(By.cssSelector("[placeholder='Password']"), user.getPassword());
    }

    public void login(String email, String password) {
        fillLoginRegistrationForm(new User().setEmail(email).setPassword(password));
        click(By.xpath("//button[contains(.,'Login')]"));
    }

    public void clickOnLoginLink() {
        click(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public void registration(String email, String password) {
        fillLoginRegistrationForm(new User().setEmail(email).setPassword(password));
        click(By.xpath("//button[contains(.,'Registration')]"));
    }

    public void clickOnSignOutButton() {
        click(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public boolean isRegistrationFormPresent() {
        return isElementPresent2(By.cssSelector(".login_login__3EHKB"));
    }

    public boolean isLoginLinkPresent() {
        return isElementPresent(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public boolean isErrorMessagePresent() {
        return isElementPresent2(By.xpath("//div[contains(.,'Registration failed with code 400')]"));
    }

    public boolean is500ErrorMessagePresent() {
        return isElementPresent2(By.xpath("//div[contains(.,'Login Failed with code 500')]"));
    }
}
