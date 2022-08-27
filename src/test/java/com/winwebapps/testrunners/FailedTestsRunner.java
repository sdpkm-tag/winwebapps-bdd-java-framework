package com.winwebapps.testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/cucumber-report/rerun.txt",
        dryRun = false,
        plugin = {"pretty",
                "summary",
                "html:test-output/failed/cucumber-report/cucumber-summary-report.html",
                "json:test-output/failed/cucumber-report/cucumber.json",
                "junit:test-output/failed/cucumber-report/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue = {"com.winwebapps.stepdefs", "com.winwebapps.apphooks"},
        monochrome = true
)
public class FailedTestsRunner {
}
