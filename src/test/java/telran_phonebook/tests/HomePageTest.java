package telran_phonebook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {

    @Test
    public void openHomePage(){
        System.out.println("Home component: " +  app.getHomepage().isHomeComponentPresent2());
        Assert.assertTrue(app.getHomepage().isHomeComponentPresent());
    }

}
