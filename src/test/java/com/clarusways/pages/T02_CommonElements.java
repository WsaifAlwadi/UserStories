package com.clarusways.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.clarusways.utilities.DriverManager;

public class T02_CommonElements {

    public T02_CommonElements() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }
    @FindBy(tagName = "body")
    public WebElement pageBody;

    @FindBy(tagName = "h1")
    public WebElement pageHeader;

    @FindBy(className = "alert-danger")
    public WebElement errorAlert;

    @FindBy(className = "alert-warning")
    public WebElement warningAlert;

    @FindBy(className = "alert-success")
    public WebElement successAlert;

    @FindBy(className = "container")
    public WebElement mainContainer;


    public String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public String getCurrentUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    public boolean isSuccessAlertDisplayed() {
        try {
            return successAlert.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorAlertDisplayed() {
        try {
            return errorAlert.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessage() {
        return isSuccessAlertDisplayed() ? successAlert.getText() : "";
    }

    public String getErrorMessage() {
        return isErrorAlertDisplayed() ? errorAlert.getText() : "";
    }
}