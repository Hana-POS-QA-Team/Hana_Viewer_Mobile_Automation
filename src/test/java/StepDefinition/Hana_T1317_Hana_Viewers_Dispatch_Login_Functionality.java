package StepDefinition;

import PageObjects.DispatchLoginPageObjects;
import PageObjects.HomePageObjects;
import PageObjects.TripPageObjects;
import ProjectBase.TestBaseClass;
import Utilities.CustomSoftAssert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Hana_T1317_Hana_Viewers_Dispatch_Login_Functionality extends TestBaseClass {

    public static HomePageObjects homePageObjects = new HomePageObjects();
    public static DispatchLoginPageObjects dispatchLoginPageObjects = new DispatchLoginPageObjects();
    public static TripPageObjects tripPageObjects = new TripPageObjects();
    public static CustomSoftAssert softAssert = new CustomSoftAssert();
    public String global_userName, global_password;

    @Given("Launch Hana Viewer Mobile App")
    public void launch_hana_viewer_mobile_app() {
        allowNotification();
        System.out.println("Mobile App launched");
    }
    @When("User clicks the Hana Viewers App")
    public void user_clicks_the_hana_viewers_app() {
        System.out.println("User Clicked the Hana Viewer Mobile App and it is launched");
    }
    @Then("Hana viewer Home Page should be displayed")
    public void hana_viewer_home_page_should_be_displayed() {
        delayWithGivenTime(3000);
        softAssert.assertTrue( homePageObjects.verify_HomePage_Is_Displayed(),"Test step 1 : Home Page is not displaying");
    }
    @When("Verify whether the Dispatch Login button is displaying or not")
    public void verify_whether_the_dispatch_login_button_is_displaying_or_not() {
        softAssert.assertTrue(homePageObjects.verify_dispatch_button_Is_Displayed(),"Test Step 2 : Dispatch login button is not displaying");
    }
    @Then("Dispatch login button should be displayed")
    public void dispatch_login_button_should_be_displayed() {
        softAssert.assertTrue(homePageObjects.verify_dispatch_button_Is_Displayed(),"Test Step 2 : Dispatch login button is not displaying");
    }
    @When("User Click on the Dispatch Login button")
    public void user_click_on_the_dispatch_login_button() {
        homePageObjects.Click_Dispatch_button();
    }

    @Then("User should be navigated to the Dispatch login page")
    public void user_should_be_navigated_to_the_dispatch_login_page() {
        delayWithGivenTime(3000);
        softAssert.assertTrue(dispatchLoginPageObjects.verify_dispatch_page_Is_Displayed(),"Test Step 3 : Dispatch login page is not displaying");
    }
    @When("Verify whether the Dispatch login button is disabled or not")
    public void verify_whether_the_dispatch_login_button_is_disabled_or_not() {
        softAssert.assertFalse(dispatchLoginPageObjects.verify_Dispatch_Login_button_Is_Disabled(),"Test step 4 : Dispatch Login button is enabled");
    }
    @Then("Respective button should be displayed")
    public void respective_button_should_be_displayed() {
        softAssert.assertFalse(dispatchLoginPageObjects.verify_Dispatch_Login_button_Is_Disabled(),"Test step 4 : Dispatch Login button is enabled");
    }
    @When("Enter a valid {string} and {string}")
    public void enter_a_valid_userName_and_password(String UserName, String Password) {
        global_password = Password;
        global_userName = UserName;
        dispatchLoginPageObjects.Enter_userName_in_dispatch_UserName_field(UserName);
        delayWithGivenTime(2000);
        dispatchLoginPageObjects.Enter_password_in_dispatch_Password_field(Password);
    }
    @Then("Entered Data should be displayed in the respective fields")
    public void entered_data_should_be_displayed_in_the_respective_fields() {
        delayWithGivenTime(2500);
        softAssert.assertEquals(dispatchLoginPageObjects.Get_Text_in_the_UserName_field(),global_userName, "Test Step 5 : Entered value is not displaying properly in UserName field");
        softAssert.assertFalse(dispatchLoginPageObjects.Password_Field_Is_Empty(), "Test Step 5 : Entered value is not displaying properly in Password field");
    }
    @When("Verify whether the Dispatch Login button is enabled or not")
    public void verify_whether_the_dispatch_login_button_is_enabled_or_not() {
        softAssert.assertTrue(dispatchLoginPageObjects.verify_Dispatch_Login_button_Is_Disabled(),"Test step 6 : Dispatch Login button is enabled");

    }
    @Then("Respective button should be enabled")
    public void respective_button_should_be_enabled() {
        softAssert.assertTrue(dispatchLoginPageObjects.verify_Dispatch_Login_button_Is_Disabled(),"Test step 6 : Dispatch Login button is enabled");

    }
    @When("Click on the Dispatch Login button")
    public void click_on_the_dispatch_login_button() {
        dispatchLoginPageObjects.Click_Dispatch_Login_button();
    }

    @Then("User should be navigate to the Trips Page.")
    public void user_should_be_navigate_to_the_trips_page() {
        delayWithGivenTime(3000);
        Click_Allow_Button_On_Location_Popup();
        softAssert.assertTrue(tripPageObjects.verify_Trip_Page_Is_Displayed(),"Test Step 7 : Trip page is not displaying");
        softAssert.assertEquals(tripPageObjects.Get_Trip_Page_header_Text(),"Trips","Test step 7 : Trip page header is not displaying properly");
        softAssert.assertAll();
    }

}
