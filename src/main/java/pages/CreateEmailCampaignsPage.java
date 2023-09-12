package pages;

import manager.BaseHelper;
import models.EmailCampaign;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateEmailCampaignsPage extends BaseHelper {
    public CreateEmailCampaignsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "(//input[@class='atp-form-text'])[1]")
    WebElement subjectInputField;
    @FindBy(xpath = "//input[@id='s2id_autogen5']")
    WebElement recipientsInputField;
    @FindBy(xpath = "//input[@id='BodyContentPlaceHolder_ctl02_FormContentTab_PreHeader_uxTextBox']")
    WebElement summaryInputField;
    @FindBy(xpath = "(//a[text()='Select template'])[3]")
    WebElement someTemplate;
    @FindBy(xpath = "//span[text()='Create email campaign']")
    WebElement createEmailCampaignTab;
    @FindBy(xpath = "//input[@class='vipBtn Next']")
    WebElement continueSendingButton;
    @FindBy(xpath = "//input[@value='Save and Send a Test Email']")
    WebElement SaveAndSendTestEmailButton;
    @FindBy(xpath = "//input[@class='vipBtn Big']")
    WebElement blastOffButton;
    @FindBy(xpath = "//div[@class='PersonalFieldInsertToTextArea far fa-address-card']")
    WebElement personalAppealButton;
    @FindBy(xpath = "//div[text()='First name']")
    WebElement personalAppealFirstNameSubjectDropDownList;
    @FindBy(xpath = "//div[text()='Last name']")
    WebElement personalAppealLastNameSubjectDropDownList;
    @FindBy(xpath = "//div[text()='User name']")
    WebElement personalAppealUserNameSubjectDropDownList;
    @FindBy(xpath = "//div[@class='select2-search']//input[@class='select2-input']")
    WebElement personalAppealSearchInputField;
    @FindBy(xpath = "//li[text()='Please enter subject']")
    WebElement errorMessageRed;
    @FindBy(xpath = "//span[text()='Yes, close']")
    WebElement yesCloseThisTabButton;
    @FindBy(xpath = "//input[@class='vipBtn']")
    WebElement sendTestEmailSendButton;
    @FindBy(xpath = "//input[@class='vipBtn Grey']")
    WebElement closeSendTestEmailButton;

    public void submitTestEmailSendButton() {
        sendTestEmailSendButton.click();
//        waitUntilClickable(closeSendTestEmailButton);
        pause(5000);
        closeSendTestEmailButton.click();
    }

    public void clickSaveAndSendTestEmailButton() {
        waitUntilClickable(SaveAndSendTestEmailButton);
        SaveAndSendTestEmailButton.click();
    }

    public void confirmClosingTab() {
        waitUntilClickable(yesCloseThisTabButton);
        yesCloseThisTabButton.click();
    }

    public void selectPersonalAppealFromDropList() {
        waitUntilClickable(personalAppealButton);
        personalAppealButton.click();
        personalAppealButton.click();
        waitUntilClickable(personalAppealFirstNameSubjectDropDownList);
        personalAppealFirstNameSubjectDropDownList.click();
    }

    public void fillEmailCampaignForm(EmailCampaign emailCampaign) {
        type(subjectInputField, emailCampaign.getSubject());
        typeIntoDropDownListWithEnterKey(recipientsInputField, emailCampaign.getRecipients());
        type(summaryInputField, emailCampaign.getSummary());
    }

    public void fillEmailCampaignForm(EmailCampaign emailCampaign, String personalAppeal) {
        waitUntilClickable(personalAppealButton);
        personalAppealButton.click();
        personalAppealButton.click();
        if (personalAppeal != null) {
            switch (personalAppeal) {
                case "First name":
                    waitUntilClickable(personalAppealFirstNameSubjectDropDownList);
                    personalAppealFirstNameSubjectDropDownList.click();
                    break;
                case "Last name":
                    waitUntilClickable(personalAppealLastNameSubjectDropDownList);
                    personalAppealLastNameSubjectDropDownList.click();
                    break;
                case "User name":
                    waitUntilClickable(personalAppealUserNameSubjectDropDownList);
                    personalAppealUserNameSubjectDropDownList.click();
                    break;
            }
        }
        pause(2000);
        typeWOClear(subjectInputField, emailCampaign.getSubject());
        typeIntoDropDownListWithEnterKey(recipientsInputField, emailCampaign.getRecipients());
        type(summaryInputField, emailCampaign.getSummary());
    }

    public void selectTemplate() {
        moveTo(someTemplate);
        someTemplate.click();
    }

    public void clickContinueSendingButton() {
        waitUntilClickable(continueSendingButton);
        continueSendingButton.click();
    }


    public void clickBlastOffButton() {
        waitUntilClickable(blastOffButton);
        blastOffButton.click();
    }

    public void typeIntoDropDownListWithEnterKey(WebElement webElement, String text) {
        type(webElement, text);
        pause(3000);
        webElement.sendKeys(Keys.ENTER);
    }

    public boolean errorMessageStringContains(String message) {
        waitUntilVisible(errorMessageRed);
        return errorMessageRed.getText().contains(message);
    }


}
