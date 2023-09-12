package models_api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class EmailCampaignResponse {
    private int id;
    private String externalId;
    private String customFromAddress;
    private String customReplyToAddress;
    private String customUnsubscribeMode;
    private String externalUnsubscribeUrl;
    private boolean trackLinks;
    private String subject;
    private String body;
    private int[] toListsById;
    private int[] toMembersById;
    private int[] excludeFromMembers;
    private int[] excludeFromLists;
    private String[] toMembersByExternalId;
}
