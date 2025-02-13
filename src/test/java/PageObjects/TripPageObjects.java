package PageObjects;

import ProjectBase.TestBaseClass;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class TripPageObjects extends TestBaseClass {
        public  TripPageObjects(){
            PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
        }
//android.view.View[@content-desc="Trips"

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Trips\"]")
    private WebElement Trip_Page_header;

    /**
     * This method will verify whether the Trip Page is displayed or not
     * @Description: This method will verify whether the Trip Page is displayed or not
     * @return: If the Page is displayed it will return true else it will return false
     * @Author: Sakrateesh R
     */
    public boolean verify_Trip_Page_Is_Displayed(){
        return Trip_Page_header.isDisplayed();
    }

    /**
     * This method will get the text of Trip Page
     * @Description: This method will get the text of Trip Page
     * @return: It will return the header text value
     * @Author: Sakrateesh R
     */
    public String Get_Trip_Page_header_Text(){
        fluentWait(Trip_Page_header);
        return Trip_Page_header.getDomAttribute("content-desc");
    }

}
