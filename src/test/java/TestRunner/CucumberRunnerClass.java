package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features", // Path to feature files
        glue = "StepDefinition",                // Package name for step definitions
        plugin = {"pretty",
                "html:reports/Ecommerce_Automation_Cucumber_Report.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
                , "rerun:target/rerun.txt"
        },

        dryRun = false,    // checks mapping between scenario steps and step definition methods
        monochrome = true,    // to avoid junk characters in output
        publish = true   // to publish report in cucumber server
        //tags="@sanity"  // this will execute scenarios tagged with @sanity
        //tags="@regression"
        //tags="@sanity and @regression" //Scenarios tagged with both @sanity and @regression
        //tags="@sanity and not @regression" //Scenarios tagged with @sanity but not tagged with @regression
        //tags="@sanity or @regression" //Scenarios tagged with either @sanity or @regression
)
public class CucumberRunnerClass {
}
