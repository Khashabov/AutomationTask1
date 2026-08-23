package com.orangehrmlive.automation.tests;

import com.orangehrmlive.automation.config.ConfigReader;
import com.orangehrmlive.automation.pageObjects.EmployeeSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeSearchTest extends BaseTest {

    @Test(
            groups = { "regression"},
            priority = 3
    )
    public void searchForEmployee() {

        login();

        EmployeeSearchPage employeeSearchPage = new EmployeeSearchPage(driver);

        employeeSearchPage.navigateToEmployeeList();
        employeeSearchPage.enterEmployeeName();
        employeeSearchPage.clickSearch();

        Assert.assertTrue(employeeSearchPage.areSearchResultsDisplayed(),
                "Search results are not displayed"
        );

        Assert.assertTrue(employeeSearchPage.isEmployeeDisplayed(),
                "Expected employee was not found in search results"
        );

        String employeeRowData = employeeSearchPage.getEmployeeRowData();

        Assert.assertTrue(employeeRowData.contains(ConfigReader.get("employeeName")),
                "Returned employee does not match the searched employee"
        );

        Assert.assertTrue(employeeRowData.contains(ConfigReader.get("employeeId")),
                "Employee ID is incorrect"
        );

        Assert.assertTrue(employeeRowData.contains(ConfigReader.get("employeeLastName")),
                "Employee last name is incorrect"
        );
    }
}