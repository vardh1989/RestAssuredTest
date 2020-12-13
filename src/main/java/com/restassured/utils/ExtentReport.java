package com.restassured.utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.restassured.constants.ConfigConstants;

import java.io.File;


public class ExtentReport {

    public static ExtentReports report = null;
    public static String extentreportpath = "";


    //To avoid external initialization
    private ExtentReport() {
        extentreportpath = ConfigConstants.EXTENTREPORTPATH;
        report = new ExtentReports(extentreportpath);
        report.loadConfig(new File(ConfigConstants.EXTENTCONFIGFILEPATH));

    }

    public static void initialize() {
        new ExtentReport();
    }
}
