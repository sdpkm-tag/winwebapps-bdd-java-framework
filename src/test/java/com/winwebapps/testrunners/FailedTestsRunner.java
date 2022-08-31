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
                "html:target/cucumber-report/cucumber-summary-report-rerun.html",
                "json:target/cucumber-report/cucumber-rerun.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "junit:target/cucumber-report/cucumber-rerun.xml"},
        glue = {"com.winwebapps.stepdefs", "com.winwebapps.apphooks"},
        monochrome = true
)
public class FailedTestsRunner {
}
