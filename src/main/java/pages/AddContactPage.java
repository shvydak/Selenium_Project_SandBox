package pages;

import manager.BaseHelper;
import models.Contact;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddContactPage extends BaseHelper {
    public AddContactPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "(//input[@class='form-control'])[1]")
    WebElement firstNameInput;
    @FindBy(xpath = "(//input[@class='form-control'])[2]")
    WebElement lastNameInput;
    @FindBy(xpath = "(//input[@class='form-control'])[3]")
    WebElement emailInput;
    @FindBy(xpath = "(//input[@class='form-control'])[4]")
    WebElement phoneInput;
    @FindBy(xpath = "(//input[@class='form-control'])[5]")
    WebElement mobileInput;
    @FindBy(xpath = "(//input[@class='form-control'])[6]")
    WebElement addressInput;
    @FindBy(xpath = "(//input[@class='form-control'])[7]")
    WebElement cityInput;
    @FindBy(xpath = "(//input[@class='form-control'])[8]")
    WebElement countryInput;
    @FindBy(xpath = "(//input[@class='form-control'])[9]")
    WebElement companyInput;
    @FindBy(xpath = "(//input[@class='form-control'])[10]")
    WebElement positionInput;
    @FindBy(xpath = "(//input[@class='form-control'])[11]")
    WebElement lastRegistrationField;
    @FindBy(xpath = "(//input[@class='form-control'])[12]")
    WebElement birthday;
    @FindBy(xpath = "//div[text()='Type the list name']")
    WebElement listNameInput;
    @FindBy(xpath = "react-select-2-input")
    WebElement listMenuInput;
    @FindBy(xpath = "//button[@type='button' and text()='Save']")
    WebElement saveButton;
    @FindBy(xpath = "//div[@class='content____VgcN']")
    WebElement errorMessageRed;


    public void fillAddContactForm(Contact user) {
        type(firstNameInput, user.getFirstName());
        type(lastNameInput, user.getLastName());
        type(emailInput, user.getEmail());
        type(phoneInput, user.getPhone());
        type(mobileInput, user.getMobile());
        type(addressInput, user.getAddress());
        type(cityInput, user.getCity());
        type(countryInput, user.getCountry());
        type(companyInput, user.getCompany());
        type(positionInput, user.getPosition());
        birthday.click();
        type(birthday, user.getBirthday());
        lastNameInput.click();

        if (user.getListName() != null) {
            moveTo(listNameInput);
            listNameInput.click();
            listNameInput.click();
            listNameInput.sendKeys(user.getListName());
        }
    }

    public void fillAndRemoveAddContactForm(Contact user) {
        typeAndRemove(firstNameInput, user.getFirstName());
        type(lastNameInput, user.getLastName());
        typeAndRemove(emailInput, user.getEmail());
        type(phoneInput, user.getPhone());
        typeAndRemove(mobileInput, user.getMobile());
        type(addressInput, user.getAddress());
        type(cityInput, user.getCity());
        type(countryInput, user.getCountry());
        type(companyInput, user.getCompany());
        type(positionInput, user.getPosition());
        birthday.click();
        type(birthday, user.getBirthday());
        lastNameInput.click();

        if (user.getListName() != null) {
            moveTo(listNameInput);
            listNameInput.click();
            listNameInput.click();
            listNameInput.sendKeys(user.getListName());
        }
    }

    public void clickSaveButton() {
        waitUntilClickable(saveButton);
        saveButton.click();
        pause(5000);
    }

    public void clickSaveButtonNegative() {
        waitUntilClickable(saveButton);
        saveButton.click();
        pause(5000);
    }

    public boolean isSaveContactButtonDisabled() {
        waitUntilVisible(saveButton);
        return saveButton.isEnabled();
    }

    public boolean errorMessageStringContainsText(String message) {
        waitUntilVisible(errorMessageRed);
        return errorMessageRed.getText().contains(message);
    }

    public boolean errorMessageStringContainsGetProperty(String message) {
        waitUntilVisible(errorMessageRed);
        return errorMessageRed.getDomProperty("innerHTML").contains(message);
    }


}
