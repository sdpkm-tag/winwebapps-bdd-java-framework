package com.winwebapps.testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@WinApp or @WebApp",
        dryRun = false,
        plugin = {"pretty",
                "summary",
                "html:target/cucumber-report/cucumber-summary-report.html",
                "json:target/cucumber-report/cucumber.json",
                "junit:target/cucumber-report/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/cucumber-report/rerun.txt"},
        glue = {"com.winwebapps.stepdefs", "com.winwebapps.apphooks"},
        monochrome = true
)
public class TestSuiteRunner {
}
