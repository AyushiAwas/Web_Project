package TestCases;

import POMPages.*;
//import POMPages.RegistrationPage;
import Utilities.ConstantData;
import Utilities.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class AccountRegistrationTest extends Utils {


    HomePage hp ;
    Utils util = new Utils();
    RegistrationPage rp;
    ConstantData cons = new ConstantData();

    @Test(groups = {"sanity"})
    public void verifyRegistrationPage()
    {
        try {
            hp = new HomePage(driver);
            rp = new RegistrationPage(driver);
            hp.clickMyAccount();
            hp.clickRegisterBtn();
            logger.info("At registration page..");
            rp.fillRegistrationForm(util.randomString(), util.randomString(), util.randomString() + "@gmail.com", util.randomNumber(), util.randomString());
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
            Assert.assertEquals(rp.accountCreated(), cons.registrationSuccess);
            rp.verifyLinks(cons.values, driver);
            rp.clickContinue();
        }
        catch (Exception e)
        {
            logger.error("Test failed");
            logger.debug("Debug logs");
            Assert.fail();
        }
        logger.info("Test execution completed");
    }


    @Test(priority = 1)
    public void verifyHomePageDropDowns()
    {
        hp= new HomePage(driver);
        hp.currencyDropDownVerify(cons.currencyValues,driver);
        hp.desktopDropdownVerify(cons.desktop,driver);
        hp.laptopNotebookDropVerify(cons.laptopNotebooks,driver);
        hp.verifycomponents(cons.components,driver);

    }





}
