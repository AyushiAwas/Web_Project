package POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegistrationPage extends BaseClass{
    WebDriver driver;

    public RegistrationPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='firstname']")
    WebElement firstName;

    @FindBy(xpath = "//input[@name='lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@name='email']")
    WebElement emailId;

    @FindBy(xpath = "//input[@name='telephone']")
    WebElement telePhone;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//input[@name='confirm']")
    WebElement confirmPass;

    @FindBy(xpath = "//label[text()='No']")
    WebElement newsLetterNo;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement submit;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement privacyAgree;

    @FindBy(xpath = "//div[@id='content']/h1")
    WebElement registrationText;

    @FindBy(xpath = "//a[text()='Continue']")
    WebElement continueBtn;

    public void enterFirstName(String firstname)
    {
        firstName.sendKeys(firstname);
    }

    public void enterLastName(String lastname)
    {
            lastName.sendKeys(lastname);
    }
    public void enterEmail(String email)
    {
        emailId.sendKeys(email);
    }
    public void enterTelephone(String phone)
    {
        telePhone.sendKeys(phone);
    }
    public void enterPass(String pass)
    {
        password.sendKeys(pass);
    }

    public void confirmPass(String pass2)
    {
        confirmPass.sendKeys(pass2);
    }

    public void newsLetter()
    {
        newsLetterNo.click();
    }

    public void submitBtn()
    {
        submit.click();
    }

    public void privacy()
    {
        privacyAgree.click();
    }

    public String accountCreated()
    {
            return registrationText.getText();
    }

    public void verifyLinks(List<String> values, WebDriver dr)
    {
        for(int i=0;i<values.size();i++) {
             dr.findElement(By.xpath("//div[@class='list-group']//a['" + values.get(i) + "']"));
        }
    }

    public void clickContinue()
    {
        continueBtn.click();
    }

     public void fillRegistrationForm(String firstname, String lastname, String email, String phone, String pass)
     {
         firstName.sendKeys(firstname);
         lastName.sendKeys(lastname);
         emailId.sendKeys(email);
         telePhone.sendKeys(phone);
         password.sendKeys(pass);
         confirmPass.sendKeys(pass);
         newsLetter();
         privacy();
         submitBtn();

     }
}
