package tests.email_campaigns;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.EmailCampaign;
import models.TestDataProvider;
import models_api.EmailCampaignBody;
import models_api.EmailCampaignResponse;
import models_api.EmailCampaignStatisticsResponse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTests;

import static io.restassured.RestAssured.given;

public class EmailCampaignsSavingTests extends BaseTests {

    @BeforeClass(alwaysRun = true)
    public void preConditions() {
        RestAssured.baseURI = "https://rest.myProjectLink :)";
        RestAssured.basePath = "v1/Campaigns";
    }

    @Test(groups = {"api"}, dataProvider = "emailCampaignToSingleEmailPositiveAPI", dataProviderClass = TestDataProvider.class)
    public void creationAndSavingEmailCampaignToSingleEmailPositiveAPI(EmailCampaignBody emailCampaignBody) {
        logger.info("EmailCampaign Body --> " + emailCampaignBody);
        EmailCampaignResponse response = given()
                .header("apikey", apiKey)
                .body(emailCampaignBody)
                .contentType(ContentType.JSON)
                .when().post()
                .then().statusCode(200)
                .extract().response().as(EmailCampaignResponse.class);
        logger.info("EmailCampaign Response --> " + response);
        Assert.assertEquals(emailCampaignBody.getSubject(), response.getSubject());

        int id = response.getId();

        app.mainPage().pause(5000);

        EmailCampaignStatisticsResponse responseStat = given()
                .header("apikey", apiKey)
                .when().get(id + "/Statistics?by=CampaignId")
                .then().statusCode(200)
                .extract().response().as(EmailCampaignStatisticsResponse.class);
        logger.info("Response statistics --> " + responseStat.toString());
        Assert.assertEquals(responseStat.getHowManyWasSent(), 0);
    }


    @Test(groups = {"smoke"}, dataProvider = "createAndSaveEmailCampaignToSingleEmailPositive", dataProviderClass = TestDataProvider.class)
    public void creationAndSavingEmailCampaignToSingleEmailPositive(EmailCampaign emailCampaign) {
        app.mainPage().clickOnCreateEmailCampaignLink();
        app.createEmailCampaignsPage().fillEmailCampaignForm(emailCampaign);
        app.createEmailCampaignsPage().selectTemplate();
        app.mainPage().clickSaveButton();
        app.mainPage().clickCloseTabButton();
        app.mainPage().clickOnMyEmailCampaignsLink();
        Assert.assertEquals(emailCampaign.getSubject(), app.myEmailCampaignsPage().savedName());
        app.mainPage().clickCloseTabButton();
    }


    @Test(groups = {"positive"}, dataProvider = "createAndSaveEmailCampaignToMultipleEmailsPositive", dataProviderClass = TestDataProvider.class)
    public void creationAndSavingEmailCampaignToMultipleleEmailsPositive(EmailCampaign emailCampaign) {
        app.mainPage().clickOnCreateEmailCampaignLink();
        app.createEmailCampaignsPage().fillEmailCampaignForm(emailCampaign);
        app.createEmailCampaignsPage().selectTemplate();
        app.mainPage().clickSaveButton();
        app.mainPage().clickCloseTabButton();
        app.mainPage().clickOnMyEmailCampaignsLink();
        Assert.assertEquals(emailCampaign.getSubject(), app.myEmailCampaignsPage().savedName());
        app.mainPage().clickCloseTabButton();
    }

    @Test(groups = {"positive"}, dataProvider = "createAndSavePersonalAppealEmailCampaignPositive", dataProviderClass = TestDataProvider.class)
    public void creationAndSavingEmailCampaignWithPersonalAppealFirstNamePositive(EmailCampaign emailCampaign) {
        app.mainPage().clickOnCreateEmailCampaignLink();
        app.createEmailCampaignsPage().fillEmailCampaignForm(emailCampaign, "First name");
        app.createEmailCampaignsPage().selectTemplate();
        app.mainPage().clickSaveButton();
        app.mainPage().clickCloseTabButton();
        app.mainPage().clickOnMyEmailCampaignsLink();
        Assert.assertTrue(app.myEmailCampaignsPage().savedName().contains(emailCampaign.getSubject()));
        app.mainPage().clickCloseTabButton();
    }

    @Test(groups = {"positive"}, dataProvider = "createAndSavePersonalAppealEmailCampaignPositive", dataProviderClass = TestDataProvider.class)
    public void creationAndSavingEmailCampaignWithPersonalAppealLastNamePositive(EmailCampaign emailCampaign) {
        app.mainPage().clickOnCreateEmailCampaignLink();
        app.createEmailCampaignsPage().fillEmailCampaignForm(emailCampaign, "Last name");
        app.createEmailCampaignsPage().selectTemplate();
        app.mainPage().clickSaveButton();
        app.mainPage().clickCloseTabButton();
        app.mainPage().clickOnMyEmailCampaignsLink();
        Assert.assertTrue(app.myEmailCampaignsPage().savedName().contains(emailCampaign.getSubject()));
        app.mainPage().clickCloseTabButton();
    }

    @Test(groups = {"positive"}, dataProvider = "createAndSavePersonalAppealEmailCampaignPositive", dataProviderClass = TestDataProvider.class)
    public void creationAndSavingEmailCampaignWithPersonalAppealUserNamePositive(EmailCampaign emailCampaign) {
        app.mainPage().clickOnCreateEmailCampaignLink();
        app.createEmailCampaignsPage().fillEmailCampaignForm(emailCampaign, "User name");
        app.createEmailCampaignsPage().selectTemplate();
        app.mainPage().clickSaveButton();
        app.mainPage().clickCloseTabButton();
        app.mainPage().clickOnMyEmailCampaignsLink();
        Assert.assertTrue(app.myEmailCampaignsPage().savedName().contains(emailCampaign.getSubject()));
        app.mainPage().clickCloseTabButton();
    }

    @Test(groups = {"negative"}, dataProvider = "createAndSaveEmailCampaignWOSubjectNegative", dataProviderClass = TestDataProvider.class)
    public void creationAndSavingEmailCampaignWOSubjectNegative(EmailCampaign emailCampaign) {
        app.mainPage().clickOnCreateEmailCampaignLink();
        app.createEmailCampaignsPage().fillEmailCampaignForm(emailCampaign);
        app.createEmailCampaignsPage().selectTemplate();
        app.mainPage().clickSaveButton();
        app.createEmailCampaignsPage().errorMessageStringContains("Please enter subject");
        app.mainPage().clickCloseTabButton();
    }

    @Test(groups = {"api"}, dataProvider = "createAndSaveEmailCampaignWORecipientsNegativeAPI", dataProviderClass = TestDataProvider.class)
    public void creationAndSavingEmailCampaignWORecipientsNegativeAPI(EmailCampaignBody emailCampaignBody) {
        logger.info("EmailCampaign Body --> " + emailCampaignBody);
        String response = given()
                .header("apikey", apiKey)
                .body(emailCampaignBody)
                .contentType(ContentType.JSON)
                .when().post()
                .then().statusCode(400)
                .extract().response().path("message");
        Assert.assertEquals(response, "Please provide any recipients");
        logger.info("EmailCampaign Response message--> " + response);
    }


    @Test(groups = {"smoke"}, dataProvider = "createAndSaveEmailCampaignToSingleEmailPositive", dataProviderClass = TestDataProvider.class)
    public void creationSavingAndSendTestEmailCampaignToSingleEmailPositive(EmailCampaign emailCampaign) {
        app.mainPage().clickOnCreateEmailCampaignLink();
        app.createEmailCampaignsPage().fillEmailCampaignForm(emailCampaign);
        app.createEmailCampaignsPage().selectTemplate();
        app.createEmailCampaignsPage().clickSaveAndSendTestEmailButton();
        app.createEmailCampaignsPage().submitTestEmailSendButton();
        app.mainPage().clickCloseTabButton();
        app.mainPage().clickOnMyEmailCampaignsLink();
        Assert.assertEquals(emailCampaign.getSubject(), app.myEmailCampaignsPage().savedName());
        app.mainPage().clickCloseTabButton();
    }

    @AfterMethod(alwaysRun = true)
    public void postConditions() {
        app.mainPage().pause(3000);
    }
}
