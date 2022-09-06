package telran_phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import model.Contact;


public class AddContactTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getUser().isSignOutButtonPresent()) {
            app.getUser().clickOnLoginLink();
            app.getUser().login("monketester13@gmail.com", "1q2W3e4R_");
            app.getUser().pause(600);
            app.getHeader().clickOnAddLink();
        }
    }

    @Test
    public void addContactPositiveTest() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        app.getContact().addContact(new Contact()
                .setName("John")
                .setLastname("Smith" + i)
                .setPhone("017300000" + i)
                .setEmail("johnsmith" + i + "@test.com")
                .setCity("Krakow")
                .setComment("good person"));
        app.getContact().pause(600);
        Assert.assertTrue(app.getContact().isContactCreated("John"));
    }

    @AfterMethod
    public void removeContactPositiveTest(){
        app.getContact().deleteContact();
    }
}
