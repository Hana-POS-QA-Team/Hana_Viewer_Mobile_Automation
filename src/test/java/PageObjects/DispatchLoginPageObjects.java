package PageObjects;

import ProjectBase.TestBaseClass;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DispatchLoginPageObjects extends TestBaseClass {
    public DispatchLoginPageObjects() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }


    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")
    private static WebElement UserName_Textbox_field;

    @AndroidFindBy(xpath = "//android.widget.EditText[@password='true']")
    private static WebElement Password_Textbox_field;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Dispatch Login']")
    private static WebElement Dispatch_Login_button;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Dispatch Login']")
    private static WebElement Dispatch_page_header;
    /**
     * This method is used to click the Dispatch Login button
     * @Description:This method is used to click the Dispatch Login button
     * @Author: Sakrateesh R
     */
    public void Click_Dispatch_Login_button(){
        Dispatch_Login_button.click();
    }

    /**
     * This method is used to verify whether the dispatch login button is enabled or not.
     * @Description: This method is used to verify whether the dispatch login button is enabled or not.
     * @return: if the dispatch login button is enabled it will return true else it will return false
     * @Author: Sakrateesh R
     */
    public boolean verify_Dispatch_Login_button_Is_Disabled(){
        return Dispatch_Login_button.getDomAttribute("clickable").equals("true");
    }

    /**
     * This method is used enter a value in the dispatch UserName field
     * @Description:This method is used enter a value in the dispatch UserName field
     * @Author: Sakrateesh R
     */
    public void Enter_userName_in_dispatch_UserName_field(String userName){
        click_and_type(UserName_Textbox_field,userName);
        pressEnter();

    }

    /**
     * This method is used enter a value in the dispatch Password field
     * @Description:This method is used enter a value in the dispatch Password field
     * @Author: Sakrateesh R
     */
    public void Enter_password_in_dispatch_Password_field(String password){
        fluentWait(Password_Textbox_field);
        click_and_type(Password_Textbox_field, password);
        pressEnter();

    }

    /**
     * This method verifies whther the Dispatch Login Page is displayed or not.
     * @Description: This method verifies whther the Dispatch Login Page is displayed or not.
     * @return: If Dispatch Login Page is displayed it will return true else it will return false.
     * @Author: Sakrateesh R
     */
    public boolean verify_dispatch_page_Is_Displayed(){

        return Dispatch_page_header.getDomAttribute("content-desc").equals("Dispatch Login");
    }

    /**
     * This method will return the value in the Username field
     * @Description: This method will return the value in the Username field
     * @return: The text present in the Username field.
     * @Author: Sakrateesh R
     */
    public String Get_Text_in_the_UserName_field(){
        return UserName_Textbox_field.getDomAttribute("text");
    }

    /**
     * This method will verify whether the password field is empty or not
     * @Description: This method will verify whether the password field is empty or not
     * @return: if password field is empty it will return true else it will return false
     * @Author: Sakrateesh R
     */
    public boolean Password_Field_Is_Empty(){
        return Password_Textbox_field.getDomAttribute("text").isEmpty();
    }

}
