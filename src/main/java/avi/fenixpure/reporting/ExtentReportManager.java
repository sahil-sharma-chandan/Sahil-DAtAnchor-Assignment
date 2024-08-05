package avi.fenixpure.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class ExtentReportManager {
    private static ExtentReports extentReports;
    private static ExtentTest test;

    static {
        // Set up the Spark reporter and attach it to ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(new File("reports/extent-report.html").getAbsolutePath());
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    // Method to get the ExtentReports instance
    public static ExtentReports getExtentReports() {
        return extentReports;
    }

    // Method to create a new test in the report
    public static ExtentTest createTest(String testName) {
        test = extentReports.createTest(testName);
        return test;
    }

    // Method to flush the reports
    public static void flush() {
        extentReports.flush();
    }
}