package com.example.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeEach
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    @AfterEach
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String captureScreenshot(String scenarioName) {
        try {
            // Créer le dossier target/screenshots/ s'il n'existe pas
            File screenshotDir = new File("target/screenshots/");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs(); // Crée les répertoires nécessaires
            }
    
            // Convertir WebDriver en TakesScreenshot
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
    
            // Capturer l'image sous forme de tableau d'octets
            byte[] screenshotBytes = takesScreenshot.getScreenshotAs(OutputType.BYTES);
    
            // Générer un horodatage pour éviter les doublons
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
    
            // Construire un nom unique pour le fichier
            String screenshotName = scenarioName.replaceAll("[^a-zA-Z0-9_-]", "_") + "-" + timestamp + ".png";
    
            // Créer un fichier dans le répertoire target/screenshots/
            File screenshotFile = new File(screenshotDir, screenshotName);
    
            // Écrire les octets capturés dans le fichier
            try (FileOutputStream fos = new FileOutputStream(screenshotFile)) {
                fos.write(screenshotBytes);
            }
    
            // Afficher le chemin absolu pour confirmer la sauvegarde
            String screenshotPath = screenshotFile.getAbsolutePath();
            System.out.println("Capture d'écran enregistrée : " + screenshotPath);
    
            // Retourner le chemin du fichier pour utilisation ultérieure
            return screenshotPath;
    
        } catch (IOException e) {
            // Gérer les erreurs lors de l'écriture du fichier
            System.err.println("Erreur lors de la sauvegarde de la capture d'écran : " + e.getMessage());
            return "";
        } catch (Exception e) {
            // Gérer toute autre erreur
            System.err.println("Erreur lors de la capture d'écran : " + e.getMessage());
            return "";
        }
    }
    
}