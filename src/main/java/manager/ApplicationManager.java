package manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import pages.*;

import java.io.*;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class ApplicationManager {
    private final String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    WebDriver webDriver;
    AdminLoginPage adminLoginPage;
    TwoStepVerificationPage twoStepVerificationPage;
    MainPage mainPage;
    CreateEmailCampaignsPage createEmailCampaignsPage;
    SendConfirmationPage sendConfirmationPage;
    AddContactPage addContactPage;
    MyEmailCampaignsPage myEmailCampaignsPage;
    SmsCreateCampaignsPage smsCreateCampaignsPage;
    MySmsCampaigns mySmsCampaigns;

    public void start() {

        if (browser.equals((Browser.CHROME.browserName()))) {
            // Check ChromeDriver Version
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if (browser.equals(Browser.FIREFOX.browserName())) {
            webDriver = new FirefoxDriver();
        } else if (browser.equals(Browser.EDGE.browserName())) {
            webDriver = new EdgeDriver();
        }

        // WebDriver SetUp
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));


        webDriver.navigate().to("https://myProjectLink :)");

        // Pages initialisation
        adminLoginPage = new AdminLoginPage(webDriver);
        twoStepVerificationPage = new TwoStepVerificationPage(webDriver);
        mainPage = new MainPage(webDriver);
        createEmailCampaignsPage = new CreateEmailCampaignsPage(webDriver);
        sendConfirmationPage = new SendConfirmationPage(webDriver);
        addContactPage = new AddContactPage(webDriver);
        myEmailCampaignsPage = new MyEmailCampaignsPage(webDriver);
        smsCreateCampaignsPage = new SmsCreateCampaignsPage(webDriver);
        mySmsCampaigns = new MySmsCampaigns(webDriver);
    }

    // Getters
    public void stop() {
        webDriver.close();
    }

    public AdminLoginPage adminLoginPage() {
        return adminLoginPage;
    }

    public TwoStepVerificationPage twoStepVerificationPage() {
        return twoStepVerificationPage;
    }

    public MainPage mainPage() {
        return mainPage;
    }

    public CreateEmailCampaignsPage createEmailCampaignsPage() {
        return createEmailCampaignsPage;
    }

    public SendConfirmationPage sendConfirmationPage() {
        return sendConfirmationPage;
    }

    public AddContactPage addContactPage() {
        return addContactPage;
    }

    public MyEmailCampaignsPage myEmailCampaignsPage() {
        return myEmailCampaignsPage;
    }

    public SmsCreateCampaignsPage smsCreateCampaignsPage() {
        return smsCreateCampaignsPage;
    }

    public MySmsCampaigns mySmsCampaigns() {
        return mySmsCampaigns;
    }
}
