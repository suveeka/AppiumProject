package org.example.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

import static org.example.drivers.DriverManager.getDriver;
public class SwipePage {
    final String textViewOption = "EXTENDABLE";
    public boolean getTextView(){
        if (getDriver().findElements(AppiumBy.className("android.widget.TextView")).get(3).getText().equals(textViewOption)){
           return true;
        }
        else{
          return  false;
        }
    }
    /*String selector = "new UiSelector().className(\"android.widget.Button\").text(textViewOption)";
            return getDriver().findElement(AppiumBy.androidUIAutomator(selector));*/

    public boolean isElementExists(WebElement webElement){
        boolean b = false;
                if (webElement.getSize()!= null)
                    b=true;
        return b;
    }

    public boolean waitForElement(int timeInSeconds, WebElement webElement){
        try{
            for(int i=0;i<timeInSeconds;i++){
                if(isElementExists(webElement))
                    return true;
                Thread.sleep(1000);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    public void swipeScreenHorizontally(){
        WebElement ele01 = getDriver().findElements(AppiumBy.className("android.view.ViewGroup")).get(3);
        if (waitForElement(3,ele01)){
        int centerY = ele01.getRect().x + (ele01.getSize().height/2);
        double startX = ele01.getRect().y + (ele01.getSize().width * 0.9);
        double endX = ele01.getRect().y + (ele01.getSize().width * 0.4);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence swipe = new Sequence(finger,1);
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),(int)startX,centerY));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),(int)endX,centerY));
        swipe.addAction(finger.createPointerUp(0));
        getDriver().perform(Arrays.asList(swipe));
        }
    }

    public void swipeScreenVertically(){
        WebElement ele01 = getDriver().findElements(AppiumBy.className("android.view.ViewGroup")).get(7);
        int centerX = ele01.getRect().x + (ele01.getSize().width/2);
        double startY = ele01.getRect().y + (ele01.getSize().height * 0.9);
        double endY = ele01.getRect().y + (ele01.getSize().height * 0.7);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence swipe = new Sequence(finger,1);
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),centerX,(int)startY));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),centerX, (int)endY));
        swipe.addAction(finger.createPointerUp(0));
        getDriver().perform(Arrays.asList(swipe));

    }

    public void swipeTextView(){
        while(!getTextView()){
            swipeScreenHorizontally();
        }
        swipeScreenVertically();

    }

    public WebElement getRobotImage(){
        return getDriver().findElement(AppiumBy.accessibilityId("WebdriverIO logo"));
    }

    private void zoomRobotImage(){

        /*PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Dimension size = getDriver().manage().window().getSize();
        Point source = new Point(size.getWidth(), size.getHeight());

        Sequence pinchAndZoom1 = new Sequence(finger, 0);
        pinchAndZoom1.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), source.x / 2, source.y / 2));
        pinchAndZoom1.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        pinchAndZoom1.addAction(new Pause(finger, Duration.ofMillis(100)));
        pinchAndZoom1.addAction(finger.createPointerMove(Duration.ofMillis(600),
                PointerInput.Origin.viewport(), source.x / 3, source.y / 3));
        pinchAndZoom1.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));*/
    }
}
