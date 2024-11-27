package com.example.stepDefinitions;

import com.aventstack.extentreports.ExtentTest;
import com.example.base.TestBase;
import com.example.extentReports.ExtentManager;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class hooks {
    public static ExtentTest _scenario;

    @Before
    public void setUp(Scenario scenario) {
        if (TestBase.getDriver() == null) {
            TestBase.setUp();
        }
        _scenario = ExtentManager.getInstance().createTest(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            String screenshotPath = new TestBase().captureScreenshot(screenshotName);

            try {
                _scenario.fail("Test Failed",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (Exception e) {
                _scenario.log(Status.FAIL, "Failed to attach screenshot: " + e.getMessage());
            }
        }

        TestBase.tearDown();
        ExtentManager.getInstance().flush();
    }
}