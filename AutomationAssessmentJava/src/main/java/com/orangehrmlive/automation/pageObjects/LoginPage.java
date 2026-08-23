package com.orangehrmlive.automation.pageObjects;

import com.orangehrmlive.automation.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private By userNameInputLocator = By.name("username");
    private By passwordInputLocator = By.name("password");
    private By submitButtonLocator = By.cssSelector(".orangehrm-login-button");
    String url = ConfigReader.get("login.url");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void visit(){
        super.visit(url);
    }

    public  void enterUserName(String userName){
        waitForElement(userNameInputLocator).sendKeys(userName);
    }

    public boolean isUsernameFieldVisible() {
        return isElementDisplayed(userNameInputLocator);
    }

    public  void enterPassword(String password){
        waitForElement(passwordInputLocator).sendKeys(password);
    }

    public boolean isPasswordFiledVisible() {
        return isElementDisplayed(passwordInputLocator);
    }

    public  void clickSubmitButton(){
        waitForElement(submitButtonLocator).click();
    }

    public boolean isSubmitButtonVisible() {
        return isElementDisplayed(submitButtonLocator);
    }

    public void executeLogin(String userName, String password){
        enterUserName(userName);
        enterPassword(password);
        clickSubmitButton();
    }
}
