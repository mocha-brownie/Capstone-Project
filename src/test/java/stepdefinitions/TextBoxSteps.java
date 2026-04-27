package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.TextBoxPage;

import java.time.Duration;

public class TextBoxSteps extends BaseTest {
    private TextBoxPage textBoxPage;

    @Given("user is on text box page")
    public void userIsOnTextBoxPage() {
        driver.get("https://demoqa.com/text-box");
        textBoxPage = new TextBoxPage(driver);
    }

    @When("user enters fullname {string} and email {string}")
    public void fillUserNameAndEmail(String user, String userEmail) {
        textBoxPage.enterFullName(user);
        textBoxPage.enterUserEmail(userEmail);
    }

    @And("user also enters current Address {string} and permanent address {string}")
    public void fillAddresses(String currAdd, String permAdd) {
        textBoxPage.enterUserCurrAdd(currAdd);
        textBoxPage.enterUserPermAdd(permAdd);
    }

    @And("user clicks on submit button")
    public void clickOnSubmitBtn() {
        textBoxPage.clickSubmit();
    }

    @Then("text data result should be verified")
    public void textDataShouldAppear() {
        textBoxPage.verifyTextData();
    }
}
