package telran_phonebook.tests;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import framework.ApplicationManager;


public class TestBase {

    protected static ApplicationManager app = new ApplicationManager();
    Logger logger = (Logger) LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void startTest(){
        logger.info("Start test");
    }

    @BeforeMethod
    public void setUp(){
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

    @AfterMethod
    public void stopTest(){
        logger.info("Stop test");
    }


}
