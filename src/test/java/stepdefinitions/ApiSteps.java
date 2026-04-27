package stepdefinitions;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class ApiSteps {

    public static String username; 
    public static String password;
    public static String token;
    public static String userId;

    @Given("user uses API to  enter username {string} and password {string}")
    public void createUser(String user, String pass) {
        username = user;
        password = pass;
        String payload = "{ \"userName\": \"" + username + "\", \"password\": \"" + password + "\" }";

        Response createResponse = given()
                .baseUri("https://demoqa.com") // Explicitly set baseURI
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/Account/v1/User");

        System.out.println("--- API CREATE USER ---");
        System.out.println("Status: " + createResponse.getStatusCode());
        System.out.println("Body: " + createResponse.getBody().asString());
        
        Assert.assertEquals(createResponse.getStatusCode(), 201, "User creation failed!");
        userId = createResponse.jsonPath().getString("userID");
    }

    @Then("I delete the user account using API")
    public void deleteUser() {
        String payload = "{ \"userName\": \"" + username + "\", \"password\": \"" + password + "\" }";

        Response tokenResponse = given()
                .baseUri("https://demoqa.com")
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/Account/v1/GenerateToken");

        token = tokenResponse.jsonPath().getString("token");

        System.out.println("--- API DELETE USER DEBUG ---");
        System.out.println("UserID to delete: " + userId);
        System.out.println("Fresh Token: " + token);

        if (userId == null || token == null) {
            Assert.fail("Cannot delete user because UserID or Token is null!");
        }

        Response deleteResponse = given()
                .baseUri("https://demoqa.com")
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json") 
                .when()
                .delete("/Account/v1/User/" + userId);

        int status = deleteResponse.getStatusCode();
        System.out.println("Delete Response Status: " + status);
        System.out.println("Delete Response Body: " + deleteResponse.getBody().asString());
        System.out.println("-----------------------------");

        Assert.assertTrue(status == 200 || status == 204, 
            "Failed to delete account! Status received: " + status);
    }
}