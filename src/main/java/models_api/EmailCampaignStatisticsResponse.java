package models_api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class EmailCampaignStatisticsResponse {
    private String sentDate;
    private int howManyWasSent;
    private int bounced;
    private int howManyWasBouncedSoft;
    private int howManyWasBouncedHard;
    private int howManyWasWatched;
    private int clicked;
    private int linksClicked;
    private int unsubcribed;
    private int resubcribed;
    private int abused;
}
