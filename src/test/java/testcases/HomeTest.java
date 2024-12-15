package testcases;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import extentlisteners.ExtentManager;
import org.secretsanta.pages.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomeTest extends BaseTest {

    @Test
    public void homeTest() {

        ExtentTest test = ExtentManager.test;

        HomePage homePage = new HomePage(driver);
        test.log(Status.INFO, "Navigate to home page");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(homePage.getPageUrl(), "http://192.168.0.107:8080/", "URL is not match");
        test.info("URL verified");

        softAssert.assertEquals(homePage.getPageTitle(), "Secret Santa", "Page title is incorrect");
        test.log(Status.INFO, "Title is verified");

        softAssert.assertTrue(homePage.getWelcomeText().contains("Cybage PALM"), "Test failed because spelling mismatch in Home page welcome text elements");
        test.log(Status.INFO, "Welcome text verified");

        softAssert.assertTrue(homePage.getText().contains("Click below to start!"), "Test failed because text mismatch in Home page elements");
        test.log(Status.INFO, "text verified");

        softAssert.assertTrue(homePage.isStartButtonClickable(), "Button is not clickable");
        test.log(Status.INFO, "Button is clickable");

        softAssert.assertAll();


    }
}
