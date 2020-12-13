package com.restassured.utils;

import com.restassured.utils.ExtentManager;
import com.restassured.utils.ExtentReport;
import com.restassured.utils.TestUtils;
import org.apache.poi.ss.formula.functions.T;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


/*
 * Listener class which is implementing ITestListener and hence we can use this to dynamically create reports, write logs.
 */
public class Listners implements ITestListener{

    private static String TestcaseName;

    public static String getTestcaseName() {
        return TestcaseName;
    }

    
    public static void setTestcaseName(String testcaseName) {
        TestcaseName = testcaseName;
    }

    public void onTestStart(ITestResult result) {
        TestcaseName =result.getMethod().getMethodName();
        setTestcaseName(TestcaseName);
        ExtentManager.setExtentTest(ExtentReport.report.startTest(TestcaseName));
        LogStatus.pass(TestcaseName+ " is started successfully");

    }

    public void onTestSuccess(ITestResult result) {

        LogStatus.pass(result.getMethod().getMethodName()+ " test case is passed");
        ExtentReport.report.endTest(ExtentManager.getExtTest());
    }

    public void onTestFailure(ITestResult result) {

        LogStatus.fail(result.getMethod().getMethodName()+ " is failed");
        LogStatus.fail(result.getThrowable().toString());
        ExtentReport.report.endTest(ExtentManager.getExtTest());

    }

    public void onTestSkipped(ITestResult result) {

        LogStatus.skip(result.getMethod().getMethodName()+ " is skipped");

        ExtentReport.report.endTest(ExtentManager.getExtTest());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ExtentReport.report.endTest(ExtentManager.getExtTest());
    }

    public void onStart(ITestContext context) {


    }

    public void onFinish(ITestContext context) {
        ExtentReport.report.endTest(ExtentManager.getExtTest());

    }


}
