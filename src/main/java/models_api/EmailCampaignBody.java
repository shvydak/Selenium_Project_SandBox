package models_api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class EmailCampaignBody {
    private String externalId;
    private String customFromAddress;
    private String customReplyToAddress;
    private String[] customData; //KeyValuePair[String,String]
    private String customUnsubscribeMode;
    private String externalUnsubscribeUrl;
    private boolean trackLinks;
    private String subject;
    private String body;
    private int[] toListsById;
    private int[] toMembersById;
    private String[] toMembersByEmail;
    private String[] campaignAttachments;
    private int[] excludeFromMembers;
    private int[] excludeFromLists;
    private String[] toMembersByExternalId;
}
