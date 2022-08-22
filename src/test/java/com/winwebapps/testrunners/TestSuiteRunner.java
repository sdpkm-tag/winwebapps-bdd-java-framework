package com.winwebapps.testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@WinApp or @WebApp",
        dryRun = false,
        plugin = {"pretty", "summary", "html:target/cucumber-report/cucumber-summary-report.html", "json:target/cucumber-report/cucumber.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue = {"com.winwebapps.stepdefs", "com.winwebapps.apphooks"}
)
public class TestSuiteRunner {
}
