package com.restassured.constants;

public class ConfigConstants {
    public static final String EXCELPATH = System.getProperty("user.dir") + "/src/test/resources/testdata_assignment.xlsx";
    public static final String TESTDATASHEETNAME = "TestData";
    public static final String EXTENTREPORTPATH = System.getProperty("user.dir") + "/ExtentReports/index.html";
    public static final String EXTENTCONFIGFILEPATH = System.getProperty("user.dir") + "/src/test/resources/extentreport.xml";

    //public static final String SCHEMAVALIDATIONJSONPATH = System.getProperty("user.dir") +
    //s        "/src/test/resources/jsonsforschemavalidations/countrydetails_response_schema.json";
    public static final String RESPONSETXTPATH = System.getProperty("user.dir") + "/output/responses/";
    public static final String RUNMANAGERSHEET = "RunnerSheet";
    //public static final String JSONSLOCATION = System.getProperty("user.dir") + "/src/test/resources/jsons";
    public static final String BASEURL = "https://reqres.in";

    //Request xml paths
    public static final String REQUEST_JSON_FOLDER_PATH = System.getProperty("user.dir") +
            "/src/test/resources/jsonsforrequestbody/";

    private ConfigConstants() {

    }

}
