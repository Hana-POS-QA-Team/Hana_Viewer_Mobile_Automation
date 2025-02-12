package PageObjects;

import ProjectBase.TestBaseClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects extends TestBaseClass {
    public LoginPageObjects() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Dispatch Login']")
    private static WebElement Dispatch_button;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")
    private static WebElement UserName_Textbox_field;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]")
    private static WebElement Password_Textbox_field;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Dispatch Login']")
    private static WebElement Dispatch_Login_button;

    public void Click_Dispatch_button(){
        //HighlightElement(Dispatch_button);
        Dispatch_button.click();
    }

    public void Click_Dispatch_Login_button(){
        Dispatch_Login_button.click();
    }

    public void Enter_userName_in_dispatch_UserName_field(String userName){
        click_and_type(UserName_Textbox_field,userName);

    }

    public void Enter_password_in_dispatch_Password_field(String password){
        clickAndType(Password_Textbox_field, password);

    }

}
