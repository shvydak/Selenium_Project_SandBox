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

public class EmailCampaignsDeletingTests extends BaseTests {
    @BeforeClass(alwaysRun = true)
    public void preConditions() {
        RestAssured.baseURI = "https://rest.myProjectLink :)";
        RestAssured.basePath = "v1/Campaigns";
    }

    @Test(groups = {"api"}, dataProvider = "emailCampaignToSingleEmailPositiveAPI", dataProviderClass = TestDataProvider.class, enabled = false)
    public void deletingEmailCampaignPositiveAPI(EmailCampaignBody emailCampaignBody) {
        // test: create new email campaign
        logger.info("Campaign to delete --> " + emailCampaignBody);
        EmailCampaignResponse response = given()
                .header("apikey", apiKey)
                .body(emailCampaignBody)
                .contentType(ContentType.JSON)
                .when().post()
                .then().statusCode(200)
                .extract().response().as(EmailCampaignResponse.class);
        Assert.assertEquals(emailCampaignBody.getSubject(), response.getSubject());

        // test: extract ID for new request
        int id = response.getId();

        app.mainPage().pause(5000);

        // test: get campaign by ID
        EmailCampaignStatisticsResponse responseStat = given()
                .header("apikey", apiKey)
                .when().get(id + "/Statistics?by=CampaignId")
                .then().statusCode(200)
                .extract().response().as(EmailCampaignStatisticsResponse.class);
        Assert.assertEquals(responseStat.getHowManyWasSent(), 0);

        app.mainPage().pause(1000);

        // test: delete campaign by ID
        String res = given()
                .header("apikey", apiKey)
                .when().delete(id + "?by=CampaignId")
                .then().statusCode(200)
                .extract().response().asString();
        Assert.assertEquals(res, "\"Deleted\"");

        // test: get campaign by ID --> error/not exist
        res = given()
                .header("apikey", apiKey)
                .when().get(id + "/Statistics?by=CampaignId")
                .then().statusCode(200)
                .extract().response().path("message");
        logger.info(res);
        Assert.assertEquals(res, "Email Campaign not exist");
    }

    @Test(groups = {"smoke"}, dataProvider = "createAndSaveEmailCampaignToSingleEmailPositive", dataProviderClass = TestDataProvider.class)
    public void deletingEmailCampaignWithIconDeletingButtonPositive(EmailCampaign emailCampaign) {
        app.mainPage().clickOnCreateEmailCampaignLink();
        app.createEmailCampaignsPage().fillEmailCampaignForm(emailCampaign);
        app.createEmailCampaignsPage().selectTemplate();
        app.mainPage().clickSaveButton();
        app.mainPage().clickCloseTabButton();
        app.mainPage().clickOnMyEmailCampaignsLink();
        Assert.assertEquals(emailCampaign.getSubject(), app.myEmailCampaignsPage().savedName());
        app.myEmailCampaignsPage().deleteFirstEmailCampaignWithCampaignIconDeleteButton();
        Assert.assertNotEquals(app.myEmailCampaignsPage().savedName(), emailCampaign.getSubject());
        app.mainPage().clickCloseTabButton();
    }

    @Test(groups = {"smoke"}, dataProvider = "createAndSaveEmailCampaignToSingleEmailPositive", dataProviderClass = TestDataProvider.class)
    public void cancelationOfDeletingEmailCampaignWithIconDeletingButtonPositive(EmailCampaign emailCampaign) {
        app.mainPage().clickOnCreateEmailCampaignLink();
        app.createEmailCampaignsPage().fillEmailCampaignForm(emailCampaign);
        app.createEmailCampaignsPage().selectTemplate();
        app.mainPage().clickSaveButton();
        app.mainPage().clickCloseTabButton();
        app.mainPage().clickOnMyEmailCampaignsLink();
        Assert.assertEquals(emailCampaign.getSubject(), app.myEmailCampaignsPage().savedName());
        app.myEmailCampaignsPage().cancelDeleteFirstEmailCampaignWithCampaignIconDeleteButton();
        Assert.assertEquals(app.myEmailCampaignsPage().savedName(), emailCampaign.getSubject());
        app.mainPage().clickCloseTabButton();
    }

    @Test(groups = {"positive"}, dataProvider = "createAndSaveEmailCampaignToSingleEmailPositive", dataProviderClass = TestDataProvider.class)
    public void deletingEmailCampaignWithHeaderDeletingButtonPositive(EmailCampaign emailCampaign) {
        app.mainPage().clickOnCreateEmailCampaignLink();
        app.createEmailCampaignsPage().fillEmailCampaignForm(emailCampaign);
        app.createEmailCampaignsPage().selectTemplate();
        app.mainPage().clickSaveButton();
        app.mainPage().clickCloseTabButton();
        app.mainPage().clickOnMyEmailCampaignsLink();
        Assert.assertEquals(emailCampaign.getSubject(), app.myEmailCampaignsPage().savedName());
        app.myEmailCampaignsPage().deleteFirstEmailCampaignWithCampaignHeaderDeleteButton();
        Assert.assertNotEquals(app.myEmailCampaignsPage().savedName(), emailCampaign.getSubject());
        app.mainPage().clickCloseTabButton();
    }

    @Test(groups = {"positive"}, priority = 1, dataProvider = "createAndSaveEmailCampaignToSingleEmailPositive", dataProviderClass = TestDataProvider.class)
    public void creationSavingDeletionAllEmailCampaignPositive(EmailCampaign emailCampaign) {
        app.mainPage().clickOnCreateEmailCampaignLink();
        app.createEmailCampaignsPage().fillEmailCampaignForm(emailCampaign);
        app.createEmailCampaignsPage().selectTemplate();
        app.mainPage().clickSaveButton();
        app.mainPage().clickCloseTabButton();
        app.mainPage().clickOnMyEmailCampaignsLink();
        Assert.assertEquals(emailCampaign.getSubject(), app.myEmailCampaignsPage().savedName());
        app.myEmailCampaignsPage().deleteAllCampaigns();
        app.mainPage().clickCloseTabButton();
    }


    @AfterMethod(alwaysRun = true)
    public void postConditions() {
        app.mainPage().pause(3000);
    }
}
