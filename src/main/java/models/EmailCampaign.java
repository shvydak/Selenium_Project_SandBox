package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class EmailCampaign {
    private String subject;
    private String recipients;
    private String summary;
}
