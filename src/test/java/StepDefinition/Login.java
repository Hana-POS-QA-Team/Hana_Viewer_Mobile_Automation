package StepDefinition;

import PageObjects.LoginPageObjects;
import ProjectBase.TestBaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login extends TestBaseClass {
    public static LoginPageObjects loginPageObjects = new LoginPageObjects();
    @Given("Login to Hana Viewer Mobile App")
    public void login_to_hana_viewer_mobile_app() {

        openNotifications();
        delayWithGivenTime(2000);
        loginPageObjects.Click_Dispatch_button();
        delayWithGivenTime(3000);

    }
    @When("User Enters {string} and {string} and click login")
    public void user_enters_and_and_click_login(String UserName, String Password) {
        loginPageObjects.Enter_userName_in_dispatch_UserName_field(UserName);
        delayWithGivenTime(2000);
        pressEnter();
        loginPageObjects.Enter_password_in_dispatch_Password_field(Password);
        delayWithGivenTime(1500);
        pressEnter();
    }
    @Then("User Should logged into Hana Driver Login")
    public void user_should_logged_into_hana_driver_login() {
        loginPageObjects.Click_Dispatch_Login_button();

    }

}
