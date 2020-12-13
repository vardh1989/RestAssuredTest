# RestAssuredTest

Test Driven API Automation Framwork built with Rest Assured, Java, Maven, TestNG, Extent Reports.
==================================================================================================

1) Environment 
Java Version : 1.8 
Node JS 10+
IDE : Intellij , Eclipse etc

===================================================
2) Clone the project in the Local System

git clone https://github.com/vardh1989/RestAssuredTest.git

cd RestAssuredTest

====================================================
3) Running the Test Suite 

One can open terminal and navigate to project folder Ex : "/Users/123/245/RestAssuredTest"
Trigeer the suite using "mvn clean test"

One can also trigger the build using testng.xml file
Run the testng.xml file from project folder.

Note: If you try to run individual tests as testng test, you will get NPE as the listeners are configured in testng.xml only.

======================================================

4) Execution Settings and Reports 

Open the Excel Sheet  under the ~/RestAssuredTest/src/test/resources/testdata_assignment.xlsx folder

In the RunnerSheet  -->Choose the test cases you want to run by choosing yes in execute 

In the TestData sheet --->Choose the test data you want to pass to the testcase from excel sheet.

The data from the excel sheet will be passed to the test method as a hashtable.

Once  Suite is completed , Reports will be flushed to browder or can be found in ~/RestAssuredTest/ExtentReports/index.html






