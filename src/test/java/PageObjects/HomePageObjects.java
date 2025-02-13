package PageObjects;

import ProjectBase.TestBaseClass;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects extends TestBaseClass {

    public HomePageObjects(){
            PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View")
    private static WebElement HomePage_Body_Element;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Dispatch Login']")
    private static WebElement Dispatch_button;



    /**
     * This method verify whether the home page is displayed or not
     * @Description: This method verify whether the home page is displayed or not
     * @return: If the Home Page is deisplayed then it will return true else it will return false
     * @AAuthor: Sakrateesh R
     */
    public boolean verify_HomePage_Is_Displayed(){
        return HomePage_Body_Element.isDisplayed();
    }

    /**
     * This method is used to click the Dispatch Login button
     * @Description:This method is used to click the Dispatch Login button
     * @Author: Sakrateesh R
     */
    public void Click_Dispatch_button(){
        //HighlightElement(Dispatch_button);
        Dispatch_button.click();
    }

    /**
     * This method is used to verify whether the dispatch button is displaying or not.
     * @Description: This method is used to verify whether the dispatch button is displaying or not.
     * @return: Is the Dispatch button is displayed then it will return true else it will return false.
     * @Author: Sakrateesh R
     */
    public boolean verify_dispatch_button_Is_Displayed(){
        //HighlightElement(Dispatch_button);
        return Dispatch_button.isDisplayed();
    }

}
