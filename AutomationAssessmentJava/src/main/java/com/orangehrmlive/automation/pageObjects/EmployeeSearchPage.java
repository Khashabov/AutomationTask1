package com.orangehrmlive.automation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeeSearchPage extends BasePage {

    private final By pimMenu =
            By.cssSelector("a.oxd-main-menu-item[href*='/pim/viewPimModule']");

    private final By employeeListMenu =
            By.xpath("//a[normalize-space()='Employee List']");

    private final By employeeNameInput =
            By.cssSelector("input[placeholder='Type for hints...']");

    private final By searchButton =
            By.cssSelector("button[type='submit']");

    private final By searchResults =
            By.cssSelector("div[role='row']");

    private final By personalDetails =
            By.xpath("//h6[normalize-space()='Personal Details']");

    private final By employeeHeaderName =
            By.cssSelector(".orangehrm-edit-employee-name h6");

    private final By employeeIdInput =
            By.xpath("//label[normalize-space()='Employee Id']/following::input[1]");

    private final By firstNameInput =
            By.name("firstName");

    private final By saveButton =
            By.xpath("//button[@type='submit' and normalize-space()='Save']");

    private final By deleteConfirmationButton =
            By.xpath("//button[normalize-space()='Yes, Delete']");

    private final By successToast =
            By.cssSelector(".oxd-toast--success");

    private String employeeName;
    private String lastName;

    public EmployeeSearchPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToEmployeeList() {
        waitForElement(pimMenu).click();
        waitForElement(employeeListMenu).click();
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void enterEmployeeName() {
        waitForElement(employeeNameInput).sendKeys(employeeName);
    }

    public void clickSearch() {
        waitForElement(searchButton).click();
    }

    public boolean areSearchResultsDisplayed() {
        return waitForElement(searchResults).isDisplayed();
    }

    public boolean isEmployeeDisplayed() {

        By employeeRowLocator = By.xpath(
                "//div[@role='row']" +
                        "[.//div[contains(normalize-space(),'" + employeeName + "')]]"
        );

        return waitForElement(employeeRowLocator).isDisplayed();
    }

    public void openEmployee() {

        By employeeRowLocator = By.xpath(
                "//div[@role='row']" +
                        "[.//div[contains(normalize-space(),'" + employeeName + "')]]"
        );

        waitForElement(employeeRowLocator).click();
    }

    public String getEmployeeRowData() {

        By employeeRowLocator = By.xpath(
                "//div[@role='row']" +
                        "[.//div[contains(normalize-space(),'" + employeeName + "')]]"
        );

        return waitForElement(employeeRowLocator).getText();
    }

    public boolean isEmployeeDetailsDisplayed() {
        return isElementDisplayed(personalDetails);
    }

    public String getEmployeeDisplayedName() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(driver -> {

            String name =
                    driver.findElement(employeeHeaderName).getText();

            return name != null && !name.trim().isEmpty()
                    ? name
                    : null;
        });
    }
    public boolean waitUntilNameDisplayed(String expectedName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            return wait.until(driver -> {
                String name = driver.findElement(employeeHeaderName).getText();
                return name != null && name.trim().equals(expectedName);
            });
        } catch (TimeoutException e) {
            String actual = driver.findElement(employeeHeaderName).getText();
            System.out.println("Expected: [" + expectedName + "]");
            System.out.println("Actual  : [" + actual + "]");
            throw e;
        }
    }

    public String getEmployeeId() {
        return waitForElement(employeeIdInput).getAttribute("value");
    }

    public void updateFirstName(String newFirstName) {

        waitForElement(firstNameInput).clear();

        waitForElement(firstNameInput).sendKeys(newFirstName);
    }

    public void clickSave() {
        waitForElement(saveButton).click();
    }

    public void deleteEmployee() {

        By deleteButton = By.xpath(
                "//div[@role='row']" +
                        "[.//div[contains(normalize-space(),'" + employeeName + "')]]" +
                        "//i[contains(@class,'bi-trash')]/ancestor::button"
        );

        waitForElement(deleteButton).click();

        waitForElement(deleteConfirmationButton).click();
    }

    public boolean isDeleteSuccessToastDisplayed() {

        return waitForElement(successToast)
                .getText()
                .contains("Successfully Deleted");
    }

    public boolean isEmployeeDeleted() {

        By employeeRowLocator = By.xpath(
                "//div[@role='row']" +
                        "[.//div[contains(normalize-space(),'" + employeeName + "')]]"
        );

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        employeeRowLocator
                )
        );
    }
}