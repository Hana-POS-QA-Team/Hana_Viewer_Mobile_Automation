package StepDefinition;

import ProjectBase.TestBaseClass;
import WebPageObjects.LoginPageObjects;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WebApp_Login_Functionality extends TestBaseClass {
    public LoginPageObjects lpg;
    @Given("Login to Hana POS Web App")
    public void login_to_hana_pos_web_app() {

        System.out.println("Browser Launched ");

    }
    @When("User Enters the {string} and {string} and click login")
    public void user_enters_and_and_click_login(String UserName, String Password) {
        lpg = new LoginPageObjects();
        lpg.EnterUserName(UserName);
        delayWithGivenTime(2000);
        pressEnter();
        lpg.EnterPassword(Password);
        delayWithGivenTime(1500);
        lpg.ClickLoginButton();
    }
    @Then("User Should logged into Hana POS App")
    public void user_should_logged_into_hana_pos_app() {
        System.out.println("User at the POS Dashboard Page");

    }
}
