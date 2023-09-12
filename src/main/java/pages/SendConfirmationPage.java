package pages;

import manager.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SendConfirmationPage extends BaseHelper {
    public SendConfirmationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//h1[text()='Your campaign was sent']")
    WebElement messageYourCampaignWasSent;

    public boolean isMessageYourCampaignWasSentAppeared() {
        waitUntilVisible(messageYourCampaignWasSent, 20);
        return messageYourCampaignWasSent.isDisplayed();
    }

}
