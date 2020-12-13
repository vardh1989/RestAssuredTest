package tests;

import com.google.common.io.Files;
import com.restassured.constants.ConfigConstants;
import com.restassured.constants.ResourceConstants;

import com.restassured.utils.TestUtils;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import static io.restassured.RestAssured.given;


public class ResourceTest extends BaseTest {

    @Test(dataProvider = "dataProviderForIterations",dataProviderClass = TestUtils.class)
    public void VerifyResponseDataForSingleResource(Hashtable<String, String> data) throws IOException {
        /*
         * Replacing the ID parameter in the endpoint with the data from excel sheet.
         * Data providers return a hashtable and the column name is used as a key to get the value
         */
        Response response = given()
                .filter(new RequestLoggingFilter(captor)) //This line is mandatory to log the request details to extent report
                .log()
                .all()
                .get(ConfigConstants.BASEURL + ResourceConstants.API_GET_RESOURCE.replace("{resourceId}", data.get("ResourceId")));

        writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());

        //Asserting the Resource ID, Name & Year in the response using jsonPath.
            //Expected value is from TESTDATA sheet and column expectedCountryCapital
            Assert.assertEquals(String.valueOf(response.getStatusCode()),data.get("expectedResponseCode"));
            Assert.assertEquals((response.jsonPath().get("data.id")).toString(), (data.get("expectedID")));
            Assert.assertEquals(response.jsonPath().get("data.name"), data.get("expectedName"));
            Assert.assertEquals(response.jsonPath().get("data.year").toString(), data.get("expectedYear"));
            //Writing the response to an log file
            Files.write(response.asByteArray(),
                    new File(ConfigConstants.RESPONSETXTPATH + data.get("TestCaseName") + data.get("ResourceId") + ".txt"));

    }

    @Test(dataProvider = "dataProviderForIterations",dataProviderClass = TestUtils.class)
    public void VerifyInvalidStatusCodeForSingleResource(Hashtable<String, String> data) throws IOException {
        /*
         * Replacing the ID parameter in the endpoint with the data from excel sheet.
         * Data providers return a hashtable and the column name is used as a key to get the value
         */
        Response response = given()
                .filter(new RequestLoggingFilter(captor)) //This line is mandatory to log the request details to extent report
                .log()
                .all()
                .get(ConfigConstants.BASEURL + ResourceConstants.API_GET_RESOURCE.replace("{resourceId}", data.get("ResourceId")));

        //Writing the request and response to extent report
            writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());

            //Asserting the Resource ID, Name & Year in the response using jsonPath.
            //Expected value is from TESTDATA sheet and column expectedResponseCode
            Assert.assertEquals(String.valueOf(response.getStatusCode()),data.get("expectedResponseCode"));
            ///Validating empty response on empty fields
            Assert.assertNull(response.getBody().jsonPath().getJsonObject("data"));
            Assert.assertNull(response.getBody().jsonPath().getJsonObject("support"));
            //Writing the response to an log file
            Files.write(response.asByteArray(),
                    new File(ConfigConstants.RESPONSETXTPATH + data.get("TestCaseName") + data.get("ResourceId") + ".txt"));
        }

}
