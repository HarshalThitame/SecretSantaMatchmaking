package testcases;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import extentlisteners.ExtentManager;
import org.secretsanta.pages.AddPeoplePage;
import org.secretsanta.pages.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AddPeopleTest extends BaseTest {


    @Test
    public void verifyElement() {
        ExtentTest test = ExtentManager.test;

        HomePage homePage = new HomePage(driver);
        AddPeoplePage addPeoplePage = homePage.gotoAddPeoplePage();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(addPeoplePage.getPageUrl(), "http://192.168.0.107:8080/addpeople", "URL not matched");
        test.log(Status.INFO, "URL Verified");

        softAssert.assertEquals(addPeoplePage.getPageTitle(), "Secret Santa", "Title not verified");
        test.log(Status.INFO, "Title Verified");

        softAssert.assertTrue(addPeoplePage.getEnterPeopleText().contains("people's names"), "Test failed  enter people text not matched ");
        test.log(Status.INFO, "Text verified");

        softAssert.assertTrue(addPeoplePage.isAddPersonButtonClickable(), "Test failed  add person button not clickable");
        test.log(Status.INFO, "Add person button verified");


        softAssert.assertAll();

    }

    @Test(dependsOnMethods = "verifyElement")
    public void addPeople() {
        ExtentTest test = ExtentManager.test;

        HomePage homePage = new HomePage(driver);
        AddPeoplePage addPeoplePage = homePage.gotoAddPeoplePage();


        SoftAssert softAssert = new SoftAssert();
        String peopleName = "John Doe";
        addPeoplePage.enterName(peopleName);
        addPeoplePage.clickAddPersonButton();
        test.log(Status.INFO, "click add person button");

        List<String> peoplesName = addPeoplePage.getPeoplesName();
        softAssert.assertTrue(peoplesName.contains(peopleName), "Test failed  peoples name not matched");
        test.log(Status.INFO, "People name verified hence people successfully added");

        softAssert.assertAll();
    }
}
