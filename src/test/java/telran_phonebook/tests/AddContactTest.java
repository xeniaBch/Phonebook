package telran_phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import model.Contact;
import utils.DataProviders;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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

    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProvider(String name, String surname, String phone, String email, String address, String comment) {
        app.getContact().addContact(new Contact()
                .setName(name)
                .setLastname(surname)
                .setPhone(phone)
                .setEmail(email)
                .setCity(address)
                .setComment(comment));
        app.getContact().deleteContact();
    }


    @Test(dataProvider = "newContactWithCsv",  dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProviderWithCsv(Contact contact) {
        app.getContact().addContact(contact);
        logger.info(String.valueOf(app.getContact().isContactCreated(contact.getName())));
        app.getUser().pause(600);
        app.getContact().deleteContact();


    }

    @AfterMethod
    public void removeContactPositiveTest(){
        app.getContact().deleteContact();
    }
}
