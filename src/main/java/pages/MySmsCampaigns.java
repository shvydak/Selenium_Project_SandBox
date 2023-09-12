package pages;

import manager.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MySmsCampaigns extends BaseHelper {
    public MySmsCampaigns(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//table[@class='smoove-table table table-sm table-hover']")
    WebElement allMyCampaignsTable;

    public boolean isMySmsCampaignsTableContainsText(String text) {
        waitUntilVisible(allMyCampaignsTable);
        return allMyCampaignsTable.getDomProperty("textContent").contains(text);
    }
}
