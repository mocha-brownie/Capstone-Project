package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class TextBoxPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators defined as private final
    private final By fullName = By.id("userName");
    private final By email = By.id("userEmail");
    private final By currentAdd = By.id("currentAddress");
    private final By permanentAdd = By.id("permanentAddress");
    private final By submitBtn = By.id("submit");

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterFullName(String userNameText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fullName));
        driver.findElement(fullName).sendKeys(userNameText);
    }

    public void enterUserEmail(String emailText) {
        driver.findElement(email).sendKeys(emailText);
    }

    public void enterUserCurrAdd(String currentAddressText) {
        driver.findElement(currentAdd).sendKeys(currentAddressText);
    }

    public void enterUserPermAdd(String permanentAddressText) {
        driver.findElement(permanentAdd).sendKeys(permanentAddressText);
    }

    public void clickSubmit() {
        driver.findElement(submitBtn).click();
    }

    public void verifyTextData() {
        // Wait for the first output element to ensure the result box has loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p#name")));

        // Extract values from the output section using the helper method
        String name = getExtractedText("p#name");
        String extractedEmail = getExtractedText("p#email");
        String currentAddress = getExtractedText("p#currentAddress");
        String permanentAddress = getExtractedText("p#permanentAddress");

        // Get the expected values directly from the input fields
        String expectedName = driver.findElement(fullName).getAttribute("value");
        String expectedEmail = driver.findElement(email).getAttribute("value");
        String expectedCurrentAdd = driver.findElement(currentAdd).getAttribute("value");
        String expectedPermanentAdd = driver.findElement(permanentAdd).getAttribute("value");

        // Verify output matches input
        Assert.assertEquals(name, expectedName);
        Assert.assertEquals(extractedEmail, expectedEmail);
        Assert.assertEquals(currentAddress, expectedCurrentAdd);
        Assert.assertEquals(permanentAddress, expectedPermanentAdd);
    }

    // Helper method to reduce code duplication for splitting text
    private String getExtractedText(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector)).getText().split(":")[1].trim();
    }
}