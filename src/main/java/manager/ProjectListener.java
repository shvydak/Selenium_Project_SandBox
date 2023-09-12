package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProjectListener implements WebDriverListener {

    Logger logger = LoggerFactory.getLogger(ProjectListener.class);

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        WebDriverListener.super.onError(target, method, args, e);
        logger.info("ERROR ***--> " + e.getTargetException());
        WebDriver webDriver = (WebDriver) target;
        TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp, new File("src/test/java/tests/screenshots/errorScreenShot.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
 /*
    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElement(driver, locator);
//        logger.info("Before find element --> " + locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        WebDriverListener.super.afterFindElement(driver, locator, result);
//        logger.info("After find element --> " + locator);
//        logger.info("Location of element --> " + result.getLocation());

    }

    @Override
    public void beforeFindElements(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElements(driver, locator);
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        WebDriverListener.super.afterFindElements(driver, locator, result);
    }

     */