package telran_phonebook;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (!isSignOutButtonPresent()) {
            clickOnLoginLink();
            login("monketester13@gmail.com", "1q2W3e4R_");
            pause(600);
            click(By.cssSelector("a:nth-child(5)"));
        }
        addContact("John","Smith", "017300000", "johnsmith@test.com", "Krakow", "good person");
    }

    @Test
    public void removeContactPositiveTest(){
        int sizeBefore = sizeofContacts();
        deleteContact();
        pause(500);
        int sizeAfter =  sizeofContacts();
        Assert.assertEquals(sizeAfter+1, sizeBefore);
    }
}
