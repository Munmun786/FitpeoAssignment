package pageobject;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RevenueCalculatorPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // Locators
    private By revenueCalculatorLink = By.xpath("//div[text()='Revenue Calculator']");
    private By sliderBar = By.xpath("//input[@type='range']");
    private By inputField = By.xpath("//input[@type='number']");
    private By totalReimbursement = By.xpath("//p[text()='Total Recurring Reimbursement for all Patients Per Month:']");
    private String cptCodeLocator = "//div[@class='MuiBox-root css-1eynrej']/descendant::span[text()='%s']";

    // Constructor
    public RevenueCalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    // Page Actions

    // Navigate to Revenue Calculator
    public void navigateToRevenueCalculator() {
        WebElement link = driver.findElement(revenueCalculatorLink);
        link.click();
    }

    // Scroll to the slider bar
    public void scrollToSlider() {
        WebElement slider = driver.findElement(sliderBar);
        actions.moveToElement(slider).perform();
    }

    // Adjust the slider to a specific value (using drag and drop)
    public void adjustSlider(int xOffset) {
        WebElement slider = driver.findElement(sliderBar);
        actions.dragAndDropBy(slider, xOffset, 0).perform();
    }

    // Update the input field with a specific value
    public void setInputField(String value) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputField));
        input.clear();
        input.sendKeys(value);
    }

    // Retrieve the current value of the input field
    public String getInputFieldValue() {
        WebElement input = driver.findElement(inputField);
        return input.getAttribute("value");
    }

    // Select a CPT code
    public void selectCPTCode(String cptCode) {
        WebElement cptBox = driver.findElement(By.xpath(String.format(cptCodeLocator, cptCode)));
        actions.moveToElement(cptBox).click().perform();
    }

    // Validate total reimbursement text
    public String getTotalReimbursementText() {
        WebElement reimbursement = driver.findElement(totalReimbursement);
        return reimbursement.getText();
    }
}

