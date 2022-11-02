import org.example.pages.LoginSignUpPage;
import org.example.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.example.drivers.DriverManager.createAndroidDriver;
import static org.example.drivers.DriverManager.quitSession;

public class SignUpTest {
    private LoginSignUpPage loginSignUpPage;
    @BeforeClass
    public void testSetUp(){
        createAndroidDriver();
        MainPage mainPage = new MainPage();
        loginSignUpPage = mainPage.navigateToLoginPage();
    }
    @DataProvider(name = "userCredentials")
    public Object[][] userCredentials()
    {
        return new Object[][]{
                { "test_email1@gmail.com", "test_password1"},
                { "test_email2@gmail.com", "test_password2" },
                { "test_email3@gmail.com", "test_password3" },
                { "test_email4@gmail.com", "test_password4" },
                { "test_email5@gmail.com", "test_password5" }
        };

    }

    @Test(dataProvider = "userCredentials")
    public void scenarioThree(String emailId, String password){
        loginSignUpPage.signUp(emailId,password);
        Assert.assertEquals(loginSignUpPage.getSuccessMessage(),"You successfully signed up!");
        loginSignUpPage.closeSuccessMessage();
    }

    @AfterClass
    public void tearDown(){
        quitSession();
    }
}
