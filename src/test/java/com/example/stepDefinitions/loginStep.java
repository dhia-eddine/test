package com.example.stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.base.TestBase;
import com.example.pages.loginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginStep extends TestBase {
    public loginPage loginPage;

    public loginStep() {
        loginPage = new loginPage(getDriver());
    }
    // @Test
    // public void loginTest() {
    // loginPage loginPage = new loginPage(driver);
    // loginPage.open();
    // loginPage.enterusername("tomsmith");
    // loginPage.enterpassword("SuperSecretPassword!");
    // loginPage.clickLogin();
    // String mess = loginPage.getMessage();
    // assertTrue(mess.contains("You logged into a secure area!"));
    // }

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        loginPage.open();
    }

    @Then("the user should see a successful login message")
    public void verifySuccessfulLogin() {
        String message = loginPage.getMessage();
        assertTrue(message.contains("You logged into a secure area!"));
    }

    @And("clicks on the login button")
    public void clicksLoginButton() {
        loginPage.clickLogin();
    }

    @And("the user enters a password")
    public void userEntersPassword() {
        loginPage.enterpassword("SuperSecretPassword!");
    }

    @When("the user enters a username")
    public void userEntersUserName() {
        loginPage.enterusername("tomsmith2");
    }

    @Then("the user should see an error message")
    public void TheUserShouldEeeAnErrorMessage() {
        String message = loginPage.getMessage();
        assertTrue(message.contains("Your username is invalid!") || message.contains("Your password is invalid!")); 
    }

}
