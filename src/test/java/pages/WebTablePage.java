package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class WebTablePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");
    private final By age = By.id("age");
    private final By salary = By.id("salary");
    private final By department = By.id("department");
    private final By addBtn = By.id("addNewRecordButton");
    private final By submitBtn = By.id("submit");
    private final By deleteBtn = By.id("delete-record-4");


    public WebTablePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickAddBtn() {
        WebElement addElement = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("addNewRecordButton")));
        addElement.click();
    }

    public void enterFirstName(String str) {
        driver.findElement(firstName).sendKeys(str);
    }

    public void enterLastName(String str) {
        driver.findElement(lastName).sendKeys(str);
    }

    public void enterEmail(String str) {
        driver.findElement(email).sendKeys(str);
    }

    public void enterAge(String str) {
        driver.findElement(age).sendKeys(str);
    }

    public void enterSalary(String str) {
        // driver.findElement(salary).sendKeys(str);
        String formattedSalary = str.contains(".") ? str.substring(0, str.indexOf(".")) : str;
        driver.findElement(salary).sendKeys(formattedSalary);
    }

    public void enterDepartment(String str) {
        driver.findElement(department).sendKeys(str);
    }

    public void userClicksSubmitBtn() {
        driver.findElement(submitBtn).click();
    }

    public void verifyDataInTable(String expectedFName, String expectedLName, String expectedEmail, String expectedAge, String expectedSalary, String expectedDept) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until 4th row is visible
        WebElement fourthRow = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='rt-tbody']/div[4]/div/div")));

        // Get all columns of 4th row
        WebElement fName = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]/div/div/div[1]"));
        WebElement lName = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]/div/div/div[2]"));
        WebElement ageVal = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]/div/div/div[3]"));
        WebElement emailVal = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]/div/div/div[4]"));
        WebElement salaryVal = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]/div/div/div[5]"));
        WebElement deptVal = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]/div/div/div[6]"));

        // Assertions
        Assert.assertEquals(fName.getText(), expectedFName);
        Assert.assertEquals(lName.getText(), expectedLName);
        Assert.assertEquals(ageVal.getText(), expectedAge);
        Assert.assertEquals(emailVal.getText(), expectedEmail);
        Assert.assertEquals(salaryVal.getText(), expectedSalary);
        Assert.assertEquals(deptVal.getText(), expectedDept);
    }

    public void deleteData() {
        driver.findElement(deleteBtn).click();
    }
}
