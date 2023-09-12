package pages;

import manager.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TwoStepVerificationPage extends BaseHelper {
    public TwoStepVerificationPage(WebDriver webDriver) {
        super(webDriver);
    }
////////////    // postponed //    ///////////
    @FindBy(xpath = "//input[@placeholder='Code (6 digits)']")
    WebElement inputVerivicationCode;

    public void clickInputVerivicationCode() {
        inputVerivicationCode.click();
        // TODO: 05/08/2023
    }
}
