package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.WebTablePage;

import java.time.Duration;

public class WebTableSteps extends BaseTest {
    private WebTablePage webTablePage;

    @Given("user is on web-table page")
    public void userIsOnWebTablePage() {
        driver.get("https://demoqa.com/webtables");
        webTablePage = new WebTablePage(driver);
    }

    @When("user click on ADD button")
    public void userClicksOnAddBtn() {
        webTablePage.clickAddBtn();
    }

    @When("user enters firstname {string} and lastname {string}")
    public void userEnterFirstAndLastName(String first, String last) {
        webTablePage.enterFirstName(first);
        webTablePage.enterLastName(last);
    }

    @When("user also enters E-mail {string}, age {string}, salary {string} and department {string}")
    public void userEnterRestOfTheDetails(String email, String age, String salary, String dept) {
        webTablePage.enterEmail(email);
        webTablePage.enterAge(age);
        webTablePage.enterSalary(salary);
        webTablePage.enterDepartment(dept);
    }

    @And("user clicks on the submit button")
    public void userClicksOnSubmitBtn() {
        webTablePage.userClicksSubmitBtn();
    }

    @Then("text data in web table should be verified: {string}, {string}, {string}, {string}, {string} and {string}")
    public void userVerifiesDataInTableRow(String fName, String lName, String age, String email, String salary, String dept) {
        webTablePage.verifyDataInTable(fName, lName, email, age, salary, dept);
    }

    @Then("user deleted the entered data")
    public void deleteUserData() {
        webTablePage.deleteData();
    }
}
