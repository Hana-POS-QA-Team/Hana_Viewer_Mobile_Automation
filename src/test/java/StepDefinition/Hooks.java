package StepDefinition;

import ProjectBase.TestBaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Properties;

public class Hooks {
    public TestBaseClass testBaseClass = new TestBaseClass();

//    @Before(order = 1)
//    public void setup() {
//        //   startAppiumServer();
//        //   startEmulator();
//        //   launchAndroidApp();
//        testBaseClass.SuiteBeforeMethods();
//    }
    @Before(order = 0)
    public void chrome_setup() {
        //   startAppiumServer();
        //   startEmulator();
        //   launchAndroidApp();
        testBaseClass.loadConfig();
        testBaseClass.Suite_Chrome_Before_Method();
    }

//    @After(order=0)
//    public void teardown() {
//
//        testBaseClass.teardown();
//    }

    @After(order=1)
    public void chrome_teardown(){
        testBaseClass.tearDown_chrome();
    }

//    @AfterStep()
//    public void addScreenshot(Scenario scenario) {
//        if (scenario.isFailed()) {
//            TakesScreenshot ts = (TakesScreenshot) testBaseClass
//            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "image/png", scenario.getName());
//        }
//    }
}
