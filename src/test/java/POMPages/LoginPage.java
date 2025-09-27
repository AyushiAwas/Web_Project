package POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BaseClass{
    public LoginPage(WebDriver driver)
    {
        super(driver);
    }



    private By inputField(String value) {
        return By.xpath("//input[@name='" + value + "']");
    }

    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginContinueBtn;

    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement myAccount;

    @FindBy(xpath = "//ul[@class='list-inline']//span[text()='My Account']")
    WebElement myAccountDropDown;

    @FindBy(xpath = "//ul[@class='list-inline']//a[text()='Logout']")
    WebElement logoutBtn;


    public void sendInput(WebDriver dr,String email, String pass)
    {
        dr.findElement(inputField("email")).sendKeys(email);
        dr.findElement(inputField("password")).sendKeys(pass);
    }

    public void clickMyAccountDropDown()
    {
        myAccountDropDown.click();
    }

    public void clickLogoutBtn()
    {
        logoutBtn.click();
    }

    public boolean myAccountDisplay()
    {
        try
        {
            myAccount.isDisplayed();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public void clickLoginContinueBtn()
    {
        loginContinueBtn.click();
    }

}
