package com.orangehrmlive.automation.tests;

import com.orangehrmlive.automation.config.ConfigReader;
import com.orangehrmlive.automation.pageObjects.DashboardPage;
import com.orangehrmlive.automation.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(groups = {"smoke", "regression"}, priority = 1)
    public void validLogin() {

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(loginPage.isUsernameFieldVisible(),
                "Username field is not visible"
        );

        Assert.assertTrue(loginPage.isPasswordFiledVisible(),
                "Password field is not visible"
        );

        Assert.assertTrue(loginPage.isSubmitButtonVisible(),
                "Submit button is not visible"
        );

        loginPage.executeLogin(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );

        Assert.assertTrue(dashboardPage.isDashboardDisplayed(),
                "Dashboard is not displayed after login"
        );

        Assert.assertTrue(dashboardPage.isUserInfoDisplayed(),
                "User information is not displayed"
        );
    }
}