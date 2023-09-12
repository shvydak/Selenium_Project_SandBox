package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class SmsCampaign {
    private String recipients;
    private String messageContent;
    private boolean autoFillFields;
    private boolean addUnsubscribeLink;
    private boolean trackLinkClicks;
}
