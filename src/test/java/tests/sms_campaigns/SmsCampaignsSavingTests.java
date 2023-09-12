package tests.sms_campaigns;

import models.SmsCampaign;
import models.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTests;

public class SmsCampaignsSavingTests extends BaseTests {
    @Test(groups = {"smoke"}, dataProvider = "createAndSaveSmsCampaignToSingleRecipientPositiveSmoke", dataProviderClass = TestDataProvider.class)
    public void creationAndSavingSmsCampaignToSingleRecipientPositiveSmoke(SmsCampaign smsCampaign) {
        app.mainPage().clickOnCreateSmsCampaignsLink();
        app.smsCreateCampaignsPage().fillSmsCampaignForm(smsCampaign);
        app.mainPage().clickSaveButton();
        app.mainPage().clickCloseTabButton();
        app.mainPage().clickMySmsCampaignsLink();
        Assert.assertTrue(app.mySmsCampaigns().isMySmsCampaignsTableContainsText(smsCampaign.getMessageContent()));
        app.mainPage().clickCloseTabButton();
    }

    @Test(groups = {"positive"}, dataProvider = "createAndSaveSmsCampaignWithAutoFillFieldsPositive", dataProviderClass = TestDataProvider.class)
    public void creationAndSavingSmsCampaignWithAutoFillFieldsPositive(SmsCampaign smsCampaign) {
        app.mainPage().clickOnCreateSmsCampaignsLink();
        app.smsCreateCampaignsPage().fillSmsCampaignForm(smsCampaign);
        app.mainPage().clickSaveButton();
        app.mainPage().clickCloseTabButton();
        app.mainPage().clickMySmsCampaignsLink();
        Assert.assertTrue(app.mySmsCampaigns().isMySmsCampaignsTableContainsText(smsCampaign.getMessageContent()));
        app.mainPage().clickCloseTabButton();
    }
}
