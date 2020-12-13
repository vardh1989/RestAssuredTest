package tests;


import com.google.common.base.Charsets;
import com.restassured.constants.ConfigConstants;
import com.restassured.utils.ExtentReport;
import com.restassured.utils.Listners;
import com.restassured.utils.TestUtils;
import com.restassured.utils.LogStatus;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;

public class BaseTest {
    protected StringWriter writer;
    protected PrintStream captor;

    /**
     * Initializing the extent report and TestUtils
     */
    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() throws Exception {

        ExtentReport.initialize();
        TestUtils.getRunStatus();

    }

    /**
     * Flushing the extent report
     * Opening the extent report automatically after the test suite execution.
     */

    @AfterSuite
    public void afterSuite() throws IOException {
        ExtentReport.report.flush();
        File htmlFile = new File(ConfigConstants.EXTENTREPORTPATH);
        Desktop.getDesktop().browse(htmlFile.toURI());

    }

    /**
     * This method helps to write the request and response to the extent report
     */
    @BeforeMethod
    public void setUp(){
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer, Charsets.UTF_8), true);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Throwable testResult = result.getThrowable();
            LogStatus.fail(testResult.getMessage());
        }
    }

    /**
     * Format the api string and log in Extent Report
     * @param content of the api
     */
    protected void formatAPIAndLogInReport(String content) {

        String prettyPrint = content.replace("\n", "<br>");
        LogStatus.info("<pre>" + prettyPrint + "</pre>");

    }

    /**
     * To Write request and response fields in the Extent report
     * @param  request as string ,response as string
     */
    public void writeRequestAndResponseInReport(String request, String response) {
        LogStatus.info("---- Request ---");
        formatAPIAndLogInReport(request);
        LogStatus.info("---- Response ---");
        formatAPIAndLogInReport(response);
    }


}
