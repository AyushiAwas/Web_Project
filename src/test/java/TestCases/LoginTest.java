package TestCases;

import POMPages.HomePage;
import POMPages.LoginPage;
import POMPages.RegistrationPage;
import Utilities.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.swing.text.Utilities;
import java.time.Duration;

public class LoginTest extends Utils {


    HomePage hp ;
    Utils util = new Utils();
    RegistrationPage rp;
    LoginPage lp;
    ConstantData cons = new ConstantData();


    @Test (priority = 0, dataProvider = "data" , dataProviderClass = DataProviders.class)
    public void login(String email, String pass, String result)
    {
        try {
            hp = new HomePage(driver);
            rp = new RegistrationPage(driver);
            lp = new LoginPage(driver);
            hp.clickMyAccount();
            hp.clickLoginBtn();
            logger.info("At login page..");

            //correct name - correct pass --> login success--> valid <--> logout
            //incorrect name - incorrect pass --> login fail--> valid
            //incorrect name - correct pass --> login success--> invalid  <--> logout
            //correct name - correct pass --> login fail--> invalid

            lp.sendInput(driver,email,pass);
            lp.clickLoginContinueBtn();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
            if(result.equalsIgnoreCase("valid")) {
                if (lp.myAccountDisplay()) {
                    logger.info("logout..credentials are valid");
                    lp.clickMyAccountDropDown();
                    lp.clickLogoutBtn();
                    Assert.assertTrue(true);
                } else {
                    logger.info("test case failed");
                    Assert.assertTrue(false);
                }
            } else if (result.equalsIgnoreCase("invalid")) {
                if (lp.myAccountDisplay()) {

                    lp.clickMyAccountDropDown();
                    lp.clickLogoutBtn();
                    Assert.assertTrue(false);
                } else {
                    logger.info("test case passed");
                    Assert.assertTrue(true);
                }
                
            }

        }
        catch (Exception e)
        {
            logger.error("Test failed");
            logger.debug("Debug logs");
            Assert.fail();
        }
        logger.info("Test execution completed");
    }
}
