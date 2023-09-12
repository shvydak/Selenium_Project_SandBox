package manager;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseHelper {
    public WebDriver webDriver;

    public BaseHelper(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void type(WebElement webElement, String text) {
        waitUntilClickable(webElement);
        if (text != null) {
            webElement.click();
            webElement.clear();
            webElement.sendKeys(text);
        }
    }

    public void typeWOClear(WebElement webElement, String text) {
        waitUntilClickable(webElement);
        webElement.click();
        if (text != null) {
            webElement.sendKeys(text);
        }
    }

    public void moveTo(WebElement webElement) {
        new Actions(webDriver).moveToElement(webElement).perform();
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitUntilClickable(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitUntilVisible(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitUntilVisible(WebElement webElement, int time) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitUntilInVisible(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public void waitUntilElementContainsText(WebElement webElement, String text) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
    }

    public void selectFromDropDownList(WebElement webElement, String text) {
        Select select = new Select(webElement);
        select.selectByValue(text);
    }

    public void typeAndRemove(WebElement webElement, String text) {
        type(webElement, text);
        webElement.clear();
    }

    public void takesScreenshot() {
        TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp, new File("src/test/java/tests/screenshots/userScreenShot.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
