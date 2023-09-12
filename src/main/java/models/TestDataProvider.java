package models;

import models_api.ContactDTO;
import com.github.javafaker.Faker;
import models_api.EmailCampaignBody;
import org.testng.annotations.DataProvider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestDataProvider {
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = currentDateTime.format(formatter);

    //       <<<========   CONTACTS   ========>>>

    @DataProvider
    public Iterator<Object[]> addNewContactAllFieldsPositive() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .firstName(Faker.instance().name().firstName())
                .lastName(Faker.instance().name().lastName())
                .email(Faker.instance().internet().emailAddress())
                .phone(Faker.instance().phoneNumber().phoneNumber())
                .mobile("053" + Faker.instance().random().nextInt(1000000, 9999999))
                .address(Faker.instance().address().streetAddress())
                .city(Faker.instance().address().city())
                .country(Faker.instance().address().country())
                .company(Faker.instance().company().name())
                .position(Faker.instance().company().profession())
                .birthday(Faker.instance().random().nextInt(1, 31) + "/"
                        + "0" + Faker.instance().random().nextInt(1, 9)
                        + "/" + Faker.instance().random().nextInt(1930, 2022))
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addNewContactSameEmailNegative() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .email("y.shvydak@gmail.com")
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addNewContactSameEmailNegativeAPI() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{ContactDTO.builder()
                .email("y.shvydak@gmail.com")
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addNewContactSameMobileNegativeAPI() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{ContactDTO.builder()
                .cellPhone("0533669983")
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addNewContactSameMobileNegative() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .mobile("0533669983")
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addNewContactTypeAndRemoveNegative() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .firstName(Faker.instance().name().firstName())
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addNewContactIncorrectEmailFormatNegative() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .email(Faker.instance().name().firstName().toLowerCase())
                .build()});
        list.add(new Object[]{Contact.builder()
                .email(Faker.instance().name().firstName() + "@")
                .build()});
        list.add(new Object[]{Contact.builder()
                .email(Faker.instance().name().firstName() + "@gmail")
                .build()});
        list.add(new Object[]{Contact.builder()
                .email(Faker.instance().name().firstName() + "@gmail.")
                .build()});
        list.add(new Object[]{Contact.builder()
                .email(Faker.instance().name().firstName() + "@.com")
                .build()});
        list.add(new Object[]{Contact.builder()
                .email(Faker.instance().name().firstName() + "gmail.com")
                .build()});
        list.add(new Object[]{Contact.builder()
                .email("@" + Faker.instance().internet().domainWord())
                .build()});
        list.add(new Object[]{Contact.builder()
                .email("@" + Faker.instance().internet().domainWord() + ".")
                .build()});
        list.add(new Object[]{Contact.builder()
                .email("@" + Faker.instance().internet().domainName())
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addNewContactIncorrectEmailFormatNegativeAPI() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{ContactDTO.builder()
                .email(Faker.instance().name().firstName().toLowerCase())
                .build()});
        list.add(new Object[]{ContactDTO.builder()
                .email(Faker.instance().name().firstName() + "@")
                .build()});
        list.add(new Object[]{ContactDTO.builder()
                .email(Faker.instance().name().firstName() + "@gmail")
                .build()});
        list.add(new Object[]{ContactDTO.builder()
                .email(Faker.instance().name().firstName() + "@gmail.")
                .build()});
        list.add(new Object[]{ContactDTO.builder()
                .email(Faker.instance().name().firstName() + "@.com")
                .build()});
        list.add(new Object[]{ContactDTO.builder()
                .email(Faker.instance().name().firstName() + "gmail.com")
                .build()});
        list.add(new Object[]{ContactDTO.builder()
                .email("@" + Faker.instance().internet().domainWord())
                .build()});
        list.add(new Object[]{ContactDTO.builder()
                .email("@" + Faker.instance().internet().domainWord() + ".")
                .build()});
        list.add(new Object[]{ContactDTO.builder()
                .email("@" + Faker.instance().internet().domainName())
                .build()});
        return list.iterator();
    }

    @DataProvider // ContactDTO --->> API
    public Iterator<Object[]> addNewContactAPI() {
        int[] listNme = {861965};
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{ContactDTO.builder()
                .firstName(Faker.instance().name().firstName())
                .lastName(Faker.instance().name().lastName())
                .email(Faker.instance().internet().emailAddress())
                .cellPhone(Faker.instance().phoneNumber().cellPhone())
                .company(Faker.instance().company().name())
                .position(Faker.instance().company().profession())
//                .lists_ToSubscribe(listNme)
                .build()});
        return list.iterator();
    }


    //       <<<========  SENDING EMAIL CAMPAIGNS   ========>>>


    @DataProvider
    public Iterator<Object[]> createAndSendEmailCampaignPositive() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{EmailCampaign.builder()
                .subject("Hello! This is test-campaign for SINGLE Contact sending, for AUTOTEST ;) // " + formattedDateTime)
                .recipients("c76b70de-a180-4111-b9aa-a2e0057512e9@mailslurp.com")
                .summary(Faker.instance().hipster().word())
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> createAndSendEmailCampaignToListPositive() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{EmailCampaign.builder()
                .subject("Hello! This is test-campaign for GROUP Contacts sending, for AUTOTEST ;) // " + formattedDateTime)
                .recipients("MailSlurp")
                .summary(Faker.instance().hipster().word())
                .build()});
        return list.iterator();
    }
    //       <<<========  SAVING EMAIL CAMPAIGNS   ========>>>


    @DataProvider
    public Iterator<Object[]> createAndSaveEmailCampaignToSingleEmailPositive() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{EmailCampaign.builder()
                .subject("This is saved test-campaign to Single Email-Address from AUTOTEST... ;) // " + formattedDateTime)
                .recipients("y.shvydak@gmail.com")
                .summary(Faker.instance().hipster().word())
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> createAndSaveEmailCampaignToMultipleEmailsPositive() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{EmailCampaign.builder()
                .subject("This is saved test-campaign to Multiple Email-Addresses from AUTOTEST... ;) // " + formattedDateTime)
                .recipients("UU")
                .summary(Faker.instance().hipster().word())
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> createAndSavePersonalAppealEmailCampaignPositive() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{EmailCampaign.builder()
                .subject(" ,This is saved test-campaign with Personal Appeal from AUTOTEST... ;) // " + formattedDateTime)
                .recipients("y.shvydak@gmail.com")
                .summary(Faker.instance().hipster().word())
                .build()});
        return list.listIterator();
    }

    @DataProvider
    public Iterator<Object[]> createAndSaveEmailCampaignWOSubjectNegative() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{EmailCampaign.builder()
                .recipients("y.shvydak@gmail.com")
                .summary(Faker.instance().hipster().word())
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> emailCampaignToSingleEmailPositiveAPI() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{EmailCampaignBody.builder()
                .trackLinks(true)
                .body("This is BODY API")
                .subject("Test API Subject // " + formattedDateTime)
                .customUnsubscribeMode("None")
//                .toMembersByEmail(new String[]{"y.shvydak@gmail.com"})
                .toMembersByEmail(new String[]{"c76b70de-a180-4111-b9aa-a2e0057512e9@mailslurp.com"})
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> createAndSaveEmailCampaignWORecipientsNegativeAPI() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{EmailCampaignBody.builder()
                .trackLinks(true)
                .subject("Subject WO Recipients!")
                .customUnsubscribeMode("None")
                .build()});
        return list.iterator();
    }

    //       <<<========   SMS CAMPAIGNS   ========>>>


    @DataProvider
    public Iterator<Object[]> createAndSaveSmsCampaignToSingleRecipientPositiveSmoke() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{SmsCampaign.builder()
                .recipients("Yurii Shvydak")
                .messageContent("Hello, I'm saving test-SMS campaign " + formattedDateTime)
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> createAndSaveSmsCampaignToSingleRecipientPositive() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{SmsCampaign.builder()
                .recipients("Yurii Shvydak")
                .messageContent("Hello, I'm saving test-SMS campaign " + formattedDateTime)
                .build()});
        list.add(new Object[]{SmsCampaign.builder()
                .recipients("Yurii Shvydak")
                .messageContent("Hello, I'm saving test-SMS campaign " + formattedDateTime)
                .trackLinkClicks(true)
                .build()});
        list.add(new Object[]{SmsCampaign.builder()
                .recipients("Yurii Shvydak")
                .messageContent("Hello, I'm saving test-SMS campaign " + formattedDateTime)
                .addUnsubscribeLink(true)
                .build()});
        list.add(new Object[]{SmsCampaign.builder()
                .recipients("Yurii Shvydak")
                .messageContent("Hello, I'm saving test-SMS campaign " + formattedDateTime)
                .autoFillFields(true)
                .build()});
        list.add(new Object[]{SmsCampaign.builder()
                .recipients("Yurii Shvydak")
                .messageContent("Hello, I'm saving test-SMS campaign " + formattedDateTime)
                .trackLinkClicks(true)
                .addUnsubscribeLink(true)
                .autoFillFields(true)
                .build()});
        return list.iterator();
    }

    public Iterator<Object[]> createAndSaveSmsCampaignWithAutoFillFieldsFieldPositive() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{SmsCampaign.builder()
                .recipients("Yurii Shvydak")
                .messageContent("Hello, You are the Best!!!")
//                .trackLinkClicks(true)
//                .addUnsubscribeLink(true)
                .autoFillFields(true)
                .build()});
        return list.iterator();
    }

}
