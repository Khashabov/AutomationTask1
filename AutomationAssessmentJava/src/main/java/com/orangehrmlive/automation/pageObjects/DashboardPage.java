package com.orangehrmlive.automation.pageObjects;

import com.orangehrmlive.automation.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private final By dashboardHeaderLocator = By.cssSelector(".oxd-topbar-header-breadcrumb-module");
    private final By userInfoLocator = By.cssSelector(".oxd-userdropdown-name");
    private final By searchInputLocator = By.cssSelector("input[placeholder='Search']");
    private final By pim = By.cssSelector("a[href*='/pim/viewPimModule']");

    public DashboardPage(WebDriver driver){
        super(driver);
    }

    public boolean isDashboardDisplayed() {
        return isElementDisplayed(dashboardHeaderLocator);
    }


    public boolean isUserInfoDisplayed() {
        return isElementDisplayed(userInfoLocator);
    }
}
