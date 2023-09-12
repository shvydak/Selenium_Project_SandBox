package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Contact {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String mobile;
    private String address;
    private String city;
    private String country;
    private String company;
    private String position;
    private String birthday;
    private String listName;

}
