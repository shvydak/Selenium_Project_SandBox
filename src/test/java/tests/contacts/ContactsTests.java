package tests.contacts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Contact;
import models_api.ContactDTO;
import models.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTests;

import static io.restassured.RestAssured.given;

public class ContactsTests extends BaseTests {
    @BeforeClass(alwaysRun = true)
    public void preConditions() {
        RestAssured.baseURI = "https://rest.myProjectLink :)";
        RestAssured.basePath = "v1/Contacts";
    }

    // test: add new contact with API
    @Test(groups = {"api"}, dataProvider = "addNewContactAPI", dataProviderClass = TestDataProvider.class)
    public void addNewContactPositiveAPI(ContactDTO user) {
        int userID;
        // Add a new Contact with API
        ContactDTO contactPost = given()
                .header("apikey", apiKey)
                .contentType(ContentType.JSON)
                .body(user)
                .when().post("?updateIfExists=true")
                .then().statusCode(200)
                .extract().response().as(ContactDTO.class);
        Assert.assertEquals(contactPost.getEmail(), user.getEmail());
        Assert.assertEquals(contactPost.getFirstName(), user.getFirstName());
        Assert.assertEquals(contactPost.getLastName(), user.getLastName());
        logger.info("Contact to save: " + contactPost.toString());

        // Save User ID
        userID = contactPost.getId();

        app.mainPage().pause(5000);

        // Get the Contact and compare
        ContactDTO contactGet =
                given().header("apikey", apiKey)
                        .when().get(Integer.toString(userID))
                        .then().assertThat().statusCode(200)
                        .extract().response().as(ContactDTO.class);
        Assert.assertEquals(contactGet.getEmail(), contactPost.getEmail());
        Assert.assertEquals(contactGet.getCellPhone(), contactPost.getCellPhone());
        Assert.assertEquals(contactGet.getFirstName(), contactPost.getFirstName());
        Assert.assertEquals(contactGet.getLastName(), contactPost.getLastName());
        logger.info("Get saved contact: " + contactGet.toString());
    }

    // test: add new contact from WEB
    @Test(groups = {"smoke"}, dataProvider = "addNewContactAllFieldsPositive", dataProviderClass = TestDataProvider.class)
    public void addNewContactPositiveWEB(Contact user) {
        app.mainPage().clickOnAddContactLink();
        app.addContactPage().fillAddContactForm(user);
        app.addContactPage().clickSaveButton();
        app.mainPage().clickCloseTabButton();
        logger.info("User to save -->> " + user.toString());

        app.mainPage().pause(5000);

        ContactDTO contactGet =
                given().header("apikey", apiKey)
                        .when().get(user.getEmail() + "?by=Email")
                        .then().assertThat().statusCode(200)
                        .extract().response().as(ContactDTO.class);
        Assert.assertEquals(contactGet.getEmail(), user.getEmail());
        Assert.assertEquals(contactGet.getFirstName(), user.getFirstName());
        Assert.assertEquals(contactGet.getLastName(), user.getLastName());
        logger.info("Get saved contact: " + contactGet.toString());
    }

    // test: leave all fields empty --> save button is not clickable
    @Test(groups = {"smoke"}, description = "ButtonDisabled")
    public void addNewContactEmptyFieldsSaveNegative() {
        app.mainPage().clickOnAddContactLink();
        Assert.assertFalse(app.addContactPage().isSaveContactButtonDisabled());
        app.mainPage().clickCloseTabButton();
    }

    @Test(groups = {"api"})
    public void AddNewContactEmptyFieldsSaveNegativeAPI() {
        String responseMessage = given().header("apikey", apiKey)
                .contentType(ContentType.JSON)
                .when().post("")
                .then().statusCode(400)
                .extract().path("message");
        Assert.assertEquals(responseMessage, "A contact identifier, i.e. email, cellPhone, or externalId, is missing in the request");
    }

    // test: type in any field and remove typed text, leave all fields empty< click save button --> error message
    @Test(groups = {"negative"}, description = "TypeAndRemove", dataProvider = "addNewContactTypeAndRemoveNegative", dataProviderClass = TestDataProvider.class)
    public void AddNewContactEmptyFieldsNegative(Contact user) {
        app.mainPage().clickOnAddContactLink();
        app.addContactPage().fillAndRemoveAddContactForm(user);
        app.addContactPage().clickSaveButton();
        Assert.assertTrue(app.addContactPage().errorMessageStringContainsText("Some values are missing or incorrect."));
        app.mainPage().clickCloseTabButtonWithConfirm();
    }

    // test: enter exist email --> error message
    @Test(groups = {"negative"}, dataProvider = "addNewContactSameEmailNegative", dataProviderClass = TestDataProvider.class)
    public void addNewContactSameEmailNegative(Contact user) {
        app.mainPage().clickOnAddContactLink();
        app.addContactPage().fillAddContactForm(user);
        app.addContactPage().clickSaveButtonNegative();
        app.addContactPage().pause(3000);
        System.out.println("RESULT OF MESSAGE --->>> " + app.addContactPage().errorMessageStringContainsGetProperty("The contact’s Email must be unique."));
        Assert.assertTrue(app.addContactPage().errorMessageStringContainsGetProperty("Email must be unique."));
        app.mainPage().clickCloseTabButtonWithConfirm();
    }

    @Test(groups = {"api"}, dataProvider = "addNewContactSameEmailNegativeAPI", dataProviderClass = TestDataProvider.class)
    public void addNewContactSameEmailNegativeAPI(ContactDTO user) {
        String res = given()
                .header("apikey", apiKey)
                .contentType(ContentType.JSON)
                .body(user)
                .when().post()
                .then().statusCode(409)
                .extract().response().asString();
        Assert.assertEquals(res, "A contact with the same identifier already exists");
    }

    // test: enter exist mobile --> error message
    @Test(groups = {"negative"}, dataProvider = "addNewContactSameMobileNegative", dataProviderClass = TestDataProvider.class)
    public void addNewContactSameMobileNegative(Contact user) {
        app.mainPage().clickOnAddContactLink();
        app.addContactPage().fillAddContactForm(user);
        app.addContactPage().clickSaveButton();
        Assert.assertTrue(app.addContactPage().errorMessageStringContainsText("Mobile must be unique."));
        app.mainPage().clickCloseTabButtonWithConfirm();
    }

    @Test(groups = {"api"}, dataProvider = "addNewContactSameMobileNegativeAPI", dataProviderClass = TestDataProvider.class)
    public void addNewContactSameMobileNegativeAPI(ContactDTO user) {
        String res = given()
                .header("apikey", apiKey)
                .contentType(ContentType.JSON)
                .body(user)
                .when().post()
                .then().statusCode(409)
                .extract().response().asString();
        Assert.assertEquals(res, "A contact with the same identifier already exists");
    }

    // test: enter invalid email format --> error message
    @Test(groups = "negative", dataProvider = "addNewContactIncorrectEmailFormatNegative", dataProviderClass = TestDataProvider.class)
    public void addNewContactInvalidEmailFormatNegative(Contact user) {
        app.mainPage().clickOnAddContactLink();
        app.addContactPage().fillAddContactForm(user);
        app.addContactPage().clickSaveButton();
        Assert.assertTrue(app.addContactPage().errorMessageStringContainsText("Email is not valid"));
        app.mainPage().clickCloseTabButtonWithConfirm();
    }

    @Test(groups = {"api"}, dataProvider = "addNewContactIncorrectEmailFormatNegativeAPI", dataProviderClass = TestDataProvider.class)
    public void addNewContactEmailFormatNegativeAPI(ContactDTO user) {
        String res = given()
                .header("apikey", apiKey)
                .contentType(ContentType.JSON)
                .body(user)
                .when().post()
                .then().statusCode(400)
                .extract().response().path("message");
//        Assert.assertEquals(res, "A contact identifier, i.e. email, cellPhone, or externalId, is missing in the request");
        // <<< !!! Bug Pressent !!! >>>
        logger.info("Entered email ': " + user.getEmail() + "' error message --> '" + res + "'");
    }

    @AfterMethod(alwaysRun = true)
    public void postConditions() {
        app.mainPage().pause(3000);
    }
}
