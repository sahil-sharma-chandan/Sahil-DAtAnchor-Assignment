package avi.fenixpure.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Ensure this path is correct
        glue = "avi.fenixpure.tests",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class TestRunner {
}