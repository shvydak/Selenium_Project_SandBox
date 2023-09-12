package pages;

import manager.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MainPage extends BaseHelper {
    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//em[@class='mnu_campaigns_text']")
    WebElement campaignsAndMessaagesButton;
    @FindBy(xpath = "//em[@class='mnu_10_text' and text()='Create email campaign']")
    WebElement createEmailCampaignLink;
    @FindBy(xpath = "//span[@id='Label1']")
    WebElement logOutButton;
    @FindBy(xpath = "(//a[@class='x-tab-strip-close'])[2]")
    WebElement closeTabButton;
    @FindBy(xpath = "//em[text()='Contacts']")
    WebElement contactsHeaderButton;
    @FindBy(xpath = "//em[text()='Add contact']")
    WebElement addContactLink;
    @FindBy(xpath = "//em[text()='My email campaigns']")
    WebElement myEmailCampaignsLink;
    @FindBy(xpath = "//span[@class='x-tab-strip-text tabs']")
    WebElement tabName;
    @FindBy(xpath = "//em[text()='Create SMS campaigns']")
    WebElement createSmsCampaignsLink;
    @FindBy(xpath = "//em[text()='My SMS campaigns']")
    WebElement mySmsCampaignsLink;
    @FindBy(xpath = "//input[@class='vipBtn Save']")
    WebElement saveButton;
    @FindBy(xpath = "//span[text()='Yes, close']")
    WebElement yesCloseThisTabButton;

    public void clickSaveButton() {
        waitUntilClickable(saveButton);
        saveButton.click();
        pause(5000);
    }

    public void clickOnCreateSmsCampaignsLink() {
        selectCampaignsAndMessagesButton();
        waitUntilClickable(createSmsCampaignsLink);
        createSmsCampaignsLink.click();
        moveTo(logOutButton);
    }

    public void clickMySmsCampaignsLink() {
        selectCampaignsAndMessagesButton();
        waitUntilClickable(mySmsCampaignsLink);
        mySmsCampaignsLink.click();
        moveTo(logOutButton);
    }

    public void clickOnMyEmailCampaignsLink() {
        selectCampaignsAndMessagesButton();
        waitUntilClickable(myEmailCampaignsLink);
        myEmailCampaignsLink.click();
        moveTo(logOutButton);
    }

    public void selectContactsButton() {
        waitUntilVisible(contactsHeaderButton);
        moveTo(contactsHeaderButton);
    }

    public void clickOnAddContactLink() {
        selectContactsButton();
        waitUntilClickable(addContactLink);
        addContactLink.click();
        moveTo(logOutButton);
    }

    public void selectCampaignsAndMessagesButton() {
        waitUntilVisible(campaignsAndMessaagesButton);
        moveTo(campaignsAndMessaagesButton);
    }

    public void clickOnCreateEmailCampaignLink() {
        selectCampaignsAndMessagesButton();
        waitUntilClickable(createEmailCampaignLink);
        createEmailCampaignLink.click();
        moveTo(logOutButton);
    }

    public void clickCloseTabButton() {
        waitUntilClickable(closeTabButton);
        closeTabButton.click();
    }

    public void clickCloseTabButtonWithConfirm() {
        waitUntilClickable(closeTabButton);
        closeTabButton.click();
        waitUntilClickable(yesCloseThisTabButton);
        yesCloseThisTabButton.click();
        pause(3000);
    }

    public void headerElementsTest() {
        waitUntilVisible(campaignsAndMessaagesButton);
        Assert.assertTrue(campaignsAndMessaagesButton.isDisplayed());
        Assert.assertTrue(logOutButton.isDisplayed());
    }

    public String returnTabName() {
        waitUntilVisible(tabName);
        return tabName.getText();
    }

    public void confirmClosingTab() {
        waitUntilClickable(yesCloseThisTabButton);
        yesCloseThisTabButton.click();
//        waitUntilInVisible(closeTabButton);
    }

}
