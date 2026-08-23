package com.orangehrmlive.automation.tests;

import com.orangehrmlive.automation.pageObjects.AddEmployeePage;
import com.orangehrmlive.automation.pageObjects.EmployeeSearchPage;
import com.orangehrmlive.automation.utils.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddEmployeeTest extends BaseTest {

    @Test(
            groups = { "regression"},
            priority = 2
    )
    public void addNewEmployee() {

        login();

        EmployeeSearchPage employeeSearchPage =
                new EmployeeSearchPage(driver);

        employeeSearchPage.navigateToEmployeeList();

        AddEmployeePage addEmployeePage =
                new AddEmployeePage(driver);

        // Create employee

        addEmployeePage.clickAddEmployee();
        addEmployeePage.generateEmployeeData();
        addEmployeePage.enterEmployeeDetails();
        addEmployeePage.clickSave();

        Assert.assertTrue(
                addEmployeePage.isEmployeeDetailsDisplayed(),
                "Employee details page is not displayed"
        );

        String employeeId = addEmployeePage.getEmployeeId();

        Assert.assertFalse(
                employeeId.isEmpty(),
                "Employee ID was not generated"
        );

        System.out.println("Employee ID: " + employeeId);


        // Search and validate employee

        employeeSearchPage.navigateToEmployeeList();

        employeeSearchPage.setEmployeeName(
                addEmployeePage.getFirstName() + " " +
                        addEmployeePage.getMiddleName()
        );

        employeeSearchPage.setLastName(
                addEmployeePage.getLastName()
        );

        employeeSearchPage.enterEmployeeName();
        employeeSearchPage.clickSearch();

        Assert.assertTrue(
                employeeSearchPage.isEmployeeDisplayed(),
                "Created employee was not found"
        );

        employeeSearchPage.openEmployee();

        Assert.assertTrue(
                employeeSearchPage.isEmployeeDetailsDisplayed(),
                "Employee details page is not displayed"
        );

        Assert.assertEquals(
                employeeSearchPage.getEmployeeDisplayedName(),
                addEmployeePage.getFirstName() + " " +
                        addEmployeePage.getLastName(),
                "Employee name does not match"
        );

        Assert.assertEquals(
                employeeSearchPage.getEmployeeId(),
                employeeId,
                "Employee ID does not match"
        );


        // Update first name

        String updatedFirstName = TestDataGenerator.generateUniqueLastName();

        employeeSearchPage.updateFirstName(updatedFirstName);
        employeeSearchPage.clickSave();

        Assert.assertTrue(
                employeeSearchPage.isEmployeeDetailsDisplayed(),
                "Employee details page is not displayed after update"
        );

        String expectedUpdatedName =
                updatedFirstName + " " + addEmployeePage.getLastName();

        Assert.assertTrue(
                employeeSearchPage.waitUntilNameDisplayed(expectedUpdatedName),
                "Employee name did not update to expected value in time"
        );

        Assert.assertEquals(
                employeeSearchPage.getEmployeeDisplayedName(),
                expectedUpdatedName,
                "Employee name was not updated correctly"
        );


        // Delete employee

        employeeSearchPage.navigateToEmployeeList();

        employeeSearchPage.setEmployeeName(
                updatedFirstName + " " +
                        addEmployeePage.getMiddleName()
        );

        employeeSearchPage.setLastName(
                addEmployeePage.getLastName()
        );

        employeeSearchPage.enterEmployeeName();
        employeeSearchPage.clickSearch();

        Assert.assertTrue(
                employeeSearchPage.isEmployeeDisplayed(),
                "Updated employee was not found before deletion"
        );

        employeeSearchPage.deleteEmployee();

        Assert.assertTrue(
                employeeSearchPage.isDeleteSuccessToastDisplayed(),
                "Employee deletion success message is not displayed"
        );

        Assert.assertTrue(
                employeeSearchPage.isEmployeeDeleted(),
                "Employee was not deleted"
        );
    }
}