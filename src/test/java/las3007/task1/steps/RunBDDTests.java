package las3007.task1.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true, // better console output
        strict = true, // undefined and pending steps should be treated as errors
        features = "src/test/resources/features" // path to feature files
)

public class RunBDDTests {
}

