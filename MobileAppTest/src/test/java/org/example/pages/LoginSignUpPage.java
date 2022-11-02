package org.example.pages;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.example.drivers.DriverManager.getDriver;
public class LoginSignUpPage {

    private WebElement emailField(){
        return getDriver().findElement(AppiumBy.accessibilityId("input-email"));
    }

    private WebElement passwordField(){
        return getDriver().findElement(AppiumBy.accessibilityId("input-password"));
    }

    private WebElement confirmPasswordField(){
        return getDriver().findElement(AppiumBy.accessibilityId("input-repeat-password"));
    }

    private WebElement signUpButton(){
        return getDriver().findElement(AppiumBy.accessibilityId("button-SIGN UP"));
    }

    @Test(dataProvider = "userCredentials")
    public void signUp(String emailId,String password)
    {
        emailField().click();
        emailField().clear();
        emailField().sendKeys(emailId);

        passwordField().click();
        passwordField().clear();
        passwordField().sendKeys(password);

        confirmPasswordField().click();
        confirmPasswordField().clear();
        confirmPasswordField().sendKeys(password);

        signUpButton().click();
    }

    public String getSuccessMessage(){
        return getDriver().findElement(AppiumBy.id("android:id/message")).getText();
    }

    private WebElement okButton(){
        return getDriver().findElement(AppiumBy.id("android:id/button1"));
    }

    public void closeSuccessMessage(){
        okButton().click();
    }
}

