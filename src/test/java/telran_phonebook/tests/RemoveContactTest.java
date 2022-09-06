package telran_phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import model.Contact;

public class RemoveContactTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getUser().isSignOutButtonPresent()) {
            app.getUser().clickOnLoginLink();
            app.getUser().login("monketester13@gmail.com", "1q2W3e4R_");
            app.getUser().pause(600);
            app.getHeader().clickOnAddLink();
        }
        app.getContact().addContact(new Contact()
                .setName("John")
                .setLastname("Smith")
                .setPhone("017300000")
                .setEmail("johnsmith@test.com")
                .setCity("Krakow")
                .setComment("good person"));
    }

    @Test
    public void removeContactPositiveTest(){
        int sizeBefore = app.getContact().sizeofContacts();
        app.getContact().deleteContact();
        app.getUser().pause(500);
        int sizeAfter =  app.getContact().sizeofContacts();
        Assert.assertEquals(sizeAfter+1, sizeBefore);
    }
}
