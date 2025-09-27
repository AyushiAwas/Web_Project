package Utilities;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Utils {
   public static WebDriver driver;
    public Logger logger;
    public Properties prop;

    //before and after class should contain all the groups because we want to run this for all the tests
    @BeforeClass(groups = "sanity")
    @Parameters({"os","browser"})
    public void setup(String os, String br) throws IOException {
        FileReader file = new FileReader("./src//test//resources//config.properties");
        prop = new Properties();
        prop.load(file);
        logger = LogManager.getLogger(this.getClass());

        if(prop.getProperty("Execution_env").equalsIgnoreCase("remote"))
        {
            DesiredCapabilities dc = new DesiredCapabilities();

            if(os.equalsIgnoreCase("windows")) {
                dc.setPlatform(Platform.WIN11);
               // dc.setBrowserName(Browser.CHROME.browserName());
            } else if (os.equalsIgnoreCase("mac")) {
                dc.setPlatform(Platform.MAC);
            }
            else
            {
                System.out.println("no matching os");
            return;}

            switch (br.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    dc.setBrowserName("chrome");
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    dc.setBrowserName("firefox");
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    dc.setBrowserName("edge");
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    dc.setBrowserName("safari");
                    break;
                default:
                    System.out.println("Invalid browser");
                    return;
            }

                 driver = new RemoteWebDriver(new URL("http://192.168.1.3:4444/"),dc);

            }
        else if (prop.getProperty("Execution_env").equalsIgnoreCase("local")) {


        switch (br.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(); break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(); break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(); break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver(); break;
            default: System.out.println("Invalid browser");
            return;
    }}

        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();

    }

    @AfterClass(groups = "sanity")
    public void tearDown()
    {
        driver.quit();
    }

    public String randomString()
    {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber()
    {
        return RandomStringUtils.randomNumeric(10);
    }

    public String captureScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());


        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File newSS = screenshot.getScreenshotAs(OutputType.FILE);

        String targetPath=System.getProperty("user.dir")+"//screenshots//"+testName+"__"+ timestamp +".jpeg";
       File screen = new File(targetPath);
       newSS.renameTo(screen);
       return targetPath;
    }
}


