package org.example.pages;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

import static org.example.drivers.DriverManager.getDriver;

public class WebViewPage {
    private WebElement getStartedButton(){
        return getDriver().findElement(AppiumBy.accessibilityId("Get Started"));
    }
    private WebElement onThisPageDropDown(){
        String selector = "new UiSelector().className(\"android.widget.Button\").text(\"On this page\")";
        return getDriver().findElement(AppiumBy.androidUIAutomator(selector));
    }

    public void swipeScreen(){
        WebElement ele01 = getDriver().findElement(AppiumBy.id("android:id/content"));
        int centerX = ele01.getRect().x + (ele01.getSize().width/2);
        double startY = ele01.getRect().y + (ele01.getSize().height * 0.9);
        double endY = ele01.getRect().y + (ele01.getSize().height * 0.4);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence swipe = new Sequence(finger,1);
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),centerX,(int)startY));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),centerX, (int)endY));
        swipe.addAction(finger.createPointerUp(0));
        getDriver().perform(Arrays.asList(swipe));
    }

  /*private WebElement closeStickyMessage(){
        return getDriver().findElement(AppiumBy.className("android.widget.Button"));
    }*/
    public void openDropDown(){
        swipeScreen();
        //closeStickyMessage().click();
        getStartedButton().click();
        onThisPageDropDown().click();

    }
}
