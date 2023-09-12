package pages;

import manager.BaseHelper;
import models.SmsCampaign;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SmsCreateCampaignsPage extends BaseHelper {
    public SmsCreateCampaignsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//li[@class='select2-search-field']/input[@type='text']")
    WebElement recipientsInputField;
    @FindBy(xpath = "//textarea")
    WebElement messageContentTextArea;
    @FindBy(xpath = "//div[@class='icheckbox_minimal-blue']")
    WebElement autoFillFieldsCheckBox;
    @FindBy(xpath = "(//div[@class='icheckbox_minimal-blue'])[2]")
    WebElement addUnsubscribeLinkCheckBox;
    @FindBy(xpath = "(//div[@class='icheckbox_minimal-blue'])[3]")
    WebElement trackLinkClickCheckBox;

    public void fillSmsCampaignForm(SmsCampaign user) {
        type(recipientsInputField, user.getRecipients());
        pause(3000);
        recipientsInputField.sendKeys(Keys.ENTER);
        type(messageContentTextArea, user.getMessageContent());
        if (user.isTrackLinkClicks()) {
            trackLinkClickCheckBox.click();
        }
        if (user.isAddUnsubscribeLink()) {
            addUnsubscribeLinkCheckBox.click();
        }
        if (user.isAutoFillFields()) {
            autoFillFieldsCheckBox.click();
        }
    }
}
