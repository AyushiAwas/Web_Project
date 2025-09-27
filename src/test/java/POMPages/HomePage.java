package POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BaseClass{
    //WebDriver driver;
    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='top-links']//span[text()='My Account']")
    WebElement myAccount;

    @FindBy(xpath = "//div[@id='top-links']//a[text()='Register']")
    WebElement registerBtn;

    @FindBy(xpath = "//div[@id='top-links']//a[text()='Login']")
    WebElement loginBtn;

    @FindBy(xpath = "//div[contains(@class,'btn-group')]//span[text()='Currency']")
    WebElement currency;

    private By linkWithText(String value) {
        return By.xpath("//li[contains(@class,'dropdown')]//a[contains(text(),'" + value + "')]");
    }

    private By getButtonWithText(String value) {
        return By.xpath("//button[contains(text(),'" + value + "')]");
    }

    @FindBy(xpath="//a[@class='dropdown-toggle'][text()='Desktops']")
    WebElement desktops;

    @FindBy(xpath = "//a[@class='dropdown-toggle'][text()='Laptops & Notebooks']")
    WebElement laptopNotebook;

    @FindBy(xpath = "//a[@class='dropdown-toggle'][text()='Components']")
    WebElement componentsDropDown;

    public void clickMyAccount()
    {
        myAccount.click();
    }
    public void clickRegisterBtn()
    {
        registerBtn.click();
    }

    public void clickLoginBtn()
    {
        loginBtn.click();
    }


    public void currencyDropDownVerify(List<String> currencyValues, WebDriver dr)
    {
        currency.click();
        for(String val:currencyValues) {
            dr.findElement(getButtonWithText(val)).isDisplayed();
        }
        currency.click();
    }


    public void desktopDropdownVerify(List<String> desktop, WebDriver dr) {
        desktops.click();
        for (String item : desktop) {
            dr.findElement(linkWithText(item)).isDisplayed();
        }
    }


    public void laptopNotebookDropVerify(List<String> laptopNotebooks,WebDriver dr) {
        laptopNotebook.click();
        for (String item : laptopNotebooks) {
            dr.findElement(linkWithText(item)).isDisplayed();
        }
    }

    public void verifycomponents(List<String> components,WebDriver dr) {
        componentsDropDown.click();
        for (String item : components) {
            dr.findElement(linkWithText(item)).isDisplayed();
        }
    }
}
