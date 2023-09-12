package tests.login;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTests;

import static io.restassured.RestAssured.given;

public class StartTest extends BaseTests {

    @BeforeClass(alwaysRun = true)
    public void preConditions() {
        RestAssured.baseURI = "https://rest.myProjectLink :)";
        RestAssured.basePath = "v1/Contacts";
    }

    @Test(groups = {"smoke"}) // REMOVE !!!
    public void start() {

    }

    @Test(groups = {"api"})
    public void badRequest400() {
        logger.info("Expected status code--> 400");
        String response = given()
                .header("apikey", apiKey)
                .contentType(ContentType.JSON)
                .when().post()
                .then()
                .assertThat()
                .statusCode(400)
                .extract().response().path("message");
        Assert.assertEquals(response, "A contact identifier, i.e. email, cellPhone, or externalId, is missing in the request");
        logger.info("Test 'badRequest400' finished");
    }


    @Test(groups = {"api"})
    public void unauthorized401() {
        logger.info("Expected status code--> 401");
        String response =
                given().header("apikey", apiKey + "12e")
                        .when().get("0533669983?by=Cellphone")
                        .then().assertThat().statusCode(401)
                        .extract().response().asString();
        Assert.assertEquals(response, "Unauthorized Cannot get Contact");
        logger.info("Test 'unauthorized401' finished");
    }

    @Test(groups = {"api"})
    public void methodNotAlowed405() {
        logger.info("Expected status code--> 405");
        String response = given()
                .header("apikey", apiKey)
                .contentType(ContentType.JSON)
                .when().post("/abc")
                .then()
                .assertThat()
                .statusCode(405)
                .extract().response().path("message");
        System.out.println(response);
        Assert.assertTrue(response.contains("The requested resource does not support http method 'POST'."));
        logger.info("Test 'methodNotAlowed405' finished");
    }

    @Test(groups = {"api"})
    public void notFound404() {
        logger.info("Expected status code--> 404");
        int response = given()
                .header("apikey", apiKey)
                .when().get("/contacts")
                .then()
                .assertThat()
                .statusCode(404).extract().statusCode();
        Assert.assertEquals(response, 404);
        logger.info("Test 'notFound404' finished");
    }

}
