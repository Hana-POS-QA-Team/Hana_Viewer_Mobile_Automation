package Utilities;

import ProjectBase.TestBaseClass;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;


/**
 * This class is used to capture screenshot on soft assert failure and add it to reports
 *
 * @author Balaji N
 * @Description: This Class overide the onAssertFailure method of Assert class to customize take screenshot on the assert failure
 */
public class CustomSoftAssert extends SoftAssert {
    TestBaseClass base = new TestBaseClass();

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        // Capture screenshot immediately on soft assert failure
//        String screenshotPath = TestBaseClass.captureScreenshotBase64();
//        ExtentReportManager.getTest().fail("Assertion failed: " + assertCommand.getMessage())
//                .addScreenCaptureFromBase64String(screenshotPath, "Test Step failed");

        byte[] screenshot = ((TakesScreenshot) TestBaseClass.getDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.getLifecycle().addAttachment("Screenshot on Failure", "image/png", "png", screenshot);
    }
}