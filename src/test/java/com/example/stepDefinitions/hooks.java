package com.example.stepDefinitions;

import com.example.base.TestBase;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class hooks {

    @Before
    public void setUp() {
        if (TestBase.getDriver() == null) {
            TestBase.setUp();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            TestBase testBase = new TestBase();
            testBase.captureScreenshot(screenshotName);
        }
        TestBase.tearDown();
    }
}
