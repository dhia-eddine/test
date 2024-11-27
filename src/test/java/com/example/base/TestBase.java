package com.example.base;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String captureScreenshot(String scenarioName) {
        try {
            // Create unique filename with timestamp
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String screenshotName = scenarioName.replaceAll("[^a-zA-Z0-9-]", "") + "-" + timestamp + ".png";

            // Define screenshot path
            File screenshotDir = new File("target/screenshots");
            if (!screenshotDir.exists()) screenshotDir.mkdirs();
            File screenshotFile = new File(screenshotDir, screenshotName);

            // Capture screenshot and save to file
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            byte[] screenshotBytes = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            org.apache.commons.io.FileUtils.writeByteArrayToFile(screenshotFile, screenshotBytes);

            // Return the absolute path of the screenshot
            return screenshotFile.getAbsolutePath();
        } catch (Exception e) {
            System.err.println("Error capturing screenshot: " + e.getMessage());
            return "";
        }
    }
}
