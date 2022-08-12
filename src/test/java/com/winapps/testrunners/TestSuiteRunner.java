package com.winapps.testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@webapp or @winapps",
        dryRun = false,
        plugin = {"pretty", "summary", "html:target/cucumber-report/cucumber-summary-report.html", "json:target/cucumber-report/cucumber.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue = {"com.winapps.stepdefs", "com.winapps.apphooks"}
)
public class TestSuiteRunner {
}
