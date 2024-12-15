package extentlisteners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Date;

public class ExtentManager implements ITestListener {

    public static Date date = new Date();
    public static ExtentTest test;
    //    public static String fileName = "Extent_Report_" + date.toString().replace(":", "_").replace(" ", "_") + ".html";
    public static String fileName = "ExtentReport.html";
    ExtentReports extent = ExtentInstance.createInstance(".\\reports\\" + fileName);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        test = extent.createTest(iTestResult.getTestClass().getName() + "@TestCase" + iTestResult.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String methodName = iTestResult.getMethod().getMethodName();
        String text = "Test case passed." + methodName.toUpperCase();
        Markup m = MarkupHelper.createLabel(text, ExtentColor.GREEN);

        test.log(Status.PASS, m);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String methodName = iTestResult.getMethod().getMethodName();
        String text = "Test case failed." + methodName.toUpperCase();
        Markup m = MarkupHelper.createLabel(text, ExtentColor.RED);
        BaseTest baseTest = (BaseTest) iTestResult.getInstance();
//        baseTest.captureScreenshot(iTestResult);
        test.log(Status.FAIL, iTestResult.getThrowable().getMessage());
        test.fail(m);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        test.log(Status.SKIP, "Test skipped: " + iTestResult.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        test.log(Status.FAIL, "Test failed but within success percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        // Can be used to set up context-wide settings
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        if (extent != null) {
            extent.flush();
        }
    }
}
