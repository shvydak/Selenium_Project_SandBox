package tests.email_campaigns;


import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.ApiException;
import com.mailslurp.models.Email;
import com.mailslurp.models.Inbox;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.EmailCampaign;
import models.TestDataProvider;
import models_api.EmailCampaignBody;
import models_api.EmailCampaignResponse;
import models_api.EmailCampaignStatisticsResponse;
import org.awaitility.Awaitility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTests;

import java.time.Duration;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class EmailCampaignsSendingTests extends BaseTests {
    private static ApiClient mailSlurpClient;
    private static final Long timeOut = 30000L; // set a timeout as fetching emails might take time
    private static Inbox inbox;
    private static Email email;
    int timeEmailWasSent = 90;


    @BeforeClass(alwaysRun = true)
    public static void beforeAll() throws ApiException {
        // Set-Up MailSlurp
        mailSlurpClient = com.mailslurp.clients.Configuration.getDefaultApiClient();
        mailSlurpClient.setApiKey(mailSlurpApiKey);
        mailSlurpClient.setConnectTimeout(timeOut.intValue());
        InboxControllerApi inboxControllerApi = new InboxControllerApi(mailSlurpClient);
        inbox = inboxControllerApi.getInbox(UUID.fromString("c76b70de-a180-4111-b9aa-a2e0057512e9"));
        Assert.assertTrue(inbox.getEmailAddress().contains("@mailslurp.com"));

        RestAssured.baseURI = "https://rest.myProjectLink :)";
        RestAssured.basePath = "v1/Campaigns";
    }


    @Test(groups = {"api"}, dataProvider = "emailCampaignToSingleEmailPositiveAPI", dataProviderClass = TestDataProvider.class)
    public void sendingEmailCampaignToSingleEmailPositiveAPI(EmailCampaignBody emailCampaignBody) throws ApiException {

        // creating of new Email Campaign
        logger.info("Creating new Email Campaign --> " + emailCampaignBody.toString());
        EmailCampaignResponse emailCampaignResponse = given()
                .header("apikey", apiKey)
                .body(emailCampaignBody)
                .contentType(ContentType.JSON)
                .when().post("?sendnow=true")
                .then().statusCode(200)
                .extract().response().as(EmailCampaignResponse.class);
        logger.info("Created new Email Campaign --> " + emailCampaignResponse.toString());
        Assert.assertEquals(emailCampaignBody.getSubject(), emailCampaignResponse.getSubject());

        // id, for checking that campaign was sent
        int id = emailCampaignResponse.getId();

        // wait while campaign sent, no more than 60 seconds
        logger.info("Waiting while campaign sent, no more than " + timeEmailWasSent + " seconds");
        Awaitility.await().atMost(Duration.ofSeconds(timeEmailWasSent)).until(() -> {
            Response response = given()
                    .header("apikey", apiKey)
                    .when().get(id + "/Statistics?by=CampaignId")
                    .then().statusCode(200)
                    .extract().response();

            EmailCampaignStatisticsResponse responseStat = response.as(EmailCampaignStatisticsResponse.class);
            boolean res = responseStat.getHowManyWasSent() == 1;
            return res;
        });


        // Assert that email received in remote email box
        logger.info("Wait while receiving email in the remote mailbox");
        WaitForControllerApi waitForControllerApi = new WaitForControllerApi(mailSlurpClient);
        email = waitForControllerApi.waitForLatestEmail(inbox.getId(), timeOut, true);
        if (emailCampaignBody.getSubject().equals(email.getSubject())) {
            logger.info("Email received 'successfully'");
        } else logger.info("Email received 'unsuccessfully'");
        Assert.assertEquals(emailCampaignBody.getSubject(), email.getSubject());
    }


    @Test(groups = {"smoke"}, dataProvider = "createAndSendEmailCampaignPositive", dataProviderClass = TestDataProvider.class)
    public void creationAndSendingEmailCampaignToSingleContactPositive(EmailCampaign emailCampaign) throws ApiException {
        // Test in browser
        app.mainPage().clickOnCreateEmailCampaignLink();
        app.createEmailCampaignsPage().fillEmailCampaignForm(emailCampaign);
        app.createEmailCampaignsPage().selectTemplate();
        app.createEmailCampaignsPage().clickContinueSendingButton();
        app.createEmailCampaignsPage().clickBlastOffButton();
        Assert.assertTrue(app.sendConfirmationPage().isMessageYourCampaignWasSentAppeared());
        app.mainPage().clickCloseTabButton();

        // Check that email-campaign appeared in "My Email Campaigns" list
        app.mainPage().clickOnMyEmailCampaignsLink();
        Assert.assertEquals(emailCampaign.getSubject(), app.myEmailCampaignsPage().savedName());
        app.mainPage().clickCloseTabButton();

        // Assert email receiving
        WaitForControllerApi waitForControllerApi = new WaitForControllerApi(mailSlurpClient);
        email = waitForControllerApi.waitForLatestEmail(inbox.getId(), timeOut, true);
        Assert.assertEquals(emailCampaign.getSubject(), email.getSubject());
        logger.info("Sent subject --> " + emailCampaign.getSubject());
        logger.info("Received subject --> " + email.getSubject());
    }

    @Test(groups = {"positive"}, dataProvider = "createAndSendEmailCampaignToListPositive", dataProviderClass = TestDataProvider.class)
    public void creationAndSendingEmailCampaignToListPositive(EmailCampaign emailCampaign) throws ApiException {
        // Test in browser
        app.mainPage().clickOnCreateEmailCampaignLink();
        app.createEmailCampaignsPage().fillEmailCampaignForm(emailCampaign);
        app.createEmailCampaignsPage().selectTemplate();
        app.createEmailCampaignsPage().clickContinueSendingButton();
        app.createEmailCampaignsPage().clickBlastOffButton();
        Assert.assertTrue(app.sendConfirmationPage().isMessageYourCampaignWasSentAppeared());
        app.mainPage().clickCloseTabButton();

        // Check that email-campaign appeared in "My Email Campaigns" list
        app.mainPage().clickOnMyEmailCampaignsLink();
        Assert.assertEquals(emailCampaign.getSubject(), app.myEmailCampaignsPage().savedName());
        app.mainPage().clickCloseTabButton();

        // Assert email receiving
        WaitForControllerApi waitForControllerApi = new WaitForControllerApi(mailSlurpClient);
        email = waitForControllerApi.waitForLatestEmail(inbox.getId(), timeOut, true);
        Assert.assertEquals(emailCampaign.getSubject(), email.getSubject());
        logger.info("Sent subject --> " + emailCampaign.getSubject());
        logger.info("Received subject --> " + email.getSubject());
    }

    @AfterMethod(alwaysRun = true)
    public void postConditions() {
        app.mainPage().pause(3000);
    }
}