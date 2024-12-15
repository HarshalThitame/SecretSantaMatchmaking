package extentlisteners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentInstance {

    public static String fileName;

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter htmlExtentReport = new ExtentSparkReporter(fileName);

        htmlExtentReport.config().setTheme(Theme.STANDARD);
        htmlExtentReport.config().setDocumentTitle(fileName);
        htmlExtentReport.config().setEncoding("UTF-8");
        htmlExtentReport.config().setReportName("ExtentReport");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(htmlExtentReport);
        extentReports.setSystemInfo("Automation Tester", "Harshal Thitame");
        extentReports.setSystemInfo("Build No", "B-102");
        return extentReports;

    }
}
