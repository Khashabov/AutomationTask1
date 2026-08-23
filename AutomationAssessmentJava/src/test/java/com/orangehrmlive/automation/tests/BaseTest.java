package com.orangehrmlive.automation.tests;

import com.orangehrmlive.automation.config.ConfigReader;
import com.orangehrmlive.automation.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("========== SETUP RUNNING ==========");

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get(ConfigReader.get("login.url"));
    }
    protected void login() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.executeLogin(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}