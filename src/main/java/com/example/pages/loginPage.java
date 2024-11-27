package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

/**
 * Hello world!
 *
 */
public class loginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    static final String URL = "https://the-internet.herokuapp.com/login";

    public loginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        ;
    }

    public void open() {
        driver.get(URL);
    }

    public void enterusername(String username) {
        WebElement usernameFild = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameFild.sendKeys(username);

    }

    public void enterpassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        WebElement loginButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        loginButton.click();
    }

    public String getMessage() {
        WebElement message = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='flash']")));
        return message.getText();
    }

    public void login() {
        WebElement loginBtn = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        loginBtn.click();
    }

    public String getMessageErr() {
        WebElement message = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='flash']")));
        return message.getText();
    }

}
