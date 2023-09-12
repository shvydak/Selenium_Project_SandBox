package tests;

import manager.ApplicationManager;
import models.LoginUser;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.lang.reflect.Method;

public class BaseTests {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    public static ApplicationManager app = new ApplicationManager(System.getProperty("borwser", Browser.CHROME.browserName()));
    public static String apiKey;
    protected static String mailSlurpApiKey;
    LoginUser user;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {

        /*
        Authentication:
            Save the file in the root of the project and fill in the following data in one line,
        separated by commas (example:user@email.com,password,userPlatformAPIkey,mailSlurpApiKey)
         */

        BufferedReader reader = new BufferedReader(new FileReader(new File(
                "login.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            user = LoginUser.builder()
                    .email(split[0])
                    .password(split[1])
                    .build();
            apiKey = split[2];
            mailSlurpApiKey = split[3];
            line = reader.readLine();
        }
        app.start();
        app.adminLoginPage().clickOnSignWithGoogleButton();
        app.adminLoginPage().fillGoogleLoginForm(user);
        app.mainPage().headerElementsTest();
    }

    @BeforeMethod(alwaysRun = true)
    public void runningTest(Method method) {
        logger.info("'" + method.getName() + "' --> Test started");
    }

    @AfterMethod(alwaysRun = true)
    public void testFinished(Method method) {
//        logger.info("'" + method.getName() + "' --> Test finished");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
        logger.info("*** TEST FINISHED ***");
    }
}
