package com.restassured.utils;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class AnnotationProcessor implements IAnnotationTransformer {

    int count = 0;

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

        try {
            if (count == 0) {
                TestUtils.getRunStatus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < TestUtils.testCases.size(); i++) {
            if (testMethod.getName().equalsIgnoreCase(TestUtils.testCases.get(i))) {
                //sets the data provider to all the test methods
                annotation.setDataProvider("dataProviderForIterations");
                //sets the retry analyser for all the test cases
                annotation.setDataProviderClass(TestUtils.class);
                //sets the priority for all the test cases based on the excel sheet input
                annotation.setPriority((int) Double.parseDouble(TestUtils.priority.get(i)));
                //sets the description for all the test cases based on the excel sheet input
                annotation.setDescription(TestUtils.testDescription.get(i));
                //sets the invocation count for all the test cases based on the excel sheet input
                annotation.setInvocationCount((int) Double.parseDouble(TestUtils.invocationCount.get(i)));
                //sets the enabled parameter for all the test cases based on the excel sheet input
                if (TestUtils.runStatus.get(i).equalsIgnoreCase("no")) {
                    annotation.setEnabled(false);
                    break;
                }
            }
        }

        count++;
    }
}
