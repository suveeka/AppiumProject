package org.example.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import static org.example.drivers.DriverManager.getDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    public String getTitle() {
        return  getDriver().findElement(AppiumBy.className("android.widget.TextView")).getText();
    }

   private WebElement webViewMenu(){
        return getDriver().findElement(AppiumBy.accessibilityId("Webview"));
    }

    private WebElement swipeMenu(){
        return getDriver().findElement(AppiumBy.accessibilityId("Swipe"));
    }

    public WebViewPage navigateToWebView(){
        webViewMenu().click();
        return new WebViewPage();
    }

    private WebElement loginMenu(){
        return getDriver().findElement(AppiumBy.accessibilityId("Login"));
    }

    private WebElement signUpLink(){
        return getDriver().findElement(AppiumBy.accessibilityId("button-sign-up-container"));
    }

    public LoginSignUpPage navigateToLoginPage(){
        loginMenu().click();
        signUpLink().click();
        return new LoginSignUpPage();
    }

    public SwipePage navigateToSwipePage(){
        swipeMenu().click();
        return new SwipePage();
    }
}