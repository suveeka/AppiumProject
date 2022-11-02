package org.example.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;
import java.util.Objects;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;

public class DriverManager {

    private static final ThreadLocal<AppiumDriver>   DRIVER   = new ThreadLocal<> ();
    private static AppiumDriverLocalService service;
    private static final String APP_PATH = System.getProperty (
            "user.dir") + "\\src\\test\\resources\\wdio_testApp.apk";

    public static void createAndroidDriver () {
        startServer ();
        DRIVER.set (new AndroidDriver(service.getUrl (), setCapabilities ()));
        setupDriverTimeouts ();
    }

    public static <D extends AppiumDriver> D getDriver () {
        return (D) DriverManager.DRIVER.get ();
    }

    public static void quitSession () {
        if (null != DRIVER.get ()) {
            getDriver ().quit ();
            DRIVER.remove ();
            stopServer ();
        }
    }


    private DriverManager () {
    }

    private static void setupDriverTimeouts () {
        getDriver ().manage ()
                .timeouts ()
                .implicitlyWait (Duration.ofSeconds (30));
    }

    private static DesiredCapabilities setCapabilities () {
        DesiredCapabilities capabilities = new DesiredCapabilities ();
        capabilities.setCapability (MobileCapabilityType.DEVICE_NAME, "Pixel_5_API_30");
        capabilities.setCapability (MobileCapabilityType.APP, APP_PATH);
        capabilities.setCapability ("noReset", false);
        capabilities.setCapability (MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability("appium:appPackage","com.wdiodemoapp");
        capabilities.setCapability("appium:appActivity","com.wdiodemoapp.MainActivity");
        capabilities.setCapability("newCommandTimeout",60);
        return capabilities;
    }

    public static void startServer () {
        AppiumServiceBuilder builder = new AppiumServiceBuilder ();
        builder.withIPAddress ("127.0.0.1")
                .usingPort (4723)
                .withArgument (BASEPATH, "/wd/hub")
                .withArgument (GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument (GeneralServerFlag.LOG_LEVEL, "debug");

        service = AppiumDriverLocalService.buildService (builder);
        service.start ();
    }

    public static void stopServer () {
        service.stop ();
    }
}
