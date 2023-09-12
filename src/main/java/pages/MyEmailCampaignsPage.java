package pages;

import manager.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyEmailCampaignsPage extends BaseHelper {
    public MyEmailCampaignsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "(//td[@class='MailSubject'])[1]")
    WebElement firstStringSubject;
    @FindBy(xpath = "//div[@class='icheckbox_minimal-blue']")
    WebElement selectAllCheckBox;
    @FindBy(xpath = "//a[text()='Delete Checked']")
    WebElement deleteCheckedButton;
    @FindBy(xpath = "(//div[@class='icheckbox_minimal-blue'])[2]")
    WebElement firstCampaignCheckbox;
    @FindBy(xpath = "//td[@class='uxDelete']")
    WebElement firstEmailCampaignDeleteIconButton;
    @FindBy(xpath = "//input[@value='Yes']")
    WebElement yesConfirmationButton;
    @FindBy(xpath = "//input[@value='No']")
    WebElement noConfirmationButton;

    public void deleteAllCampaigns() {
        waitUntilClickable(selectAllCheckBox);
        selectAllCheckBox.click();
        pause(1000);
        deleteCheckedButton.click();
        waitUntilClickable(yesConfirmationButton);
        yesConfirmationButton.click();
    }

    public void deleteFirstEmailCampaignWithCampaignIconDeleteButton() {
        waitUntilClickable(firstEmailCampaignDeleteIconButton);
        firstEmailCampaignDeleteIconButton.click();
        waitUntilClickable(yesConfirmationButton);
        yesConfirmationButton.click();
        pause(3000);
    }
    public void cancelDeleteFirstEmailCampaignWithCampaignIconDeleteButton() {
        waitUntilClickable(firstEmailCampaignDeleteIconButton);
        firstEmailCampaignDeleteIconButton.click();
        waitUntilClickable(noConfirmationButton);
        noConfirmationButton.click();
        pause(3000);
    }
    public void deleteFirstEmailCampaignWithCampaignHeaderDeleteButton() {
        waitUntilClickable(firstCampaignCheckbox);
        firstCampaignCheckbox.click();
        deleteCheckedButton.click();
        waitUntilClickable(yesConfirmationButton);
        yesConfirmationButton.click();
        pause(3000);
    }

    public String savedName() {
        waitUntilVisible(firstStringSubject);
        return firstStringSubject.getText();
    }
}
