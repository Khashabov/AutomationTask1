package com.orangehrmlive.automation.pageObjects;

import com.orangehrmlive.automation.utils.TestDataGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage extends BasePage {

    private final By addEmployeeMenu = By.xpath("//a[normalize-space()='Add Employee']");

    private final By firstNameInput = By.name("firstName");

    private final By middleNameInput = By.name("middleName");

    private final By lastNameInput = By.name("lastName");

    private final By saveButton = By.xpath("//button[@type='submit' and normalize-space()='Save']");

    private final By employeeIdInput = By.xpath("//label[normalize-space()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input");

    private final By employeeDetailsHeader = By.xpath("//h6[normalize-space()='Personal Details']");

    private String firstName;
    private String middleName;
    private String lastName;

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public void clickAddEmployee() {
        waitForElement(addEmployeeMenu).click();
    }

    public void generateEmployeeData() {
        firstName = "Automation";
        middleName = "Test";
        lastName = TestDataGenerator.generateUniqueLastName();
    }

    public void enterEmployeeDetails() {
        waitForElement(firstNameInput).sendKeys(firstName);
        waitForElement(middleNameInput).sendKeys(middleName);
        waitForElement(lastNameInput).sendKeys(lastName);
    }

    public void clickSave() {
        waitForElement(saveButton).click();
    }

    public boolean isEmployeeDetailsDisplayed() {
        return isElementDisplayed(employeeDetailsHeader);
    }

    public String getEmployeeId() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        return wait.until(driver -> {
            String value = driver.findElement(employeeIdInput)
                    .getAttribute("value");

            return value != null && !value.isEmpty() ? value : null;
        });
    }

    public String getEmployeeNumberFromUrl() {
        String url = driver.getCurrentUrl();
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullEmployeeName() {
        return firstName + " " + middleName + " " + lastName;
    }
}