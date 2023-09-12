package models_api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ContactDTO {
    private int id;
    private String externalId;
    private String password;
    private String email;
    private String phone;
    private String cellPhone;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String address;
    private String city;
    private String country;
    private String company;
    private String position;
    private boolean canReceiveEmails;
    private boolean canReceiveSmsMessages;
    private int[] lists_ToSubscribe;
    private int[] lists_ToUnsubscribe;
    private Object customFields;
    private String campaignSource;
    private int c_DaysSinceSignup;
    private String listAssociationTime;
    private String joinSource;
    private String lastChanged;
    private String timestampSignup;
    private String ipSignup;
    private boolean deleted;
}
