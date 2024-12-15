package base;

import extentlisteners.ExtentManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import static extentlisteners.ExtentManager.test;

@Listeners(ExtentManager.class)
public class BaseTest {


    public Logger log = Logger.getLogger(BaseTest.class);
    public WebDriver driver;
    public WebDriverWait wait;
    public Properties config = new Properties();

    @BeforeMethod
    public void setUp() {

        PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
        log.info("Test execution started");

        try {
            FileInputStream fis = new FileInputStream("./src/test/resources/properties/config.properties");
            config.load(fis);
            log.info("Config property loaded");
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }

        if (config.getProperty("browser").equals("chrome")) {
            driver = new ChromeDriver();
            log.info("Chrome driver loaded");
        } else if (config.getProperty("browser").equals("firefox")) {
            driver = new FirefoxDriver();
            log.info("Firefox driver loaded");
        } else if (config.getProperty("browser").equals("ie")) {
            driver = new InternetExplorerDriver();
            log.info("Internet explorer driver loaded");
        } else if (config.getProperty("browser").equals("edge")) {
            driver = new EdgeDriver();
            log.info("Edge driver loaded");
        }

        driver.get(config.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(config.getProperty("implicit.wait"))));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        log.info("Test execution ended");
    }

    public void captureScreenshot(ITestResult result) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = "target/screenshots/" + result.getName() + "_" + timeStamp + ".jpg";

        try {
            FileUtils.copyFile(src, new File(screenshotPath));
            test.addScreenCaptureFromPath(screenshotPath, "Test failure screenshot");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
