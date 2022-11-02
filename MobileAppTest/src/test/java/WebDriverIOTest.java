import org.example.pages.LoginSignUpPage;
import org.example.pages.MainPage;
import org.example.pages.WebViewPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.example.drivers.DriverManager.createAndroidDriver;
import static org.example.drivers.DriverManager.quitSession;

public class WebDriverIOTest {
    private MainPage mainPage;

    @BeforeClass
    public void testSetUp(){
        createAndroidDriver();
        mainPage = new MainPage();
    }

    @Test
    public void scenarioOne(){
        Assert.assertEquals(mainPage.getTitle(),"WEBDRIVER");
    }

    @Test
    public void scenarioTwo(){
        mainPage.navigateToWebView().openDropDown();
    }

    @Test
    public void scenarioFour(){
        mainPage.navigateToSwipePage().swipeTextView();
    }

    @AfterClass
    public void tearDown(){
        quitSession();
    }
}
