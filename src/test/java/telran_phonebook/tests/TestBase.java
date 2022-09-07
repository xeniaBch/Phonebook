package telran_phonebook.tests;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import framework.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;


public class TestBase {

    protected static ApplicationManager app = new ApplicationManager();
    Logger logger = (Logger) LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void startTest(Method m, Object[] o){
        logger.info("Start test with method " + m.getName() + " with data: " + Arrays.asList(o));

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
    public void stopTest(ITestResult result){
        if(result.isSuccess()){
        logger.info("PASSED " + result.getMethod().getMethodName());
        }
        else {
            logger.info("FAILED " + result.getMethod().getMethodName() + "screenshot path is: " + app.getUser().takeScreenshot());
        }

        logger.info("______________________________________");
    }


}
