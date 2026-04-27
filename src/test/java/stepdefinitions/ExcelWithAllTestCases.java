package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.LoginPage;

import java.time.Duration;

public class ExcelWithAllTestCases extends BaseTest {

    private LoginPage loginPage;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        // driver is initialized in Hooks
        driver.get("https://demoqa.com/login");
        loginPage = new LoginPage(driver);
    }

    // @When("user enters username {string} and password {string}")
    // public void user_enters_username_and_password(String username, String
    // password) {
    // loginPage.enterUsername(username);
    // loginPage.enterPassword(password);
    // }
    @When("user enters username {string} and password {string}")
    public void enter_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("clicks on login button")
    public void clicks_on_login_button() {
        loginPage.clickLogin();
    }

    @And("login result should be {string}")
    public void login_result_should_be(String expectedResult) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        if (expectedResult.equalsIgnoreCase("success")) {
            wait.until(ExpectedConditions.urlContains("profile"));
            Assert.assertTrue(driver.getCurrentUrl().contains("profile"),
                    "Expected to land on profile page after successful login");
        } else {
            wait.until(ExpectedConditions.urlContains("login"));
            Assert.assertTrue(driver.getCurrentUrl().contains("login"),
                    "Expected to stay on login page after failed login");
        }
    }
}
