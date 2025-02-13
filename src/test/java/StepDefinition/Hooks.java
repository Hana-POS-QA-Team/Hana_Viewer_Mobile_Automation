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

    @Before(order = 1)
    public void setup() {
        //   startAppiumServer();
        //   startEmulator();
        //   launchAndroidApp();
        testBaseClass.SuiteBeforeMethods();
    }

    @After(order=0)
    public void teardown() {

        testBaseClass.teardown();
    }


}
