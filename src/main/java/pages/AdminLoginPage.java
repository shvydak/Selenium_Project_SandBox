package pages;

import manager.BaseHelper;
import models.LoginUser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage extends BaseHelper {
    public AdminLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//input[@title='User name']")
    WebElement loginInputField;
    @FindBy(xpath = "//input[@title='Password']")
    WebElement passwordInputField;
    @FindBy(xpath = "//input[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "//a[text()=' Sign in with Google']")
    WebElement signWithGoogleButton;
    @FindBy(xpath = "//input[@type='email']")
    WebElement emailGoogleInputField;
    @FindBy(xpath = "//span[text()='Next']")
    WebElement nextGoogleButton;
    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordGoogleInputField;

    public void fillLoginForm(LoginUser user) {
        type(loginInputField, user.getEmail());
        type(passwordInputField, user.getPassword());
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public void clickOnSignWithGoogleButton() {
        waitUntilClickable(signWithGoogleButton);
        signWithGoogleButton.click();
    }

    public void fillGoogleLoginForm(LoginUser user) {
        waitUntilClickable(emailGoogleInputField);
        type(emailGoogleInputField, user.getEmail());
        nextGoogleButton.click();
        waitUntilClickable(passwordGoogleInputField);
        type(passwordGoogleInputField, user.getPassword());
        nextGoogleButton.click();
    }

}
